package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zoglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.AdvancementMessages;
import gamercoder215.smpcore.utils.GeneralUtils;
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
