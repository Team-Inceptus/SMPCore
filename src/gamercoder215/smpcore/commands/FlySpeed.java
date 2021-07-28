package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;


public class FlySpeed implements CommandExecutor {
	
	public Main plugin;
	
	public FlySpeed(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("flyspeed").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		Player p = (Player) sender;
		if (!p.isFlying()) {
			p.sendMessage(ChatColor.RED + "You have to be flying to execute this command!");
		} else {
			if (args[0] != null) {
				if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 10) {
					p.sendMessage(ChatColor.RED + "Please set a fly speed between 1 and 10.");
				} else {
					p.setFlySpeed(Float.parseFloat(args[0]) / 10);
					p.sendMessage(ChatColor.GREEN + "Sucessfully set your fly speed to " + ChatColor.GOLD + args[0]);
				}
			} else {
				p.sendMessage(ChatColor.RED + "Please provide a speed, or make sure your speed is not a double (has decimal points).");
			}
		}
		return false;
	}

}
