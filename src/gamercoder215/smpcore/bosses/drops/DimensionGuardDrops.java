package gamercoder215.smpcore.bosses.drops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;

public class DimensionGuardDrops implements Listener {
	public Main plugin;
	
	public DimensionGuardDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeathWither(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.WITHER))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getCustomName().contains("Dimensional Guard"))) return;
		
		Random r = new Random();
		
		e.setDroppedExp((r.nextInt(150 - 75) + 75));
		Location deathLoc = e.getEntity().getLocation();
		
		ItemStack dimensionalEssence = new ItemStack(Material.CLAY, 1);
		ItemMeta dimEssenceMeta = dimensionalEssence.getItemMeta();
		
		dimEssenceMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		dimEssenceMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Dimension Essence");
		dimEssenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		dimensionalEssence.setItemMeta(dimEssenceMeta);
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, dimensionalEssence);
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.WITHER_ROSE, 32));
		
		if (r.nextInt(100) < 10) {
			World deathLocWorld = deathLoc.getWorld();
			String worldName = deathLocWorld.getName();
			
			if (worldName == "world") {
				ItemStack overworldEssence = new ItemStack(Material.LIME_DYE, 1);
				ItemMeta overEssMeta = overworldEssence.getItemMeta();
				overEssMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
				overEssMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Overworld Essence");
				overworldEssence.setItemMeta(overEssMeta);
				
				deathLocWorld.dropItemNaturally(deathLoc, overworldEssence);
			} else if (worldName == "world_nether") {
				ItemStack netherEssence = new ItemStack(Material.RED_DYE, 1);
				ItemMeta neEssMeta = netherEssence.getItemMeta();
				neEssMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
				neEssMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Essence");
				netherEssence.setItemMeta(neEssMeta);
				
				deathLocWorld.dropItemNaturally(deathLoc, netherEssence);
			} else if (worldName == "world_the_end") {
				ItemStack endEssence = new ItemStack(Material.PURPLE_DYE, 1);
				ItemMeta eEssMeta = endEssence.getItemMeta();
				eEssMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
				eEssMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Essence");
				endEssence.setItemMeta(eEssMeta);
				
				deathLocWorld.dropItemNaturally(deathLoc, endEssence);
			}
		}
		
		if (r.nextBoolean() == true) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.WITHER_SKELETON_SKULL, 16));
		}
	}
	
	@EventHandler
	public void onDeathField(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.GHAST))) return;
		if (!(e.getEntity().getCustomName().contains("Guard's Energy Field"))) return;
		
		Bukkit.broadcastMessage(ChatColor.AQUA + "A Dimensional Guard's " + ChatColor.DARK_AQUA + "Energy Field" + ChatColor.AQUA + " has fallen!");
		Location deathLoc = e.getEntity().getLocation();
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GHAST_TEAR, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 32));
	}
}
