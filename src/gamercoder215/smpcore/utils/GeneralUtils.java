package gamercoder215.smpcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.Main;
import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;

public class GeneralUtils {
	
	public Main plugin;
	
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
}
