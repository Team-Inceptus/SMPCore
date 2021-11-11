package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.utils.TradeInventories;

public class TradesMenu implements CommandExecutor {

	public Main plugin;
	
	public TradesMenu(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("trades").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "GUIs can only be executed by players!");
		
		Player p = (Player) sender;
		
		if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER) < 1) {
			p.sendMessage(ChatColor.RED + "This menu can only be used if you have killed a wither.");
		} else {
			p.openInventory(TradeInventories.getPage1());
			p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 0F);
		}
		return false;
	}

}
