package us.teaminceptus.smpcore.bosses.drops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.SMPCore;

public class AraneaDrops implements Listener {
	
	public SMPCore plugin;
	
	public AraneaDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.SPIDER))) return;
		if (!(e.getEntity().getCustomName().contains("Aranea"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Random r = new Random();
		e.setDroppedExp(r.nextInt(250 - 100) + 100);
		
		Location deathLoc = e.getEntity().getLocation();
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.FERMENTED_SPIDER_EYE, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.FERMENTED_SPIDER_EYE, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.FERMENTED_SPIDER_EYE, 32));
	}
}
