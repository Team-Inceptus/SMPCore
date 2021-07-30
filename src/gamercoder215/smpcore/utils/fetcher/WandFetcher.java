package gamercoder215.smpcore.utils.fetcher;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.classes.PrimarySpell;
import gamercoder215.smpcore.utils.classes.SecondarySpell;
import gamercoder215.smpcore.utils.classes.Wand;
import gamercoder215.smpcore.utils.enums.SpellActivationType;
import gamercoder215.smpcore.utils.enums.SpellType;
import gamercoder215.smpcore.utils.enums.WandType;

public class WandFetcher {
	
	
	
	public static Wand getExplosionWand(Player p, Main plugin) {
		
		ArrayList<String> explosionDesc = new ArrayList<String>();
		explosionDesc.add("Creates a very large");
		explosionDesc.add("explosion.");
		
		ArrayList<String> explosion2Desc = new ArrayList<String>();
		explosion2Desc.add("Summons an explosive");
		explosion2Desc.add("fireball.");
		
		PrimarySpell explosion = new PrimarySpell(ChatColor.RED + "Super Explosion", explosionDesc, SpellType.EXPLOSION, SpellActivationType.WAND, Particle.LAVA);
		SecondarySpell explosion2 = new SecondarySpell(ChatColor.DARK_RED + "Explosive Fireball", explosion2Desc, SpellType.EXPLOSION);
		
		Wand explosionWand = new Wand(plugin, ChatColor.RED + "Explosion Wand", p, WandType.BLAZE_ROD, explosion, explosion2);
		
		return explosionWand;
	}
	
	public static Wand getHealingWand(Player p, Main plugin) {
		
		ArrayList<String> healingDesc = new ArrayList<String>();
		healingDesc.add("Heals 1 HP.");
		
		ArrayList<String> healing2Desc = new ArrayList<String>();
		healing2Desc.add("Gives Regeneration II");
		healing2Desc.add("for 2 seconds.");
		
		PrimarySpell heal = new PrimarySpell(ChatColor.RED + "Heal", healingDesc, SpellType.HEAL, SpellActivationType.WAND, Particle.HEART);
		SecondarySpell regen = new SecondarySpell(ChatColor.RED + "Regeneration", healing2Desc, SpellType.POTION_EFFECT);
		
		Wand healingWand = new Wand(plugin, ChatColor.RED + "Healing Wand", p, WandType.ALLIUM, heal, regen);
		
		return healingWand;
	}
	
	public static Wand getLightningWand(Player p, Main plugin) {
		
		ArrayList<String> lightningDesc = new ArrayList<String>();
		lightningDesc.add("Summons a lightning bolt");
		lightningDesc.add("at all entities within a");
		lightningDesc.add("20x20x20 area.");
		lightningDesc.add("Cooldown of 60s.");
		
		ArrayList<String> lightning2Desc = new ArrayList<String>();
		lightning2Desc.add("Creates a forcefield of");
		lightning2Desc.add("lightning all around you.");
		lightning2Desc.add("Cooldown of 30s.");
		
		PrimarySpell lightning = new PrimarySpell(ChatColor.WHITE + "Smiter", lightningDesc, SpellType.SPAWN_ENTITY, SpellActivationType.WAND);
		SecondarySpell forcefield = new SecondarySpell(ChatColor.GRAY + "Lightning Forcefield", lightning2Desc, SpellType.SPAWN_ENTITY);
		
		Wand lightningWand = new Wand(plugin, ChatColor.WHITE + "Lightning Wand", p, WandType.TRIDENT, lightning, forcefield);
		
		return lightningWand;
	}
	
	public static Wand getEnderWand(Player p, Main plugin) {
		
		ArrayList<String> endDesc = new ArrayList<String>();
		endDesc.add("Kills all non-boss enderman, endermites,");
		endDesc.add("and shulkers in the area. 60s Cooldown.");
		endDesc.add("Only works in The End.");
		
		ArrayList<String> end2Desc = new ArrayList<String>();
		end2Desc.add("Teleport 15 blocks in front");
		end2Desc.add("of yourself. No cooldown.");
		
		PrimarySpell endKiller = new PrimarySpell(ChatColor.DARK_PURPLE + "End Killer", endDesc, SpellType.AREA_EFFECT, SpellActivationType.WAND);
		SecondarySpell teleport = new SecondarySpell(ChatColor.BLUE + "Teleporter", end2Desc, SpellType.CUSTOM);
		
		Wand endWand = new Wand(plugin, ChatColor.LIGHT_PURPLE + "Ender Wand", p, WandType.END_ROD, endKiller, teleport);
		
		return endWand;
	}
	
	public static Wand getImmutoWand(Player p, Main plugin) {
		
		ArrayList<String> changeDesc = new ArrayList<String>();
		changeDesc.add("Haste IV for 30 seconds.");
		changeDesc.add("Cooldown for 60s.");
		
		ArrayList<String> change2Desc = new ArrayList<String>();
		change2Desc.add("Allows flight for 3 minutes,");
		change2Desc.add("and after done, cooldown for 10m.");
		change2Desc.add("Damage done to all entities while");
		change2Desc.add("flying will be blocked.");
		
		PrimarySpell superPick = new PrimarySpell(ChatColor.YELLOW + "Hasty Changer", changeDesc, SpellType.POTION_EFFECT, SpellActivationType.WAND);
		SecondarySpell builderFlight = new SecondarySpell(ChatColor.WHITE + "Immutatio's Flight", change2Desc, SpellType.CUSTOM);
		
		Wand immutoWand = new Wand(plugin, ChatColor.DARK_AQUA + "Immutatio Wand", p, WandType.TRIPWIRE, superPick, builderFlight);
		
		return immutoWand;
	}
	
	public static Wand getInfernoWand(Player p, Main plugin) {
		
		ArrayList<String> infernoDesc = new ArrayList<String>();
		infernoDesc.add("Summon 8 small fireballs");
		infernoDesc.add("from your location.");
		infernoDesc.add("No cooldown.");
		
		ArrayList<String> inferno2Desc = new ArrayList<String>();
		inferno2Desc.add("Every entity (including bosses)");
		inferno2Desc.add("within a 15x15x15 radius will be");
		inferno2Desc.add("set on fire for 10 seconds. 5s");
		inferno2Desc.add("cooldown.");
		
		PrimarySpell infernoFireball = new PrimarySpell(ChatColor.RED + "Inferno Fireball", infernoDesc, SpellType.SPAWN_ENTITY, SpellActivationType.WAND);
		SecondarySpell fireAura = new SecondarySpell(ChatColor.GOLD + "Fire Aura", inferno2Desc, SpellType.AREA_EFFECT, Particle.FLAME);
		
		Wand infernoWand = new Wand(plugin, ChatColor.GOLD + "Inferno Wand", p, WandType.BLAZE_ROD, infernoFireball, fireAura);
		
		return infernoWand;
	}
	
	public static Wand getWithermeal(Player p, Main plugin) {
		
		ArrayList<String> witherDesc = new ArrayList<String>();
		witherDesc.add("Suck up 50% health of all non-boss");
		witherDesc.add("entities within a 10x10x10 area.");
		witherDesc.add("90s cooldown.");
		
		ArrayList<String> wither2Desc = new ArrayList<String>();
		wither2Desc.add("Bonemeal at your current location.");
		wither2Desc.add("No cooldown.");
		
		PrimarySpell witherVampire = new PrimarySpell(ChatColor.DARK_GRAY + "Wither Vampire", witherDesc, SpellType.HEAL, SpellActivationType.WAND, Particle.DAMAGE_INDICATOR);
		SecondarySpell witherGrower = new SecondarySpell(ChatColor.DARK_GREEN + "Wither Grower", wither2Desc, SpellType.AREA_EFFECT);
		
		Wand withermealWand = new Wand(plugin, ChatColor.DARK_GRAY + "Withermeal Wand", p, WandType.SOUL_TORCH, witherVampire, witherGrower);
		
		return withermealWand;
	}
	
	public static Wand getDamageWand(Player p, Main plugin) {
		
		ArrayList<String> damageDesc = new ArrayList<String>();
		damageDesc.add("All non-boss entities within a");
		damageDesc.add("10x10x10 area are damaged by 5 HP.");
		
		ArrayList<String> damage2Desc = new ArrayList<String>();
		damage2Desc.add("Gain Strength 15 for 10 seconds.");
		damage2Desc.add("60s Cooldown.");
		
		PrimarySpell damageAura = new PrimarySpell(ChatColor.RED + "Damage Aura", damageDesc, SpellType.AREA_EFFECT, SpellActivationType.WAND, Particle.DAMAGE_INDICATOR);
		SecondarySpell strength = new SecondarySpell(ChatColor.DARK_RED + "Super Strength", damage2Desc, SpellType.POTION_EFFECT);
		
		Wand damageWand = new Wand(plugin, ChatColor.RED + "Damage Wand", p, WandType.STICK, damageAura, strength);
		
		return damageWand;
	}

}
