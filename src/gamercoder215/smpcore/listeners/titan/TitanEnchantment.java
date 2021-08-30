package gamercoder215.smpcore.listeners.titan;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import gamercoder215.smpcore.utils.fetcher.EnchantmentFetcher;

public class TitanEnchantment {
	
	public enum TitanEnchant {
		DOUBLE_DAMAGE,
		SMELTING,
		BEFORLUS_FIRE,
		BEFORLUS_EXPLOSION,
		BEFORLUS_PROJECTILE,
		BEFORLUS_LIGHTNING,
		BEFORLUS_POTION,
		BEFORLUS_FALLING,
		BEFORLUS_WITHER,
		WEAKENING,
		WITHERING,
		POISONING,
		LONGSHOT,
		PENTASHOT,
		TIMBERING,
		CONTAGIOUS,
		OBSIDIAN_WALKER,
		PULSAMUS,
		UNKNOWN
	}
	
	public enum Item {
		CHESTPLATE,
		LEGGINGS,
		BOOTS,
		HELMET,
		ARMOR,
		PICKAXE,
		AXE,
		HOE,
		WEAPON,
		SHOVEL,
		BOW,
		TOOL,
		MELEE,
		RANGED,
		ALL
	}
	
	public String name;
	public Item type;
	public String sum;
	public ArrayList<String> cost;
	
	public TitanEnchantment(String name, Item type, String summary, String... cost) {
		this.name = name;
		this.type = type;
		this.sum = ChatColor.GRAY + summary;
		ArrayList<String> formattedCost = new ArrayList<String>();
		
		for (String li : cost) {
			formattedCost.add(ChatColor.DARK_GRAY + li);
		}
		this.cost = formattedCost;
	}
	
	public static TitanEnchant parseString(String arg0) {
		String enchant = ChatColor.stripColor(arg0).toLowerCase().replaceAll("\\s", "_");
		
		if (enchant.equalsIgnoreCase("double_damage")) return TitanEnchant.DOUBLE_DAMAGE;
		else if (enchant.equalsIgnoreCase("weakening")) return TitanEnchant.WEAKENING;
		else if (enchant.equalsIgnoreCase("withering")) return TitanEnchant.WITHERING;
		else if (enchant.equalsIgnoreCase("poisoning")) return TitanEnchant.POISONING;
		else if (enchant.equalsIgnoreCase("fire_beforlus")) return TitanEnchant.BEFORLUS_FIRE;
		else if (enchant.equalsIgnoreCase("explosion_beforlus")) return TitanEnchant.BEFORLUS_EXPLOSION;
		else if (enchant.equalsIgnoreCase("projectile_beforlus")) return TitanEnchant.BEFORLUS_PROJECTILE;
		else if (enchant.equalsIgnoreCase("lightning_beforlus")) return TitanEnchant.BEFORLUS_LIGHTNING;
		else if (enchant.equalsIgnoreCase("potion_beforlus")) return TitanEnchant.BEFORLUS_POTION;
		else if (enchant.equalsIgnoreCase("falling_beforlus")) return TitanEnchant.BEFORLUS_FALLING;
		else if (enchant.equalsIgnoreCase("wither_beforlus")) return TitanEnchant.BEFORLUS_WITHER;
		else if (enchant.equalsIgnoreCase("smelting")) return TitanEnchant.SMELTING;
		else if (enchant.equalsIgnoreCase("longshot")) return TitanEnchant.LONGSHOT;
		else if (enchant.equalsIgnoreCase("penta-shot")) return TitanEnchant.PENTASHOT;
		else if (enchant.equalsIgnoreCase("timbering")) return TitanEnchant.TIMBERING;
		
		
		else return TitanEnchant.UNKNOWN;
	}
	
	public ItemStack generateItemStack() {
		ItemStack titanEnchant = new ItemStack(Material.ENCHANTED_BOOK, 1);
		EnchantmentStorageMeta enchantMeta = (EnchantmentStorageMeta) titanEnchant.getItemMeta();
		enchantMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Titan Enchanted Book");
		ArrayList<String> enchLore = new ArrayList<String>();
		enchLore.add(this.name);
		
		for (String li : this.cost) {
			enchLore.add(li);
		}
		
		enchLore.add(ChatColor.AQUA + "300 Levels");
		
		enchantMeta.setLore(enchLore);
		enchantMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE);
		enchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4519, true);
		
		titanEnchant.setItemMeta(enchantMeta);
		
		return titanEnchant;
	}
	
	public static TitanEnchantment fromItemStack(ItemStack i) {
		String enchant = ChatColor.stripColor(i.getItemMeta().getLore().get(0)).toLowerCase().replaceAll("\\s", "_");
		
		if (enchant.equalsIgnoreCase("double_damage")) return EnchantmentFetcher.doubleDamage;
		else if (enchant.equalsIgnoreCase("weakening")) return EnchantmentFetcher.weakening;
		else if (enchant.equalsIgnoreCase("withering")) return EnchantmentFetcher.withering;
		else if (enchant.equalsIgnoreCase("poisoning")) return EnchantmentFetcher.poisoning;
		else if (enchant.equalsIgnoreCase("fire_beforlus")) return EnchantmentFetcher.fireImmunity;
		else if (enchant.equalsIgnoreCase("explosion_beforlus")) return EnchantmentFetcher.explosionImmunity;
		else if (enchant.equalsIgnoreCase("projectile_beforlus")) return EnchantmentFetcher.projectileImmunity;
		else if (enchant.equalsIgnoreCase("lightning_beforlus")) return EnchantmentFetcher.lightningImmunity;
		else if (enchant.equalsIgnoreCase("potion_beforlus")) return EnchantmentFetcher.potionImmunity;
		else if (enchant.equalsIgnoreCase("falling_beforlus")) return EnchantmentFetcher.fallingImmunity;
		else if (enchant.equalsIgnoreCase("wither_beforlus")) return EnchantmentFetcher.witherImmunity;
		else if (enchant.equalsIgnoreCase("smelting")) return EnchantmentFetcher.smelting;
		else if (enchant.equalsIgnoreCase("longshot")) return EnchantmentFetcher.longshot;
		else if (enchant.equalsIgnoreCase("penta-shot")) return EnchantmentFetcher.pentashot;
		else if (enchant.equalsIgnoreCase("timbering")) return EnchantmentFetcher.timbering;
		
		else return null;
	}
}
