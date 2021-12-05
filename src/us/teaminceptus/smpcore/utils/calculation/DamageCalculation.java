package us.teaminceptus.smpcore.utils.calculation;

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

import us.teaminceptus.smpcore.Main;

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
		
		int worldNerf = 1;
		
		if (p.getWorld().getName().equalsIgnoreCase("world") || p.getWorld().getName().equalsIgnoreCase("world_nether") || p.getWorld().getName().equalsIgnoreCase("world_the_end")) worldNerf = 1;
		else if (p.getWorld().getName().contains("titan") && !(p.getWorld().getName().contains("caves"))) worldNerf = 10000;
		else if (p.getWorld().getName().contains("caves")) worldNerf = 5000;
		else if (p.getWorld().getName().contains("devotido")) worldNerf = 1000000;
		
		/*
		Pet dmgPet = Pet.fromTier(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("pet_damage"), Pet.Type.DAMAGE);
		
		Pet defensePet = Pet.fromTier(plugin.getConfig().getConfigurationSection(target.getUniqueId().toString()).getInt("pet_defense"), Pet.Type.DEFENSE);
		
		
		double dmg = e.getDamage();
		*/
		
		targetDefense = 0;
		
		if (target.getInventory().getArmorContents() != null) {
			for (ItemStack i : target.getInventory().getArmorContents()) {
				if (i.getItemMeta() == null) continue;
				
				Collection<AttributeModifier> armor = i.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
				
				Collection<AttributeModifier> armorToughness = i.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR_TOUGHNESS);
				
				if (armor != null)
				armor.forEach(at -> {
					targetDefense += at.getAmount();
				});
				
				if (armorToughness != null)
				armorToughness.forEach(at -> {
					targetDefense += (at.getAmount() * 2);
				});
			}
		}
		
		// double newDefense = Pet.getModifier(targetDefense, target.getHealth(), defensePet)[1];
		
		
		e.setDamage((Math.floor(p.getStatistic(Statistic.MOB_KILLS) / 200)) + (p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() / worldNerf));
		
	}
	
}
