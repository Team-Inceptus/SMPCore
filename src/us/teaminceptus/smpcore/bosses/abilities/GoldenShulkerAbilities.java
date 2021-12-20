package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.SMPCore;

public class GoldenShulkerAbilities implements Listener {
	
	public SMPCore plugin;
	
	public GoldenShulkerAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onTeleport(EntityTeleportEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.SHULKER))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getCustomName().contains("Golden Shulker"))) return;
		
		Location to = e.getTo();
		Material blockUnder = to.getWorld().getBlockAt(to.getBlockX(), to.getBlockY() - 1, to.getBlockZ()).getType();
		if (!(blockUnder.equals(Material.BEDROCK) || !blockUnder.equals(Material.END_PORTAL) || !blockUnder.equals(Material.BARRIER) || !blockUnder.equals(Material.JIGSAW) || !blockUnder.equals(Material.STRUCTURE_BLOCK) || !blockUnder.equals(Material.STRUCTURE_VOID))) {
			to.getWorld().getBlockAt(to.getBlockX(), to.getBlockY() - 1, to.getBlockZ()).setType(Material.GOLD_BLOCK);
		}
	}
	
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.SHULKER))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getCustomName().contains("Golden Shulker"))) return;
		
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		Random r = new Random();
		
		Player p = (Player) e.getDamager();
		
		if (r.nextBoolean() == true) {
			e.getDamager().getWorld().createExplosion(e.getDamager().getLocation(), 4F, false, true, e.getEntity());
		}
		
		if (r.nextInt(100) < 40) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 1, false, false, true));
		}
	}
}
