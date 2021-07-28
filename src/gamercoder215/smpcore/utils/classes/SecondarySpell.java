package gamercoder215.smpcore.utils.classes;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Particle;

import gamercoder215.smpcore.utils.enums.SpellActivationType;
import gamercoder215.smpcore.utils.enums.SpellType;

public class SecondarySpell extends PrimarySpell {

	public SecondarySpell(String spellName, ArrayList<String> spellDescription, SpellType type) {
		super(spellName, spellDescription, type, SpellActivationType.WAND);
		this.spellType = type;
		this.name = spellName + ChatColor.GRAY + " (Secondary)";
		this.activation = SpellActivationType.WAND;
		this.desc = spellDescription;
	}
	
	public SecondarySpell(String spellName, ArrayList<String> spellDescription, SpellType type, Particle particleEffect) {
		super (spellName, spellDescription, type, SpellActivationType.WAND, particleEffect);
		this.spellType = type;
		this.name = spellName + ChatColor.GRAY + " (Secondary)";
		this.desc = spellDescription;
		this.particle = particleEffect;
		this.activation = SpellActivationType.WAND;
	}

}
