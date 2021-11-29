package us.teaminceptus.smpcore.entities.arena_titans;

import java.util.EnumSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Wither;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalArrowAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.player.EntityHuman;

public class WitherTitan extends EntityWither {

	public WitherTitan(Location loc) {
		super(EntityTypes.aZ, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanPickupLoot(false);
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Wither Titan"));
		
		Wither en = (Wither) this.getBukkitEntity();
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 120);
	}
	
	protected void initPathfinder() {
		this.bP.a(0, new WitherTitan.WitherGoal());
		this.bP.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 40, 20.0F));
		this.bP.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
		this.bP.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bP.a(7, new PathfinderGoalRandomLookaround(this));
		this.bQ.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bQ.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, false, false));
	}

	private class WitherGoal extends PathfinderGoal {
		public WitherGoal() {
			this.a(EnumSet.of(Type.a, Type.c, Type.b));
		}

		public boolean a() {
			return WitherTitan.this.getInvul() > 0;
		}
	}
	

}