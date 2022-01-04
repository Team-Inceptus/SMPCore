package us.teaminceptus.smpcore.listeners.titan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class TitanFinder {
	
	protected static ItemStack comingLater() {
		ItemStack comingLater = new ItemStack(Material.COAL_BLOCK, 1);
		ItemMeta cMeta = comingLater.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_GRAY + "This extruciatingly difficult");
		lore.add(ChatColor.DARK_GRAY + "boss will be added at a later");
		lore.add(ChatColor.DARK_GRAY + "date.");
		cMeta.setLore(lore);
		cMeta.setDisplayName(ChatColor.GRAY + "Coming Soon!");
		
		comingLater.setItemMeta(cMeta);
		
		return comingLater;
	}
	
	protected static ItemStack notUnlocked(int amountReq) {
		ItemStack notUnlocked = new ItemStack(Material.BARRIER, 1);
		ItemMeta nMeta = notUnlocked.getItemMeta();
		nMeta.setDisplayName(ChatColor.RED + "You haven't unlocked this yet!");
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "To fight this boss, you need");
		lore.add(ChatColor.GRAY + "to kill " + ChatColor.GREEN + Integer.toString(amountReq) + ChatColor.GRAY + " titans.");
		
		notUnlocked.setItemMeta(nMeta);
		
		return notUnlocked;
	}
	
	protected static boolean hasUnlocked(SMPCore plugin, Player p, int amountReq) {
		return (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_kills") >= amountReq);
	}
	
	public static ItemStack generateTitanItem(Material icon, String name, double rating) {
		ItemStack item = new ItemStack(icon, 1);
		
		if (rating > 10) rating = 10;
		
		ItemMeta iMeta = item.getItemMeta();
		iMeta.setDisplayName(name + ChatColor.DARK_GRAY + " Rating: " + Double.toString(rating) + " / 10");
		
		iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		iMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		
		item.setItemMeta(iMeta);
		
		return item;
	}
	
	public static Inventory getTitanFinder(SMPCore plugin, Player p) {
		Inventory titanFinder = GUIManagers.generateGUI(36, ChatColor.GRAY + "" + ChatColor.BOLD + "Titan Finder");
		
		ItemStack later = comingLater();
		
		ItemStack info = new ItemStack(Material.REDSTONE_TORCH, 1);
		ItemMeta iMeta = info.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Every 20 minutes, you are eligible to");
		lore.add(ChatColor.GRAY + "fight a random titan, that is based around");
		lore.add(ChatColor.GRAY + "your current statistics.");
		iMeta.setLore(lore);
		iMeta.setDisplayName(ChatColor.YELLOW + "Titan Finder");
		
		info.setItemMeta(iMeta);
		
		titanFinder.setItem(4, info);
		
		// T1
		
		List<String> fireLore = new ArrayList<>();
		fireLore.add(ChatColor.GRAY + "This titan is very good with flames");
		fireLore.add(ChatColor.GRAY + "and fire. Be careful, he can do");
		fireLore.add(ChatColor.GOLD + "True Fire" + ChatColor.GRAY + ", which can remove");
		fireLore.add(ChatColor.GRAY + "fire resistance. He can also spawn");
		fireLore.add(ChatColor.GRAY + "default entities from the nether, however");
		fireLore.add(ChatColor.GRAY + "since they are frequent, they are not as");
		fireLore.add(ChatColor.GRAY + "much of a challenge.");
		fireLore.add("");
		fireLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack fireTitan = generateTitanItem(Material.BLAZE_ROD, ChatColor.GOLD + "Fire Titan", 1.3);
		ItemMeta fMeta = fireTitan.getItemMeta();
		
		fMeta.setLore(fireLore);
		fireTitan.setItemMeta(fMeta);
		
		
		List<String> magicLore = new ArrayList<>();
		magicLore.add(ChatColor.GRAY + "The Magical Titan was always my favorite");
		magicLore.add(ChatColor.GRAY + "to fight, since it drops an item that can");
		magicLore.add(ChatColor.GRAY + "increase your base statistics. But, all");
		magicLore.add(ChatColor.GRAY + "good things come at a price.");
		magicLore.add("");
		magicLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack magicTitan = generateTitanItem(Material.AMETHYST_SHARD, ChatColor.DARK_GREEN + "Magical Titan", 1.4);
		ItemMeta mMeta = magicTitan.getItemMeta();
		
		mMeta.setLore(magicLore);
		magicTitan.setItemMeta(mMeta);
		
		List<String> crossbowLore = new ArrayList<>();
		crossbowLore.add(ChatColor.GRAY + "The Crossbow Titan is a master of");
		crossbowLore.add(ChatColor.GRAY + "archery. He sometimes teleports behind");
		crossbowLore.add(ChatColor.GRAY + "you, which can get annoying.");
		crossbowLore.add("");
		crossbowLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack crossbowTitan = generateTitanItem(Material.CROSSBOW, ChatColor.DARK_AQUA + "Crossbow Titan", 1.65);
		ItemMeta cMeta = crossbowTitan.getItemMeta();
		
		cMeta.setLore(crossbowLore);
		crossbowTitan.setItemMeta(cMeta);
		
		List<String> axeLore = new ArrayList<>();
		axeLore.add(ChatColor.GRAY + "The Axe titan is very strong, but");
		axeLore.add(ChatColor.GRAY + "lacks decent abilities. I recommend");
		axeLore.add(ChatColor.GRAY + "not bringing a shield to this fight,");
		axeLore.add(ChatColor.GRAY + "you will definetly not be happy with");
		axeLore.add(ChatColor.GRAY + "the outcome.");
		axeLore.add("");
		axeLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack axeTitan = generateTitanItem(Material.NETHERITE_AXE, ChatColor.AQUA + "Axe Titan", 1.8);
		ItemMeta aMeta = axeTitan.getItemMeta();
		
		aMeta.setLore(axeLore);
		axeTitan.setItemMeta(aMeta);
		
		List<String> knockbackLore = new ArrayList<>();
		knockbackLore.add(ChatColor.GRAY + "Sending you far is this titan's");
		knockbackLore.add(ChatColor.GRAY + "thing. Be careful where you land.");
		knockbackLore.add(ChatColor.GRAY + "Minions appear as well.");
		knockbackLore.add("");
		knockbackLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack knockbackTitan = generateTitanItem(Material.STICK, ChatColor.RED + "Knockback Titan", 1.95);
		ItemMeta kMeta = knockbackTitan.getItemMeta();
		
		kMeta.setLore(knockbackLore);
		knockbackTitan.setItemMeta(kMeta);
		
		List<String> pLore = new ArrayList<>();
		pLore.add(ChatColor.GRAY + "Potions effects are thrown all");
		pLore.add(ChatColor.GRAY + "the time with this titan. Make sure");
		pLore.add(ChatColor.GRAY + "to bring plenty of milk, although it");
		pLore.add(ChatColor.GRAY + "may not be enough.");
		pLore.add("");
		pLore.add(ChatColor.GREEN + "Good Luck Soldier!");
		
		ItemStack potTitan = generateTitanItem(Material.POTION, ChatColor.DARK_PURPLE + "Potion Titan", 2.1);
		ItemMeta pMeta = potTitan.getItemMeta();
		pMeta.setLore(pLore);
		potTitan.setItemMeta(pMeta);

		List<String> cLore = new ArrayList<>();
		cLore.add(ChatColor.GRAY + "Explosions will seemingly appear");
		cLore.add(ChatColor.GRAY + "every second or so, prepare youself");
		cLore.add(ChatColor.GRAY + "and your sound settings.");
		cLore.add("");
		cLore.add(ChatColor.GREEN + "Good Luck Soldier!");

		ItemStack explosiveTitan = generateTitanItem(Material.TNT, ChatColor.DARK_GREEN + "Explosive Titan", 2.4);
		ItemMeta eMeta = explosiveTitan.getItemMeta();
		eMeta.setLore(cLore);
		explosiveTitan.setItemMeta(eMeta);
		
		// T2
		List<String> iLore = new ArrayList<>();
		iLore.add(ChatColor.GRAY + "The titan made");
		iLore.add(ChatColor.GRAY + "of pure iron. Get");
		iLore.add(ChatColor.GRAY + "ready for a good");
		iLore.add(ChatColor.GRAY + "fight!");
		
		ItemStack ironTitan = generateTitanItem(Material.IRON_BLOCK, ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Titan", 2.85);
		ItemMeta irMeta = ironTitan.getItemMeta();
		irMeta.setLore(iLore);
		ironTitan.setItemMeta(irMeta);
		
		List<String> sLore = new ArrayList<>();
		sLore.add(ChatColor.GRAY + "A weak but tactical");
		sLore.add(ChatColor.GRAY + "titan, this one uses");
		sLore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "special methods" + ChatColor.GRAY + ".");
		
		ItemStack sandTitan = generateTitanItem(Material.SAND, ChatColor.GOLD + "" + ChatColor.BOLD + "Sand Titan", 3);
		ItemMeta sMeta = sandTitan.getItemMeta();
		sMeta.setLore(sLore);
		sandTitan.setItemMeta(sMeta);
		
		List<String> gLore = new ArrayList<>();
		gLore.add(ChatColor.GRAY + "Spooky and mysterious,");
		gLore.add(ChatColor.GRAY + "this titan is even older than");
		gLore.add(ChatColor.GRAY + "the king! Watch out for the vexes!");
		
		ItemStack ghostTitan = generateTitanItem(Material.SOUL_SAND, ChatColor.GRAY + "" + ChatColor.BOLD + "Ghost Titan", 3.25);
		ItemMeta gMeta = ghostTitan.getItemMeta();
		gMeta.setLore(gLore);
		ghostTitan.setItemMeta(gMeta);
		
		List<String> icLore = new ArrayList<>();
		icLore.add(ChatColor.GRAY + "Buff but inexperienced,");
		icLore.add(ChatColor.GRAY + "this titan has a range of");
		icLore.add(ChatColor.GRAY + "attacks that are just WONDERFUL!");
		
		ItemStack iceTitan = generateTitanItem(Material.ICE, ChatColor.AQUA + "" + ChatColor.BOLD + "Ice Titan", 3.5);
		ItemMeta icMeta = iceTitan.getItemMeta();
		icMeta.setLore(icLore);
		iceTitan.setItemMeta(icMeta);
		
		List<String> aLore = new ArrayList<>();
		aLore.add(ChatColor.GRAY + "Tactical and spiky,");
		aLore.add(ChatColor.GRAY + "this titan relies on");
		aLore.add(ChatColor.GRAY + "its subjects and your");
		aLore.add(ChatColor.GRAY + "faults. Truly one of the");
		aLore.add(ChatColor.GRAY + "smartest rogues out there.");
		
		ItemStack ameTitan = generateTitanItem(Material.AMETHYST_BLOCK, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Amethyst Titan", 3.8);
		ItemMeta amMeta = ameTitan.getItemMeta();
		amMeta.setLore(aLore);
		ameTitan.setItemMeta(amMeta);
		
		List<String> nLore = new ArrayList<>();
		nLore.add(ChatColor.GRAY + "This titan is as hard");
		nLore.add(ChatColor.GRAY + "as bedrock, if there weren't");
		nLore.add(ChatColor.GRAY + "all those tutorials on how to ");
		nLore.add(ChatColor.GRAY + "break it. One of the tank brothers.");
		
		ItemStack nTitan = generateTitanItem(Material.NETHERITE_INGOT, GeneralUtils.hexToChat("392c2d", ChatColor.BOLD + "Netherite Titan"), 4.15);
		ItemMeta nMeta = nTitan.getItemMeta();
		nMeta.setLore(nLore);
		nTitan.setItemMeta(nMeta);
		
		List<String> arLore = new ArrayList<>();
		arLore.add(ChatColor.GRAY + "Skilled with bows,");
		arLore.add(ChatColor.GRAY + "its arrows almost never");
		arLore.add(ChatColor.GRAY + "miss. Good luck!");
		
		ItemStack archTitan = generateTitanItem(Material.BOW, ChatColor.RED + "" + ChatColor.BOLD + "Archery Titan", 4.5);
		ItemMeta arMeta = archTitan.getItemMeta();
		arMeta.setLore(arLore);
		archTitan.setItemMeta(arMeta);
		
		List<String> wLore = new ArrayList<>();
		wLore.add(ChatColor.GRAY + "King of all withers,");
		wLore.add(ChatColor.GRAY + "You're in for a WONDERFUL treat!");
		
		ItemStack witherTitan = generateTitanItem(Material.WITHER_ROSE, ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Wither Titan", 4.9);
		ItemMeta wMeta = witherTitan.getItemMeta();
		wMeta.setLore(wLore);
		witherTitan.setItemMeta(wMeta);
		
		List<String> drLore = new ArrayList<>();
		drLore.add(ChatColor.GRAY + "The final boss before");
		drLore.add(ChatColor.GRAY + "advancing to the titan");
		drLore.add(ChatColor.GRAY + "army. I wish you the best");
		drLore.add(ChatColor.GRAY + "of luck.");
		
		ItemStack dragonTitan = generateTitanItem(Material.END_CRYSTAL, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dragon Titan", 5.5);
		ItemMeta drMeta = dragonTitan.getItemMeta();
		drMeta.setLore(drLore);
		dragonTitan.setItemMeta(drMeta);
		
		// Setting
		titanFinder.setItem(10, (hasUnlocked(plugin, p, 40) ? fireTitan : notUnlocked(40)));
		titanFinder.setItem(11, (hasUnlocked(plugin, p, 45) ? magicTitan : notUnlocked(45)));
		titanFinder.setItem(12, (hasUnlocked(plugin, p, 55) ? crossbowTitan : notUnlocked(55)));
		titanFinder.setItem(13, (hasUnlocked(plugin, p, 65) ? axeTitan : notUnlocked(65)));
		titanFinder.setItem(14, (hasUnlocked(plugin, p, 80) ? knockbackTitan : notUnlocked(80)));
		titanFinder.setItem(15, (hasUnlocked(plugin, p, 90) ? potTitan : notUnlocked(90)));
		titanFinder.setItem(16, (hasUnlocked(plugin, p, 105) ? explosiveTitan : notUnlocked(105)));
		
		titanFinder.setItem(19, (hasUnlocked(plugin, p, 120) ? ironTitan : notUnlocked(120)));
		titanFinder.setItem(20, (hasUnlocked(plugin, p, 145) ? sandTitan : notUnlocked(145)));
		titanFinder.setItem(21, (hasUnlocked(plugin, p, 170) ? ghostTitan : notUnlocked(170)));
		titanFinder.setItem(22, (hasUnlocked(plugin, p, 200) ? iceTitan : notUnlocked(200)));
		titanFinder.setItem(23, (hasUnlocked(plugin, p, 225) ? ameTitan : notUnlocked(225)));
		titanFinder.setItem(24, (hasUnlocked(plugin, p, 255) ? nTitan : notUnlocked(255)));
		titanFinder.setItem(25, (hasUnlocked(plugin, p, 290) ? archTitan : notUnlocked(290)));
		
		titanFinder.setItem(30, (hasUnlocked(plugin, p, 350) ? witherTitan : notUnlocked(350)));
		titanFinder.setItem(32, (hasUnlocked(plugin, p, 425) ? dragonTitan : notUnlocked(425)));
		
		// Later
		titanFinder.setItem(30, later);
		
		titanFinder.setItem(32, later); 
		
		
		return titanFinder;
	}
}
