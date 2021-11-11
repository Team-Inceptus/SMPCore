package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.utils.classes.PlayerAbility;
import us.teaminceptus.smpcore.utils.enums.AbilityType;

public class Abilities implements CommandExecutor {
	
	public Main plugin;
	
	public Abilities(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("abilities").setExecutor(this);
	}
	
	public static ItemStack getLockedAbility(String requirement) {
		ItemStack lockedAbility = new ItemStack(Material.BARRIER, 1);
		ItemMeta lockedMeta = lockedAbility.getItemMeta();
		
		lockedMeta.setDisplayName(ChatColor.RED + "You haven't unlocked this ability yet!");
		
		ArrayList<String> lockedInfo = new ArrayList<String>();
		lockedInfo.add("");
		lockedInfo.add(ChatColor.RED + "Requirement:");
		lockedInfo.add(ChatColor.GRAY + "- " + requirement);
		lockedInfo.add("");
		
		lockedMeta.setLore(lockedInfo);
		
		lockedAbility.setItemMeta(lockedMeta);
		
		return lockedAbility;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "GUIs can only be executed by players.");
		
		Player p = (Player) sender;
		
		Inventory abilityGUI = GUIManagers.generateGUI(45, ChatColor.AQUA + "" + ChatColor.BOLD + "Player Abilities");
		
		ArrayList<String> groundInfo = new ArrayList<String>();
		
		groundInfo.add("Create a ground pound all");
		groundInfo.add("around you in a 5x5x5 area,");
		groundInfo.add("damaging all mobs around you.");
		
		PlayerAbility groundPound = new PlayerAbility(p, ChatColor.GRAY + "Ground Pound", groundInfo, AbilityType.HARM_ENTITY, 50, EntityType.ZOMBIE);
		
		ArrayList<String> maxHealInfo = new ArrayList<String>();
		
		groundInfo.add("Heals you up to max health.");
		
		PlayerAbility maxHeal = new PlayerAbility(p, ChatColor.RED + "Max Heal", maxHealInfo, AbilityType.HEAL_PLAYER, 25, EntityType.ENDER_DRAGON);
		
		// Setting Locked
		abilityGUI.setItem(10, getLockedAbility("50 Zombies Killed"));
		abilityGUI.setItem(19, getLockedAbility("25 Dragons Killed"));
		
		// Setting Unlocked
		if (groundPound.hasUnlocked()) {
			abilityGUI.setItem(10, groundPound.getItemStackInfo(Material.STONE, true));
		}
		
		if (maxHeal.hasUnlocked()) {
			abilityGUI.setItem(19, maxHeal.getItemStackInfo(Material.GOLDEN_APPLE, false));
		}
		
		p.openInventory(abilityGUI);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3F, 1.5F);
		
		return false;
	}

}
