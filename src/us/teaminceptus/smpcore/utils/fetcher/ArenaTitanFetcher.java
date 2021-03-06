package us.teaminceptus.smpcore.utils.fetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import us.teaminceptus.smpcore.utils.GeneralUtils;

public class ArenaTitanFetcher {
	
	public static Map<EquipmentSlot, ItemStack> getTitanAmethystusSet() {
		Map<EquipmentSlot, ItemStack> set = new HashMap<>();
		
		ItemStack aBlade = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta bMeta = aBlade.getItemMeta();
		bMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "" + ChatColor.BOLD + "|()|" +
		ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " Titan Amethystus " + 
		ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "" + ChatColor.BOLD + "|()|");
		bMeta.addEnchant(Enchantment.DAMAGE_ALL, 900, true);
		bMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 750, true);
		bMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 750, true);
		bMeta.addEnchant(Enchantment.FIRE_ASPECT, 400, true);
		bMeta.addEnchant(Enchantment.SWEEPING_EDGE, 200, true);
		bMeta.addEnchant(Enchantment.KNOCKBACK, 50, true);
		bMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bMeta.setUnbreakable(true);
		bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 60, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 60, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		aBlade.setItemMeta(bMeta);
		
		set.put(EquipmentSlot.HAND, aBlade);
		
		try {
			ItemStack aHelmet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Titan Amethystus Helmet\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:800s},{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:150s},{id:\"minecraft:thorns\",lvl:300s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:6.5,Operation:2,UUID:[I;-443287496,-14464775,-1092581379,694561711],Slot:\"head\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:4,Operation:2,UUID:[I;1462340147,-884586224,-1400147851,1704606687],Slot:\"head\"},{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:4,Operation:2,UUID:[I;658276330,-1722857560,-1326002770,-1940481340],Slot:\"head\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:3,Operation:2,UUID:[I;535971511,-1431744834,-1319161351,2028834786],Slot:\"head\"}],SkullOwner:{Id:[I;-2013507775,215892955,-1470470728,1259677754],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY3ZDMxNjA0ZDcyYmY2NTUxMGU5ZDcwN2EwYjgwYTUyY2QyMDJkY2Y1MTRmMTgyNzMyY2QyNWEyZDNkNDc3ZiJ9fX0=\"}]}}}}");
			set.put(EquipmentSlot.HEAD, aHelmet);
		} catch (Exception e) {
			set.put(EquipmentSlot.HEAD, null);
			e.printStackTrace();
		}
		
		ItemStack aChest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta cMeta = (LeatherArmorMeta) aChest.getItemMeta();
		cMeta.setColor(Color.fromBGR(142, 0, 150));
		cMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan Amethystus Chestplate");
		cMeta.setUnbreakable(true);
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 800, true);
		cMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		cMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE);
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 6.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		aChest.setItemMeta(cMeta);
		
		set.put(EquipmentSlot.CHEST, aChest);
		
		ItemStack aLegs = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lMeta = (LeatherArmorMeta) aChest.getItemMeta();
		lMeta.setColor(Color.fromBGR(142, 0, 150));
		lMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan Amethystus Leggings");
		lMeta.setUnbreakable(true);
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 800, true);
		lMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		lMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE);
		lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 6.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		aLegs.setItemMeta(lMeta);
		
		set.put(EquipmentSlot.LEGS, aLegs);
		
		ItemStack aBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta boMeta = (LeatherArmorMeta) aBoots.getItemMeta();
		boMeta.setColor(Color.fromBGR(142, 0, 150));
		boMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Titan Amethystus Boots");
		boMeta.setUnbreakable(true);
		boMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 800, true);
		boMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		boMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		boMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE);
		boMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 6.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		boMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		boMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		boMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		aBoots.setItemMeta(boMeta);
		
		set.put(EquipmentSlot.FEET, aBoots);
		
		return set;
	}
	
	public static Map<EquipmentSlot, ItemStack> getTitanNetheriteSet() {
		Map<EquipmentSlot, ItemStack> set = new HashMap<>();
		
		ItemStack nTotem = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta nMeta = nTotem.getItemMeta();
		nMeta.setDisplayName(ChatColor.DARK_GRAY + "Netherite Totem");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "25%" + ChatColor.GRAY + " chance to deflect attacks,");
		lore.add(ChatColor.GRAY + "when in ANY slot from your inventory.");
		lore.add(ChatColor.GRAY + "Can also save you from death.");
		nMeta.setLore(lore);
		nMeta.setLocalizedName("netherite_totem");
		nMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		nMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		nTotem.setItemMeta(nMeta);
		
		set.put(EquipmentSlot.OFF_HAND, nTotem);
		
		ItemStack nAxe = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta aMeta = nAxe.getItemMeta();
		aMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Axe of Netherite");
		aMeta.setUnbreakable(true);
		aMeta.addEnchant(Enchantment.DAMAGE_ALL, 900, true);
		aMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 500, true);
		aMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 500, true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aMeta.setLocalizedName("axe_netherite_titan");
		aMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 30, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		aMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 24, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		aMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 28, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		nAxe.setItemMeta(aMeta);
		
		set.put(EquipmentSlot.HAND, nAxe);
		
		try {
			ItemStack nHelmet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'[{\"text\":\"||\",\"color\":\"gray\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Titan Netherite Helmet \",\"color\":\"dark_gray\",\"obfuscated\":false},{\"text\":\"||\"}]'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:900s},{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:200s},{id:\"minecraft:thorns\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:3,Operation:2,UUID:[I;-1006681468,-2082061131,-1479477314,2053064455],Slot:\"head\"},{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:42,Operation:2,UUID:[I;-1823974042,1342915170,-1616704891,-1569107980],Slot:\"head\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:37,Operation:2,UUID:[I;925633059,1532051549,-1135220727,-874988559],Slot:\"head\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:-0.05,Operation:2,UUID:[I;382656228,1411269156,-1156122382,450530748],Slot:\"head\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:34,Operation:2,UUID:[I;-452446159,-808828639,-1501147117,364873115],Slot:\"head\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:-0.1,Operation:2,UUID:[I;206149818,-1803860259,-1682652738,1476812186],Slot:\"head\"}],SkullOwner:{Id:[I;-842754105,2022919240,-1893530114,-1444333272],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg1MWIxMTJjMmY3OThjMGFjNzAyZmY5NWZlZTU5Y2RhMGNkODNjYWNmN2QxNWZjY2YyNjVjNGQ3MjhkODk5YiJ9fX0=\"}]}}}}");
			set.put(EquipmentSlot.HEAD, nHelmet);
		} catch (Exception e) {
			set.put(EquipmentSlot.HEAD, null);
		}
		
		ItemStack nChest = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta cMeta = nChest.getItemMeta();
		cMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||" + 
		ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " Titan Netherite Chestplate " +
		ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||");
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 900, true);
		cMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		cMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		cMeta.addEnchant(Enchantment.OXYGEN, 200, true);
		cMeta.addEnchant(Enchantment.THORNS, 250, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 42, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 37, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 34, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), -0.05, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), -0.16, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		nChest.setItemMeta(cMeta);

		set.put(EquipmentSlot.CHEST, nChest);
		
		ItemStack nLegs = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta lMeta = nLegs.getItemMeta();
		lMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||" + 
		ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " Titan Netherite Leggings " +
		ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||");
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 900, true);
		lMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		lMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		lMeta.addEnchant(Enchantment.THORNS, 250, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 42, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 37, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 34, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), -0.05, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), -0.14, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		nLegs.setItemMeta(lMeta);

		set.put(EquipmentSlot.LEGS, nLegs);
		
		ItemStack nBoots = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta bMeta = nBoots.getItemMeta();
		bMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||" + 
		ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " Titan Netherite Boots " +
		ChatColor.GRAY + "" + ChatColor.MAGIC + ChatColor.BOLD + "||");
		bMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 900, true);
		bMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		bMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		bMeta.addEnchant(Enchantment.THORNS, 250, true);
		bMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 3, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 42, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 37, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 34, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), -0.05, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), -0.08, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		nBoots.setItemMeta(bMeta);

		set.put(EquipmentSlot.FEET, nBoots);
		return set;
	}
	
	public static ItemStack getArcherHelmet() {
		ItemStack aH = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta aMeta = aH.getItemMeta();
		aMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Archer's Helmet");
		aMeta.setUnbreakable(true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aMeta.setLocalizedName("archer_helmet");
		aMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
		aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 875, true);
		aMeta.addEnchant(Enchantment.OXYGEN, 300, true);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + "+400% Arrow Damage");
		aMeta.setLore(lore);
		aMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 5.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));
		aH.setItemMeta(aMeta);
		return aH;
	}
	
	public static Map<EquipmentSlot, ItemStack> getPlasmaArmor() {
		Map<EquipmentSlot, ItemStack> set = new HashMap<>();
		
		try {
			ItemStack pHelmet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Plasma Helmet\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1100s},{id:\"minecraft:blast_protection\",lvl:950s},{id:\"minecraft:projectile_protection\",lvl:950s},{id:\"minecraft:respiration\",lvl:300s},{id:\"minecraft:thorns\",lvl:400s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:50,Operation:2,UUID:[I;-121327002,571228413,-1549857384,717145752],Slot:\"head\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:45,Operation:2,UUID:[I;723002238,1330333119,-1993329393,113890733],Slot:\"head\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:8,Operation:2,UUID:[I;-716202217,-2116663682,-1155718300,388806540],Slot:\"head\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:67,Operation:2,UUID:[I;-64067257,-2141894122,-1207609201,82685403],Slot:\"head\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:5,Operation:0,UUID:[I;219480626,-397717923,-1968370094,753688513],Slot:\"head\"}],SkullOwner:{Id:[I;921572127,1665680068,-1816382702,751612658],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU4NWJjNzU3ZGY1MDhhN2MyZWY3MDU1ZmY0ZDliYmQ2OWIxYmE4MGIzNTRjOWUzOWJjNTUyY2VjYmQ3ZTVjIn19fQ==\"}]}}}}");
			set.put(EquipmentSlot.HEAD, pHelmet);
		} catch (Exception e) {
			set.put(EquipmentSlot.HEAD, null);
		}
		
		ItemStack pChest = new ItemStack(Material.GOLDEN_CHESTPLATE);
		ItemMeta cMeta = pChest.getItemMeta();
		cMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Plasma Chestplate");
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1100, true);
		cMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 950, true);
		cMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 950, true);
		cMeta.addEnchant(Enchantment.THORNS, 400, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cMeta.setUnbreakable(true);
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 50, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 45, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 67, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		cMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		pChest.setItemMeta(cMeta);
		
		set.put(EquipmentSlot.CHEST, pChest);
		
		ItemStack pLegs = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemMeta lMeta = pLegs.getItemMeta();
		lMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Plasma Leggings");
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1100, true);
		lMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 950, true);
		lMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 950, true);
		lMeta.addEnchant(Enchantment.THORNS, 400, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lMeta.setUnbreakable(true);
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 50, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 45, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 67, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		lMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		pLegs.setItemMeta(lMeta);
		
		set.put(EquipmentSlot.LEGS, pLegs);
		
		ItemStack pBoots = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta bMeta = pBoots.getItemMeta();
		bMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Plasma Boots");
		bMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1100, true);
		bMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 950, true);
		bMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 950, true);
		bMeta.addEnchant(Enchantment.THORNS, 400, true);
		bMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bMeta.setUnbreakable(true);
		bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 50, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 45, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 67, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
		pBoots.setItemMeta(bMeta);
		
		set.put(EquipmentSlot.FEET, pBoots);
		
		return set;
	}
	
	public static ItemStack getProtectionPotato() {
		// No Jokes, k?
		ItemStack pPo = new ItemStack(Material.POTATO);
		ItemMeta pMeta = pPo.getItemMeta();
		pMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		pMeta.setLocalizedName("protection_potato");
		pMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pMeta.setDisplayName(ChatColor.GOLD + "Protection Potato");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "When consumed, armor,");
		lore.add(ChatColor.GRAY + "armor toughness, or knockback");
		lore.add(ChatColor.GRAY + "resistance will be buffed by");
		lore.add(ChatColor.GREEN + "5%" + ChatColor.GRAY + ".");
		pMeta.setLore(lore);
		pPo.setItemMeta(pMeta);
		return pPo;
	}
	
	public static ItemStack getWitherScythe() {
		ItemStack wS = new ItemStack(Material.NETHERITE_HOE);
		ItemMeta wMeta = wS.getItemMeta();
		wMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Wither Scythe");
		wMeta.setLocalizedName("wither_scythe");
		wMeta.addEnchant(Enchantment.DAMAGE_ALL, 1300, true);
		wMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 1000, true);
		wMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 1000, true);
		wMeta.addEnchant(Enchantment.KNOCKBACK, 25, true);
		wMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		wMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 67, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		wMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 52, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		wMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 40, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		wS.setItemMeta(wMeta);
		
		return wS;
	}

  public static Map<EquipmentSlot, ItemStack> getHydraSet() {
    Map<EquipmentSlot, ItemStack> set = new HashMap<>();

    try {
      ItemStack helmet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Hydra Helmet\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:3000s},{id:\"minecraft:fire_protection\",lvl:2500s},{id:\"minecraft:blast_protection\",lvl:2500s},{id:\"minecraft:projectile_protection\",lvl:2500s},{id:\"minecraft:respiration\",lvl:300s},{id:\"minecraft:thorns\",lvl:50s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:90,Operation:2,UUID:[I;926565534,-1001370761,-1518815121,93068847],Slot:\"head\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:120,Operation:2,UUID:[I;670019754,-255835484,-1525204091,-387250670],Slot:\"head\"},{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:110,Operation:2,UUID:[I;-1363572109,459293619,-2129402186,1881795696],Slot:\"head\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:95,Operation:2,UUID:[I;-501317485,-1974712709,-1240764910,-1625564670],Slot:\"head\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:60,Operation:2,UUID:[I;-1134933296,1482440848,-1683491317,-1580489677],Slot:\"head\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:2,Operation:2,UUID:[I;1775200696,1831355822,-1161373750,-772849071],Slot:\"head\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:25,Operation:2,UUID:[I;1461735467,1311195423,-1572823321,757955705],Slot:\"head\"}],SkullOwner:{Id:[I;1185748012,1572095227,-1360977709,1907579260],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFkN2RjYmU5MzQwMWIzZDNiNGU1MjIyZWY4MDk3YWVmNDQzMWY0ODA2NGQwYTA1MzFmYTA5NTJjMjExZTBlNiJ9fX0=\"}]}}}}");
      set.put(EquipmentSlot.HEAD, helmet);
    } catch (Exception e) {
      set.put(EquipmentSlot.HEAD, null);
    }
    
    ItemStack hChest = new ItemStack(Material.NETHERITE_CHESTPLATE);
    ItemMeta cMeta = hChest.getItemMeta();
    cMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Hydra Chestplate");
    cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3000, true);
    cMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2500, true);
    cMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2500, true);
    cMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2500, true);
    cMeta.addEnchant(Enchantment.THORNS, 50, true);
    cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    cMeta.setUnbreakable(true);
    cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 90, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 120, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 110, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 95, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_LUCK.name(), 60, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
    
    hChest.setItemMeta(cMeta);
    
    set.put(EquipmentSlot.CHEST, hChest);
    
    ItemStack hLegs = new ItemStack(Material.NETHERITE_LEGGINGS);
    ItemMeta lMeta = hLegs.getItemMeta();
    lMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Hydra Leggings");
    lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3000, true);
    lMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2500, true);
    lMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2500, true);
    lMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2500, true);
    lMeta.addEnchant(Enchantment.THORNS, 50, true);
    lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    lMeta.setUnbreakable(true);
    lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 90, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 120, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 110, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 95, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_LUCK.name(), 60, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    lMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
    
    hLegs.setItemMeta(lMeta);
    
    set.put(EquipmentSlot.LEGS, hLegs);
    
    ItemStack hBoots = new ItemStack(Material.NETHERITE_BOOTS);
    ItemMeta bMeta = hBoots.getItemMeta();
    bMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Hydra Boots");
    bMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3000, true);
    bMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2500, true);
    bMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2500, true);
    bMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2500, true);
    bMeta.addEnchant(Enchantment.THORNS, 50, true);
    bMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    bMeta.setUnbreakable(true);
    bMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), 90, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 120, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), 110, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 95, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_LUCK.name(), 60, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    bMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
    
    hBoots.setItemMeta(bMeta);
    
    set.put(EquipmentSlot.FEET, hBoots);
    return set;
  }	
}
