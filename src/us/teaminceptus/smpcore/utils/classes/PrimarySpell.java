package us.teaminceptus.smpcore.utils.classes;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.utils.enums.SpellActivationType;
import us.teaminceptus.smpcore.utils.enums.SpellType;

public class PrimarySpell {
	
	public SpellType spellType;
	public String name;
	public ArrayList<String> desc;
	public Particle particle;
	public SpellActivationType activation;
	
	public PrimarySpell(String spellName, ArrayList<String> spellDescription, SpellType type, SpellActivationType actType) {
		this.spellType = type;
		this.name = spellName;
		this.activation = actType;
		this.desc = spellDescription;
	}
	
	public PrimarySpell(String spellName, ArrayList<String> spellDescription, SpellType type, SpellActivationType actType, Particle particleEffect) {
		this.spellType = type;
		this.name = spellName;
		this.desc = spellDescription;
		this.particle = particleEffect;
		this.activation = actType;
	}
	
	protected String parseSpelltype(SpellType type) {
		if (type.equals(SpellType.AREA_EFFECT)) return (ChatColor.AQUA + "Area Effect");
		else if (type.equals(SpellType.CUSTOM)) return (ChatColor.DARK_AQUA + "Custom");
		else if (type.equals(SpellType.EXPLOSION)) return (ChatColor.RED + "Explosive");
		else if (type.equals(SpellType.HEAL)) return (ChatColor.RED + "Heal");
		else if (type.equals(SpellType.POTION_EFFECT)) return (ChatColor.BLUE + "Potion Effect(s)");
		else if (type.equals(SpellType.SPAWN_ENTITY)) return (ChatColor.GOLD + "Summon Entity");
		else return (ChatColor.DARK_GRAY + "Unknown");
	}
	
	protected String parseActivationType(SpellActivationType type) {
		if (type.equals(SpellActivationType.VERBAL)) return (ChatColor.GREEN + "Verbal");
		else if (type.equals(SpellActivationType.WAND)) return (ChatColor.AQUA + "Wand");
		else return (ChatColor.AQUA + "Wand");
	}
	
	public ItemStack generateSpellInfo(Material representItem) {
		ItemStack spellItem = new ItemStack(representItem, 1);
		ItemMeta spellMeta = spellItem.getItemMeta();
		
		spellMeta.setDisplayName(this.name);
		spellMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		spellMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_POTION_EFFECTS);
		
		ArrayList<String> spellLore = new ArrayList<String>();
		spellLore.add("");
		for (String li : this.desc) {
			spellLore.add(ChatColor.GRAY + li);
		}
		spellLore.add("");
		spellLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Properties");
		spellLore.add("");
		spellLore.add(ChatColor.DARK_GRAY + "Spell Type: " + parseSpelltype(this.spellType));
		spellLore.add(ChatColor.DARK_GRAY + "Activation Type: " + parseActivationType(this.activation));
		spellLore.add(ChatColor.DARK_GRAY + "Particles: " + (this.particle != null ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No"));
		spellLore.add("");
		
		spellMeta.setLore(spellLore);
		
		spellItem.setItemMeta(spellMeta);
		
		return spellItem;
	}
	
}
