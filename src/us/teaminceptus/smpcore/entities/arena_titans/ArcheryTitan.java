package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Skeleton;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class ArcheryTitan extends EntitySkeleton {

	public ArcheryTitan(Location loc) {
		super(EntityTypes.aB, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
		
		this.r(false); // Can Pick up Loot
		this.u(true); // Aggressive
		this.n(true); // Custom Name Visible
		this.a(new ChatComponentText(ChatColor.BLUE + "" + ChatColor.BOLD + "Archery Titan")); // Set Custom Name
		
		Skeleton en = (Skeleton) this.getBukkitEntity();
		
		en.getEquipment().setItemInMainHand(PlanataeFetcher.getChalcBow());
		en.getEquipment().setItemInMainHandDropChance(0f);
		
		en.getEquipment().setHelmet(ArenaTitanFetcher.getArcherHelmet());
		en.getEquipment().setHelmetDropChance(0F);
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 45);
	}
	
}
