package gamercoder215.smpcore.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;

public class PerussiWeapons implements Listener {
	
	public Main plugin;
	
	public PerussiWeapons(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		Player p = (Player) e.getDamager();
		
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		ItemMeta handMeta = mainhand.getItemMeta();
		if (!(handMeta.hasDisplayName())) return;
		if (!(handMeta.getDisplayName().contains("Perussi"))) return;
		if (!(handMeta.isUnbreakable())) return;
		if (!(handMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))) return;
		if (!(handMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES))) return;
		if (!(handMeta.hasLore())) return;
		
		if (e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains("Titan")) return;
		
		if (handMeta.getDisplayName().contains("Titan Wither Perussi") && mainhand.getType().equals(Material.NETHERITE_AXE)) {
			e.setDamage(e.getDamage() + (p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER) / 5) * 10);
		} else if (handMeta.getDisplayName().contains("Titan Spider Perussi") && mainhand.getType().equals(Material.IRON_SWORD)) {
			e.setDamage(e.getDamage() + (p.getStatistic(Statistic.KILL_ENTITY, EntityType.SPIDER) / 50) * 10);
		} else if (handMeta.getDisplayName().contains("Titan Skeleton Perussi") && mainhand.getType().equals(Material.IRON_SWORD)) {
			e.setDamage(e.getDamage() + (p.getStatistic(Statistic.KILL_ENTITY, EntityType.SKELETON) / 50) * 10);
		} else if (handMeta.getDisplayName().contains("Titan Dragon Perussi") && mainhand.getType().equals(Material.NETHERITE_SWORD)) {
			e.setDamage(e.getDamage() + p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON) * 25);
			
			if (e.getEntity().getCustomName() == null) return;
			if (e.getEntity().getType().equals(EntityType.ENDER_DRAGON)) {
				e.setDamage(e.getDamage() + 125);
			} else if (e.getEntity().getType().equals(EntityType.ENDERMAN)) {
				e.setDamage(e.getDamage() + 300);
			}
		}
	}

}
