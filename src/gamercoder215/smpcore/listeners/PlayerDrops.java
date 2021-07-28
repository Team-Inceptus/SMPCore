package gamercoder215.smpcore.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.ItemFetcher;

public class PlayerDrops implements Listener {
	
	public Main plugin;
	
	public PlayerDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	/*
	private String parseDamageCause(DamageCause dmg, Entity killer) {
		if (killer == null) return " died";
		
		
		if (dmg.equals(DamageCause.CONTACT)) return " was killed by something he touched";
		else if (dmg.equals(DamageCause.CRAMMING)) return " was squished too much";
		else if (dmg.equals(DamageCause.DRAGON_BREATH)) return " was killed by the Dragon's Breath";
		else if (dmg.equals(DamageCause.DROWNING)) return " ran out of air";
		else if (dmg.equals(DamageCause.DRYOUT)) return " was too try";
		else if (dmg.equals(DamageCause.FALL)) return " broke his legs";
		else if (dmg.equals(DamageCause.FIRE)) return " burned to death";
		else if (dmg.equals(DamageCause.FIRE_TICK)) return " burned to death";
		else if (dmg.equals(DamageCause.FLY_INTO_WALL)) return " broke his head";
		else if (dmg.equals(DamageCause.FREEZE)) return " froze to death";
		else if (dmg.equals(DamageCause.HOT_FLOOR)) return "'s toes melted";
		else if (dmg.equals(DamageCause.LAVA)) return " melted to death";
		else if (dmg.equals(DamageCause.LIGHTNING)) return " was smited";
		else if (dmg.equals(DamageCause.MAGIC)) return " was killed by magic";
		else if (dmg.equals(DamageCause.MELTING)) return " melted to death";
		else if (dmg.equals(DamageCause.POISON)) return " was poisoned";
		else if (dmg.equals(DamageCause.STARVATION)) return " starved himself to death";
		else if (dmg.equals(DamageCause.SUFFOCATION)) return " was encased in a block";
		else if (dmg.equals(DamageCause.SUICIDE)) return " commited die";
		else if (dmg.equals(DamageCause.THORNS)) return " was prickled to death";
		else if (dmg.equals(DamageCause.VOID)) return " was consumed by the physical endless void";
		else if (dmg.equals(DamageCause.WITHER)) return " withered to death";
		else if (dmg.equals(DamageCause.BLOCK_EXPLOSION) || dmg.equals(DamageCause.ENTITY_EXPLOSION)) return " got bombed by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.ENTITY_ATTACK)) return " was killed by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.ENTITY_SWEEP_ATTACK)) return " was killed by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.FALLING_BLOCK)) return " was squished by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.PROJECTILE)) return " was killed by " + ( ( (Entity) ( (Projectile) killer).getShooter()).getCustomName() != null ? ((Entity) ((Projectile) killer).getShooter()).getCustomName() : ((Entity) ((Projectile) killer).getShooter()).getName());
		else return " died";
	}
	*/
	
	public void initiateDeathItems(Player p) {
		// Drop Head
		ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
		playerHeadMeta.setOwningPlayer(p);
		playerHeadMeta.setDisplayName(ChatColor.GOLD + p.getName() + "'s " + ChatColor.GREEN + "Head");
		
		ArrayList<String> playerHeadLore = new ArrayList<>();
		playerHeadLore.add(ChatColor.GRAY + "Obtained by killing " + ChatColor.GOLD + p.getName() + ChatColor.GRAY + ".");
		
		playerHeadMeta.setLore(playerHeadLore);
		
		playerHead.setItemMeta(playerHeadMeta);
		
		p.getWorld().dropItemNaturally(p.getLocation(), playerHead);
		// Drop Actual XP
		ExperienceOrb exp = (ExperienceOrb) p.getWorld().spawnEntity(p.getLocation(), EntityType.EXPERIENCE_ORB);
		exp.setExperience(p.getTotalExperience());
	}
	
	ArrayList<UUID> teleporters = new ArrayList<UUID>();
	
	/*@EventHandler
   public void onDeathAnimation(EntityDamageEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		Location damageLoc = p.getLocation();
		GameMode gm = p.getGameMode();
		
		if (p.getHealth() - e.getDamage() < 1) {
			e.setCancelled(true);
			initiateDeathItems(p, e.getCause());
			if (p.getInventory().containsAtLeast(ItemFetcher.getInventoryCore(), 1)) {
				p.getInventory().removeItem(ItemFetcher.getInventoryCore());
				Bukkit.broadcastMessage(ChatColor.GREEN + parseDamageCause(e.getCause(), null) + ", but their items were saved!");
				p.sendMessage(ChatColor.GREEN + "Your Inventory Core saved your inventory and levels!");
			} else {
				for (ItemStack i : p.getInventory()) {
					p.getWorld().dropItemNaturally(p.getLocation(), i);
				}
				
				p.getInventory().clear();
				initiateDeathItems(p, e.getCause());
			}
			
			
			p.setGameMode(GameMode.SPECTATOR);
			
			new BukkitRunnable() {
				public void run() {
					p.sendTitle(ChatColor.RED + "3", null, 10, 70, 20);
					p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 0F);
					new BukkitRunnable() {
						public void run() {
							p.sendTitle(ChatColor.YELLOW + "2", null, 10, 70, 20);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 0.75F);
							new BukkitRunnable() {
								public void run() {
									p.sendTitle(ChatColor.GREEN + "1", null, 10, 70, 20);
									p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 1F);
									new BukkitRunnable() {
										public void run() {
											p.resetTitle();
											p.teleport(damageLoc, TeleportCause.PLUGIN);
											p.setGameMode(gm);
										}
									}.runTaskLater(plugin, 20);
								}
							}.runTaskLater(plugin, 20);							
						}
					}.runTaskLater(plugin, 20);
				}
			}.runTaskLater(plugin, 40);
		}
	}
	*/
	
	@EventHandler
	public void onDeathAnimationKiller(PlayerDeathEvent e) {
		Player p = e.getEntity();
		e.setDeathMessage(ChatColor.GREEN + e.getDeathMessage());
		
		if (p.getInventory().containsAtLeast(ItemFetcher.getInventoryCore(), 1)) {
			p.getInventory().removeItem(ItemFetcher.getInventoryCore());
			e.setKeepInventory(true);
			e.setKeepLevel(true);
			e.setDeathMessage(ChatColor.GREEN + e.getDeathMessage() + ", but their items were saved!");
			p.sendMessage(ChatColor.GREEN + "Your Inventory Core saved your inventory and levels!");
		} else {
			initiateDeathItems(p);
		}
	}
}
