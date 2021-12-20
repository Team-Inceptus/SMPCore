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

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.bosses.abilities.EmeraldWarriorAbilities;

public class EmeraldWarriorDrops implements Listener {
	
	public SMPCore plugin;
	
	public EmeraldWarriorDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getEntity().getCustomName().contains("Emerald Warrior"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		EmeraldWarriorAbilities.announceDialogue("Time for me to ascend with the emerald gods.");
		
		e.getEntity().getNearbyEntities(20, 20, 20).forEach(en -> {
			if (en.getType().equals(EntityType.PLAYER)) {
				Player p = (Player) en;
				p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 3F, 0.5F);
			}
		});
		
		Location deathLoc = e.getEntity().getLocation();
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BONE, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.EMERALD_BLOCK, 64));
	}

}
