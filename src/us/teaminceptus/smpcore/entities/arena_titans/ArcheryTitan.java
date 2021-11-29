package us.teaminceptus.smpcore.entities.arena_titans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Skeleton;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;

public class ArcheryTitan extends EntitySkeleton {

	public ArcheryTitan(Location loc) {
		super(EntityTypes.aB, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setCanPickupLoot(false);
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText(ChatColor.BLUE + "" + ChatColor.BOLD + "Archery Titan"));
		
		Skeleton en = (Skeleton) this.getBukkitEntity();
		
		en.getEquipment().setItemInMainHand(PlanataeFetcher.getChalcBow());
		en.getEquipment().setItemInMainHandDropChance(0f);
		
		en.getEquipment().setHelmet(ArenaTitanFetcher.getArcherHelmet());
		en.getEquipment().setHelmetDropChance(0F);
		
		FinderUtils.addTitanEffects(2, en);
		FinderUtils.setAttributes(en, 45);
	}
	
}
