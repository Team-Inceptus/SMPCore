package us.teaminceptus.smpcore.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TradeParser {
	
	public static int getAmountCost(ItemStack item) {
		List<String> lore = item.getItemMeta().getLore();
		
		String costLine = lore.get(1);
		
		int cost = Integer.parseInt(ChatColor.stripColor(costLine.split("\\s")[2].replace("x", "")));
		
		return cost;
	}
	
	public static ItemStack getMaterialCost(ItemStack item) {
		List<String> lore = item.getItemMeta().getLore();
		
		String costLine = lore.get(1);
		
		ItemStack costItem = new ItemStack(Material.matchMaterial(ChatColor.stripColor(costLine.split("\\s")[1].replaceAll("-", "_").toUpperCase())), 1);
		
		return costItem;
	}
	
	public static int getAmountReward(ItemStack item) {
		List<String> lore = item.getItemMeta().getLore();
		
		String rewardLine = lore.get(2);
		
		int reward = Integer.parseInt(ChatColor.stripColor(rewardLine.split("\\s")[2].replace("x", "")));
		
		return reward;
	}
	
	public static ItemStack getMaterialReward(ItemStack item) {
		List<String> lore = item.getItemMeta().getLore();
		
		String rewardLine = lore.get(2);
		
		ItemStack rewardItem = new ItemStack(Material.matchMaterial(ChatColor.stripColor(rewardLine.split("\\s")[1].replaceAll("-", "_").toUpperCase())), 1);
		
		return rewardItem;
	}
	
}
