package gamercoder215.smpcore.utils.fetcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.listeners.GUIManagers;
import gamercoder215.smpcore.utils.GeneralUtils;
import gamercoder215.smpcore.utils.enums.TitanType;

public class TitanFetcher {
	/* Titan Checks:
	 * Unbreaking 30126 (DURABILITY)
	 * Hide CanPlaceOn (HIDE_PLACED_ON)
	 * 
	 * Titan Misc Checks:
	 * Protection 26105 (PROTECTION_ENVIRONMENTAL)
	 * Hide Unbreakable (HIDE_UNBREAKABLE)
	 * 
	 * Titan Armor Checks:
	 * Mending 29 (MENDING)
	 * Hide CanDestroy (HIDE_DESTROYS)
	 */
	
	public static Inventory getTitanWarps() {
		Inventory inv = GUIManagers.generateGUI(45, ChatColor.DARK_AQUA + "Titan Warps");
		
		ItemStack dimTeleporter = new ItemStack(Material.END_PORTAL_FRAME, 1);
		ItemMeta dimMeta = dimTeleporter.getItemMeta();
		dimMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dimensional Teleporter");
		dimMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		dimMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dimTeleporter.setItemMeta(dimMeta);
		
		inv.setItem(19, dimTeleporter);
		
		ItemStack arescentAve = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta aveMeta = arescentAve.getItemMeta();
		aveMeta.setDisplayName(ChatColor.RED + "Arescent Avenue");
		aveMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		aveMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		arescentAve.setItemMeta(aveMeta);
		
		inv.setItem(21, arescentAve);
		
		ItemStack praefortisPlantation = new ItemStack(Material.WARPED_WART_BLOCK);
		ItemMeta praefortisMeta = praefortisPlantation.getItemMeta();
		praefortisMeta.setDisplayName(ChatColor.DARK_AQUA + "Praefortis Plantation");
		praefortisPlantation.setItemMeta(praefortisMeta);
		
		inv.setItem(22, praefortisPlantation);
		
		ItemStack iabesiumMines = new ItemStack(Material.RAW_COPPER);
		ItemMeta minesMeta = iabesiumMines.getItemMeta();
		minesMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		minesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		minesMeta.setDisplayName(GeneralUtils.hexToChat("9b5d00", "Iabesium Mines"));
		iabesiumMines.setItemMeta(minesMeta);
		
		inv.setItem(23, iabesiumMines);
		
		ItemStack constibilis = new ItemStack(Material.SOUL_SAND, 1);
		ItemMeta constibilisMeta = constibilis.getItemMeta();
		constibilisMeta.setDisplayName(ChatColor.AQUA + "Constibilis Crator");
		constibilis.setItemMeta(constibilisMeta);
		
		inv.setItem(13, constibilis);
		
		ItemStack ferrumBlock = new ItemStack(Material.IRON_BLOCK, 1);
		ItemMeta blockmeta = ferrumBlock.getItemMeta();
		blockmeta.setDisplayName(ChatColor.WHITE + "Ferrum Mountains");
		ferrumBlock.setItemMeta(blockmeta);
		
		inv.setItem(31, ferrumBlock);
		
		ItemStack incitatusForest = new ItemStack(Material.ANCIENT_DEBRIS, 1);
		ItemMeta forestMeta = incitatusForest.getItemMeta();
		forestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Incitatus Forest");
		incitatusForest.setItemMeta(forestMeta);
		
		inv.setItem(25, incitatusForest);
		
		return inv;
	}
	
	public static ItemStack getShopInfo() {
		ItemStack shopInfo = new ItemStack(Material.REDSTONE_TORCH);
		ItemMeta shopMeta = shopInfo.getItemMeta();
		shopMeta.setDisplayName(ChatColor.YELLOW + "Left Click to Buy");
		ArrayList<String> shopLore = new ArrayList<String>();
		shopLore.add(ChatColor.YELLOW + "Right Click to Sell");
		shopMeta.setLore(shopLore);
		shopInfo.setItemMeta(shopMeta);
		
		return shopInfo;
	}
	
	
	
	public static Inventory getVenaliciusTrade() {
		Inventory inv = GUIManagers.generateGUI(54, ChatColor.GRAY + "Titan Shop - Venalicius's Barter");
		
		ItemStack ossumTrade = new ItemStack(Material.TUFF);
		ItemMeta ossumMeta = ossumTrade.getItemMeta();
		ossumMeta.setDisplayName(ChatColor.GRAY + "Ossum Trade");
		ArrayList<String> ossumLore = new ArrayList<String>();
		ossumLore.add(ChatColor.GRAY + "Get: Ossum x1");
		ossumLore.add(ChatColor.DARK_GRAY + "For: Saxum x8");
		ossumMeta.setLore(ossumLore);
		ossumTrade.setItemMeta(ossumMeta);
		
		inv.setItem(10, ossumTrade);
		
		ItemStack praefortisTrade = new ItemStack(Material.WARPED_WART_BLOCK);
		ItemMeta praefortisMeta = praefortisTrade.getItemMeta();
		praefortisMeta.setDisplayName(ChatColor.AQUA + "Praefortis Trade");
		ArrayList<String> praefortisLore = new ArrayList<String>();
		praefortisLore.add(ChatColor.GRAY + "Get: Praefortis Block x1");
		praefortisLore.add(ChatColor.DARK_GRAY + "For: Praefortis Shroom x9");
		praefortisMeta.setLore(praefortisLore);
		praefortisTrade.setItemMeta(praefortisMeta);
		
		inv.setItem(11, praefortisTrade);
		
		inv.setItem(31, getShopInfo());
		
		return inv;
	}
	
	// TODO Other Items
	
	public static ItemStack getTitanCore() {
		try {
			ItemStack titanCore = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", tag: {display: {Name: '{\"text\":\"Titan Core\",\"color\":\"#006478\",\"bold\":true,\"italic\":false}'}, SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRhZDIyOWQ4MDMwODA1OWZhN2FlZDg2NTQzNzc5Y2Y5MzNmOTFiNmE0Mzc0MzEyOTNkMGJiMzFhMDk1NWI3MSJ9fX0=\"}]}, Id: [I; 1341988342, 1338392604, -1334015479, -26366109]}}, Count: 1b}");
			return titanCore;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// TODO Titan World Tools
	public static ItemStack getOpulensHoe() {
		try {
			ItemStack opulensHoe = GeneralUtils.itemFromNBT("{id:\"minecraft:wooden_hoe\",Count:1b,tag:{CanDestroy:[\"minecraft:warped_fungus\",\"minecraft:warped_nylium\"],display:{Name:'{\"text\":\"Opulens Hoe\",\"color\":\"dark_aqua\",\"italic\":false}'},HideFlags:8,Unbreakable:1b}}");
			return opulensHoe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getPraefortisGen(int gen) {
		try {
			if (gen > 4) gen = 4;
			if (gen < 1) gen = 1;
			
			if (gen == 1) {
				ItemStack praefortisGen = GeneralUtils.itemFromNBT("{id: \"minecraft:wooden_hoe\", Count: 1b, tag: {Unbreakable: 1b, HideFlags: 9, display: {Name: '{\"text\":\"Praefortis Gen I\",\"color\":\"aqua\",\"italic\":false}'}, Enchantments: [{lvl: 1s, id: \"minecraft:efficiency\"}], Damage: 0, CanDestroy: [\"minecraft:warped_fungus\", \"minecraft:warped_nylium\", \"minecraft:warped_wart_block\"]}}");
				return praefortisGen;
			} else if (gen == 2) {
				ItemStack praefortisGen = GeneralUtils.itemFromNBT("{id:\"minecraft:wooden_hoe\",Count:1b,tag:{CanDestroy:[\"minecraft:warped_fungus\",\"minecraft:warped_nylium\",\"minecraft:warped_wart_block\",\"minecraft:shroomlight\"],display:{Name:'{\"text\":\"Praefortis Gen II\",\"color\":\"aqua\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:2s}]}}");
				return praefortisGen;
			} else if (gen == 3) {
				ItemStack praefortisGen = GeneralUtils.itemFromNBT("{id:\"minecraft:stone_hoe\",Count:1b,tag:{CanDestroy:[\"minecraft:warped_fungus\",\"minecraft:warped_nylium\",\"minecraft:warped_wart_block\",\"minecraft:shroomlight\",\"minecraft:soul_sand\"],display:{Name:'{\"text\":\"Praefortis Gen III\",\"color\":\"dark_aqua\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:3s}]}}");
				return praefortisGen;
			} else if (gen == 4) {
				ItemStack praefortisGen = GeneralUtils.itemFromNBT("{id:\"minecraft:iron_hoe\",Count:1b,tag:{CanDestroy:[\"minecraft:warped_fungus\",\"minecraft:warped_nylium\",\"minecraft:warped_wart_block\",\"minecraft:shroomlight\",\"minecraft:soul_sand\",\"minecraft:warped_hyphae\"],display:{Name:'{\"text\":\"Praefortis Gen IV\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:5s}]}}");
				return praefortisGen;
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getConstibilisGen() {
		try {
			ItemStack constibilisGen = GeneralUtils.itemFromNBT("{id:\"minecraft:iron_axe\",Count:1b,tag:{CanDestroy:[\"minecraft:warped_fungus\",\"minecraft:warped_nylium\",\"minecraft:warped_wart_block\",\"minecraft:shroomlight\",\"minecraft:soul_sand\",\"minecraft:warped_hyphae\"],display:{Name:'{\"text\":\"Constibilis Gen I\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:9s}]}}");
			return constibilisGen;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getSaxumPickaxe() {
		try {
			ItemStack saxumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:wooden_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\"],display:{Name:'{\"text\":\"Saxum Pickaxe\",\"color\":\"gray\",\"italic\":false}'},HideFlags:8,Unbreakable:1b}}");
			return saxumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getRefinedSaxumPickaxe() {
		try {
			ItemStack saxumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:stone_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\"],display:{Name:'{\"text\":\"Refined Saxum Pickaxe\",\"color\":\"gray\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:1s}]}}");
			return saxumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	public static ItemStack getOssumPickaxe() {
		try {
			ItemStack ossumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:stone_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\",\"minecraft:deepslate_copper_ore\"],display:{Name:'{\"text\":\"Ossum Pickaxe\",\"color\":\"dark_gray\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:3s}]}}");
			return ossumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getCantoniumPickaxe() {
		try {
			ItemStack cantoniumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:iron_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\",\"minecraft:deepslate_copper_ore\",\"minecraft:polished_diorite\"],display:{Name:'{\"text\":\"Cantonium Pickaxe\",\"color\":\"white\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:5s}]}}");
			return cantoniumPickaxe;
		} catch (CommandSyntaxException e)  {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getRefinedCantoniumPickaxe() {
		try {
			ItemStack cantoniumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:iron_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\",\"minecraft:deepslate_copper_ore\",\"minecraft:polished_diorite\",\"minecraft:raw_iron_block\"],display:{Name:'{\"text\":\"Refined Cantonium Pickaxe\",\"color\":\"white\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:7s}]}}");
			return cantoniumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getFerrumPickaxe() {
		try {
			ItemStack ferrumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:iron_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\",\"minecraft:deepslate_copper_ore\",\"minecraft:polished_diorite\",\"minecraft:raw_iron_block\",\"minecraft:iron_block\"],display:{Name:'{\"text\":\"Ferrum Pickaxe\",\"color\":\"white\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:9s}]}}");
			return ferrumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getEnchantedFerrumPickaxe() {
		try {
			ItemStack ferrumPickaxe = GeneralUtils.itemFromNBT("{id:\"minecraft:diamond_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:deepslate\",\"minecraft:tuff\",\"minecraft:deepslate_copper_ore\",\"minecraft:polished_diorite\",\"minecraft:raw_iron_block\",\"minecraft:iron_block\"],display:{Name:'{\"text\":\"Enchanted Ferrum Pickaxe\",\"color\":\"white\",\"bold\":true,\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:12s}]}}");
			return ferrumPickaxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getIncitatusAxe() {
		try {
			ItemStack incitatusAxe = GeneralUtils.itemFromNBT("{id:\"minecraft:diamond_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:amethyst_block\",\"minecraft:ancient_debris\"],display:{Name:'{\"text\":\"Incitatus Axe\",\"color\":\"light_purple\",\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:4s}]}}");
			return incitatusAxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack getNovaAxe() {
		try {
			ItemStack novaAxe = GeneralUtils.itemFromNBT("{id:\"minecraft:netherite_pickaxe\",Count:1b,tag:{CanDestroy:[\"minecraft:glowstone\",\"minecraft:amethyst_block\",\"minecraft:smooth_basalt\",\"minecraft:ancient_debris\"],display:{Name:'{\"text\":\"Nova Axe\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:efficiency\",lvl:11s}]}}");
			return novaAxe;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Titan World Items
	public static ItemStack getPraefortisShroom() throws CommandSyntaxException {
		ItemStack praefortisShroom = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQyNTE4Mjc4YjZhODg3MDE0Y2E3ZjczZDY5MGJiM2MwZjg3ZjE5MGMwYTI1ZThhY2ZmZjVkYjIwMWZiYTIxNyJ9fX0=\"}]}, Id: [I; -606413287, -1332919988, -1336847232, 493686667]}, display: {Name: '{\"text\":\"Warped Fungus\"}'}}}");
		ItemMeta praefortisMeta = praefortisShroom.getItemMeta();
		praefortisMeta.setDisplayName(ChatColor.DARK_AQUA + "Praefortis Shroom");
		praefortisShroom.setItemMeta(praefortisMeta);
		
		return praefortisShroom;
	}
	
	public static ItemStack getPraefortis() {
		ItemStack praefortis = new ItemStack(Material.WARPED_WART_BLOCK, 1);
		ItemMeta praefortisMeta = praefortis.getItemMeta();
		praefortisMeta.setDisplayName(ChatColor.DARK_AQUA + "Praefortis Block");
		praefortis.setItemMeta(praefortisMeta);
		
		return praefortis;
	}
	
	public static ItemStack getFerrumBlock() {
		ItemStack ferrumBlock = new ItemStack(Material.IRON_BLOCK, 1);
		ItemMeta blockmeta = ferrumBlock.getItemMeta();
		blockmeta.setDisplayName(ChatColor.WHITE + "Block of Ferrum");
		ferrumBlock.setItemMeta(blockmeta);
		
		return ferrumBlock;
	}
	
	public static ItemStack getConstibilis() throws CommandSyntaxException {
		ItemStack constibilis = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5NmIwZDdlZGRmN2IwNTlmYjQwY2ExNmZmMjllOGZjYWU4NTI3NTEyZTJjN2UwZjY3MTdjNGZlYmQ3YjBjNSJ9fX0=\"}]}, Id: [I; -700933268, 1075333996, -1156945916, -859483202]}, display: {Name: '{\"text\":\"Warped Hyphae\"}'}}}");
		ItemMeta constibilisMeta = constibilis.getItemMeta();
		constibilisMeta.setDisplayName(ChatColor.AQUA + "Constibilis");
		constibilis.setItemMeta(constibilisMeta);
		
		return constibilis;
	}
	
	public static ItemStack getIabesium() {
		ItemStack iabesium = new ItemStack(Material.RAW_COPPER, 1);
		ItemMeta iabesiumMeta = iabesium.getItemMeta();
		iabesiumMeta.setDisplayName(GeneralUtils.hexToChat("9b5d00", "Iabesium"));
		iabesiumMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		iabesiumMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		iabesium.setItemMeta(iabesiumMeta);
		
		return iabesium;
	}
	
	public static ItemStack getCantonium() throws CommandSyntaxException {
		ItemStack cantonium = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRhZDBkZGQ1M2ZlMmQzMDk1YmUyMDg1NWFhYTU1M2U5ZWViMGY5ODAxMzRjZjVlMDEyOGRjZjI5MDM3ZmY1NiJ9fX0=\"}]}, Id: [I; 1592369478, -73577914, -1653763014, 132391569]}, display: {Name: '{\"text\":\"Calcite\"}'}}}");
		ItemMeta cantMeta = cantonium.getItemMeta();
		cantMeta.setDisplayName(ChatColor.WHITE + "Cantonium");
		cantonium.setItemMeta(cantMeta);
		
		return cantonium;
	}
	
	public static ItemStack getClarus() throws CommandSyntaxException {
		ItemStack clarus = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU3NTJjOGY0MjIwZTJiM2UzOTc1NTU2ODQ1NDYwNjEzYWFlYjRkYjBjNjRiYTFjZTk2ZmFiZjNkNmVjMzVkYyJ9fX0=\"}]}, Id: [I; -736629994, 528239951, -2024529591, -692181003]}, display: {Name: '{\"text\":\"Light Rune\"}'}}}");
		ItemMeta clarusMeta = clarus.getItemMeta();
		clarusMeta.setDisplayName(ChatColor.YELLOW + "Clarus");
		clarus.setItemMeta(clarusMeta);
		
		return clarus;
	}
	
	public static ItemStack getEnchantedFerrum() throws CommandSyntaxException {
		ItemStack enchantedFerrum = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTM5YTFjOTBhMzY3YzQ0ZmFhNDlkMGUyNzM0ODc3NzBmMmUzZjgzZGNiMWViYTZkNzU4YjQ3MzBhZTVmOTY4OCJ9fX0=\"}]}, Id: [I; 285665203, 1068583350, -1452503605, 419396161]}, display: {Name: '{\"text\":\"Iron Egg\"}'}}}");
		ItemMeta enchantedMeta = enchantedFerrum.getItemMeta();
		enchantedMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.WHITE + "Enchanted Ferrum");
		enchantedFerrum.setItemMeta(enchantedMeta);
		
		return enchantedFerrum;
	}
	
	public static ItemStack getFerrumIngot() {
		ItemStack ferrum = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta ferrumMeta = ferrum.getItemMeta();
		ferrumMeta.setDisplayName(ChatColor.WHITE + "Ferrum Ingot");
		ferrum.setItemMeta(ferrumMeta);
		
		return ferrum;
	}
	
	public static ItemStack getRawFerrumIngot() {
		ItemStack rawFerrum = new ItemStack(Material.RAW_IRON, 1);
		ItemMeta rawFerrumMeta = rawFerrum.getItemMeta();
		rawFerrumMeta.setDisplayName(ChatColor.WHITE + "Raw Ferrum Ingot");
		rawFerrum.setItemMeta(rawFerrumMeta);
		
		return rawFerrum;
	}
	
	public static ItemStack getNova() throws CommandSyntaxException {
		ItemStack nova = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc0MDBlYTE5ZGJkODRmNzVjMzlhZDY4MjNhYzRlZjc4NmYzOWY0OGZjNmY4NDYwMjM2NmFjMjliODM3NDIyIn19fQ==\"}]}, Id: [I; -497262744, 1908819480, -1557314868, 888921618]}, display: {Name: '{\"text\":\"Energy Core\"}'}}}");
		ItemMeta novaMeta = nova.getItemMeta();
		novaMeta.setDisplayName(ChatColor.YELLOW + "Nova");
		nova.setItemMeta(novaMeta);
		
		return nova;
	}
	
	public static ItemStack getIncitatusLeaves() throws CommandSyntaxException {
		ItemStack leaves = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyZWE5NDIzYmRmM2MzM2E5NjEyZGZmY2EwYjQ1ZmIyYTQ3NzkzNWNjYTcyNjM0YmNlYTY5NTFjZmVkNTE3In19fQ==\"}]}, Id: [I; -1350470105, -613332726, -1547509688, -1542169711]}, display: {Name: '{\"text\":\"Wisteria Leaves\"}'}}}");
		ItemMeta leavesMeta = leaves.getItemMeta();
		leavesMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Incitatus Leaves");
		leaves.setItemMeta(leavesMeta);
		
		return leaves;
	}
	
	public static ItemStack getSaxum() throws CommandSyntaxException {
		ItemStack saxum = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyYjVkNDhlNTc1Nzc1NjNhY2EzMTczNTUxOWNiNjIyMjE5YmMwNThiMWYzNDY0OGI2N2I4ZTcxYmMwZmEifX19\"}]}, Id: [I; 411544170, 604653863, -1201111754, 837825911]}, display: {Name: '{\"text\":\"Stone Ball\"}'}}}");
		ItemMeta saxumMeta = saxum.getItemMeta();
		saxumMeta.setDisplayName(ChatColor.GRAY + "Saxum");
		saxum.setItemMeta(saxumMeta);
		
		return saxum;
	}
	
	public static ItemStack getOssum() throws CommandSyntaxException {
		ItemStack ossum = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDY4YjljNjEzMTMxN2NhZTc5ODk4MmJiMDAwZDliNDRiYTk4MWE0YmIwY2YxMjE2NGVjN2Q0ZDA5Yjk0MGM1MCJ9fX0=\"}]}, Id: [I; -137003361, -60601692, -1482376035, -1229799306]}, display: {Name: '{\"text\":\"Tuff\"}'}}}");
		ItemMeta ossumMeta = ossum.getItemMeta();
		ossumMeta.setDisplayName(GeneralUtils.hexToChat("c6bfa3", "Ossum"));
		ossum.setItemMeta(ossumMeta);
		
		return ossum;
	}
	
	public static ItemStack getIncitatusLog() {
		ItemStack incitatusLog = new ItemStack(Material.ANCIENT_DEBRIS, 1);
		ItemMeta incitatusLogMeta = incitatusLog.getItemMeta();
		incitatusLogMeta.setDisplayName(ChatColor.DARK_AQUA + "Incitatus Log");
		incitatusLog.setItemMeta(incitatusLogMeta);
		
		return incitatusLog;
	}
	
	public static ItemStack getCito() throws CommandSyntaxException {
		ItemStack cito = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODA4MjJjNjEzOWFiNDhkNjg1MjMzMjNhOTNjYzJkMjA0M2U2MzRmM2E0MGY2NjRhNGE1MzQ5ZjNjYjdjMmIxOSJ9fX0=\"}]}, Id: [I; -1772556877, 1984712477, -2113045231, 941288179]}, display: {Name: '{\"text\":\"Basalt\"}'}}}");
		ItemMeta citoMeta = cito.getItemMeta();
		citoMeta.setDisplayName(ChatColor.DARK_GRAY + "Cito");
		cito.setItemMeta(citoMeta);
		
		return cito;
	}
	
	public static ItemStack getOpulens() {
		ItemStack opulens = new ItemStack(Material.WARPED_NYLIUM, 1);
		ItemMeta opulensMeta = opulens.getItemMeta();
		opulensMeta.setDisplayName(ChatColor.AQUA + "Opulens");
		opulens.setItemMeta(opulensMeta);
		
		return opulens;
	}
	
	public static ItemStack getChalybs() throws CommandSyntaxException {
		ItemStack chalybs = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRlNzE5YjcyOTA5ZWZhMDk3ODE1YTYzMzgwZjQ0NTZhZjllNGFmZWJkZDg5NGU1YjU4YjdjOWUwNTY3NTU3NyJ9fX0=\"}]}, Id: [I; 662591030, 1631273344, -1703775134, -1075639018]}, display: {Name: '{\"text\":\"Obsidian\"}'}}}");
		ItemMeta chalybsMeta = chalybs.getItemMeta();
		chalybsMeta.setDisplayName(ChatColor.GRAY + "Chalybs");
		chalybs.setItemMeta(chalybsMeta);
		
		return chalybs;
	}
	
	public static ItemStack getExitatus() throws CommandSyntaxException {
		ItemStack exitatus = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", Count: 1b, tag: {SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA4ZDU5Mzc2NmM1NWU0MmI0OTUzYTY4YmY2ZjM0ODI1NTMxODg0MDgxYzE1MGMxNjZjODExMDI3OWM5ZjJhNSJ9fX0=\"}]}, Id: [I; 284203740, 1682000916, -1646723021, -90232381]}, display: {Name: '{\"text\":\"Soul Sand\"}'}}}");
		ItemMeta exitatusMeta = exitatus.getItemMeta();
		exitatusMeta.setDisplayName(GeneralUtils.hexToChat("493a16", "Exitatus"));
		exitatus.setItemMeta(exitatusMeta);
		
		return exitatus;
	}
	
	public static ArrayList<ItemStack> getTitanWorldItems() {
		try {
			ArrayList<ItemStack> titanWorldItems = new ArrayList<ItemStack>();
			
			ItemStack[] items = {
					getOpulensHoe(),
					getPraefortisGen(1),
					getPraefortisGen(2),
					getPraefortisGen(3),
					getPraefortisGen(4),
					getConstibilisGen(),
					getSaxumPickaxe(),
					getRefinedSaxumPickaxe(),
					getOssumPickaxe(),
					getCantoniumPickaxe(),
					getRefinedCantoniumPickaxe(),
					getFerrumPickaxe(),
					getEnchantedFerrumPickaxe(),
					getIncitatusAxe(),
					getNovaAxe(),
					getPraefortisShroom(),
					getPraefortis(),
					getFerrumBlock(),
					getConstibilis(),
					getIabesium(),
					getCantonium(),
					getClarus(),
					getEnchantedFerrum(),
					getFerrumIngot(),
					getRawFerrumIngot(),
					getNova(),
					getIncitatusLeaves(),
					getSaxum(),
					getOssum(),
					getIncitatusLog(),
					getCito(),
					getOpulens(),
					getChalybs(),
					getExitatus()
			};
			titanWorldItems.addAll(Arrays.asList(items));
			
			return titanWorldItems;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static boolean isTitanItem(ItemStack item, TitanType type) {
			if (!(item.hasItemMeta())) return false;
			if (!(item.getItemMeta().hasDisplayName())) return false;
			if (!(item.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS))) return false;
		if (type.equals(TitanType.WEAPON)) {
			if (!(item.getItemMeta().isUnbreakable())) return false;
			return (item.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 30126 && item.getItemMeta().hasItemFlag(ItemFlag.HIDE_PLACED_ON));
		} else if (type.equals(TitanType.MISC)) {
			return (item.getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 26105 && item.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE));
		} else if (type.equals(TitanType.ARMOR)) {
			if (!(item.getItemMeta().isUnbreakable())) return false;
			return (item.getItemMeta().getEnchantLevel(Enchantment.MENDING) == 29 && item.getItemMeta().hasItemFlag(ItemFlag.HIDE_DESTROYS));
		} else return false;
	}
	
	public static ItemStack[] getTitanDimensionWeapons() {
		ItemStack arescent = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta arescentMeta = arescent.getItemMeta();
		arescentMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Titan Arescent");
		arescentMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		arescentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		arescentMeta.addEnchant(Enchantment.DAMAGE_ALL, 325, true);
		arescentMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 275, true);
		arescentMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 275, true);
		arescentMeta.addEnchant(Enchantment.FIRE_ASPECT, 125, true);
		arescentMeta.addEnchant(Enchantment.KNOCKBACK, 25, true);
		arescentMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 25, true);
		arescentMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 75, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		arescentMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 12.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		arescentMeta.setUnbreakable(true);
		
		arescent.setItemMeta(arescentMeta);
		
		ItemStack vivet = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta vivetMeta = vivet.getItemMeta();
		vivetMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Titan Vivet");
		vivetMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		vivetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		vivetMeta.addEnchant(Enchantment.DAMAGE_ALL, 250, true);
		vivetMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 200, true);
		vivetMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 200, true);
		vivetMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		vivetMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 80, true);
		vivetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 100, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		vivetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 80, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		vivetMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 6, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		vivetMeta.setUnbreakable(true);
		
		vivet.setItemMeta(vivetMeta);
		
		ItemStack celer = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta celerMeta = celer.getItemMeta();
		celerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Titan Celer");
		celerMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		celerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		celerMeta.addEnchant(Enchantment.DAMAGE_ALL, 275, true);
		celerMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 225, true);
		celerMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 225, true);
		celerMeta.addEnchant(Enchantment.KNOCKBACK, 35, true);
		celerMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 45, true);
		celerMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		celerMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", 0.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		celerMeta.setUnbreakable(true);
		
		celer.setItemMeta(celerMeta);
		
		ItemStack aribus = new ItemStack(Material.NETHERITE_AXE, 1);
		ItemMeta aribusMeta = aribus.getItemMeta();
		aribusMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Titan Aribus");
		aribusMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		aribusMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		aribusMeta.addEnchant(Enchantment.DAMAGE_ALL, 350, true);
		aribusMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 300, true);
		aribusMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 300, true);
		aribusMeta.addEnchant(Enchantment.FIRE_ASPECT, 150, true);
		aribusMeta.addEnchant(Enchantment.KNOCKBACK, 35, true);
		aribusMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 125, true);
		aribusMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 95, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 35, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 125, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 100, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		aribusMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 7.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		aribusMeta.setUnbreakable(true);
		
		aribus.setItemMeta(aribusMeta);
		ItemStack[] dimWeapons = {
			arescent,
			vivet,
			celer,
			aribus
		};
		
		return dimWeapons;
	}
	
	public static ItemStack getSuperConfiguat() {
		ItemStack confugiat = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta confugiatMeta = confugiat.getItemMeta();
		confugiatMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Super Confugiat");
		
		ArrayList<String> superCLore = new ArrayList<String>();
		superCLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Regular Confugiat, blessed");
		superCLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "and forged in the new");
		superCLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + ChatColor.BOLD + "Titan Dimension" + ChatColor.RESET + "" + ChatColor.GRAY + "" + ChatColor.ITALIC + ".");
		
		confugiatMeta.setLore(superCLore);
		confugiatMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		confugiatMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		confugiatMeta.addEnchant(Enchantment.DAMAGE_ALL, 250, true);
		confugiatMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 400, true);
		confugiatMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 100, true);
		confugiatMeta.addEnchant(Enchantment.FIRE_ASPECT, 35, true);
		confugiatMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 160, true);
		confugiatMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 100, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		confugiatMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 11, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		confugiatMeta.setUnbreakable(true);
		
		confugiat.setItemMeta(confugiatMeta);
		
		return confugiat;
	}
	
	public static ItemStack getSuperChrysos() {
		ItemStack chrysosBlade = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta chrysosBladeMeta = chrysosBlade.getItemMeta();
		chrysosBladeMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Super Chrysos Blade");
		
		ArrayList<String> chrysosLore = new ArrayList<String>();
		chrysosLore.add(ChatColor.GRAY + "The only blade with enough pure");
		chrysosLore.add(ChatColor.GRAY + "gold to damage the " + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Titan" + ChatColor.RESET + ChatColor.GRAY + ",");
		chrysosLore.add(ChatColor.GRAY + "however, this blade was hit with a massive");
		chrysosLore.add(ChatColor.GRAY + "wave of Black Hole Energy, making it");
		chrysosLore.add(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "much more powerful.");
		
		chrysosBladeMeta.setLore(chrysosLore);
		chrysosBladeMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		chrysosBladeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		chrysosBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 400, true);
		chrysosBladeMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 175, true);
		chrysosBladeMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 175, true);
		chrysosBladeMeta.addEnchant(Enchantment.FIRE_ASPECT, 125, true);
		chrysosBladeMeta.addEnchant(Enchantment.KNOCKBACK, 65, true);
		chrysosBladeMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 175, true);
		chrysosBladeMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 125, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		chrysosBladeMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", -0.7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		chrysosBladeMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		chrysosBladeMeta.setUnbreakable(true);
		
		chrysosBlade.setItemMeta(chrysosBladeMeta);
		
		return chrysosBlade;
	}
	
	public static ItemStack getLightningBlade() {
		ItemStack lightningBlade = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta lightningBladeMeta = lightningBlade.getItemMeta();
		lightningBladeMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Lightning Blade");
		lightningBladeMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		lightningBladeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		lightningBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 225, true);
		lightningBladeMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 150, true);
		lightningBladeMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 150, true);
		lightningBladeMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
		lightningBladeMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 50, true);
		lightningBladeMeta.setUnbreakable(true);
		
		lightningBlade.setItemMeta(lightningBladeMeta);
		
		return lightningBlade;
	}
	
	public static ItemStack getTitanGhastSword() {
		ItemStack ghastS = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta ghastSMeta = ghastS.getItemMeta();
		ghastSMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Titan Ghast Sword");
		ghastSMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		ghastSMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		ghastSMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
		ghastSMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 325, true);
		ghastSMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 200, true);
		ghastSMeta.addEnchant(Enchantment.FIRE_ASPECT, 250, true);
		ghastSMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		ghastSMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 35, true);
		ghastSMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 35, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		ghastSMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 23, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		ghastSMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH",-0.8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		ghastSMeta.setUnbreakable(true);
		
		ghastS.setItemMeta(ghastSMeta);
		
		return ghastS;
	}
	
	public static ItemStack getInfernoLightsaber() {
		ItemStack lightsaber = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta lightsaberMeta = lightsaber.getItemMeta();
		lightsaberMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Inferno Lightsaber");
		lightsaberMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		lightsaberMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		lightsaberMeta.addEnchant(Enchantment.DAMAGE_ALL, 250, true);
		lightsaberMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 150, true);
		lightsaberMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 150, true);
		lightsaberMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
		lightsaberMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		lightsaberMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 100, true);
		lightsaberMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		lightsaberMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "GENERIC_LUCK", 11, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		lightsaberMeta.setUnbreakable(true);
		
		lightsaber.setItemMeta(lightsaberMeta);
		
		return lightsaber;
	}
	
	public static ItemStack getBlackHoleBlade() {
		ItemStack bhBlade = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta bhBladeMeta = bhBlade.getItemMeta();
		bhBladeMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Black Hole Blade");
		bhBladeMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		bhBladeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		
		ArrayList<String> bhLore = new ArrayList<String>();
		bhLore.add("");
		bhLore.add(ChatColor.GRAY + "Long ago, a Black Hole appeared");
		bhLore.add(ChatColor.GRAY + "in The End. You may know that");
		bhLore.add(ChatColor.GRAY + "story already. What you might not");
		bhLore.add(ChatColor.GRAY + "know, is that the scythe was cloned");
		bhLore.add(ChatColor.GRAY + "by the titans, and refurbished to meet");
		bhLore.add(ChatColor.GRAY + "their expectations.");
		
		bhBladeMeta.setLore(bhLore);
		bhBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 425, true);
		bhBladeMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 325, true);
		bhBladeMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 325, true);
		bhBladeMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 175, true);
		bhBladeMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 105, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		bhBladeMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 55, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		bhBladeMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_SPEED", 40, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		bhBladeMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 150, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		bhBladeMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 125, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		bhBladeMeta.setUnbreakable(true);
		
		bhBlade.setItemMeta(bhBladeMeta);
		
		return bhBlade;
	}
	
	public static ItemStack getAmethystus() {
		ItemStack amethystus = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta amethystusMeta = amethystus.getItemMeta();
		amethystusMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Amethystus");
		amethystusMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		amethystusMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
		amethystusMeta.addEnchant(Enchantment.DAMAGE_ALL, 250, true);
		amethystusMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 125, true);
		amethystusMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 125, true);
		amethystusMeta.addEnchant(Enchantment.KNOCKBACK, 25, true);
		amethystusMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 200, true);
		amethystusMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 12.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		amethystusMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "GENERIC_LUCK", 29, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		amethystusMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "GENERIC_LUCK", 29, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		amethystusMeta.setUnbreakable(true);
		
		amethystus.setItemMeta(amethystusMeta);
		
		return amethystus;
	}
	
	public static ItemStack getInfernoBlade() {
		ItemStack inferno = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta infernoMeta = inferno.getItemMeta();
		infernoMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Inferno Blade");
		
		ArrayList<String> infernoLore = new ArrayList<String>();
		infernoLore.add(ChatColor.GRAY + "Forged from the surface of");
		infernoLore.add(ChatColor.GRAY + "the sun, can set anyone on");
		infernoLore.add(ChatColor.GRAY + "fire, " + ChatColor.GOLD + "" + ChatColor.BOLD + "permanently" + ChatColor.RESET + ChatColor.GRAY + ".");
		
		infernoMeta.setLore(infernoLore);
		infernoMeta.addEnchant(Enchantment.DURABILITY, 30126, true);
		infernoMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ATTRIBUTES);
		infernoMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
		infernoMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 250, true);
		infernoMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 200, true);
		infernoMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
		infernoMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		infernoMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 55, true);
		infernoMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 55, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		infernoMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH",-0.6, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		infernoMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH",-0.6, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		infernoMeta.setUnbreakable(true);
		
		inferno.setItemMeta(infernoMeta);
		
		return inferno;
	}
	
	public static ArrayList<ItemStack> getTitanWeapons() {
		ArrayList<ItemStack> titanWeapons = new ArrayList<ItemStack>();
		
		for (ItemStack i : getTitanDimensionWeapons()) {
			titanWeapons.add(i);
		}
		
		titanWeapons.add(getSuperConfiguat());
		titanWeapons.add(getSuperChrysos());
		titanWeapons.add(getLightningBlade());
		titanWeapons.add(getTitanGhastSword());
		titanWeapons.add(getInfernoLightsaber());
		titanWeapons.add(getBlackHoleBlade());
		titanWeapons.add(getAmethystus());
		titanWeapons.add(getInfernoBlade());
		
		titanWeapons.add(WeaponFetcher.getArcherPerussi());
		titanWeapons.add(WeaponFetcher.getDragonCounter());
		titanWeapons.add(WeaponFetcher.getWitherCounter());
		titanWeapons.add(WeaponFetcher.getSpiderCounter());
		
		return titanWeapons;
	}
	
	// Armor
	

	protected static ItemStack getCustomSkull(String url) {
	
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
	}
	
	public static ItemStack[] getDecaySet() {
		ItemStack decayHelmet = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjQ5NTM1YTI3NmFhY2M0ZGM4NDEzM2JmZTgxYmU1ZjJhNDc5OWE0YzA0ZDlhNGRkYjcyZDgxOWVjMmIyYiJ9fX0=");
		SkullMeta helmetMeta = (SkullMeta) decayHelmet.getItemMeta(); 
		
		helmetMeta.setDisplayName(GeneralUtils.hexToChat("212121", "Decay Helmet"));
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 275, true);
		helmetMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		helmetMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		helmetMeta.addEnchant(Enchantment.OXYGEN, 50, true);
		helmetMeta.addEnchant(Enchantment.MENDING, 29, true);
		helmetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
		helmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));
		helmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));
		helmetMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEATH", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		
		decayHelmet.setItemMeta(helmetMeta);
		
		ItemStack decayChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) decayChestplate.getItemMeta(); 
		
		chestplateMeta.setDisplayName(GeneralUtils.hexToChat("212121", "Decay Chestplate"));
		chestplateMeta.setColor(Color.fromRGB(25, 48, 74));
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 275, true);
		chestplateMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		chestplateMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		chestplateMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
		chestplateMeta.addEnchant(Enchantment.MENDING, 29, true);
		chestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE);
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		
		decayChestplate.setItemMeta(chestplateMeta);
		
		ItemStack decayLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) decayLeggings.getItemMeta(); 
		
		leggingsMeta.setDisplayName(GeneralUtils.hexToChat("212121", "Decay Leggings"));
		leggingsMeta.setColor(Color.fromRGB(25, 48, 74));
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 275, true);
		leggingsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		leggingsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		leggingsMeta.addEnchant(Enchantment.MENDING, 29, true);
		leggingsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE);
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		
		decayLeggings.setItemMeta(leggingsMeta);
		
		ItemStack decayBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) decayBoots.getItemMeta(); 
		
		bootsMeta.setDisplayName(GeneralUtils.hexToChat("212121", "Decay Boots"));
		bootsMeta.setColor(Color.fromRGB(25, 48, 74));
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 275, true);
		bootsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		bootsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
		bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 225, true);
		bootsMeta.addEnchant(Enchantment.MENDING, 29, true);
		bootsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE);
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 8, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
		
		decayBoots.setItemMeta(bootsMeta);
		ItemStack[] decaySet = {
				decayHelmet,
				decayChestplate,
				decayLeggings,
				decayBoots
		};
		
		return decaySet;
	}
	
	public static ItemStack[] getInfernoNetheriteSet() {
		ArrayList<String> infernoLore = new ArrayList<String>();
		infernoLore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Full Set Bonus:" + ChatColor.RESET + ChatColor.GRAY + " Fire and Lava");
		infernoLore.add(ChatColor.GRAY + "do not damage you at all.");
		
		ItemStack infernoHelmet = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta helmetMeta = infernoHelmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.DARK_RED + "Inferno Netherite Helmet");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 300, true);
		helmetMeta.setLore(infernoLore);
		helmetMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		helmetMeta.addEnchant(Enchantment.MENDING, 29, true);
		helmetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
		helmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		helmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		
		infernoHelmet.setItemMeta(helmetMeta);
		
		ItemStack infernoChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta chestplateMeta = infernoChestplate.getItemMeta();
		chestplateMeta.setDisplayName(ChatColor.DARK_RED + "Inferno Netherite Chestplate");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 300, true);
		chestplateMeta.setLore(infernoLore);
		chestplateMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		chestplateMeta.addEnchant(Enchantment.MENDING, 29, true);
		chestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 80, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 80, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		
		infernoChestplate.setItemMeta(chestplateMeta);
		
		ItemStack infernoLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta leggingsMeta = infernoLeggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.DARK_RED + "Inferno Netherite Leggings");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 300, true);
		leggingsMeta.setLore(infernoLore);
		leggingsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		leggingsMeta.addEnchant(Enchantment.MENDING, 29, true);
		leggingsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 70, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 70, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		
		infernoLeggings.setItemMeta(leggingsMeta);
		
		ItemStack infernoBoots = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta bootsMeta = infernoBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.DARK_RED + "Inferno Netherite Boots");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 300, true);
		bootsMeta.setLore(infernoLore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
		bootsMeta.addEnchant(Enchantment.MENDING, 29, true);
		bootsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 40, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 40, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
		
		infernoBoots.setItemMeta(bootsMeta);
		
		ItemStack[] infernoNetherite = {
			infernoHelmet,
			infernoChestplate,
			infernoLeggings,
			infernoBoots
		};
		
		return infernoNetherite;
	}
	
	public static ArrayList<ItemStack[]> getTitanArmors() {
		ArrayList<ItemStack[]> titanArmors = new ArrayList<ItemStack[]>();
		
		titanArmors.add(getDecaySet());
		titanArmors.add(getInfernoNetheriteSet());
		
		return titanArmors;
	}
}