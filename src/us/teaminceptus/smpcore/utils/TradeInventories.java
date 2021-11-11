package us.teaminceptus.smpcore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.listeners.GUIManagers;

public class TradeInventories {
	
	public static ItemStack generateTrade(Material mat, String trade, String cost, String reward) {
		ItemStack tradeItem = new ItemStack(mat, 1);
		ItemMeta tradeMeta = tradeItem.getItemMeta();
		
		tradeMeta.setDisplayName(trade + " Trade");
		tradeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
		
		ArrayList<String> tradeLore = new ArrayList<String>();
		tradeLore.add("");
		tradeLore.add(ChatColor.DARK_GRAY + "Trade: " + cost);
		tradeLore.add(ChatColor.DARK_GRAY + "For: " + reward);
		tradeLore.add("");
		
		tradeMeta.setLore(tradeLore);
		tradeItem.setItemMeta(tradeMeta);
		
		return tradeItem;
	}
	
	public static ItemStack getPageArrow(int page) {
		ItemStack pageArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowMeta = pageArrow.getItemMeta();
		
		arrowMeta.setDisplayName(ChatColor.AQUA + "Page " + Integer.toString(page));
		arrowMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		pageArrow.setItemMeta(arrowMeta);
		
		return pageArrow;
	}
	
	public static Inventory getPage1() {
		Inventory tradeMenu1 = GUIManagers.generateGUI(54, ChatColor.RED + "SMP Trades Menu - Page 1");
		
		ItemStack stringTrade = generateTrade(Material.STRING, ChatColor.WHITE + "String", ChatColor.WHITE + "String x3", ChatColor.WHITE + "Cobweb x1");
		ItemStack fireTrade = generateTrade(Material.FIRE_CHARGE, ChatColor.GOLD + "Fire Charge", ChatColor.GOLD + "Blaze-Rod x1", ChatColor.GOLD + "Fire-Charge x1");
		
		// Setting
		tradeMenu1.setItem(10, stringTrade);
		tradeMenu1.setItem(11, fireTrade);
		
		tradeMenu1.setItem(47, getPageArrow(1));
		tradeMenu1.setItem(48, getPageArrow(2));
		tradeMenu1.setItem(49, getPageArrow(3));
		tradeMenu1.setItem(50, getPageArrow(4));
		tradeMenu1.setItem(51, getPageArrow(5));
		
		return tradeMenu1;
	}
	
	public static Inventory getPage2() {
		Inventory tradeMenu2 = GUIManagers.generateGUI(54, ChatColor.RED + "SMP Trades Menu - Page 2");
		
		ItemStack ironTrade = generateTrade(Material.IRON_INGOT, ChatColor.WHITE + "Iron-Ingot", ChatColor.GRAY + "Coal x8", ChatColor.WHITE + "Iron-Ingot x1");
		ItemStack goldTrade = generateTrade(Material.GOLD_INGOT, ChatColor.GOLD + "Gold-Ingot", ChatColor.WHITE + "Iron-Ingot x8", ChatColor.GOLD + "Gold-Ingot x1");
		ItemStack diamondTrade1 = generateTrade(Material.DIAMOND, ChatColor.AQUA + "Diamond", ChatColor.GOLD + "Gold-Ingot x24", ChatColor.AQUA + "Diamond x1");
		ItemStack diamondTrade2 = generateTrade(Material.DIAMOND, ChatColor.AQUA + "Diamond", ChatColor.GREEN + "Emerald x16", ChatColor.AQUA + "Diamond x1");
		ItemStack diamondTrade3 = generateTrade(Material.DIAMOND, ChatColor.AQUA + "Diamond", ChatColor.LIGHT_PURPLE + "Amethyst-Shard x16", ChatColor.AQUA + "Diamond x1");
		ItemStack netheriteTrade = generateTrade(Material.NETHERITE_INGOT, ChatColor.GRAY + "Netherite-Ingot", ChatColor.AQUA + "Diamond-Block x10", ChatColor.GRAY + "Netherite-Ingot x1");
		
		// Setting
		tradeMenu2.setItem(10, ironTrade);
		tradeMenu2.setItem(11, goldTrade);
		tradeMenu2.setItem(12, diamondTrade1);
		tradeMenu2.setItem(13, diamondTrade2);
		tradeMenu2.setItem(14, diamondTrade3);
		tradeMenu2.setItem(15, netheriteTrade);
		
		tradeMenu2.setItem(47, getPageArrow(1));
		tradeMenu2.setItem(48, getPageArrow(2));
		tradeMenu2.setItem(49, getPageArrow(3));
		tradeMenu2.setItem(50, getPageArrow(4));
		tradeMenu2.setItem(51, getPageArrow(5));
		
		return tradeMenu2;
	}
	
	public static Inventory getPage3() {
		Inventory tradeMenu3 = GUIManagers.generateGUI(54, ChatColor.RED + "SMP Trades Menu - Page 3");
		
		ItemStack netherGoldTrade = generateTrade(Material.NETHER_GOLD_ORE, ChatColor.GOLD + "Nether Gold Ore", ChatColor.RED + "Netherrack x8", ChatColor.GOLD + "Nether-Gold-Ore x1");
		ItemStack stoneTrade1 = generateTrade(Material.ANDESITE, ChatColor.GRAY + "Stone", ChatColor.GRAY + "Andesite x4", ChatColor.GRAY + "Stone x4");
		ItemStack stoneTrade2 = generateTrade(Material.DIORITE, ChatColor.GRAY + "Stone", ChatColor.WHITE + "Diorite x4", ChatColor.GRAY + "Stone x4");
		ItemStack stoneTrade3 = generateTrade(Material.GRANITE, ChatColor.GRAY + "Stone", ChatColor.RED + "Granite x4", ChatColor.GRAY + "Stone x4");
		ItemStack stoneTrade4 = generateTrade(Material.CALCITE, ChatColor.GRAY + "Stone", ChatColor.WHITE + "Calcite x4", ChatColor.GRAY + "Stone x4");
		ItemStack soulSoilTrade = generateTrade(Material.SOUL_SOIL, ChatColor.DARK_GRAY + "Soul Soil", ChatColor.DARK_GRAY + "Soul-Sand x2", ChatColor.DARK_GRAY + "Soul-Soil x2");
		ItemStack slimeTrade = generateTrade(Material.SLIME_BLOCK, ChatColor.GREEN + "Slime Block", ChatColor.GOLD + "Honey-Block x2", ChatColor.GREEN + "Slime-Block x2");
		ItemStack honeyTrade = generateTrade(Material.HONEY_BLOCK, ChatColor.GOLD + "Honey Block", ChatColor.GREEN + "Slime-Block x2", ChatColor.GOLD + "Honey-Block x2");
		// Setting
		tradeMenu3.setItem(10, netherGoldTrade);
		tradeMenu3.setItem(11, stoneTrade1);
		tradeMenu3.setItem(12, stoneTrade2);
		tradeMenu3.setItem(13, stoneTrade3);
		tradeMenu3.setItem(14, stoneTrade4);
		tradeMenu3.setItem(15, soulSoilTrade);
		tradeMenu3.setItem(16, slimeTrade);
		
		tradeMenu3.setItem(19, honeyTrade);
		
		tradeMenu3.setItem(47, getPageArrow(1));
		tradeMenu3.setItem(48, getPageArrow(2));
		tradeMenu3.setItem(49, getPageArrow(3));
		tradeMenu3.setItem(50, getPageArrow(4));
		tradeMenu3.setItem(51, getPageArrow(5));
		return tradeMenu3;
	}
	
	public static Inventory getPage4() {
		Inventory tradeMenu4 = GUIManagers.generateGUI(54, ChatColor.RED + "SMP Trades Menu - Page 4");
		
		ItemStack rawPorkTrade = generateTrade(Material.PORKCHOP, ChatColor.LIGHT_PURPLE + "Raw Porkchop", ChatColor.WHITE + "Cooked-Porkchop x4", ChatColor.LIGHT_PURPLE + "Porkchop x4");
		ItemStack rawBeefTrade = generateTrade(Material.BEEF, ChatColor.RED + "Raw Beef", ChatColor.DARK_RED + "Cooked-Beef x4", ChatColor.RED + "Beef x4");
		ItemStack rawMuttonTrade = generateTrade(Material.MUTTON, ChatColor.RED + "Raw Mutton", ChatColor.RED + "Cooked-Mutton x4", ChatColor.LIGHT_PURPLE + "Mutton x4");
		ItemStack rawChickenTrade = generateTrade(Material.CHICKEN, ChatColor.YELLOW + "Raw Chicken", ChatColor.GOLD + "Cooked-Chicken x4", ChatColor.YELLOW + "Chicken x4");
		ItemStack rawRabbitTrade = generateTrade(Material.RABBIT, ChatColor.YELLOW + "Raw Rabbit", ChatColor.GOLD + "Cooked-Rabbit x4", ChatColor.YELLOW + "Rabbit x4");
		ItemStack rawBeefTrade2 = generateTrade(Material.BEEF, ChatColor.RED + "Raw Beef", ChatColor.DARK_RED + "Rotten-Flesh x8", ChatColor.RED + "Beef x1");
		ItemStack appleTrade = generateTrade(Material.APPLE, ChatColor.RED + "Apples", ChatColor.GREEN + "Oak-Leaves x8", ChatColor.RED + "Apple x1");
		
		// Setting
		tradeMenu4.setItem(10, rawPorkTrade);
		tradeMenu4.setItem(11, rawBeefTrade);
		tradeMenu4.setItem(12, rawMuttonTrade);
		tradeMenu4.setItem(13, rawChickenTrade);
		tradeMenu4.setItem(14, rawRabbitTrade);
		tradeMenu4.setItem(15, rawBeefTrade2);
		tradeMenu4.setItem(16, appleTrade);
		
		tradeMenu4.setItem(47, getPageArrow(1));
		tradeMenu4.setItem(48, getPageArrow(2));
		tradeMenu4.setItem(49, getPageArrow(3));
		tradeMenu4.setItem(50, getPageArrow(4));
		tradeMenu4.setItem(51, getPageArrow(5));
		
		return tradeMenu4;
	}
	
	public static Inventory getPage5() {
		Inventory tradeMenu5 = GUIManagers.generateGUI(54, ChatColor.RED + "SMP Trades Menu - Page 5");
		
		ItemStack boneTrade = generateTrade(Material.BONE, ChatColor.WHITE + "Bone", ChatColor.WHITE + "Bone-Meal x3", ChatColor.WHITE + "Bone x1");
		ItemStack witherSkullTrade = generateTrade(Material.WITHER_SKELETON_SKULL, ChatColor.DARK_GRAY + "Wither-Skeleton-Skull", ChatColor.DARK_GRAY + "Wither-Rose x8", ChatColor.DARK_GRAY + "Wither-Skeleton-Skull x1");
		ItemStack hideTrade = generateTrade(Material.RABBIT_HIDE, ChatColor.WHITE + "Rabbit Hide", ChatColor.WHITE + "Leather x2", ChatColor.WHITE + "Rabbit-Hide x8");
		ItemStack stickTrade = generateTrade(Material.BAMBOO, ChatColor.GREEN + "Bamboo", ChatColor.WHITE + "Stick x2", ChatColor.GREEN + "Bamboo x4");
		ItemStack prismarineTrade1 = generateTrade(Material.PRISMARINE_SHARD, ChatColor.DARK_AQUA + "Prismarine Shard", ChatColor.AQUA + "Prismarine-Crystals x2", ChatColor.DARK_AQUA + "Prismarine-Shard x2");
		ItemStack prismarineTrade2 = generateTrade(Material.PRISMARINE_CRYSTALS, ChatColor.AQUA + "Prismarine Crystals", ChatColor.DARK_AQUA + "Prismarine-Shard x2", ChatColor.AQUA + "Prismarine-Crystals x2");
		// Setting
		tradeMenu5.setItem(10, boneTrade);
		tradeMenu5.setItem(11, witherSkullTrade);
		tradeMenu5.setItem(12, hideTrade);
		tradeMenu5.setItem(13, stickTrade);
		tradeMenu5.setItem(14, prismarineTrade1);
		tradeMenu5.setItem(15, prismarineTrade2);
		
		tradeMenu5.setItem(47, getPageArrow(1));
		tradeMenu5.setItem(48, getPageArrow(2));
		tradeMenu5.setItem(49, getPageArrow(3));
		tradeMenu5.setItem(50, getPageArrow(4));
		tradeMenu5.setItem(51, getPageArrow(5));
		
		return tradeMenu5;
	}
	
	public static List<Inventory> getPages() {
		Inventory[] invs = {
				getPage1(),
				getPage2(),
				getPage3(),
				getPage4(),
				getPage5()
		};
		
		List<Inventory> inventories = Arrays.asList(invs);
		
		return inventories;
	}
	
}
