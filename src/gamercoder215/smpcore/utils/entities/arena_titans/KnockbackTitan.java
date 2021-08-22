package gamercoder215.smpcore.utils.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Hoglin;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;

public class KnockbackTitan extends EntityHoglin {
	
	public KnockbackTitan(Location loc) {
		super (EntityTypes.L, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setImmuneToZombification(true);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setBaby(false);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.RED + "Knockback Titan"));
		this.setCanPickupLoot(false);
		
		Hoglin en = (Hoglin) this.getBukkitEntity();
		
		en.setIsAbleToBeHunted(false);
		
		en.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(50);
		en.setCollidable(false);
		
		FinderUtils.addTitanEffects(1, en);
		FinderUtils.setAttributes(en, 3);
	}
	
}
