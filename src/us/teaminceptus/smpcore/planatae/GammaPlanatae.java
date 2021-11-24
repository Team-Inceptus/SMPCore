package us.teaminceptus.smpcore.planatae;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class GammaPlanatae implements Listener { 

	protected Main plugin;
	
	public GammaPlanatae(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static Map<Material, Integer> getExpChance() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.AMETHYST_CLUSTER, 50);
		exp.put(Material.OBSIDIAN, 75);
		exp.put(Material.BLUE_ICE, 80);
		exp.put(Material.DEEPSLATE_COPPER_ORE, 100);
		exp.put(Material.SNOW_BLOCK, 10);
		exp.put(Material.BLUE_ICE, 15);
		exp.put(Material.STONE, 10);
		
		return exp;
	}	
	
	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.AMETHYST_CLUSTER, 5);
		exp.put(Material.OBSIDIAN, 10);
		exp.put(Material.BLUE_ICE, 4);
		exp.put(Material.DEEPSLATE_COPPER_ORE, 20);
		exp.put(Material.SNOW_BLOCK, 5);
		exp.put(Material.BLUE_ICE, 2);
		exp.put(Material.STONE, 3);
		
		return exp;
	}
	
	static Random r = new Random();
	
	private void replenish(Block b) {
		switch (b.getType()) {
			case AMETHYST_CLUSTER: {
				b.setType(Material.AIR);
				break;
			}
			case OBSIDIAN: {
				b.setType((r.nextInt(100) < 50 ? Material.COBBLED_DEEPSLATE : Material.DEEPSLATE));
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.OBSIDIAN);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(25) + 5));
				break;
			}
			case GRANITE: {
				b.setType(Material.STONE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.GRANITE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(25) + 5));
				break;
			}
			case BLUE_ICE: {
				b.setType(Material.PACKED_ICE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.BLUE_ICE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(35) + 15));
				break;
			}
			case SNOW_BLOCK: {
				b.setType(Material.PACKED_ICE);
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.SNOW_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(15) + 5));
				break;
			}
			case DEEPSLATE_COPPER_ORE: {
				b.setType((r.nextInt(100) < 50 ? Material.COBBLED_DEEPSLATE : Material.DEEPSLATE));
				new BukkitRunnable() {
					public void run() {
						b.setType(Material.DEEPSLATE_COPPER_ORE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(30) + 10));
				break;
			}
			default: {
				Material originalType = b.getType();
				b.setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.setType(originalType);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(10) + 5));
				break;
			}
		}
	}
	
	private void spawnEntity(Player p, Block b) {
		Material type = b.getType();
		switch (type) {
			case DEEPSLATE_COPPER_ORE: {
				if (r.nextInt(100) < 10) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon husk ~ ~ ~ {Silent:0b,Invulnerable:0b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:100f,IsBaby:0b,CustomName:'{\"text\":\"Gamma Husk\",\"color\":\"gold\",\"italic\":false}',ArmorItems:[{},{},{},{id:\"minecraft:golden_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Gamma Helmet\",\"color\":\"gold\",\"italic\":false}'},Unbreakable:0b,Enchantments:[{id:\"minecraft:protection\",lvl:8s},{id:\"minecraft:unbreaking\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:5,Operation:0,UUID:[I;1348885184,826360567,-2146803890,-652790889],Slot:\"head\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:3,Operation:0,UUID:[I;-560426148,-905622945,-1584466156,1979476755],Slot:\"head\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.3,Operation:2,UUID:[I;1190688945,164908129,-1356458920,-632697790],Slot:\"head\"}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.100F],Attributes:[{Name:generic.max_health,Base:100},{Name:generic.follow_range,Base:50},{Name:generic.knockback_resistance,Base:0.5},{Name:generic.attack_damage,Base:8},{Name:generic.armor,Base:10},{Name:generic.armor_toughness,Base:7},{Name:generic.attack_knockback,Base:1},{Name:zombie.spawn_reinforcements,Base:0}]}");
				}
				return;
			}
			case AMETHYST_BLOCK: {
				if (r.nextInt(100) < 4) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:200f,CustomName:'{\\\"text\\\":\\\"Amethyst Zombie\\\",\\\"color\\\":\\\"light_purple\\\",\\\"italic\\\":false}',HandItems:[{id:\\\"minecraft:amethyst_shard\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Super Amethyst Shard\\\",\\\"color\\\":\\\"dark_purple\\\",\\\"italic\\\":false}'},HideFlags:1,Enchantments:[{id:\\\"minecraft:sharpness\\\",lvl:15s},{id:\\\"minecraft:smite\\\",lvl:10s},{id:\\\"minecraft:bane_of_arthropods\\\",lvl:10s},{id:\\\"minecraft:looting\\\",lvl:10s},{id:\\\"minecraft:sweeping\\\",lvl:25s}],AttributeModifiers:[{AttributeName:\\\"generic.attack_damage\\\",Name:\\\"generic.attack_damage\\\",Amount:5,Operation:0,UUID:[I;-909321703,1930970078,-2108694589,370468640],Slot:\\\"mainhand\\\"},{AttributeName:\\\"generic.attack_damage\\\",Name:\\\"generic.attack_damage\\\",Amount:5,Operation:0,UUID:[I;114330334,-1037156303,-1388268589,-1427227550],Slot:\\\"offhand\\\"}]}},{}],HandDropChances:[0.063F,0.085F],ArmorItems:[{id:\\\"minecraft:leather_boots\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Amethyst Boots\\\",\\\"color\\\":\\\"light_purple\\\",\\\"italic\\\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\\\"minecraft:protection\\\",lvl:15s},{id:\\\"minecraft:fire_protection\\\",lvl:7s},{id:\\\"minecraft:blast_protection\\\",lvl:7s},{id:\\\"minecraft:projectile_protection\\\",lvl:7s},{id:\\\"minecraft:thorns\\\",lvl:2s},{id:\\\"minecraft:unbreaking\\\",lvl:25s}],AttributeModifiers:[{AttributeName:\\\"generic.armor\\\",Name:\\\"generic.armor\\\",Amount:4,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\\\"feet\\\"}]}},{id:\\\"minecraft:leather_leggings\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Amethyst Leggings\\\",\\\"color\\\":\\\"light_purple\\\",\\\"italic\\\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\\\"minecraft:protection\\\",lvl:15s},{id:\\\"minecraft:fire_protection\\\",lvl:7s},{id:\\\"minecraft:blast_protection\\\",lvl:7s},{id:\\\"minecraft:projectile_protection\\\",lvl:7s},{id:\\\"minecraft:thorns\\\",lvl:2s},{id:\\\"minecraft:unbreaking\\\",lvl:25s}],AttributeModifiers:[{AttributeName:\\\"generic.armor\\\",Name:\\\"generic.armor\\\",Amount:7,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\\\"legs\\\"}]}},{id:\\\"minecraft:leather_chestplate\\\",Count:1b,tag:{display:{Name:'{\\\"text\\\":\\\"Amethyst Chestplate\\\",\\\"color\\\":\\\"light_purple\\\",\\\"italic\\\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\\\"minecraft:protection\\\",lvl:15s},{id:\\\"minecraft:fire_protection\\\",lvl:7s},{id:\\\"minecraft:blast_protection\\\",lvl:7s},{id:\\\"minecraft:projectile_protection\\\",lvl:7s},{id:\\\"minecraft:thorns\\\",lvl:2s},{id:\\\"minecraft:unbreaking\\\",lvl:25s}],AttributeModifiers:[{AttributeName:\\\"generic.armor\\\",Name:\\\"generic.armor\\\",Amount:8,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\\\"chest\\\"}]}},{id:\\\"minecraft:amethyst_block\\\",Count:16b}],ArmorDropChances:[0.125F,125.000F,0.125F,1.000F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.movement_speed,Base:0.2},{Name:generic.attack_damage,Base:10},{Name:generic.armor,Base:15},{Name:generic.armor_toughness,Base:10},{Name:generic.attack_knockback,Base:4},{Name:zombie.spawn_reinforcements,Base:1}]}");
				}
			}
			default: {
				return;
			}
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_planatae_gamma"))) return;
		Material type = e.getBlock().getType();
		if (!(getDrops().containsKey(type))) e.setCancelled(true);
		// Insta Break
		switch (type) {
			case DEEPSLATE_COPPER_ORE:
			case SMALL_AMETHYST_BUD:
			case MEDIUM_AMETHYST_BUD:
			case LARGE_AMETHYST_BUD:
			case AMETHYST_CLUSTER:
			case SNOW_BLOCK: {
				e.setInstaBreak(true);
				break;
			}
			default: {
				break;
			}
		}
	}
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.AMETHYST_BLOCK, PlanataeFetcher.getGammaAmethyst());
		drops.put(Material.AMETHYST_CLUSTER, PlanataeFetcher.getGammaAmethyst());
		drops.put(Material.DEEPSLATE_COPPER_ORE, PlanataeFetcher.getRawChalc());
		drops.put(Material.OBSIDIAN, PlanataeFetcher.getEpsilonSheet());
		drops.put(Material.COBBLED_DEEPSLATE, PlanataeFetcher.getGammaStone());
		drops.put(Material.DEEPSLATE, PlanataeFetcher.getGammaStone());
		
		ItemStack powder = PlanataeFetcher.getGammaPowder().clone();
		powder.setAmount(r.nextInt(4) + 1);
		
		drops.put(Material.STONE, powder);
		
		drops.put(Material.GRANITE, PlanataeFetcher.getEpsilonStone());
		drops.put(Material.PACKED_ICE, PlanataeFetcher.getGammaIce());
		drops.put(Material.BLUE_ICE, PlanataeFetcher.getEpsilonIce());
		
		ItemStack powder2 = PlanataeFetcher.getZetaPowder().clone();
		powder.setAmount(r.nextInt(3) + 1);
		
		drops.put(Material.SNOW_BLOCK, powder2);
		
		return drops;
	}
	
	@EventHandler
	public void onBreak(EntityChangeBlockEvent e) {
		Block b = e.getBlock();
		if (!(b.getWorld().getName().contains("world_planatae"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(EntityExplodeEvent e) {
		Entity en = e.getEntity();
		if (!(en.getWorld().getName().contains("world_planatae"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBurn(FurnaceBurnEvent e) {
		Furnace block = (Furnace) e.getBlock();
		FurnaceInventory inv = block.getInventory();
		
		if (e.getFuel().isSimilar(PlanataeFetcher.getIotaLava())) {
			new BukkitRunnable() {
				public void run() {
					inv.setFuel(PlanataeFetcher.getIotaLava());
				}
			}.runTask(plugin);
		}
	}
	
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent e) {
		ItemStack source = e.getSource();
		
		if (source.isSimilar(PlanataeFetcher.getRawChalc())) e.setResult(PlanataeFetcher.getChalc());
		else if (source.isSimilar(PlanataeFetcher.getRawAurum())) e.setResult(PlanataeFetcher.getAurum());
		else if (source.isSimilar(PlanataeFetcher.getRawTermentium())) e.setResult(PlanataeFetcher.getTermentium());
		else if (source.isSimilar(PlanataeFetcher.getRawNivi())) e.setResult(PlanataeFetcher.getNivi());
	}
	
	@EventHandler
	public void onBucketFill(PlayerBucketFillEvent e) {
		Player p = e.getPlayer();
		if (!(p.getWorld().getName().contains("world_planatae"))) return;
		if (e.getBlockClicked().getType() == Material.LAVA) {
			e.setItemStack(PlanataeFetcher.getIotaLava());
			new BukkitRunnable() {
				public void run() {
					e.getBlockClicked().setType(Material.LAVA);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(180) + 120));
		}
		else if (e.getBlockClicked().getType() == Material.WATER) e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("core.admin.gamemodebypass")) return;
		for (ItemStack i : PlanataeFetcher.getItems()) {
			if (e.getItemInHand().isSimilar(i)) {
				e.setCancelled(true);
				break;
			}
		}
		if (!(p.getWorld().getName().contains("world_planatae"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Material type = b.getType();
		
		if (!(p.getWorld().getName().equalsIgnoreCase("world_planatae_gamma"))) return;
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
			
			b.getWorld().dropItemNaturally(b.getLocation(), drop);
			spawnEntity(p, b);
			replenish(b);
		});
	}
}
