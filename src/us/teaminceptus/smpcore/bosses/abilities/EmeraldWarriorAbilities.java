package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.SMPCore;

public class EmeraldWarriorAbilities implements Listener {
	
	public SMPCore plugin;
	
	public EmeraldWarriorAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	public static void announceDialogue(String msg) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "Emerald Warrior" + ChatColor.GREEN + "] " + ChatColor.GREEN + msg);
	}
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getEntity().getCustomName().contains("Emerald Warrior"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getDamager();
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,Health:250f,CanBreakDoors:1b,CustomName:'{\"text\":\"Emerald Gladiator\",\"color\":\"green\",\"italic\":false}',ArmorItems:[{id:\"minecraft:iron_boots\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:3s},{id:\"minecraft:feather_falling\",lvl:6s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:frost_walker\",lvl:2s},{id:\"minecraft:soul_speed\",lvl:1s}]}},{},{},{id:\"minecraft:emerald_block\",Count:1b,tag:{Enchantments:[{id:\"minecraft:protection\",lvl:6s}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.000F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:250},{Name:generic.follow_range,Base:100},{Name:generic.knockback_resistance,Base:0.5},{Name:generic.attack_damage,Base:15},{Name:generic.attack_knockback,Base:3},{Name:zombie.spawn_reinforcements,Base:1}]}");
		p.sendMessage(ChatColor.GREEN + "A Minion has spawned!");
		
		
	}
	
	@EventHandler
	public void onDamageDefensiveTitan(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.GIANT))) return;
		if (!(e.getEntity().getCustomName().contains("Warrior's Titan"))) return;
		if (e.getEntity().isCustomNameVisible()) return;
		
		Player p = (Player) e.getDamager();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 400, 14, true, false, true));
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 400, 2, true, false, true));
		}
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 9, true, false, true));
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getDamager().getType().equals(EntityType.ZOMBIE))) return;
		if (!(e.getDamager().getCustomName().contains("Emerald Warrior"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 19, true, false, true));
		}
		
		if (r.nextInt(100) < 25) {
			announceDialogue("Emerald Stab.");
		}
		
		if (r.nextInt(100) < 10) {
			announceDialogue(ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + ", I love emeralds. I like it when I kill players with them.");
		}
	}
		
}
