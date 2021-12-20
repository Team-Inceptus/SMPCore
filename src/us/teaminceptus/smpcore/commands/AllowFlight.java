package us.teaminceptus.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;


public class AllowFlight implements CommandExecutor {
	
	public SMPCore plugin;
	
	public AllowFlight(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("allowflight").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		Player p = (Player) sender;
		
		if (!(p.isOp())) {
			p.sendMessage(ChatColor.RED + "Only Operators can use this command.");
		} else {
			if (args[0] == null) p.sendMessage(ChatColor.RED + "Please provide a player!");
			Player target = Bukkit.getPlayer(args[0]);
			
			if (args[1] == null) p.sendMessage(ChatColor.RED + "Please provide true or false in your second argument.");
			
			if (args[1].contains("true")) {
				target.setAllowFlight(true);
				target.sendMessage(ChatColor.GREEN + "You are now allowed to fly!");
			} else if (args[1].contains("false")) {
				target.setAllowFlight(false);
				target.sendMessage(ChatColor.RED + "You are now not allowed to fly.");
				if (target.isFlying()) {
					target.setFlying(false);
				}
			} else {
				p.sendMessage(ChatColor.RED + "Please provide true or false.");
			}
		}
		return false;
	}

}
