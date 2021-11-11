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

import us.teaminceptus.smpcore.Main;

public class DimensionGuardAbilities implements Listener {
	
	public Main plugin;
	
	public DimensionGuardAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.WITHER)) && !(e.getEntityType().equals(EntityType.GHAST))) return;
		if (!(e.getEntity().getCustomName().contains("Dimensional Guard")) && !(e.getEntity().getCustomName().contains("Guard's Energy Field"))) return;
		Random r = new Random();
		
		if (e.getEntity().getCustomName().contains("Guard's Energy Field")) {
			e.getDamager().getWorld().strikeLightningEffect(e.getDamager().getLocation());
			e.getDamager().setFireTicks(100);
		} else {
		
			if (!(e.getDamager() instanceof Player)) return;
			Player p = (Player) e.getDamager();
			
			if (r.nextInt(100) < 25) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0, false));
			}
			if (r.nextInt(100) < 5) {
				p.getWorld().createExplosion(p.getLocation(), 10, true, true, e.getEntity());
			}
			if (r.nextInt(100) < 10) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.AQUA + "The Guard's Energy has absorbed your attack!");
			}
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getType().equals(EntityType.WITHER))) return;
		if (!(e.getDamager().getCustomName().contains("Dimensional Guard"))) return;
		Random r = new Random();
		
		Player p = (Player) e.getEntity();
		if (r.nextInt(100) < 15) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, false));
			p.sendMessage(ChatColor.DARK_RED + "The Dimensional Guard has frozen you!");
		}
		
		
	}
	
}
