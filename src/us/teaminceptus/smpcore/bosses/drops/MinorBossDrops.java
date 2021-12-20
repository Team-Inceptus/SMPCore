package us.teaminceptus.smpcore.bosses.drops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.fetcher.ItemFetcher;

public class MinorBossDrops implements Listener {
	
	public SMPCore plugin;
	
	public MinorBossDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		ItemStack netherEssence = new ItemStack(Material.RED_DYE, 1);
		ItemMeta netherMeta = netherEssence.getItemMeta();
		netherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		netherMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Essence");
		netherMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		netherEssence.setItemMeta(netherMeta);
		
		ItemStack enchantedGoldIngot = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta goldMeta = enchantedGoldIngot.getItemMeta();
		goldMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		goldMeta.setDisplayName(ChatColor.GOLD + "Enchanted Gold Ingot");
		goldMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		enchantedGoldIngot.setItemMeta(goldMeta);
		
		Entity en = e.getEntity();
		
		Random r = new Random();
		Location deathLoc = en.getLocation();
		if (en.getCustomName().contains("The Hogatar") && e.getEntityType().equals(EntityType.HOGLIN) && en.isGlowing()) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.COOKED_PORKCHOP, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.CRIMSON_FUNGUS, 32));
			if (r.nextInt(100) < 8) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, netherEssence);
				Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "CRAZY RARE DROP!" + ChatColor.RESET + ChatColor.DARK_RED + " Nether Essence"
				+ ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " from " + ChatColor.RED + "The Hogatar" + ChatColor.GRAY + ".");
			}
		} else if (en.getCustomName().contains("Super Sniper") && e.getEntityType().equals(EntityType.SKELETON) && en.isGlowing()) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BONE, 48));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.SPECTRAL_ARROW, 48));
			if (r.nextBoolean() == true) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.SKELETON_SKULL, 12));
			}
			if (r.nextInt(4) == 1) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.WITHER_SKELETON_SKULL, 6));
			}
		} else if (en.getCustomName().contains("Rotten Army")) {
			String name = en.getCustomName();
			if (name.contains("Private of Rotten Army")) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 24));
			} else if (name.contains("Specialist of Rotten Army")) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.BONE, 24));
			} else if (name.contains("Seargant of Rotten Army")) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 48));
				if (r.nextBoolean() == true) {
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
					deathLoc.getWorld().dropItemNaturally(deathLoc, enchantedGoldIngot);
				}
				if (r.nextInt(1000) < 6) {
					deathLoc.getWorld().dropItemNaturally(deathLoc, netherEssence);
					Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "CRAZY RARE DROP!" + ChatColor.RESET + ChatColor.DARK_RED + " Nether Essence"
							+ ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " from " + ChatColor.DARK_GREEN + "Seargant of Rotten Army" + ChatColor.GRAY + ".");
				}
			}
		} else if (en.getCustomName().contains("Warglin") && en.getType().equals(EntityType.PIGLIN_BRUTE)) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLDEN_AXE, 1));
			if (r.nextInt(100) < 8) {
				Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "CRAZY RARE DROP!" + ChatColor.RESET + ChatColor.DARK_RED + " Nether Essence"
				+ ChatColor.DARK_GRAY + " x1" + ChatColor.GRAY + " from " + ChatColor.DARK_RED + "Warglin" + ChatColor.GRAY + ".");
			}
		} else if (en.getCustomName().contains("Rotten Army Air Support") && en.getType().equals(EntityType.GHAST)) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GHAST_TEAR, 48));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ROTTEN_FLESH, 32));
		} else if (en.getCustomName().contains("Magillager")) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.EMERALD, 64));
		} else if (en.getCustomName().contains("Netherite Skeleton")) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.NETHERITE_INGOT, 4));
		} else if (en.getCustomName().contains("Witherman")) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ENDER_PEARL, 16));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ENDER_PEARL, 16));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.ENDER_PEARL, 16));
			
			if (r.nextBoolean() == true) {
				deathLoc.getWorld().dropItemNaturally(deathLoc, ItemFetcher.getEndEssence());
			}
		}
	}
}
