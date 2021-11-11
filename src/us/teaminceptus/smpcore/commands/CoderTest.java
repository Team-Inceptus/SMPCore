package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.Main;

public class CoderTest implements CommandExecutor {
	public Main plugin;
	
	public CoderTest(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("codertest").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		if (!(p.getUniqueId().toString().equalsIgnoreCase("8069233e-5b25-410c-9475-daa6a044c365"))) {
			p.sendMessage(ChatColor.RED + "Only GamerCoder215 can make worlds.");
		} else {
			try {
				if (args[0].equalsIgnoreCase("colortest")) {
				}
			} catch (NullPointerException e) {
				p.sendMessage(ChatColor.RED + "Invalid Arguments");
			}
		}
		return false;
	}
}
