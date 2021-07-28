package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;

public class CreateWorld implements CommandExecutor {
	
	public Main plugin;
	
	public CreateWorld(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("createworld").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		if (!(p.getUniqueId().toString().equalsIgnoreCase("8069233e-5b25-410c-9475-daa6a044c365"))) {
			p.sendMessage(ChatColor.RED + "Only GamerCoder215 can make worlds.");
		} else {
			WorldCreator worldCreator = new WorldCreator(args[0]);
			worldCreator.generateStructures(true);
			worldCreator.type(WorldType.NORMAL);
			worldCreator.environment(Environment.NORMAL);
			World newWorld = worldCreator.createWorld();
			
			p.teleport(newWorld.getSpawnLocation());
		}
		
		return false;
	}

}
