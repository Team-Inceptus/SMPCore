package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Giant;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.entity.player.EntityHuman;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class GroundTitan extends EntityGiantZombie {

	public GroundTitan(Location loc) {
		super(EntityTypes.G, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(GeneralUtils.hexToChat("a5986f", ChatColor.BOLD + "Ground Titan"))); // Custom Name
		
		Giant en = (Giant) this.getBukkitEntity();
		this.ep().b().add(new AttributeModifiable(GenericAttributes.f, (e) -> {e.a(1d);}));
		this.ep().b().add(new AttributeModifiable(GenericAttributes.g, (e) -> {e.a(1d);}));
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 60);
	}

	protected void u() {
		this.bR.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bR.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bR.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
		this.bR.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bR.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bR.a(3, new PathfinderGoalRandomLookaround(this));
	}
	
}
