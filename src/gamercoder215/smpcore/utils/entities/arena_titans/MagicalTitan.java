package gamercoder215.smpcore.utils.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityIllagerIllusioner;

public class MagicalTitan extends EntityIllagerIllusioner {
	
	public MagicalTitan(Location loc) {
		super(EntityTypes.O, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanJoinRaid(false);
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Magical Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 1.1);
		FinderUtils.addTitanEffects(1, en);
	}
	
}
