package gamercoder215.smpcore.listeners.titan;

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

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.listeners.GUIManagers;

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
	
	protected static boolean hasUnlocked(Main plugin, Player p, int amountReq) {
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
	
	public static Inventory getTitanFinder(Main plugin, Player p) {
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

		ItemStack explosiveTitan = generateTitanItem(Material.TNT, ChatColor.DARK_GREEN + "Explosive Titan", 2.25);
		ItemMeta eMeta = explosiveTitan.getItemMeta();
		eMeta.setLore(cLore);
		explosiveTitan.setItemMeta(eMeta);
		
		// Setting
		titanFinder.setItem(10, (hasUnlocked(plugin, p, 40) ? fireTitan : notUnlocked(40)));
		titanFinder.setItem(11, (hasUnlocked(plugin, p, 45) ? magicTitan : notUnlocked(45)));
		titanFinder.setItem(12, (hasUnlocked(plugin, p, 55) ? crossbowTitan : notUnlocked(55)));
		titanFinder.setItem(13, (hasUnlocked(plugin, p, 65) ? axeTitan : notUnlocked(65)));
		titanFinder.setItem(14, (hasUnlocked(plugin, p, 80) ? knockbackTitan : notUnlocked(80)));
		titanFinder.setItem(15, (hasUnlocked(plugin, p, 90) ? potTitan : notUnlocked(90)));
		
		// Later
		titanFinder.setItem(28, later);
		titanFinder.setItem(29, later);
		titanFinder.setItem(30, later);
		titanFinder.setItem(31, later);
		titanFinder.setItem(32, later);
		titanFinder.setItem(33, later);
		titanFinder.setItem(34, later);
		
		
		return titanFinder;
	}
}
