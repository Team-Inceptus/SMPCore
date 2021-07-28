package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import gamercoder215.smpcore.Main;

public class BlazeKingAbilities implements Listener {
	
	public Main plugin;
	
	public BlazeKingAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.BLAZE))) return;
		if (!(e.getEntity().getCustomName().contains("Blaze King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Player p = (Player) e.getDamager();
		e.getDamager().setFireTicks(600);
		if (r.nextInt(100) < 75) {
			e.getDamager().getWorld().strikeLightningEffect(e.getDamager().getLocation());
		}
		
		if (r.nextInt(100) < 40) {
			p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.BLAZE);
			p.sendMessage(ChatColor.DARK_RED + "A Minion has Spawned!");
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getCustomName().contains("Blaze King"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		
		Player p = (Player) e.getEntity();
		
		
		if (r.nextInt(100) < 50) {
			if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
				p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			}
		}
	}
	
}
