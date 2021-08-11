package gamercoder215.smpcore.listeners.titan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
	
	public static ItemStack generateTitanItem(Material icon, String name, int rating) {
		ItemStack item = new ItemStack(icon, 1);
		
		if (rating > 10) rating = 10;
		
		ItemMeta iMeta = item.getItemMeta();
		iMeta.setDisplayName(name + ChatColor.DARK_GRAY + " Rating: " + Integer.toString(rating) + " / 10");
		
		iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		iMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
		
		item.setItemMeta(iMeta);
		
		return item;
	}
	
	public static Inventory getTitanFinder() {
		Inventory titanFinder = GUIManagers.generateGUI(45, ChatColor.GRAY + "Titan Finder");
		
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
