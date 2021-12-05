package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PolarBear;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityPolarBear;

public class IceBear extends EntityPolarBear {

	public IceBear(Location loc, LivingEntity den) {
		super(EntityTypes.ar, ((CraftWorld) loc.getWorld()).getHandle());
	
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.a(den.getUniqueId()); // Set Anger Target
		
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
