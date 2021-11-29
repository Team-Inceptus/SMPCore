package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FinderUtils {
	
	public static void addTitanEffects(int tier, LivingEntity en) {
		if (tier < 1) tier = 1;
		if (tier > 3) tier = 3;
		
		en.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1, true, false, false));
		en.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, false));
		en.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, true, false, false));
		
		if (tier == 1) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 4, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, true, false, false));
		} else if (tier == 2) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 9, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 6, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 6, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, true, false, false));
		} else if (tier == 3) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 14, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 9, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 9, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 2, true, false, false));
			en.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, false));
		}
	}
	
	public static void setAttributes(LivingEntity en, double hpMil) {
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hpMil * Math.pow(10, 6));
		en.setHealth(hpMil * Math.pow(10, 6));
		
		en.setRemoveWhenFarAway(false);
		
		if (en.getAttribute(Attribute.GENERIC_ARMOR) != null) en.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(hpMil * 200);
		if (en.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) en.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(hpMil * 100);
		
		if (en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(hpMil * 150);
		if (en.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK) != null) en.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(3);
		
		if (en.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE) != null) en.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		if (en.getAttribute(Attribute.GENERIC_FOLLOW_RANGE) != null) en.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(2048);
		
		if (en instanceof Zombie) {
			en.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(1);
		}
	}
	
}
