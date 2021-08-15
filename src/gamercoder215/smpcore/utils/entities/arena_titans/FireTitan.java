package gamercoder215.smpcore.utils.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityBlaze;

public class FireTitan extends EntityBlaze {
	
	public FireTitan(Location loc) {
		super(EntityTypes.h, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 1);
		FinderUtils.addTitanEffects(1, en);
	}
	
	
}
