package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityBlaze;

public class FireTitan extends EntityBlaze {
	
	public FireTitan(Location loc) {
		super(EntityTypes.h, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 1);
		FinderUtils.addTitanEffects(1, en);
	}
	
	
}
