package gamercoder215.smpcore.listeners.caves;

import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;

public abstract class Cave implements Listener {
	
	protected Main plugin;
	protected Map<Material, ItemStack> drops;
	protected int chance;
	protected int exp;
	
	protected Cave(Main plugin, Map<Material, ItemStack> drops, int chance) {
		this.plugin = plugin;
		this.drops = drops;
		this.chance = chance;
		this.exp = 0;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	protected Cave(Main plugin, Map<Material, ItemStack> drops, int chance, int exp) {
		this.plugin = plugin;
		this.drops = drops;
		this.chance = chance;
		this.exp = exp;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	private void replenish(Block b, int replenishTime) {
		b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
		
		new BukkitRunnable() {
			public void run() {
				b.getWorld().getBlockAt(b.getLocation()).setType(b.getType());
			}
		}.runTaskLater(plugin, 20 * replenishTime);
	}

	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().contains("cave"))) return;
		
		e.setCancelled(true);
		e.setDropItems(false);
		e.setExpToDrop(exp);
		
		if (drops.containsKey(e.getBlock().getType())) {
			ItemStack drop = drops.get(e.getBlock().getType());
			
			int chance = this.chance;
			
			if (r.nextInt(100) < chance) {
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
				ExperienceOrb exporb = (ExperienceOrb) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.EXPERIENCE_ORB);
				
				exporb.setExperience(r.nextInt((exp + 20) - (exp - 20)) + (exp - 20));
				
				if (e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_delta")) {
					replenish(e.getBlock(), r.nextInt(5 - 3) + 3);
				} else if (e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_alpha")) {
					replenish(e.getBlock(), r.nextInt(15 - 7) + 7);
				} else if (e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_titan")) {
					replenish(e.getBlock(), r.nextInt(35 - 15) + 15);
				} else replenish(e.getBlock(), r.nextInt(5 - 3) + 3);
			}
		}
	}
	
	
	
}
