package gamercoder215.smpcore.creatures;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.Creatures;

public class CreatureGUI implements Listener {
	
	public Main plugin;
	
	public CreatureGUI(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	ArrayList<UUID> creatureCooldown = new ArrayList<UUID>();
	@EventHandler
	public void onInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		InventoryView inv = e.getView();
		if (!(inv.getTitle().contains("SMP Creatures Menu"))) return;
		e.setCancelled(true);
		
        ItemStack clickedItem = e.getCurrentItem();
        
        String displayName = clickedItem.getItemMeta().getDisplayName();
        
        if (creatureCooldown.contains(p.getUniqueId())) {
        	p.sendMessage(ChatColor.RED + "You are too tired to look for another creature. Look back in an hour...");
        } else {
	        if (displayName.contains("Ice Golem")) {
	        	Creatures.spawnCreature(p, "Ice Golem");
	        } else if (displayName.contains("Iron Horse")) {
	        	Creatures.spawnCreature(p, "Iron Horse");
	        }
	        
	        
	        if (!(p.isOp())) {
	        	creatureCooldown.add(p.getUniqueId());
	            new BukkitRunnable() {
	            	public void run() {
	            		creatureCooldown.remove(p.getUniqueId());
	            		p.sendMessage(ChatColor.GREEN + "You can spawn another creature!");
	            		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
	            	}
	            }.runTaskLater(plugin, 20 * 60 * 60);
	        }
        }
	}
}
