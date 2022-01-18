package us.teaminceptus.smpcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.teaminceptus.smpcore.SMPCore;

public class CoderTest implements CommandExecutor {
	public SMPCore plugin;
	
	public CoderTest(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("codertest").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) return false;
		
		org.bukkit.inventory.ItemStack item = p.getInventory().getItemInMainHand();
		
		if (item == null) return false;
		
		
		
		return false;
	}
}
