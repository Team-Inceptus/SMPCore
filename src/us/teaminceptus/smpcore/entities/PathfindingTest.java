package us.teaminceptus.smpcore.entities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;

public class PathfindingTest extends EntityZombie {
	
    public PathfindingTest(Location loc)
    {
        super(EntityTypes.be, ((CraftWorld) loc.getWorld()).getHandle());
        
		this.b(loc.getX(), loc.getY(), loc.getZ()); // Position
    }
}
