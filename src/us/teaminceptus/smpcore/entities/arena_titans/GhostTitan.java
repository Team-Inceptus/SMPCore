package us.teaminceptus.smpcore.entities.arena_titans;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Stray;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeletonStray;

public class GhostTitan extends EntitySkeletonStray {

	public GhostTitan(Location loc) {
		super(EntityTypes.aK, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.GRAY + "" + ChatColor.BOLD + "Ghost Titan"));
		
		Stray en = (Stray) this.getBukkitEntity();
		
		ItemStack ghostBow = new ItemStack(Material.BOW);
		ItemMeta gMeta = ghostBow.getItemMeta();
		gMeta.addEnchant(Enchantment.ARROW_DAMAGE, 700, true);
		gMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		gMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 100, true);
		gMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		gMeta.setUnbreakable(true);
		gMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Ghost Bow");
		gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "GENERIC_LUCK", 23, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		gMeta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "GENERIC_LUCK", 23, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		ghostBow.setItemMeta(gMeta);
		
		en.getEquipment().setItemInMainHand(ghostBow);
		en.getEquipment().setItemInMainHandDropChance(0.0005f);
		
		ItemStack ghostPants = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta pMeta = ghostPants.getItemMeta();
		pMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Ghost Pants");
		pMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pMeta.setUnbreakable(true);
		pMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 700, true);
		pMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
		pMeta.addEnchant(Enchantment.THORNS, 250, true);
		pMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 11, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
		ghostPants.setItemMeta(pMeta);
		
		en.getEquipment().setLeggings(ghostPants);
		en.getEquipment().setLeggingsDropChance(0.001f);
		
		FinderUtils.setAttributes(en, 15);
		FinderUtils.addTitanEffects(2, en);
	}

}
