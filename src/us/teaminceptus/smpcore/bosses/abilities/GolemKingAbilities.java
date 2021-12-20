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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.teaminceptus.smpcore.SMPCore;

public class GolemKingAbilities implements Listener {
	
	public SMPCore plugin;
	
	public GolemKingAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.IRON_GOLEM))) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getEntity().getCustomName().contains("Iron King"))) return;
		
		Random r = new Random();
		
		Player p = (Player) e.getDamager();
		
		if (r.nextInt(100) < 80) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon horse ~2 ~ ~ {Silent:1b,Glowing:0b,CustomNameVisible:0b,Health:25f,Bred:0b,EatingHaystack:0b,Tame:1b,Variant:256,Passengers:[{id:\"minecraft:iron_golem\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:200f,PlayerCreated:0b,AngerTime:200000,CustomName:'{\"text\":\"Iron Minion\",\"color\":\"gray\",\"italic\":false}',ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25},{Name:generic.attack_knockback,Base:25}]}],CustomName:'{\"text\":\"Iron Horse\",\"color\":\"white\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:25},{Name:horse.jump_strength,Base:2}],SaddleItem:{id:\"minecraft:saddle\",Count:1b},ArmorItem:{id:\"minecraft:iron_horse_armor\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Iron Horsearmor\",\"color\":\"gray\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}}}");
			p.sendMessage(ChatColor.DARK_RED + "A minion has spawned!");
		}
		
		if (r.nextBoolean() == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 4, true, false, true));
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getType().equals(EntityType.IRON_GOLEM))) return;
		if (!(e.getDamager().getCustomName().contains("Iron King"))) return;
		
		Random r = new Random();
		
		Player p = (Player) e.getEntity();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 19, true, false, true));
		
		if (r.nextInt(100) < 5) {
			p.dropItem(true);
			p.getInventory().setItemInMainHand(new ItemStack(Material.IRON_SWORD, 1));
			p.sendMessage(ChatColor.RED + "The Golem has infused your weapon with iron and has forced your old one to drop!");
		}
	}

}
