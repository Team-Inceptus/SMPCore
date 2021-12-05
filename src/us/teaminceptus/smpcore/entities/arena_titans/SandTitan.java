package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Husk;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntityZombieHusk;

public class SandTitan extends EntityZombieHusk {

	public SandTitan(Location loc) {
		super(EntityTypes.N, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sand Titan"));
		
		ep().b().add(new AttributeModifiable(GenericAttributes.g, a -> a.a(1d)));
		
		Husk en = (Husk) this.getBukkitEntity();
		
		FinderUtils.setAttributes(en, 10);
		FinderUtils.addTitanEffects(2, en);
		
		ItemStack sandHelmet = new ItemStack(Material.SAND);
		ItemMeta sMeta = sandHelmet.getItemMeta();
		sMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 600, true);
		sMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
		
		sandHelmet.setItemMeta(sMeta);
		
		en.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, false));
		en.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(15);
		
		en.getEquipment().setHelmet(sandHelmet);
		en.getEquipment().setHelmetDropChance(0);
	}

}
