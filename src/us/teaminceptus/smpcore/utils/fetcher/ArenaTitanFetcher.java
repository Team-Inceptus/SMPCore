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
	
	public static List<ItemStack> getItems() {
		List<ItemStack> items = new ArrayList<>();
		items.add(getArcherHelmet());
		
		for (EquipmentSlot s : EquipmentSlot.values()) {
			if (getTitanAmethystusSet().get(s) != null) items.add(getTitanAmethystusSet().get(s));
			if (getTitanNetheriteSet().get(s) != null) items.add(getTitanNetheriteSet().get(s));
		}
		
		return items;
	}
	
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
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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
		boMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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
		aMeta.setLocalizedName("netherite_totem");
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
		lMeta.addEnchant(Enchantment.OXYGEN, 200, true);
		lMeta.addEnchant(Enchantment.THORNS, 250, true);
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
		bMeta.addEnchant(Enchantment.OXYGEN, 200, true);
		bMeta.addEnchant(Enchantment.THORNS, 250, true);
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
	
}
