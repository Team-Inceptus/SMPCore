package us.teaminceptus.smpcore.planatae;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.level.WorldServer;
import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.planatae.entities.WildDamageTitan;
import us.teaminceptus.smpcore.planatae.entities.WildGoldenTitan;
import us.teaminceptus.smpcore.planatae.entities.WildNetherTitan;
import us.teaminceptus.smpcore.planatae.entities.WildSpeedTitan;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class TitanPlanatae implements Listener {
	
	protected Main plugin;
	
	public TitanPlanatae(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.NETHERITE_BLOCK, 45);
		exp.put(Material.END_STONE, 35);
		exp.put(Material.SLIME_BLOCK, 25);
		exp.put(Material.GOLD_BLOCK, 55);
		exp.put(Material.RAW_GOLD_BLOCK, 20);
		exp.put(Material.DEEPSLATE_EMERALD_ORE, 35);
		exp.put(Material.ANCIENT_DEBRIS, 30);
		exp.put(Material.DRIPSTONE_BLOCK, 10);
		exp.put(Material.NETHER_WART_BLOCK, 15);
		exp.put(Material.COARSE_DIRT, 10);
		exp.put(Material.RED_NETHER_BRICKS, 5);
		exp.put(Material.NETHER_GOLD_ORE, 15);
		exp.put(Material.GILDED_BLACKSTONE, 20);
		
		return exp;
	}
	
	public static Map<Material, Integer> getExpChance() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.NETHERITE_BLOCK, 75);
		exp.put(Material.END_STONE, 40);
		exp.put(Material.SLIME_BLOCK, 75);
		exp.put(Material.GOLD_BLOCK, 40);
		exp.put(Material.RAW_GOLD_BLOCK, 25);
		exp.put(Material.DEEPSLATE_EMERALD_ORE, 100);
		exp.put(Material.ANCIENT_DEBRIS, 60);
		exp.put(Material.DRIPSTONE_BLOCK, 10);
		exp.put(Material.NETHER_WART_BLOCK, 15);
		exp.put(Material.COARSE_DIRT, 25);
		exp.put(Material.RED_NETHER_BRICKS, 25);
		exp.put(Material.NETHER_GOLD_ORE, 100);
		exp.put(Material.GILDED_BLACKSTONE, 80);
		
		return exp;
	}
	
	public static Map<Material, Integer> getDropChance() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.NETHERITE_BLOCK, 10);
		dropChance.put(Material.RAW_IRON_BLOCK, 30);
		dropChance.put(Material.GOLD_BLOCK, 50);
		dropChance.put(Material.DEEPSLATE_EMERALD_ORE, 75);
		dropChance.put(Material.SMOOTH_BASALT, 35);
		dropChance.put(Material.GILDED_BLACKSTONE, 80);
		dropChance.put(Material.ANCIENT_DEBRIS, 1);
		dropChance.put(Material.DIRT, 35);
		dropChance.put(Material.COARSE_DIRT, 45);
		dropChance.put(Material.DRIPSTONE_BLOCK, 30);
		
		return dropChance;
	}
	
	static Random r = new Random();
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.LIME_STAINED_GLASS, PlanataeFetcher.getUpsilonegen());
		drops.put(Material.SLIME_BLOCK, PlanataeFetcher.getUpsilonegen());
		drops.put(Material.GREEN_STAINED_GLASS, PlanataeFetcher.getUpsilonegen());
		drops.put(Material.END_STONE, PlanataeFetcher.getZaminium());
		drops.put(Material.NETHERITE_BLOCK, PlanataeFetcher.getHardenedZaminium());
		drops.put(Material.RAW_IRON_BLOCK, PlanataeFetcher.getRawTermentium());
		drops.put(Material.SMOOTH_SANDSTONE, PlanataeFetcher.getClade());
		
		ItemStack bI = PlanataeFetcher.getBeratiumIngot();
		bI.setAmount(r.nextInt(5) + 1);
		ItemStack bI2 = PlanataeFetcher.getBeratiumIngot();
		bI2.setAmount(r.nextInt(7) + 3);
		
		drops.put(Material.NETHER_BRICKS, bI);
		drops.put(Material.RED_NETHER_BRICKS, bI2);
		drops.put(Material.NETHERRACK, PlanataeFetcher.getSoftBeratium());
		drops.put(Material.CRIMSON_NYLIUM, PlanataeFetcher.getSoftBeratium());
		drops.put(Material.NETHER_WART_BLOCK, PlanataeFetcher.getBeratiumWart());
		
		ItemStack bI3 = PlanataeFetcher.getBeratiumNugget();
		bI3.setAmount(r.nextInt(8) + 2);
		
		drops.put(Material.NETHER_GOLD_ORE, bI3);
		
		ItemStack aI = PlanataeFetcher.getRawAurum();
		aI.setAmount(r.nextInt(3) + 1);
		ItemStack aI2 = PlanataeFetcher.getAurum();
		aI2.setAmount(r.nextInt(2) + 1);
		
		drops.put(Material.RAW_GOLD_BLOCK, aI);
		drops.put(Material.GOLD_BLOCK, aI2);
		
		drops.put(Material.DEEPSLATE_EMERALD_ORE, PlanataeFetcher.getPrasinus());
		drops.put(Material.SMOOTH_BASALT, PlanataeFetcher.getSilicomium());
		drops.put(Material.BLACKSTONE, PlanataeFetcher.getLambdaAter());
		drops.put(Material.GILDED_BLACKSTONE, PlanataeFetcher.getRawAurum());
		drops.put(Material.ANCIENT_DEBRIS, PlanataeFetcher.getLambdaStick());
		drops.put(Material.DIRT, PlanataeFetcher.getStercus());
		drops.put(Material.COARSE_DIRT, PlanataeFetcher.getStercus());
		drops.put(Material.DRIPSTONE_BLOCK, PlanataeFetcher.getEpsilonSheet());
		
		return drops;
	}
	
	private void replenish(Block b) {
		switch (b.getType()) {
			case LIME_STAINED_GLASS_PANE:
			case GREEN_STAINED_GLASS_PANE: {
				Material original = b.getType();
				b.setType(Material.AIR);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(30) + 15));
				break;
			}
			case SLIME_BLOCK: {
				b.setType(Material.LIME_STAINED_GLASS);
				break;
			}
			case ANCIENT_DEBRIS: {
				b.setType(Material.DRIPSTONE_BLOCK);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.ANCIENT_DEBRIS);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(40) + 20));
			}
			case DEEPSLATE_EMERALD_ORE: {
				b.setType(Material.SMOOTH_BASALT);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.DEEPSLATE_EMERALD_ORE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(45) + 15));
				break;
			}
			case GILDED_BLACKSTONE: {
				b.setType(Material.BLACKSTONE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.GILDED_BLACKSTONE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 30));
				break;
			}
			case NETHER_GOLD_ORE:
			case CRIMSON_NYLIUM: {
				Material original = b.getType();
				b.setType(Material.NETHERRACK);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(20) + 45));
				break;
			}
			case NETHER_WART_BLOCK: {
				b.setType(Material.RED_NETHER_BRICKS);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.NETHER_WART_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(45) + 25));
				break;
			}
			case NETHERITE_BLOCK: {
				b.setType(Material.AIR);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.NETHERITE_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(60) + 40));
				break;
			}
			case RAW_IRON_BLOCK: {
				b.setType(Material.SMOOTH_SANDSTONE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.RAW_IRON_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 35));
				break;
			}
			default: {
				Material original = b.getType();
				b.setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 20));
				break;
			}
		}
	}
	
	private void spawnMob(Block b) {
		Material type = b.getType();
		WorldServer ws = ((CraftWorld) b.getWorld()).getHandle();
		switch (type) {
			case COARSE_DIRT:
			case DIRT: {
				if (r.nextInt(100) < 2) {
					WildSpeedTitan t = new WildSpeedTitan(b.getLocation());
					ws.addEntity(t);
				}
				return;
			}
			case GOLD_BLOCK: {
				if (r.nextInt(100) < 1) {
					WildGoldenTitan t = new WildGoldenTitan(b.getLocation());
					ws.addEntity(t);
				}
				return;
			}
			case RAW_GOLD_BLOCK: {
				if (r.nextInt(1000) < 5) {
					WildGoldenTitan t = new WildGoldenTitan(b.getLocation());
					ws.addEntity(t);
				}
				return;
			}
			case RED_NETHER_BRICKS:
			case NETHER_BRICKS: {
				if (r.nextInt(100) < 3) {
					WildNetherTitan t = new WildNetherTitan(b.getLocation());
					ws.addEntity(t);
				}
				return;
			}
			case NETHERITE_BLOCK: {
				if (r.nextInt(100) < 1) {
					WildDamageTitan t = new WildDamageTitan(b.getLocation());
					ws.addEntity(t);
				}
				return;
			}
			default: {
				return;
			}
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_planatae_titan"))) return;
		Material type = e.getBlock().getType();
		
		// Insta Break
		switch (type) {
			case RAW_IRON_BLOCK:
			case NETHERRACK:
			case CRIMSON_NYLIUM:
			case DEEPSLATE_EMERALD_ORE:
			case DIRT:
			case LIME_STAINED_GLASS: {
				e.setInstaBreak(true);
				break;
			}
			default: {
				break;
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Material type = b.getType();
		
		if (!(p.getWorld().getName().equalsIgnoreCase("world_planatae_titan"))) return;
		if (p.getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
		e.setDropItems(false);
		e.setExpToDrop(0);
		getDrops().forEach((material, drop) -> {
			if (material != type) return;
			
			if (getExp().containsKey(type)) {
				if (r.nextInt(100) < getExpChance().get(type)) {
					ExperienceOrb orb = (ExperienceOrb) p.getWorld().spawnEntity(b.getLocation(), EntityType.EXPERIENCE_ORB);
					orb.setExperience(getExp().get(type));
					p.playSound(b.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
				}
			}
			
			if (getDropChance().containsKey(type)) {
				if (r.nextInt(100) < getDropChance().get(type)) {
					b.getWorld().dropItemNaturally(b.getLocation(), drop);
				}
			} else {
				b.getWorld().dropItemNaturally(b.getLocation(), drop);
			}
			spawnMob(b);
			replenish(b);
		});
	}

}
