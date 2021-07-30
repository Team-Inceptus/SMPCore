package gamercoder215.smpcore.utils.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
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
		
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(false);
		this.setCustomName(new ChatComponentText(ChatColor.GREEN + "" +ChatColor.BOLD + "Titan Enderman"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		en.setHealth(1050f);
	}
	
    public void initPathfinder() {
	   this.bP.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
       this.bP.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
	   this.bP.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	   this.bP.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	   this.bP.a(2, new PathfinderGoalRandomLookaround(this));
	   this.bP.a(3, new PathfinderGoalHurtByTarget(this, new Class[0]));
    }
}
