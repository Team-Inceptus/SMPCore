package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
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
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setCanPickupLoot(false);
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(GeneralUtils.hexToChat("a5986f", ChatColor.BOLD + "Ground Titan")));
		
		Giant en = (Giant) this.getBukkitEntity();
		this.getAttributeMap().b().add(new AttributeModifiable(GenericAttributes.f, (e) -> {e.setValue(1d);}));
		this.getAttributeMap().b().add(new AttributeModifiable(GenericAttributes.g, (e) -> {e.setValue(1d);}));
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 60);
	}

	protected void initPathfinder() {
		this.bP.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bP.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bP.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
		this.bP.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bP.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bP.a(3, new PathfinderGoalRandomLookaround(this));
	}
	
}
