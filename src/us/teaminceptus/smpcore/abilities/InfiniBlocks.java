package us.teaminceptus.smpcore.abilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.Main;

public class InfiniBlocks implements Listener {
	
	public Main plugin;
	
	public InfiniBlocks(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Block bl = e.getBlockPlaced();
		Player p = e.getPlayer();
		if ((p.getInventory().getItemInMainHand() == null ? p.getInventory().getItemInOffHand().getItemMeta() : p.getInventory().getItemInMainHand().getItemMeta()) == null) return;

		
		ItemStack handItem = p.getInventory().getItemInMainHand() == null ? p.getInventory().getItemInOffHand() : p.getInventory().getItemInMainHand();
		
		if (handItem.getItemMeta().getDisplayName().contains("InfiniTNT")) {
			if (p.getWorld().getName().contains("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			e.setCancelled(true);
			bl.getWorld().spawnEntity(bl.getLocation(), EntityType.PRIMED_TNT);
		} else if (handItem.getItemMeta().getDisplayName().contains("InfiniBall")) {
			if (p.getWorld().getName().contains("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			e.setCancelled(true);
		}
	}
}
