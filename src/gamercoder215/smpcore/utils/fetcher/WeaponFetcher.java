package gamercoder215.smpcore.utils.fetcher;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class WeaponFetcher {
	
	/* TODO 
	 * 
	 * Make Perussis:
	 * Nether / End Perussi
	 * Raider Perussi
	 * Player Perussi
	 * Dragon Perussi
	 * 
	 */
	
	// Perussis
	public static ItemStack getWitherCounter() {
		ItemStack witherCounter = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta counterMeta = witherCounter.getItemMeta();
		
		counterMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Titan Wither Perussi");
		
		counterMeta.addEnchant(Enchantment.DAMAGE_ALL, 225, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 200, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 200, true);
		counterMeta.addEnchant(Enchantment.DIG_SPEED, 200, true);
		
		counterMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		
		counterMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> counterLore = new ArrayList<String>();
		
		counterLore.add("");
		counterLore.add(ChatColor.GRAY + "For every " + ChatColor.RED + "5" + ChatColor.GRAY + " withers killed,");
		counterLore.add(ChatColor.GRAY + "this weapon gains " + ChatColor.RED + "10" + ChatColor.GRAY + " damage.");
		counterLore.add("");
		
		counterMeta.setLore(counterLore);
		
		counterMeta.setUnbreakable(true);
		
		witherCounter.setItemMeta(counterMeta);
		
		return witherCounter;
		
	}
	
	public static ItemStack getSpiderCounter() {
		ItemStack spiderCounter = new ItemStack(Material.IRON_SWORD);
		ItemMeta counterMeta = spiderCounter.getItemMeta();
		
		counterMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Titan Spider Perussi");
		
		counterMeta.addEnchant(Enchantment.DAMAGE_ALL, 175, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 100, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 400, true);
		
		counterMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 7.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		
		counterMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> counterLore = new ArrayList<String>();
		
		counterLore.add("");
		counterLore.add(ChatColor.GRAY + "For every " + ChatColor.RED + "50" + ChatColor.GRAY + " spiders killed,");
		counterLore.add(ChatColor.GRAY + "this weapon gains " + ChatColor.RED + "10" + ChatColor.GRAY + " damage.");
		counterLore.add("");
		
		counterMeta.setLore(counterLore);
		
		counterMeta.setUnbreakable(true);
		
		spiderCounter.setItemMeta(counterMeta);
		
		return spiderCounter;
	}
	
	public static ItemStack getArcherPerussi() {
		ItemStack archerCounter = new ItemStack(Material.IRON_SWORD);
		ItemMeta counterMeta = archerCounter.getItemMeta();
		
		counterMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Titan Skeleton Perussi");
		
		counterMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 200, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 400, true);
		counterMeta.addEnchant(Enchantment.DAMAGE_ALL, 150, true);
		counterMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 10, true);
		
		counterMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 7.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 6.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.75, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		
		counterMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> counterLore = new ArrayList<String>();
		
		counterLore.add("");
		counterLore.add(ChatColor.GRAY + "For every " + ChatColor.RED + "50" + ChatColor.GRAY + " skeletons killed,");
		counterLore.add(ChatColor.GRAY + "this weapon gains " + ChatColor.RED + "10" + ChatColor.GRAY + " damage.");
		counterLore.add("");
		
		counterMeta.setLore(counterLore);
		
		counterMeta.setUnbreakable(true);
		
		archerCounter.setItemMeta(counterMeta);
		
		return archerCounter;
	}
	
	public static ItemStack getDragonCounter() {
		ItemStack dragonCounter = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta counterMeta = dragonCounter.getItemMeta();
		
		counterMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "|()()()()|" + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " Titan Dragon Perussi " + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|()()()()|");
		
		counterMeta.addEnchant(Enchantment.DAMAGE_ALL, 275, true); // Sharpness CCLXXV (275)
		counterMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 200, true); // Smite CC (200)
		counterMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 175, true); // Bane of Arthropods CLXXV (175) 
		counterMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 100, true); // Looting C (100)
		
		counterMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 9, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		counterMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		
		counterMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> counterLore = new ArrayList<String>();
		
		counterLore.add(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Dragon Slayer XXV");
		counterLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Enderman Slayer LX");
		counterLore.add("");
		counterLore.add(ChatColor.GRAY + "For every dragon killed,");
		counterLore.add(ChatColor.GRAY + "this weapon gains " + ChatColor.RED + "25" + ChatColor.GRAY + " damage.");
		counterLore.add("");
		
		counterMeta.setLore(counterLore);
		
		counterMeta.setUnbreakable(true);
		
		dragonCounter.setItemMeta(counterMeta);
		
		return dragonCounter;
	}
}
