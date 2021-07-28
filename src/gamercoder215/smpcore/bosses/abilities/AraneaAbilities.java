package gamercoder215.smpcore.bosses.abilities;

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

import gamercoder215.smpcore.Main;

public class AraneaAbilities implements Listener {
	
	public Main plugin;
	
	public AraneaAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.SPIDER))) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getEntity().getCustomName().contains("Aranea"))) return;
		Random r = new Random();
		
		Player p = (Player) e.getDamager();
		if (r.nextInt(100) < 40) {
			p.sendMessage(ChatColor.DARK_RED + "The Aranea has weakened you!");
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 19, true, false, true));
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getType().equals(EntityType.SPIDER))) return;
		if (!(e.getDamager().getCustomName().contains("Aranea"))) return;
		Random r = new Random();
		
		Player p = (Player) e.getEntity();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 3, 600, true, false, true));
		if (r.nextInt(100) < 75) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 4, 600, true, false, true));
		}
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 6, 600, true, false, true));
		}
	}
	
}
