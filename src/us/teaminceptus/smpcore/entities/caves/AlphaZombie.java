package us.teaminceptus.smpcore.entities.caves;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import us.teaminceptus.smpcore.listeners.caves.AlphaCave;

public class AlphaZombie extends EntityZombie {

	public AlphaZombie(Location loc) {
		super(EntityTypes.be, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.DARK_GREEN + "Alpha Zombie"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();

		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
		en.setHealth(1000f);
		
		en.getEquipment().setItemInMainHand(AlphaCave.getAlphaSword());
		en.getEquipment().setItemInMainHandDropChance(0.005f);
		
		en.getEquipment().setHelmet(AlphaCave.getHardenedAlphaStone());
		en.getEquipment().setHelmetDropChance(0.5f);
	}	
	
	
	
}
