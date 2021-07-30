package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.listeners.GUIManagers;
import gamercoder215.smpcore.utils.classes.PrimarySpell;
import gamercoder215.smpcore.utils.classes.SecondarySpell;
import gamercoder215.smpcore.utils.classes.Wand;
import gamercoder215.smpcore.utils.fetcher.WandFetcher;

public class WandInfo implements CommandExecutor {
	
	public Main plugin;
	
	public WandInfo(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("wandinfo").setExecutor(this);
	}
	
	private Wand getWand(Player p, String name) {
		String parsedName = name.toLowerCase();
		
		if (parsedName.contains("explosion")) return WandFetcher.getExplosionWand(p, plugin);
		else if (parsedName.contains("lightning")) return WandFetcher.getLightningWand(p, plugin);
		else if (parsedName.contains("damage")) return WandFetcher.getDamageWand(p, plugin);
		else if (parsedName.contains("withermeal")) return WandFetcher.getWithermeal(p, plugin);
		else if (parsedName.contains("end")) return WandFetcher.getEnderWand(p, plugin);
		else if (parsedName.contains("immutatio")) return WandFetcher.getLightningWand(p, plugin);
		else if (parsedName.contains("inferno")) return WandFetcher.getInfernoWand(p, plugin);
		else if (parsedName.contains("healing")) return WandFetcher.getHealingWand(p, plugin);
		else return null;
	}
	
	private Material getSpellMaterial(PrimarySpell spell) {
		if (spell.name.contains("Super Explosion")) return Material.TNT;
		else if (spell.name.contains("Heal")) return Material.GOLDEN_APPLE;
		else if (spell.name.contains("Smiter")) return Material.DIAMOND_SWORD;
		else if (spell.name.contains("End Killer")) return Material.ENDER_EYE;
		else if (spell.name.contains("Hasty Changer")) return Material.GOLDEN_PICKAXE;
		else if (spell.name.contains("Inferno Fireball")) return Material.FIRE_CHARGE;
		else if (spell.name.contains("Wither Vampire")) return Material.WITHER_SKELETON_SKULL;
		else if (spell.name.contains("Damage Aura")) return Material.NETHERITE_SWORD;
		else return Material.EMERALD;
	}
	
	private Material getSpellMaterial(SecondarySpell spell) {
		if (spell.name.contains("Explosive Fireball")) return Material.FIRE_CHARGE;
		else if (spell.name.contains("Regeneration")) return Material.APPLE;
		else if (spell.name.contains("Lightning Forcefield")) return Material.OBSIDIAN;
		else if (spell.name.contains("Teleporter")) return Material.ENDER_PEARL;
		else if (spell.name.contains("Immutatio's Flight")) return Material.FEATHER;
		else if (spell.name.contains("Fire Aura")) return Material.FLINT_AND_STEEL;
		else if (spell.name.contains("Wither Grower")) return Material.BONE_MEAL;
		else if (spell.name.contains("Super Strength")) return Material.BEDROCK;
		else return Material.EMERALD;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (args.length < 1) {
			p.sendMessage(ChatColor.RED + "You need to provide a wand!");
		} else {
			
			Wand chosenWand = getWand(p, args[0]);
			
			if (chosenWand == null) {
				p.sendMessage(ChatColor.RED + "The wand you have chosen does not exist.");
			} else {
				PrimarySpell primary = chosenWand.primary;
				SecondarySpell secondary = chosenWand.secondary;
				
				Inventory infoGUI = GUIManagers.generateGUI(36, ChatColor.AQUA + "Wand Info - " + chosenWand.wandName);
				
				infoGUI.setItem(13, chosenWand.generateWand());
				infoGUI.setItem(20, primary.generateSpellInfo(getSpellMaterial(primary)));
				infoGUI.setItem(24, secondary.generateSpellInfo(getSpellMaterial(secondary)));
				
				p.openInventory(infoGUI);
				p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 3F, 1F);
			}
		}
		
		return false;
	}

}
