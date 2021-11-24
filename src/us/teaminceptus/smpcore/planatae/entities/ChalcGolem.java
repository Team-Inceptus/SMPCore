package us.teaminceptus.smpcore.planatae.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityGolem;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class ChalcGolem extends EntityGolem {

	@SuppressWarnings("unchecked")
	public ChalcGolem(Player target, Location loc) {
		super((EntityTypes<? extends EntityGolem>) GeneralUtils.matchEntityType("iron_golem"), ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setAggressive(true);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setGoalTarget(((CraftPlayer) target).getHandle());
		
		IronGolem golem = (IronGolem) this.getBukkitEntity();
		golem.setTarget(target);
		
		golem.setCustomName(GeneralUtils.hexToChat("5e4200", ChatColor.BOLD + "Chalc Golem"));
		golem.setCustomNameVisible(true);
		golem.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2500);
		this.setHealth(2500);
		golem.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(50);
		golem.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(50);
		
		ItemStack chalc = PlanataeFetcher.getChalc();
		chalc.setAmount(8);
		golem.getEquipment().setBoots(chalc);
		golem.getEquipment().setBootsDropChance(1.0f);
	}
	
}
