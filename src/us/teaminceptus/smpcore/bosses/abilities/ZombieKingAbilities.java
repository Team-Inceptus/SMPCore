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
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.Main;

public class ZombieKingAbilities implements Listener {
	
	public Main plugin;
	
	public ZombieKingAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void announceDialogue(String msg) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "Zombie King" + ChatColor.GREEN + "] " + ChatColor.RED + msg);
	}
	
	boolean playerWarped = false;
	
	Random r = new Random();
	
	@EventHandler
	public void onTransform(EntityTransformEvent e) {
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getEntity().getCustomName().contains("Zombie King"))) return;
		
		announceDialogue("I am immune to drowning! Get me out of this puny liquid!");
		e.setCancelled(true);
		
		List<Entity> entities = e.getEntity().getNearbyEntities(10000, 10000, 10000);
		
		e.getEntity().getWorld().getPlayers().forEach(pl -> {
			if (entities.contains(pl) && !playerWarped) {
				e.getEntity().teleport(pl);
				pl.sendMessage(ChatColor.DARK_RED + "The Zombie King has warped to you!");
				playerWarped = true;
			} else return;
		});
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getEntity().getType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getEntity().getCustomName().contains("Zombie King"))) return;
		
		if (e.getDamager().getType().equals(EntityType.IRON_GOLEM)) {
			announceDialogue("Golems bore me. Begone!");
			e.getDamager().remove();
		} else if (e.getDamager().getType().equals(EntityType.PLAYER)) {
			Player p = (Player) e.getDamager();
			if (r.nextInt(100) < 10) {
				announceDialogue(ChatColor.YELLOW + p.getName() + ChatColor.RED + ", you are brave. That will cause your downfall.");
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 0, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 2, true, false, true));
			}
			
			p.sendMessage(ChatColor.DARK_RED + "A minion has spawned!");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:40f,CanBreakDoors:1b,CustomName:'{\"text\":\"Zombie Knight\",\"color\":\"white\",\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Zombie Knight Sword\",\"color\":\"green\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:6s},{id:\"minecraft:smite\",lvl:5s},{id:\"minecraft:bane_of_arthropods\",lvl:2s},{id:\"minecraft:fire_aspect\",lvl:1s},{id:\"minecraft:unbreaking\",lvl:6s}]}},{}],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:3s},{id:\"minecraft:fire_protection\",lvl:2s},{id:\"minecraft:feather_falling\",lvl:6s},{id:\"minecraft:thorns\",lvl:3s},{id:\"minecraft:depth_strider\",lvl:2s},{id:\"minecraft:frost_walker\",lvl:3s}]}},{id:\"minecraft:diamond_leggings\",Count:1b},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:10s},{id:\"minecraft:projectile_protection\",lvl:25s},{id:\"minecraft:thorns\",lvl:6s}]}},{id:\"minecraft:iron_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Knight Helmet\",\"color\":\"white\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:blast_protection\",lvl:4s},{id:\"minecraft:respiration\",lvl:7s},{id:\"minecraft:aqua_affinity\",lvl:1s},{id:\"minecraft:thorns\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:9s}]}}],ArmorDropChances:[0.085F,0.085F,0.000F,25.000F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:40},{Name:generic.follow_range,Base:100},{Name:generic.knockback_resistance,Base:0.75},{Name:generic.attack_damage,Base:5},{Name:generic.attack_knockback,Base:2},{Name:zombie.spawn_reinforcements,Base:0}]}"
					.replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (!(e.getDamager().isCustomNameVisible())) return;
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getCustomName().contains("Zombie King"))) return;
		if (!(e.getDamager().getType().equals(EntityType.ZOMBIE))) return;
		
		Player p = (Player) e.getEntity();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 29, true, false, true));
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 39, true, false, true));
	}
}
