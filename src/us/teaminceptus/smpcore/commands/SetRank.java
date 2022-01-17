package us.teaminceptus.smpcore.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.PermissionUtils;

public class SetRank implements CommandExecutor {
	
	public SMPCore plugin;
	
	public SetRank(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("setrank").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender cmdsender, Command cmd, String label, String[] args) {
		if (!(cmdsender instanceof Player sender)) return false;
		
		if (!(sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "Nope.");
			return false;
		}
		
		if (args.length < 1) sender.sendMessage(ChatColor.RED + "Please provide a player.");
		else {
			if (Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage(ChatColor.RED + "This Player does not exist.");
				return false;
			}
			Player p = Bukkit.getPlayer(args[0]);
			if (args.length < 2) sender.sendMessage(ChatColor.RED + "Please provide a rank.");
			else {
				FileConfiguration config = SMPCore.getFile(p);
				config.set("rank", args[1]);
				
		      try {
		    	  config.save(new File(SMPCore.getPlayersDirectory(), p.getUniqueId().toString() + ".yml"));
		      } catch (IOException err) {
		      	err.printStackTrace();
		      }
				
				String rank = args[1];
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
		}
		
		return false;
	}
}
