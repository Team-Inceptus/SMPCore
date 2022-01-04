package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;

public class Boss implements CommandExecutor {
	
	public SMPCore plugin;
	
	public Boss(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("boss").setExecutor(this);
	}
	
	public static ItemStack generateItem(Material mat, String label, ChatColor labelColor, int tier) {
		if (tier > 5) tier = 5;
		ItemStack item = new ItemStack(mat, 1);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);

		itemMeta.setDisplayName(labelColor + "" + label + " " + ChatColor.GRAY + "(T" + Integer.toString(tier) + " Boss)");
		
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getComingSoon() {
		ItemStack comingSoon = new ItemStack(Material.COAL_BLOCK, 1);
		ItemMeta soonMeta = comingSoon.getItemMeta();
		soonMeta.setDisplayName(ChatColor.GRAY + "Coming Soon!");
		
		ArrayList<String> soonLore = new ArrayList<String>();
		
		soonLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "This boss will come out");
		soonLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "at a later date.");
		
		soonMeta.setLore(soonLore);
		
		comingSoon.setItemMeta(soonMeta);
		
		return comingSoon;
	}
	
	public static ItemStack getTitanSummoner() {
		ItemStack titanSummoner = new ItemStack(Material.GOLD_BLOCK, 1);
		ItemMeta titanMeta = titanSummoner.getItemMeta();
		titanMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner");
		
		ArrayList<String> titanLore = new ArrayList<String>();
		
		titanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "This item can be used");
		titanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "to summon a " + ChatColor.RED + "titan boss" + ChatColor.DARK_GRAY + ".");
		
		titanMeta.setLore(titanLore);
		
		titanMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		titanMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		
		titanSummoner.setItemMeta(titanMeta);
		
		return titanSummoner;
	}
	
	// T5 Boss Inventory
	public static void openEliteBosses(Player p) {
		// ItemStack comingSoon = getComingSoon();
		Inventory eliteBossGUI = GUIManagers.generateGUI(54, ChatColor.DARK_AQUA + "" +ChatColor.BOLD + "SMP T5 Bosses Menu");
		
		ItemStack goldShulker = generateItem(Material.SHULKER_SHELL, ChatColor.BOLD + "Golden Shulker", ChatColor.GOLD, 5);
		
		ArrayList<String> goldLore = new ArrayList<String>();
		
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.RED + "25,000 HP");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Golden Apple" + ChatColor.DARK_GRAY + " x32");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.GOLD + "Midas Touch");
		goldLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "On teleport, sets the block below itself to ");
		goldLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "gold, with the exception of a few unbreakables.");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + "Super Shulker");
		goldLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Levitation II" + ChatColor.GRAY + " spikes.");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.RED + "Spontaneous Combustion");
		goldLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "50%" + ChatColor.GRAY + " chance to cause a tnt-sized explosion");
		goldLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "when hit.");
		goldLore.add("");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		goldLore.add("");
		goldLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Gold Block" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		goldLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Patina's Gold Chestplate" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (5%)");
		goldLore.add(ChatColor.RESET + "- " + ChatColor.DARK_PURPLE + "Shulker Shell" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (100%)");
		goldLore.add(ChatColor.RESET + "- " + ChatColor.LIGHT_PURPLE + "Enchanted Golden Apple" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (50%)");
		
		ItemMeta goldMeta = goldShulker.getItemMeta();
		goldMeta.setLore(goldLore);
		goldShulker.setItemMeta(goldMeta);
		
		ItemStack pillagerKing = generateItem(Material.CROSSBOW, ChatColor.BOLD + "Pillager King", ChatColor.GREEN, 5);
		
		ArrayList<String> kingLore = new ArrayList<String>();
		
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.RED + "30,000 HP (10,000 HP Ravager; 20,000 HP Normal)");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Arrow" + ChatColor.DARK_GRAY + " x256");
		kingLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Emerald" + ChatColor.DARK_GRAY + " x48");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.DARK_AQUA + "Advanced Crossbow");
		kingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Wields the Praedo Crossbow.");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.DARK_RED + "Trainer");
		kingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Rides a " + ChatColor.RED + "mutant ravager" + ChatColor.GRAY + ".");
		kingLore.add("");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		kingLore.add("");
		kingLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Emerald Block" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		kingLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Praedo" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		kingLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Ucide's Boots" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (50%)");
		
		ItemMeta kingMeta = pillagerKing.getItemMeta();
		kingMeta.setLore(kingLore);
		pillagerKing.setItemMeta(kingMeta);
		
		ItemStack phantomKing = generateItem(Material.ELYTRA, ChatColor.BOLD + "Phantom King", ChatColor.DARK_GRAY, 5);
		
		ArrayList<String> phLore = new ArrayList<String>();
		
		phLore.add("");
		phLore.add(ChatColor.RESET + "" + ChatColor.RED + "15,000 HP");
		phLore.add("");
		phLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		phLore.add("");
		phLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "Elytra" + ChatColor.DARK_GRAY + " x1");
		phLore.add("");
		phLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		phLore.add("");
		phLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Royalty");
		phLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "80%" + ChatColor.GRAY + " chance to spawn a phantom when hit.");
		phLore.add("");
		phLore.add("");
		phLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		phLore.add("");
		phLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "Phantom Membrane" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		phLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Phantom Leggings" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		phLore.add(ChatColor.RESET + "- " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Tenuem" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		
		ItemMeta phMeta = phantomKing.getItemMeta();
		phMeta.setLore(phLore);
		phantomKing.setItemMeta(phMeta);
		
		ItemStack emeraldWarrior = generateItem(Material.EMERALD_BLOCK, ChatColor.BOLD + "Emerald Warrior", ChatColor.DARK_GREEN, 5);
		
		ArrayList<String> eLore = new ArrayList<String>();
		
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.RED + "55,000 HP (25,000 HP Giant; 30,000 HP Normal)");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		eLore.add("");
		eLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Emerald" + ChatColor.DARK_GRAY + " x48");
		eLore.add("");
		eLore.add(ChatColor.GRAY + "Worshipped by illagers,");
		eLore.add(ChatColor.GRAY + "said to have came from a");
		eLore.add(ChatColor.GREEN + "whole new dimension" + ChatColor.GRAY + ", where");
		eLore.add(ChatColor.GRAY + "there is a whole new");
		eLore.add(ChatColor.GRAY + "expereince of bosses, mobs,");
		eLore.add(ChatColor.GRAY + "and more. Only the strongest");
		eLore.add(ChatColor.GRAY + "and smartest can survive this boss.");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + ChatColor.MAGIC + "Royalty");
		eLore.add(ChatColor.RESET + "" + ChatColor.GREEN + ChatColor.MAGIC + "100%" + ChatColor.GRAY + ChatColor.MAGIC + " chance to spawn a zombie when hit.");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.GREEN + ChatColor.MAGIC + "Emeralds");
		eLore.add(ChatColor.RESET + "" + ChatColor.GREEN + ChatColor.MAGIC + "100%" + ChatColor.GRAY + ChatColor.MAGIC + " emeralds and more and more emeralds.");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.AQUA + ChatColor.MAGIC + "Blue Emeralds");
		eLore.add(ChatColor.RESET + "" + ChatColor.GREEN + ChatColor.MAGIC + "100%" + ChatColor.GRAY + ChatColor.MAGIC + " blue and more blue emeralds. Very blue.");
		eLore.add("");
		eLore.add("");
		eLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		eLore.add("");
		eLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + ChatColor.MAGIC + "Emerald Block" + ChatColor.DARK_GRAY + " x64" + ChatColor.GRAY + " (100%)");
		eLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.MAGIC + "Zmaragdus Ope" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		eLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + ChatColor.MAGIC + "Emerald Enriched Chestplate" + ChatColor.DARK_GRAY + ChatColor.MAGIC + " x1" + ChatColor.GRAY + " (100%)");
		
		ItemMeta eMeta = emeraldWarrior.getItemMeta();
		eMeta.setLore(eLore);
		emeraldWarrior.setItemMeta(eMeta);
		
		ItemStack creeperKing = generateItem(Material.TNT, ChatColor.BOLD + "Creeper King", ChatColor.GREEN, 5);
		
		ArrayList<String> cLore = new ArrayList<String>();
		
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.RED + "50,000 HP");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		cLore.add("");
		cLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "TNT" + ChatColor.DARK_GRAY + " x64");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Royalty");
		cLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "80%" + ChatColor.GRAY + " chance to spawn a creeper when hit.");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.RED + "Explosive");
		cLore.add(ChatColor.RESET + "" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Very destructive.");
		cLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "When explodes, goes back to " + ChatColor.RED + "10,000 HP" + ChatColor.GRAY + ".");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.RED + "Ex-Nether Biologist");
		cLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Rides a " + ChatColor.RED + "powerful" + ChatColor.GRAY + " ghast.");
		cLore.add("");
		cLore.add("");
		cLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		cLore.add("");
		cLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "Gunpowder" + ChatColor.DARK_GRAY + " x256" + ChatColor.GRAY + " (100%)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Random Music Discs" + ChatColor.DARK_GRAY + " x3" + ChatColor.GRAY + " (100%)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Creeper Head" + ChatColor.DARK_GRAY + " x16" + ChatColor.GRAY + " (100%)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Gunpowder" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.RED + "TNT" + ChatColor.DARK_GRAY + " x160" + ChatColor.GRAY + " (100%)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Essence of Trinitrotoluene" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (25% | True Chance)");
		cLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "" + ChatColor.BOLD + "Creeper Handle" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (50% | True Chance)");
		
		
		ItemMeta cMeta = creeperKing.getItemMeta();
		cMeta.setLore(cLore);
		creeperKing.setItemMeta(cMeta);
		
		ItemStack damageTitan = generateItem(Material.NETHERITE_SWORD, ChatColor.BOLD + "Damage Titan", ChatColor.DARK_RED, 5);
		
		ArrayList<String> damageLore = new ArrayList<String>();
		
		damageLore.add("");
		damageLore.add(ChatColor.RESET + "" + ChatColor.RED + "50 Titan HP");
		damageLore.add("");
		damageLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		damageLore.add("");
		damageLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner" + ChatColor.DARK_GRAY + " x1");
		damageLore.add("");
		damageLore.add(ChatColor.GRAY + "One of the 5 titans,");
		damageLore.add(ChatColor.GRAY + "mysterious things that forged");
		damageLore.add(ChatColor.GRAY + "and can only be damaged by");
		damageLore.add(ChatColor.GRAY + "their weapon counterparts,");
		damageLore.add(ChatColor.GRAY + "and their god-blessed superiors.");
		damageLore.add("");
		damageLore.add(ChatColor.GRAY + "Each counterpart weapon");
		damageLore.add(ChatColor.GRAY + "Does a " + ChatColor.RED + "titan damage," + ChatColor.GRAY + " and");
		damageLore.add(ChatColor.GRAY + "it varies based on the superior god weapon.");
		
		ItemMeta damageMeta = damageTitan.getItemMeta();
		damageMeta.setLore(damageLore);
		damageTitan.setItemMeta(damageMeta);
		
		ItemStack speedTitan = generateItem(Material.IRON_BOOTS, ChatColor.BOLD + "Speed Titan", ChatColor.AQUA, 5);
		
		ArrayList<String> speedLore = new ArrayList<String>();
		
		speedLore.add("");
		speedLore.add(ChatColor.RESET + "" + ChatColor.RED + "100 Titan HP");
		speedLore.add("");
		speedLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		speedLore.add("");
		speedLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner" + ChatColor.DARK_GRAY + " x1");
		speedLore.add("");
		speedLore.add(ChatColor.GRAY + "One of the 5 titans,");
		speedLore.add(ChatColor.GRAY + "mysterious things that forged");
		speedLore.add(ChatColor.GRAY + "and can only be damaged by");
		speedLore.add(ChatColor.GRAY + "their weapon counterparts,");
		speedLore.add(ChatColor.GRAY + "and their god-blessed superiors.");
		speedLore.add("");
		speedLore.add(ChatColor.GRAY + "Each counterpart weapon");
		speedLore.add(ChatColor.GRAY + "Does a " + ChatColor.RED + "titan damage," + ChatColor.GRAY + " and");
		speedLore.add(ChatColor.GRAY + "it varies based on the superior god weapon.");
		
		ItemMeta speedMeta = speedTitan.getItemMeta();
		speedMeta.setLore(speedLore);
		speedTitan.setItemMeta(speedMeta);
		
		ItemStack aranea = generateItem(Material.COBWEB, ChatColor.BOLD + "Aranea", ChatColor.DARK_RED, 5);
		
		ArrayList<String> aLore = new ArrayList<String>();
		
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.RED + "60,000 HP");
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		aLore.add("");
		aLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Cobweb" + ChatColor.DARK_GRAY + " x64");
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.RED + "Vigilante");
		aLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Agile; Immune to projectiles and fire.");
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.DARK_RED + "Venomous");
		aLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "When hit, gain " + ChatColor.GREEN + "Poison IV" + ChatColor.GRAY + ",");
		aLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "75%" + ChatColor.GRAY + " chance for " + ChatColor.DARK_GRAY + "Wither V" + ChatColor.GRAY + ",");
		aLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "and " + ChatColor.GREEN + "50%" + ChatColor.GRAY + " chance for " + ChatColor.DARK_GREEN + "Hunger VII" + ChatColor.GRAY + ".");
		aLore.add("");
		aLore.add("");
		aLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		aLore.add("");
		aLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "String" + ChatColor.DARK_GRAY + " x320" + ChatColor.GRAY + " (100%)");
		aLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "" + ChatColor.BOLD + "Spider Sericum" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		aLore.add(ChatColor.RESET + "- " + ChatColor.RED + "Fermented Spider Eye" + ChatColor.DARK_GRAY + " x160" + ChatColor.GRAY + " (100%)");
		aLore.add(ChatColor.RESET + "- " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Chordate Sword" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.6%)");
		aLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Saliant Boots" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (5%)");
		
		
		ItemMeta aMeta = aranea.getItemMeta();
		aMeta.setLore(aLore);
		aranea.setItemMeta(aMeta);
		
		ItemStack golemKing = generateItem(Material.IRON_BLOCK, ChatColor.BOLD + "Iron King", ChatColor.WHITE, 5);
		
		ArrayList<String> iLore = new ArrayList<String>();
		
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.RED + "65,000 HP");
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		iLore.add("");
		iLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Iron Ingot" + ChatColor.DARK_GRAY + " x128");
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Power");
		iLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Blessed by the iron gods.");
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Royalty");
		iLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "80%" + ChatColor.GRAY + " chance to spawn an " + ChatColor.WHITE + "iron minion" + ChatColor.GRAY + " when hit.");
		iLore.add("");
		iLore.add("");
		iLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		iLore.add("");
		iLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Iron Nugget" + ChatColor.DARK_GRAY + " x384" + ChatColor.GRAY + " (100%)");
		iLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "Iron Ingot" + ChatColor.DARK_GRAY + " x160" + ChatColor.GRAY + " (50% | True Chance)");
		iLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "" + ChatColor.BOLD + "Ferrum Set" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (25% each)");
		iLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Essence" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		iLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "" + ChatColor.BOLD + "Aspect of Ferrum" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.2%)");
		
		
		ItemMeta iMeta = golemKing.getItemMeta();
		iMeta.setLore(iLore);
		golemKing.setItemMeta(iMeta);
		
		ItemStack endTitan = generateItem(Material.ENDER_EYE, ChatColor.BOLD + "End Titan", ChatColor.DARK_PURPLE, 5);
		
		ArrayList<String> endLore = new ArrayList<String>();
		
		endLore.add("");
		endLore.add(ChatColor.RESET + "" + ChatColor.RED + "75 Titan HP");
		endLore.add("");
		endLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		endLore.add("");
		endLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner" + ChatColor.DARK_GRAY + " x1");
		endLore.add("");
		endLore.add(ChatColor.GRAY + "One of the 5 titans,");
		endLore.add(ChatColor.GRAY + "mysterious things that forged");
		endLore.add(ChatColor.GRAY + "and can only be endd by");
		endLore.add(ChatColor.GRAY + "their weapon counterparts,");
		endLore.add(ChatColor.GRAY + "and their god-blessed superiors.");
		endLore.add("");
		endLore.add(ChatColor.GRAY + "Each counterpart weapon");
		endLore.add(ChatColor.GRAY + "Does a " + ChatColor.RED + "titan damage," + ChatColor.GRAY + " and");
		endLore.add(ChatColor.GRAY + "it varies based on the superior god weapon.");
		endLore.add("");
		endLore.add(ChatColor.GRAY + "This is a " + ChatColor.GREEN + "picky" + ChatColor.GRAY + " titan,");
		endLore.add(ChatColor.GRAY + "so it can only be spawned in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + "."); 
		
		ItemMeta endMeta = endTitan.getItemMeta();
		endMeta.setLore(endLore);
		endTitan.setItemMeta(endMeta);
		
		ItemStack netherTitan = generateItem(Material.NETHER_BRICKS, ChatColor.BOLD + "Nether Titan", ChatColor.RED, 5);
		
		ArrayList<String> netherLore = new ArrayList<String>();
		
		netherLore.add("");
		netherLore.add(ChatColor.RESET + "" + ChatColor.RED + "200 Titan HP");
		netherLore.add("");
		netherLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		netherLore.add("");
		netherLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner" + ChatColor.DARK_GRAY + " x1");
		netherLore.add("");
		netherLore.add(ChatColor.GRAY + "One of the 5 titans,");
		netherLore.add(ChatColor.GRAY + "mysterious things that forged");
		netherLore.add(ChatColor.GRAY + "and can only be damaged by");
		netherLore.add(ChatColor.GRAY + "their weapon counterparts,");
		netherLore.add(ChatColor.GRAY + "and their god-blessed superiors.");
		netherLore.add("");
		netherLore.add(ChatColor.GRAY + "Each counterpart weapon");
		netherLore.add(ChatColor.GRAY + "Does a " + ChatColor.RED + "titan damage," + ChatColor.GRAY + " and");
		netherLore.add(ChatColor.GRAY + "it varies based on the superior god weapon.");
		netherLore.add("");
		netherLore.add(ChatColor.GRAY + "This is a " + ChatColor.GREEN + "picky" + ChatColor.GRAY + " titan,");
		netherLore.add(ChatColor.GRAY + "so it can only be spawned in " + ChatColor.RED + "The Nether" + ChatColor.GRAY + "."); 
		
		ItemMeta netherMeta = netherTitan.getItemMeta();
		netherMeta.setLore(netherLore);
		netherTitan.setItemMeta(netherMeta);
		
		ItemStack dimDragon = generateItem(Material.DRAGON_EGG, ChatColor.BOLD + "Dimensional Dragon", ChatColor.DARK_AQUA, 5);
		
		ArrayList<String> dLore = new ArrayList<String>();
		
		dLore.add("");
		dLore.add(ChatColor.RESET + "" + ChatColor.RED + "125,000 HP");
		dLore.add("");
		dLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		dLore.add("");
		dLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Refined Black Hole Candle" + ChatColor.DARK_GRAY + " x1");
		dLore.add("");
		dLore.add(ChatColor.GRAY + "The chief of the Dimensional Task");
		dLore.add(ChatColor.GRAY + "Force. Very powerful. It has a");
		dLore.add(ChatColor.GRAY + "secret that, when heard, he");
		dLore.add(ChatColor.GRAY + "will know who has discovered it.");
		dLore.add(ChatColor.GRAY + "He is rumored to be worth of the");
		dLore.add(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Black Hole Scythe" + ChatColor.RESET + ChatColor.GRAY + ".");
		
		ItemStack goldenTitan = generateItem(Material.GOLD_BLOCK, ChatColor.BOLD + "Golden Titan", ChatColor.GOLD, 5);
		
		ArrayList<String> goldenLore = new ArrayList<String>();
		
		goldenLore.add("");
		goldenLore.add(ChatColor.RESET + "" + ChatColor.RED + "1,000 Titan HP");
		goldenLore.add("");
		goldenLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		goldenLore.add("");
		goldenLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "" + ChatColor.BOLD + "Titan Summoner" + ChatColor.DARK_GRAY + " x2");
		goldenLore.add("");
		goldenLore.add(ChatColor.GRAY + "One of the 5 titans,");
		goldenLore.add(ChatColor.GRAY + "mysterious things that forged");
		goldenLore.add(ChatColor.GRAY + "and can only be goldend by");
		goldenLore.add(ChatColor.GRAY + "their weapon counterparts,");
		goldenLore.add(ChatColor.GRAY + "and their god-blessed superiors.");
		goldenLore.add("");
		goldenLore.add(ChatColor.GRAY + "This is the " + ChatColor.DARK_RED + "strongest" + ChatColor.GRAY + " titan out of");
		goldenLore.add(ChatColor.GRAY + "the group, and it can only be damaged");
		goldenLore.add(ChatColor.GRAY + "by a weapon with " + ChatColor.GOLD + "pure but unraw" + ChatColor.GRAY + " gold."); 
		
		ItemMeta goldenMeta = goldenTitan.getItemMeta();
		goldenMeta.setLore(goldenLore);
		goldenTitan.setItemMeta(goldenMeta);
		
		ItemMeta dMeta = dimDragon.getItemMeta();
		dMeta.setLore(dLore);
		dimDragon.setItemMeta(dMeta);
		
		// Setting
		eliteBossGUI.setItem(10, goldShulker);
		eliteBossGUI.setItem(11, pillagerKing);
		eliteBossGUI.setItem(12, phantomKing);
		eliteBossGUI.setItem(13, emeraldWarrior);
		eliteBossGUI.setItem(14, creeperKing);
		eliteBossGUI.setItem(15, aranea);
		eliteBossGUI.setItem(16, golemKing);
		
		eliteBossGUI.setItem(19, damageTitan);
		eliteBossGUI.setItem(20, endTitan);
		eliteBossGUI.setItem(21, netherTitan);
		eliteBossGUI.setItem(22, speedTitan);
		eliteBossGUI.setItem(23, goldenTitan);
		eliteBossGUI.setItem(24, dimDragon);
		
		p.openInventory(eliteBossGUI);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3F, 0.5F);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "GUIs can only be executed by Players.");
		}
		Player p = (Player) sender;
		
		// ItemStack comingSoon = getComingSoon();
		
		Inventory bossGUI = GUIManagers.generateGUI(54, ChatColor.BOLD + "" + ChatColor.AQUA + "SMP Bosses Menu");
		
		// T1
		ItemStack superSniper = generateItem(Material.BOW, "Super Sniper", ChatColor.DARK_PURPLE, 1);
		
		ArrayList<String> sniperLore = new ArrayList<String>();
		
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.RED + "300 HP");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Arrow" + ChatColor.DARK_GRAY + " x64");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "One With The Arrow");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.WHITE + "Slow Falling II" + ChatColor.GRAY + ", " + ChatColor.AQUA + "Speed II"
		+ ChatColor.GRAY + ",");
		sniperLore.add(ChatColor.GRAY + "and " + ChatColor.GREEN + "Jump Boost II" + ChatColor.GRAY + ". Immune to " + ChatColor.RED + "knockback" + ChatColor.GRAY + ".");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.GOLD + "Inner Flame");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to Fire Damage; Always uses fire arrows.");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.GOLD + "Super Range");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Can follow you up to " + ChatColor.GREEN + "150" + ChatColor.GRAY + " blocks.");
		sniperLore.add("");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		sniperLore.add("");
		sniperLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Bone" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		sniperLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Spectral Arrow" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		sniperLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Skeleton Head" + ChatColor.DARK_GRAY + " x12" + ChatColor.GRAY + " (50% | True Chance)");
		sniperLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Wither Skeleton Head" + ChatColor.DARK_GRAY + " x6" + ChatColor.GRAY + " (25% | True Chance)");
		
		ItemMeta sniperMeta = superSniper.getItemMeta();
		sniperMeta.setLore(sniperLore);
		superSniper.setItemMeta(sniperMeta);
		
		ItemStack rottenPrivate = generateItem(Material.ROTTEN_FLESH, "Private of Rotten Army", ChatColor.DARK_GREEN, 1);
		
		ArrayList<String> privateLore = new ArrayList<String>();
		
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "" + ChatColor.RED + "50 HP");
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Rotten Flesh" + ChatColor.DARK_GRAY + " x16");
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Rotten Army");
		privateLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength 4" + ChatColor.GRAY + ".");
		privateLore.add("");
		privateLore.add("");
		privateLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		privateLore.add("");
		privateLore.add(ChatColor.GRAY + "- " + ChatColor.WHITE + "Rotten Flesh" + ChatColor.DARK_GRAY + " x24" + ChatColor.GRAY + " (100%)");
		
		ItemMeta privateMeta = rottenPrivate.getItemMeta();
		privateMeta.setLore(privateLore);
		rottenPrivate.setItemMeta(privateMeta);
		
		ItemStack rottenSpec = generateItem(Material.BONE, "Specialist of Rotten Army", ChatColor.GREEN, 1);
		
		ArrayList<String> specLore = new ArrayList<String>();
		
		specLore.add("");
		specLore.add(ChatColor.RESET + "" + ChatColor.RED + "80 HP");
		specLore.add("");
		specLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		specLore.add("");
		specLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Bone" + ChatColor.DARK_GRAY + " x16");
		specLore.add("");
		specLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		specLore.add("");
		specLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Rotten Army");
		specLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength 4" + ChatColor.GRAY + ".");
		specLore.add("");
		specLore.add("");
		specLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		specLore.add("");
		specLore.add(ChatColor.GRAY + "- " + ChatColor.WHITE + "Bone" + ChatColor.DARK_GRAY + " x24" + ChatColor.GRAY + " (100%)");
		
		ItemMeta specMeta = rottenSpec.getItemMeta();
		specMeta.setLore(specLore);
		rottenSpec.setItemMeta(specMeta);
		
		ItemStack netheriteSkele = generateItem(Material.NETHERITE_INGOT, "Netherite Skeleton", ChatColor.DARK_GRAY, 1);
		
		ArrayList<String> nLore = new ArrayList<String>();
		
		nLore.add("");
		nLore.add(ChatColor.RESET + "" + ChatColor.RED + "20 HP");
		nLore.add("");
		nLore.add(ChatColor.GRAY + "A normal wither skeleton,");
		nLore.add(ChatColor.GRAY + "that stumbled upon ancient");
		nLore.add(ChatColor.GRAY + "debris, and has trained to");
		nLore.add(ChatColor.GRAY + "adapt to its special properties.");
		nLore.add("");
		nLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		nLore.add("");
		nLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "Ancient Debris" + ChatColor.DARK_GRAY + " x1");
		nLore.add("");
		nLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		nLore.add("");
		nLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Advanced Species");
		nLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength II" + ChatColor.GRAY + " and ");
		nLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "Jump Boost III" + ChatColor.GRAY + ".");
		nLore.add("");
		nLore.add("");
		nLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		nLore.add("");
		nLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_GRAY + "Netherite Ingot" + ChatColor.DARK_GRAY + " x4" + ChatColor.GRAY + " (100%)");
		nLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_GRAY + "Netherite Block" + ChatColor.DARK_GRAY + " x2" + ChatColor.GRAY + " (5%)");
		
		ItemMeta nMeta = netheriteSkele.getItemMeta();
		nMeta.setLore(nLore);
		netheriteSkele.setItemMeta(nMeta);
		
		ItemStack amethystZombie = generateItem(Material.AMETHYST_BLOCK, "Amethyst Zombie", ChatColor.GREEN, 1);
		
		ArrayList<String> amLore = new ArrayList<String>();
		
		amLore.add("");
		amLore.add(ChatColor.RESET + "" + ChatColor.RED + "200 HP");
		amLore.add("");
		amLore.add(ChatColor.GRAY + "A zombie that was consumed with");
		amLore.add(ChatColor.GRAY + "a special type of budding amethyst,");
		amLore.add(ChatColor.GRAY + "changing and infusing the zombie's");
		amLore.add(ChatColor.GRAY + "DNA with amethyst properties.");
		amLore.add("");
		amLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		amLore.add("");
		amLore.add(ChatColor.RESET + "- " + ChatColor.LIGHT_PURPLE + "Amethyst Shard" + ChatColor.DARK_GRAY + " x32");
		amLore.add("");
		amLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		amLore.add("");
		amLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Amethyst DNA");
		amLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength IV" + ChatColor.GRAY + " and ");
		amLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Speed III" + ChatColor.GRAY + ".");
		amLore.add("");
		amLore.add("");
		amLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		amLore.add("");
		amLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + "Block of Amethyst" + ChatColor.DARK_GRAY + " x16" + ChatColor.GRAY + " (100%)");
		amLore.add(ChatColor.GRAY + "- " + ChatColor.LIGHT_PURPLE + "Amethyst Chestplate/Leggings/Boots" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (12.5% each)");
		amLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + "Super Amethyst Shard" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (6.25%)");
		
		ItemMeta amMeta = amethystZombie.getItemMeta();
		amMeta.setLore(amLore);
		amethystZombie.setItemMeta(amMeta);
		// T2
		ItemStack rottenSear = generateItem(Material.GOLD_INGOT, "Seargant of Rotten Army", ChatColor.DARK_GREEN, 2);
		
		ArrayList<String> searLore = new ArrayList<String>();
		
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.RED + "400 HP");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		searLore.add("");
		searLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Gold Ingot" + ChatColor.DARK_GRAY + " x32");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "Higher Rotten Army");
		searLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength 6" + ChatColor.GRAY + " and ");
		searLore.add(ChatColor.RED + "Regeneration 4" + ChatColor.GRAY + ".");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.RED + "Guerilla Warfare");
		searLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.WHITE + "Speed II" + ChatColor.GRAY + " .");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.RED + "Combat Stress");
		searLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Does not know who to be angry at.");
		searLore.add("");
		searLore.add("");
		searLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		searLore.add("");
		searLore.add(ChatColor.GRAY + "- " + ChatColor.WHITE + "Gold Ingot" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		searLore.add(ChatColor.GRAY + "- " + ChatColor.WHITE + "Enchanted Gold Ingot" + ChatColor.DARK_GRAY + " x8" + ChatColor.GRAY + " (50%)");
		searLore.add(ChatColor.RESET + "- " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Essence" + ChatColor.RESET + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.6% | True Chance)");
		
		ItemMeta searMeta = rottenSear.getItemMeta();
		searMeta.setLore(searLore);
		rottenSear.setItemMeta(searMeta);
		
		ItemStack hogatar = generateItem(Material.PORKCHOP, "The Hogatar", ChatColor.RED, 2);
		
		ArrayList<String> hogatarLore = new ArrayList<String>();
		
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.RED + "500 HP");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "- " + ChatColor.LIGHT_PURPLE + "Raw Porkchop" + ChatColor.DARK_GRAY + " x128");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Power of Wind");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to knockback and permanent slow falling.");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.RED + "Power of Fire");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "You catch on fire when hit; Immune to fire damage.");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Power of Water");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to drowning and zombification.");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Power of Earth");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.RED + "Strength 4" + ChatColor.GRAY + " and " + ChatColor.GREEN + "Jump Boost 3" + ChatColor.GRAY + " equipped.");
		hogatarLore.add("");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		hogatarLore.add("");
		hogatarLore.add(ChatColor.RESET + "- " + ChatColor.LIGHT_PURPLE + "Cooked Porkchop" + ChatColor.DARK_GRAY + " x64" + ChatColor.GRAY + " (100%)");
		hogatarLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Crimson Fungus" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (100% | True Chance)");
		hogatarLore.add(ChatColor.RESET + "- " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Essence" + ChatColor.RESET + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (7% | True Chance)");
		
		ItemMeta hogatarMeta = hogatar.getItemMeta();
		hogatarMeta.setLore(hogatarLore);
		hogatar.setItemMeta(hogatarMeta);
		
		ItemStack warglin = generateItem(Material.DIAMOND_SWORD, ChatColor.BOLD + "Warglin", ChatColor.DARK_RED, 2);
	
		ArrayList<String> warglinLore = new ArrayList<String>();
		
		warglinLore.add("");
		warglinLore.add(ChatColor.RESET + "" + ChatColor.RED + "150 HP");
		warglinLore.add("");
		warglinLore.add(ChatColor.GRAY + "\"A subspecies of Piglins, trained to be");
		warglinLore.add(ChatColor.GRAY + "faster, stronger, smarter, and immune to");
		warglinLore.add(ChatColor.GRAY + "the effects of the overworld.\"");
		warglinLore.add("");
		warglinLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		warglinLore.add("");
		warglinLore.add(ChatColor.RESET + "- " + ChatColor.LIGHT_PURPLE + "Raw Porkchop" + ChatColor.DARK_GRAY + " x64");
		warglinLore.add(ChatColor.RESET + "- " + ChatColor.AQUA + "Diamond Sword" + ChatColor.DARK_GRAY + " x1");
		warglinLore.add("");
		warglinLore.add("");
		warglinLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		warglinLore.add("");
		warglinLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Golden Axe" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100% | True Chance)");
		warglinLore.add(ChatColor.RESET + "- " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Essence" + ChatColor.RESET + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (7% | True Chance)");
		
		ItemMeta warMeta = warglin.getItemMeta();
		warMeta.setLore(warglinLore);
		warglin.setItemMeta(warMeta);
		
		ItemStack magillager = generateItem(Material.EMERALD, "Magillager", ChatColor.DARK_AQUA, 2);
		
		ArrayList<String> magLore = new ArrayList<String>();
		
		magLore.add("");
		magLore.add(ChatColor.RESET + "" + ChatColor.RED + "150 HP");
		magLore.add("");
		magLore.add(ChatColor.GRAY + "A lost member of the illager");
		magLore.add(ChatColor.GRAY + "family, excluded for their");
		magLore.add(ChatColor.GRAY + "malicious past.");
		magLore.add("");
		magLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		magLore.add("");
		magLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Bow" + ChatColor.DARK_GRAY + " x2");
		magLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Emerald" + ChatColor.DARK_GRAY + " x16");
		magLore.add("");
		magLore.add("");
		magLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		magLore.add("");
		magLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Emerald" + ChatColor.DARK_GRAY + " x64" + ChatColor.GRAY + " (100%)");
		magLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "The Magic Bow" + ChatColor.RESET + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (5%)");
		
		ItemMeta magMeta = magillager.getItemMeta();
		magMeta.setLore(magLore);
		magillager.setItemMeta(magMeta);
		
		
		ItemStack rottenSub = generateItem(Material.SEA_LANTERN, "Rotten Army Submarine", ChatColor.DARK_AQUA, 2);
		
		ArrayList<String> subLore = new ArrayList<String>();
		
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.RED + "200 HP");
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		subLore.add("");
		subLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Prismarine Shard" + ChatColor.DARK_GRAY + " x16");
		subLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Rotten Army");
		subLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength 4" + ChatColor.GRAY + ".");
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "Submarine Tank");
		subLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Slow on land, fast on water. Requires you in water to");
		subLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "be spawned.");
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Echolocation");
		subLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Can find you from up to " + ChatColor.GREEN + "300" + ChatColor.GRAY + " blocks.");
		subLore.add("");
		subLore.add("");
		subLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		subLore.add("");
		subLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "Prismarine Crystal" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (100%)");
		subLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "Sea Lantern" + ChatColor.DARK_GRAY + " x16" + ChatColor.GRAY + " (50%)");
		subLore.add(ChatColor.RESET + "- " + ChatColor.AQUA + "" + ChatColor.BOLD + "Guardian Boots" + ChatColor.RESET + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (8%)");
		
		ItemMeta subMeta = rottenSub.getItemMeta();
		subMeta.setLore(subLore);
		rottenSub.setItemMeta(subMeta);
		// T3
		ItemStack blazeKing = generateItem(Material.BLAZE_ROD, ChatColor.BOLD + "Blaze King", ChatColor.GOLD, 3);
		
		ArrayList<String> blazeKingLore = new ArrayList<String>();
		
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.RED + "1,000 HP");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Blaze Rod" + ChatColor.DARK_GRAY + " x128");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Fire Energy");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "75%" + ChatColor.GRAY + " Chance to Strike Lightning when you are hit; Immune to Arrows.");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.GOLD + "Blazing Touch");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Become on Fire for 30 seconds when hitting him.");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.YELLOW + "True Fire");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "50%" + ChatColor.GRAY + " Chance to Remove Fire Resistance (if applied).");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Royalty");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "40%" + ChatColor.GRAY + " Chance to Summon a Blaze when hit.");
		blazeKingLore.add("");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		blazeKingLore.add("");
		blazeKingLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Blaze Sword" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		blazeKingLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "Blaze Rods" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (100%)");
		
		ItemMeta blazeKingMeta = blazeKing.getItemMeta();
		blazeKingMeta.setLore(blazeKingLore);
		blazeKing.setItemMeta(blazeKingMeta);
		
		ItemStack spiderQueen = generateItem(Material.STRING, ChatColor.BOLD + "Spider Queen", ChatColor.GRAY, 3);
		
		ArrayList<String> spiderQueenLore = new ArrayList<String>();
		
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.RED + "800 HP");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "String" + ChatColor.DARK_GRAY + " x128");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Royalty");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "40%" + ChatColor.GRAY + " Chance to Summon a poisonous spider when hit.");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.DARK_RED + "Venomous Bite");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "Poison II" + ChatColor.GRAY + " when hit; " + ChatColor.GREEN + "75%" + ChatColor.GRAY + " Chance to cause");
		spiderQueenLore.add(ChatColor.DARK_GRAY + "Slowness III" + ChatColor.GRAY + " & " + ChatColor.GREEN + "50%" + ChatColor.GRAY + " Chance to cause " + ChatColor.DARK_PURPLE + "Blindness II");
		spiderQueenLore.add("");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		spiderQueenLore.add("");
		spiderQueenLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "" + ChatColor.BOLD + "Tarantula Sword" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		spiderQueenLore.add(ChatColor.RESET + "- " + ChatColor.GOLD + "String" + ChatColor.DARK_GRAY + " x55 - x64" + ChatColor.GRAY + " (100%)");
		
		ItemMeta spiderQueenMeta = spiderQueen.getItemMeta();
		spiderQueenMeta.setLore(spiderQueenLore);
		spiderQueen.setItemMeta(spiderQueenMeta);
		
		ItemStack rottenAir = generateItem(Material.GHAST_TEAR, "Rotten Army Air Support", ChatColor.RED, 3);
		
		ArrayList<String> airLore = new ArrayList<String>();
		
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.RED + "400 HP");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		airLore.add("");
		airLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Ghast Tear" + ChatColor.DARK_GRAY + " x32");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Rotten Army");
		airLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength 4" + ChatColor.GRAY + ".");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.RED + "Missile");
		airLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Shoots fireballs at " + ChatColor.RED + "Explosion Power 10" + ChatColor.GRAY + ".");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Armored Tank");
		airLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to arrows.");
		airLore.add("");
		airLore.add("");
		airLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		airLore.add("");
		airLore.add(ChatColor.RESET + "- " + ChatColor.RED + "TNT" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x128" + ChatColor.GRAY + " (100%)");
		airLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Ghast Tear" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (100%)");
		airLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Rotten Flesh" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + " (100%)");
		
		ItemMeta airMeta = rottenAir.getItemMeta();
		airMeta.setLore(airLore);
		rottenAir.setItemMeta(airMeta);
		
		
		ItemStack witherman = generateItem(Material.ENDER_EYE, "Witherman", ChatColor.RED, 3);
		
		ArrayList<String> withermanLore = new ArrayList<String>();
		
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.RED + "1,050 HP");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Ender Pearl" + ChatColor.DARK_GRAY + " x64");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GREEN + "Wither Enriched");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Permanent " + ChatColor.DARK_RED + "Strength VI" + ChatColor.GRAY + ", ");
		withermanLore.add(ChatColor.GOLD + "Fire Resistance II" + ChatColor.GRAY + ", and " + ChatColor.RED + "Regeneration" + ChatColor.GRAY + ".");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "Ender Side");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Scared of " + ChatColor.DARK_GRAY + "Withers" + ChatColor.GRAY + ".");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Wither Spikes");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Gain " + ChatColor.GRAY + "Wither III" + ChatColor.GRAY + " when it is damaged.");
		withermanLore.add("");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		withermanLore.add("");
		withermanLore.add(ChatColor.RESET + "- " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Essence" + ChatColor.DARK_GRAY + "x1" + ChatColor.GRAY + " (50% | True Chance)");
		withermanLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Ender Pearl" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + "(100%)");
		
		ItemMeta withermanMeta = witherman.getItemMeta();
		withermanMeta.setLore(withermanLore);
		witherman.setItemMeta(withermanMeta);
		// T4
		
		ItemStack dimGuard = generateItem(Material.CLAY, ChatColor.BOLD + "Dimensional Guard", ChatColor.GRAY, 4);
		
		ArrayList<String> dimGuardLore = new ArrayList<String>();
		
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.RED + "5,000 HP (2,000 Field HP; 3,000 Regular HP)");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Netherite Sword" + ChatColor.DARK_GRAY + " x3");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Energy Field");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Any entity hitting its field will be struck with lightning.");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.YELLOW + "Light Interference");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "25%" + ChatColor.GRAY + " chance to be given" + ChatColor.DARK_GRAY + " Blindess I" + ChatColor.GRAY + " when you");
		dimGuardLore.add(ChatColor.GRAY + "hit it.");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Time Travel");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "15%" + ChatColor.GRAY + " chance to be frozen for 3 seconds when hit.");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.RED + "Charged Bomb");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "5%" + ChatColor.GRAY + " chance to cause a " + ChatColor.RED + "huge" + " explosion when it is hit.");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "Cancel-Out");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "10%" + ChatColor.GRAY + " chance to cancel out an attack.");
		dimGuardLore.add("");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		dimGuardLore.add("");
		dimGuardLore.add(ChatColor.RESET + "- " + ChatColor.GRAY + "" + ChatColor.BOLD + "Dimension Essence" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100% | True Chance)");
		dimGuardLore.add(ChatColor.RESET + "- " + ChatColor.GREEN + "Overworld" + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "Nether" + ChatColor.GRAY + "/" + ChatColor.LIGHT_PURPLE + "End Essence " + ChatColor.GRAY + "(depends on");
		dimGuardLore.add(ChatColor.GRAY + "world fought in)" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (10% | True Chance)");
		dimGuardLore.add(ChatColor.RESET + "- " + ChatColor.BLACK + "Wither Rose" + ChatColor.DARK_GRAY + " x32" + ChatColor.GRAY + "(100%)");
		dimGuardLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Wither Skeleton Skull" + ChatColor.DARK_GRAY + " x16" + ChatColor.GRAY + "(50%)");
		
		ItemMeta dimGuardMeta = dimGuard.getItemMeta();
		dimGuardMeta.setLore(dimGuardLore);
		dimGuard.setItemMeta(dimGuardMeta);
		
		ItemStack snowPrince = generateItem(Material.SNOW_BLOCK, ChatColor.BOLD + "Snow Prince", ChatColor.WHITE, 4);
		
		ArrayList<String> snowPrinceLore = new ArrayList<String>();
		
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.RED + "4,000 HP (1,000 Bear HP; 3,000 Regular HP)");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Son of the Snow King, husband to the Snow Princess,");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "and enemy to the Blaze Family.");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Packed Ice" + ChatColor.DARK_GRAY + " x64");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Snow Block" + ChatColor.DARK_GRAY + " x48");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Royalty");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "40%" + ChatColor.GRAY + " Chance to Summon a " + ChatColor.AQUA + "snow minion" + ChatColor.GRAY + " when hit.");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "True Freeze");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to fire & projectile damage, and knockback.");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "50%" + ChatColor.GRAY + " chance to freeze you for 5 seconds when hit.");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "25%" + ChatColor.GRAY + " chance to absorb an attack.");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.RED + "Cold Regeneration");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.RED + "Regeneration 3" + ChatColor.GRAY + " and " + ChatColor.GOLD + "Resistance 3" + ChatColor.GRAY + "equipped.");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Bear Lover");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Rides a " + ChatColor.DARK_AQUA + "royal ice bear" + ChatColor.GRAY + " with " + ChatColor.RED + " 1,000 HP" + ChatColor.GRAY + "");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "that has similar abilities.");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Keeper of Ice Boots");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Frost Walker XXV" + ChatColor.GRAY + " boots equipped.");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "As the keeper, he can disable rain.");
		snowPrinceLore.add("");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		snowPrinceLore.add("");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.AQUA + "" + ChatColor.BOLD + "Super Icey Boots" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (9%)");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "" + "Snow Block" + ChatColor.DARK_GRAY + " x64" + ChatColor.GRAY + " (100%)");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.AQUA + "" + "Blue Ice" + ChatColor.DARK_GRAY + " x48" + ChatColor.GRAY + " (50% | True Chance)");
		snowPrinceLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Freeze Wand" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (100%)");
		
		ItemMeta snowPrinceMeta = snowPrince.getItemMeta();
		snowPrinceMeta.setLore(snowPrinceLore);
		snowPrince.setItemMeta(snowPrinceMeta);
		
		ItemStack zking = generateItem(Material.BAKED_POTATO, ChatColor.BOLD + "Zombie King", ChatColor.DARK_RED, 4);
		
		List<String> zkingLore = new ArrayList<>();
		
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.RED + "10,050 HP (50 Horse HP; 10,000 Regular HP)");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "A wicked and cruel monarch,");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "forged the strongest and deadliest");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "sword, feared by the undead species.");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Rotten Flesh" + ChatColor.DARK_GRAY + " x128");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Royalty");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GREEN + "100%" + ChatColor.GRAY + " Chance to Summon an " + ChatColor.DARK_RED + "undead minion" + ChatColor.GRAY + " when hit.");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.WHITE + "Iron Fist");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Very strong & agile. A pain to battle. Will");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "most likely die a couple times.");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Immune to explosions, projectiles, and");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "drowning.");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Ultimate Power");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Gets bored a lot. Likes to play around with");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.GRAY + "the powers " + ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "Aribus" + ChatColor.RESET + "" + ChatColor.GRAY + " has blessed him with.");
		zkingLore.add("");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		zkingLore.add("");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Confugiat" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.5%)");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Rotten Flesh" + ChatColor.DARK_GRAY + " x256" + ChatColor.GRAY + " (100%)");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Bone" + ChatColor.DARK_GRAY + " x128" + ChatColor.GRAY + " (100%)");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.YELLOW + "Baked Potato" + ChatColor.DARK_GRAY + " x96" + ChatColor.GRAY + " (100%)");
		zkingLore.add(ChatColor.RESET + "- " + ChatColor.WHITE + "Iron Ingot" + ChatColor.DARK_GRAY + " x64" + ChatColor.GRAY + " (100%)");
		
		ItemMeta zkingMeta = zking.getItemMeta();
		zkingMeta.setLore(zkingLore);
		zking.setItemMeta(zkingMeta);
		
		ItemStack sculkWitch = generateItem(Material.SCULK_SENSOR, ChatColor.BOLD + "The Sculk Witch", ChatColor.DARK_AQUA, 4);
		
		List<String> sculkWitchLore = new ArrayList<>();
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.RED + "7,000 HP");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.GRAY + "A mysterious witch, seemingly from the future.");
		sculkWitchLore.add(ChatColor.GRAY + "Her abilities are glitched and are not ready yet for");
		sculkWitchLore.add(ChatColor.GRAY + "the inferior minds of 1.17 players. Perhaps 1.18 or even");
		sculkWitchLore.add(ChatColor.GRAY + "1.19 will fix this...");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spawn Cost");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.RESET + "- " + ChatColor.DARK_GRAY + "Rotten Flesh" + ChatColor.DARK_GRAY + " x160");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Abilities");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.GREEN + "Organic Hands");
		sculkWitchLore.add(ChatColor.GRAY + "Summons 1-4 Zombies as defense.");
		sculkWitchLore.add("");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Drops");
		sculkWitchLore.add("");
		sculkWitchLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Sculk Helmet" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.3%)");
		sculkWitchLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Sculk Chestplate" + ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " (0.05%)");
		sculkWitchLore.add(ChatColor.RESET + "- " + ChatColor.DARK_AQUA + "Sculk Sensor" + ChatColor.DARK_GRAY + " x96" + ChatColor.GRAY + " (64x 100%, 96x 50%)");
		
		ItemMeta sculkWitchMeta = sculkWitch.getItemMeta();
		sculkWitchMeta.setLore(sculkWitchLore);
		sculkWitch.setItemMeta(sculkWitchMeta);
		// T1 Sets
		bossGUI.setItem(10, superSniper);
		bossGUI.setItem(11, rottenPrivate);
		bossGUI.setItem(12, rottenSpec);
		bossGUI.setItem(13, netheriteSkele);
		bossGUI.setItem(14, amethystZombie);
		// T2 Sets
		bossGUI.setItem(19, rottenSear);
		bossGUI.setItem(20, hogatar);
		bossGUI.setItem(21, warglin);
		bossGUI.setItem(22, magillager);
		// T3 Sets
		bossGUI.setItem(28, blazeKing);
		bossGUI.setItem(29, spiderQueen);
		bossGUI.setItem(30, rottenAir);
		bossGUI.setItem(31, witherman);
		// T4 Sets
		bossGUI.setItem(37, snowPrince);
		bossGUI.setItem(38, dimGuard);
		bossGUI.setItem(39, zking);
		bossGUI.setItem(40, sculkWitch);
		
		if (p.getWorld().getName().contains("world_titan")) {
			p.sendMessage(ChatColor.RED + "You are too weak to summon a boss here...");
		} else {
			if (args.length < 1) {
				p.openInventory(bossGUI);
				p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 3F, 0.5F);
			} else if (args[0].equalsIgnoreCase("t5")){
	        	 if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER) >= 50 && p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN) >= 1000) {
	        		 openEliteBosses(p);
	        	 } else {
	        		 p.sendMessage(ChatColor.RED + Integer.toString(p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER)) + " / 50 Withers and " + Integer.toString(p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN)) + " / 1,000 Enderman.");
	        		 if (p.isOp()) {
	        			 p.sendMessage(ChatColor.GREEN + "Operator Bypass.");
	        			 openEliteBosses(p);
	        		 }
	        	 }
			} else {
				p.openInventory(bossGUI);
				p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 3F, 0.5F);
			}
		}
		
		return false;
	}
}
