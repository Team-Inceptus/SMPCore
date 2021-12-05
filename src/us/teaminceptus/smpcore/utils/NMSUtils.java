package us.teaminceptus.smpcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import us.teaminceptus.smpcore.Main;

public class NMSUtils implements Listener {
	
	protected Main plugin;
	
	public NMSUtils(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onHealthRegain(EntityRegainHealthEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		CraftPlayer cp = (CraftPlayer) p;
		
		float healthFloat = (float) p.getHealth();
		
		PacketPlayOutUpdateHealth packet1 = new PacketPlayOutUpdateHealth(healthFloat / 10, p.getFoodLevel(), p.getSaturation());
		
		new BukkitRunnable() {
			public void run() {
				cp.getHandle().b.a(packet1);
			}
		}.runTask(plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
			
		CraftPlayer cp = (CraftPlayer) p;
		
		float healthFloat = (float) p.getHealth();
		
		PacketPlayOutUpdateHealth packet1 = new PacketPlayOutUpdateHealth(healthFloat / 10, p.getFoodLevel(), p.getSaturation());
		
		new BukkitRunnable() {
			public void run() {
				cp.getHandle().b.a(packet1);
			}
		}.runTask(plugin);
	}
	
}
