package gamercoder215.smpcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;

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
				cp.getHandle().b.sendPacket(packet1);
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
				cp.getHandle().b.sendPacket(packet1);
			}
		}.runTask(plugin);
	}
	
}
