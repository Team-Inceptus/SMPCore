package us.teaminceptus.smpcore.entities.caves;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import us.teaminceptus.smpcore.listeners.caves.DeltaCave;

public class DeltaSkeleton extends EntitySkeleton {

	public DeltaSkeleton(Location loc) {
		super(EntityTypes.aB, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.WHITE + "Delta Skeleton"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();

		en.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
		en.setHealth(250f);
		
		en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
		
		en.getEquipment().setItemInMainHand(DeltaCave.getDeltaBow());
		en.getEquipment().setItemInMainHandDropChance(0.0001f);
		
		en.getEquipment().setHelmet(DeltaCave.getDeltaSet().get(EquipmentSlot.HEAD));
		en.getEquipment().setHelmetDropChance(0.07f);
		
		en.getEquipment().setChestplate(DeltaCave.getDeltaSet().get(EquipmentSlot.CHEST));
		en.getEquipment().setChestplateDropChance(0.007f);
		
		en.getEquipment().setLeggings(DeltaCave.getDeltaSet().get(EquipmentSlot.LEGS));
		en.getEquipment().setLeggingsDropChance(0.008f);
		
		en.getEquipment().setBoots(DeltaCave.getDeltaSet().get(EquipmentSlot.FEET));
		en.getEquipment().setBootsDropChance(0.08f);
	}	
	
	
	
}
