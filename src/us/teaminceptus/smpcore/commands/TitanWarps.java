package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.titan.TitanEnchantmentTable;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class TitanWarps implements CommandExecutor {
	
	public SMPCore plugin;
	
	public TitanWarps(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("titanwarps").setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if (!(TitanEnchantmentTable.hasUnlocked(plugin, p))) {
			p.sendMessage(ChatColor.RED + "You need to kill 30 titans to unlock this command!");
		} else {
			p.openInventory(TitanFetcher.getTitanWarps());
		}
		
		return false;
	}
}
