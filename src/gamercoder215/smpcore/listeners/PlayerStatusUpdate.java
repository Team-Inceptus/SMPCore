package gamercoder215.smpcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public class PlayerStatusUpdate implements Listener {
	
	private Main plugin;
   
	public PlayerStatusUpdate(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
   
   double speedVal = 0;
   
   public void setRank(Player p) {
	   if (!(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).get("rank") instanceof String)) plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("rank", "default");
	   String rank = (String) plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).get("rank");
	   
	   if (rank.equalsIgnoreCase("default")) {
		   p.setDisplayName(ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_GRAY + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("vip")) {
		   p.setDisplayName(ChatColor.DARK_GREEN + "VIP " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_GREEN + "[VIP] " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("vip+")) {
		   p.setDisplayName(ChatColor.GREEN + "VIP+ " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.GREEN + "[VIP+] " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("mvp")) {
		   p.setDisplayName(ChatColor.AQUA + "MVP " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.AQUA + "[MVP] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("mvp+")) {
		   p.setDisplayName(ChatColor.DARK_PURPLE + "MVP+ " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.DARK_PURPLE + "[MVP+] " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("tmod")) {
		   p.setDisplayName(ChatColor.LIGHT_PURPLE + "Trial Mod " + ChatColor.RED + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[T-MOD] " + ChatColor.RED + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("jrmod")) {
		   p.setDisplayName(ChatColor.RED + "JrMod " + ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.RED + "[JRMOD] " + ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
	   } else if (rank.equalsIgnoreCase("mod")) {
		   p.setDisplayName(ChatColor.RED + "Mod " + ChatColor.YELLOW + p.getName() + ChatColor.RESET);
		   p.setPlayerListName(ChatColor.RED + "[MOD] " + ChatColor.YELLOW + p.getName() + ChatColor.RESET);
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
	   }
   }
   
   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
	  
	  plugin.saveConfig();
	  
      Player p = e.getPlayer();
      
      
      String uuid = p.getUniqueId().toString();
      
      if (plugin.getConfig().getConfigurationSection(uuid) == null) {
    	  plugin.getConfig().createSection(uuid);
      }
      
      if (plugin.getConfig().getConfigurationSection(uuid).get("titan_summons") == null) {
    	  plugin.getConfig().getConfigurationSection(uuid).set("titan_summons", 0);
      }
      
      if (plugin.getConfig().getConfigurationSection(uuid).get("titan_kills") == null) {
    	  plugin.getConfig().getConfigurationSection(uuid).set("titan_kills", 0);
      }
      
      if (plugin.getConfig().getConfigurationSection(uuid).get("boss_summons") == null) {
    	  plugin.getConfig().getConfigurationSection(uuid).set("boss_summons", 0);
      }
      
      if (plugin.getConfig().getConfigurationSection(uuid).get("rank") == null) {
    	  plugin.getConfig().getConfigurationSection(uuid).set("rank", "default");
      }
      
      plugin.saveConfig();
      setRank(p);
      e.setJoinMessage(ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GREEN + " joined the game");
      p.setPlayerListHeader(ChatColor.GOLD + "\n You are playing on " + ChatColor.GREEN + "noobygodssmp.apexmc.co \n" + ChatColor.BLUE + "\n discord.io/thenoobygods \n" + ChatColor.RED + "reddit.com/r/TheNoobyGods\n\n" + ChatColor.GOLD + "Version " + ChatColor.GREEN + "v" + plugin.getDescription().getVersion());
      
      int onlinePlayers = p.getServer().getOnlinePlayers().size();
      int maxPlayers = p.getServer().getMaxPlayers();
      
      for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
    	  pl.setPlayerListFooter(ChatColor.GOLD + Integer.toString(onlinePlayers) + " / " + Integer.toString(maxPlayers) + ChatColor.GREEN + " Online Players");
    	  p.setPlayerListFooter(ChatColor.GOLD + Integer.toString(onlinePlayers) + " / " + Integer.toString(maxPlayers) + ChatColor.GREEN + " Online Players");
      }
   }

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