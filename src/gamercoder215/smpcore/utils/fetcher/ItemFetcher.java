package gamercoder215.smpcore.utils.fetcher;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.utils.GeneralUtils;

public class ItemFetcher {
	
	public static ItemStack getInventoryCore() {
		ItemStack inventoryCore = new ItemStack(Material.SOUL_LANTERN, 1);
		ItemMeta coreMeta = inventoryCore.getItemMeta();
		
		coreMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Respawn Core");
		coreMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		coreMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> coreLore = new ArrayList<String>();
		
		coreLore.add("");
		coreLore.add(ChatColor.GRAY + "On death, this item");
		coreLore.add(ChatColor.GRAY + "is consumed, but will");
		coreLore.add(ChatColor.GRAY + "keep your inventory intact.");
		coreLore.add("");
		
		coreMeta.setLore(coreLore);
		
		inventoryCore.setItemMeta(coreMeta);
		
		return inventoryCore;
	}
	
	public static ItemStack getBlackHoleCandle() {
		ItemStack blackHoleCandle = new ItemStack(Material.BLACK_CANDLE, 1);
		ItemMeta candleMeta = blackHoleCandle.getItemMeta();
		
		candleMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Refined Black Hole Candle");
		candleMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		candleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		blackHoleCandle.setItemMeta(candleMeta);
		
		return blackHoleCandle;
	}
	
	public static ItemStack getSpeedArtifact() {
		ItemStack blackHoleCandle = new ItemStack(Material.SOUL_LANTERN, 1);
		ItemMeta candleMeta = blackHoleCandle.getItemMeta();
		
		candleMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Speed Artifact");
		candleMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		candleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		blackHoleCandle.setItemMeta(candleMeta);
		
		return blackHoleCandle;
	}
	
	public static ItemStack getIronEssence() {
		ItemStack ironEssence = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta essenceMeta = ironEssence.getItemMeta();
		
		essenceMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Essence");
		essenceMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		essenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		ironEssence.setItemMeta(essenceMeta);
		
		return ironEssence;
	}
	
	public static ItemStack getGoldenEssence() {
		ItemStack goldEssence = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta essenceMeta = goldEssence.getItemMeta();
		
		essenceMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Gold Essence");
		essenceMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		essenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		goldEssence.setItemMeta(essenceMeta);
		
		return goldEssence;
	}
	
	public static ItemStack getOverworldEssence() {
		try {
			ItemStack oEssence = GeneralUtils.itemFromNBT("{id: \"minecraft:lime_dye\", tag: {display: {Name: '{\"text\":\"Overworld Essence\",\"color\":\"green\",\"bold\":true,\"italic\":false}'}, HideFlags: 1, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}]}, Count: 1b}");
			return oEssence;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ItemStack getEndEssence() {
		try {
			ItemStack eEssence = GeneralUtils.itemFromNBT("{id: \"minecraft:purple_dye\", tag: {display: {Name: '{\"text\":\"End Essence\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'}, HideFlags: 1, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}]}, Count: 1b}");
			return eEssence;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getEnchantedOverworldEssence() {
		ItemStack enchantedOverworldEssence = new ItemStack(Material.GREEN_DYE, 1);
		ItemMeta essenceMeta = enchantedOverworldEssence.getItemMeta();
		
		essenceMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "()()" + ChatColor.RESET + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + " Enchanted Overworld Essence " + ChatColor.GREEN + "" + ChatColor.MAGIC + "()()");
		essenceMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		essenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		enchantedOverworldEssence.setItemMeta(essenceMeta);
		
		return enchantedOverworldEssence;
	}
	
	public static ItemStack getEnchantedNetherEssence() {
		ItemStack enchantedNetherEssence = new ItemStack(Material.MAGMA_CREAM, 1);
		ItemMeta essenceMeta = enchantedNetherEssence.getItemMeta();
		
		essenceMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "()()" + ChatColor.RESET + ChatColor.DARK_RED + "" + ChatColor.BOLD + " Enchanted Nether Essence " + ChatColor.RED + "" + ChatColor.MAGIC + "()()");
		essenceMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		essenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		enchantedNetherEssence.setItemMeta(essenceMeta);
		
		return enchantedNetherEssence;
	}
	
	
	/*
	 * Checks:
	 * 
	 * Channeling 14
	 * Hide CanPlaceOn AND Hide CanDestroy
	 * Feather Falling 13657
	 */
	public static ItemStack getTeleportationHook() {
		ItemStack teleportationHook = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta hookMeta = teleportationHook.getItemMeta();
		ArrayList<String> hookLore = new ArrayList<String>();
		hookLore.add(ChatColor.GRAY + "Rename to what you want.");
		hookLore.add(ChatColor.GRAY + "A better, much optimized");
		hookLore.add(ChatColor.GRAY + "version of the Grapple.");
		
		hookMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Teleportation Hook");
		hookMeta.setLore(hookLore);
		hookMeta.addEnchant(Enchantment.CHANNELING, 14, true);
		hookMeta.addEnchant(Enchantment.PROTECTION_FALL, 13657, true);
		hookMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_DESTROYS);
		
		teleportationHook.setItemMeta(hookMeta);
		
		return teleportationHook;
	}

	public static ItemStack getAlphaHoe() {
		ItemStack alHoe = new ItemStack(Material.NETHERITE_HOE);
		ItemMeta aMeta = alHoe.getItemMeta();
		aMeta.setDisplayName(ChatColor.GREEN + "Alpha Hoe");

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "Insta-breaks any and");
		lore.add(ChatColor.GRAY + "all crops. Cannot break");
		lore.add(ChatColor.GRAY + "farmland and logs.");

		aMeta.setLore(lore);
		aMeta.addEnchant(Enchantment.DIG_SPEED, 125, true);
		aMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		aMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", 4, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aMeta.setUnbreakable(true);

		alHoe.setItemMeta(aMeta);

		return alHoe;
	}

	
 }
