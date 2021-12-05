package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Hoglin;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;

public class KnockbackTitan extends EntityHoglin {
	
	public KnockbackTitan(Location loc) {
		super (EntityTypes.L, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.RED + "Knockback Titan"));
		this.v(true); // Immune to Zombification
		
		Hoglin en = (Hoglin) this.getBukkitEntity();
		
		en.setIsAbleToBeHunted(false);
		
		en.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(50);
		en.setCollidable(false);
		
		FinderUtils.addTitanEffects(1, en);
		FinderUtils.setAttributes(en, 3);
	}
	
}
