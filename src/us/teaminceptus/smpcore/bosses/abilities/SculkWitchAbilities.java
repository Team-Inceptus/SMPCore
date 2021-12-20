package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import us.teaminceptus.smpcore.SMPCore;

public class SculkWitchAbilities implements Listener {
	
	public SMPCore plugin;
	
	public SculkWitchAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onTargetChance(EntityTargetEvent e) {
		if (!(e.getTarget() instanceof Player p)) return;
		if (p.getEquipment().getHelmet() == null) return;
		if (!(p.getEquipment().getHelmet().getItemMeta().getDisplayName().contains("Sculk Helmet"))) return;
		if (!(p.getEquipment().getHelmet().getItemMeta().getEnchantLevel(Enchantment.OXYGEN) == 250)) return;
		
		if (e.getEntityType() == EntityType.ZOMBIE || e.getEntityType() == EntityType.SKELETON) e.setTarget(null);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType() == EntityType.WITCH)) return;
		if (!(e.getEntity().getCustomName().contains("Sculk"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Player p = (Player) e.getDamager();

		p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
		
		int chance = r.nextInt(100);
		if (chance < 75) {
			p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
		}
		
		if (chance < 50) {
			p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
		}
		
		if (chance < 10) {
			p.getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
		}
		
		
	}
}
