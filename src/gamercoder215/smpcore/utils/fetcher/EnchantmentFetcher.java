package gamercoder215.smpcore.utils.fetcher;

import org.bukkit.ChatColor;

import gamercoder215.smpcore.listeners.titan.TitanEnchantment;
import gamercoder215.smpcore.listeners.titan.TitanEnchantment.Item;
import gamercoder215.smpcore.utils.GeneralUtils;

public class EnchantmentFetcher {
	
	public static TitanEnchantment doubleDamage = new TitanEnchantment(ChatColor.RED + "Double Damage", Item.WEAPON, "Every hit, your damage is doubled.", "Nova x640");
	public static TitanEnchantment weakening = new TitanEnchantment(ChatColor.GRAY + "Weakening", Item.WEAPON, "Every entity you hit will gain weakness.", "Constibilis x320");
	public static TitanEnchantment withering = new TitanEnchantment(GeneralUtils.hexToChat("383838", "Withering"), Item.WEAPON, "Every entity you hit will gain the wither effect.", "Refined Wither Material x64", "Nova x640");
	public static TitanEnchantment poisoning = new TitanEnchantment(ChatColor.DARK_GREEN + "Poisoning", Item.WEAPON, "Every entity you hit will get poisioned.", "Pufferfish x16", "Nova x640");
	public static TitanEnchantment fireImmunity = new TitanEnchantment(ChatColor.GOLD + "Fire Beforlus", Item.ARMOR, "Gain immunity to fire and lava damage.", "Nova x832");
	public static TitanEnchantment explosionImmunity = new TitanEnchantment(ChatColor.DARK_RED + "Explosion Beforlus", Item.ARMOR, "Gain immunity to explosion damage.", "TNT x256", "Nova x704");
	public static TitanEnchantment projectileImmunity = new TitanEnchantment(ChatColor.AQUA + "Projectile Beforlus", Item.CHESTPLATE, "Gain immunity to projectile damage.", "Arrow x128", "Nova x704");
	public static TitanEnchantment lightningImmunity = new TitanEnchantment(ChatColor.WHITE + "Lightning Beforlus", Item.ARMOR, "Gain immunity to lightning damage.", "Clarus x128", "Nova x704");
	public static TitanEnchantment potionImmunity = new TitanEnchantment(ChatColor.DARK_AQUA + "Potion Beforlus", Item.ARMOR, "Gain immunity to magical and poison damage.", "Blaze Rod x320", "Nova x704");
	public static TitanEnchantment fallingImmunity = new TitanEnchantment(ChatColor.WHITE + "Falling Beforlus", Item.BOOTS, "Gain immunity to fall damage.", "Feather x384", "Nova x704");
	public static TitanEnchantment witherImmunity = new TitanEnchantment(GeneralUtils.hexToChat("383838", "Wither Beforlus"), Item.WEAPON, "Gain immunity to wither damage.", "Refined Wither Material x256", "Nova x960", "Exitatus x256");
	public static TitanEnchantment smelting = new TitanEnchantment(ChatColor.BLUE + "Smelting", Item.PICKAXE, "Blocks you mine will automatically smelt.", "Nova x128");
	public static TitanEnchantment longshot = new TitanEnchantment(ChatColor.AQUA + "Longshot", Item.RANGED, "Gain x2 the arrow range.", "Cito x512");
	public static TitanEnchantment pentashot = new TitanEnchantment(ChatColor.DARK_BLUE + "Penta-Shot", Item.BOW, "Shoot 5 arrows at a time.", "Nova x1024");
	public static TitanEnchantment timbering = new TitanEnchantment(ChatColor.DARK_BLUE + "Timbering", Item.AXE, "Mining a tree takes the entire tree down.", "Nova x128");
	public static TitanEnchantment adreanaline = new TitanEnchantment(GeneralUtils.hexToChat("c76000", "Adrenaline"), Item.LEGGINGS, "When Damaged, 10% chance to gain Speed VII for 5 seconds.", "Clarus x320");
	public static TitanEnchantment rubber = new TitanEnchantment(ChatColor.GOLD + "Rubber", Item.CHESTPLATE, "When damaged, 10% chance for 1/2 to damage the attacker.", "Clarus x1024");
	public static TitanEnchantment pecus = new TitanEnchantment(ChatColor.LIGHT_PURPLE + "Pecus", Item.WEAPON, "Deal 500% damage to animals.", "Incitatus Leaves x128");
	
}
