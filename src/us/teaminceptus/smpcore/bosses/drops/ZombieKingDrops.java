package us.teaminceptus.smpcore.bosses.drops;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.bosses.abilities.ZombieKingAbilities;

public class ZombieKingDrops implements Listener {
	
	public Main plugin;
	
	public ZombieKingDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getEntity().getCustomName().contains("Zombie King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		ZombieKingAbilities.announceDialogue("About time someone beat me. Let's see if you get my super sword.");
		
		e.getEntity().getNearbyEntities(20, 20, 20).forEach(en -> {
			if (en.getType().equals(EntityType.PLAYER)) {
				Player p = (Player) en;
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 3F, 0.5F);
			}
		});
		
		Location deathLoc = e.getEntity().getLocation();
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BONE, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BONE, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.IRON_INGOT, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BAKED_POTATO, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BAKED_POTATO, 32));
	}

}
