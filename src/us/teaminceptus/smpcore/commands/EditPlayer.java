package us.teaminceptus.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;

public class EditPlayer implements CommandExecutor {
	
	public SMPCore plugin;
	
	public EditPlayer(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("editplayer").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if (args.length < 1) {
			p.sendMessage(ChatColor.RED + "You need to add a player!");
		} else {
			Player ta = Bukkit.getPlayer(args[0]);
			if (ta == null) {
				p.sendMessage(ChatColor.RED + "This player does not exist.");
			} else {
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "Please provide an attribute.");
				} else {
					if (args.length < 3) {
						p.sendMessage(ChatColor.RED + "Please set to a valid value.");
					} else {
						if (args[1].equalsIgnoreCase("gravity")) {
							ta.setGravity(Boolean.parseBoolean(args[2].toLowerCase()));
							p.sendMessage("Set attribute " + ChatColor.AQUA + "gravity" + ChatColor.WHITE + " to " + ChatColor.GREEN + Boolean.toString(Boolean.parseBoolean(args[2])) + ChatColor.WHITE + ".");
						} else if (args[1].equalsIgnoreCase("health")) {
							try {
								ta.setHealth(Integer.parseInt(args[2]));
								p.sendMessage("Set attribute " + ChatColor.AQUA + "health" + ChatColor.WHITE + " to " + ChatColor.GREEN + Integer.toString(Integer.parseInt(args[2])) + ChatColor.WHITE + ".");
							} catch (NumberFormatException e) {
								p.sendMessage(ChatColor.RED + "Please provide a valid integer.");
							}
						}
					}
				}
				
				}
			}
		
		return false;
	}
	
	
}
