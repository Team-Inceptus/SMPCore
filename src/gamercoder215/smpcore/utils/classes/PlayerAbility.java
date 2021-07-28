package gamercoder215.smpcore.utils.classes;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.utils.enums.AbilityType;

public class PlayerAbility {
	
	public String abilityName;
	public ArrayList<String> abilityDescription;
	
	public EntityType killEntityType;
	public Material breakBlockType;
	
	public Player player;
	public AbilityType abilityType;
	
	public Statistic statistic;
	public int requiredAmount;

	
	public PlayerAbility(Player p, String abilityName, ArrayList<String> description, AbilityType type, int statRequiredValue, Statistic statRequired) {
		this.abilityName = abilityName;
		
		this.abilityDescription = description;
		
		this.player = p;
		
		this.abilityType = type;
		
		this.statistic = statRequired;
		
		this.requiredAmount = statRequiredValue;
	}
	
	public PlayerAbility(Player p, String abilityName, ArrayList<String> description, AbilityType type, int statRequiredValue, Material breakBlockType) {
		this.abilityName = abilityName;
		
		this.abilityDescription = description;
		
		this.player = p;
		
		this.abilityType = type;
		
		this.requiredAmount = statRequiredValue;
		
		this.breakBlockType = breakBlockType;
	}
	
	public PlayerAbility(Player p, String abilityName, ArrayList<String> description, AbilityType type, int killEntityAmount, EntityType killEntityType) {
		this.abilityName = abilityName;
		
		this.abilityDescription = description;
		
		this.player = p;
		
		this.killEntityType = killEntityType;
		
		this.requiredAmount = killEntityAmount;
		
		this.abilityType = type;
	}
	
	public boolean hasUnlocked() {
		Statistic requiredStatistic = this.killEntityType != null ? Statistic.KILL_ENTITY : this.statistic;
		int requiredAmount = this.requiredAmount;
		Player p = this.player;
		
		if (this.killEntityType != null) {
			return (p.getStatistic(requiredStatistic, this.killEntityType) >= requiredAmount);
		} else if (this.breakBlockType != null) {
			return (p.getStatistic(requiredStatistic, this.breakBlockType) >= requiredAmount);
		} else return (p.getStatistic(requiredStatistic) >= requiredAmount);
	}
	
	private String parseAbilityType(AbilityType ab) {
		if (ab.equals(AbilityType.CUSTOM)) {
			return (ChatColor.GRAY + "Custom");
		} else if (ab.equals(AbilityType.ADD_POTION_EFFECT)) {
			return (ChatColor.AQUA + "Potion Effect (Add)");
		} else if (ab.equals(AbilityType.HARM_ENTITY)) {
			return (ChatColor.DARK_RED + "Attack");
		} else if (ab.equals(AbilityType.HEAL_PLAYER)) {
			return (ChatColor.RED + "Heal");
		} else if (ab.equals(AbilityType.REMOVE_POTION_EFFECT)) {
			return (ChatColor.DARK_AQUA + "Potion Effect (Remove)");
		} else if (ab.equals(AbilityType.SPAWN_ENTITY)) {
			return (ChatColor.BLUE + "Summon Entity");
		} else return (ChatColor.DARK_GRAY + "Unknown");
	}
	
	public ItemStack getItemStackInfo(Material representedMaterial, boolean addGlint) {
		ItemStack abilityInfo = new ItemStack(representedMaterial, 1);
		ItemMeta infoMeta = abilityInfo.getItemMeta();
		if (addGlint) infoMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		
		infoMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
		infoMeta.setDisplayName(this.abilityName);
		
		ArrayList<String> infoLore = new ArrayList<String>();
		
		infoLore.add("");
		for (String li : this.abilityDescription) {
			infoLore.add(ChatColor.GRAY + li);
		}
		infoLore.add("");
		infoLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Information");
		infoLore.add("");
		infoLore.add(ChatColor.DARK_GRAY + "Name: " + this.abilityName);
		infoLore.add(ChatColor.DARK_GRAY + "Ability Type: " + parseAbilityType(this.abilityType));
		infoLore.add(ChatColor.DARK_GRAY + "Req. to Activate: " + ChatColor.AQUA + Integer.toString(this.requiredAmount));
		infoLore.add(ChatColor.DARK_GRAY + "Unlocked: " + (hasUnlocked() ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No"));
		
		infoMeta.setLore(infoLore);
		
		abilityInfo.setItemMeta(infoMeta);
		
		return abilityInfo;
	}
	
}
