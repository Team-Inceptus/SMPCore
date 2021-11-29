package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import net.minecraft.server.level.WorldServer;
import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.entities.PathfindingTest;
import us.teaminceptus.smpcore.entities.TitanEnderman;
import us.teaminceptus.smpcore.entities.TitanPiglin;
import us.teaminceptus.smpcore.entities.arena_titans.GroundTitan;
import us.teaminceptus.smpcore.entities.arena_titans.IceTitan;

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
		WorldServer wrld = ((CraftWorld) p.getWorld()).getHandle();
		
		if (!(p.isOp())) {
			p.sendMessage(ChatColor.RED + "You need to be an OP to use this command!");
		} else {
			if (args.length < 1) p.sendMessage(ChatColor.RED + "Please provide an entity!");
			
			if (args[0].contains("test")) {
				PathfindingTest test = new PathfindingTest(p.getLocation());
				wrld.addEntity(test);
			} else if (args[0].contains("titan_enderman")) {
				TitanEnderman titane = new TitanEnderman(p.getLocation());
				wrld.addEntity(titane);
			} else if (args[0].contains("titan_piglin")) {
				TitanPiglin titanp = new TitanPiglin(p.getLocation(), false);
				wrld.addEntity(titanp);
			} else if (args[0].contains("ice_titan")) {
				IceTitan i = new IceTitan(p.getLocation());
				wrld.addEntity(i);
			} else if (args[0].contains("ground_titan")) {
				GroundTitan g = new GroundTitan(p.getLocation());
				wrld.addEntity(g);
			}
		}
		
		return false;
	} 
}
