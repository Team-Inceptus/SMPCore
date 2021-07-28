package gamercoder215.smpcore.bosses.drops;

import java.util.ArrayList;
import java.util.List;
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

public class BlazeKingDrops implements Listener {
	public Main plugin;
	
	public BlazeKingDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.BLAZE))) return;
		if (!(e.getEntity().getCustomName().contains("Blaze King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Random r = new Random();
		
		e.setDroppedExp((r.nextInt(100 - 50) + 50));
		Location deathLoc = e.getEntity().getLocation();
		
		
		ItemStack blazeRodDrops = new ItemStack(Material.BLAZE_ROD, (r.nextInt(50 - 25) + 25));
		deathLoc.getWorld().dropItemNaturally(deathLoc, blazeRodDrops);
		
		ItemStack blazeSword = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta blazeSwordMeta = blazeSword.getItemMeta();
		blazeSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
		blazeSwordMeta.addEnchant(Enchantment.DURABILITY, 30, true);
		blazeSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 25, true);
		
		List<String> blazeSwordLore = new ArrayList<>();
		blazeSwordLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Forged from the blaze king,");
		blazeSwordLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "can set anything on fire for");
		blazeSwordLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "a very long time. Can also");
		blazeSwordLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "remove equipped Fire Resistance.");
		
		blazeSwordMeta.setLore(blazeSwordLore);
		blazeSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		blazeSwordMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Blaze Sword");
		
		blazeSword.setItemMeta(blazeSwordMeta);
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, blazeSword);
	}
}
