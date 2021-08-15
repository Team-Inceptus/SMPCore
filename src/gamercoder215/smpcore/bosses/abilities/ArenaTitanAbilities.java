package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

public class ArenaTitanAbilities implements Listener {
	
	protected Main plugin;
	
	public ArenaTitanAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	protected void announceDialogue(LivingEntity en, String dialogue) {
		String customName = (en.getCustomName().contains("-") ? en.getCustomName().split("-")[0].substring(0, en.getCustomName().split("-")[0].length() - 1) : en.getCustomName());
		en.getWorld().getPlayers().forEach(p -> {
			p.sendMessage(ChatColor.RED + "[" + customName + ChatColor.RED + "] " + ChatColor.WHITE + dialogue);
		});
	}
	
	
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (e.getEntity() instanceof HumanEntity) return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		
		LivingEntity en = (LivingEntity) e.getEntity();
		LivingEntity den = (LivingEntity) (e.getDamager() instanceof Projectile ? ((Projectile) e.getDamager()).getShooter() : e.getDamager());
		
		if (r.nextInt(100) < 7) {
			den.sendMessage(ChatColor.RED + "A mysterious energy blocked your attack...!");
		}
		
		if (e.getEntityType() == EntityType.BLAZE) {
			if (den.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
				den.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			}
			
			if (r.nextInt(100) < 5) {
				announceDialogue(en, "Fire is a deadly thing. Glad I can use it well.");
				den.setVelocity(new Vector(den.getVelocity().getX() * -2.5, den.getVelocity().getY() * 2, den.getVelocity().getZ() * -2.5));
			}
			
			en.getWorld().spawnEntity(en.getLocation(), EntityType.BLAZE);
			
			if (r.nextBoolean() == true) {
				if (r.nextBoolean() == true && r.nextBoolean() == true) {
					announceDialogue(en, "Come, my fellow fire brothers!");
				}
				MagmaCube cube = (MagmaCube) en.getWorld().spawnEntity(en.getLocation(), EntityType.MAGMA_CUBE);
				cube.setSize(r.nextInt(14 - 6) + 6);
			}
			
			
		} else if (e.getEntityType() == EntityType.ILLUSIONER) {
			if (r.nextInt(100) < 15) {
				announceDialogue(en, "Come, my villager brothers!");
				if (r.nextInt(100) < 25) {
					en.getWorld().spawnEntity(en.getLocation(), EntityType.EVOKER);
				} else {
					if (r.nextBoolean() == true) {
						Vindicator v = (Vindicator) en.getWorld().spawnEntity(en.getLocation(), EntityType.VINDICATOR);
						
						v.setCustomName(ChatColor.DARK_GREEN + "Magic Vindicator");
						v.setCustomNameVisible(true);
						v.setHealth(r.nextInt(10000 - 25000) + 25000);
						v.getEquipment().setItem(EquipmentSlot.HAND, TitanFetcher.getMagicalAxe());            
					} else {
						en.getWorld().spawnEntity(en.getLocation(), EntityType.PILLAGER);
					}
				}
			}
		}
		
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (!(e.getDamager().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (e.getDamager() instanceof HumanEntity) return;
		if (!(e.getDamager() instanceof LivingEntity)) return;
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getDamager().getCustomName().contains("Titan"))) return;
		
		LivingEntity en = (LivingEntity) (e.getDamager() instanceof Projectile ? ((Projectile) e.getDamager()).getShooter() : e.getDamager());
		LivingEntity target = (LivingEntity) e.getEntity();
		
		if (en.getType() == EntityType.BLAZE) {
			if (r.nextBoolean() == true) {
				target.getWorld().createExplosion(target.getLocation(), 6, true, false, en);
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (e.getEntity() instanceof HumanEntity) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		
		LivingEntity en = e.getEntity();
		
		if (e.getEntityType() == EntityType.BLAZE) {
			en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getInfernoBlade());
			
			if (r.nextInt(100) < 25) {
				if (r.nextInt(100) < 35) {
					en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getInfernoNetheriteSet()[0]);
				}
				
				if (r.nextInt(100) < 20) {
					en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getInfernoNetheriteSet()[1]);
				}
				
				if (r.nextInt(100) < 25) {
					en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getInfernoNetheriteSet()[2]);
				}
				
				if (r.nextInt(100) < 40) {
					en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getInfernoNetheriteSet()[3]);
				}
			}
		} else if (e.getEntityType() == EntityType.ILLUSIONER) {
			en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getAttributeApple());
		}
	}
	
}
