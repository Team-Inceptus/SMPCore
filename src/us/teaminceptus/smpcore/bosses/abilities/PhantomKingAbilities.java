package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import us.teaminceptus.smpcore.SMPCore;

public class PhantomKingAbilities implements Listener {
	
	public SMPCore plugin;
	
	public PhantomKingAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.PHANTOM))) return;
		if (!(e.getEntity().getCustomName().contains("Phantom King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Player p = (Player) e.getDamager();

		if (r.nextInt(100) < 80) {
			p.getWorld().spawnEntity(p.getLocation(), EntityType.PHANTOM);
			p.sendMessage(ChatColor.GRAY + "A Minion has spawned!");
		}
	}
}
