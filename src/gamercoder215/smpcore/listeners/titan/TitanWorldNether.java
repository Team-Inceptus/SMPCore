package gamercoder215.smpcore.listeners.titan;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

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
			
			if (chance < 5 && b.getLightFromBlocks() < 2) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.EMERALD_BLOCK);
			} else if (chance > 5 && chance < 10 && b.getLightFromBlocks() > 2) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.SEA_LANTERN);
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
		} else if (type.equals(Material.CRIMSON_NYLIUM)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.CRIMSON_NYLIUM);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(30 - 10) + 10));
		} else if (type.equals(Material.CRIMSON_HYPHAE)) {
			int chance = r.nextInt(1000);
			
			if (chance < 5) {
				b.getWorld().dropItemNaturally(b.getLocation(), TitanFetcher.getMitisEssence());
				p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 3F, 1F);
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.DARK_AQUA + " Mitis Essence" + ChatColor.GRAY + " mined by " + p.getName() + "!");
			}
			
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.CRIMSON_HYPHAE);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(45 - 15) + 15));
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		Material type = e.getBlock().getType();
		
		if (type.equals(Material.SEA_LANTERN)) e.setInstaBreak(true);
	}
	
	@EventHandler
	public void onMine(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		
		Player p = e.getPlayer();
		
		if (p.isOp() && p.getGameMode().equals(GameMode.CREATIVE)) return;
		
		e.setCancelled(true);
		replenish(p, e.getBlock());
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_titan_nether"))) return;
		
		if (e.getPlayer().isOp() && e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		
		e.setCancelled(true);
	}
	
}
