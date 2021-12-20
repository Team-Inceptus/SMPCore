package us.teaminceptus.smpcore.abilities;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;

public class PlayerAbilities implements Listener {
	
	public SMPCore plugin;
	
	public PlayerAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	ArrayList<UUID> tier1cooldowns = new ArrayList<UUID>();
	ArrayList<UUID> tier2cooldowns = new ArrayList<UUID>();
	ArrayList<UUID> tier3cooldowns = new ArrayList<UUID>();
	
	private void initiateCooldown(Player p, int tier) {
		if (tier > 3) tier = 3;
		if (tier == 1) {
			tier1cooldowns.add(p.getUniqueId());
			new BukkitRunnable() {
				public void run() {
					tier1cooldowns.remove(p.getUniqueId());
					p.sendMessage(ChatColor.GREEN + "Your Tier 1 Ability Cooldown has been refreshed!");
				}
			}.runTaskLater(plugin, 20 * 30);
		} else if (tier == 2) {
			tier2cooldowns.add(p.getUniqueId());
			new BukkitRunnable() {
				public void run() {
					tier2cooldowns.remove(p.getUniqueId());
					p.sendMessage(ChatColor.GREEN + "Your Tier 2 Ability Cooldown has been refreshed!");
				}
			}.runTaskLater(plugin, 20 * 60);
		} else if (tier == 3) {
			tier3cooldowns.add(p.getUniqueId());
			new BukkitRunnable() {
				public void run() {
					tier3cooldowns.remove(p.getUniqueId());
					p.sendMessage(ChatColor.DARK_GREEN + "Your Tier 3 Ability Cooldown has been refreshed!");
				}
			}.runTaskLater(plugin, 20 * 180);
		}
	}
	
	private boolean cooldownCheck(Player p, int tier) {
		if (tier > 3) tier = 3;
		if (tier < 1) tier = 1;
		
		if (tier == 1) {
			return (!(tier1cooldowns.contains(p.getUniqueId())));
		} else if (tier == 2) {
			return (!(tier2cooldowns.contains(p.getUniqueId())));
		} else if (tier == 3) {
			return (!(tier3cooldowns.contains(p.getUniqueId())));
		} else return false;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;
		if (e.getWhoClicked() == null) return;
        Player p = (Player)e.getWhoClicked();
        Inventory inventory = e.getClickedInventory();
        if (!(inventory.contains(GUIManagers.getInventoryPlaceholder()))) return;
        InventoryView inv = e.getView();
        
        if (!(inv.getTitle().contains("Player Abilities"))) return;
        
        e.setCancelled(true);
        
        ItemStack clickedItem = e.getCurrentItem();
        if (!(clickedItem.hasItemMeta())) return;
        if (!(clickedItem.getItemMeta().hasDisplayName()));
        String itemName = clickedItem.getItemMeta().getDisplayName();
        String abilityCooldown1 = ChatColor.RED + "You can only use a Tier 1 Ability Every 30s!";
        String abilityCooldown2 = ChatColor.RED + "You can only use a Tier 2 Ability Every minute!";
        // String abilityCooldown3 = ChatColor.RED + "You can only use a Tier 3 Ability Every 3 minutes!";
        
        if (itemName.contains("Ground Pound")) {
        	if (!cooldownCheck(p, 1)) {
        		p.sendMessage(abilityCooldown1);
        	} else {
        		initiateCooldown(p, 1);
        		p.getNearbyEntities(5, 5, 5).forEach(en -> {
        			if (en instanceof LivingEntity && en.isOnGround()) {
        				LivingEntity len = (LivingEntity) en;
        				if (!len.isCustomNameVisible() && !len.isGlowing()) {
        					len.damage(10, p);
        				}
        			}
        		});
        		p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 3F, 0.5F);
        	}
        	p.closeInventory();
        } else if (itemName.contains("Max Heal")) {
        	if (!cooldownCheck(p, 2)) {
        		p.sendMessage(abilityCooldown2);
        	} else {
        		if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == p.getHealth()) {
        			p.sendMessage(ChatColor.AQUA + "You are at Max Health! Ability Cancelled.");
        		} else {
        			initiateCooldown(p, 2);
        			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        			p.sendMessage(ChatColor.GREEN + "You have been healed to Max HP!");
        		}
        		
        	}
        	p.closeInventory();
        }
	}
}
