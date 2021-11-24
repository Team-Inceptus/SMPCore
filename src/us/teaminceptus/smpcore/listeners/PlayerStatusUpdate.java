package us.teaminceptus.smpcore.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.utils.PermissionUtils;

public class PlayerStatusUpdate implements Listener {
	
	protected Main plugin;
   
	public PlayerStatusUpdate(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
   
   double speedVal = 0;
   
   public void setRank(Player p) {
	   if (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null) {
		   plugin.getConfig().createSection(p.getUniqueId().toString());
	   }
	   
	   if (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).get("rank") == null) {
		   plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("rank", "default");
	   }
	   
	   if (!(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).get("rank") instanceof String)) plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("rank", "default");
	   String rank = (String) plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).get("rank");
	   
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
	   }
   }
   
   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
	  
	  plugin.saveConfig();
	  
      Player p = e.getPlayer();
      
      setRank(p);
      e.setJoinMessage(ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GREEN + " joined the game");
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
   }
   
   Location[] spawns = {
		   Bukkit.getWorld("world").getSpawnLocation(),
		   new Location(Bukkit.getWorld("world"), 3343, 64, 701),
		   new Location(Bukkit.getWorld("world"), 19748, 68, 23639),
		   new Location(Bukkit.getWorld("world"), -61272, 71, 3198),
		   new Location(Bukkit.getWorld("world"), -103423, 66, 331541),
		   new Location(Bukkit.getWorld("world"), 8829, 63, -441603),
		   new Location(Bukkit.getWorld("world"), 72866, 67, -1608953),
		   new Location(Bukkit.getWorld("world"), 271979, 107, 15078),
		   new Location(Bukkit.getWorld("world"), 4691, 67, -2526),
		   new Location(Bukkit.getWorld("world"), 2154511, 63, -3145615),
		   new Location(Bukkit.getWorld("world"), 54949, 69, -14451180),
		   new Location(Bukkit.getWorld("world"), -771156, 66, 1142434),
		   new Location(Bukkit.getWorld("world"), 912397, 66, -2234952),
		   new Location(Bukkit.getWorld("world"), 1002465, 85, -8221399),
		   new Location(Bukkit.getWorld("world"), -14506, 92, 3314451),
		   new Location(Bukkit.getWorld("world"), -9982106, 64, 30028),
		   new Location(Bukkit.getWorld("world"), 6000002, 90, 5999985),
		   new Location(Bukkit.getWorld("world"), 9221532, 68, 5313324),
		   new Location(Bukkit.getWorld("world"), -8463117, 70, -6623204),
		   new Location(Bukkit.getWorld("world"), -1330, 81, 8849),
		   new Location(Bukkit.getWorld("world"), 3019, 72, -8902),
		   new Location(Bukkit.getWorld("world"), -11910, 63, -211),
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