package us.teaminceptus.smpcore.bosses.abilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class BossStatusUpdate implements Listener {
	
	protected Main plugin;
	
	public BossStatusUpdate(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	

	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity en = e.getEntity();
		
		if (en.getType().equals(EntityType.PLAYER)) return;
		if (en.getType().equals(EntityType.WITHER)) return;
		if (en.getType().equals(EntityType.ARMOR_STAND)) return;
		if (!(en.isCustomNameVisible())) return;
		
		if (!(en instanceof LivingEntity)) return;
		
		LivingEntity len = (LivingEntity) en;
		
		String customName = (len.getCustomName().contains("-") ? len.getCustomName().split("-")[0].substring(0, len.getCustomName().split("-")[0].length() - 1) : len.getCustomName());
		
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.FLOOR);
		
		new BukkitRunnable() {
			public void run() {
				len.setCustomName(customName + ChatColor.GREEN + " - " + GeneralUtils.withSuffix(Double.parseDouble(df.format(len.getHealth()))));
			}
		}.runTask(plugin);
	}
	
	@EventHandler
	public void onHealth(EntityRegainHealthEvent e) {
		Entity en = e.getEntity();
		
		if (en.getType().equals(EntityType.PLAYER)) return;
		if (en.getType().equals(EntityType.WITHER)) return;
		if (en.getType().equals(EntityType.ARMOR_STAND)) return;
		if (!(en.isCustomNameVisible())) return;
		
		if (!(en instanceof LivingEntity)) return;
		
		LivingEntity len = (LivingEntity) en;
		
		String customName = (len.getCustomName().contains("-") ? len.getCustomName().split("-")[0].substring(0, len.getCustomName().split("-")[0].length() - 1) : len.getCustomName());
		
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.FLOOR);
		
		new BukkitRunnable() {
			public void run() {
				len.setCustomName(customName + ChatColor.GREEN + " - " + GeneralUtils.withSuffix(Double.parseDouble(df.format(len.getHealth()))));
			}
		}.runTask(plugin);
	}
	
}
