package gamercoder215.smpcore.utils.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityIronGolem;

public class IronTitan extends EntityIronGolem {

	public IronTitan(Location loc, LivingEntity target) {
		super(EntityTypes.P, ((CraftWorld) loc.getWorld()).getHandle());

		this.setPosition(loc.getX(), loc.getY(), loc.getZ());

		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Titan"));
		
		IronGolem en = (IronGolem) this.getBukkitEntity();
		en.setTarget(target);

		FinderUtils.setAttributes(en, 7);
		FinderUtils.addTitanEffects(1, en);
	}

	public void initPathfinder() {
		this.bP.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bP.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bP.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.bP.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bP.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bP.a(3, new PathfinderGoalRandomLookaround(this));
	}
}