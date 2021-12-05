package us.teaminceptus.smpcore.planatae.entities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class WildSpeedTitan extends EntityZombie {

	static Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public WildSpeedTitan(Location loc) {
		super((EntityTypes<? extends EntityZombie>) GeneralUtils.matchEntityType("zombie"), ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPersistenceRequired(false);
		this.a(true); // Set Baby
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		Zombie w = (Zombie) this.getBukkitEntity();
		w.setHealth(8000);
		w.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(4000);
		w.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.5D);
		w.setCustomName(ChatColor.DARK_AQUA + "Wild Speed Titan");
		w.setCustomNameVisible(true);
		
		ItemStack star = PlanataeFetcher.getOmegaStar();
		star.setAmount(r.nextInt(3) + 1);
		
		w.getEquipment().setChestplate(star);
		w.getEquipment().setChestplateDropChance(0.05f);
		
		w.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
	}
	
}
