package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.TitanEnchantmentTable;
import gamercoder215.smpcore.utils.TitanFetcher;

public class TitanWarps implements CommandExecutor {
	
	public Main plugin;
	
	public TitanWarps(Main plugin) {
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
