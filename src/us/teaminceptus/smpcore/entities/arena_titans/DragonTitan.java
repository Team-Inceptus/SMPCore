package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;

public class DragonTitan extends EntityEnderDragon {

	public DragonTitan(Location loc) {
		super(EntityTypes.v, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dragon Titan")); // Custom Name
		
		EnderDragon en = (EnderDragon) this.getBukkitEntity();
		en.setPhase(Phase.LEAVE_PORTAL);
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 300);
	}

}
