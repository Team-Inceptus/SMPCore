package us.teaminceptus.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;

public class Rejoin implements CommandExecutor {
	
	public SMPCore plugin;
	
	public Rejoin(SMPCore plugin) {
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
