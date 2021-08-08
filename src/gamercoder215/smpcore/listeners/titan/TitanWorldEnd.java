package gamercoder215.smpcore.listeners.titan;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

public class TitanWorldEnd implements Listener {
	
	protected Main plugin;
	
	public TitanWorldEnd(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDragonBreakBlock(EntityExplodeEvent e) {
		if (e.getEntity() == null) return;
		if (!(e.getEntity() instanceof EnderDragon)) return;
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onBlockDamageUnbreakables(BlockDamageEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		Material mat = e.getBlock().getType();
		
		if (mat.equals(Material.END_STONE_BRICKS) || mat.equals(Material.BLACKSTONE) || mat.equals(Material.CRYING_OBSIDIAN)) e.setCancelled(true);
	}
	
	@EventHandler
	public void onJump(PlayerStatisticIncrementEvent e) {
		if (e.getPlayer() == null) return;
		if (e.getStatistic() == null) return;
		if (!(e.getStatistic().equals(Statistic.JUMP))) return;
		if (!(e.getPlayer().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		Player p = e.getPlayer();
		
		p.setVelocity(new Vector(p.getVelocity().getX(), p.getVelocity().getY() * 3, p.getVelocity().getZ()));
		
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
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		
		Player p = e.getPlayer();
		ItemStack tool = e.getItemInHand();
		Material toolType = tool.getType();
		
		if (toolType != Material.NETHERITE_PICKAXE) e.setCancelled(true);
		else {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2, 1, true, false, false));
		}
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
