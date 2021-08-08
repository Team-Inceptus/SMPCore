package gamercoder215.smpcore.utils.calculation;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.pets.Pet;
import gamercoder215.smpcore.pets.Pet.Type;

public class DamageCalculation implements Listener  {
	
	protected Main plugin;
	
	public DamageCalculation(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	double targetDefense = 0;
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) return;
		if (!(e.getEntity() instanceof Player)) return;
		
		Player target = (Player) e.getEntity();
		Player p = (Player) e.getDamager();
		
		Pet dmgPet = Pet.fromTier(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("pet_damage"), Pet.Type.DAMAGE);
		
		Pet defensePet = Pet.fromTier(plugin.getConfig().getConfigurationSection(target.getUniqueId().toString()).getInt("pet_defense"), Pet.Type.DEFENSE);
		
		double dmg = e.getDamage();
		
		targetDefense = 0;
		
		if (target.getInventory().getArmorContents() != null) {
			for (ItemStack i : target.getInventory().getArmorContents()) {
				Collection<AttributeModifier> armor = i.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
				
				Collection<AttributeModifier> armorToughness = i.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR_TOUGHNESS);
				
				armor.forEach(at -> {
					targetDefense += at.getAmount();
				});
				
				armorToughness.forEach(at -> {
					targetDefense += (at.getAmount() * 2);
				});
			}
		}
		
		double newDefense = Pet.getModifier(targetDefense, target.getHealth(), defensePet)[1];
		
		
		e.setDamage((Pet.getModifier(dmg, dmgPet, Type.DAMAGE) + Math.floor(p.getStatistic(Statistic.MOB_KILLS) / 100)) /
				newDefense);
		
	}
	
}
