package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.EquipmentSlot;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.world.entity.monster.EntitySkeletonWither;
import net.minecraft.world.entity.player.EntityHuman;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;

public class NetheriteTitan extends EntitySkeletonWither {

	public NetheriteTitan(Location loc) {
		super(EntityTypes.ba, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Netherite Titan"));
		
		WitherSkeleton en = (WitherSkeleton) this.getBukkitEntity();
		
		// Pure Chance in Drops
		en.getEquipment().setItemInMainHand(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HAND));
		en.getEquipment().setItemInMainHandDropChance(0f);
		
		en.getEquipment().setItemInOffHand(ArenaTitanFetcher.getTitanNetheriteSet().get(EquipmentSlot.OFF_HAND));
		en.getEquipment().setItemInOffHandDropChance(0f);
		
		en.getEquipment().setHelmet(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HEAD));
		en.getEquipment().setHelmetDropChance(0f);
		
		en.getEquipment().setChestplate(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.CHEST));
		en.getEquipment().setChestplateDropChance(0f);
		
		en.getEquipment().setLeggings(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.LEGS));
		en.getEquipment().setLeggingsDropChance(0f);
		
		en.getEquipment().setBoots(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.FEET));
		en.getEquipment().setBootsDropChance(0f);
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 32);
	}
	
	protected void u() {
		this.bR.a(0, new PathfinderGoalFloat(this));
		this.bR.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.bR.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true, false));
		this.bR.a(1, new PathfinderGoalNearestAttackableTarget<EntityWolf>(this, EntityWolf.class, true, false));
		this.bR.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.bR.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		this.bR.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
		this.bR.a(3, new PathfinderGoalRandomLookaround(this));
	}

}
