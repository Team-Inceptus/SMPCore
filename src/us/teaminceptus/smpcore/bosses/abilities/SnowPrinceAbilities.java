package us.teaminceptus.smpcore.bosses.abilities;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.Main;

public class SnowPrinceAbilities implements Listener {
	
	public Main plugin;
	
	public SnowPrinceAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	public boolean snowPrinceExists(List<Entity> entities) {
		
		entities.stream().filter(en -> !en.isSilent() && en.isCustomNameVisible() && en.getType().equals(EntityType.SNOWMAN));
		
		if (entities.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.SNOWMAN))) return;
		if (!(e.getEntity().getCustomName().contains("Snow Prince"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		Player p = (Player) e.getDamager();
		
		if (r.nextInt(3) == 1) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.WHITE + "The Snow Prince absorbed your attack!");
		}
		
		if (r.nextInt(100) < 40) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon iron_golem %x% %y% %z% {AngerTime:200000,Silent:1b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:75f,IsBaby:0b,CanBreakDoors:1b,CustomName:'{\"text\":\"Ice Minion\",\"color\":\"aqua\",\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Aspect of the Cold\",\"color\":\"white\",\"bold\":true,\"italic\":false}'},Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:10s},{id:\"minecraft:bane_of_arthropods\",lvl:10s},{id:\"minecraft:knockback\",lvl:5s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:5,Operation:0,UUID:[I;-2113737440,-19050714,-1578247818,-1745033275],Slot:\"mainhand\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:5,Operation:0,UUID:[I;1292738471,1692421194,-1681453558,1090041991],Slot:\"offhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:3,Operation:0,UUID:[I;1521202144,568018775,-1127118106,317085158],Slot:\"mainhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;-658257896,-40611951,-1382310988,-244478497],Slot:\"mainhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;-1823275676,624903440,-1778048623,-659723042],Slot:\"offhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:3,Operation:0,UUID:[I;-279387007,-394509640,-1435357391,-1880866204],Slot:\"offhand\"}]}},{}],HandDropChances:[3.000F,0.085F],ArmorItems:[{id:\"minecraft:leather_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Snow Boots\",\"color\":\"white\",\"italic\":false}',color:16777215},Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:projectile_protection\",lvl:5s},{id:\"minecraft:thorns\",lvl:4s},{id:\"minecraft:frost_walker\",lvl:5s}]}},{id:\"minecraft:leather_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Snow Leggings\",\"color\":\"white\",\"italic\":false}',color:16777215},HideFlags:64,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:projectile_protection\",lvl:5s},{id:\"minecraft:thorns\",lvl:4s}]}},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Snow Chestplate\",\"color\":\"white\",\"italic\":false}',color:16777215},HideFlags:64,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:projectile_protection\",lvl:5s},{id:\"minecraft:thorns\",lvl:4s}]}},{id:\"minecraft:blue_ice\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:32767s},{id:\"minecraft:aqua_affinity\",lvl:1s}]}}],ArmorDropChances:[4.000F,5.000F,5.000F,0.000F],ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:75},{Name:generic.knockback_resistance,Base:0.4},{Name:generic.attack_damage,Base:4},{Name:generic.attack_knockback,Base:1},{Name:zombie.spawn_reinforcements,Base:0}]}"
					.replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
			p.sendMessage(ChatColor.DARK_AQUA + "A Minion has spawned!");
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getCustomName().contains("Snow Prince"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		
		Player p = (Player) e.getEntity();
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, true));
		}
		
	}
}
