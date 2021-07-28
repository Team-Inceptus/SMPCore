package gamercoder215.smpcore.bosses.abilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public class BossStatusUpdate implements Listener {
	
	private Main plugin;
	
	public BossStatusUpdate(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity en = e.getEntity();
		
		if (en.getType().equals(EntityType.PLAYER)) return;
		if (en.getType().equals(EntityType.WITHER)) return;
		if (en.getType().equals(EntityType.ARMOR_STAND)) return;
		if (!(en.isCustomNameVisible())) return;
		
		if (!(en instanceof LivingEntity)) return;
		
		LivingEntity len = (LivingEntity) en;
		
		ArmorStand healthBar = (ArmorStand) len.getWorld().spawnEntity(len.getLocation(), EntityType.ARMOR_STAND);
		
		healthBar.setInvulnerable(true);
		healthBar.setMarker(true);
		healthBar.setVisible(false);
		healthBar.setBasePlate(false);
		healthBar.setGravity(false);
		healthBar.setCustomName(ChatColor.RED + Double.toString(Math.floor(len.getHealth())) + " HP");
		healthBar.setCustomNameVisible(true);
		
		healthBar.teleport(len.getLocation().subtract(0, 0.75, 0));
		
		new BukkitRunnable() {
			public void run() {
				while (!healthBar.isDead()) {
					healthBar.remove();
				}
			}
		}.runTaskLater(plugin, 60);
	}
	
}
