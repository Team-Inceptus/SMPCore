package us.teaminceptus.smpcore.planatae.entities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.MagmaCube;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityMagmaCube;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class WildNetherTitan extends EntityMagmaCube {

	static Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public WildNetherTitan(Location loc) {
		super((EntityTypes<? extends EntityMagmaCube>) GeneralUtils.matchEntityType("magma_cube"), ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPersistenceRequired(false);
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		
		MagmaCube w = (MagmaCube) this.getBukkitEntity();
		w.setHealth(8000);
		w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(7000);
		w.setCustomName(ChatColor.DARK_RED + "Wild Nether Titan");
		w.setCustomNameVisible(true);
		w.setSize(r.nextInt(5) + 1);
		
		ItemStack stick = PlanataeFetcher.getLambdaStick();
		stick.setAmount(r.nextInt(7) + 2);
		
		w.getEquipment().setChestplate(stick);
		w.getEquipment().setChestplateDropChance(1f);
	}
	
}
