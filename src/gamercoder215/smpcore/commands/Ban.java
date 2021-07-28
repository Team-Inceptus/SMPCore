package gamercoder215.smpcore.commands;

import java.util.ArrayList;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;

public class Ban implements CommandExecutor {
	
	public Main plugin;
	
	public Ban(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ban").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String bumper = org.apache.commons.lang.StringUtils.repeat("\n", 35);
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Please provide a target.");
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "A reason needs to be provided.");
			} else {
				ArrayList<String> reasonArgs = new ArrayList<String>();
				for (int i = 1; i < args.length; i++) {
					reasonArgs.add(args[i]);
				}
				String reason = String.join(" ", reasonArgs);
				if (target != null ) {
					target.kickPlayer(ChatColor.RED + "You have been permanently banned!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason);
				}
				Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), bumper + ChatColor.RED + "You have been permanently banned!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason + bumper, null, sender.getName());
			}
		}
		return false;
	}

}
