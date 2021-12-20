package us.teaminceptus.smpcore.listeners.titan;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class TitanEntities implements Listener {
	
	protected SMPCore plugin;
	
	public TitanEntities(SMPCore plugin) {
		
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) return;
		
		World wrld = e.getEntity().getWorld();
		
		if (!(wrld.getName().contains("world_titan"))) return;
		
		EntityType type = e.getEntityType();
		
		LivingEntity en = e.getEntity();
		
		if (type.equals(EntityType.ENDERMAN)) {
			e.getDrops().clear();
			
			ItemStack essence = TitanFetcher.getMitisEssence();
			
			if (r.nextInt(100) < 4) {
				en.getWorld().dropItemNaturally(en.getLocation(), essence);
			}
			
		} else if (type.equals(EntityType.PIGLIN)) {
			e.getDrops().clear();
			
			ItemStack pork = TitanFetcher.getTitanPorkchop();
			pork.setAmount(r.nextInt(3));
			
			if (r.nextBoolean() == true && r.nextBoolean() == true) {
				en.getWorld().dropItemNaturally(en.getLocation(), pork);
			}
		}
	}
}
