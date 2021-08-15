package gamercoder215.smpcore.utils;

import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.Main;
import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;

public class GeneralUtils {
	
	public Main plugin;
	
	public static String withSuffix(double count) {
	    if (count < 1000) return "" + count;
	    int exp = (int) (Math.log(count) / Math.log(1000));
	    return String.format("%.1f%c",
	                         count / Math.pow(1000, exp),
	                         "KMBTQISPOND".charAt(exp-1));
	}
	
	public static String hexToChat(String hexCode, String message) {
		
		String hex1 = "&" + Character.toString(hexCode.charAt(0)).toUpperCase();
		String hex2 = "&" + Character.toString(hexCode.charAt(1)).toUpperCase();
		String hex3 = "&" + Character.toString(hexCode.charAt(2)).toUpperCase();
		String hex4 = "&" + Character.toString(hexCode.charAt(3)).toUpperCase();
		String hex5 = "&" + Character.toString(hexCode.charAt(4)).toUpperCase();
		String hex6 = "&" + Character.toString(hexCode.charAt(5)).toUpperCase();
		
		return (ChatColor.translateAlternateColorCodes('&', "&x" + hex1 + hex2 + hex3 + hex4 + hex5 + hex6 + message));
	}
	
	public static ItemStack itemFromNBT(String nbtStr) throws CommandSyntaxException {
		NBTTagCompound nbt =  MojangsonParser.parse(nbtStr);
		net.minecraft.world.item.ItemStack nmsItem = net.minecraft.world.item.ItemStack.a(nbt);
		org.bukkit.inventory.ItemStack item = CraftItemStack.asBukkitCopy(nmsItem);
		
		return item;
	}

    public final static String toRoman(int number) {
    	TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    	
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    	
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
