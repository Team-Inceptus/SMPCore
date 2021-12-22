package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;

public class Wild implements CommandExecutor {

	protected SMPCore plugin;
	
	public Wild(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("wild").setExecutor(this);
	}
	
	static Random r = new Random();
	
	List<UUID> cooldown = new ArrayList<>();
	
	private Location getWild(World w) {
		Location wild = new Location(w, r.nextInt((int) Math.floor(w.getWorldBorder().getSize())) - r.nextInt((int) Math.floor(w.getWorldBorder().getSize())), r.nextInt(100) + 30, r.nextInt((int) Math.floor(w.getWorldBorder().getSize())) - r.nextInt((int) Math.floor(w.getWorldBorder().getSize())));
		Block pos = w.getBlockAt(wild);
		
		if (pos.isPassable() && !(w.getBlockAt(wild.subtract(0, 3, 0)).isPassable())) return wild;
		else return getWild(w);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) return false;
		if (!(p.getWorld().getName().equalsIgnoreCase("world")) && !(p.getWorld().getName().equalsIgnoreCase("world_nether"))) {
			p.sendMessage(ChatColor.RED + "This world is not supported.");
			return false;
		}
		
		if (cooldown.contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + "This command is on a cooldown!");
			return false;
		}
	
		World w = p.getWorld();
		
		Location wild = getWild(w);
		p.sendMessage(ChatColor.GREEN + "Teleported!");
		p.teleport(wild);
		
		cooldown.add(p.getUniqueId());
		
		new BukkitRunnable() {
			public void run() {
				p.sendMessage(ChatColor.GREEN + "Your wild cooldown has been reset!");
			}
		}.runTaskLater(plugin, 20 * 120);
		return false;
	}

}
