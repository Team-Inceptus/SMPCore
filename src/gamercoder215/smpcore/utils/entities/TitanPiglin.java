package gamercoder215.smpcore.utils.entities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityZoglin;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.player.EntityHuman;

public class TitanPiglin extends EntityPiglin {

	public TitanPiglin(Location loc, boolean isBaby) {
		super(EntityTypes.ao, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setHealth(1500f);
		this.setImmuneToZombification(true);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());

		if (isBaby) {
			this.setBaby(true);
		}
		
	}
	
	public void initPathfinder() {
	   this.bP.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
       this.bP.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
       this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityHoglin>(this, EntityHoglin.class, true, false));
       this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityZoglin>(this, EntityZoglin.class, true, false));
       this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityPigZombie>(this, EntityPigZombie.class, true, false));
	   this.bP.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	   this.bP.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	   this.bP.a(3, new PathfinderGoalRandomLookaround(this));
	   this.bP.a(4, new PathfinderGoalHurtByTarget(this, new Class[0]));
	}
	
	

}
