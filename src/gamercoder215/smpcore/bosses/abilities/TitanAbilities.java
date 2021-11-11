package gamercoder215.smpcore.bosses.abilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.AdvancementMessages;
import gamercoder215.smpcore.utils.fetcher.ItemFetcher;

public class TitanAbilities implements Listener {
	
	private Main plugin;
	
	public TitanAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void announceDialogue(String msg) {
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "Mysterious Titan" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + msg);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.VOID) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.WITHER)) && !(e.getEntityType().equals(EntityType.MAGMA_CUBE)) && !(e.getEntityType().equals(EntityType.ENDER_DRAGON))) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		
		if (!(e.getCause().equals(DamageCause.ENTITY_ATTACK)) && !(e.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) && !(e.getCause().equals(DamageCause.PROJECTILE))) e.setCancelled(true);
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		
		Player p = (Player) e.getEntity().getShooter();
		
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		
		ItemMeta handMeta = mainhand.getItemMeta();
		if (!(handMeta.hasDisplayName())) return;
		
		if ((handMeta.getDisplayName().contains("Deus Bow") || handMeta.getDisplayName().contains("Dues Bow")) && handMeta.isUnbreakable() && !handMeta.hasLore() && handMeta.hasAttributeModifiers() && handMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && handMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS)) {
			e.getEntity().setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Titan Arrow");
			e.getEntity().setCustomNameVisible(true);
		} else if (handMeta.getDisplayName().contains("Poeas's Bow") && handMeta.isUnbreakable() && handMeta.hasLore() && handMeta.getEnchantLevel(Enchantment.PROTECTION_FALL) == 2 && handMeta.hasAttributeModifiers()) {
			e.getEntity().setCustomName(ChatColor.GRAY + "" + ChatColor.BOLD + "Archer King's Arrow");
			e.getEntity().setCustomNameVisible(true);
		}
	}
	
	@EventHandler
	public void onProjectileHit(EntityDamageByEntityEvent e) {
		if (e.getCause() == DamageCause.VOID) return;
		if (e.getEntity() == null) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntity().getType().equals(EntityType.WITHER)) && !(e.getEntityType().equals(EntityType.MAGMA_CUBE)) && !(e.getEntityType().equals(EntityType.ENDER_DRAGON)) && !(e.getEntityType().equals(EntityType.ZOMBIE)) && !(e.getEntityType().equals(EntityType.SKELETON))) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		
		if (!(e.getDamager().getType().equals(EntityType.ARROW))) return;
		
		if (e.getDamager().isCustomNameVisible() && e.getDamager().getCustomName().contains("Titan Arrow")) {
			e.setDamage(5);
		} else if (e.getDamager().isCustomNameVisible() && e.getDamager().getCustomName().contains("Archer King's Arrow")) {
			e.setDamage(15);
		} else e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onDamageDefensive(EntityDamageByEntityEvent e) {
		if (e.getCause() == DamageCause.VOID) return;
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.WITHER)) && !(e.getEntityType().equals(EntityType.MAGMA_CUBE)) && !(e.getEntityType().equals(EntityType.ENDER_DRAGON)) && !(e.getEntityType().equals(EntityType.ZOMBIE)) && !(e.getEntityType().equals(EntityType.SKELETON))) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		Random r = new Random();
		
		if (e.getDamager().getType() != EntityType.PLAYER) return;
		
		Player p = (Player) e.getDamager();
		
		if (p.getInventory().getItemInMainHand() == null) e.setCancelled(true);
		ItemStack weapon = p.getInventory().getItemInMainHand();
		if (!(weapon.hasItemMeta())) e.setCancelled(true);
		if (!(weapon.getItemMeta().isUnbreakable())) e.setCancelled(true);
		if (!(weapon.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS))) e.setCancelled(true);
		if (!(weapon.getItemMeta().hasDisplayName())) e.setCancelled(true);
		if (!(weapon.getItemMeta().hasEnchants())) e.setCancelled(true);
		String displayName = weapon.getItemMeta().getDisplayName();
		
		if (e.getEntity().getCustomName().contains("Damage Titan")) {
			
			if (displayName.contains("Arescent")) e.setDamage(3);
			else if (displayName.contains("Aribus")) e.setDamage(6);
			else if (displayName.contains("Perussi")) e.setDamage(5.5);
			else if (displayName.contains("Confugiat")) e.setDamage(4.5);
			else if (displayName.contains("Ζητήστε τη λεπίδα του Τιτάνα")) e.setDamage(7.5);
			else if (displayName.contains("Base Titan Sword")) e.setDamage(5);
			else e.setCancelled(true);
			
			if (r.nextInt(100) < 25) {
				announceDialogue("I am the Damage Titan! Fear me!");
			}
			
			if (r.nextInt(100) < 10) {
				announceDialogue("My boi Health Titan needs to give us more Titan HP...");
			}
		} else if (e.getEntity().getCustomName().contains("Speed Titan")) {

			if (displayName.contains("Celer")) e.setDamage(9);
			else if (displayName.contains("Aribus")) e.setDamage(20);
			else if (displayName.contains("Speedy Sword")) e.setDamage(35);
			else if (displayName.contains("Confugiat")) e.setDamage(20);
			else if (displayName.contains("Base Titan Sword")) e.setDamage(15);
			else e.setCancelled(true);
			
			if (r.nextBoolean() == true) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60 * 20, 14, true, true, true));
			}
			
			if (r.nextInt(100) < 5) {
				announceDialogue("Going around at the speed of sound...");
			}
			
			if (r.nextInt(100) < 3) {
				announceDialogue("WARP SPEEEEEEEEEEEED");
			}
		} else if (e.getEntity().getCustomName().contains("Ender Titan")) {
			
			if (displayName.contains("Celer")) e.setDamage(3);
			else if (displayName.contains("Aribus")) e.setDamage(6);
			else if (displayName.contains("Confugiat")) e.setDamage(4.5);
			else if (displayName.contains("Base Titan Sword")) e.setDamage(5);
			else e.setCancelled(true);
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2, true, false, true));
			
			if (r.nextInt(100) < 5) {
				announceDialogue("The End is my home, and I will destroy you in it.");
			}
			
			if (r.nextInt(100) ==  1) {
				announceDialogue("Flying above all of my problems with mere humans is very satisfying.");
			}
			
			
		} else if (e.getEntity().getCustomName().contains("Nether Titan")) {
			if (displayName.contains("Arescent") && !displayName.contains("Inferno")) e.setDamage(3);
			else if (displayName.contains("Aribus")) e.setDamage(6);
			else if (displayName.contains("Perussi")) e.setDamage(5.5);
			else if (displayName.contains("Confugiat")) e.setDamage(4.5);
			else if (displayName.contains("Ζητήστε τη λεπίδα του Τιτάνα")) e.setDamage(7.5);
			else if (displayName.contains("Base Titan Sword")) e.setDamage(5);
			else if (displayName.contains("Inferno Arescent")) e.setDamage(10);
			else e.setCancelled(true);
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 45 * 20, 9, true, false, true));
			
			if (r.nextInt(100) < 25) {
				announceDialogue("The Nether is my home, and I will destroy you in it.");
			}
			
			if (r.nextBoolean() == true) {
				if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
					p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				}
				
				if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				}
			}
		} else if (e.getEntity().getCustomName().contains("Golden Titan")) {
			if (displayName.contains("Chrysos Blade") && weapon.getItemMeta().getLore().get(0).contains("The only blade with enough pure")) e.setDamage(300);
			else e.setCancelled(true);
			
			
			if (p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			}
			
			if (e.isCancelled()) return;
			
			if (r.nextBoolean() == true) {
				announceDialogue("I am the strongest of the titans. Bow down or die.");
			}
			
			if (r.nextBoolean() == true && r.nextBoolean() == true) {
				int titanPick = r.nextInt(2);
				

				if (titanPick == 0) {
					announceDialogue("Nether Titan, I will give you an aura that will simulate you in the nether.");
					new BukkitRunnable() {
						public void run() {
			        		 TitanAbilities.announceDialogue("The " + ChatColor.RED + "Nether Titan" + ChatColor.GOLD + " has been summoned by the Golden Titan!");
		        			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon magma_cube ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:200f,Size:5,wasOnGround:1b,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Nether Titan\",\"color\":\"red\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{display:{Name:'[{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Inferno Arescent \",\"color\":\"red\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:250s},{id:\"minecraft:smite\",lvl:200s},{id:\"minecraft:bane_of_arthropods\",lvl:175s},{id:\"minecraft:fire_aspect\",lvl:25s},{id:\"minecraft:looting\",lvl:75s},{id:\"minecraft:sweeping\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.8,Operation:2,UUID:[I;508656979,-1395504158,-1778247833,-1280057405],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:11,Operation:2,UUID:[I;754816496,-144684281,-1084403386,-2053114714],Slot:\"mainhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:0,UUID:[I;-1025514587,-1746910995,-1599264703,282817427],Slot:\"mainhand\"}]}},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{Unbreakable:1b}},{},{},{id:\"minecraft:apple\",Count:1b,tag:{display:{Name:'{\"text\":\"Inferno Apple\",\"color\":\"red\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorDropChances:[0.000F,0.085F,0.085F,1.000F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:14b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:5b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:80},{Name:generic.attack_knockback,Base:10}]}");
						}
					}.runTaskLater(plugin, 40);
				} else if (titanPick == 1) {
					announceDialogue("Speed Titan, go supersonic.");
	        		 TitanAbilities.announceDialogue("The " + ChatColor.AQUA + "Speed Titan" + ChatColor.GOLD + " has been summoned by the Golden Titan");
	        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,NoAI:0b,CanPickUpLoot:0b,Health:100f,IsBaby:1b,CanBreakDoors:1b,CustomName:'{\"text\":\"Speed Titan\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Speedy Sword\",\"color\":\"dark_blue\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:25s},{id:\"minecraft:smite\",lvl:15s},{id:\"minecraft:bane_of_arthropods\",lvl:15s},{id:\"minecraft:knockback\",lvl:30s},{id:\"minecraft:fire_aspect\",lvl:2s},{id:\"minecraft:looting\",lvl:15s},{id:\"minecraft:sweeping\",lvl:255s}],AttributeModifiers:[{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:15,Operation:0,UUID:[I;-2034508983,666519266,-1184414323,-2059296110],Slot:\"mainhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;1318279769,-1970322398,-1432561688,247014184],Slot:\"mainhand\"}]}},{id:\"minecraft:totem_of_undying\",Count:2b}],HandDropChances:[0.250F,0.000F],ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Speedy Soul Boots\",\"color\":\"#301B02\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:feather_falling\",lvl:255s},{id:\"minecraft:thorns\",lvl:5s},{id:\"minecraft:depth_strider\",lvl:3s},{id:\"minecraft:frost_walker\",lvl:8s},{id:\"minecraft:soul_speed\",lvl:30s}]}},{},{},{id:\"minecraft:leather_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Speed Crown\",\"color\":\"blue\",\"bold\":true,\"italic\":false}',color:63487},Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:respiration\",lvl:10s},{id:\"minecraft:aqua_affinity\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:5,Operation:0,UUID:[I;-1531656764,-1855894734,-1956664037,1619467644],Slot:\"head\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;359970970,692931930,-2121941000,78001233],Slot:\"head\"}]}}],ArmorDropChances:[1.000F,0.085F,0.085F,0.085F],ActiveEffects:[{Id:1b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:100},{Name:generic.follow_range,Base:400},{Name:generic.knockback_resistance,Base:0},{Name:generic.movement_speed,Base:0.42},{Name:generic.attack_damage,Base:15},{Name:generic.attack_knockback,Base:5},{Name:zombie.spawn_reinforcements,Base:1}]}");
				}
			}
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getCause() == DamageCause.VOID) return;
		if (e.getDamager().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		if (!(e.getDamager().getCustomName().contains("Titan"))) return;
		if (!(e.getDamager().isCustomNameVisible())) return;
		if (!(e.getEntityType().equals(EntityType.WITHER))  && !(e.getEntityType().equals(EntityType.MAGMA_CUBE)) && !(e.getEntityType().equals(EntityType.ENDER_DRAGON)) && !(e.getEntityType().equals(EntityType.ZOMBIE)) && !(e.getEntityType().equals(EntityType.SKELETON)) && !(e.getEntityType().equals(EntityType.ARROW))) return;

		Random r = new Random();
		
		Player p = (Player) e.getEntity();
		
		if (r.nextBoolean() == true) {
			p.sendMessage(ChatColor.DARK_RED + "The Titan may have removed some of your positive effects!");
			if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			}
			
			if (p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			}
			
			if (p.hasPotionEffect(PotionEffectType.SPEED)) {
				p.removePotionEffect(PotionEffectType.SPEED);
			}
			
			if (p.hasPotionEffect(PotionEffectType.LUCK)) {
				p.removePotionEffect(PotionEffectType.LUCK);
			}
			
			if (p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
				p.removePotionEffect(PotionEffectType.ABSORPTION);
			}
			
			if (p.hasPotionEffect(PotionEffectType.REGENERATION)) {
				p.removePotionEffect(PotionEffectType.REGENERATION);
			}
			
			if (p.hasPotionEffect(PotionEffectType.SLOW_FALLING)) {
				p.removePotionEffect(PotionEffectType.SLOW_FALLING);
			}
			
			if (p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
				p.removePotionEffect(PotionEffectType.WATER_BREATHING);
			}
		}
		
		if (e.getDamager().getCustomName().contains("Damage Titan")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 24, true, false, true));
			if (r.nextBoolean() == true) {
				p.getWorld().strikeLightning(p.getLocation());
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!(e.getEntityType().equals(EntityType.WITHER))  && !(e.getEntityType().equals(EntityType.MAGMA_CUBE)) && !(e.getEntityType().equals(EntityType.ENDER_DRAGON))  && !(e.getEntityType().equals(EntityType.ZOMBIE)) && !(e.getEntityType().equals(EntityType.SKELETON))) return;
		if (!(e.getEntity().getCustomName().contains("Titan"))) return;
		if (!(e.getEntity().isCustomNameVisible())) return;
		
		// Random r = new Random();
		
		Location deathLoc = e.getEntity().getLocation();
		
		if (e.getEntity().getCustomName().contains("Nether Titan")) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, ItemFetcher.getEnchantedNetherEssence());
		} else if (e.getEntity().getCustomName().contains("Speed Titan")) {
			deathLoc.getWorld().dropItemNaturally(deathLoc, ItemFetcher.getSpeedArtifact());
		} else if (e.getEntity().getCustomName().contains("Golden Titan")) {
			announceDialogue("You beat me... I was told this was going to happen but didn't believe it.");
			new BukkitRunnable() {
				public void run() {
					announceDialogue("I guess gold isn't enough. I'll have to talk to an old friend... the " + ChatColor.AQUA + "" + ChatColor.BOLD + "Diamond Wizard" + ChatColor.RESET + "" + ChatColor.GRAY + ".");
				}
			}.runTaskLater(plugin, 40);
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_BLOCK, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_BLOCK, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_BLOCK, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_BLOCK, 64));
			
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.RAW_GOLD_BLOCK, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.RAW_GOLD_BLOCK, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.RAW_GOLD_BLOCK, 32));
			
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
			deathLoc.getWorld().dropItemNaturally(deathLoc, new ItemStack(Material.GOLD_INGOT, 64));
		}
		
		if (e.getEntity().getKiller() == null) return;
		Player p = e.getEntity().getKiller();
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
