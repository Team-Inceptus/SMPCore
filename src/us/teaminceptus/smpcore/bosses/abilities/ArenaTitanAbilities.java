package us.teaminceptus.smpcore.bosses.abilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftHusk;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SizedFireball;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zoglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.entities.arena_titans.IceBear;
import us.teaminceptus.smpcore.utils.AdvancementMessages;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.ItemFetcher;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class ArenaTitanAbilities implements Listener {
	
	protected SMPCore plugin;
	
	public ArenaTitanAbilities(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
	private World world = Bukkit.getWorld("world_titan_end");
	
	public Location[] crystalCoords = {
		new Location(world, -12.5, 95, -39.5),
		new Location(world, -33.5, 86, -24.5),
		new Location(world, -41.5, 86, -0.5),
		new Location(world, -33.5, 83, 24.5),
		new Location(world, -12.5, 89, 39.5),
		new Location(world, 12.5, 80, 39.5),
		new Location(world, 33.5, 92, 24.5),
		new Location(world, 42.5, 101, 0.5),
		new Location(world, 33.5, 77, -24.5),
		new Location(world, 12.5, 98, -39.5),
	};
	
	protected void announceDialogue(LivingEntity en, String dialogue) {
		String customName = (en.getCustomName().contains("-") ? en.getCustomName().split("-")[0].substring(0, en.getCustomName().split("-")[0].length() - 1) : en.getCustomName());
		en.getWorld().getPlayers().forEach(p -> {
			p.sendMessage(ChatColor.RED + "[" + customName + ChatColor.RED + "] " + ChatColor.WHITE + dialogue);
		});
	}
	
	List<UUID> nullified = new ArrayList<>();
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		Projectile proj = e.getEntity();
		
		if (!(proj.getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (proj instanceof AbstractArrow) return;
		if (proj.getShooter() == null) return;
		if (!(proj.getShooter() instanceof LivingEntity en)) return;
		if (en instanceof HumanEntity) return;
		if (en.getCustomName() == null) return;
		if (!(en.getCustomName().contains("Titan"))) return;

		
		if (en.getType() == EntityType.SNOWMAN && proj instanceof Snowball s) {
			s.remove();
			SmallFireball fb = (SmallFireball) en.getWorld().spawnEntity(en.getEyeLocation().add(en.getEyeLocation().getDirection().multiply(4)), EntityType.SMALL_FIREBALL);
			fb.setDisplayItem(new ItemStack(Material.SNOWBALL));
			fb.setDirection(en.getEyeLocation().getDirection());
		}
	}
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent e) {
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (e.getEntity() instanceof HumanEntity) return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		
		LivingEntity en = (LivingEntity) e.getEntity();
		
		if (e.getEntityType() == EntityType.PILLAGER) {
			e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(3));
			
			if (r.nextInt(100) < 5) {
				announceDialogue(en, "My bow fires extremely fast arrows to speeds that simpletons like you cannot understand.");
			}
		} else if (e.getEntityType() == EntityType.SKELETON) {
			e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(5));
			
			if (r.nextInt(100) < 2) {
				announceDialogue(en, "Crossbow Titan's arrows are too slow, so I sped it up.");
			}
		}
	}
	
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (!(e.getEntity().getWorld().getName().equalsIgnoreCase("world_titan_end"))) return;
		if (e.getEntity() instanceof HumanEntity) return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;

		LivingEntity en = (LivingEntity) e.getEntity();

		if (e.getEntityType() == EntityType.CREEPER) {
			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();

					en.getNearbyEntities(50, 50, 50).forEach(target -> {
						if (!(target instanceof LivingEntity)) return;
						LivingEntity ten = (LivingEntity) target;

						ten.sendMessage(ChatColor.RED + "The Explosion Titan is preparing its Explosive Aura!");
						new BukkitRunnable() {
							public void run() {
								ten.getWorld().createExplosion(ten.getEyeLocation(), 5, false, false, en);
							}
						}.runTaskLater(plugin, 20 * 5);
						
						
 					});
				}
			}.runTaskTimer(plugin, 0, 20 * 10);
		} else if (e.getEntityType() == EntityType.SNOWMAN) {
			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();
					
					SizedFireball fb = (SizedFireball) en.getWorld().spawnEntity(en.getLocation().add(en.getEyeLocation().getDirection().multiply(2)), EntityType.FIREBALL);
					fb.setDirection(en.getEyeLocation().getDirection());
					fb.setDisplayItem(new ItemStack(Material.ICE));
					
				}
			}.runTaskTimer(plugin, 0, 20 * (r.nextInt(2) + 3));
		} else if (e.getEntityType() == EntityType.GIANT) {
			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();
					if (en.isOnGround()) {
						en.getWorld().spawnParticle(Particle.CRIT_MAGIC, en.getLocation().subtract(0, 1.5, 0), 5000, 5, 0, 5, 1);
						en.getNearbyEntities(2.5, 1.5, 2.5).forEach(ent -> {
							if (!(ent instanceof Player p)) return;
							p.damage(125, en);
							p.sendMessage(ChatColor.RED + "You were damaged by the ground pound!");
						});
					}
				}
			}.runTaskTimer(plugin, 0, 20 * (r.nextInt(8) + 3));
		} else if (e.getEntityType() == EntityType.ENDER_DRAGON) {
			List<Location> crystalList = Arrays.asList(crystalCoords);
			new BukkitRunnable() {
				public void run() {
          if (en.isDead()) cancel();
					List<Location> validLocs = new ArrayList<>();
					List<EnderCrystal> crystals = Arrays.asList(world.getEntitiesByClass(EnderCrystal.class).toArray(new EnderCrystal[] {}));
					for (int i = 0; i < crystals.size(); i++) {
						if (!(crystalList.contains(crystals.get(i).getLocation()))) {
							validLocs.add(crystalCoords[i]);
						}
					}
					
					en.getWorld().spawnEntity(validLocs.get(r.nextInt(validLocs.size())), EntityType.ENDER_CRYSTAL);
				}
			}.runTaskTimer(plugin, 100, 20 * (r.nextInt(10) + 5));
			
			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();
					Collection<EnderCrystal> crystals = world.getEntitiesByClass(EnderCrystal.class);

					crystals.forEach(crystal -> {
						crystal.getWorld().playSound(crystal.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 6F, 1F);
            
						Location fb1L = crystal.getLocation();
						fb1L.setYaw(45);
			            fb1L.setPitch(20);
			            crystal.getWorld().spawnEntity(fb1L, EntityType.DRAGON_FIREBALL);

						Location fb2L = crystal.getLocation();
						fb2L.setYaw(90);
						fb2L.setPitch(20);
						crystal.getWorld().spawnEntity(fb2L, EntityType.DRAGON_FIREBALL);
						
						Location fb3L = crystal.getLocation();
						fb3L.setYaw(135);
						fb3L.setPitch(20);
						crystal.getWorld().spawnEntity(fb3L, EntityType.DRAGON_FIREBALL);
						
						Location fb4L = crystal.getLocation();
						fb4L.setYaw(180);
						fb4L.setPitch(20);
						crystal.getWorld().spawnEntity(fb4L, EntityType.DRAGON_FIREBALL);
						
						Location fb5L = crystal.getLocation();
						fb5L.setYaw(225);
						fb5L.setPitch(20);
						crystal.getWorld().spawnEntity(fb5L, EntityType.DRAGON_FIREBALL);
						
						Location fb6L = crystal.getLocation();
						fb6L.setYaw(270);
						fb6L.setPitch(20);
						crystal.getWorld().spawnEntity(fb6L, EntityType.DRAGON_FIREBALL);
						
						Location fb7L = crystal.getLocation();
						fb7L.setYaw(315);
						fb7L.setPitch(20);
						crystal.getWorld().spawnEntity(fb7L, EntityType.DRAGON_FIREBALL);
						
						Location fb8L = crystal.getLocation();
						fb8L.setYaw(360);
						fb8L.setPitch(20);
						crystal.getWorld().spawnEntity(fb8L, EntityType.DRAGON_FIREBALL);
					});
				}
			}.runTaskTimer(plugin, 100, 20 * (r.nextInt(5) + 5));

			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();
					if (en.getPassengers().size() < 1) {
						EnderCrystal dragonBeam = (EnderCrystal) en.getWorld().spawnEntity(en.getLocation(), EntityType.ENDER_CRYSTAL);
		
						dragonBeam.setCustomName(ChatColor.LIGHT_PURPLE + "Dragon Beam");
						dragonBeam.setCustomNameVisible(true);
		
						en.addPassenger(dragonBeam);
					}
				}
			}.runTaskTimer(plugin, 100, 20 * (r.nextInt(15) + 15));

			new BukkitRunnable() {
				public void run() {
					if (en.isDead()) cancel();
					EnderCrystal dragonBeam = world.getEntitiesByClass(EnderCrystal.class).stream().filter(crystal -> crystal.isCustomNameVisible()).findFirst().get();
          
					Player target = world.getPlayers().stream().filter(p -> p.getLocation().distanceSquared(dragonBeam.getLocation()) <= 10000).toList().get(0);

					if (dragonBeam != null && target != null) {
						dragonBeam.setBeamTarget(target.getLocation());

						if (r.nextInt(100) < 4) {
							target.damage(5, dragonBeam);
						}
					}
				}
			}.runTaskTimer(plugin, 0, 1);
		}
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
		WorldServer ws = ((CraftWorld) en.getWorld()).getHandle();
		
		if (den == null) {
			e.setCancelled(true);
			return;
		}
		
		if (r.nextInt(100) < 7) {
			den.sendMessage(ChatColor.RED + "A mysterious energy blocked your attack...!");
		}
		
		if (nullified.contains(den.getUniqueId())) {
			den.sendMessage(ChatColor.DARK_RED + "Your attacks have been nullified! Wait for it to come back to you...");
			e.setCancelled(true);
			return;
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
				announceDialogue(en, "Come, my illager brothers!");
				en.getWorld().spawnEntity(en.getLocation(), EntityType.EVOKER);
			}
		} else if (e.getEntityType() == EntityType.PILLAGER) {
			if (r.nextInt(100) < 10) {
				Block b = en.getWorld().getBlockAt(den.getLocation().subtract(den.getLocation().getDirection().multiply(-3)));
				
				if (b.isPassable()) {
					en.teleport(den.getLocation().subtract(den.getLocation().getDirection().multiply(-3)));
				}
				den.damage(100, en);
			}
			
			if (r.nextInt(100) < 25) {
				en.getWorld().spawnEntity(en.getLocation(), EntityType.RAVAGER);
			}
		} else if (e.getEntityType() == EntityType.VINDICATOR) {
			if (r.nextInt(100) < 75) {
				Skeleton s = (Skeleton) en.getWorld().spawnEntity(en.getLocation(), EntityType.SKELETON);
				
				s.setCustomNameVisible(true);
				s.setCustomName(ChatColor.BLUE + "Axe Skeleton");
				
				s.getEquipment().setHelmet(TitanFetcher.getAxeHelmet());
				s.getEquipment().setHelmetDropChance(0.0005f);
				
				s.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_AXE, 1));
				s.getEquipment().setItemInMainHandDropChance(0f);
				
				s.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250000);
				s.setHealth(250000);
				s.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(0.2);
				s.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.7);
				
				s.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, true, false, false));
			}
			
			
		} else if (e.getEntityType() == EntityType.HOGLIN) {
			if (r.nextInt(100) < 40) {
				den.setVelocity(den.getVelocity().multiply(-5));
				
				announceDialogue(en, "Goodbye!");
			}
			
			if (r.nextInt(100) < 25) {
				Zoglin z = (Zoglin) en.getWorld().spawnEntity(en.getLocation(), EntityType.ZOGLIN);
				
				z.setCustomNameVisible(true);
				z.setCustomName(ChatColor.RED + "Zombified Knockback Hoglin");
				
				z.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(5);
				z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300000);
			}
		} else if (e.getEntityType() == EntityType.WITCH) {
			PotionEffectType[] types = {
				PotionEffectType.POISON,
				PotionEffectType.WITHER,
				PotionEffectType.CONFUSION,
				PotionEffectType.HARM,
				PotionEffectType.SLOW,
				PotionEffectType.WEAKNESS,
				PotionEffectType.UNLUCK,
				PotionEffectType.HUNGER,
				PotionEffectType.BLINDNESS
			};
			
			if (r.nextBoolean() == true) {
				den.addPotionEffect(new PotionEffect(types[r.nextInt(types.length)], 20 * 120, r.nextInt(14 - 4) + 4, false, true, true));
			}
			
			en.getWorld().spawnEntity(en.getLocation(), EntityType.WITCH);
			
			if (r.nextInt(100) < 25) {
				en.getWorld().spawnEntity(en.getLocation(), EntityType.EVOKER);
			}
		} else if (e.getEntityType() == EntityType.CREEPER) {
			if (r.nextInt(100) < 75) {
				en.getWorld().createExplosion(en.getLocation(), r.nextInt(14 - 8) + 8, false, false, en);
			}

			en.getWorld().spawnEntity(en.getLocation(), EntityType.CREEPER);
			en.getWorld().spawnEntity(en.getLocation(), EntityType.CREEPER);
			en.getWorld().spawnEntity(en.getLocation(), EntityType.CREEPER);

			if (r.nextBoolean() == true) {
				Fireball f = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

				f.setYield(1);
				f.setDirection(new Vector(0, 180, 0));
				
				f.setIsIncendiary(true);

				if (r.nextBoolean()) {
					Fireball f1 = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

					f1.setYield(1);
					f1.setDirection(new Vector(0, 180, 0));
					
					f1.setIsIncendiary(true);

					if (r.nextBoolean()) {
						Fireball f2 = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

						f2.setYield(1);
						f2.setDirection(new Vector(0, 180, 0));
						
						f2.setIsIncendiary(true);
						if (r.nextBoolean()) {
							Fireball f3 = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

							f3.setYield(1);
							f3.setDirection(new Vector(0, 180, 0));
							
							f3.setIsIncendiary(true);

							if (r.nextBoolean()) {
								Fireball f4 = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

								f4.setYield(1);
								f4.setDirection(new Vector(0, 180, 0));
								
								f4.setIsIncendiary(true);
								if (r.nextBoolean()) {
									Fireball f5 = (Fireball) den.getWorld().spawnEntity(en.getLocation().add(0, 10, 0), EntityType.FIREBALL);

									f5.setYield(1);
									f5.setDirection(new Vector(0, 180, 0));
									
									f5.setIsIncendiary(true);
								}		
							}
						}
					}
				}
			}
			
			
			if (r.nextInt(100) < 10) {
				Location fireworkLoc = den.getLocation().add(0, 10, 0);
				fireworkLoc.setDirection(new Vector(0, 180, 0));

				Firework f = (Firework) en.getWorld().spawnEntity(fireworkLoc, EntityType.FIREWORK);

				FireworkMeta fMeta = f.getFireworkMeta();

				fMeta.setPower(r.nextInt(20 - 12) + 12);

				FireworkEffect effect = FireworkEffect.builder().withTrail().trail(true).with(FireworkEffect.Type.CREEPER).withColor(Color.GREEN).withFade(Color.fromRGB(15, 120, 0)).build();

				fMeta.addEffect(effect);
				f.setShotAtAngle(true);

				f.setFireworkMeta(fMeta);

				f.setVisualFire(true);
				f.setVelocity(new Vector(0, -1.5, 0));
				
				new BukkitRunnable() {
					public void run() {
						f.detonate();
					}
				}.runTaskLater(plugin, 30);
			}
		} else if (e.getEntityType() == EntityType.IRON_GOLEM) {
			if (r.nextBoolean() == true) {
				Skeleton s = (Skeleton) en.getWorld().spawnEntity(en.getLocation(), EntityType.SKELETON);

				ItemStack titanFerrum = new ItemStack(Material.IRON_BLOCK);
				ItemMeta tMeta = titanFerrum.getItemMeta();
				tMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Titan Ferrum");
				tMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 500, true);
				tMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
				titanFerrum.setItemMeta(tMeta);

				s.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				s.getEquipment().setHelmet(titanFerrum);
				s.getEquipment().setHelmetDropChance(0.0000001f);

				ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
				ItemMeta chestplateMeta = chestplate.getItemMeta();
				chestplateMeta.setUnbreakable(true);
				chestplate.setItemMeta(chestplateMeta);

				s.getEquipment().setChestplate(chestplate);
				s.getEquipment().setChestplateDropChance(0);

				ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
				ItemMeta leggingsMeta = leggings.getItemMeta();
				leggingsMeta.setUnbreakable(true);
				leggings.setItemMeta(leggingsMeta);

				s.getEquipment().setLeggings(leggings);
				s.getEquipment().setLeggingsDropChance(0);

				ItemStack boots = new ItemStack(Material.IRON_BOOTS);
				ItemMeta bootsMeta = boots.getItemMeta();
				bootsMeta.setUnbreakable(true);
				boots.setItemMeta(bootsMeta);

				s.getEquipment().setBoots(boots);
				s.getEquipment().setBootsDropChance(0);


				s.setCustomName(ChatColor.WHITE + "Iron Skeleton");
				s.setCustomNameVisible(true);

				s.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(r.nextInt(700 - 500) + 500);
				s.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(r.nextInt(600 - 450) + 450);
				s.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(750000);
				s.setHealth(750000);
				s.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(5);
				s.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(5);
				s.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(r.nextInt(550 - 300) + 300);


			}


		} else if (e.getEntityType() == EntityType.HUSK) {
			if (r.nextBoolean()) {
				Husk s = (Husk) en.getWorld().spawnEntity(en.getLocation(), EntityType.HUSK);
				
				s.getEquipment().setHelmet(new ItemStack(Material.SAND, 16));
				s.getEquipment().setHelmetDropChance(0.5f);
				
				CraftHusk nmsEntity = (CraftHusk) s;
				nmsEntity.getHandle().ep().b().add(new AttributeModifiable(GenericAttributes.g, a -> a.a(1d)));
				
				s.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(10);
				s.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(600);
			}
			

		} else if (e.getEntityType() == EntityType.STRAY) {
			en.getWorld().spawnEntity(en.getLocation(), EntityType.VEX);
			
			if (r.nextInt(100) < 75) {
				en.getWorld().spawnEntity(en.getLocation(), EntityType.VEX);
				
				if (r.nextBoolean()) {
					en.getWorld().spawnEntity(en.getLocation(), EntityType.VEX);
					
					if (r.nextInt(100) < 25) {
						en.getWorld().spawnEntity(en.getLocation(), EntityType.VEX);
						
						if (r.nextInt(100) < 10) {
							en.getWorld().spawnEntity(en.getLocation(), EntityType.VEX);
						}
					}
				}
			}
		} else if (e.getEntityType() == EntityType.SNOWMAN) {
			if (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LAVA || e.getCause() == DamageCause.LAVA) e.setCancelled(true);
			
			if (r.nextInt(100) < 25) {
				den.sendMessage(ChatColor.AQUA + "You have been frozen!");
				den.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, false));
			}
			
			if (r.nextInt(100) < 75) {
				IceBear b = new IceBear(en.getLocation(), den);
				ws.e(b);
			}
			
		} else if (e.getEntityType() == EntityType.ZOMBIE) {
			if (r.nextInt(100) < 75 && den instanceof Player p) {
				 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:200f,CustomName:'{\"text\":\"Amethyst Zombie\",\"color\":\"light_purple\",\"italic\":false}',HandItems:[{id:\"minecraft:amethyst_shard\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Amethyst Shard\",\"color\":\"dark_purple\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:sharpness\",lvl:15s},{id:\"minecraft:smite\",lvl:10s},{id:\"minecraft:bane_of_arthropods\",lvl:10s},{id:\"minecraft:looting\",lvl:10s},{id:\"minecraft:sweeping\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:5,Operation:0,UUID:[I;-909321703,1930970078,-2108694589,370468640],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:5,Operation:0,UUID:[I;114330334,-1037156303,-1388268589,-1427227550],Slot:\"offhand\"}]}},{}],HandDropChances:[0.063F,0.085F],ArmorItems:[{id:\"minecraft:leather_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Boots\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:4,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"feet\"}]}},{id:\"minecraft:leather_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Leggings\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:7,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"legs\"}]}},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Chestplate\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:8,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"chest\"}]}},{id:\"minecraft:amethyst_block\",Count:16b}],ArmorDropChances:[0.125F,125.000F,0.125F,1.000F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.movement_speed,Base:0.2},{Name:generic.attack_damage,Base:10},{Name:generic.armor,Base:15},{Name:generic.armor_toughness,Base:10},{Name:generic.attack_knockback,Base:4},{Name:zombie.spawn_reinforcements,Base:1}]}");
			}
			
			if (r.nextInt(100) < 15) {
				den.sendMessage(ChatColor.RED + "Blocked Attack!");
				e.setCancelled(true);
			}
		} else if (e.getEntityType() == EntityType.WITHER_SKELETON) {
			if (r.nextInt(100) < 80 && den instanceof Player p) {
				 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon wither_skeleton ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PortalCooldown:200000,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:20f,CustomName:'{\"text\":\"Netherite Skeleton\",\"color\":\"#542D05\",\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Sword\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:5s},{id:\"minecraft:bane_of_arthropods\",lvl:5s},{id:\"minecraft:knockback\",lvl:4s}]}},{id:\"minecraft:netherite_block\",Count:2b}],HandDropChances:[15.000F,5.000F],ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Boots\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Leggings\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Chestplate\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Helmet\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}}],ArmorDropChances:[0.085F,0.085F,10.000F,10.000F],ActiveEffects:[{Id:5b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:20}]}");
			}
			
			if (r.nextInt(100) < 10) {
				den.sendMessage(ChatColor.RED + "Your attacks have been nullified! Wait for it to come back to you...");
				nullified.add(den.getUniqueId());
				new BukkitRunnable() {
					public void run() {
						den.sendMessage(ChatColor.GREEN + "Your attack power has been restored!");
						nullified.remove(den.getUniqueId());
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(15) + 5));
			}
		} else if (e.getEntityType() == EntityType.SKELETON) {
			if (den instanceof Player p)
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon skeleton ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,FallFlying:1b,NoAI:0b,CanPickUpLoot:1b,Health:300f,CustomName:'{\"text\":\"Super Sniper\",\"color\":\"dark_purple\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Bow\",\"color\":\"dark_purple\",\"italic\":false}'},Unbreakable:1b,Enchantments:[{id:\"minecraft:unbreaking\",lvl:7s},{id:\"minecraft:power\",lvl:10s},{id:\"minecraft:punch\",lvl:3s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s},{id:\"minecraft:mending\",lvl:1s}]}},{}],ArmorItems:[{},{},{},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Cap\",\"color\":\"dark_purple\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:4s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:respiration\",lvl:3s},{id:\"minecraft:thorns\",lvl:4s},{id:\"minecraft:unbreaking\",lvl:7s}]}}],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:300},{Name:generic.knockback_resistance,Base:1}]}");
		
			if (r.nextInt(100) < 10 && den instanceof Player p) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon skeleton ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,FallFlying:1b,NoAI:0b,CanPickUpLoot:1b,Health:300f,CustomName:'{\"text\":\"Super Sniper\",\"color\":\"dark_purple\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Bow\",\"color\":\"dark_purple\",\"italic\":false}'},Unbreakable:1b,Enchantments:[{id:\"minecraft:unbreaking\",lvl:7s},{id:\"minecraft:power\",lvl:10s},{id:\"minecraft:punch\",lvl:3s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s},{id:\"minecraft:mending\",lvl:1s}]}},{}],ArmorItems:[{},{},{},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Cap\",\"color\":\"dark_purple\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:4s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:respiration\",lvl:3s},{id:\"minecraft:thorns\",lvl:4s},{id:\"minecraft:unbreaking\",lvl:7s}]}}],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:300},{Name:generic.knockback_resistance,Base:1}]}");
			}
			
			if (r.nextInt(100) < 25) {
				den.sendMessage(ChatColor.RED + "Arrow Horde!");
				for (int i = 0; i < 100; i++) {
					new BukkitRunnable() {
						public void run() {
							den.getWorld().spawnArrow(den.getLocation().add(0, 5, 0), new Vector(0, 180, 0), 0.9F, 3);
						}
					}.runTaskLater(plugin, 1);
				}
			}
		} else if (e.getEntityType() == EntityType.GIANT) {
			if (r.nextInt(100) < 5) {
				announceDialogue(en, "Kickoff Time!");
				den.setVelocity(den.getLocation().getDirection().multiply(-5));
			}
			
			
			EntityType[] groundTypes = {
				EntityType.ZOMBIE,
				EntityType.HUSK,
				EntityType.SKELETON,
				EntityType.WITHER_SKELETON,
				EntityType.DROWNED,
			};
			
			LivingEntity minion = (LivingEntity) en.getWorld().spawnEntity(en.getLocation(), groundTypes[r.nextInt(groundTypes.length)]);
			
			minion.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(r.nextDouble() * 1.5);
			minion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(r.nextInt(100) + 50);
			
			ItemStack groundChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemMeta gMeta = groundChestplate.getItemMeta();
			gMeta.setUnbreakable(true);
			gMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 200, true);
			gMeta.addEnchant(Enchantment.THORNS, 50, true);
			gMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
			gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			gMeta.setDisplayName(GeneralUtils.hexToChat("a06b09", "Ground Chestplate"));
			
			groundChestplate.setItemMeta(gMeta);
			
			minion.getEquipment().setChestplate(groundChestplate);
			minion.getEquipment().setChestplateDropChance(0.0005f);
			
			ItemStack stone = new ItemStack(Material.STONE);
			ItemMeta sMeta = stone.getItemMeta();
			sMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
			stone.setItemMeta(sMeta);
			
			minion.getEquipment().setHelmet(stone);
			minion.getEquipment().setHelmetDropChance(0f);
		} else if (e.getEntityType() == EntityType.WITHER) {
			if (r.nextInt(100) < 10) {
				
				WitherSkeleton sk = (WitherSkeleton) en.getWorld().spawnEntity(en.getLocation(), EntityType.WITHER_SKELETON);
				
				try {
					ItemStack witherHelmet = GeneralUtils.itemFromNBT("{id: \"minecraft:player_head\", tag: {Enchantments: [{id: \"minecraft:blast_protection\", lvl: 30s}, {id: \"minecraft:fire_protection\", lvl: 30s}, {id: \"minecraft:protection\", lvl: 30s}, {id: \"minecraft:thorns\", lvl: 10s}], display: {Name: '{\"text\":\"Wither Helmet\",\"color\":\"black\",\"bold\":true,\"italic\":false}'}, AttributeModifiers: [{Name: \"generic.max_health\", AttributeName: \"minecraft:generic.max_health\", Operation: 0, UUID: [I; 259362886, -139505908, -1834494027, -1202290931], Slot: \"head\", Amount: 50.0d}], HideFlags: 7, SkullOwner: {Properties: {textures: [{Value: \"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19\"}]}, Id: [I; 295450395, -367638583, -1384176717, -661368310]}}, Count: 1b}");
					sk.getEquipment().setHelmet(witherHelmet);
					sk.getEquipment().setHelmetDropChance(0.00001f);
					
					ItemStack witherChestplate = GeneralUtils.itemFromNBT("{id: \"minecraft:leather_chestplate\", tag: {display: {Name: '{\"text\":\"Wither Chestplate\",\"color\":\"black\",\"bold\":true,\"italic\":false}', Lore: ['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"green\",\"text\":\"Damagecontrol 5\"}],\"text\":\"\"}', '{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"white\",\"text\":\"Glide 1\"}],\"text\":\"\"}', '{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Immunity 2\"}],\"text\":\"\"}', '{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"red\",\"text\":\"Regeneration 1\"}],\"text\":\"\"}'], color: 0}, AttributeModifiers: [{Slot: \"chest\", AttributeName: \"minecraft:generic.max_health\", Operation: 0, UUID: [I; 259362886, -139505908, -1834494027, -1202290931], Name: \"generic.max_health\", Amount: 50.0d}], Enchantments: [{id: \"minecraft:blast_protection\", lvl: 30s}, {id: \"minecraft:fire_protection\", lvl: 30s}, {id: \"minecraft:protection\", lvl: 30s}, {id: \"minecraft:thorns\", lvl: 10s}], Unbreakable: 1b, Damage: 0, HideFlags: 7}, Count: 1b}");
					sk.getEquipment().setChestplate(witherChestplate);
					sk.getEquipment().setChestplateDropChance(0.000000000000001f);
					
					ItemStack witherLeggings = GeneralUtils.itemFromNBT("{id: \"minecraft:leather_leggings\", tag: {HideFlags: 7, Damage: 0, Enchantments: [{id: \"minecraft:blast_protection\", lvl: 30s}, {id: \"minecraft:fire_protection\", lvl: 30s}, {id: \"minecraft:protection\", lvl: 30s}, {id: \"minecraft:thorns\", lvl: 10s}], Unbreakable: 1b, AttributeModifiers: [{Name: \"generic.max_health\", Amount: 50.0d, Operation: 0, UUID: [I; 259362886, -139505908, -1834494027, -1202290931], Slot: \"legs\", AttributeName: \"minecraft:generic.max_health\"}], display: {Name: '{\"text\":\"Wither Leggings\",\"color\":\"black\",\"bold\":true,\"italic\":false}', color: 0, Lore: ['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"green\",\"text\":\"Damagecontrol 5\"}],\"text\":\"\"}']}}, Count: 1b}");
					sk.getEquipment().setLeggings(witherLeggings);
					sk.getEquipment().setLeggingsDropChance(0.000000001f);
					
					ItemStack witherBoots = GeneralUtils.itemFromNBT("{id: \"minecraft:leather_boots\", tag: {display: {Name: '{\"text\":\"Wither Boots\",\"color\":\"black\",\"bold\":true,\"italic\":false}', Lore: ['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"green\",\"text\":\"Damagecontrol 5\"}],\"text\":\"\"}', '{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"blue\",\"text\":\"Bunny 3\"}],\"text\":\"\"}'], color: 0}, AttributeModifiers: [{Name: \"generic.max_health\", AttributeName: \"minecraft:generic.max_health\", Operation: 0, UUID: [I; 259362886, -139505908, -1834494027, -1202290931], Slot: \"feet\", Amount: 50.0d}], Enchantments: [{id: \"minecraft:blast_protection\", lvl: 30s}, {id: \"minecraft:fire_protection\", lvl: 30s}, {id: \"minecraft:protection\", lvl: 30s}, {id: \"minecraft:thorns\", lvl: 10s}], Unbreakable: 1b, Damage: 0, HideFlags: 7}, Count: 1b}");
					sk.getEquipment().setBoots(witherBoots);
					sk.getEquipment().setBootsDropChance(0.001f);
				} catch (Exception err) {
				}
				
				if (r.nextInt(100) < 10) {
					en.getWorld().spawnEntity(en.getLocation(), EntityType.WITHER);
				}
			}
		} else if (e.getEntityType() == EntityType.ENDER_DRAGON) {
      if (r.nextBoolean()) {
        den.getWorld().createExplosion(den.getLocation(), 5F, false, false, en);
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
		} else if (en.getType() == EntityType.PILLAGER) {
			if (r.nextBoolean() == true) {
				target.getWorld().createExplosion(target.getLocation(), 4, false, false, en);
			}
		} else if (en.getType() == EntityType.VINDICATOR) {
			if (target.getEquipment().getItemInOffHand().getType() == Material.SHIELD) {
				if (target.getEquipment().getItemInOffHand().getItemMeta().isUnbreakable()) {
					ItemStack newShield = target.getEquipment().getItemInOffHand().clone();
					ItemMeta sMeta = newShield.getItemMeta();
					sMeta.setUnbreakable(false);
					newShield.setItemMeta(sMeta);
					
					target.getEquipment().setItemInOffHand(newShield);
					
					announceDialogue(en, "I can make unbreakable shields breakable again! Nice try...");
				}
			}
		} else if (en.getType() == EntityType.HOGLIN) {
			target.setVelocity(target.getVelocity().multiply(3));
		} else if (en.getType() == EntityType.IRON_GOLEM) {
			if (r.nextInt(100) < 10) {
				boolean chance = r.nextBoolean();

				if (chance) {
					target.setVelocity(new Vector(target.getVelocity().getX() * 1.25, 2.5, target.getVelocity().getZ() * 1.25));
				} else {
					target.setVelocity(new Vector(target.getVelocity().getX(), 3, target.getVelocity().getZ()));
				}
			}

			if (r.nextBoolean()) target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 4, false, false, true));
			
			if (r.nextInt(100) < 15) {
				for (int count = 0;count < 20; count++) {
					new BukkitRunnable() {
						public void run() {
							target.damage(25, en);
						}
					}.runTaskLater(plugin, 5L);
				}
			}


		} else if (en.getType() == EntityType.HUSK) {
			if (r.nextInt(100) < 75) {
				target.sendMessage(ChatColor.YELLOW + "You've been blinded!");
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 2, true, false, false));
			}
			
			if (r.nextInt(100) < 10) {
				target.sendMessage(ChatColor.GREEN + "Whoosh!");
				target.setVelocity(target.getVelocity().multiply(-7));
			}
		} else if (en.getType() == EntityType.STRAY) {
			if (r.nextInt(100) < 50) {
				target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BOO!");
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 3, true, false, false));
				target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 2, true, false, false));
				target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 500, 4, true, false, false));
			}
		} else if (en.getType() == EntityType.SNOWMAN) {
			if (r.nextInt(100) < 10) {
				target.sendMessage(ChatColor.AQUA + "DOUBLE DAMAGE!");
				target.damage(e.getFinalDamage(), en);
			}
		} else if (en.getType() == EntityType.ZOMBIE) {
			if (r.nextInt(100) < 25) {
				announceDialogue(en, "Amethyst PUNCH!");
				target.setVelocity(target.getVelocity().multiply(-5.5));
			}
			
			if (r.nextInt(100) < 10) {
				target.sendMessage(ChatColor.DARK_RED + "You got impaled!");
				target.damage(110, en);
			}
		} else if (en.getType() == EntityType.WITHER_SKELETON) {
			if (r.nextInt(100) < 10) {
				announceDialogue(en, "Say goodbye to you Legs!");
				target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, false));
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 255, true, false, false));
			}
			
			if (r.nextInt(100) < 5) {
				announceDialogue(en, "*Yeets into the Sun*");
				target.setVelocity(new Vector(target.getVelocity().getX(), 4, target.getVelocity().getZ()));
			}
		} else if (en.getType() == EntityType.SKELETON) {
			if (r.nextInt(100) < 60) {
				announceDialogue(en, "BOOM!");
				target.getWorld().createExplosion(en.getLocation(), 5F, false, false, en);
			}
		} else if (en.getType() == EntityType.GIANT) {
			if (r.nextInt(100) < 25) {
				target.getWorld().createExplosion(en.getLocation(), 6F, false, false, en);
			}
		} else if (en.getType() == EntityType.WITHER) {
			if (e.getEntity() instanceof Projectile proj) {
				if (r.nextBoolean()) {
					proj.getWorld().createExplosion(proj.getLocation(), 6F, false, false, en); 
				}
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
		} else if (e.getEntityType() == EntityType.PILLAGER) {
			ItemStack arrows = TitanFetcher.getVelocityArrow();
			arrows.setAmount(r.nextInt(32 - 4) + 4);
			
			en.getWorld().dropItemNaturally(en.getLocation(), arrows);
		} else if (e.getEntityType() == EntityType.VINDICATOR) {
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getClades());
			}
			
			
		} else if (e.getEntityType() == EntityType.HOGLIN) {
			ItemStack pork = TitanFetcher.getTitanPorkchop();
			pork.setAmount(r.nextInt(64 - 16) + 16);
			
			en.getWorld().dropItemNaturally(en.getLocation(), pork);
			
			if (r.nextInt(100) < 10) {
				ItemStack punchStick = new ItemStack(Material.BONE, 1);
				ItemMeta pMeta = punchStick.getItemMeta();
				
				pMeta.setDisplayName(ChatColor.RED + "Punch Stick");
				pMeta.addEnchant(Enchantment.KNOCKBACK, 700, true);
				pMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
				
				punchStick.setItemMeta(pMeta);
				
				en.getWorld().dropItemNaturally(en.getLocation(), punchStick);
			}
		} else if (e.getEntityType() == EntityType.WITCH) {
			int chance = r.nextInt(100);
			try {
				ItemStack titanPot = GeneralUtils.itemFromNBT("{id: \"minecraft:potion\", tag: {display: {Name: '{\"text\":\"Potion of Titans\",\"color\":\"blue\",\"bold\":true,\"italic\":false}'}, Enchantments: [{id: \"minecraft:protection\", lvl: 1s}], CustomPotionEffects: [{ShowParticles: 1b, Id: 1b, Duration: 6000, ShowIcon: 1b, Amplifier: 2b, Ambient: 0b}, {ShowParticles: 1b, Id: 5b, Duration: 6000, ShowIcon: 1b, Amplifier: 6b, Ambient: 0b}, {ShowParticles: 1b, Id: 8b, Duration: 6000, ShowIcon: 1b, Amplifier: 2b, Ambient: 0b}, {ShowParticles: 1b, Id: 11b, Duration: 6000, ShowIcon: 1b, Amplifier: 3b, Ambient: 0b}, {ShowParticles: 1b, Id: 12b, Duration: 6000, ShowIcon: 1b, Amplifier: 2b, Ambient: 0b}, {ShowParticles: 1b, Id: 13b, Duration: 6000, ShowIcon: 1b, Amplifier: 1b, Ambient: 0b}, {ShowParticles: 1b, Id: 16b, Duration: 6000, ShowIcon: 1b, Amplifier: 1b, Ambient: 0b}, {ShowParticles: 1b, Id: 22b, Duration: 6000, ShowIcon: 1b, Amplifier: 6b, Ambient: 0b}, {ShowParticles: 1b, Id: 23b, Duration: 6000, ShowIcon: 1b, Amplifier: 1b, Ambient: 0b}, {ShowParticles: 1b, Id: 26b, Duration: 6000, ShowIcon: 1b, Amplifier: 3b, Ambient: 0b}, {ShowParticles: 1b, Id: 28b, Duration: 6000, ShowIcon: 1b, Amplifier: 2b, Ambient: 0b}], HideFlags: 1, CustomPotionColor: 39423, Potion: \"minecraft:empty\"}, Count: 1b}");
				en.getWorld().dropItemNaturally(en.getLocation(), titanPot);
				
				if (chance < 60) {
					en.getWorld().dropItemNaturally(en.getLocation(), titanPot);
				}
				
				if (chance < 30) {
					en.getWorld().dropItemNaturally(en.getLocation(), titanPot);
				}
				
				if (chance < 15) {
					en.getWorld().dropItemNaturally(en.getLocation(), titanPot);
				}
				
				if (chance < 5) {
					en.getWorld().dropItemNaturally(en.getLocation(), titanPot);
				}
			} catch (CommandSyntaxException err) {
				err.printStackTrace();
			}
		} else if (e.getEntityType() == EntityType.IRON_GOLEM) {
			int max = r.nextInt(40 - 15) + 15;

			for (int count = 0; count < max; count++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.IRON_BLOCK, 64));
			}

			ItemStack ferrumStack = TitanFetcher.getFerrumBlock();
			ferrumStack.setAmount(64);

			en.getWorld().dropItemNaturally(en.getLocation(), ferrumStack);

			if (r.nextInt(100) < 15) {
				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getFerrumCore());
			}

			if (r.nextInt(1000) < 5) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.WHITE + "Iron Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
		} else if (e.getEntityType() == EntityType.HUSK) {
			ItemStack clade = PlanataeFetcher.getClade();
			clade.setAmount(r.nextInt(16) + 16);
			en.getWorld().dropItemNaturally(en.getLocation(), clade);
			
			
			if (r.nextInt(1000) < 8) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.GOLD + "Sand Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
		} else if (e.getEntityType() == EntityType.STRAY) {
			if (r.nextBoolean()) en.getWorld().dropItemNaturally(en.getLocation(), PlanataeFetcher.getOmegaStar());
			
			if (r.nextInt(100) < 1) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.GRAY + "Ghost Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
		} else if (e.getEntityType() == EntityType.SNOWMAN) {
			int maxPacked = r.nextInt(16) + 16;
			int maxBlue = r.nextInt(8) + 8;
			int maxGamma = r.nextInt(4) + 2;
			
			for (int count = 0; count < maxPacked; count++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.PACKED_ICE, 64));
			}
			
			for (int count = 0; count < maxBlue; count++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.BLUE_ICE, 64));
			}
			
			ItemStack gamma = PlanataeFetcher.getGammaIce();
			gamma.setAmount(64);
			
			for (int count = 0; count < maxGamma; count++) {
				en.getWorld().dropItemNaturally(en.getLocation(), gamma);
			}
			
			if (r.nextInt(100) < 2) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.AQUA + "Ice Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
		} else if (e.getEntityType() == EntityType.ZOMBIE) {
			int max = r.nextInt(6) + 4;
			int maxGamma = r.nextInt(4) + 1;
			
			for (int count = 0; count < max; count++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.AMETHYST_BLOCK, 64));
			}
			
			ItemStack gammaA = PlanataeFetcher.getGammaAmethyst();
			gammaA.setAmount(64);

			for (int i = 0; i < maxGamma; i++) {
				en.getWorld().dropItemNaturally(en.getLocation(), gammaA);
			}
			
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HAND));
			}
			
			if (r.nextInt(100) < 5) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.DARK_PURPLE + " Titan Amethysts Boots");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.FEET));
			}
			
			if (r.nextInt(100) < 1) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.DARK_PURPLE + " Titan Amethysts Helmet");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HEAD));
			}
			
			if (r.nextInt(1000) < 1) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Legendary Drop!" + ChatColor.DARK_PURPLE + " Titan Amethysts Leggings");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.LEGS));
			}
			
			if (r.nextInt(10000) < 1) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Mythic Drop!" + ChatColor.DARK_PURPLE + " Titan Amethysts Chestplate");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.CHEST));
			}
			
			if (r.nextInt(100) < 2) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.LIGHT_PURPLE + "Amethyst Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
		} else if (e.getEntityType() == EntityType.WITHER_SKELETON) {
			int max = r.nextInt(2) + 1;
			
			for (int i = 0; i < max; i++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.NETHERITE_INGOT, 64));
			}
			
			if (r.nextInt(100) < 3) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.DARK_GRAY + "Netherite Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
			
			if (r.nextInt(100) < 5) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.GRAY + " Netherite Totem");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.OFF_HAND));
			}
			
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanAmethystusSet().get(EquipmentSlot.HAND));
			}
			
			if (r.nextInt(100) < 3) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.DARK_GRAY + " Titan Netherite Boots");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanNetheriteSet().get(EquipmentSlot.FEET));
			}
			
			if (r.nextInt(1000) < 2) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.DARK_GRAY + " Titan Netherite Helmet");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanNetheriteSet().get(EquipmentSlot.HEAD));
			}
			
			if (r.nextInt(10000) < 5) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Legendary Drop!" + ChatColor.DARK_GRAY + " Titan Netherite Leggings");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanNetheriteSet().get(EquipmentSlot.LEGS));
			}
			
			if (r.nextInt(100000) < 4) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Mythic Drop!" + ChatColor.DARK_GRAY + " Titan Netherite Chestplate");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getTitanNetheriteSet().get(EquipmentSlot.CHEST));
			}
		} else if (e.getEntityType() == EntityType.SKELETON) {
			if (r.nextInt(100) < 5) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.WHITE + " Titan Essence" + ChatColor.GRAY + " dropped by " + ChatColor.GRAY + "Archer Titan" + ChatColor.GRAY + "!");

				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
			
			int max = r.nextInt(16) + 8;
			
			for (int i = 0; i < max; i++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.ARROW, 64));
			}
			
			if (r.nextInt(1000) < 4) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crazy Rare Drop!" + ChatColor.DARK_BLUE + " Archer Helmet");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getArcherHelmet());
			}
			
			if (r.nextInt(100) < 5) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.GOLD + " Chalc Bow");
				en.getWorld().dropItemNaturally(en.getLocation(), PlanataeFetcher.getChalcBow());
			}
		} else if (e.getEntityType() == EntityType.GIANT) {
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
			
			int max = r.nextInt(24) + 16;
			
			for (int i = 0; i < max; i++) {
				en.getWorld().dropItemNaturally(en.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 64));
			}
			
			int count = r.nextInt(4) + 1;
			
			ItemStack potato = ArenaTitanFetcher.getProtectionPotato();
			potato.setAmount(count);
			
			en.getWorld().dropItemNaturally(en.getLocation(), potato);
		} else if (e.getEntityType() == EntityType.WITHER) {
			if (r.nextInt(100) < 10) {
				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
			
			int max = r.nextInt(12) + 4;
			
			ItemStack material = ItemFetcher.getWitherMaterial();
			material.setAmount(64);
			
			for (int i = 0; i < max; i++) {
				en.getWorld().dropItemNaturally(en.getLocation(), material);
			}
			
			if (r.nextInt(100) < 1) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Rare Drop!" + ChatColor.DARK_GRAY + " Wither Scythe");
				en.getWorld().dropItemNaturally(en.getLocation(), ArenaTitanFetcher.getWitherScythe());
			}
		} else if (e.getEntityType() == EntityType.ENDER_DRAGON) {
			if (r.nextInt(100) < 25) {
				en.getWorld().dropItemNaturally(en.getLocation(), TitanFetcher.getTitanEssence());
			}
			
			ItemStack cracks = ItemFetcher.getVoidCrack();
			cracks.setAmount(r.nextInt(16) + 32);
			
			ItemStack remnants = ItemFetcher.getWingRemnant();
			remnants.setAmount(r.nextInt(32) + 32);
			
			ItemStack essence = ItemFetcher.getEndEssence();
			essence.setAmount(r.nextInt(16) + 48);
			
			en.getWorld().dropItemNaturally(en.getLocation(), cracks);
			en.getWorld().dropItemNaturally(en.getLocation(), remnants);
			
			en.getWorld().dropItemNaturally(en.getLocation(), essence);
			en.getWorld().dropItemNaturally(en.getLocation(), essence);
		}
		
		if (en.getKiller() == null) return;
		Player p = en.getKiller();
		
		plugin.saveConfig();

		plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("titan_kills", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_kills") + 1);
		
	   	 int titanKills = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_kills");
	   	 if (titanKills == 1) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(1, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 5) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(2, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 15) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(3, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 30) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(4, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 50) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(5, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 80) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(6, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 } else if (titanKills == 125) {
	   		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanKiller(7, true));
	   		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	   	 }
		
		plugin.saveConfig();
	}
	
}
