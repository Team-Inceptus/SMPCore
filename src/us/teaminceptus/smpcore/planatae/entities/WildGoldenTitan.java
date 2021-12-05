package us.teaminceptus.smpcore.planatae.entities;

import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class WildGoldenTitan extends EntitySkeleton {

	static Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public WildGoldenTitan(Location loc) {
		super((EntityTypes<? extends EntitySkeleton>) GeneralUtils.matchEntityType("skeleton"), ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPersistenceRequired(false);
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		
		Skeleton w = (Skeleton) this.getBukkitEntity();
		w.setHealth(8000);
		w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10000);
		w.setCustomName(ChatColor.GOLD + "Wild Golden Titan");
		w.setCustomNameVisible(true);
		
		ItemStack gold = PlanataeFetcher.getAurum();
		gold.setAmount(r.nextInt(32) + 8);
		
		w.getEquipment().setChestplate(gold);
		w.getEquipment().setChestplateDropChance(0.75f);
		
		ItemStack chrysosHelmet = new ItemStack(Material.GOLDEN_HELMET);
		ItemMeta cMeta = chrysosHelmet.getItemMeta();
		cMeta.setDisplayName(ChatColor.GOLD + "Chrysos Helmet");
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true);
		cMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
		
		cMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 75, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		
		chrysosHelmet.setItemMeta(cMeta);
		
		w.getEquipment().setHelmet(chrysosHelmet);
		w.getEquipment().setItemInMainHand(null);
	}

}
