package us.teaminceptus.smpcore.utils.fetcher;

import java.util.ArrayList;
import java.util.List;
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

import us.teaminceptus.smpcore.utils.GeneralUtils;

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
		ItemStack oEssence = GeneralUtils.itemFromNBT("{id: \"minecraft:lime_dye\", tag: {display: {Name: '{\"text\":\"Overworld Essence\",\"color\":\"green\",\"bold\":true,\"italic\":false}'}, HideFlags: 1, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}]}, Count: 1b}");
		return oEssence;
	}
	
	
	public static ItemStack getEndEssence() {
		ItemStack eEssence = GeneralUtils.itemFromNBT("{id: \"minecraft:purple_dye\", tag: {display: {Name: '{\"text\":\"End Essence\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'}, HideFlags: 1, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}]}, Count: 1b}");
		return eEssence;
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
	
	public static ItemStack getWitherMaterial() {
		try {
			return GeneralUtils.itemFromNBT("{id: \"minecraft:charcoal\", tag: {display: {Name: '{\"text\":\"Wither Material\",\"color\":\"black\",\"italic\":false}'}, HideFlags: 1, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}]}, Count: 1b}");
		} catch (Exception e) {
			return null;
		}
	}
	
	private static ItemStack getNBT(String nbt) {
		try {
			return GeneralUtils.itemFromNBT(nbt);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ItemStack getT1Heart() {
		return getNBT("{id:\"minecraft:heart_of_the_sea\",Count:64b,tag:{display:{Name:'{\"text\":\"T1 Heart\",\"color\":\"red\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getT2Heart() {
		return getNBT("{id:\"minecraft:heart_of_the_sea\",Count:64b,tag:{display:{Name:'{\"text\":\"T2 Heart\",\"color\":\"#FF8112\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getEndCore() {
		return getNBT("{id:\"minecraft:player_head\",Count:64b,tag:{display:{Name:'{\"text\":\"End Core\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlOGNjOTliYjQyZGRhMmFhZmJmZjQ1Nzc1Njc3NmIyOGM4ZTM0ZWUyNDVjYzU1M2QyNjk0ZTZiMDRiNzIifX19\"}]},Id:[I; -755202538, 188499458, -1222370440, 1210382921]}}}");
	}
	
	public static ItemStack getAquaticCore() {
		return getNBT("{id:\"minecraft:player_head\",Count:64b,tag:{display:{Name:'{\"text\":\"Aquatic Core\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUxZDVhMWFjMTE0YTgyZmE2NTJmYzIzN2IzZTc4ZjIyZmU5ZDU4ZGU5N2M1MzdiZDVlZjk5YzM4ZmI2NmIyIn19fQ==\"}]},Id:[I; 245611374, -2062007065, -1916001814, 802225404]}}}");
	}
	
	public static ItemStack getVoidCrack() {
		return getNBT("{id:\"minecraft:ender_eye\",Count:64b,tag:{display:{Name:'{\"text\":\"Void Crack\",\"color\":\"gray\",\"bold\":true,\"italic\":false}',Lore:['[{\"text\":\"\"}]']},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getEnchantedEnderPearl() {
		return getNBT("{id:\"minecraft:ender_pearl\",Count:16b,tag:{display:{Name:'{\"text\":\"Enchanted Ender Pearl\",\"color\":\"white\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getEnchantedGoldIngot() {
		return getNBT("{id:\"minecraft:gold_ingot\",Count:64b,tag:{HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getWingRemnant() {
		return getNBT("{id:\"minecraft:leather\",Count:64b,tag:{display:{Name:'{\"text\":\"Wing Remnant\",\"color\":\"dark_gray\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getGoldenNugget() {
		return getNBT("{id:\"minecraft:gold_nugget\",Count:64b,tag:{display:{Name:'{\"text\":\"Enchanted Gold Nugget\",\"color\":\"gold\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getEnergyCore() {
		return getNBT("{id:\"minecraft:player_head\",Count:64b,tag:{display:{Name:'[{\"text\":\"||\",\"color\":\"dark_purple\",\"italic\":false,\"obfuscated\":true},{\"text\":\"Energy Core\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"||\",\"color\":\"dark_purple\",\"italic\":false,\"obfuscated\":true}]'},SkullOwner:{Name:\"MrCodingMen\",Properties:{textures:[{Value:\"ewogICJ0aW1lc3RhbXAiIDogMTYxNDg5MjY4Njg1NywKICAicHJvZmlsZUlkIiA6ICI5YTEzNDQzMWMzMjI0NGMxYWJkMzcwYWUyZjFkOTQ5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNckNvZGluZ01lbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lNzE4Y2I3NzUwZTFiNjU4MDAyN2JmMDgxYTIwNTZkNjY2NTk3MmY4MzUyNjY3ZjY0MWZjNGU0ZWM4YmU2OTEwIgogICAgfQogIH0KfQ==\",Signature:\"hfzBNc8F5VuG2U6pRl83hbhUGQymLNaQYbqZilDTTJ9ZQSt5cASz+4qePiFcv4GCfM1efw3BG41BNu8aXJWmy2PNVpaUHoGZlPzOAf9cse1hEKQ/wYucjY99LePBbeQFEV4vWVuCDiigaUZ+qIJf56uixV4Rm8gtbwGaOaUOfri2XCJQW3iG9cxzbAJ0XWFguXVlJ8ANbhILqgJU3iDxJKetIMYWjpYsxDtjj8PueUISfNXPKIYvyqT5ZHMJQiUU/RTOXbwYJ4VyMXTmTCR1hCEEZgP2xYCToCMoAOdW5h4tDOVulGK3DIB0cbB5tFo7Lwhxr8R7Zc8OCNfbvtOKlxVPcoavb4XMi9jV8i/q1TRuqGRaXIItk67plb526dql/eTftxKpR5S8kums+UAgWVx2ALkYPP8eg8EN4fF5c1YpBW9mZnHi0w4tanx757+0H/uX7SuBH+UQuX79i8TJyJ2xVKXlcguLrFC7QlHLailBd/lvxvdR4ebe0iPTGWB6d4OEajkRWHJBVqCgd73lR5XESdjjBO3O/aOL1S4hxeHpaQlDk/ryIsNo6bEatuYi1ZHH5Y8sxY46vGBeNnn8zCOaNdQc/EigGQd/wev8rGFL33xO/lmCahAI+DfreSRJVZ6bKg3qIeh7zOONaCaokk5xxl5BegdnVu+FfMIcR7Y=\"}]},Id:[I; -1710013391, -1021164351, -1412206418, 790467735]}}}");
	}
	
	public static ItemStack getGlowingEndstoneFragment() {
		return getNBT("{id:\"minecraft:iron_nugget\",Count:64b,tag:{display:{Name:'{\"text\":\"Glowing Endstone Fragment\",\"color\":\"dark_purple\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}");
	}
	
	public static ItemStack getUndeadCore() {
		return getNBT("{id:\"minecraft:player_head\",Count:64b,tag:{display:{Name:'{\"text\":\"Undead Core\",\"color\":\"#6B6B6B\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFkN2M4MTZmYzhjNjM2ZDdmNTBhOTNhMGJhN2FhZWZmMDZjOTZhNTYxNjQ1ZTllYjFiZWYzOTE2NTVjNTMxIn19fQ==\"}]},Id:[I; 1275683968, 1255558020, -1180031804, -266380137]}}}");
	}
	
	public static ItemStack getNetherCore() {
		return getNBT("{id:\"minecraft:player_head\",Count:64b,tag:{display:{Name:'{\"text\":\"Nether Core\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTlhNWExZTY5YjRmODEwNTYyNTc1MmJjZWUyNTM0MDY2NGIwODlmYTFiMmY1MjdmYTkxNDNkOTA2NmE3YWFkMiJ9fX0=\"}]},Id:[I; 1414716801, 1338001167, -1570915896, -1506943044]}}}");
	}
	
	
	
	
	
	
	
 }
