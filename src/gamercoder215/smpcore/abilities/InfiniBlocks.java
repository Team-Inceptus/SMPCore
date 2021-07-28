package gamercoder215.smpcore.abilities;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import gamercoder215.smpcore.Main;

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
			e.setCancelled(true);
			bl.getWorld().spawnEntity(bl.getLocation(), EntityType.PRIMED_TNT);
		} else if (handItem.getItemMeta().getDisplayName().contains("InfiniBall")) {
			e.setCancelled(true);
		}
	}
}
