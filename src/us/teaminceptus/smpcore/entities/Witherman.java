package us.teaminceptus.smpcore.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.monster.EntityEndermite;
import net.minecraft.world.entity.player.EntityHuman;

public class Witherman extends EntityEnderman {

	public Witherman(Location loc) {
		super(EntityTypes.w, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Witherman"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1050f);
		en.setHealth(1050f);
		en.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5, true, false, true));
		en.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, true));
		en.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, true, false, true));
	}
	
    public void initPathfinder() {
       this.bP.a(0, new PathfinderGoalAvoidTarget<EntityWither>(this, EntityWither.class, 6f, 1d, 1.2d));
	   this.bP.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
       this.bP.a(1, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
	   this.bP.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	   this.bP.a(2, new PathfinderGoalNearestAttackableTarget<EntityEndermite>(this, EntityEndermite.class, true, false));
	   this.bP.a(3, new PathfinderGoalHurtByTarget(this, new Class[0]));
	   this.bP.a(4, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	   this.bP.a(4, new PathfinderGoalRandomLookaround(this));
	   
    }
}
