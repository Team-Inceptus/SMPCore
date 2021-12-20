package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.SMPCore;

public class SpiderQueenAbilities implements Listener {
	
	public SMPCore plugin;
	
	public SpiderQueenAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.SPIDER))) return;
		if (!(e.getEntity().getCustomName().contains("Spider Queen"))) return;
		Random r = new Random();
		
		if (e.getDamager().getType() != EntityType.PLAYER) return;
		
		Player p = (Player) e.getDamager();
		
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 2, false, false, true));
		}
		
		if (r.nextInt(100) < 40) {
			p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.CAVE_SPIDER);
			p.sendMessage(ChatColor.DARK_RED + "A Minion has Spawned!");
		}

	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getCustomName().contains("Spider Queen"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		Random r = new Random();
		
		if (e.getDamager().getType() != EntityType.PLAYER) return;
		
		Player p = (Player) e.getEntity();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1, false, false, true));
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1, false, false, true));
		}
		
		if (r.nextInt(100) < 75) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 5, 2, false, false, true));
		}


	}
	
}
