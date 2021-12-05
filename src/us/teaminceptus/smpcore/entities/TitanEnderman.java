package us.teaminceptus.smpcore.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.player.EntityHuman;

public class TitanEnderman extends EntityEnderman {

	public TitanEnderman(Location loc) {
		super(EntityTypes.w, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.GREEN + "" +ChatColor.BOLD + "Titan Enderman"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1050f);
		en.setHealth(1050f);
	}
	
    public void u() {
	   this.bR.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
       this.bR.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
	   this.bR.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	   this.bR.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	   this.bR.a(2, new PathfinderGoalRandomLookaround(this));
	   this.bR.a(3, new PathfinderGoalHurtByTarget(this, new Class[0]));
    }
}
