package gamercoder215.smpcore.bosses.drops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.bosses.abilities.CreeperKingAbilities;

public class CreeperKingDrops implements Listener {
	
	public Main plugin;
	
	public CreeperKingDrops(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.CREEPER))) return;
		if (!(e.getEntity().getCustomName().contains("Creeper King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		CreeperKingAbilities.announceDialogue("Ok, I won't speak Javanese this time. Good job.");
		
		e.getEntity().getNearbyEntities(20, 20, 20).forEach(en -> {
			if (en.getType().equals(EntityType.PLAYER)) {
				Player p = (Player) en;
				p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 3F, 0.5F);
			}
		});
		
		ItemStack tntEssence = new ItemStack(Material.LIGHT_GRAY_DYE, 1);
		ItemMeta tntMeta = tntEssence.getItemMeta();
		tntMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		tntMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		tntMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Essence of Trinitrotoluene");
		tntEssence.setItemMeta(tntMeta);
		
		ItemStack creeperHandle = new ItemStack(Material.STICK, 1);
		ItemMeta handMeta = creeperHandle.getItemMeta();
		handMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		handMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
		handMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Creeper Handle");
		creeperHandle.setItemMeta(handMeta);
		
		Location deathLoc = e.getEntity().getLocation();
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GUNPOWDER, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GUNPOWDER, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GUNPOWDER, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GUNPOWDER, 64));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.MUSIC_DISC_BLOCKS, 1));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.MUSIC_DISC_CAT, 1));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.MUSIC_DISC_MELLOHI, 1));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.CREEPER_HEAD, 16));
		
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 64));
		deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.TNT, 32));
		
		if (r.nextBoolean() == true && r.nextBoolean() == true) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, tntEssence);
		}
		
		if (r.nextBoolean() == true) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, creeperHandle);
		}
	}

}
