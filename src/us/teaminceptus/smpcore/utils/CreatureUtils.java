package us.teaminceptus.smpcore.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreatureUtils {
	
	
	private static String parseRarity(String rarity) {
		if (rarity.toLowerCase().contains("very common")) return (ChatColor.WHITE + "(Very Common)");
		else if (rarity.toLowerCase().contains("common")) return (ChatColor.GREEN + "(Common)");
		else if (rarity.toLowerCase().contains("uncommon")) return (ChatColor.DARK_GREEN + "(Uncommon)");
		else if (rarity.toLowerCase().contains("rare")) return (ChatColor.BLUE + "(Rare)");
		else if (rarity.toLowerCase().contains("super rare")) return (ChatColor.DARK_BLUE + "(SUPER RARE)");
		else if (rarity.toLowerCase().contains("extremely rare")) return (ChatColor.DARK_PURPLE + "(EXTREMELY RARE)");
		else if (rarity.toLowerCase().contains("unique")) return (ChatColor.GOLD + "" + ChatColor.BOLD + "(UNIQUE)");
		else if (rarity.toLowerCase().contains("nearly extinct")) return (ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "(NEARLY EXTINCT)");
		else if (rarity.toLowerCase().contains("one of a kind")) return (ChatColor.DARK_RED + "" + ChatColor.BOLD + "(ONE-OF-A-KIND)");
		else return (ChatColor.GRAY + "(" + rarity + ")");
	}
	
	private static String parseHostility(String hostility) {
		if (hostility.toLowerCase().contains("passive")) return (ChatColor.GREEN + "Passive");
		else if (hostility.toLowerCase().contains("neutral")) return (ChatColor.YELLOW + "Neutral");
		else if (hostility.toLowerCase().contains("aggressive")) return (ChatColor.RED + "Aggressive");
		else return (ChatColor.GRAY + "Unknown");
	}
	
	
	public static ItemStack generateCreatureItem(Material mat, String rarity, String label, String hostility, String speciesAge, ArrayList<String> desc, ArrayList<String> drops, boolean isRideable) {
		ItemStack creatureItem = new ItemStack(mat, 1);
		ItemMeta creatureMeta = creatureItem.getItemMeta();
		
		ArrayList<String> creatureLore = new ArrayList<String>();
		
		creatureLore.add("");
		for (String line : desc) {
			creatureLore.add(ChatColor.GRAY + line);
		}
		
		creatureLore.add("");
		creatureLore.add(ChatColor.DARK_GRAY + "Name: " + label);
		creatureLore.add(ChatColor.DARK_GRAY + "Hostility: " + parseHostility(hostility));
		creatureLore.add(ChatColor.DARK_GRAY + "Species Age: " + ChatColor.AQUA + speciesAge);
		creatureLore.add(ChatColor.DARK_GRAY + "Rideable: " + (isRideable ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No"));
		creatureLore.add("");
		creatureLore.add("");
		creatureLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Kill Drops");
		creatureLore.add("");
		for (String line : drops) {
			creatureLore.add(ChatColor.WHITE + "- " + line);
		}
		creatureLore.add("");
		
		
		creatureMeta.setLore(creatureLore);
		creatureMeta.setDisplayName(label + " " + parseRarity(rarity));
		creatureMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		creatureMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		
		creatureItem.setItemMeta(creatureMeta);
		
		return creatureItem;
	}
	
	public static void spawnCreature(Player p, String creature) {
		Random r = new Random();
		
		String xCoord = Integer.toString(r.nextInt(200) - 100);
		String yCoord = Integer.toString(r.nextInt(60) - 30);
		String zCoord = Integer.toString(r.nextInt(200) - 100);
		
		String creatureSpawned = ChatColor.GRAY + "The creature " + creature + ChatColor.GRAY + " has been spawned at " + ChatColor.GOLD + "~" + xCoord + " ~" + yCoord + " ~" + zCoord + ChatColor.GRAY + "! You may need to dig it out!";
		
		if (creature.contains("Ice Golem")) {
			p.sendMessage(creatureSpawned);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon snow_golem ~" + xCoord + " ~" + yCoord + " ~" + zCoord + " {Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:50f,Pumpkin:0b,CustomName:'[{\"text\":\"Creature\",\"color\":\"gray\",\"italic\":false},{\"text\":\" Ice Golem\",\"color\":\"aqua\",\"italic\":false}]',ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Icey Boots\",\"color\":\"dark_aqua\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:feather_falling\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:depth_strider\",lvl:3s},{id:\"minecraft:frost_walker\",lvl:6s},{id:\"minecraft:unbreaking\",lvl:4s}]}},{},{},{id:\"minecraft:ice\",Count:2b}],ArmorDropChances:[1.000F,0.085F,0.085F,1.000F],Attributes:[{Name:generic.max_health,Base:50},{Name:generic.knockback_resistance,Base:0.25},{Name:generic.attack_knockback,Base:4}]}");
		} else if (creature.contains("Iron Horse")) {
			p.sendMessage(creatureSpawned);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon horse ~" + xCoord + " ~" + yCoord + " ~" + zCoord + " {Health:40f,Bred:0b,EatingHaystack:0b,Tame:1b,Variant:256,HandItems:[{id:\"minecraft:iron_ingot\",Count:16b},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{Enchantments:[{id:\"minecraft:feather_falling\",lvl:32767s}]}},{},{},{}],ArmorDropChances:[0.000F,0.085F,0.085F,0.085F],Attributes:[{Name:generic.knockback_resistance,Base:0.9},{Name:generic.movement_speed,Base:0.5},{Name:horse.jump_strength,Base:2}],SaddleItem:{id:\"minecraft:saddle\",Count:1b},ArmorItem:{id:\"minecraft:iron_horse_armor\",Count:1b,tag:{display:{Name:'{\"text\":\"Damaged Iron Horsearmor\",\"color\":\"dark_gray\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:5s}]}}}");
		}
	}
}
