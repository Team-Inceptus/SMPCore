package gamercoder215.smpcore.utils.entities;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.player.EntityHuman;

public class TitanSkeleton extends EntitySkeleton {

	public TitanSkeleton(Location loc) {
	
		super(EntityTypes.aB, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200f);
		en.setHealth(200f);
		en.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, false));
	}
	
	public void initPathfinder() {
        this.bP.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bP.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
		this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityPiglin>(this, EntityPiglin.class, true));
		this.bP.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
        this.bP.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.bP.a(3, new PathfinderGoalRandomLookaround(this));
	}

}
