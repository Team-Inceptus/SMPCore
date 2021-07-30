package gamercoder215.smpcore.listeners.titan;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.listeners.GUIManagers;
import gamercoder215.smpcore.utils.fetcher.EnchantmentFetcher;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

public class TitanEnchantmentTable {
	
	public static boolean hasUnlocked(Main plugin, Player p) {
		return (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_kills") >= 30);
	}
	
	
	public static ItemStack getInnerPlaceholder() {
		ItemStack innerPlaceholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta innerMeta = innerPlaceholder.getItemMeta();
		innerMeta.setDisplayName(" ");
		innerPlaceholder.setItemMeta(innerMeta);
		
		return innerPlaceholder;
	}
	
	public static Inventory getTitanEnchantTable() {
		Inventory titanEtable = GUIManagers.generateGUI(54, ChatColor.DARK_AQUA + "Titan Enchant Table");
		
		// Default Sets
		for (int i = 10; i <= 16; i++) {
			titanEtable.setItem(i, getInnerPlaceholder());
		}
		
		for (int i = 19; i <= 25; i++) {
			titanEtable.setItem(i, getInnerPlaceholder());
		}
		
		for (int i = 28; i <= 34; i++) {
			titanEtable.setItem(i, getInnerPlaceholder());
		}
		
		for (int i = 37; i <= 43; i++) {
			titanEtable.setItem(i, getInnerPlaceholder());
		}
		
		ItemStack enchantInfo = new ItemStack(Material.ENCHANTING_TABLE, 1);
		ItemMeta infoMeta = enchantInfo.getItemMeta();
		infoMeta.setDisplayName(ChatColor.AQUA + "Titan Enchant Table");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "These are applied to the item");
		lore.add(ChatColor.GRAY + "in the empty slot below.");
		infoMeta.setLore(lore);
		enchantInfo.setItemMeta(infoMeta);
		
		titanEtable.setItem(4, enchantInfo);
		
		titanEtable.setItem(10, EnchantmentFetcher.doubleDamage.generateItemStack());
		titanEtable.setItem(11, EnchantmentFetcher.weakening.generateItemStack());
		titanEtable.setItem(12, EnchantmentFetcher.withering.generateItemStack());
		titanEtable.setItem(13, EnchantmentFetcher.poisoning.generateItemStack());
		
		/*
		titanEtable.setItem(14, EnchantmentFetcher.fireImmunity.generateItemStack());
		titanEtable.setItem(15, EnchantmentFetcher.explosionImmunity.generateItemStack());
		titanEtable.setItem(16, EnchantmentFetcher.projectileImmunity.generateItemStack());
		
		titanEtable.setItem(19, EnchantmentFetcher.lightningImmunity.generateItemStack());
		titanEtable.setItem(20, EnchantmentFetcher.potionImmunity.generateItemStack());
		titanEtable.setItem(21, EnchantmentFetcher.fallingImmunity.generateItemStack());
		titanEtable.setItem(22, EnchantmentFetcher.witherImmunity.generateItemStack());
		titanEtable.setItem(23, EnchantmentFetcher.smelting.generateItemStack());
		
		*/
		
		titanEtable.setItem(49, null);
		
		return titanEtable;
	}
	
	public static ArrayList<ItemStack> getEligibleItems() {
		ArrayList<ItemStack> eligible = new ArrayList<ItemStack>();
		
		for (ItemStack i : TitanFetcher.getTitanWeapons()) {
			eligible.add(i);
		}
		
		TitanFetcher.getTitanArmors().forEach(arrayi -> {
			for (ItemStack i : arrayi) {
				eligible.add(i);
			}
		});
		
		return eligible;
	}
	
	public static boolean isEligible(ItemStack i) {
		for (ItemStack it : getEligibleItems()) {
			if (it.isSimilar(i)) return true;
		}
		
		return false;
	}
	
}
