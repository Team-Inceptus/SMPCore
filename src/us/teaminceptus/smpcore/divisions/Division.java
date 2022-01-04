package us.teaminceptus.smpcore.divisions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.commands.Value;

public class Division {
	
	private static final FileConfiguration FILE = SMPCore.getDivisionsConfig();
	
	private final String id;
	private final ConfigurationSection section;
	
	private OfflinePlayer owner;
	private int level;
	private double experience;
	private String name;
	private Map<OfflinePlayer, Rank> playersMapped;
	private List<OfflinePlayer> players;
	private Map<Setting, Boolean> settings;
	private List<Invite> invites;
	
	private Location corner1;
	private Location corner2;
	private Location home;
	
	protected Division(OfflinePlayer owner, String id, String name, ConfigurationSection section) {
		this.id = id;
		this.name = name;
		this.section = section;
		this.owner = owner;
		this.level = 0;
		this.experience = 0.0D;
		
		Map<OfflinePlayer, Rank> playersMapped = new HashMap<>();
		playersMapped.put(owner, Rank.OWNER);
		this.playersMapped = playersMapped;
		
		List<OfflinePlayer> players = new ArrayList<>();
		players.add(owner);
		this.players = players;
		
		Map<Setting, Boolean> settings = new HashMap<>();
		settings.put(Setting.PVP_ENABLED, true);
		settings.put(Setting.PASSIVE_ENABLED, false);
		settings.put(Setting.CLOSED, false);
		settings.put(Setting.EXPLOSION_BREAK, false);
		settings.put(Setting.FIRE_BREAK, false);
		
		this.settings = settings;
		
		this.invites = new ArrayList<>();
	}
	
	protected Division(OfflinePlayer owner, String id, String name, ConfigurationSection section, Map<OfflinePlayer, Rank> players, Map<Setting, Boolean> settings, List<Invite> invites) {
		this.id = id;
		this.name = name;
		this.section = section;
		this.owner = owner;
		List<OfflinePlayer> totalP = new ArrayList<>();
		totalP.addAll(players.keySet());
		this.players = totalP;
		
		this.playersMapped = players;
		this.settings = settings;
		this.invites = invites;
		this.level = section.getInt("level");
		this.experience = section.getDouble("total_experience");
		this.corner1 = section.getLocation("corner_1");
		this.corner2 = section.getLocation("corner_2");
		this.home = section.getLocation("home");
	}
	
	static Random r = new Random();
	
	public static enum Rank {
		MEMBER,
		ADMIN,
		OWNER
	}
	
	public static enum Setting {
		PVP_ENABLED,
		PASSIVE_ENABLED,
		FIRE_BREAK,
		EXPLOSION_BREAK,
		CLOSED,
	}
	
	public static class Invite {
		
		private final OfflinePlayer invitee;
		private final Division div;
		private final String id;
		
		private static List<Invite> invites = new ArrayList<>();
		
		public Invite(OfflinePlayer invitee, Division div) {
			this.div = div;
			this.invitee = invitee;
			this.id = generateID();
			
			invites.add(this);
		}
		
		public final OfflinePlayer getPlayer() {
			return this.invitee;
		}
		
		public static List<Invite> getAllInvites() {
			return invites;
		}
		
		public static Invite getById(String id) {
			for (Invite invite : getAllInvites()) {
				if (invite.getID().equals(id)) return invite;
				else continue;
			}
			return null;
		}
		
		public final void expire() {
			invites.remove(this);
			div.invites.remove(this);
			div.broadcastMessage(ChatColor.YELLOW + "The invite from " + invitee.getName() + " has expired");
			
			if (this.invitee.isOnline()) {
				Player p = (Player) this.invitee;
				p.sendMessage(ChatColor.RED + "Your invite for " + div.getName() + " has expired!");
			}
		}
		
		public final void accept() {
			invites.remove(this);
			div.invites.remove(this);
			div.addPlayer(invitee, Rank.MEMBER);
			div.broadcastMessage(invitee.getName() + " has joined " + div.getName() + ChatColor.YELLOW + "!");
		}
		
		public final void reject() {
			invites.remove(this);
			div.invites.remove(this);
			if (invitee.isOnline()) {
				Player p = (Player) invitee;
				p.sendMessage(ChatColor.RED + "Your invite for " + div.getName() + " has been rejected!");
			}
		}
		
		public static Invite getByPlayer(OfflinePlayer p) {
			for (Invite invite : getAllInvites()) {
				if (invite.getPlayer().getUniqueId().equals(p.getUniqueId())) return invite;
				else continue;
			}
			return null;
		}
		
		public final Division getDivision() {
			return this.div;
		}
		
		public final String getID() {
			return this.id;
		}
		
	}
	
	/**
	 * Creates a Divison
	 * @param owner The owner
	 * @param name The name of the division
	 * @return The division
	 * @throws IllegalStateException If there are too many divisions
	 * @throws UnsupportedOperationException If already exists
	 */
	public static final Division createDivision(OfflinePlayer owner, String name) throws IllegalStateException, UnsupportedOperationException {
		String id = generateID();
		if (FILE.isConfigurationSection(name.toUpperCase())) {
			throw new UnsupportedOperationException("Division already exists");
		}
		
		if (getDivisions().size() >= 100) {
			throw new IllegalStateException("Maximum amount of divisions");
		}
		
		ConfigurationSection sec = FILE.createSection(id);
		
		Division div = new Division(owner, id, name, sec);
		div.checkDivision();
		return div;
	}
	
	public static Division getById(String id) {
		if (!(FILE.isConfigurationSection(id))) return null;
		
		ConfigurationSection sec = FILE.getConfigurationSection(id);
		
		Map<OfflinePlayer, Rank> players = new HashMap<>();
		sec.getConfigurationSection("players").getValues(true).forEach((uuid, obj) -> {
			if (!(obj instanceof ConfigurationSection player)) return;
			OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
			Rank rnk = Rank.valueOf(player.getString("rank"));
			players.put(pl, rnk);
		});
		
		Map<Setting, Boolean> settings = new HashMap<>();
		sec.getConfigurationSection("settings").getValues(false).forEach((setting, value) -> {
			if (!(value instanceof Boolean b)) return;
			Setting stng = Setting.valueOf(setting.toUpperCase());
			settings.put(stng, b);
		});
		
		List<Invite> invites = new ArrayList<>();
		sec.getConfigurationSection("invites").getKeys(false).forEach((inviteid -> {
			invites.add(Invite.getById(inviteid));
		}));
		
		return new Division(sec.getOfflinePlayer("owner"), id, sec.getString("name"), sec, players, settings, invites);
	}
	
	public static final List<Division> getDivisions() {
		List<Division> divisions = new ArrayList<>();
		
		List<ConfigurationSection> sections = new ArrayList<>();
		for (String sec : FILE.getKeys(false)) {
			sections.add(FILE.getConfigurationSection(sec));
		}
		
		for (ConfigurationSection s : sections) {
			Map<OfflinePlayer, Rank> players = new HashMap<>();
			s.getConfigurationSection("players").getValues(true).forEach((uuid, obj) -> {
				if (!(obj instanceof ConfigurationSection player)) return;
				OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
				Rank rnk = Rank.valueOf(player.getString("rank"));
				players.put(pl, rnk);
			});
			
			Map<Setting, Boolean> settings = new HashMap<>();
			s.getConfigurationSection("settings").getValues(false).forEach((setting, value) -> {
				if (!(value instanceof Boolean b)) return;
				Setting stng = Setting.valueOf(setting.toUpperCase());
				settings.put(stng, b);
			});
			
			List<Invite> invites = new ArrayList<>();
			s.getConfigurationSection("invites").getKeys(false).forEach((inviteid -> {
				invites.add(Invite.getById(inviteid));
			}));
			
			Division div = new Division(s.getOfflinePlayer("owner"), s.getString("name"), s.getString("id"), s, players, settings, invites);
			divisions.add(div);
		}
		
		return divisions;
	}
	
	public static Division getByName(String s) {
		for (Map.Entry<String, Object> entry : FILE.getValues(true).entrySet()) {
			if (!(FILE.isConfigurationSection(entry.getKey()))) continue;
			ConfigurationSection sec = FILE.getConfigurationSection(entry.getKey());
			
			if (sec.getString("name") == null) continue;
			if (sec.getString("name").equalsIgnoreCase(s)) {
				Map<OfflinePlayer, Rank> divplayers = new HashMap<>();
				sec.getConfigurationSection("players").getValues(true).forEach((uuid, obj) -> {
					if (!(obj instanceof ConfigurationSection player)) return;
					OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
					Rank rnk = Rank.valueOf(player.getString("rank"));
					divplayers.put(pl, rnk);
				});
				
				Map<Setting, Boolean> settings = new HashMap<>();
				sec.getConfigurationSection("settings").getValues(false).forEach((setting, value) -> {
					if (!(value instanceof Boolean b)) return;
					Setting stng = Setting.valueOf(setting.toUpperCase());
					settings.put(stng, b);
				});
				
				List<Invite> invites = new ArrayList<>();
				sec.getConfigurationSection("invites").getKeys(false).forEach((id -> {
					invites.add(Invite.getById(id));
				}));
				Division div = new Division(sec.getOfflinePlayer("owner"), sec.getString("id"), sec.getString("name"), sec, divplayers, settings, invites);
				return div;
			} else continue;
		}
		return null;
	}
	
	public static Division getByPlayer(OfflinePlayer p) {
		for (Map.Entry<String, Object> entry : FILE.getValues(true).entrySet()) {
			if (!(FILE.isConfigurationSection(entry.getKey()))) continue;
			ConfigurationSection sec = FILE.getConfigurationSection(entry.getKey());
			if (sec.getConfigurationSection("players") == null) continue;
			ConfigurationSection players = sec.getConfigurationSection("players");
			for (String s : players.getKeys(false)) {
				if (s.equals(p.getUniqueId().toString())) {
					Map<OfflinePlayer, Rank> divplayers = new HashMap<>();
					players.getValues(true).forEach((uuid, obj) -> {
						if (!(obj instanceof ConfigurationSection player)) return;
						OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
						Rank rnk = Rank.valueOf(player.getString("rank"));
						divplayers.put(pl, rnk);
					});
					
					Map<Setting, Boolean> settings = new HashMap<>();
					sec.getConfigurationSection("settings").getValues(false).forEach((setting, value) -> {
						if (!(value instanceof Boolean b)) return;
						Setting stng = Setting.valueOf(setting.toUpperCase());
						settings.put(stng, b);
					});
					
					List<Invite> invites = new ArrayList<>();
					sec.getConfigurationSection("invites").getKeys(false).forEach((id -> {
						invites.add(Invite.getById(id));
					}));
					Division div = new Division(sec.getOfflinePlayer("owner"), sec.getString("id"), sec.getString("name"), sec, divplayers, settings, invites);
					return div;
				} else continue;
			}
		};
		return null;
	}
	
	/**
	 * Whether or not this Division equals another players.
	 * @param obj The object to compare
	 */
	@Override
	public final boolean equals(Object obj) {
		if (!(obj instanceof Division div)) return false;
		return this.id.equals(div.id);
	}
	
	public final boolean isAdmin(OfflinePlayer p) {
		return (getPlayersMapped().get(p) == Rank.OWNER || getPlayersMapped().get(p) == Rank.ADMIN);
	}
	
	protected void checkDivision(boolean refresh) {
		section.set("corner_1", this.corner1);
		section.set("corner_2", this.corner2);
		section.set("home", this.home);
		
		if (refresh) {
			section.set("name", this.name);
			section.set("owner", this.owner);
			section.set("id", this.id);
			section.set("experience", this.experience);
			section.set("level", this.level);
			
			if (!(section.isConfigurationSection("players"))) {
				section.createSection("players");
			}
			
			ConfigurationSection players = section.getConfigurationSection("players");
			
			Map<OfflinePlayer, Rank> map = getPlayersMapped();
			
			for (String s : players.getKeys(false)) {
				UUID uuid = UUID.fromString(s);
				
				if (!(getPlayers().contains(Bukkit.getOfflinePlayer(uuid)))) {
					players.set(s, null);
				}
			}
			
			for (OfflinePlayer p : getPlayers()) {
				if (!(players.isConfigurationSection(p.getUniqueId().toString()))) {
					players.createSection(p.getUniqueId().toString());
				}
				
				ConfigurationSection sec = players.getConfigurationSection(p.getUniqueId().toString());
				
				sec.set("name", p.getName());
				sec.set("rank", map.get(p).name());
				sec.set("player", p);
			}
			
			if (!(section.isConfigurationSection("settings"))) {
				section.createSection("settings");
			}
			
			ConfigurationSection settings = section.getConfigurationSection("settings");
			
			for (Setting s : Setting.values()) {
				settings.set(s.name().toLowerCase(), getSetting(s));
			}
			
			if (!(section.isConfigurationSection("invites"))) {
				section.createSection("invites");
			}
			
			ConfigurationSection invites = section.getConfigurationSection("invites");
			
			for (Invite invite : getInvites()) {
				if (invite == null) continue;
				if (!(invites.isConfigurationSection("invites"))) {
					invites.createSection(invite.getID());
				}
				 
				ConfigurationSection inviteInfo = invites.getConfigurationSection(invite.getID());
				inviteInfo.set("from", invite.getPlayer());
				inviteInfo.set("invite-id", invite.getID());
				inviteInfo.set("division-id", invite.getDivision().getID());
			}
			
			try {
				FILE.save(SMPCore.getDivisionsFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (!(section.isString("name"))) {
				section.set("name", this.name);
			}
			
			if (!(section.isOfflinePlayer("owner"))) {
				section.set("owner", this.owner);
			}
			
			if (!(section.isString("id"))) {
				section.set("id", this.id);
			}
			
			if (!(section.isConfigurationSection("players"))) {
				section.createSection("players");
			}
			
			if (!(section.isInt("level"))) {
				section.set("level", this.level);
			}
			
			if (!(section.isDouble("experience"))) {
				section.set("experience", this.experience);
			}
			
			ConfigurationSection players = section.getConfigurationSection("players");
			Map<OfflinePlayer, Rank> map = getPlayersMapped();
			
			for (OfflinePlayer p : getPlayers()) {
				if (!(players.isConfigurationSection(p.getUniqueId().toString()))) {
					players.createSection(p.getUniqueId().toString());
				}
				
				ConfigurationSection sec = players.getConfigurationSection(p.getUniqueId().toString());
				
				if (!(sec.isString("name"))) {
					sec.set("name", p.getName());
				}
				
				if (!(sec.isString("rank"))) {
					sec.set("rank", map.get(p).name());
				}
				
				if (!(sec.isOfflinePlayer("player"))) {
					sec.set("player", p);
				}
			}
			
			if (!(section.isConfigurationSection("settings"))) {
				section.createSection("settings");
			}
			
			ConfigurationSection settings = section.getConfigurationSection("settings");
			
			for (Setting s : Setting.values()) {
				if (!(settings.isString(s.name().toLowerCase()))) {
					settings.set(s.name().toLowerCase(), getSetting(s));
				}
			}
			
			if (!(section.isConfigurationSection("invites"))) {
				section.createSection("invites");
			}
			
			ConfigurationSection invites = section.getConfigurationSection("invites");
			
			for (Invite invite : getInvites()) {
				if (!(invites.isConfigurationSection("invites"))) {
					invites.createSection(invite.getID());
				}
				
				ConfigurationSection inviteInfo = invites.getConfigurationSection(invite.getID());
				
				if (!(inviteInfo.isOfflinePlayer("from"))) {
					inviteInfo.set("from", invite.getPlayer());
				}
				
				if (!(inviteInfo.isString("invite-id"))) {
					inviteInfo.set("invite-id", invite.getID());
				}
				
				if (!(inviteInfo.isString("division-id"))) {
					inviteInfo.set("division-id", invite.getDivision().getID());
				}	
			}
			
			try {
				FILE.save(SMPCore.getDivisionsFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void checkDivision() {
		checkDivision(true);
	}
	
	protected static String generateID() {
		String chars = "abcdefghijklmnopqrstuvwxyz+$!-_@";
		String nums = "0123456789";
		
		String id = "";
		
		for (int i = 0; i < 12; i++) {
			if (r.nextBoolean()) {
				id += chars.charAt(r.nextInt(chars.length()));
			} else {
				id += nums.charAt(r.nextInt(nums.length()));
			}
		}
		
		return id;
	}
	
	public final String getID() {
		return section.getString("id");
	}
	
	public final String getName() {
		return section.getString("name");
	}
	
	public final ConfigurationSection getSection() {
		return this.section;
	}
	
	public final void sendMessage(Player from, String message) {
		for (OfflinePlayer p : getPlayers()) {
			if (!(p.isOnline())) return;
			Player target = (Player) p;
			target.sendMessage(ChatColor.DARK_GREEN + "Division" + ChatColor.DARK_GRAY + " > " + from.getDisplayName() + ChatColor.WHITE + ": " + message);
		}
	}
	
	public final void broadcastMessage(String message) {
		for (OfflinePlayer p : getPlayers()) {
			if (!(p.isOnline())) return;
			Player target = (Player) p;
			target.sendMessage(ChatColor.DARK_AQUA + "----------\n" + ChatColor.RESET + message + ChatColor.DARK_AQUA + "\n----------");
		}
	}
	
	public static final boolean isInDivision(OfflinePlayer target) {
		return (Division.getByPlayer(target) != null);
	}
	
	public Rank getRank(OfflinePlayer p) {
		return getPlayersMapped().get(p);
	}
	
	public List<OfflinePlayer> getPlayers() {
		List<OfflinePlayer> players = new ArrayList<>();
		for (String s : section.getConfigurationSection("players").getKeys(false)) {
			if (UUID.fromString(s) == null) continue;
			if (Bukkit.getOfflinePlayer(UUID.fromString(s)) == null) continue;
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(s)));
		}
		return players;
	}
	
	public List<UUID> getPlayersUUID() {
		List<UUID> uuids = new ArrayList<>();
		
		for (OfflinePlayer p : getPlayers()) {
			uuids.add(p.getUniqueId());
		}
		
		return uuids;
	}
	
	public void setPlayers(final Map<OfflinePlayer, Rank> players) {
		this.playersMapped = players;
		checkDivision();
	}
	
	public Map<OfflinePlayer, Rank> getPlayersMapped() {
		Map<OfflinePlayer, Rank> players = new HashMap<>();
		for (String s : section.getConfigurationSection("players").getKeys(false)) {
			if (UUID.fromString(s) == null) continue;
			if (Bukkit.getOfflinePlayer(UUID.fromString(s)) == null) continue;
			players.put(Bukkit.getOfflinePlayer(UUID.fromString(s)), Rank.valueOf(section.getConfigurationSection("players").getConfigurationSection(s).getString("rank")));
		}
		
		return players;
	}
	
	public boolean addPlayer(OfflinePlayer p, Rank r) {
		this.playersMapped.put(p, r);
		boolean bool = this.players.add(p);
		checkDivision();
		return bool;
	}
	
	public boolean removePlayer(OfflinePlayer p) {
		return removePlayer(p, true);
	}
	
	public boolean removePlayer(OfflinePlayer p, boolean save) {
		this.playersMapped.remove(p);
		boolean bool = this.players.remove(p);
		if (save) checkDivision();
		return bool;
	}
	
	public final OfflinePlayer getOwner() {
		return this.owner;
	}
	
	public final boolean getSetting(Setting setting) {
		return settings.get(setting);
	}
	
	public final void setSetting(Setting setting, boolean value) {
		settings.put(setting, value);
		checkDivision();
	}
	
	public List<Invite> getInvites() {
		return this.invites;
	}
	
	public void sendInvite(Invite inv) {
		this.invites.add(inv);
		for (OfflinePlayer p : getPlayers()) {
			if (isAdmin(p) && p.isOnline()) {
				Player op = (Player) p;
				TextComponent id = new TextComponent(ChatColor.YELLOW + inv.getPlayer().getName() + " has requested to join the division!\n" + ChatColor.GOLD + "Invite ID: " + inv.getID());
				id.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "Copy to Clipboard")));
				id.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, inv.getID()));
				
				op.spigot().sendMessage(id);
			}
		}
		checkDivision();
		
		new BukkitRunnable() {
			public void run() {
				inv.expire();
			}
		}.runTaskLater(JavaPlugin.getPlugin(SMPCore.class), 20 * (60 * 20)); // Expire after 10 minutes
	}
	
	public void revokeInvite(Invite inv) {
		inv.reject();
		checkDivision();
	}
	
	public void acceptInvite(Invite inv) {
		inv.accept();
		checkDivision();
	}
	
	public void setName(String name) {
		this.name = name;
		checkDivision();
	}
	
	public final void setOwner(OfflinePlayer owner) {
		this.setRank(this.owner, Rank.ADMIN);
		this.owner = owner;
		this.setRank(owner, Rank.OWNER);
	}
	
	public final void disband() {
		for (OfflinePlayer p : this.players) {
			if (p.isOnline()) {
				((Player) p).sendMessage(ChatColor.RED + "Your division has been disbanded!");
			}
		}
		
		FILE.set(this.id, null);
		try {
			FILE.save(SMPCore.getDivisionsFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final void setRank(OfflinePlayer p, Rank r) {
		this.playersMapped.put(p, r);
		checkDivision();
	}
	
	public final int getLevel() {
		return section.getInt("level");
	}
	
	public final double getExperience() {
		return section.getDouble("experience");
	}
	
	public final void setLevel(int level) {
		this.level = level;
		this.experience = toMinExperience(level);
		checkDivision();
	}
	
	public final void setExperience(double exp) {
		this.experience = exp;
		this.level = toLevel(exp);
		checkDivision();
	}
	
	public final void addExperience(double add) {
		setExperience(getExperience() + add);
	}
	
	public final void addLevel(int add) {
		setLevel(getLevel() + add);
	}
	
	public static double toMinExperience(int level) {
		if (level > 0 && level <= 10)
			return Math.floor(200 * (Math.pow(level, 3.05)));
		else if (level > 10 && level <= 30)
			return Math.floor(500 * (Math.pow(level, 2.6)));
		else if (level > 30)
			return Math.floor(1500 * (Math.pow(level, 2.3)));
		else return 0.0D;
	}
	
	public final Location getCorner1() {
		return section.getLocation("corner_1");
	}
	
	public final Location getCorner2() {
		return section.getLocation("corner_2");
	}
	
	public static int toLevel(double experience) {
		if (experience < 255029) { // Level 0 - 10
			return (int) Math.floor(Math.pow(experience / 200, 1D/3.05D));
		} else if (experience >= 255029 && experience < 4038528) { // Level 11 - 30
			return (int) Math.floor(Math.pow(experience / 500, 1D/2.6D));
		} else if (experience > 403852800) { // Level 31+
			return (int) Math.floor(Math.pow(experience / 1500, 1D/2.3D));			
		} else return 0;
	}
	
	public final void setCorner1(Location loc) {
		this.corner1 = loc;
		checkDivision();
	}
	
	public final void setCorner2(Location loc) {
		this.corner2 = loc;
		checkDivision();
	}
	
	public final void setHome(Location loc) {
		this.home = loc;
		checkDivision();
	}
	
	public Location getHome() {
		return section.getLocation("home");
	}
	
	public final double getDivisionNetworth() {
		double score = 100; // Base Worth of 100
		
		for (OfflinePlayer p : getPlayers()) {
			if (p.isOnline()) {
				Player op = (Player) p;
				double echestValue = 0;
				for (ItemStack i : op.getEnderChest()) {
					echestValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
				}
				
				double invValue = 0;
				for (ItemStack i : op.getInventory()) {
					invValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
				}
				
				score += (echestValue + invValue) * getValueMultiplier();
			} else {
				double lastworth = JavaPlugin.getPlugin(SMPCore.class).getConfig().getConfigurationSection(p.getUniqueId().toString()).getDouble("last_networth");
				score += lastworth * getValueMultiplier();
			}
		}
		
		return score;
	}
	
	public final double getMaxRegionSizeSquared() {
		double validSpace = getPlayers().size() * 10000; // 100 blocks per player (square rooted)
		return validSpace;
	}
	
	public final BoundingBox getClaimRegion() {
		if (this.corner1 == null) return null;
		if (this.corner2 == null) return null;
		
		BoundingBox claim = new BoundingBox(this.getCorner1().getX(), 320, this.getCorner1().getZ(), 
				this.corner2.getX(), this.getCorner2().getY() - 10, this.getCorner2().getZ());
		
		return claim;
	}
	
	public final double getValueMultiplier() {
		double modifier = 1;
		
		if (this.getLevel() <= 5) {
			modifier += this.getLevel() * 0.1;
		} else if (this.getLevel() > 5 && this.getLevel() <= 30) {
			modifier += 0.5 + (this.getLevel() * 0.05);
		} else if (this.getLevel() > 30) {
			modifier += 2 + (this.getLevel() * 0.01);
		}
		
		return modifier;
	}

}
