package us.teaminceptus.smpcore.pets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import us.teaminceptus.smpcore.listeners.GUIManagers;

public class PetUtils {
	
	public static Inventory getPetSlots(Player p) {
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.GRAY + p.getName() + "'s Pet Slots");
		
		inv.setItem(1, GUIManagers.getInventoryPlaceholder());
		inv.setItem(3, GUIManagers.getInventoryPlaceholder());
		
		return inv;
	}
	
}
