package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityWitch;

public class PotionTitan extends EntityWitch {
	
	public PotionTitan(Location loc) {
		super(EntityTypes.aY, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.y(false); // Can Join Raid
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Axe Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 4.2);
		FinderUtils.addTitanEffects(1, en);
	}
	
}
