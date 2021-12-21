package us.teaminceptus.smpcore.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.fetcher.ItemFetcher;

public class CustomDrops implements Listener {
	
	protected SMPCore plugin;
	
	public CustomDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static EntityType[] undeadMobs = {
		EntityType.DROWNED,
		EntityType.SKELETON,
		EntityType.WITHER,
		EntityType.ZOMBIE,
		EntityType.ZOMBIFIED_PIGLIN,
		EntityType.HUSK,
		EntityType.SKELETON_HORSE,
		EntityType.WITHER_SKELETON,
		EntityType.PHANTOM,
		EntityType.STRAY,
		EntityType.ZOGLIN,
		EntityType.ZOMBIE_VILLAGER
	};
	
	public static EntityType[] netherMobs = {
		EntityType.BLAZE,
		EntityType.MAGMA_CUBE,
		EntityType.SKELETON,
		EntityType.ZOGLIN,
		EntityType.GHAST,
		EntityType.PIGLIN,
		EntityType.STRIDER,
		EntityType.ZOMBIFIED_PIGLIN,
		EntityType.HOGLIN,
		EntityType.PIGLIN_BRUTE,
		EntityType.WITHER_SKELETON
	};
	
	public static EntityType[] endMobs = {
		EntityType.ENDER_DRAGON,
		EntityType.ENDERMAN,
		EntityType.SHULKER
	};
	
	public static EntityType[] aquaticMobs = {
		EntityType.COD,
		EntityType.SALMON,
		EntityType.DROWNED,
		EntityType.PUFFERFISH,
		EntityType.AXOLOTL
	};
	
	static Random r = new Random();	
	
	private static List<ItemStack> getCores(EntityType type) {
		List<ItemStack> cores = new ArrayList<>();
		
		if (Arrays.asList(undeadMobs).contains(type)) {
			cores.add(ItemFetcher.getUndeadCore());
		}
		
		if (Arrays.asList(netherMobs).contains(type)) {
			cores.add(ItemFetcher.getNetherCore());
		}
		
		if (Arrays.asList(endMobs).contains(type)) {
			cores.add(ItemFetcher.getEndCore());
		}
		
		if (Arrays.asList(aquaticMobs).contains(type)) {
			cores.add(ItemFetcher.getAquaticCore());
		}
		
		return cores;
	}
	
	@EventHandler
	public void onDeathCores(EntityDeathEvent e) {
		if (e.getEntity() == null) return;
		LivingEntity en = e.getEntity();
		if (!(en.getWorld().getName().equalsIgnoreCase("world")) && !(en.getWorld().getName().equalsIgnoreCase("world_nether")) && !(en.getWorld().getName().equalsIgnoreCase("world_the_end")));
		if (en.getKiller() == null) return;
		Player p = en.getKiller();
		List<ItemStack> cores = getCores(e.getEntityType());
		if (cores.size() < 1) return;
		
		for (ItemStack item : cores) {
			int amount = (r.nextInt(100) < 50 ? 1 : 0);
			double lootingLevel = (p.getEquipment().getItemInMainHand() == null ? 0 : p.getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));
			
			for (int i = 0; i < lootingLevel; i++) {
				if (r.nextInt(100) < (100 * (lootingLevel / lootingLevel + 1))) {
					amount++;
					continue;
				} else break;
			}
			
			item.setAmount(amount);
			en.getWorld().dropItemNaturally(en.getLocation(), item);
		}
	}
	
	@EventHandler
	public void onDeathOther(EntityDeathEvent e) {
		LivingEntity en = e.getEntity();
		if (!(en.getWorld().getName().equalsIgnoreCase("world")) && !(en.getWorld().getName().equalsIgnoreCase("world_nether")) && !(en.getWorld().getName().equalsIgnoreCase("world_the_end")));
		if (en.getKiller() == null) return;
		Player p = en.getKiller();
		int lootingBuff = 0;
		double lootingLevel = (p.getEquipment().getItemInMainHand() == null ? 0 : p.getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));
		
		for (int i = 0; i < lootingLevel; i++) {
			if (r.nextInt(100) < (100 * (lootingLevel / lootingLevel + 1))) {
				lootingBuff++;
				continue;
			} else break;
		}
		
		if (e.getEntityType() == EntityType.ENDER_DRAGON) {
			en.getWorld().dropItemNaturally(en.getLocation(), ItemFetcher.getVoidCrack());
			
			ItemStack wings = ItemFetcher.getWingRemnant();
			wings.setAmount(r.nextInt(4) + 1);
			en.getWorld().dropItemNaturally(en.getLocation(), wings);
		} else if (e.getEntityType() == EntityType.ENDERMAN) {
			ItemStack enchE = ItemFetcher.getEnchantedEnderPearl();
			enchE.setAmount((r.nextInt(3) + 1) + lootingBuff);
			en.getWorld().dropItemNaturally(en.getLocation(), enchE);
		} else if (e.getEntityType() == EntityType.WITHER) {
			ItemStack witherMats = ItemFetcher.getWitherMaterial();
			witherMats.setAmount((r.nextInt(3) + 2) + lootingBuff);
			en.getWorld().dropItemNaturally(en.getLocation(), witherMats);
		} else if (Arrays.asList(aquaticMobs).contains(e.getEntityType())) {
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), ItemFetcher.getT1Heart());
			}
			
			if (r.nextInt(100) < 3) {
				en.getWorld().dropItemNaturally(en.getLocation(), ItemFetcher.getT2Heart());
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Material mat = b.getType();
		if (!(b.getWorld().getName().equalsIgnoreCase("world")) && !(b.getWorld().getName().equalsIgnoreCase("world_nether")) && !(b.getWorld().getName().equalsIgnoreCase("world_the_end")));
		
		int fortuneBuff = 0;
		double fortuneLevel = (p.getEquipment().getItemInMainHand() == null ? 0 : p.getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
		
		for (int i = 0; i < fortuneLevel; i++) {
			if (r.nextInt(100) < (100 * (fortuneLevel / fortuneLevel + 1))) {
				fortuneBuff++;
				continue;
			} else break;
		}
		
		if (mat == Material.GOLD_ORE || mat == Material.DEEPSLATE_GOLD_ORE || mat == Material.NETHER_GOLD_ORE) {
			if (r.nextInt(100) < 10 + (100 * (fortuneLevel / fortuneLevel + 1))) {
				ItemStack ingots = ItemFetcher.getEnchantedGoldIngot();
				ingots.setAmount(fortuneBuff + (r.nextInt(2) + 1));
				b.getWorld().dropItemNaturally(b.getLocation(), ingots);
			}
		} else if (mat == Material.REDSTONE_ORE || mat == Material.DEEPSLATE_REDSTONE_ORE) {
			if (r.nextInt(100) < 10 + (100 * (fortuneLevel / fortuneLevel + 1))) {
				ItemStack cores = ItemFetcher.getEnergyCore()
;				cores.setAmount(fortuneBuff + 1);
				b.getWorld().dropItemNaturally(b.getLocation(), cores);
			}
		}
	}

}
