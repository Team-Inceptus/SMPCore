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
import net.minecraft.world.entity.monster.EntityIllagerIllusioner;

public class MagicalTitan extends EntityIllagerIllusioner {
	
	Random r = new Random();
	
	public MagicalTitan(Location loc) {
		super(EntityTypes.O, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setCanJoinRaid(false);
		this.setCanPickupLoot(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Magical Titan"));
		
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		
		ItemStack enchantedBow = new ItemStack(Material.BOW, 1);
		ItemMeta eMeta = enchantedBow.getItemMeta();
		eMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "The Enchanted Bow");
		
		eMeta.addEnchant(Enchantment.ARROW_DAMAGE, 450, true);
		eMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		eMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 25, true);
		eMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
		eMeta.setUnbreakable(true);
		
		eMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		
		eMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 70, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		eMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 65, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND));
		
		enchantedBow.setItemMeta(eMeta);
		
		en.getEquipment().setItemInMainHand(enchantedBow);
		en.getEquipment().setItemInMainHandDropChance(r.nextFloat());

		FinderUtils.setAttributes(en, 1.3);
		FinderUtils.addTitanEffects(1, en);
	}
	
}
