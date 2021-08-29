package gamercoder215.smpcore.listeners.caves;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;

public class DeltaCave extends Cave implements Listener {
	
	public DeltaCave(Main plugin) {
		super(plugin, null, 0);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static ItemStack getDeltaStone() {
		ItemStack dStone = new ItemStack(Material.STONE, 1);
		ItemMeta dMeta = dStone.getItemMeta();
		dMeta.setDisplayName(ChatColor.DARK_GRAY + "Delta Stone");
		dStone.setItemMeta(dMeta);
		
		return dStone;
	}
	
	public static ItemStack getDeltarack() {
		ItemStack drack = new ItemStack(Material.NETHERRACK, 1);
		
		return drack;
	}

}
