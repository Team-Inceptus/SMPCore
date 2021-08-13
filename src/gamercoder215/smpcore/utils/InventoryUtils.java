package gamercoder215.smpcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;

import gamercoder215.smpcore.Main;

public class InventoryUtils implements Listener {
	
	protected Main plugin;
	
	public InventoryUtils(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory() == null) return;
		if (e.getWhoClicked() == null) return;
		
		if (!(e.getInventory() instanceof AnvilInventory)) return;
		
		AnvilInventory inv = (AnvilInventory) e.getInventory();
		
		if (inv.getRenameText().length() < 1) return;
		
		if (e.getCurrentItem() == null) return;
		
		
		
		if (e.getSlotType() == null) return;
		if (!(e.getSlotType().equals(SlotType.RESULT))) return;
		
		if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) return;
		if (e.getCurrentItem().getType().equals(Material.AXOLOTL_BUCKET)) return;
		if (e.getCurrentItem().getType().equals(Material.TROPICAL_FISH_BUCKET)) return;
		
		e.setCancelled(true);
		e.getWhoClicked().sendMessage(ChatColor.RED + "You cannot rename items! The Exceptions are:\n- Name Tags\n- Axolotl Buckets\n- Tropical Fish Buckets");
	}
}
