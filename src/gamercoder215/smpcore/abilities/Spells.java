package gamercoder215.smpcore.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.classes.Wand;
import gamercoder215.smpcore.utils.fetcher.WandFetcher;

	public class Spells implements Listener {
	
	private Main plugin;
	
	public Spells(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteractItem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		if (p.getInventory().getItemInMainHand() == null) return;
		
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		ItemMeta handMeta = mainhand.getItemMeta();
		if (!(handMeta.hasDisplayName())) return;
		
		Wand explosionWand = WandFetcher.getExplosionWand(p, plugin);
		Wand healWand = WandFetcher.getHealingWand(p, plugin);
		Wand lightningWand = WandFetcher.getLightningWand(p, plugin);
		Wand endWand = WandFetcher.getEnderWand(p, plugin);
		Wand immutatioWand = WandFetcher.getImmutoWand(p, plugin);
		Wand infernoWand = WandFetcher.getInfernoWand(p, plugin);
		Wand withermeal = WandFetcher.getWithermeal(p, plugin);
		Wand damageWand = WandFetcher.getDamageWand(p, plugin);
		
		if (handMeta.getDisplayName().contains(explosionWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(explosionWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR)) {
				explosionWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR)) {
				explosionWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(healWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(healWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR)) {
				healWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR)) {
				healWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(lightningWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(lightningWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
				lightningWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				lightningWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(endWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(endWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR)) {
				endWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR)) {
				endWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(immutatioWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(immutatioWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
				immutatioWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				immutatioWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(infernoWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(infernoWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
				infernoWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				infernoWand.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(withermeal.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(withermeal.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
				withermeal.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				withermeal.executeSecondary();
			}
		} else if (handMeta.getDisplayName().contains(damageWand.generateWand().getItemMeta().getDisplayName()) && handMeta.hasLore() && mainhand.getType().equals(damageWand.generateWand().getType())) {
			if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
				damageWand.executePrimary();
			} else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				damageWand.executeSecondary();
			}
		}
	}
}
