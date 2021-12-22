package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;

public class WorldChat implements CommandExecutor {
	
	public SMPCore plugin;
	
	public WorldChat(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("worldchat").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (args.length < 1) p.sendMessage(ChatColor.RED + "You need to provide something to say!");
		else {
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			
			for (Player pl : p.getWorld().getPlayers()) {
				pl.sendMessage(ChatColor.WHITE + "<" + p.getDisplayName() + ChatColor.DARK_GREEN + " | WorldChat" + ChatColor.WHITE + "> " + msg);
			}
		}
		return false;
	}

}
