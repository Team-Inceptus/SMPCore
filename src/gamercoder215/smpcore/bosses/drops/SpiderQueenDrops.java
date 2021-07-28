package gamercoder215.smpcore.bosses.drops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;

public class SpiderQueenDrops implements Listener {
	public Main plugin;
	
	public SpiderQueenDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.SPIDER))) return;
		if (!(e.getEntity().getCustomName().contains("Spider Queen"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Random r = new Random();
		
		Location deathLoc = e.getEntity().getLocation();
		
		ItemStack taraSword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta taraMeta = taraSword.getItemMeta();
		taraMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Tarantula Sword");
		taraMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 100, true);
		taraMeta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
		taraMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 10, true);
		taraMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 4, true);
		taraMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
		taraMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		taraMeta.setUnbreakable(true);
		taraSword.setItemMeta(taraMeta);
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.STRING, (r.nextInt(64 - 55) + 55)));
		deathLoc.getWorld().dropItemNaturally(deathLoc, taraSword);
	}
}
