package us.teaminceptus.smpcore.planatae.entities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.wither.EntityWither;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class WildDamageTitan extends EntityWither {
	
	static Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public WildDamageTitan(Location loc) {
		super((EntityTypes<? extends EntityWither>) GeneralUtils.matchEntityType("wither"), ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPersistenceRequired(false);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Wither w = (Wither) this.getBukkitEntity();
		w.setHealth(8000);
		w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(8000);
		w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
		w.setCustomName(ChatColor.RED + "Wild Damage Titan");
		w.setCustomNameVisible(true);
		
		ItemStack star = PlanataeFetcher.getOmegaStar();
		star.setAmount(r.nextInt(4) + 1);
		
		w.getEquipment().setChestplate(star);
		w.getEquipment().setChestplateDropChance(1f);
	}
	
}
