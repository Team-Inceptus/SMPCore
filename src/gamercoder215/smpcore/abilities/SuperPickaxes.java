package gamercoder215.smpcore.abilities;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public class SuperPickaxes implements Listener {
	
	private Main plugin;
	
	public SuperPickaxes(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	ArrayList<UUID> stevenCooldownLeft = new ArrayList<UUID>();
	ArrayList<UUID> stevenCooldownRight = new ArrayList<UUID>();

	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		ItemStack item = e.getItem();
		if (item.getItemMeta() == null) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		Player p = e.getPlayer();
		Action clickedAction = e.getAction();
		
		if (itemMeta.getDisplayName().contains("Steven's Drill") && itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
			if (clickedAction == Action.LEFT_CLICK_AIR) {
				if (stevenCooldownLeft.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "Your Haste Ability is on a cooldown!");
				} else {
					stevenCooldownLeft.add(p.getUniqueId());
					p.sendMessage(ChatColor.DARK_AQUA + "You have used your Haste Ability!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 4, true, false, true));
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Your Haste Ability cooldown has been refreshed!");
							stevenCooldownLeft.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 45 * 20);
				}
			} else if (clickedAction == Action.RIGHT_CLICK_AIR) {
				if (stevenCooldownRight.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "Your Explosion Ability is on a cooldown!");
				} else {
					stevenCooldownRight.add(p.getUniqueId());
					p.sendMessage(ChatColor.DARK_AQUA + "You have used your Explosion Ability!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 255, true, false, false));
					p.getWorld().createExplosion(p.getLocation(), 15F, false, true, p);
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Your Explosion Ability cooldown has been refreshed!");
							stevenCooldownRight.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 120 * 20);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		ItemMeta handMeta = mainhand.getItemMeta();
		
		if (!(handMeta.hasDisplayName())) return;
		if (handMeta.getDisplayName().contains("Telekinetic Pickaxe") && handMeta.isUnbreakable() && handMeta.hasLore() && handMeta.getEnchantLevel(Enchantment.DIG_SPEED) == 35 && handMeta.hasAttributeModifiers()) {
			e.setDropItems(false);
			for (ItemStack i : b.getDrops()) {
				p.getInventory().addItem(i);
			}
		}
	}
}
