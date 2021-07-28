package gamercoder215.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;

public class Rejoin implements CommandExecutor {
	
	public Main plugin;
	
	public Rejoin(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("rejoinplayers").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.kickPlayer("A rejoin is initiating. Please rejoin!");
		}
		return false;
	}
	
}
