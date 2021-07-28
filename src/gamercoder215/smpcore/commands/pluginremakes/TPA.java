package gamercoder215.smpcore.commands.pluginremakes;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public class TPA implements CommandExecutor {

	public Main plugin;
	public HashMap<Player, Player> tpa;
	
	public TPA(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("tpa").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can teleport.");
		} else {
			Player p = (Player) sender;
			if(tpa.containsKey(p)) {
				p.sendMessage(ChatColor.RED + "You already have an outgoing request!");
				return false;
			}
			
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Please provide a player.");
				return false;
			}
			
			if (Bukkit.getPlayer(args[0]) == null) {
				p.sendMessage(ChatColor.RED + "This player does not exist.");
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			tpa.put(p, target);
			target.sendMessage(ChatColor.GREEN + "Teleport request from " + ChatColor.AQUA + (p.getDisplayName() == null ? p.getName() : p.getDisplayName()) + ChatColor.GREEN + ";\nType " + ChatColor.DARK_GREEN + "/tpaccept" + ChatColor.GREEN + " to accept!");
			p.sendMessage(ChatColor.GREEN + "Sucessfully sent teleport request to " + ChatColor.AQUA + (target.getDisplayName() == null ? p.getName() : p.getDisplayName()));
			
			new BukkitRunnable() {
				public void run() {
					p.sendMessage(ChatColor.GREEN + "Your TPA to " + ChatColor.AQUA + (target.getDisplayName() == null ? target.getName() : target.getDisplayName()) + ChatColor.GREEN + " has expired!");
					target.sendMessage(ChatColor.GREEN + "TPA from " + ChatColor.AQUA + (p.getDisplayName() == null ? p.getName() : p.getDisplayName()) + ChatColor.GREEN + " has expired!");
					tpa.remove(p);
				}
			}.runTaskLater(plugin, 20 * 30);
		}
		return false;
	}

}
