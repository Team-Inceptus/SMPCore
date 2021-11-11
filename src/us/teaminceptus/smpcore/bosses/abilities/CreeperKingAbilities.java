package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.Main;

public class CreeperKingAbilities implements Listener {
	
	public Main plugin;
	
	public CreeperKingAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void announceDialogue(String msg) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "Creeper King" + ChatColor.GREEN + "] " + ChatColor.GOLD + msg);
	}
	Random r = new Random();
	
	boolean newSpawn = false;
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.CREEPER))) return;
		if (!(e.getEntity().getCustomName().contains("Creeper King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		newSpawn = false;
		e.getEntity().getNearbyEntities(1000, 1000, 1000).forEach(en -> {
			if (newSpawn == true) return;
			else if (en.getType().equals(EntityType.PLAYER)) {
				newSpawn = true;
				Player p = (Player) en;
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon creeper ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:10000f,powered:1b,ExplosionRadius:10b,CustomName:'{\"text\":\"Creeper King\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',HandItems:[{id:'minecraft:iron_sword',Count:1b,tag:{display:{Name:'{\"text\":\"Crepitus\\'s Sword\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'}}},{id:'minecraft:orange_dye',Count:1b,tag:{display:{Name:'{\"text\":\"Golden Gunpowder\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:'minecraft:protection',lvl:1s}]}}],HandDropChances:[0.010F,1.000F],ArmorItems:[{},{},{id:'minecraft:netherite_chestplate',Count:1b,tag:{display:{Name:'{\"text\":\"Milpuko\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:'minecraft:blast_protection',lvl:32767s},{id:'minecraft:projectile_protection',lvl:5s},{id:'minecraft:thorns',lvl:5s}],AttributeModifiers:[{AttributeName:'generic.luck',Name:'generic.luck',Amount:10,Operation:0,UUID:[I;-368925575,-301513808,-1573610956,26497177],Slot:'chest'},{AttributeName:'generic.max_health',Name:'generic.max_health',Amount:-0.75,Operation:2,UUID:[I;-1028458552,-1530246215,-1643352042,-486583264],Slot:'chest'},{AttributeName:'generic.knockback_resistance',Name:'generic.knockback_resistance',Amount:1,Operation:0,UUID:[I;-1081172476,377046109,-1624282245,-1560377532],Slot:'chest'},{AttributeName:'generic.attack_speed',Name:'generic.attack_speed',Amount:2,Operation:0,UUID:[I;-1863814995,2061190514,-1490516814,-2096508824],Slot:'chest'}]}},{id:'minecraft:leather_helmet',Count:1b,tag:{display:{Name:'{\"text\":\"Creeper Crown\",\"color\":\"green\",\"bold\":true,\"italic\":false}',Lore:[\"{\\\"text\\\":\\\"Sneak to explode.\\\",\\\"color\\\":\\\"gray\\\",\\\"italic\\\":false}\"],color:51476},HideFlags:1,Unbreakable:1b,Enchantments:[{id:'minecraft:fire_protection',lvl:32767s},{id:'minecraft:blast_protection',lvl:32767s},{id:'minecraft:respiration',lvl:32767s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:10,Operation:0,UUID:[I;155126535,-112639539,-1991033893,-1984088194],Slot:'head'},{AttributeName:'generic.armor_toughness',Name:'generic.armor_toughness',Amount:2,Operation:2,UUID:[I;-945970645,1329876341,-2018248538,538386748],Slot:'head'},{AttributeName:'generic.max_health',Name:'generic.max_health',Amount:-0.25,Operation:0,UUID:[I;1655601796,42814757,-1325670691,-167250676],Slot:'head'}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.100F],Attributes:[{Name:generic.max_health,Base:40000},{Name:generic.knockback_resistance,Base:1}]}");
			} else return;
		});
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.CREEPER))) return;
		if (!(e.getEntity().getCustomName().contains("Creeper King"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getDamager();
		
		ItemStack mainhand = p.getInventory().getItemInMainHand() != null ? p.getInventory().getItemInMainHand() : new ItemStack(Material.DIAMOND_SWORD, 1); 
		
		if (r.nextInt(100) < 80 && !(mainhand.hasItemMeta()) && !(mainhand.getItemMeta().isUnbreakable()) && !(mainhand.getItemMeta().hasDisplayName()) && !(mainhand.getType().equals(Material.DIAMOND_SWORD)) && !(mainhand.getItemMeta().getDisplayName().contains("Fragor's Sword"))) {
			p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
			p.sendMessage(ChatColor.GREEN + "A Minion has spawned!");
		}
		
		if (r.nextBoolean() == true && !(mainhand.hasItemMeta()) && !(mainhand.getItemMeta().isUnbreakable()) && !(mainhand.getItemMeta().hasDisplayName()) && !(mainhand.getType().equals(Material.DIAMOND_SWORD)) && !(mainhand.getItemMeta().getDisplayName().contains("Fragor's Sword"))) {
			p.getWorld().strikeLightningEffect(p.getLocation());
			p.setFireTicks(100);
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getDamager().getType().equals(EntityType.CREEPER))) return;
		if (!(e.getDamager().getCustomName().contains("Creeper King"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 9, true, false, true));
	}
}
