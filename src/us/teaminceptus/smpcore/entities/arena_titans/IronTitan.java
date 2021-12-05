package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.player.EntityHuman;

public class IronTitan extends EntityIronGolem {

	public IronTitan(Location loc, LivingEntity target) {
		super(EntityTypes.P, ((CraftWorld) loc.getWorld()).getHandle());

		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Titan"));
		
		IronGolem en = (IronGolem) this.getBukkitEntity();
		en.setTarget(target);

		FinderUtils.setAttributes(en, 8.5);
		FinderUtils.addTitanEffects(2, en);
	}

	public void u() {
		this.bR.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bR.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bR.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.bR.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bR.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bR.a(3, new PathfinderGoalRandomLookaround(this));
	}
}