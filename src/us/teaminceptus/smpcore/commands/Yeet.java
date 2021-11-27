package us.teaminceptus.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import us.teaminceptus.smpcore.Main;

public class Yeet implements CommandExecutor {

	protected Main plugin;
	
	public Yeet(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("yeet").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Invalid Player.");
			return false;
		}
		
		if (Bukkit.getPlayer(args[0]) == null) {
			sender.sendMessage(ChatColor.RED + "Invalid Player.");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		
		if (sender.getName().equalsIgnoreCase("GamerCoder215")) {
			target.sendMessage(ChatColor.BLUE + "GamerCoder215 yeeted you into the sun!");
			if (args.length < 4) {
				target.setVelocity(new Vector(target.getVelocity().getX(), 5, target.getVelocity().getZ()));
			} else {
				target.setVelocity(new Vector(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
			}
		} else {
			target.sendMessage(ChatColor.BLUE + "You got yeeted into the sun by " + sender.getName() + "!");
			target.setVelocity(new Vector(target.getVelocity().getX(), 5, target.getVelocity().getZ()));
		}
		
		return false;
	}

	
	
}
