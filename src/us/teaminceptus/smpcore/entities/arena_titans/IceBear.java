package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PolarBear;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityPolarBear;

public class IceBear extends EntityPolarBear {

	public IceBear(Location loc, LivingEntity den) {
		super(EntityTypes.ar, ((CraftWorld) loc.getWorld()).getHandle());
	
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setAngerTarget(den.getUniqueId());
		if (den != null) this.setGoalTarget(((CraftLivingEntity) den).getHandle());
		
		PolarBear en = (PolarBear) this.getBukkitEntity();
		en.setCustomName(ChatColor.DARK_AQUA + "Ice Bear");
		en.setCustomNameVisible(true);
		
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10000D);
		en.setHealth(10000D);
		en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
		en.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.8D);
		if (den != null) en.setTarget(den);
	}
}
