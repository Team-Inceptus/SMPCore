package us.teaminceptus.smpcore.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import us.teaminceptus.smpcore.Main;

public class GeneralUtils {
	
	public Main plugin;
	
	public static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	
	public static void moveToward(Entity entity, Location to, double speed){
		Location loc = entity.getLocation();
		double x = loc.getX() - to.getX();
		double y = loc.getY() - to.getY();
		double z = loc.getZ() - to.getZ();
		Vector velocity = new Vector(x, y, z).normalize().multiply(-speed);
		entity.setVelocity(velocity);   
	}

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
    
    public static String sendGETRequest(String url) {
    	try {
	    	HttpRequest request = HttpRequest.newBuilder()
	    			.GET()
	    			.uri(URI.create(url))
	    			.setHeader("User-Agent", "Java 11 HttpClient Bot")
	    			.build();
	    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	    	
	    	if (response.statusCode() == 200) {
	    		return response.body();
	    	}
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    public static int sendGETRequestStatusCode(String url) {
    	try {
	    	HttpRequest request = HttpRequest.newBuilder()
	    			.GET()
	    			.uri(URI.create(url))
	    			.setHeader("User-Agent", "Java 11 HttpClient Bot")
	    			.build();
	    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	    	
	    	return response.statusCode();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return 404;
    }
    
    public static String thousandSeparator(int n, String ch)
    {
  
        int l = (int)Math.floor(Math.log10(n)) + 1;
        StringBuffer str = new StringBuffer("");
        int count = 0;
        int r = 0;
  
        if (l > 3) {
  
            for (int i = l - 1; i >= 0; i--) {
  
                r = n % 10;
                n = n / 10;
                count++;
                if (((count % 3) == 0) && (i != 0)) {
  
                    str.append(String.valueOf(r));
  
                    str.append(ch);
                }
                else
                    str.append(String.valueOf(r));
            }
            str.reverse();
        }
  
        else
            str.append(String.valueOf(n));
  
        return str.toString();
    }
    
    public static UUID untrimUUID(String oldUUID) {
    	String p1 = oldUUID.substring(0, 8);
    	String p2 = oldUUID.substring(8, 12);
    	String p3 = oldUUID.substring(12, 16);
    	String p4 = oldUUID.substring(16, 20);
    	String p5 = oldUUID.substring(20, 32);
    	
    	String newUUID = p1 + "-" + p2 + "-" + p3 + "-" + p4 + "-" + p5;
    	
    	return UUID.fromString(newUUID);
    }
}