package gamercoder215.smpcore.utils.classes;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.WandCooldowns;
import gamercoder215.smpcore.utils.enums.WandType;

public class Wand {
	
	private Main plugin;
	
	public String wandName;
	public WandType wandType;
	public ItemStack wandItem;
	public Player player;
	
	public PrimarySpell primary;
	public SecondarySpell secondary;
	
	private ItemStack parseWandType(WandType type) {
		if (type.equals(WandType.ALLIUM)) return new ItemStack(Material.ALLIUM, 1);
		else if (type.equals(WandType.BAMBOO)) return new ItemStack(Material.BAMBOO, 1);
		else if (type.equals(WandType.BLAZE_ROD)) return new ItemStack(Material.BLAZE_ROD, 1);
		else if (type.equals(WandType.BONE)) return new ItemStack(Material.BONE, 1);
		else if (type.equals(WandType.END_ROD)) return new ItemStack(Material.END_ROD, 1);
		else if (type.equals(WandType.FIREWORK_ROCKET)) return new ItemStack(Material.FIREWORK_ROCKET, 1);
		else if (type.equals(WandType.LEVER)) return new ItemStack(Material.LEVER, 1);
		else if (type.equals(WandType.SOUL_TORCH)) return new ItemStack(Material.SOUL_TORCH, 1);
		else if (type.equals(WandType.STICK)) return new ItemStack(Material.STICK, 1);
		else if (type.equals(WandType.TORCH)) return new ItemStack(Material.TORCH, 1);
		else if (type.equals(WandType.TRIDENT)) return new ItemStack(Material.TRIDENT, 1);
		else if (type.equals(WandType.TRIPWIRE)) return new ItemStack(Material.TRIPWIRE_HOOK, 1);
		else return new ItemStack(Material.STICK, 1);
	}
	
	public Wand(Main plugin, String wandName, Player p, WandType type, PrimarySpell primary, SecondarySpell secondary) {
		this.wandName = wandName;
		this.plugin = plugin;
		this.wandType = type;
		this.player = p;
		this.wandItem = parseWandType(type);
		
		this.primary = primary;
		this.secondary = secondary;
	}
	
	public ItemStack generateWand() {
		ItemStack wand = this.wandItem;
		ItemMeta wandMeta = wand.getItemMeta();
		
		wandMeta.setDisplayName(this.wandName);
		
		ArrayList<String> wandLore = new ArrayList<String>();
		wandLore.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "Spells");
		wandLore.add("");
		wandLore.add(this.primary.name);
		wandLore.add(this.secondary.name);
		wandLore.add("");
		
		wandMeta.setLore(wandLore);
		wandMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		wandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ATTRIBUTES);
		
		if (this.wandItem.getType().equals(Material.TRIDENT)) {
			wandMeta.addEnchant(Enchantment.RIPTIDE, 1, true);
		}
		
		wand.setItemMeta(wandMeta);
		
		return wand;
	}
	
	public void executePrimary() {
		Player p = this.player;
		String displayName = this.primary.name;
		if (displayName.contains("Super Explosion")) {
			Particle explosionParticle = Particle.LAVA;
			
			p.getWorld().spawnParticle(explosionParticle, p.getLocation().add(p.getLocation().getDirection().multiply(2)), 50, 0, 0, 0, 1.0, null, true);
			p.getWorld().spawnParticle(explosionParticle, p.getLocation().add(p.getLocation().getDirection().multiply(4)), 50, 0, 0, 0, 1.0, null, true);
			p.getWorld().spawnParticle(explosionParticle, p.getLocation().add(p.getLocation().getDirection().multiply(6)), 50, 0, 0, 0, 1.0, null, true);
			p.getWorld().spawnParticle(explosionParticle, p.getLocation().add(p.getLocation().getDirection().multiply(8)), 50, 0, 0, 0, 1.0, null, true);
			
			p.getWorld().createExplosion(p.getLocation().add(p.getLocation().getDirection().multiply(10)), 6F, false, false, p);
			
		} else if (displayName.contains("Heal")) {
			if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == p.getHealth()) {
				p.sendMessage(ChatColor.RED + "You are at Max HP!");
			} else {
				Particle healParticle = Particle.HEART;
				
				p.getWorld().spawnParticle(healParticle, p.getEyeLocation(), 15, 0.5, 0.5, 0.5, 1.0, null, true);
				
				double hp = p.getHealth();
				
				p.setHealth(hp + 1);
			}
		} else if (displayName.contains("Smiter")) {
			ArrayList<UUID> smiterCooldown = WandCooldowns.getSmiterCooldown();
			
			if (smiterCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Smiter ability is on a cooldown!");
			} else {
				p.getNearbyEntities(20, 20, 20).forEach(en -> {
					if (en.equals(p)) return;
					if (en.isCustomNameVisible()) return;
					if (!(en instanceof Damageable)) return;
					if (!(en instanceof LivingEntity)) return;
					en.getWorld().strikeLightning(en.getLocation());
				});
				
				if (!(p.isOp())) {
					smiterCooldown.add(p.getUniqueId());
					
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Your Smiter ability has been refreshed!");
							smiterCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 20 * 60);
				}
			}
		} else if (displayName.contains("End Killer")) {
			ArrayList<UUID> endKillerCooldown = WandCooldowns.getEndKillerCooldown();
			if (p.getWorld().getName().contains("world_the_end")) {
				
				if (endKillerCooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "Your End Killer ability is on a cooldown!");
				} else {
					p.getNearbyEntities(10, 10, 10).forEach(en -> {
						if (en.isCustomNameVisible()) return;
						if (!(en.getType().equals(EntityType.ENDERMAN)) && !(en.getType().equals(EntityType.ENDERMITE)) && !(en.getType().equals(EntityType.SHULKER))) return;
						if (!(en instanceof LivingEntity)) return;
						
						LivingEntity len = (LivingEntity) en;
						
						len.setHealth(0);
						p.incrementStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN, 3);
					});
					if (!(p.isOp())) {
						endKillerCooldown.add(p.getUniqueId());
						new BukkitRunnable() {
							public void run() {
								endKillerCooldown.remove(p.getUniqueId());
								p.sendMessage(ChatColor.GREEN + "Your End Killer ability has been refreshed!");
							}
						}.runTaskLater(plugin, 20 * 60);
					}
				}
			} else p.sendMessage(ChatColor.RED + "You need to be in the end to activate this spell!");
		} else if (displayName.contains("Hasty Changer")) {
			ArrayList<UUID> hastyChangerCooldown = WandCooldowns.getHastyBuilderCooldown();
			
			if (hastyChangerCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Hasty Changer ability is on a cooldown!");
			} else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 3, true, false, true));
				
				hastyChangerCooldown.add(p.getUniqueId());
				
				new BukkitRunnable() {
					public void run() {
						hastyChangerCooldown.remove(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Your Hasty Changer ability has been refreshed!");
					}
				}.runTaskLater(plugin, 1800);
			}
		} else if (displayName.contains("Inferno Fireball")) {
			Location oneF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 45, p.getLocation().getPitch());
			Location twoF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 90, p.getLocation().getPitch());
			Location threeF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 135, p.getLocation().getPitch());
			Location fourF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 180, p.getLocation().getPitch());
			Location fiveF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 225, p.getLocation().getPitch());
			Location sixF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 270, p.getLocation().getPitch());
			Location sevenF = new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY(), p.getEyeLocation().getZ(), p.getEyeLocation().getYaw() + 315, p.getLocation().getPitch());
			Location eightF = p.getEyeLocation();
			
			p.getWorld().spawnEntity(oneF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(twoF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(threeF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(fourF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(fiveF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(sixF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(sevenF, EntityType.SMALL_FIREBALL);
			p.getWorld().spawnEntity(eightF, EntityType.SMALL_FIREBALL);
			
			p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 3F, 0.5F);
			
		} else if (displayName.contains("Wither Vampire")) {
			ArrayList<UUID> vampireCooldown = WandCooldowns.getWitherVampireCooldown();
			
			if (vampireCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Wither Vampire Spell is currently on a cooldown!");
			} else {
				p.getNearbyEntities(10, 10, 10).forEach(en -> {
					if (en.isCustomNameVisible()) return;
					if (en.getType().equals(EntityType.WITHER)) return;
					if (en.getType().equals(EntityType.RAVAGER)) return;
					if (en.getType().equals(EntityType.ENDER_DRAGON)) return;
					if (!(en instanceof LivingEntity)) return;
					if (!(en instanceof Damageable)) return;
					
					LivingEntity len = (LivingEntity) en;
					
					double halfHealth = len.getHealth() / 2;
					len.setHealth(halfHealth);
					p.setAbsorptionAmount(p.getAbsorptionAmount() + halfHealth);
				});
				
				Particle wither = Particle.DAMAGE_INDICATOR;
				
				p.getWorld().spawnParticle(wither, p.getEyeLocation(), 6000, 10, 10, 10, 1.0, null, true);
				
				vampireCooldown.add(p.getUniqueId());
				new BukkitRunnable() {
					public void run() {
						vampireCooldown.remove(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Your Wither Vampire Spell has been refreshed!");
					}
				}.runTaskLater(plugin, 20 * 90);
			}
		} else if (displayName.contains("Damage Aura")) {
			p.getNearbyEntities(10, 10, 10).forEach(en -> {
				if (en.isCustomNameVisible()) return;
				if (en.getType().equals(EntityType.WITHER)) return;
				if (en.getType().equals(EntityType.RAVAGER)) return;
				if (en.getType().equals(EntityType.ENDER_DRAGON)) return;
				if (!(en instanceof LivingEntity)) return;
				if (!(en instanceof Damageable)) return;
				
				LivingEntity len = (LivingEntity) en;
				
				double previousHp = len.getHealth();
				len.setHealth(previousHp - 5);
				
				
			});
			Particle damage = Particle.DAMAGE_INDICATOR;
			p.getWorld().spawnParticle(damage, p.getEyeLocation(), 3000, 5, 5, 5, 1.0, null, true);
		}
	}
	
	public void executeSecondary() {
		Player p = this.player;
		String displayName = this.secondary.name;
		if (displayName.contains("Explosive Fireball")) {
			
			p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREBALL);
		} else if (displayName.contains("Regen")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 1, true, false, true));
		} else if (displayName.contains("Lightning Forcefield")) {
			ArrayList<UUID> forcefieldCooldown = WandCooldowns.getForcefieldCooldown();
			
			if (forcefieldCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Forcefield ability is on a cooldown!");
			} else {
				Location lightning1 = p.getLocation().add(4, 0, -1);
				Location lightning2 = p.getLocation().add(4, 0, 0);
				Location lightning3 = p.getLocation().add(4, 0, 1);
				
				Location lightning4 = p.getLocation().add(-4, 0, -1);
				Location lightning5 = p.getLocation().add(-4, 0, 0);
				Location lightning6 = p.getLocation().add(-4, 0, 1);
				
				Location lightning7 = p.getLocation().add(-1, 0, 4);
				Location lightning8 = p.getLocation().add(0, 0, 4);
				Location lightning9 = p.getLocation().add(1, 0, 4);
				
				Location lightning10 = p.getLocation().add(-1, 0, -4);
				Location lightning11 = p.getLocation().add(0, 0, -4);
				Location lightning12 = p.getLocation().add(1, 0, -4);
				
				Location lightning13 = p.getLocation().add(3, 0, -2);
				Location lightning14 = p.getLocation().add(3, 0, 2);
				Location lightning15 = p.getLocation().add(2, 0, 3);
				Location lightning16 = p.getLocation().add(2, 0, -3);
				Location lightning17 = p.getLocation().add(-3, 0, -2);
				Location lightning18 = p.getLocation().add(-3, 0, 2);
				Location lightning19 = p.getLocation().add(-2, 0, 3);
				Location lightning20 = p.getLocation().add(-2, 0, -3);
				
				World lw = p.getWorld();
				
				lw.strikeLightning(lightning1);
				lw.strikeLightning(lightning2);
				lw.strikeLightning(lightning3);
				lw.strikeLightning(lightning4);
				lw.strikeLightning(lightning5);
				lw.strikeLightning(lightning6);
				lw.strikeLightning(lightning7);
				lw.strikeLightning(lightning8);
				lw.strikeLightning(lightning9);
				lw.strikeLightning(lightning10);
				lw.strikeLightning(lightning11);
				lw.strikeLightning(lightning12);
				lw.strikeLightning(lightning13);
				lw.strikeLightning(lightning14);
				lw.strikeLightning(lightning15);
				lw.strikeLightning(lightning16);
				lw.strikeLightning(lightning17);
				lw.strikeLightning(lightning18);
				lw.strikeLightning(lightning19);
				lw.strikeLightning(lightning20);
				
				if (!(p.isOp())) {
					forcefieldCooldown.add(p.getUniqueId());
					
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Your Forcefield Cooldown has been reset!");
							forcefieldCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 20 * 30);
				}
			}
		} else if (displayName.contains("Teleporter")) {
			if (p.getWorld().getBlockAt(p.getLocation().add(p.getEyeLocation().getDirection().multiply(15))).isPassable()) {
				p.teleport(p.getLocation().add(p.getEyeLocation().getDirection().multiply(15)), TeleportCause.ENDER_PEARL);
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3F, 1.5F);
			} else {
				p.sendMessage(ChatColor.RED + "There are blocks in the way!");
			}
		} else if (displayName.contains("Immutatio's Flight")) {
			ArrayList<UUID> immutatioCooldown = WandCooldowns.getImmutoCooldown();
			if (immutatioCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Flight is currently on a cooldown!");
			} else {
				p.sendMessage(ChatColor.AQUA + "You are now allowed to fly! This will be cancelled in 3 minutes.");
				
				p.setAllowFlight(true);
				
				String warningTwoM = ChatColor.RED + "You have two minutes remaining!";
				String warningOneM = ChatColor.RED + "You have one minute remaining!";
				String warningThirty = ChatColor.RED + "You have 30 seconds remaining!";
				String warningTen = ChatColor.RED + "You have 10 seconds remaining!";
				String warningThree = ChatColor.RED + "You have 3 seconds remaining!";
				String warningTwo = ChatColor.RED + "You have 2 seconds remaining!";
				String warningOne = ChatColor.RED + "You have 1 seconds remaining!";
				String disableFlight = ChatColor.RED + "Your flight has been removed!";
				
				immutatioCooldown.add(p.getUniqueId());
				
				new BukkitRunnable() {
					public void run() {
						p.sendMessage(warningTwoM);
						new BukkitRunnable() {
							public void run() {
								p.sendMessage(warningOneM);
								new BukkitRunnable() {
									public void run() {
										p.sendMessage(warningThirty);
										new BukkitRunnable() {
											public void run() {
												p.sendMessage(warningTen);
												new BukkitRunnable() {
													public void run() {
														p.sendMessage(warningThree);
														new BukkitRunnable() {
															public void run() {
																p.sendMessage(warningTwo);
																new BukkitRunnable() {
																	public void run() {
																		p.sendMessage(warningOne);
																		new BukkitRunnable() {
																			public void run() {
																				p.sendMessage(disableFlight);
																				p.setAllowFlight(false);
																				if (p.isFlying()) {
																					p.setFlying(false);
																				}
																				new BukkitRunnable() {
																					public void run() {
																						p.sendMessage(ChatColor.GREEN + "Your Immutatio's Flight cooldown has been refreshed!");
																						immutatioCooldown.remove(p.getUniqueId());
																					}
																				}.runTaskLater(plugin, 20 * 60 * 12);
																			}
																		}.runTaskLater(plugin, 20);
																	}
																}.runTaskLater(plugin, 20);
															}
														}.runTaskLater(plugin, 20);
													}
												}.runTaskLater(plugin, 20 * 7);
											}
										}.runTaskLater(plugin, 20 * 20);
									}
								}.runTaskLater(plugin, 20 * 30);
							}
						}.runTaskLater(plugin, 20 * 60);
					}
				}.runTaskLater(plugin, 20 * 60);
			}
		} else if (displayName.contains("Fire Aura")) {
			Particle flame = Particle.FLAME;
			
			ArrayList<UUID> auraCooldown = WandCooldowns.getAuraCooldown();
			if (auraCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Fire Aura spell is currently on a cooldown!");
			} else {
				p.getNearbyEntities(15, 15, 15).forEach(en -> {
					if (en.getType().equals(EntityType.DROPPED_ITEM)) return;
					if (en.getType().equals(EntityType.ITEM_FRAME)) return;
					if (en.getType().equals(EntityType.PAINTING)) return;
					if (en.getType().equals(EntityType.LEASH_HITCH)) return;
					
					en.setFireTicks(200);
				});
				
				p.getWorld().spawnParticle(flame, p.getLocation(), 5000, 15, 15, 15, 1.0, null, true);
				
				auraCooldown.add(p.getUniqueId());
				new BukkitRunnable() {
					public void run() {
						auraCooldown.remove(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Your Fire Aura Spell has been refreshed!");
					}
				}.runTaskLater(plugin, 100);
			}
		} else if (displayName.contains("Wither Grower")) {
			Particle emerald = Particle.VILLAGER_HAPPY;
			
			p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0)).applyBoneMeal(BlockFace.UP);
			p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0)).applyBoneMeal(BlockFace.DOWN);
			p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0)).applyBoneMeal(BlockFace.SELF);
			p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0)).applyBoneMeal(BlockFace.NORTH);
			p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0)).applyBoneMeal(BlockFace.SOUTH);
			
			p.getWorld().spawnParticle(emerald, p.getEyeLocation(), 100, 1, 1, 1, 1.0, null, true);
		} else if (displayName.contains("Super Strength")) {
			ArrayList<UUID> strengthCooldown = WandCooldowns.getSuperStrengthCooldown();
			if (strengthCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Super Strength Spell is currently on a cooldown!");
			} else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 14, true, false, true));
				
				strengthCooldown.add(p.getUniqueId());
				
				new BukkitRunnable() {
					public void run() {
						strengthCooldown.remove(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Your Super Stregnth Spell has been refreshed!");
					}
				}.runTaskLater(plugin, 20 * 70);
			}
		}
	}

}
