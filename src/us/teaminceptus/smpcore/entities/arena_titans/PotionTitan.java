package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityWitch;

public class PotionTitan extends EntityWitch {
	
	public PotionTitan(Location loc) {
		super(EntityTypes.aY, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanJoinRaid(false);
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Axe Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 4.2);
		FinderUtils.addTitanEffects(1, en);
	}
	
}
