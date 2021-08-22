package gamercoder215.smpcore.utils.entities.arena_titans;

import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityVindicator;

public class AxeTitan extends EntityVindicator {
	
	Random r = new Random();
	
	public AxeTitan(Location loc) {
		super (EntityTypes.aW, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanJoinRaid(false);
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.AQUA + "" + ChatColor.BOLD + "Axe Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		ItemStack bossclades = new ItemStack(Material.NETHERITE_AXE, 1);
		ItemMeta cMeta = bossclades.getItemMeta();
		cMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Clades");
		cMeta.setUnbreakable(true);
		cMeta.addEnchant(Enchantment.DAMAGE_ALL, 600, true);
		cMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 450, true);
		cMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 450, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 900, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 900, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", -0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", -0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", -0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		cMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", -0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		
		bossclades.setItemMeta(cMeta);
		
		en.getEquipment().setItemInMainHand(bossclades);
		en.getEquipment().setItemInMainHandDropChance(0);
		
		FinderUtils.setAttributes(en, 2);
		FinderUtils.addTitanEffects(1, en);
	}
	
}
