package gamercoder215.smpcore.listeners.titan;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public class TitanWorldNether implements Listener {
	
	protected Main plugin;
	
	public TitanWorldNether(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	private void replenish(Player p, Block b) {
		Material type = b.getType();
		
		if (type.equals(Material.END_STONE)) {
			int chance = r.nextInt(100);
			
			if (chance < 5 && b.getLightLevel() < 2) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.EMERALD_BLOCK);
			} else {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.getWorld().getBlockAt(b.getLocation()).setType(Material.END_STONE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
			}
		} else if (type.equals(Material.SEA_LANTERN)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.END_STONE);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.SEA_LANTERN);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(75 - 30) + 30));
		} else if (type.equals(Material.EMERALD_BLOCK)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.END_STONE);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.EMERALD_BLOCK);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(300 - 180) + 180));
		} else if (type.equals(Material.BLACKSTONE)) {
			int chance = r.nextInt(100);
			
			if (chance < 15) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.GILDED_BLACKSTONE);
			} else {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.getWorld().getBlockAt(b.getLocation()).setType(Material.BLACKSTONE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
			}
		} else if (type.equals(Material.GILDED_BLACKSTONE)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BLACKSTONE);
		}
	}
	
	@EventHandler
	public void onBlockDamageUnbreakables(BlockDamageEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		Material type = e.getBlock().getType();
		
		if (type.equals(Material.GRANITE) || type.equals(Material.SAND) || type.equals(Material.CAVE_VINES) || type.equals(Material.CAVE_VINES_PLANT) || 
				type.equals(Material.GLOW_LICHEN) || type.equals(Material.NETHER_WART_BLOCK) || type.equals(Material.NETHERRACK)) e.setCancelled(true);
	}
	
	@EventHandler
	public void onHarvest(PlayerHarvestBlockEvent e) {
		if (e.getHarvestedBlock() == null) return;
		if (!(e.getHarvestedBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		
		Material type = e.getHarvestedBlock().getType();
		
		if (type.equals(Material.CAVE_VINES) || type.equals(Material.CAVE_VINES_PLANT)) return;
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		Player p = e.getPlayer();
		Material type = e.getBlock().getType();
		
		if (type.equals(Material.SEA_LANTERN)) e.setInstaBreak(true);
		
		if (type.equals(Material.EMERALD_BLOCK)) p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 3, 2, true, false, false));
		else if (type.equals(Material.BLACKSTONE)) p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 3, 5, true, false, false));
	}
	
	@EventHandler
	public void onMine(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		replenish(p, e.getBlock());
	}
	
}
