package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import gamercoder215.smpcore.Main;

public class EnergyGuardianAbilities implements Listener {
	
	public static Main plugin;
	
	public EnergyGuardianAbilities(Main plugin) {
		EnergyGuardianAbilities.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) return;
		LivingEntity en = (LivingEntity) e.getEntity();
		if(!(en.getType().equals(EntityType.ELDER_GUARDIAN))) return;
		if (en.getCustomName() == null) return;
		if (!(en.getCustomName().contains("Energy Guardian"))) return;
		
		if (e.getCause().equals(DamageCause.PROJECTILE) || e.getCause().equals(DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(DamageCause.BLOCK_EXPLOSION)) e.setCancelled(true);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) return;
		LivingEntity en = (LivingEntity) e.getEntity();
		if(!(en.getType().equals(EntityType.ELDER_GUARDIAN))) return;
		if (en.getCustomName() == null) return;
		if (!(en.getCustomName().contains("Energy Guardian"))) return;
		
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
		p.getWorld().strikeLightning(p.getLocation());
		
		if (r.nextInt(100) < 45) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1, false));
		}
		if (r.nextInt(100) < 30) {
			p.getWorld().createExplosion(p.getLocation(), 6, true, true, e.getEntity());
		}
		if (r.nextInt(100) < 60) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.AQUA + "The Guard's Energy has cancelled your attack!");
		}
	}
}
