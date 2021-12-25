package us.teaminceptus.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;

public class WitherZombieAbilities implements Listener {

	protected SMPCore plugin;
	
	public WitherZombieAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	static Random r = new Random();
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType() == EntityType.ZOMBIE)) return;
		if (!(e.getEntity().getCustomName().contains("Wither Zombie"))) return;
		
		Zombie en = (Zombie) e.getEntity();
		
		new BukkitRunnable() {
			public void run() {
				if (en.isDead()) cancel();
				WitherSkull skull = (WitherSkull) en.getWorld().spawnEntity(en.getLocation().add(en.getLocation().getDirection()), EntityType.WITHER_SKULL);
				skull.setDirection(en.getLocation().getDirection());
				skull.setYield(r.nextInt(3) + 4);
				skull.setShooter(en);
			}
		}.runTaskTimer(plugin, 20 * 5, 20 * 5);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType() == EntityType.ZOMBIE)) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getEntity().getCustomName().contains("Wither Zombie"))) return;
		
		Entity en = e.getEntity();
		
		if (r.nextInt(100) < 75) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + en.getUniqueId() + " run summon wither_skeleton  {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,AbsorptionAmount:100f,Health:300f,CustomName:'{\"text\":\"Wither Archer\",\"color\":\"black\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Withering Bow 2.0\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,lvl:1s,Enchantments:[{id:\"minecraft:power\",lvl:10s},{id:\"minecraft:punch\",lvl:5s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:10,Operation:0,UUID:[I;-2030584424,611798294,-1592433588,113432246],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:15,Operation:0,UUID:[I;-1491094037,-1593490874,-1130771910,-643541655],Slot:\"mainhand\"}]}},{}],ArmorItems:[{},{},{},{id:\"minecraft:netherite_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Wither Netherite Helmet\",\"color\":\"dark_gray\",\"italic\":false}'},Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:40s},{id:\"minecraft:fire_protection\",lvl:30s},{id:\"minecraft:blast_protection\",lvl:30s},{id:\"minecraft:respiration\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:1.5,Operation:2,UUID:[I;-875368505,779438665,-1591165347,-1422404287],Slot:\"head\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:2,Operation:2,UUID:[I;-1815454291,108677042,-1776166854,-332164220],Slot:\"head\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:10,Operation:0,UUID:[I;2033766024,-1920578753,-1648918566,-1673109656],Slot:\"head\"}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.000F],ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:300},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:10}]}");
		}
	}

}
