package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.entities.PathfindingTest;
import net.minecraft.server.level.WorldServer;

public class SpawnCustomEntity implements CommandExecutor{
	
	public Main plugin;

	public SpawnCustomEntity(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("spawncustomentity").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (!(p.isOp())) {
			p.sendMessage(ChatColor.RED + "You need to be an OP to use this command!");
		} else {
			if (args.length < 1) p.sendMessage(ChatColor.RED + "Please provide an entity!");
			
			if (args[0].contains("test")) {
				PathfindingTest test = new PathfindingTest(p.getLocation());
				WorldServer wrld = ((CraftWorld) p.getWorld()).getHandle();
				wrld.addEntity(test);
			}
		}
		
		return false;
	} 
}
