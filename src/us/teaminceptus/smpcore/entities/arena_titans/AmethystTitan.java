package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntityZombie;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;

public class AmethystTitan extends EntityZombie {
	
	public AmethystTitan(Location loc) {
		super(EntityTypes.be, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setCanPickupLoot(false);
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Amethyst Titan"));
		
		Zombie en = (Zombie) this.getBukkitEntity();
		
		this.getAttributeMap().b(GenericAttributes.g);
		// Pure Chance in Drops
		en.getEquipment().setItemInMainHand(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HAND));
		en.getEquipment().setItemInMainHandDropChance(0f);
		
		en.getEquipment().setHelmet(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HEAD));
		en.getEquipment().setHelmetDropChance(0f);
		
		en.getEquipment().setChestplate(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.CHEST));
		en.getEquipment().setChestplateDropChance(0f);
		
		en.getEquipment().setLeggings(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.LEGS));
		en.getEquipment().setLeggingsDropChance(0f);
		
		en.getEquipment().setBoots(ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.FEET));
		en.getEquipment().setBootsDropChance(0f);
		
		FinderUtils.setAttributes(en, 21.5);
		FinderUtils.addTitanEffects(2, en);
	}

}
