package us.teaminceptus.smpcore.abilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;

public class Cosmetics implements Listener {

	protected SMPCore plugin;
	
	public Cosmetics(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	private Particle getParticle(Player p) {
		String rank = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getString("rank");
		
		Particle pa = null;
		
		if (rank.contains("vip"))
			pa = Particle.VILLAGER_HAPPY;
		else if (rank.equalsIgnoreCase("mvp"))
			pa = Particle.CRIT_MAGIC;
		else if (rank.equalsIgnoreCase("mvp+"))
			pa = Particle.SPELL_WITCH;
		else if (p.hasPermission("core.admin.gamemodebypass"))
			pa = Particle.ELECTRIC_SPARK;
		
		return pa;
	}
	
	@EventHandler
	public void onArrowShoot(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() == null) return;
		if (!(e.getEntity().getShooter() instanceof Player p)) return;
		
		Projectile proj = e.getEntity();
		
		final Particle pa = getParticle(p);
		
		new BukkitRunnable() {
			public void run() {
				if (pa == null) cancel();
				if (proj.isDead() || (proj instanceof AbstractArrow a && a.isInBlock())) cancel();
				
				proj.getWorld().spawnParticle(pa, proj.getLocation().subtract(proj.getLocation().getDirection().multiply(-1)), 1);
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String rank = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getString("rank");
		
		if (rank.equalsIgnoreCase("mvp+") || p.hasPermission("core.admin.gamemodebypass")) {
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
 	}

}
