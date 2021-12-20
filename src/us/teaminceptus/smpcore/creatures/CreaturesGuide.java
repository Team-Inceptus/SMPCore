package us.teaminceptus.smpcore.creatures;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.utils.CreatureUtils;

public class CreaturesGuide implements CommandExecutor {
	
	public SMPCore plugin;
	
	public CreaturesGuide(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("creaturesguide").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "GUIs can only be executed by players.");
		
		Player p = (Player) sender;
		
		Inventory creatureGUI = GUIManagers.generateGUI(54, ChatColor.DARK_GRAY + "SMP Creatures Menu");
		
		// V Cmn
		ArrayList<String> iceDescription = new ArrayList<String>();
		iceDescription.add("A relatively new sub-species");
		iceDescription.add("of golems, was concieved in");
		iceDescription.add("the ice spikes.");
		
		ArrayList<String> iceDrops = new ArrayList<String>();
		iceDrops.add(ChatColor.AQUA + "Ice x2 " + ChatColor.DARK_GRAY + "(100%)");
		iceDrops.add(ChatColor.DARK_AQUA + "Icey Boots x1 " + ChatColor.DARK_GRAY + "(100%)");
		
		ItemStack iceGolem = CreatureUtils.generateCreatureItem(Material.ICE, "Very Common", ChatColor.AQUA + "Ice Golem", "Passive", "300 years, 5 months, 12 days", iceDescription, iceDrops, false);
		
		
		// Cmn
		ArrayList<String> ironDescription = new ArrayList<String>();
		ironDescription.add("Horses that had a rare");
		ironDescription.add("disease that infused");
		ironDescription.add("their DNA with iron.");
		
		ArrayList<String> ironDrops = new ArrayList<String>();
		ironDrops.add(ChatColor.WHITE + "Iron Ingot x16 " + ChatColor.DARK_GRAY + "(100%)");
		ironDrops.add(ChatColor.DARK_GRAY + "Damaged Iron Horsearmor x1" + "(100%)");
		
		ItemStack ironHorse = CreatureUtils.generateCreatureItem(Material.IRON_INGOT, "Common", ChatColor.WHITE + "Iron Horse", "Passive", "150 years, 7 months, 21 days", ironDescription, ironDrops, true);
		// UCmn
		// Rr
		
		// V Rr
		// Ex Rr
		// Uni
		// OOAK
		
		// Setting
		creatureGUI.setItem(10, iceGolem);
		creatureGUI.setItem(19, ironHorse);
		
		if (p.getWorld().getName().contains("world_titan")) {
			p.sendMessage(ChatColor.RED + "You are too weak to summon a creature here...");
		} else {
			p.openInventory(creatureGUI);
		}
		
		return false;
	}
}
