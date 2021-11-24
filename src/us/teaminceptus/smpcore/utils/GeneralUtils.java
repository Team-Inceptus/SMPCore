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
import net.minecraft.world.entity.EntityTypes;
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
	
	public static EntityTypes<?> matchEntityType(String oldname) {
		String name = oldname.replaceAll("minecraft:", "");
		
		if (name.equalsIgnoreCase("silverfish")) return EntityTypes.aA;
		else if (name.equalsIgnoreCase("slime")) return EntityTypes.aD;
		else if (name.equalsIgnoreCase("chest_minecart")) return EntityTypes.aa;
		else if (name.equalsIgnoreCase("skeleton")) return EntityTypes.aB;
		else if (name.equalsIgnoreCase("command_block_minecart")) return EntityTypes.ab;
		else if (name.equalsIgnoreCase("skeleton_horse")) return EntityTypes.aC;
		else if (name.equalsIgnoreCase("furnace_minecart")) return EntityTypes.ac;
		else if (name.equalsIgnoreCase("hopper_minecart")) return EntityTypes.ad;
		else if (name.equalsIgnoreCase("small_fireball")) return EntityTypes.aE;
		else if (name.equalsIgnoreCase("spawner_minecart")) return EntityTypes.ae;
		else if (name.equalsIgnoreCase("snow_golem")) return EntityTypes.aF;
		else if (name.equalsIgnoreCase("tnt_minecart")) return EntityTypes.af;
		else if (name.equalsIgnoreCase("mule")) return EntityTypes.ag;
		else if (name.equalsIgnoreCase("snowball")) return EntityTypes.aG;
		else if (name.equalsIgnoreCase("mooshroom")) return EntityTypes.ah;
		else if (name.equalsIgnoreCase("spectral_arrow")) return EntityTypes.aH;
		else if (name.equalsIgnoreCase("ocelot")) return EntityTypes.ai;
		else if (name.equalsIgnoreCase("spider")) return EntityTypes.aI;
		else if (name.equalsIgnoreCase("squid")) return EntityTypes.aJ;
		else if (name.equalsIgnoreCase("panda")) return EntityTypes.ak;
		else if (name.equalsIgnoreCase("stray")) return EntityTypes.aK;
		else if (name.equalsIgnoreCase("parrot")) return EntityTypes.al;
		else if (name.equalsIgnoreCase("strider")) return EntityTypes.aL;
		else if (name.equalsIgnoreCase("phantom")) return EntityTypes.am;
		else if (name.equalsIgnoreCase("egg")) return EntityTypes.aM;
		else if (name.equalsIgnoreCase("pig")) return EntityTypes.an;
		else if (name.equalsIgnoreCase("ender_pearl")) return EntityTypes.aN;
		else if (name.equalsIgnoreCase("piglin")) return EntityTypes.ao;
		else if (name.equalsIgnoreCase("experience_bottle")) return EntityTypes.aO;
		else if (name.equalsIgnoreCase("piglin_brute")) return EntityTypes.ap;
		else if (name.equalsIgnoreCase("potion")) return EntityTypes.aP;
		else if (name.equalsIgnoreCase("pillager")) return EntityTypes.aq;
		else if (name.equalsIgnoreCase("trident")) return EntityTypes.aQ;
		else if (name.equalsIgnoreCase("polar_bear")) return EntityTypes.ar;
		else if (name.equalsIgnoreCase("trader_llama")) return EntityTypes.aR;
		else if (name.equalsIgnoreCase("tnt")) return EntityTypes.as;
		else if (name.equalsIgnoreCase("tropical_fish")) return EntityTypes.aS;
		else if (name.equalsIgnoreCase("pufferfish")) return EntityTypes.at;
		else if (name.equalsIgnoreCase("turtle")) return EntityTypes.aT;
		else if (name.equalsIgnoreCase("rabbit")) return EntityTypes.au;
		else if (name.equalsIgnoreCase("vex")) return EntityTypes.aU;
		else if (name.equalsIgnoreCase("ravager")) return EntityTypes.av;
		else if (name.equalsIgnoreCase("villager")) return EntityTypes.aV;
		else if (name.equalsIgnoreCase("salmon")) return EntityTypes.aw;
		else if (name.equalsIgnoreCase("vindicator")) return EntityTypes.aW;
		else if (name.equalsIgnoreCase("sheep")) return EntityTypes.ax;
		else if (name.equalsIgnoreCase("wandering_trader")) return EntityTypes.aX;
		else if (name.equalsIgnoreCase("shulker")) return EntityTypes.ay;
		else if (name.equalsIgnoreCase("witch")) return EntityTypes.aY;
		else if (name.equalsIgnoreCase("shulker_bullet")) return EntityTypes.az;
		else if (name.equalsIgnoreCase("wither")) return EntityTypes.aZ;
		else if (name.equalsIgnoreCase("wither_skeleton")) return EntityTypes.ba;
		else if (name.equalsIgnoreCase("wither_skull")) return EntityTypes.bb;
		else if (name.equalsIgnoreCase("wolf")) return EntityTypes.bc;
		else if (name.equalsIgnoreCase("zoglin")) return EntityTypes.bd;
		else if (name.equalsIgnoreCase("zombie")) return EntityTypes.be;
		else if (name.equalsIgnoreCase("zombie_horse")) return EntityTypes.bf;
		else if (name.equalsIgnoreCase("zombie_villager")) return EntityTypes.bg;
		else if (name.equalsIgnoreCase("zombie_piglin")) return EntityTypes.bh;
		else if (name.equalsIgnoreCase("area_affect_cloud")) return EntityTypes.b;
		else if (name.equalsIgnoreCase("eye_of_ender")) return EntityTypes.B;
		else if (name.equalsIgnoreCase("armor_stand")) return EntityTypes.c;
		else if (name.equalsIgnoreCase("falling_block")) return EntityTypes.C;
		else if (name.equalsIgnoreCase("axolotl")) return EntityTypes.e;
		else if (name.equalsIgnoreCase("fox")) return EntityTypes.E;
		else if (name.equalsIgnoreCase("bat")) return EntityTypes.f;
		else if (name.equalsIgnoreCase("ghast")) return EntityTypes.F;
		else if (name.equalsIgnoreCase("bee")) return EntityTypes.g;
		else if (name.equalsIgnoreCase("giant")) return EntityTypes.G;
		else if (name.equalsIgnoreCase("blaze")) return EntityTypes.h;
		else if (name.equalsIgnoreCase("glow_item_frame")) return EntityTypes.H;
		else if (name.equalsIgnoreCase("boat")) return EntityTypes.i;
		else if (name.equalsIgnoreCase("glow_squid")) return EntityTypes.I;
		else if (name.equalsIgnoreCase("cat")) return EntityTypes.j;
		else if (name.equalsIgnoreCase("goat")) return EntityTypes.J;
		else if (name.equalsIgnoreCase("cave_spider")) return EntityTypes.k;
		else if (name.equalsIgnoreCase("guardian")) return EntityTypes.K;
		else if (name.equalsIgnoreCase("chicken")) return EntityTypes.l;
		else if (name.equalsIgnoreCase("hoglin")) return EntityTypes.L;
		else if (name.equalsIgnoreCase("cod")) return EntityTypes.m;
		else if (name.equalsIgnoreCase("horse")) return EntityTypes.M;
		else if (name.equalsIgnoreCase("cow")) return EntityTypes.n;
		else if (name.equalsIgnoreCase("husk")) return EntityTypes.N;
		else if (name.equalsIgnoreCase("creeper")) return EntityTypes.o;
		else if (name.equalsIgnoreCase("illusioner")) return EntityTypes.O;
		else if (name.equalsIgnoreCase("dolphin")) return EntityTypes.p;
		else if (name.equalsIgnoreCase("iron_golem")) return EntityTypes.P;
		else if (name.equalsIgnoreCase("donkey")) return EntityTypes.q;
		else if (name.equalsIgnoreCase("item")) return EntityTypes.Q;
		else if (name.equalsIgnoreCase("dragon_fireball")) return EntityTypes.r;
		else if (name.equalsIgnoreCase("item_frame")) return EntityTypes.R;
		else if (name.equalsIgnoreCase("drowned")) return EntityTypes.s;
		else if (name.equalsIgnoreCase("fireball")) return EntityTypes.S;
		else if (name.equalsIgnoreCase("elder_guardian")) return EntityTypes.t;
		else if (name.equalsIgnoreCase("end_crystal")) return EntityTypes.u;
		else if (name.equalsIgnoreCase("lightning")) return EntityTypes.U;
		else if (name.equalsIgnoreCase("ender_dragon")) return EntityTypes.v;
		else if (name.equalsIgnoreCase("llama")) return EntityTypes.V;
		else if (name.equalsIgnoreCase("enderman")) return EntityTypes.w;
		else if (name.equalsIgnoreCase("llama_spit")) return EntityTypes.W;
		else if (name.equalsIgnoreCase("endermite")) return EntityTypes.x;
		else if (name.equalsIgnoreCase("magma_cube")) return EntityTypes.X;
		else if (name.equalsIgnoreCase("evoker")) return EntityTypes.y;
		else if (name.equalsIgnoreCase("evoker_fangs")) return EntityTypes.z;
		else if (name.equalsIgnoreCase("minecart")) return EntityTypes.Z;
		else return null;
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
