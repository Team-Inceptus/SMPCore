package gamercoder215.smpcore.listeners.titan;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

public class TitanWorldEnd implements Listener {
	
	
	protected Main plugin;
	
	public TitanWorldEnd(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDragonBreakBlock(EntityExplodeEvent e) {
		if (e.getEntity() == null) return;
		if (!(e.getEntity() instanceof EnderDragon)) return;
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		
		if (!(p.getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		if (e.getCause().equals(DamageCause.FALL)) e.setCancelled(true);
	}
	
	@EventHandler
	public void onMine(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		e.setDropItems(false);
		
		Block b = e.getBlock();
		Material mat = b.getType();
		
		if (mat.equals(Material.END_STONE)) {
			b.getWorld().dropItemNaturally(b.getLocation(), TitanFetcher.getMitis());
		} else if (mat.equals(Material.STONE)) {
			b.getWorld().dropItemNaturally(b.getLocation(), TitanFetcher.getHardenedMitis());
		} else e.setCancelled(true);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer() == null) return;
		Player p = e.getPlayer();
		
		if (!(p.getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		if (p.isInWater()) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 5, 4, true, false, false));
		}
	}
}
