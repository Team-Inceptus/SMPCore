package us.teaminceptus.smpcore.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.player.EntityHuman;

public class TitanPiglin extends EntityPiglin {

	public TitanPiglin(Location loc, boolean isBaby) {
		super(EntityTypes.ao, ((CraftWorld) loc.getWorld()).getHandle());
		this.v(true); // Immune to Zombification
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.RED + "Titan Piglin")); // Custom Name
		
		if (isBaby) {
			this.a(true); // Set Baby
		}
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(4000f);
		en.setHealth(4000f);
		
		en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
		
	}
	
	public void u() {
	   this.bR.a(0, new PathfinderGoalAvoidTarget<EntitySkeleton>(this, EntitySkeleton.class, 6f, 1d, 1.2d));
	   this.bR.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
       this.bR.a(1, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
	   this.bR.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	   this.bR.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	   this.bR.a(3, new PathfinderGoalRandomLookaround(this));
	   this.bR.a(4, new PathfinderGoalHurtByTarget(this, new Class[0]));
	}
	
	

}
