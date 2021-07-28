package gamercoder215.smpcore.utils;

import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AdvancementMessages {
	
	private static final TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();
	static {
		treemap.put(1000, "M");
		treemap.put(900, "CM");
		treemap.put(500, "D");
		treemap.put(400, "CD");
		treemap.put(100, "C");
		treemap.put(90, "XC");
		treemap.put(50, "L");
		treemap.put(40, "XL");
		treemap.put(10, "X");
		treemap.put(9, "IX");
		treemap.put(5, "V");
		treemap.put(4, "IV");
		treemap.put(1, "I");

	}

	public static final String integerToRoman(int number) {
		int l = treemap.floorKey(number);
		if (number == l) {
			return treemap.get(number);
		}
		return treemap.get(l) + integerToRoman(number - l);
	}
	
	public static String getUnlockedMessage(Player p) {
		String msg = p.getDisplayName() + ChatColor.RESET + ChatColor.GREEN + " has unlocked ";
		return (msg);
	}
	
	public static String getTitanSpawner(int tier) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Titan Spawner " + tierMsg;
		
		return msg;
	}
	
	public static String getTitanSpawner(int tier, boolean addExclamation) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Titan Spawner " + tierMsg + (addExclamation ? "!" : "");
		
		return msg;
	}
	
	public static String getTitanKiller(int tier) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Titan Killer " + tierMsg;
		
		return msg;
	}
	
	public static String getTitanKiller(int tier, boolean addExclamation) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Titan Killer " + tierMsg + (addExclamation ? "!" : "");
		
		return msg;
	}
	
	public static String getBossSpawner(int tier) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Boss Spawner " + tierMsg;
		
		return msg;
	}
	
	public static String getBossSpawner(int tier, boolean addExclamation) {
		String tierMsg = integerToRoman(tier);
		
		String msg = ChatColor.GOLD + "Boss Spawner " + tierMsg + (addExclamation ? "!" : "");
		
		return msg;
	}
}
