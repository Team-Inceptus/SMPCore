package gamercoder215.smpcore.utils.entities;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import gamercoder215.smpcore.utils.fetcher.TitanFetcher;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.monster.EntitySkeletonWither;
import net.minecraft.world.entity.player.EntityHuman;

public class EnderSkeleton extends EntitySkeletonWither {
	
	Random r = new Random();
	
	public EnderSkeleton(Location loc) {
		super(EntityTypes.ba, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1200f);
		en.setHealth(1200f);
		en.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 4, true, false, false));
		
		
		if (r.nextInt(100) < 5) {
			en.getEquipment().setHelmet(TitanFetcher.getMitisHelmet());
			en.getEquipment().setHelmetDropChance(0.005f);
		}
	}
	
	public void initPathfinder() {
		this.bP.a(0, new PathfinderGoalAvoidTarget<EntityEnderman>(this, EntityEnderman.class, 6f, 1d, 1.2d));
	    this.bP.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
	    this.bP.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	    this.bP.a(3, new PathfinderGoalHurtByTarget(this, new Class[0]));
	    this.bP.a(4, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	    this.bP.a(4, new PathfinderGoalRandomLookaround(this));
	}

}
