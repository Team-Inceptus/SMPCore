package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import gamercoder215.smpcore.Main;

public class WithermanAbilities implements Listener {
	
	protected Main plugin;
	
	public WithermanAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntityType().equals(EntityType.ENDERMAN))) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getCustomName().contains("Witherman"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		if (!(e.getDamager() instanceof LivingEntity)) return;
		
		LivingEntity damager = (LivingEntity) e.getDamager();
		
		if (r.nextBoolean() == true) {
			damager.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 2, true, true, true));
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (!(e.getDamager().getType().equals(EntityType.ENDERMAN))) return;
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getDamager().getCustomName().contains("Witherman"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		
		if (!(e.getEntity() instanceof LivingEntity)) return;
		
		LivingEntity target = (LivingEntity) e.getEntity();
		
		target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 3, true, true, true));
		
		if (r.nextInt(100) < 10) {
			target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 *5, 1, true, false, true));
		}
	}
}
