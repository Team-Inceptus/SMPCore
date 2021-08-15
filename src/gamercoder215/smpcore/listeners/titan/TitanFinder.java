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
		Inventory titanFinder = GUIManagers.generateGUI(45, ChatColor.GRAY + "" + ChatColor.BOLD + "Titan Finder");
		
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
		
		
		// Setting
		titanFinder.setItem(10, (hasUnlocked(plugin, p, 40) ? fireTitan : notUnlocked(40)));
		titanFinder.setItem(11, (hasUnlocked(plugin, p, 50) ? magicTitan : notUnlocked(50)));
		
		// Later
		titanFinder.setItem(37, later);
		titanFinder.setItem(38, later);
		titanFinder.setItem(39, later);
		titanFinder.setItem(40, later);
		titanFinder.setItem(41, later);
		titanFinder.setItem(42, later);
		titanFinder.setItem(43, later);
		
		
		return titanFinder;
	}
}
