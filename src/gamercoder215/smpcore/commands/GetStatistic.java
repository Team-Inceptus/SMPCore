package gamercoder215.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.CommandTabCompleter;

public class GetStatistic implements CommandExecutor {
	
	public Main plugin;
	
	public GetStatistic(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("getstatistic").setExecutor(this);
		plugin.getCommand("getstatistic").setTabCompleter(new CommandTabCompleter());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length < 1) sender.sendMessage(ChatColor.RED + "Please provide a player.");
		else {
			if (Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage(ChatColor.RED + "This Player does not exist.");
				return false;
			}
			Player p = Bukkit.getPlayer(args[0]);
			if (args.length < 2) sender.sendMessage(ChatColor.RED + "Please provide a statistic.");
			else {
				if (Statistic.valueOf(args[1].toUpperCase().replaceAll("\\s", "_")) == null) {
					p.sendMessage(ChatColor.RED + "This statistic does not exist.");
					return false;
				}
				int stat = p.getStatistic(Statistic.valueOf(args[1].toUpperCase().replaceAll("\\s", "_")));
				
				p.sendMessage("Player " + ChatColor.GREEN + p.getName() + ChatColor.WHITE + "'s Statistic for " + ChatColor.BLUE + args[1].toUpperCase().replaceAll("\\s", "_") + ChatColor.WHITE + " is " + ChatColor.GOLD + Integer.toString(stat));
			}
		}
		
		return false;
	}
}
