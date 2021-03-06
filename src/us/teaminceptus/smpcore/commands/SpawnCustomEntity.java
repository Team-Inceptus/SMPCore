package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Player;

import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.level.WorldServer;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.entities.TitanEnderman;
import us.teaminceptus.smpcore.entities.TitanPiglin;
import us.teaminceptus.smpcore.entities.arena_titans.ArcheryTitan;
import us.teaminceptus.smpcore.entities.arena_titans.GroundTitan;
import us.teaminceptus.smpcore.entities.arena_titans.IceTitan;
import us.teaminceptus.smpcore.fishing.FishingFetcher;
import us.teaminceptus.smpcore.fishing.FishingUtils;
import us.teaminceptus.smpcore.fishing.FishingUtils.FishSkin;

public class SpawnCustomEntity implements CommandExecutor{
	
	public SMPCore plugin;

	public SpawnCustomEntity(SMPCore plugin) {
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
			
			if (args[0].contains("titan_enderman")) {
				TitanEnderman titane = new TitanEnderman(p.getLocation());
				wrld.e(titane);
			} else if (args[0].contains("titan_piglin")) {
				TitanPiglin titanp = new TitanPiglin(p.getLocation(), false);
				wrld.e(titanp);
			} else if (args[0].contains("ice_titan")) {
				IceTitan i = new IceTitan(p.getLocation());
				wrld.e(i);
			} else if (args[0].contains("ground_titan")) {
				GroundTitan g = new GroundTitan(p.getLocation());
				wrld.e(g);
			} else if (args[0].contains("archery_titan")) {
				ArcheryTitan a = new ArcheryTitan(p.getLocation());
				wrld.e(a);
			} else if (args[0].contains("sea_snake")) {
				NPC seaSnake = FishingFetcher.getNPCS().get(FishSkin.SEA_SNAKE);
				seaSnake.spawn(p.getLocation());
				if (seaSnake.isSpawned()) {
					FishingUtils.addNPCAttributes(p, seaSnake, 2);
				}
			}
		}
		
		return false;
	} 
}
