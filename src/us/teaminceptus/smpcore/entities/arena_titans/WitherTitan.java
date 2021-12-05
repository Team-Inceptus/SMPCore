package us.teaminceptus.smpcore.entities.arena_titans;

import java.util.EnumSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
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
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Wither Titan")); // Custom Name
		
		Wither en = (Wither) this.getBukkitEntity();
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 120);
	}
	
	protected void u() {
		this.bR.a(0, new WitherTitan.WitherGoal());
		this.bR.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 40, 20.0F));
		this.bR.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
		this.bR.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bR.a(7, new PathfinderGoalRandomLookaround(this));
		this.bS.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bS.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, false, false));
	}

	private class WitherGoal extends PathfinderGoal {
		public WitherGoal() {
			this.a(EnumSet.of(Type.a, Type.c, Type.b));
		}

		public boolean a() {
			return WitherTitan.this.t() > 0; // Get Invul Ticks
		}
	}
	

}