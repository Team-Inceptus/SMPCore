package us.teaminceptus.smpcore.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.commands.Value;
import us.teaminceptus.smpcore.commands.Value.Rarity;
import us.teaminceptus.smpcore.utils.PermissionUtils;

public class PlayerStatusUpdate implements Listener {
	
	protected SMPCore plugin;
   
	public PlayerStatusUpdate(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
   
   double speedVal = 0;
   
   public void setRank(Player p) {
	   String rank = SMPCore.getFile(p).getString("rank");
	   
	   if (rank.equalsIgnoreCase("default")) {
		   p.setDisplayName(ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_GRAY + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("vip")) {
		   p.setDisplayName(ChatColor.DARK_GREEN + "VIP " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_GREEN + "[VIP] " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("vip+")) {
		   p.setDisplayName(ChatColor.GREEN + "VIP+ " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.GREEN + "[VIP+] " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("mvp")) {
		   p.setDisplayName(ChatColor.AQUA + "MVP " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.AQUA + "[MVP] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("mvp+")) {
		   p.setDisplayName(ChatColor.DARK_PURPLE + "MVP+ " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_PURPLE + "[MVP+] " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("tmod")) {
		   p.setDisplayName(ChatColor.LIGHT_PURPLE + "Trial Mod " + ChatColor.RED + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[T-MOD] " + ChatColor.RED + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveTrialModPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("jrmod")) {
		   p.setDisplayName(ChatColor.RED + "JrMod " + ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.RED + "[JRMOD] " + ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveJrModPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("mod")) {
		   p.setDisplayName(ChatColor.RED + "Mod " + ChatColor.YELLOW + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.RED + "[MOD] " + ChatColor.YELLOW + p.getName() + ChatColor.RESET);
		   
		   PermissionUtils.giveModPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("srmod")) {
		   p.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "SrMod " + ChatColor.GOLD + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.YELLOW + "" + ChatColor.BOLD + "[SRMOD] " + ChatColor.GOLD + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("headmod")) {
		   p.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Head Mod " + ChatColor.RED + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.BLUE + "" + ChatColor.BOLD + "[H-MOD] " + ChatColor.RED + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("god")) {
		   p.setDisplayName(ChatColor.BOLD + "" + ChatColor.BLUE + "God " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[GOD] " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("owner")) {
		   p.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Owner " + ChatColor.GOLD + p.getName() + ChatColor.RESET);
		   p.setPlayerListName( ChatColor.DARK_RED + "" + ChatColor.BOLD + "[OWNER] " + ChatColor.GOLD + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("idea-man")) {
		   p.setDisplayName(ChatColor.BLUE + "Idea Man " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.BLUE + "[IDEAS] " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);

		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("booster")) {
		   p.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Booster " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[BOOSTER] " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.RESET);
	   
		   PermissionUtils.giveDefaultPermissions(plugin, p);
	   } else if (rank.equalsIgnoreCase("headgod")) {
		   p.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Head God " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.GREEN + "" + ChatColor.BOLD + "[HEAD GOD] " + ChatColor.DARK_BLUE + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("president")) {
		   p.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "President " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[PRESIDENT] " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
	   }
   }
   
   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      Player p = e.getPlayer();
      
      FileConfiguration config = SMPCore.getFile(p);
      if (!(config.isString("rank"))) {
    	  config.set("rank", "default");
      }
      
      if (config.get("titan_summons") == null) {
    	  config.set("titan_summons", 0);
      }
      
      if (config.get("titan_kills") == null) {
    	  config.set("titan_kills", 0);
      }
      
      if (config.get("boss_summons") == null) {
    	  config.set("boss_summons", 0);
      }
      
      if (!(config.isBoolean("killed_dimguard"))) {
    	  config.set("killed_dimguard", false);
      }
      
      if (config.get("pet_damage") == null) {
    	  config.set("pet_damage", 0);
      }
      if (config.get("pet_defense") == null) {
    	  config.set("pet_defense", 0);
      }
      if (config.get("pet_speed") == null) {
    	  config.set("pet_speed", 0);
      }
      
      if (!(config.isBoolean("killed_dragtitan"))) {
    	  config.set("killed_dragtitan", false);
      }
      
      
      if (!(config.isDouble("last_networth"))) {
    	  double echestValue = 0;
    	  for (ItemStack i : p.getEnderChest()) {
    		  echestValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
    	  }
	
    	  double invValue = 0;
    	  for (ItemStack i : p.getInventory()) {
    		  invValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
    	  }
  
    	  config.set("last_networth", echestValue + invValue);
      }
      
      // NPCs
      
      if (config.get("npc_talks") == null) {
    	  config.createSection("npc_talks");
      }
      
      if (config.getConfigurationSection("npc_talks").get("bellator") == null) {
    	  config.getConfigurationSection("npc_talks").set("bellator", false);;
      }
      
      setRank(p);
      e.setJoinMessage(ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GREEN + " joined the game");
      if (!(p.hasPlayedBefore())) {
    	  Bukkit.broadcastMessage(ChatColor.YELLOW + "This guy is new! Give him a warm welcome!");
      }
      p.setPlayerListHeader(ChatColor.GOLD + "\n You are playing on " + ChatColor.GREEN + "noobygodssmp.apexmc.co \n" + ChatColor.BLUE + "\n discord.io/thenoobygods \n" + ChatColor.RED + "reddit.com/r/TheNoobyGods\n\n" + ChatColor.GOLD + "Version " + ChatColor.GREEN + "v" + plugin.getDescription().getVersion());
      
      int onlinePlayers = p.getServer().getOnlinePlayers().size();
      int maxPlayers = p.getServer().getMaxPlayers();
      
      for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
    	  pl.setPlayerListFooter(ChatColor.GOLD + Integer.toString(onlinePlayers) + " / " + Integer.toString(maxPlayers) + ChatColor.GREEN + " Online Players");
    	  p.setPlayerListFooter(ChatColor.GOLD + Integer.toString(onlinePlayers) + " / " + Integer.toString(maxPlayers) + ChatColor.GREEN + " Online Players");
      }
      
      if (!(p.hasPlayedBefore())) {
    	  p.teleport(spawns[r.nextInt(spawns.length)]);
      }
      
      // Non-Player Updates
      if (!(plugin.getConfig().isConfigurationSection("networth_leaderboard"))) {
    	  plugin.getConfig().createSection("networth_leaderboard");
      }
      
      try {
    	  config.save(new File(SMPCore.getPlayersDirectory(), p.getUniqueId().toString() + ".yml"));
      } catch (IOException err) {
      	err.printStackTrace();
      }
   }
   
   Location[] spawns = {
		   Bukkit.getWorld("world").getSpawnLocation(),
		   new Location(Bukkit.getWorld("world"), 137, 64, -237),
		   new Location(Bukkit.getWorld("world"), 49, 68, -874),
		   new Location(Bukkit.getWorld("world"), -20341, 76, 3084),
		   new Location(Bukkit.getWorld("world"), -20619, 92, 3207),
   };
   
   Random r = new Random();

   @EventHandler
   public void onLeave(PlayerQuitEvent e) {
      Player p = e.getPlayer();
      e.setQuitMessage(ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GREEN + " left the game");
      
      int onlinePlayers = p.getServer().getOnlinePlayers().size() - 1;
      int maxPlayers = p.getServer().getMaxPlayers();
      
      for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
    	  pl.setPlayerListFooter(ChatColor.GOLD + Integer.toString(onlinePlayers) + " / " + Integer.toString(maxPlayers) + ChatColor.GREEN + " Online Players");
    	  if (!(pl.canSee(p))) {
    		  pl.showPlayer(plugin, p);
    	  }
      }
      
      // Update Networth
      double echestValue = 0;
      for (ItemStack i : p.getEnderChest()) {
    	  echestValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
      }
	
      double invValue = 0;
      for (ItemStack i : p.getInventory()) {
    	  invValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
      }
      
      double newValue = echestValue + invValue;
      
      FileConfiguration config = SMPCore.getFile(p);
      config.set("last_networth", newValue);
      
      try {
    	  config.save(new File(SMPCore.getPlayersDirectory(), p.getUniqueId().toString() + ".yml"));
      } catch (IOException err) {
      	err.printStackTrace();
      }
   }
   
   @EventHandler
   public void onCraftAttempt(InventoryClickEvent e) {
	   if (!(e.getClickedInventory() instanceof CraftingInventory inv)) return;
	   if (!(e.getWhoClicked() instanceof Player p)) return;
	   List<ItemStack> newItems = new ArrayList<>();
	   for (ItemStack i : inv.getMatrix()) {
      	 ItemMeta oldMeta = i.getItemMeta();
      	 if (oldMeta.hasLore()) {
      		 List<String> newLore = oldMeta.getLore();
      		 for (Rarity r : Rarity.values()) {
      			 if (newLore.contains(r.nameColor())) newLore.remove(r.nameColor());
      		 }
      		 oldMeta.setLore((newLore.size() == 0 ? null : newLore));
      	 }
      	 i.setItemMeta(oldMeta);
      	 newItems.add(i);
	   }
	   
	   ItemStack result = Bukkit.craftItem(newItems.toArray(new ItemStack[] {}), p.getWorld(), p);
	   if (result.getType() != Material.AIR) {
		   inv.setResult(result);
	   }
   }
   
   @EventHandler
   public void onSpawn(ItemSpawnEvent e) {
	   new BukkitRunnable() {
		   public void run() {
			   e.getEntity().remove();
		   }
	   }.runTaskLater(plugin, 20 * 180);
   }
}