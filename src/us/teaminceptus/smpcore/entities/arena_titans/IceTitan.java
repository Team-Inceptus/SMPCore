package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Snowman;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalArrowAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntitySnowman;
import net.minecraft.world.entity.player.EntityHuman;

public class IceTitan extends EntitySnowman {

	public IceTitan(Location loc) {
		super(EntityTypes.aF, ((CraftWorld) loc.getWorld()).getHandle());
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.AQUA + "" + ChatColor.BOLD + "Ice Titan"));
		
		Snowman en = (Snowman) this.getBukkitEntity();
		
		en.setDerp(true);
		
		FinderUtils.setAttributes(en, 20);
		FinderUtils.addTitanEffects(2, en);
	}
	
	protected void u() {
		this.bR.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bR.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bR.a(1, new PathfinderGoalArrowAttack(this, 1.0D, 1, 60, 50));
		this.bR.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bR.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bR.a(3, new PathfinderGoalRandomLookaround(this));
	}
}