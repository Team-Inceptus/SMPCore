package gamercoder215.smpcore.utils.entities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;

public class PathfindingTest extends EntityZombie {
	
    public PathfindingTest(Location loc)
    {
        super(EntityTypes.be, ((CraftWorld) loc.getWorld()).getHandle());
        
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
    }
}
