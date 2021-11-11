package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.Main;

public class DimensionDragonAbilities implements Listener {
	
	public Main plugin;
	
	public DimensionDragonAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.ENDER_DRAGON))) return;
		if (!(e.getEntity().getCustomName().contains("Dimensional Dragon"))) return;
		Random r = new Random();
			
			if (!e.getDamager().getType().equals(EntityType.ARROW) && !e.getDamager().getType().equals(EntityType.SPECTRAL_ARROW)) {
				e.getDamager().getWorld().strikeLightning(e.getDamager().getLocation());
			} else {
				Projectile proj = (Projectile) e.getDamager();
				if (!(proj.getShooter() instanceof Entity)) return;
				Entity dat = (Entity) proj.getShooter();
				dat.getWorld().strikeLightning(dat.getLocation());
			}
		
			if (!(e.getDamager() instanceof Player)) return;
			Player p = (Player) e.getDamager();
			
			if (r.nextInt(100) < 25) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1, false));
			}
			if (r.nextInt(100) < 10) {
				p.getWorld().createExplosion(p.getLocation(), 10, true, true, e.getEntity());
			}
			if (r.nextInt(100) < 30) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.AQUA + "The Guard's Energy has cancelled your attack!");
			}
			
			if (r.nextBoolean() == true) {
				Location oneF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 45, p.getLocation().getPitch());
				Location twoF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 90, p.getLocation().getPitch());
				Location threeF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 135, p.getLocation().getPitch());
				Location fourF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 180, p.getLocation().getPitch());
				Location fiveF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 225, p.getLocation().getPitch());
				Location sixF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 270, p.getLocation().getPitch());
				Location sevenF = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ(), e.getEntity().getLocation().getYaw() + 315, p.getLocation().getPitch());
				Location eightF = e.getEntity().getLocation();
				
				e.getEntity().getWorld().spawnEntity(oneF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(twoF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(threeF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(fourF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(fiveF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(sixF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(sevenF, EntityType.DRAGON_FIREBALL);
				e.getEntity().getWorld().spawnEntity(eightF, EntityType.DRAGON_FIREBALL);
			}
			
			if (r.nextInt(100) < 25) {
				EnderCrystal ec = (EnderCrystal) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ENDER_CRYSTAL);
				ec.setShowingBottom(true);
			}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getType().equals(EntityType.ENDER_DRAGON))) return;
		if (!(e.getDamager().getCustomName().contains("Dimensional Dragon"))) return;
		Random r = new Random();
		
		Player p = (Player) e.getEntity();
		if (r.nextInt(100) < 30) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 255, false));
			p.sendMessage(ChatColor.DARK_RED + "The Dimensional Dragon has frozen you!");
		}
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 19, true, false, true));
		}
		
		
	}
}
