package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityPillager;

public class CrossbowTitan extends EntityPillager {
	
	public CrossbowTitan(Location loc) {
		super (EntityTypes.aq, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.y(false); // Can Join Raid
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Crossbow Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 1.6);
		FinderUtils.addTitanEffects(1, en);
	}
}
