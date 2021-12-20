package us.teaminceptus.smpcore.bosses.drops;

import java.util.ArrayList;
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

import us.teaminceptus.smpcore.SMPCore;

public class SnowPrinceDrops implements Listener {
	public SMPCore plugin;
	
	public SnowPrinceDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDeathPrince(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.SNOWMAN))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getCustomName().contains("Snow Prince"))) return;
		
		e.setDroppedExp((r.nextInt(250 - 150) + 150));
		Location deathLoc = e.getEntity().getLocation();
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.SNOW_BLOCK, 64));
		
		ItemStack freezeWand = new ItemStack(Material.STICK, 1);
		ItemMeta freezeMeta = freezeWand.getItemMeta();
		freezeMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Freeze Wand");
		
		ArrayList<String> freezeLore = new ArrayList<String>();
		freezeLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Opposite from the Blaze Sword,");
		freezeLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "it can freeze anyone for" + ChatColor.GREEN + "" + ChatColor.ITALIC + " 5");
		freezeLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "seconds.");
		
		freezeMeta.setLore(freezeLore);
		freezeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		freezeMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		
		freezeWand.setItemMeta(freezeMeta);
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, freezeWand);
		
		if (r.nextBoolean() == true) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BLUE_ICE, 48));
		}
	}
}
