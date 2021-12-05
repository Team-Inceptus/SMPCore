package us.teaminceptus.smpcore.planatae;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
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
import us.teaminceptus.smpcore.entities.Witherman;
import us.teaminceptus.smpcore.planatae.entities.ChalcGolem;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class OmegaPlanatae implements Listener {

	protected Main plugin;
	
	public OmegaPlanatae(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.DIAMOND_BLOCK, 25);
		exp.put(Material.EMERALD_BLOCK, 30);
		exp.put(Material.CALCITE, 25);
		exp.put(Material.NETHER_QUARTZ_ORE, 20);
		exp.put(Material.DEEPSLATE_GOLD_ORE, 40);
		exp.put(Material.IRON_ORE, 30);
		exp.put(Material.STONE, 5);
		exp.put(Material.PRISMARINE, 5);
		
		return exp;
	}
	
	public static Map<Material, Integer> getExpChance() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.DIAMOND_BLOCK, 100);
		exp.put(Material.EMERALD_BLOCK, 80);
		exp.put(Material.CALCITE, 100);
		exp.put(Material.NETHER_QUARTZ_ORE, 50);
		exp.put(Material.DEEPSLATE_GOLD_ORE, 60);
		exp.put(Material.IRON_ORE, 75);
		exp.put(Material.STONE, 15);
		exp.put(Material.PRISMARINE, 25);
		
		return exp;
	}
	
	public static Map<Material, Integer> getDropChance() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.DIAMOND_BLOCK, 25);
		dropChance.put(Material.NETHER_QUARTZ_ORE, 75);
		dropChance.put(Material.CALCITE, 5);
		dropChance.put(Material.DIORITE, 40);
		dropChance.put(Material.PURPUR_BLOCK, 1);
		dropChance.put(Material.EMERALD_BLOCK, 10);
		dropChance.put(Material.IRON_ORE, 50);
		dropChance.put(Material.DEEPSLATE_GOLD_ORE, 5);
		dropChance.put(Material.CRYING_OBSIDIAN, 60);
		
		return dropChance;
	}
	
	static Random r = new Random();
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.DIAMOND_BLOCK, PlanataeFetcher.getOmegaDiamond());
		drops.put(Material.NETHER_QUARTZ_ORE, PlanataeFetcher.getSigmaQuartz());
		drops.put(Material.SHROOMLIGHT, PlanataeFetcher.getOmegaPlasma());
		drops.put(Material.RED_NETHER_BRICKS, PlanataeFetcher.getSigmaBrick());
		drops.put(Material.DIORITE, PlanataeFetcher.getRawNivi());
		drops.put(Material.CALCITE, PlanataeFetcher.getOmegaStarShard());
		drops.put(Material.PURPUR_BLOCK, PlanataeFetcher.getOmegaEssence());
		drops.put(Material.END_STONE, PlanataeFetcher.getOmegaStone());
		drops.put(Material.EMERALD_BLOCK, PlanataeFetcher.getSigmaEssence());
		drops.put(Material.STONE, PlanataeFetcher.getSigmaStone());
		drops.put(Material.IRON_ORE, PlanataeFetcher.getRawNivi());
		drops.put(Material.DEEPSLATE_GOLD_ORE, PlanataeFetcher.getOmegaStar());
		drops.put(Material.DEEPSLATE, PlanataeFetcher.getSigmaStone());
		drops.put(Material.GRANITE, PlanataeFetcher.getGehe());
		drops.put(Material.OBSIDIAN, PlanataeFetcher.getEpsilonScrap());
		drops.put(Material.MAGMA_BLOCK, PlanataeFetcher.getOmegaCharge());
		drops.put(Material.CRYING_OBSIDIAN, PlanataeFetcher.getOmegaStarShard());
		drops.put(Material.NETHERRACK, PlanataeFetcher.getOmegaBrick());
		
		ItemStack oneChalc = PlanataeFetcher.getChalc();
		ItemStack twoChalc = PlanataeFetcher.getChalc();
		twoChalc.setAmount(2);
		
		ItemStack fourChalc = PlanataeFetcher.getChalc();
		fourChalc.setAmount(4);
		
		ItemStack sevenChalc = PlanataeFetcher.getChalc();
		sevenChalc.setAmount(7);
		
		drops.put(Material.COPPER_BLOCK, oneChalc);
		drops.put(Material.EXPOSED_COPPER, twoChalc);
		drops.put(Material.WEATHERED_COPPER, fourChalc);
		drops.put(Material.OXIDIZED_COPPER, sevenChalc);
		
		ItemStack shard = PlanataeFetcher.getLympiaShard();
		shard.setAmount(r.nextInt(5) + 2);
		
		drops.put(Material.PRISMARINE, shard);
		ItemStack crystal = PlanataeFetcher.getLympiaCrystal();
		shard.setAmount(r.nextInt(3) + 1);
		
		drops.put(Material.PRISMARINE_BRICKS, crystal);
		drops.put(Material.PACKED_ICE, PlanataeFetcher.getGammaIce());
		drops.put(Material.BLUE_ICE, PlanataeFetcher.getEpsilonIce());
		drops.put(Material.DARK_PRISMARINE, PlanataeFetcher.getLambdaStone());
		return drops;
	}
	
	private void spawnMob(Player p, Block b) {
		Material type = b.getType();
		WorldServer ws = ((CraftWorld) b.getWorld()).getHandle();
		switch (type) {
			case NETHER_QUARTZ_ORE: {
				if (r.nextInt(100) < 5) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon piglin_brute ~ ~ ~ {PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:150f,IsImmuneToZombification:1b,CustomName:'{\\\"text\\\":\\\"Warglin\\\",\\\"color\\\":\\\"dark_red\\\",\\\"italic\\\":false}',HandItems:[{id:\\\"minecraft:diamond_sword\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Warglin\\\\'s Weapon\\\",\\\"color\\\":\\\"blue\\\",\\\"italic\\\":false}'},Enchantments:[{id:\\\"minecraft:sharpness\\\",lvl:6s},{id:\\\"minecraft:smite\\\",lvl:7s},{id:\\\"minecraft:bane_of_arthropods\\\",lvl:8s},{id:\\\"minecraft:knockback\\\",lvl:2s},{id:\\\"minecraft:fire_aspect\\\",lvl:3s},{id:\\\"minecraft:unbreaking\\\",lvl:4s}]}},{}],ArmorItems:[{},{},{},{id:\\\"minecraft:diamond_helmet\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Warglin Helmet\\\",\\\"color\\\":\\\"dark_blue\\\",\\\"italic\\\":false}'},Enchantments:[{id:\\\"minecraft:protection\\\",lvl:6s},{id:\\\"minecraft:fire_protection\\\",lvl:6s},{id:\\\"minecraft:blast_protection\\\",lvl:7s},{id:\\\"minecraft:respiration\\\",lvl:4s}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,10.000F],ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:150},{Name:generic.follow_range,Base:50},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:2}]}\"");
				}
				return;
			}
			case CALCITE: {
				if (r.nextInt(100) < 4) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon stray ~ ~ ~ {Health:1000f,CustomName:'{\"text\":\"Star Stray\",\"color\":\"blue\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Sigma Bow\",\"color\":\"blue\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:power\",lvl:75s},{id:\"minecraft:punch\",lvl:20s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:1.5,Operation:2,UUID:[I;877011035,-1946596015,-2021352948,1773675588],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:1.5,Operation:2,UUID:[I;1828503636,898714366,-1658237611,-1792248317],Slot:\"offhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.25,Operation:2,UUID:[I;-780850967,1699561780,-1165856331,1333999081],Slot:\"offhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.25,Operation:2,UUID:[I;1214105808,1636320170,-1088120685,661744986],Slot:\"mainhand\"}]}},{}],HandDropChances:[0.001F,0.085F],ArmorItems:[{},{},{id:\"minecraft:diamond_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Omega Chestplate\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:30s},{id:\"minecraft:fire_protection\",lvl:250s},{id:\"minecraft:projectile_protection\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:1.5,Operation:2,UUID:[I;1953029027,1199852844,-1463750490,-135029731],Slot:\"chest\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:0.75,Operation:2,UUID:[I;-1060648588,-1381415210,-1618494470,-603423965],Slot:\"chest\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:0.45,Operation:2,UUID:[I;275046773,-1997123776,-1689169019,1904284343],Slot:\"chest\"}]}},{id:\"minecraft:nether_star\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:25s}]}}],ArmorDropChances:[0.085F,0.085F,0.050F,0.000F],Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:0.8},{Name:generic.attack_damage,Base:15},{Name:generic.armor,Base:30},{Name:generic.armor_toughness,Base:30},{Name:generic.attack_knockback,Base:2}]}");
				}
				return;
			}
			case END_STONE: {
				if (r.nextInt(100) < 1) {
					Witherman w = new Witherman(b.getLocation().add(0, 1, 0));
					ws.e(w);
				}
				return;
			}
			case EMERALD_BLOCK: {
				if (r.nextInt(100) < 6) {
					Witherman w = new Witherman(b.getLocation().add(0, 1, 0));
					ws.e(w);
				}
				return;
			}
			case COPPER_BLOCK:
			case EXPOSED_COPPER:
			case WEATHERED_COPPER:
			case OXIDIZED_COPPER: {
				if (r.nextInt(100) < 4) {
					ChalcGolem c = new ChalcGolem(p, b.getLocation().add(0, 1, 0));
					ws.e(c);
				}
				return;
			}
			default: {
				return;
			}
		}
	}
	
	private void replenish(Block b) {
		switch (b.getType()) {
			case SHROOMLIGHT: {
				b.setType(Material.RED_NETHER_BRICKS);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.SHROOMLIGHT);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(30) + 12));
				break;
			}
			case DIAMOND_BLOCK:
			case NETHER_QUARTZ_ORE: {
				Material original = b.getType();
				b.setType(Material.NETHER_QUARTZ_ORE);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 15));
				break;
			}
			case CALCITE: {
				b.setType(Material.DIORITE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.CALCITE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(25) + 15));
				break;
			}
			case PURPUR_BLOCK: {
				b.setType(Material.DIORITE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.PURPUR_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(40) + 20));
				break;
			}
			case EMERALD_BLOCK: {
				b.setType(Material.END_STONE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.PURPUR_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 15));
				break;
			}
			case IRON_ORE: {
				b.setType(Material.STONE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.IRON_ORE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(25) + 10));
				break;
			}
			case DEEPSLATE_GOLD_ORE: {
				b.setType(Material.DEEPSLATE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.DEEPSLATE_GOLD_ORE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(60) + 30));
				break;
			}
			case CRYING_OBSIDIAN: {
				b.setType(Material.OBSIDIAN);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.CRYING_OBSIDIAN);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(15) + 20));
				break;
			}
			case MAGMA_BLOCK: {
				b.setType(Material.NETHERRACK);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.MAGMA_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(15) + 15));
			}
			case EXPOSED_COPPER:
			case WEATHERED_COPPER:
			case OXIDIZED_COPPER: {
				b.setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.COPPER_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(26) + 11));
				break;
			}
			case BLUE_ICE:
			case PACKED_ICE: {
				Material original = b.getType();
				b.setType(Material.PRISMARINE);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(60) + 35));
				break;
			}
			case PRISMARINE_BRICKS: {
				b.setType(Material.PRISMARINE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.PRISMARINE_BRICKS);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(40) + 20));
				break;
			}
			default: {
				Material original = b.getType();
				b.setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.setType(original);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(25) + 10));
				break;
			}
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_planatae_omega"))) return;
		Material type = e.getBlock().getType();
		
		// Insta Break
		switch (type) {
			case WEATHERED_COPPER:
			case OXIDIZED_COPPER:
			case BLUE_ICE:
			case SHROOMLIGHT:
			case EMERALD_BLOCK:
			case CALCITE: {
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
		if (!(p.getWorld().getName().equalsIgnoreCase("world_planatae_omega"))) return;
		if (p.getGameMode() == GameMode.CREATIVE) return;
		Block b = e.getBlock();
		Material type = b.getType();
		
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
			spawnMob(p, b);
			replenish(b);
		});
	}
}
