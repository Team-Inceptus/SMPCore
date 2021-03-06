package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;

public class Craft implements CommandExecutor {
	
	public SMPCore plugin;
	
	public Craft(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("craft").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "GUIs can only be executed by players.");
		
		Player p = (Player) sender;
		if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON) < 1) {
			p.sendMessage(ChatColor.RED + "This command can only be executed if you beat the ender dragon.");
			if (p.isOp()) {
				p.sendMessage(ChatColor.GREEN + "Operator Bypass.");
				p.openInventory(p.openWorkbench(p.getLocation(), true));
			}
		} else {
			p.openInventory(p.openWorkbench(p.getLocation(), true));
		}
		return false;
	}
	
}
