package us.teaminceptus.smpcore.listeners.caves;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.level.WorldServer;
import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.entities.caves.DeltaSkeleton;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class DeltaCave implements Listener {
	
	protected Main plugin;
	protected Map<Material, ItemStack> drops;
	protected Map<Material, Integer> dropChance;
	protected Map<Material, Integer> exp;
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.NETHERRACK, getDeltarack());
		drops.put(Material.STONE, getDeltaStone());
		drops.put(Material.BLACKSTONE, getBlackDeltaStone());
		drops.put(Material.NETHER_GOLD_ORE, getDeltaGold());
		drops.put(Material.NETHER_QUARTZ_ORE, getDeltaQuartz());
		drops.put(Material.SOUL_SAND, getDeltaSand());
		
		return drops;
	}
	
	public static Map<Material, Integer> getDropChance() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.NETHERRACK, 100);
		dropChance.put(Material.STONE, 100);
		dropChance.put(Material.BLACKSTONE, 75);
		dropChance.put(Material.NETHER_GOLD_ORE, 25);
		dropChance.put(Material.NETHER_QUARTZ_ORE, 10);
		dropChance.put(Material.SOUL_SAND, 100);
		
		return dropChance;
	}
	
	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.NETHERRACK, 0);
		dropChance.put(Material.STONE, 0);
		dropChance.put(Material.BLACKSTONE, 5);
		dropChance.put(Material.NETHER_GOLD_ORE, 15);
		dropChance.put(Material.NETHER_QUARTZ_ORE, 35);
		dropChance.put(Material.SOUL_SAND, 0);
		
		return dropChance;
	}
	
	static Random r = new Random();
	
	public DeltaCave(Main plugin) {
		this.plugin = plugin;
		this.drops = getDrops();
		this.exp = getExp();
		this.dropChance = getDropChance();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_delta"))) return;
		
		Block b = e.getBlock();
		Player p = e.getPlayer();
		WorldServer w = ((CraftWorld) b.getWorld()).getHandle();
		
		if (b.getType() == Material.STONE) {
			if (r.nextInt(100) < 10) {			
				DeltaSkeleton s = new DeltaSkeleton(p.getLocation());
				w.e(s); // Add Entity
			}
		}
	}
	
	public static ItemStack getDeltaStone() {
		ItemStack dStone = new ItemStack(Material.STONE, 1);
		ItemMeta dMeta = dStone.getItemMeta();
		dMeta.setDisplayName(ChatColor.DARK_GRAY + "Delta Stone");
		dStone.setItemMeta(dMeta);
		
		return dStone;
	}
	
	public static ItemStack getDeltarack() {
		ItemStack drack = new ItemStack(Material.NETHERRACK, 1);
		ItemMeta dMeta = drack.getItemMeta();
		dMeta.setDisplayName(ChatColor.RED + "Deltarack");
		dMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		drack.setItemMeta(dMeta);
		
		return drack;
	}
	
	public static ItemStack getDeltaSand() {
		ItemStack dSand = new ItemStack(Material.SOUL_SAND, 1);
		ItemMeta dMeta = dSand.getItemMeta();
		dMeta.setDisplayName(GeneralUtils.hexToChat("665500", "Delta Sand"));
		dMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		dSand.setItemMeta(dMeta);
		
		return dSand;
	}
	
	public static ItemStack getBlackDeltaStone() {
		ItemStack bStone = new ItemStack(Material.BLACKSTONE, 1);
		ItemMeta bMeta = bStone.getItemMeta();
		bMeta.setDisplayName(ChatColor.DARK_GRAY + "Black Delta Stone");
		
		bStone.setItemMeta(bMeta);
		
		return bStone;
	}
	
	public static ItemStack getDeltaQuartz() {
		ItemStack dQuartz = new ItemStack(Material.QUARTZ, 1);
		ItemMeta dMeta = dQuartz.getItemMeta();
		dMeta.setDisplayName(ChatColor.WHITE + "Delta Quartz");
		dMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		dQuartz.setItemMeta(dMeta);
		
		return dQuartz;
	}
	
	public static ItemStack getDeltaGold() {
		ItemStack dGold = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta dMeta = dGold.getItemMeta();
		dMeta.setDisplayName(ChatColor.GOLD + "Delta Gold");
		
		dGold.setItemMeta(dMeta);
		
		return dGold;
	}
	
	public static List<ItemStack> getItems() {
		ItemStack[] items = {
			getDeltaStone(),
			getDeltarack(),
			getDeltaSand(),
			getBlackDeltaStone(),
			getDeltaQuartz(),
			getDeltaGold(),
			
			getDeltaBow(),
			getDeltaSet().get(EquipmentSlot.HEAD),
			getDeltaSet().get(EquipmentSlot.CHEST),
			getDeltaSet().get(EquipmentSlot.LEGS),
			getDeltaSet().get(EquipmentSlot.FEET),
		};
		
		return Arrays.asList(items);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_delta"))) return;
		
		for (ItemStack i : getItems()) {
			if (i.getType() == e.getBlock().getType()) e.setDropItems(false);
		}
		e.setExpToDrop(0);
		
		if (drops.containsKey(e.getBlock().getType())) {
			ItemStack drop = drops.get(e.getBlock().getType());
			
			int chance = this.dropChance.get(e.getBlock().getType());
			
			if (r.nextInt(100) < chance) {
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
				
				if (r.nextBoolean()) 
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
				
				if (this.exp != null) {
					e.setExpToDrop(this.exp.get(e.getBlock().getType()));
				}
			}
		}
	}
	
	public static ItemStack getDeltaBow() {
		ItemStack deltaBow = new ItemStack(Material.BOW);
		ItemMeta dBow = deltaBow.getItemMeta();
		dBow.setDisplayName(ChatColor.AQUA + "Delta Bow");
		dBow.addEnchant(Enchantment.ARROW_DAMAGE, 8, true);
		dBow.addEnchant(Enchantment.DURABILITY, 6, true);
		dBow.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		dBow.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
		if (r.nextInt(100) < 15) {
			dBow.addEnchant(Enchantment.MENDING, 1, true);
		}
		
		dBow.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		dBow.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		
		deltaBow.setItemMeta(dBow);
		
		
		return deltaBow;
	}
	
	public static Map<EquipmentSlot, ItemStack> getDeltaSet() {
		 Map<EquipmentSlot, ItemStack> deltaSet = new HashMap<>();
		 
		 ItemStack deltaHelmet = new ItemStack(Material.IRON_HELMET);
		 ItemMeta hMeta = deltaHelmet.getItemMeta();
		 hMeta.setDisplayName(ChatColor.AQUA + "Delta Helmet");
		 hMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
		 hMeta.addEnchant(Enchantment.DURABILITY, 7, true);
		 hMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		 deltaHelmet.setItemMeta(hMeta);
		 
		 deltaSet.put(EquipmentSlot.HEAD, deltaHelmet);
		 
		 ItemStack deltaChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		 ItemMeta cMeta = deltaChestplate.getItemMeta();
		 cMeta.setDisplayName(ChatColor.AQUA + "Delta Chestplate");
		 cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 9, true);
		 cMeta.addEnchant(Enchantment.DURABILITY, 10, true);
		 cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 11, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		 deltaChestplate.setItemMeta(cMeta);
		 
		 deltaSet.put(EquipmentSlot.CHEST, deltaChestplate);
		 
		 ItemStack deltaLeggings = new ItemStack(Material.IRON_LEGGINGS);
		 ItemMeta lMeta = deltaLeggings.getItemMeta();
		 lMeta.setDisplayName(ChatColor.AQUA + "Delta Leggings");
		 lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 8, true);
		 lMeta.addEnchant(Enchantment.DURABILITY, 9, true);
		 lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		 deltaLeggings.setItemMeta(lMeta);
		 
		 deltaSet.put(EquipmentSlot.LEGS, deltaLeggings);
		 
		 ItemStack deltaBoots = new ItemStack(Material.IRON_BOOTS);
		 ItemMeta bMeta = deltaBoots.getItemMeta();
		 bMeta.setDisplayName(ChatColor.AQUA + "Delta Boots");
		 bMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		 bMeta.addEnchant(Enchantment.DURABILITY, 6, true);
		 bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
		 deltaBoots.setItemMeta(bMeta);
		 
		 deltaSet.put(EquipmentSlot.FEET, deltaBoots);
		 
		 return deltaSet;
	}

	
}
