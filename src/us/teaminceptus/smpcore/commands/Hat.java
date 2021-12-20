package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.SMPCore;

public class Hat implements CommandExecutor {

	protected SMPCore plugin;
	
	public Hat(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("hat").setExecutor(this);;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) {
			sender.sendMessage(ChatColor.RED + "Players only.");
			return false;
		}
		
		String rank = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getString("rank");
		
		if (!(rank.contains("mvp")) && !(p.hasPermission("core.admin.gamemodebypass"))) {
			p.sendMessage(ChatColor.RED + "You need MVP or Higher to use this Command!");
			return false;
		}
		
		if (p.getEquipment().getHelmet() != null) {
			p.sendMessage(ChatColor.RED + "You need to have an empty helmet to use this command!");
			return false;
		}
		
		if (p.getEquipment().getItemInMainHand() == null) {
			p.sendMessage(ChatColor.RED + "You need to have a valid item in hand!");
			return false;
		}
		
		ItemStack item = p.getEquipment().getItemInMainHand();
		
		p.getEquipment().setItemInMainHand(null);
		p.getEquipment().setHelmet(item);
		
		return true;
	}

}
