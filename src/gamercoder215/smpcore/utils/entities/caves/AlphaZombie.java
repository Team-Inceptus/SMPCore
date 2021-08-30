package gamercoder215.smpcore.utils.entities.caves;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import gamercoder215.smpcore.listeners.caves.AlphaCave;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;

public class AlphaZombie extends EntityZombie {

	public AlphaZombie(Location loc) {
		super(EntityTypes.be, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setCustomName(new ChatComponentText(ChatColor.DARK_GREEN + "Alpha Zombie"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();

		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
		en.setHealth(1000f);
		
		en.getEquipment().setItemInMainHand(AlphaCave.getAlphaSword());
		en.getEquipment().setItemInMainHandDropChance(0.005f);
		
		en.getEquipment().setHelmet(AlphaCave.getHardenedAlphaStone());
		en.getEquipment().setHelmetDropChance(0.5f);
	}	
	
	
	
}
