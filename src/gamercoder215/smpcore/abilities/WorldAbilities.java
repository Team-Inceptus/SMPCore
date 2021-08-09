package gamercoder215.smpcore.abilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.listeners.GUIManagers;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

public class WorldAbilities implements Listener {
	
	private Main plugin;
	
	public WorldAbilities(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	ArrayList<UUID> cooldown = new ArrayList<UUID>();
	ArrayList<UUID> aribusCooldown = new ArrayList<UUID>();
	ArrayList<UUID> vivetCooldown = new ArrayList<UUID>();
	ArrayList<UUID> celerCooldown = new ArrayList<UUID>();
	ArrayList<UUID> fireballCooldown = new ArrayList<UUID>();
	ArrayList<UUID> hurricaneCooldown = new ArrayList<UUID>();
	ArrayList<UUID> shockwaveCooldown = new ArrayList<UUID>();
	ArrayList<UUID> blackHoleCooldown = new ArrayList<UUID>();
	
	Random r = new Random();
	
	@EventHandler
	public void onLaunch(ProjectileLaunchEvent e) {
		Entity en = e.getEntity();
		
		if (en.getCustomName() == null) return;
		if (!(en.getType().equals(EntityType.TRIDENT)) && !(en.getType().equals(EntityType.FIREWORK))) return;
		
		
		if (en.getCustomName().contains("Wand") && !(en.getCustomName().contains(ChatColor.ITALIC + ""))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		ItemStack item = e.getItem();
		if (!(item.hasItemMeta())) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		Player p = e.getPlayer();
		Action clickedAction = e.getAction();
		
		if (itemMeta.getDisplayName().contains("Arescent") && itemMeta.isUnbreakable() && !itemMeta.hasLore()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.LEFT_CLICK_AIR || clickedAction == Action.LEFT_CLICK_BLOCK) {
				Location fireballLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
				p.getWorld().spawnEntity(fireballLoc, EntityType.SMALL_FIREBALL);
			} else {
				if (cooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.DARK_RED + "Your Strength Ability is currently on a cooldown!");
				} else {
					p.sendMessage(ChatColor.RED + "You used your Strength Ability!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 14, true, false, true));
					cooldown.add(p.getUniqueId());
					new BukkitRunnable() {
	
						@Override
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Your Strength Ability Cooldown has been refreshed!");
							cooldown.remove(p.getUniqueId());
						}
						
					}.runTaskLater(plugin, 200);
				}
			}
		} else if (itemMeta.getDisplayName().contains("Aribus") && itemMeta.isUnbreakable() && item.getType().equals(Material.NETHERITE_AXE) && itemMeta.hasLore()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.RIGHT_CLICK_BLOCK) {
				if (aribusCooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.DARK_GREEN + "Your Lightning Ability is currently on a cooldown!");
				} else {
					if (e.getClickedBlock() == null) return;
					p.getWorld().strikeLightning(e.getClickedBlock().getLocation());
					aribusCooldown.add(p.getUniqueId());
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.AQUA + "Your Lightning Ability cooldown has been refreshed!");
							aribusCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 40);
				}
			}
		} else if (itemMeta.getDisplayName().contains("Vivet") && itemMeta.isUnbreakable() && !itemMeta.hasLore()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (vivetCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.DARK_AQUA + "Your Health Ability is currently on a cooldown!");
			} else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15 * 20, 9, true, false, true));
				vivetCooldown.add(p.getUniqueId());
				new BukkitRunnable() {
					public void run() {
						p.sendMessage(ChatColor.LIGHT_PURPLE + "Your Health Ability cooldown has been refreshed!");
						vivetCooldown.remove(p.getUniqueId());
					}
				}.runTaskLater(plugin, 600);
			}
		} else if (itemMeta.getDisplayName().contains("Celer") && itemMeta.isUnbreakable() && !itemMeta.hasLore() && !itemMeta.hasLore()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
				if (clickedAction == Action.RIGHT_CLICK_AIR || clickedAction == Action.RIGHT_CLICK_BLOCK) {
					if (celerCooldown.contains(p.getUniqueId())) {
						p.sendMessage(ChatColor.DARK_AQUA + "Your Speed Ability is currently on a cooldown!");
					} else {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15 * 20, 14, true, false, true));
						celerCooldown.add(p.getUniqueId());
					}
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.LIGHT_PURPLE + "Your Speed Ability cooldown has been refreshed!");
							celerCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 600);
				} else {
					if (fireballCooldown.contains(p.getUniqueId())) {
						p.sendMessage(ChatColor.DARK_RED + "Your Fireball Ability is on a cooldown!");
					} else {
						Location enderFireballLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
						p.getWorld().spawnEntity(enderFireballLoc, EntityType.DRAGON_FIREBALL);
						fireballCooldown.add(p.getUniqueId());
						new BukkitRunnable() {
							public void run() {
								p.sendMessage(ChatColor.DARK_PURPLE + "You can fire a fireball again!");
								fireballCooldown.remove(p.getUniqueId());
							}
						}.runTaskLater(plugin, 20 * 5);
					}
				}
		} else if (itemMeta.getDisplayName().contains("Praedo") && itemMeta.isUnbreakable() && !itemMeta.hasLore() && !itemMeta.hasAttributeModifiers()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.LEFT_CLICK_AIR) {
				if (hurricaneCooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.GRAY + "Your Arrow Hurricane Ability is on a cooldown!");
				} else {
					Location arrowLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
					
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
					
					p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 3F, 1.5F);
					hurricaneCooldown.add(p.getUniqueId());
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GRAY + "Your Arrow Hurricane Abilitiy has been refreshed!");
							hurricaneCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 20 * 3);
				}
			}
		} else if (itemMeta.getDisplayName().contains("Ζητήστε τη λεπίδα του Τιτάνα") && !itemMeta.hasLore() && itemMeta.hasAttributeModifiers()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.RIGHT_CLICK_AIR) {
				p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKULL);
				p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKULL);
				p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKULL);
			} else if (clickedAction == Action.RIGHT_CLICK_BLOCK) {
				if (!(e.getClickedBlock().getType().equals(Material.BEDROCK)) && !(e.getClickedBlock().getType().equals(Material.END_PORTAL_FRAME)) && !!(e.getClickedBlock().getType().equals(Material.END_PORTAL)) && !(e.getClickedBlock().getType().equals(Material.NETHER_PORTAL)) && !(e.getClickedBlock().getType().equals(Material.COMMAND_BLOCK)) && !(e.getClickedBlock().getType().equals(Material.CHAIN_COMMAND_BLOCK)) && !(e.getClickedBlock().getType().equals(Material.REPEATING_COMMAND_BLOCK))) {
					e.getClickedBlock().breakNaturally();
				}
			}
		} else if (itemMeta.getDisplayName().contains("Confugiat") && itemMeta.hasLore() && itemMeta.getEnchantLevel(Enchantment.DAMAGE_UNDEAD) == 300 && itemMeta.getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 35 && itemMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 25) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction.equals(Action.RIGHT_CLICK_BLOCK)) {
				if (shockwaveCooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.DARK_GREEN + "Confugiat needs to charge up his shockwave!");
				} else {
					p.sendMessage(ChatColor.DARK_GREEN + "Confugiat has killed all non-boss undead mobs in the area.");
					shockwaveCooldown.add(p.getUniqueId());
					List<Entity> nearbyUndead = p.getNearbyEntities(12.5, 12.5, 12.5);
					
					nearbyUndead.forEach(en -> {
						if ((en.getType().equals(EntityType.ZOMBIE) || en.getType().equals(EntityType.SKELETON) || en.getType().equals(EntityType.ZOGLIN) || en.getType().equals(EntityType.ZOMBIFIED_PIGLIN) || en.getType().equals(EntityType.ZOMBIE_VILLAGER) || en.getType().equals(EntityType.WITHER_SKELETON) || en.getType().equals(EntityType.HUSK) || en.getType().equals(EntityType.STRAY) || en.getType().equals(EntityType.DROWNED)) && !en.isCustomNameVisible()) {
							if (!(en instanceof LivingEntity)) return;
							LivingEntity len = (LivingEntity) en;
							len.setHealth(0);
						}
					});
					new BukkitRunnable() {
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Confugiat's Shockwave has been charged!");
							shockwaveCooldown.remove(p.getUniqueId());
						}
					}.runTaskLater(plugin, 20 * 120);
				}
			} else if (clickedAction.equals(Action.LEFT_CLICK_BLOCK) || clickedAction.equals(Action.RIGHT_CLICK_AIR)) {
				Arrow witherArrow = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 2.5F, 0);
				witherArrow.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 200, 4, true, false, true), true);
				witherArrow.setColor(Color.fromRGB(0, 0, 0));
			}
		} else if (itemMeta.getDisplayName().contains("Machine Gun") && item.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) >= 10) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.LEFT_CLICK_AIR || clickedAction == Action.LEFT_CLICK_BLOCK) {
				Location arrowLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
				
				p.getWorld().spawnArrow(arrowLoc, p.getLocation().getDirection(), 1.8F, 6);
				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 3F, 0.5F);
			}
		} else if (itemMeta.getDisplayName().contains("Ghast Sword") && itemMeta.isUnbreakable()) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.RIGHT_CLICK_AIR || clickedAction == Action.RIGHT_CLICK_BLOCK) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 255, true, false, false));
				p.getWorld().createExplosion(p.getLocation(), 5F, false, false, p);
				p.sendMessage(ChatColor.GREEN + "You created an explosion!");
				p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, p.getLocation(), 1);
			}
		} else if (itemMeta.getDisplayName().contains("InfiniBall") && itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))  {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.RIGHT_CLICK_AIR) {
				Location fireballLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
				p.getWorld().spawnEntity(fireballLoc, EntityType.FIREBALL);
			}
		} else if (itemMeta.getDisplayName().contains("Dimensional Teleporter") && itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && item.getType().equals(Material.END_PORTAL_FRAME)) {
			if (clickedAction == Action.LEFT_CLICK_AIR || clickedAction == Action.RIGHT_CLICK_AIR || clickedAction == Action.RIGHT_CLICK_BLOCK) {
					
				Inventory warpInventory = GUIManagers.generateGUI(27, ChatColor.LIGHT_PURPLE + "Teleport to Dimension");
				
				ItemStack overworld = new ItemStack(Material.GRASS_BLOCK, 1);
				ItemMeta overMeta = overworld.getItemMeta();
				overMeta.setDisplayName(ChatColor.GREEN + "Overworld");
				overworld.setItemMeta(overMeta);
				
				ItemStack nether = new ItemStack(Material.NETHERRACK, 1);
				ItemMeta neMeta = nether.getItemMeta();
				neMeta.setDisplayName(ChatColor.RED + "Nether");
				nether.setItemMeta(neMeta);
				
				ItemStack end = new ItemStack(Material.END_STONE, 1);
				ItemMeta eMeta = end.getItemMeta();
				eMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "End");
				end.setItemMeta(eMeta);
				
				warpInventory.setItem(10, overworld);
				warpInventory.setItem(13, nether);
				warpInventory.setItem(16, end);
				
				p.openInventory(warpInventory);
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3F, 0.5F);
			}
		} else if (itemMeta.getDisplayName().contains("Black Hole") && itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && itemMeta.hasLore() && itemMeta.getLore().get(1).contains("Long ago, a Black Hole appeared") && itemMeta.hasAttributeModifiers() && (item.getType().equals(Material.NETHERITE_HOE) || item.getType().equals(Material.NETHERITE_SWORD))) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (clickedAction == Action.RIGHT_CLICK_AIR || clickedAction == Action.RIGHT_CLICK_BLOCK) {
				
				if (blackHoleCooldown.contains(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "Please wait before shooting another black hole!");
				} else {
					p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3F, 1.5F);
					ArmorStand blackHole = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
					blackHole.setInvulnerable(true);
					blackHole.setGravity(false);
					blackHole.setInvisible(true);
					blackHole.setBasePlate(false);
					
					Location blackHoleLoc = p.getLocation().add(p.getLocation().getDirection().multiply(10));
					
					blackHole.teleport(blackHoleLoc);
					
					blackHole.getNearbyEntities(30, 30, 30).forEach(en -> {
						if (!(en instanceof LivingEntity)) return;
						if (en.equals(p)) return;
						LivingEntity len = (LivingEntity) en;
						len.teleport(blackHole);
						len.setGravity(false);
						len.setAI(false);
						len.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255, true, false, true));
						if (len.getType().equals(EntityType.PLAYER)) {
							Player pl = (Player) len;
							pl.playSound(p.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 3F, 2F);
						}
					});
					
					new BukkitRunnable() {
						public void run() {
							p.getWorld().createExplosion(blackHole.getLocation(), 8F, false, true, p);
							blackHole.getNearbyEntities(30, 30, 30).forEach(en -> {
								if (!(en instanceof LivingEntity)) return;
								if (en.equals(p)) return;
								LivingEntity len = (LivingEntity) en;
								len.setGravity(true);
								len.setAI(true);
							});
							while (!blackHole.isDead()) {
								blackHole.remove();
							}
						}
					}.runTaskLater(plugin, 40);
					
					if (!(p.isOp())) {
						blackHoleCooldown.add(p.getUniqueId());
						new BukkitRunnable() {
							public void run() {
								blackHoleCooldown.remove(p.getUniqueId());
								p.sendMessage(ChatColor.GREEN + "You can fire another black hole!");
							}
						}.runTaskLater(plugin, 200);
					}
				}
			}
		} else if (itemMeta.getDisplayName().contains("Item Protector") && itemMeta.hasLore() && itemMeta.getEnchantLevel(Enchantment.PROTECTION_EXPLOSIONS) == 16) {
			ItemStack oneItem = new ItemStack(item.getType(), 1);
			ItemMeta oneMeta = item.getItemMeta();
			oneItem.setItemMeta(oneMeta);
			
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 3F, 1.5F);
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + p.getName() + " permission set core.special.itemprotector true");
			p.sendMessage(ChatColor.GOLD + "Your items are now (almost) protected!\nYou can throw away this item now; it's useless. If you can...");
		}
	}
	
	@EventHandler
	public void onHitMainhand(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (e.getHitEntity() == null) return;
		if (!(e.getHitEntity() instanceof LivingEntity)) return;
		LivingEntity en = (LivingEntity) e.getHitEntity();
		Player p = (Player) e.getEntity().getShooter();
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack item = p.getInventory().getItemInMainHand();
		if (!(item.hasItemMeta())) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		if (itemMeta.getDisplayName().contains("Ocassus Bow") && itemMeta.getEnchantLevel(Enchantment.RIPTIDE) == 2 && itemMeta.getEnchantLevel(Enchantment.ARROW_INFINITE) == 4) {
			if (!(en.getType().equals(EntityType.ENDERMAN))) e.setCancelled(true);
			else {
				e.setCancelled(true);
				String displayName = itemMeta.getDisplayName();
				
				if (displayName.contains("Tier I")) {
					en.damage(10, p);
				} else if (displayName.contains("Tier II")) {
					en.damage(20, p);
				} else if (displayName.contains("Tier III")) {
					en.damage(30, p);
				} else if (displayName.contains("Tier IV")) {
					en.damage(40, p);
				} else if (displayName.contains("Tier V")) {
					en.damage(50, p);
				} else if (displayName.contains("Tier VI")) {
					en.damage(60, p);
				} else if (displayName.contains("Tier VII")) {
					en.damage(70, p);
				} else if (displayName.contains("Tier VIII")) {
					en.damage(80, p);
				} else if (displayName.contains("Tier IX")) {
					en.damage(90, p);
				} else if (displayName.contains("Tier X")) {
					en.damage(100, p);
				} else en.damage(10, p);
				
				e.getEntity().remove();
			}
		} else if (itemMeta.getDisplayName().contains("Poeas's Bow") && itemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL) == 2 && itemMeta.hasLore() && itemMeta.isUnbreakable() && itemMeta.hasAttributeModifiers()) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9, true, false, true));
			en.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 4, true, false, true));
		}
	}
	
	@EventHandler
	public void onHitOffhand(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (e.getHitEntity() == null) return;
		if (!(e.getHitEntity() instanceof LivingEntity)) return;
		LivingEntity en = (LivingEntity) e.getHitEntity();
		Player p = (Player) e.getEntity().getShooter();
		if (p.getInventory().getItemInOffHand() == null) return;
		ItemStack item = p.getInventory().getItemInOffHand();
		if (!(item.hasItemMeta())) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		if (itemMeta.getDisplayName().contains("Ocassus Bow") && itemMeta.getEnchantLevel(Enchantment.RIPTIDE) == 2 && itemMeta.getEnchantLevel(Enchantment.ARROW_INFINITE) == 4) {
			if (!(en.getType().equals(EntityType.ENDERMAN))) e.setCancelled(true);
			else {
				e.setCancelled(true);
				String displayName = itemMeta.getDisplayName();
				
				if (displayName.contains("Tier I")) {
					en.damage(10, p);
				} else if (displayName.contains("Tier II")) {
					en.damage(20, p);
				} else if (displayName.contains("Tier III")) {
					en.damage(30, p);
				} else if (displayName.contains("Tier IV")) {
					en.damage(40, p);
				} else if (displayName.contains("Tier V")) {
					en.damage(50, p);
				} else if (displayName.contains("Tier VI")) {
					en.damage(60, p);
				} else if (displayName.contains("Tier VII")) {
					en.damage(70, p);
				} else if (displayName.contains("Tier VIII")) {
					en.damage(80, p);
				} else if (displayName.contains("Tier IX")) {
					en.damage(90, p);
				} else if (displayName.contains("Tier X")) {
					en.damage(100, p);
				} else en.damage(10, p);
				
				e.getEntity().remove();
			}
		} else if (itemMeta.getDisplayName().contains("Poeas's Bow") && itemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL) == 2 && itemMeta.hasLore() && itemMeta.isUnbreakable() && itemMeta.hasAttributeModifiers()) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9, true, false, true));
			en.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 4, true, false, true));
		}
	}
	
	@EventHandler
	public void onPlaceMainhand(BlockPlaceEvent e) {
		if (e.getItemInHand() == null) return;
		ItemStack item = e.getItemInHand();
		if (!(item.hasItemMeta())) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		if (itemMeta.getDisplayName().contains("Enchanted")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Dimensional")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Dimension")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Titan")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Coin")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Core")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Orb")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Wand")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Artifact")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Refine")) {
			e.setCancelled(true);
		} else if (itemMeta.getDisplayName().contains("Protector")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onExpBottle(ExpBottleEvent e) {
		ThrownExpBottle bot = e.getEntity();
		if (!(bot.getItem().hasItemMeta())) return;
		if (bot.getCustomName().contains("A Bigger Bottle o' Enchanting & Experience")) {
			e.setExperience(r.nextInt(100 - 50) + 50);
		} else if (bot.getCustomName().contains("A Much Bigger Bottle o' Enchanting & Experience")) {
			e.setExperience(r.nextInt(300 - 250) + 250);
		} else if (bot.getCustomName().contains("A Humongous Bottle o' Enchanting & Experience")) {
			e.setExperience(r.nextInt(550 - 400) + 400);
		} else if (bot.getCustomName().contains("A Gigantic Bottle o' Enchanting & Experience")) {
			e.setExperience(r.nextInt(800 - 650) + 650);
		} else if (bot.getCustomName().contains("A Preposterous Bottle o' Enchanting & Experience")) {
			e.setExperience(r.nextInt(1000 - 900) + 900);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		EntityType et = e.getEntity().getType();
		EntityType dat = e.getDamager().getType();
		
		if (!(dat.equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getDamager();
		
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		
		ItemMeta handMeta = mainhand.getItemMeta();
		if (!(handMeta.hasDisplayName())) return;
		
		if (handMeta.getDisplayName().contains("Fragor's Sword") && handMeta.isUnbreakable() && handMeta.hasAttributeModifiers() && mainhand.getType().equals(Material.DIAMOND_SWORD)) {
			if (!(et.equals(EntityType.CREEPER))) return;
			
			e.setDamage(e.getDamage() + 500);
			if (e.getEntity().getCustomName() == null) return;
			if (e.getEntity().getCustomName().contains("Creeper King")) {
				e.setDamage(e.getDamage() + 2500);
			}
		} else if (handMeta.getDisplayName().contains("Aspect of Ferrum") && mainhand.getType().equals(Material.IRON_SWORD) && handMeta.hasAttributeModifiers() && !handMeta.hasLore() && handMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
			if (!(et.equals(EntityType.IRON_GOLEM))) return;
			
			e.setDamage((e.getDamage() * 3));
		}
	}
	
	
	@EventHandler
	public void onDamageBoots(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		if (p.getInventory().getBoots() == null) return;
		
		if (p.getInventory().getBoots().isSimilar(TitanFetcher.getMitisBoots()) && e.getCause().equals(DamageCause.FALL)) {
			e.setCancelled(true);
			double bounceY = Math.abs(p.getFallDistance()) / 20;
			p.setVelocity(new Vector(p.getVelocity().getX(), bounceY, p.getVelocity().getZ()));
		}
		
	}
	
	@EventHandler
	public void onDamageDefenseChestplate(EntityDamageByEntityEvent e) {
		EntityType et = e.getEntity().getType();
		
		if (!(et.equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		
		if (p.getInventory().getChestplate() == null) return;
		ItemStack chestplate = p.getInventory().getChestplate();
		
		if (!(chestplate.hasItemMeta())) return;
		ItemMeta chestMeta = chestplate.getItemMeta();
		
		if (!(chestMeta.hasDisplayName())) return;
		if (chestMeta.getDisplayName().contains("Absorber Chestplate") && chestMeta.getEnchantLevel(Enchantment.MENDING) == 8) {
			if (r.nextInt(100) < 25) {
				p.sendMessage(ChatColor.AQUA + "You have absorbed the damage!");
				e.setCancelled(true);
				double playerHP = p.getHealth();
				p.setHealth(playerHP + (e.getDamage() / 4));
			}
		}
	}

	
	@EventHandler
	public void onDamagePlayer(EntityDamageByEntityEvent e) {
		EntityType et = e.getEntity().getType();
		EntityType dat = e.getDamager().getType();
		
		if (!(dat.equals(EntityType.PLAYER))) return;
		if (!(et.equals(EntityType.PLAYER))) return;
		
		Player da = (Player) e.getDamager();
		Player p = (Player) e.getEntity();
		
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		if (!(mainhand.hasItemMeta())) return;
		
		ItemMeta handMeta = mainhand.getItemMeta();
		if (!(handMeta.hasDisplayName())) return;
		
		if (handMeta.getDisplayName().contains("Blaze Sword")) {
			if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
				p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				p.sendMessage(ChatColor.GOLD + da.getName() + "'s " + ChatColor.RED + "Blaze Sword removed your fire aspect!");
			}
		} else if (handMeta.getDisplayName().contains("Freeze Wand")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, true));
			p.sendMessage(ChatColor.YELLOW + da.getName() + "'s " + ChatColor.DARK_AQUA + "Freeze Wand has frozen you!");
		}
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent e) {
		State fishState = e.getState();
		if (!(fishState.equals(State.REEL_IN))) return;
		
		Player p = e.getPlayer();
		if (p.getInventory().getItemInMainHand() == null) return;
		ItemStack item = p.getInventory().getItemInMainHand();
		if (!(item.hasItemMeta())) return;
		ItemMeta itemMeta = item.getItemMeta();
		
		if (itemMeta.getDisplayName().contains("Grappling Hook") && itemMeta.isUnbreakable() && item.getType().equals(Material.FISHING_ROD) && itemMeta.getEnchantLevel(Enchantment.PROTECTION_FIRE) == 8) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			p.setVelocity(p.getLocation().getDirection().multiply(p.getLocation().distance(e.getHook().getLocation()) / 7));
		} else if (itemMeta.hasEnchant(Enchantment.CHANNELING) && itemMeta.hasEnchant(Enchantment.PROTECTION_FALL) && itemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL) == 13657 && itemMeta.getEnchantLevel(Enchantment.CHANNELING) == 14 && itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS) && itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON)) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			Vector facing = p.getLocation().getDirection();
			p.teleport(e.getHook().getLocation().setDirection(facing));
			p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3F, 1F);
		}
	}
	
	ArrayList<UUID> gappleCooldown = new ArrayList<UUID>();
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null) return;
		
		if (e.getItem().isSimilar(TitanFetcher.getTitanPorkchop())) {
			p.setFoodLevel(20);
		} else if (e.getItem().isSimilar(TitanFetcher.getTitanGapple())) {
			e.setCancelled(true);
			
			if (gappleCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Your Titan Gapple is currently on a cooldown!");
			} else {
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 3F, 1F);
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 60 * 8, 14, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 6, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60 * 8, 1, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 8, 5, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 60 * 8, 8, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 60 * 8, 1, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 8, 9, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 8, 2, true, false, true));
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 8, 1, true, false, true));
				
				p.setFoodLevel(p.getFoodLevel() + 6);
				
				if (!(p.isOp())) {
					gappleCooldown.add(p.getUniqueId());
					
					new BukkitRunnable() {
						public void run() {
							gappleCooldown.remove(p.getUniqueId());
							p.sendMessage(ChatColor.GREEN + "Your Titan Gapple cooldown has been refreshed!");
						}
					}.runTaskLater(plugin, 20 * 60 * 15);
				}
			}
		}
	}
	
	@EventHandler
	public void onCombust(EntityCombustEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	
	@EventHandler
	public void onCombustBlock(EntityCombustByBlockEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	
	@EventHandler
	public void onCombustEntity(EntityCombustByEntityEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	
	@EventHandler
	public void onCombust(EntityDamageEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	
	@EventHandler
	public void onCombustBlock(EntityDamageByBlockEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	
	@EventHandler
	public void onCombustEntity(EntityDamageByEntityEvent e) {
		if (!(e.getEntity().getType().equals(EntityType.DROPPED_ITEM))) return;
		
		Item it = (Item) e.getEntity();
		Player p = Bukkit.getPlayer(it.getThrower());
		if (p == null) return;
		
		if (p.hasPermission("core.special.itemprotector")) {
			e.setCancelled(true);
			e.getEntity().setFireTicks(0);
		}
	}
	

	
	@EventHandler
	public void onSneakHelmet(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (!(e.isSneaking())) return;
		
		if (p.getInventory().getHelmet() == null) return;
		ItemStack helmet = p.getInventory().getHelmet();
		
		if (!(helmet.hasItemMeta())) return;
		
		ItemMeta helmetMeta = helmet.getItemMeta();
		
		if (!(helmetMeta.hasDisplayName())) return;
		
		if (!(helmet.getType().equals(Material.LEATHER_HELMET))) return;
		
		LeatherArmorMeta leatherHelmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
		
		Color creeperCrown = Color.fromRGB(0, 201, 20);
		
		if (helmetMeta.getDisplayName().contains("Creeper Crown") && helmetMeta.isUnbreakable() && leatherHelmetMeta.getColor().equals(creeperCrown)) {
			p.getWorld().createExplosion(p.getLocation(), 4F, false, true, p);
		}
	}
	
	ArrayList<UUID> saliantCooldown = new ArrayList<UUID>();
	@EventHandler
	public void onSneakBoots(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (!(e.isSneaking())) return;
		
		if (p.getInventory().getBoots() == null) return;
		ItemStack boots = p.getInventory().getBoots();
		
		if (!(boots.hasItemMeta())) return;
		
		ItemMeta bootsMeta = boots.getItemMeta();
		if (!(bootsMeta.hasDisplayName())) return;
		if (!(bootsMeta.isUnbreakable())) return;
		
		if (bootsMeta.getDisplayName().contains("Saliant Boots") && boots.getType().equals(Material.NETHERITE_BOOTS) && bootsMeta.hasLore() && bootsMeta.getEnchantLevel(Enchantment.PROTECTION_FALL) == 200) {
			if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
				p.sendMessage(ChatColor.RED + "Normal Abilities don't work here...");
				return;
			}
			if (saliantCooldown.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Saliant Boots are currently on a cooldown!");
			} else {
				e.setCancelled(true);
				saliantCooldown.add(p.getUniqueId());
				p.setVelocity(new Vector(p.getVelocity().getX(), 3, p.getVelocity().getZ()));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 15, 1, true, false, true));
				p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 3F, 0.5F);
				new BukkitRunnable() {
					public void run() {
						saliantCooldown.remove(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Saliant Boots cooldown has been refreshed!");
					}
				}.runTaskLater(plugin, 200);
			}
		}
	}
}
