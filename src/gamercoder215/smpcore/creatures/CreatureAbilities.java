package gamercoder215.smpcore.creatures;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import gamercoder215.smpcore.Main;

public class CreatureAbilities implements Listener {
	
	public Main plugin;
	
	public CreatureAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity en = e.getEntity();
		if (en.getType().equals(EntityType.PLAYER)) return;
		if (en.getCustomName() == null) return;
		
		String name = en.getCustomName();
		if (!(name.contains("Creature"))) return;
		
		if (e.getCause().equals(DamageCause.FALL) || e.getCause().equals(DamageCause.SUFFOCATION) || e.getCause().equals(DamageCause.CRAMMING) || e.getCause().equals(DamageCause.DROWNING)) e.setCancelled(true);
	}

}
