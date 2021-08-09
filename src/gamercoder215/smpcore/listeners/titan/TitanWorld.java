package gamercoder215.smpcore.listeners.titan;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.advancements.TitanAdvancements;
import gamercoder215.smpcore.utils.entities.TitanEnderman;
import gamercoder215.smpcore.utils.entities.TitanPiglin;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;
import net.minecraft.server.level.WorldServer;

public class TitanWorld implements Listener {
	
	public Main plugin;
	private static AdvancementManager m = TitanAdvancements.m;
	
	
	public TitanWorld(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public enum TitanMaterial {
		SAXUM,
		OSSUM,
		EXITATUS,
		OPULENS,
		CHALYBS,
		CITO,
		INCITATUS_LEAVES,
		INCITATUS_LOG,
		NOVA,
		PRAEFORTIS_BLOCK,
		PRAEFORTIS_SHROOM,
		CLARUS,
		IABESIUM,
		RAW_FERRUM_INGOT,
		FERRUM_INGOT,
		ENCHANTED_FERRUM,
		FERRUM_BLOCK,
		CANTONIUM,
		CONSTIBILIS,
		// Nether
		MITIS,
		SOFTENED_MITIS,
		CRYSTAL_MITIS,
		RED_MITIS,
		BLACK_MITIS,
		GOLDEN_MITIS,
		// End
		HARDENED_MITIS
		
		
	}
	public TitanMaterial matchTitanMaterial(Material mat) {
		if (mat.equals(Material.DEEPSLATE)) return TitanMaterial.SAXUM;
		else if (mat.equals(Material.TUFF)) return TitanMaterial.OSSUM;
		else if (mat.equals(Material.SOUL_SAND)) return TitanMaterial.EXITATUS;
		else if (mat.equals(Material.WARPED_NYLIUM)) return TitanMaterial.OPULENS;
		else if (mat.equals(Material.OBSIDIAN)) return TitanMaterial.CHALYBS;
		else if (mat.equals(Material.SMOOTH_BASALT)) return TitanMaterial.CITO;
		else if (mat.equals(Material.ANCIENT_DEBRIS)) return TitanMaterial.INCITATUS_LOG;
		else if (mat.equals(Material.AMETHYST_BLOCK)) return TitanMaterial.INCITATUS_LEAVES;
		else if (mat.equals(Material.GLOWSTONE)) return TitanMaterial.NOVA;
		else if (mat.equals(Material.IRON_BLOCK)) return TitanMaterial.ENCHANTED_FERRUM;
		else if (mat.equals(Material.RAW_IRON_BLOCK)) return TitanMaterial.RAW_FERRUM_INGOT;
		else if (mat.equals(Material.SHROOMLIGHT)) return TitanMaterial.CLARUS;
		else if (mat.equals(Material.POLISHED_DIORITE)) return TitanMaterial.CANTONIUM;
		else if (mat.equals(Material.WARPED_HYPHAE)) return TitanMaterial.CONSTIBILIS;
		else if (mat.equals(Material.DEEPSLATE_COPPER_ORE)) return TitanMaterial.IABESIUM;
		else if (mat.equals(Material.WARPED_WART_BLOCK)) return TitanMaterial.PRAEFORTIS_BLOCK;
		else if (mat.equals(Material.WARPED_FUNGUS)) return TitanMaterial.PRAEFORTIS_SHROOM;
		else if (mat.equals(Material.END_STONE)) return TitanMaterial.MITIS;
		else if (mat.equals(Material.STONE)) return TitanMaterial.HARDENED_MITIS;
		else if (mat.equals(Material.SEA_LANTERN)) return TitanMaterial.SOFTENED_MITIS;
		else if (mat.equals(Material.EMERALD_BLOCK)) return TitanMaterial.CRYSTAL_MITIS;
		else if (mat.equals(Material.BLACKSTONE)) return TitanMaterial.BLACK_MITIS;
		else if (mat.equals(Material.CRIMSON_NYLIUM) || mat.equals(Material.CRIMSON_HYPHAE)) return TitanMaterial.RED_MITIS;
		else if (mat.equals(Material.GILDED_BLACKSTONE)) return TitanMaterial.GOLDEN_MITIS;
		else return null;
	}
	
	public ItemStack parseMaterial(TitanMaterial mat) {
		try {
			if (mat.equals(TitanMaterial.INCITATUS_LEAVES)) {
				return TitanFetcher.getIncitatusLeaves();
			} else if (mat.equals(TitanMaterial.SAXUM)) {
				return TitanFetcher.getSaxum();
			} else if (mat.equals(TitanMaterial.OSSUM)) {
				return TitanFetcher.getOssum();
			} else if (mat.equals(TitanMaterial.EXITATUS)) {
				return TitanFetcher.getExitatus();
			} else if (mat.equals(TitanMaterial.OPULENS)) {
				return TitanFetcher.getOpulens();
			} else if (mat.equals(TitanMaterial.CHALYBS)) {
				return TitanFetcher.getChalybs();
			} else if (mat.equals(TitanMaterial.CITO)) {
				return TitanFetcher.getCito();
			} else if (mat.equals(TitanMaterial.INCITATUS_LOG)) {
				return TitanFetcher.getIncitatusLog();
			} else if (mat.equals(TitanMaterial.NOVA)) {
				return TitanFetcher.getNova();
			} else if (mat.equals(TitanMaterial.RAW_FERRUM_INGOT)) {
				return TitanFetcher.getRawFerrumIngot();
			} else if (mat.equals(TitanMaterial.FERRUM_INGOT)) {
				return TitanFetcher.getFerrumIngot();
			} else if (mat.equals(TitanMaterial.ENCHANTED_FERRUM)) {
				return TitanFetcher.getEnchantedFerrum();
			} else if (mat.equals(TitanMaterial.CLARUS)) {
				return TitanFetcher.getClarus();
			} else if (mat.equals(TitanMaterial.CANTONIUM)) {
				return TitanFetcher.getCantonium();
			} else if (mat.equals(TitanMaterial.IABESIUM)) {
				return TitanFetcher.getIabesium();
			} else if (mat.equals(TitanMaterial.CONSTIBILIS)) {
				return TitanFetcher.getConstibilis();
			} else if (mat.equals(TitanMaterial.FERRUM_BLOCK)) {
				return TitanFetcher.getFerrumBlock();
			} else if (mat.equals(TitanMaterial.PRAEFORTIS_BLOCK)) {
				return TitanFetcher.getPraefortis();
			} else if (mat.equals(TitanMaterial.PRAEFORTIS_SHROOM)) {
				return TitanFetcher.getPraefortisShroom();
			}
			// Nether
			else if (mat.equals(TitanMaterial.SOFTENED_MITIS)) {
				return TitanFetcher.getSoftenedMitis();
			} else if (mat.equals(TitanMaterial.CRYSTAL_MITIS)) {
				return TitanFetcher.getCrystalMitis();
			} else if (mat.equals(TitanMaterial.BLACK_MITIS)) {
				return TitanFetcher.getBlackMitis();
			} else if (mat.equals(TitanMaterial.RED_MITIS)) {
				return TitanFetcher.getRedMitis();
			} else if (mat.equals(TitanMaterial.GOLDEN_MITIS)) {
				return TitanFetcher.getGoldenMitis();
			}
			// End
			else if (mat.equals(TitanMaterial.MITIS)) {
				return TitanFetcher.getMitis();
			} else if (mat.equals(TitanMaterial.HARDENED_MITIS)) {
				return TitanFetcher.getHardenedMitis();
			}
			else return null;
		
		} catch (CommandSyntaxException e) {
			return null;
		}
	}
	
	Random r = new Random();
	
	private void replenish(Player p, Block b) {
		if (b.getType().equals(Material.DEEPSLATE)) {
			if (!(m.getAdvancement(new NameKey("titan", "mine-ossum")).isDone(p))) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "mine-ossum")));
			}
			int chance = r.nextInt(100);
			if (chance < 10) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.DEEPSLATE_COPPER_ORE);
			} else {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.getWorld().getBlockAt(b.getLocation()).setType(Material.DEEPSLATE);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
			}
		} else if (b.getType().equals(Material.DEEPSLATE_COPPER_ORE)) {
			if (!(m.getAdvancement(new NameKey("titan", "mine-iabesium")).isDone(p))) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "mine-iabesium")));
			}
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.DEEPSLATE);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.RAW_IRON_BLOCK)) {
			if (!(m.getAdvancement(new NameKey("titan", "mine-ferrum")).isDone(p))) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "mine-ferrum")));
			}
			int chance = r.nextInt(100);
			if (chance < 5) {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.IRON_BLOCK);
			} else {
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
				new BukkitRunnable() {
					public void run() {
						b.getWorld().getBlockAt(b.getLocation()).setType(Material.RAW_IRON_BLOCK);
					}
				}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
			}
		} else if (b.getType().equals(Material.GLOWSTONE)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.GLOWSTONE);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.IRON_BLOCK)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.RAW_IRON_BLOCK);
		} else if (b.getType().equals(Material.AMETHYST_BLOCK)) {
			b.setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.AMETHYST_BLOCK);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(120 - 60) + 60));
		} else if (b.getType().equals(Material.SOUL_SAND)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.SOUL_SOIL);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.SOUL_SAND);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(180 - 90) + 90));
		} else if (b.getType().equals(Material.WARPED_HYPHAE)) {
			b.setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.WARPED_HYPHAE);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(90 - 60) + 60));
		} else if (b.getType().equals(Material.WARPED_FUNGUS)) {
			if (!(m.getAdvancement(new NameKey("titan", "farm-praefortis")).isDone(p))) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "farm-praefortis")));
			}
			b.setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.WARPED_FUNGUS);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(30 - 10) + 10));
		} else if (b.getType().equals(Material.WARPED_NYLIUM)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.WARPED_NYLIUM);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.SHROOMLIGHT)) {
			if (!(m.getAdvancement(new NameKey("titan", "mine-clarus")).isDone(p))) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "mine-clarus")));
			}
			b.setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.SHROOMLIGHT);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(120 - 90) + 90));
		} else if (b.getType().equals(Material.WARPED_WART_BLOCK)) {
			b.setType(Material.AIR);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.WARPED_WART_BLOCK);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(90 - 45) + 45));
		} else if (b.getType().equals(Material.OBSIDIAN)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.OBSIDIAN);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.ANCIENT_DEBRIS)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.ANCIENT_DEBRIS);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.TUFF)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.TUFF);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(10 - 3) + 3));
		} else if (b.getType().equals(Material.POLISHED_DIORITE)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.POLISHED_DIORITE);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(25 - 10) + 10));
		} else if (b.getType().equals(Material.SMOOTH_BASALT)) {
			b.getWorld().getBlockAt(b.getLocation()).setType(Material.BEDROCK);
			new BukkitRunnable() {
				public void run() {
					b.getWorld().getBlockAt(b.getLocation()).setType(Material.SMOOTH_BASALT);
				}
			}.runTaskLater(plugin, 20 * (r.nextInt(15 - 5) + 5));
		}
		m.saveProgress(p, "titan");
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		if (p.getWorld().getName().equalsIgnoreCase("world_titan_end")) return;
		if (p.getGameMode().equals(GameMode.CREATIVE)) return;
		e.setDropItems(false);
		
		if (p.isOp()) return;
		e.setCancelled(true);
		
		Block b = e.getBlock();
		
		if (b.getType() == null) return;
		
		// Drops
		if (matchTitanMaterial(b.getType()) == null) return;
		
		TitanMaterial mat = matchTitanMaterial(b.getType());
		ItemStack drop = parseMaterial(mat);
		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
		
		ExperienceOrb exp = (ExperienceOrb) b.getWorld().spawnEntity(b.getLocation(), EntityType.EXPERIENCE_ORB);
		exp.setExperience(r.nextInt(50 - 10) + 10);
		p.getWorld().dropItemNaturally(b.getLocation(), drop);
		
		if (b.getType().equals(Material.IRON_BLOCK) && r.nextInt(100) < 5) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon elder_guardian ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,AbsorptionAmount:500f,Health:50000f,CustomName:'{\"text\":\"Energy Guardian\",\"color\":\"blue\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Titan Core\",\"color\":\"#006478\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRhZDIyOWQ4MDMwODA1OWZhN2FlZDg2NTQzNzc5Y2Y5MzNmOTFiNmE0Mzc0MzEyOTNkMGJiMzFhMDk1NWI3MSJ9fX0=\"}]},Id:[I; 1341988342, 1338392604, -1334015479, -26366109]}}},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{},{},{},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Energy Cap\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:blast_protection\",lvl:255s},{id:\"minecraft:projectile_protection\",lvl:255s},{id:\"minecraft:respiration\",lvl:255s},{id:\"minecraft:aqua_affinity\",lvl:1s},{id:\"minecraft:thorns\",lvl:125s}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.100F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:5b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:50000},{Name:generic.knockback_resistance,Base:2},{Name:generic.movement_speed,Base:1},{Name:generic.attack_damage,Base:75},{Name:generic.armor,Base:35},{Name:generic.armor_toughness,Base:35},{Name:generic.attack_knockback,Base:15}]}");
			p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 3F, 1F);
		}
		
		replenish(p, b);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		Player p = (Player) e.getEntity();
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		
		if (e.getCause().equals(DamageCause.LAVA)) {
			e.setDamage(100);
		} else if (e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.FIRE_TICK)) {
			e.setDamage(50);
		}
	}
	
	Sound[] villagerSounds = {
			Sound.ENTITY_VILLAGER_AMBIENT,
			Sound.ENTITY_VILLAGER_CELEBRATE,
			Sound.ENTITY_VILLAGER_YES,
			Sound.ENTITY_VILLAGER_TRADE
	};
	
	Sound[] pillagerSounds = {
			Sound.ENTITY_PILLAGER_AMBIENT,
			Sound.ENTITY_PILLAGER_CELEBRATE
	};
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked().getType().equals(EntityType.PLAYER))) return;
		
		Player p = e.getPlayer();
		Player npc = (Player) e.getRightClicked();
		
		String name = ChatColor.stripColor(npc.getCustomName() == null ? npc.getName() : npc.getCustomName());
		if (name.equalsIgnoreCase("Venalicius")) {
			p.playSound(p.getLocation(), villagerSounds[r.nextInt(villagerSounds.length)], 3F, 0F);
			p.openInventory(TitanFetcher.getVenaliciusTrade());
		} else if (name.equalsIgnoreCase("Shulker Wizard")) {
			p.playSound(p.getLocation(), pillagerSounds[r.nextInt(pillagerSounds.length)], 3F, 0.75F);
		}
	}
	
	@EventHandler
	public void onInteractInventory(InventoryClickEvent e) {
		if (e.getWhoClicked() == null) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getView() == null) return;
		InventoryView inv = e.getView();
		if (!(inv.getTitle().contains("Titan Shop"))) return;
		if (e.getCurrentItem() == null) return;
		ItemStack clickedItem = e.getCurrentItem();
		if (!(clickedItem.hasItemMeta())) return;
		ItemMeta itemMeta = clickedItem.getItemMeta();
		if (!(itemMeta.hasDisplayName())) return;
		String display = ChatColor.stripColor(itemMeta.getDisplayName());
		if (e.getClick() == null) return;
		ClickType click = e.getClick();
		String notEnoughBuy = ChatColor.RED + "You don't have enough items to buy!";
		String notEnoughSell = ChatColor.RED + "You don't have enough items to sell!";
		
		if (p.getInventory().firstEmpty() == -1) {
			p.sendMessage(ChatColor.RED + "Your inventory is full!");
			return;
		}
		
		try {
			if (inv.getTitle().contains("Venalicius's Barter")) {
				if (display.equalsIgnoreCase("Ossum Trade")) {
					if (click.equals(ClickType.LEFT) || click.equals(ClickType.SHIFT_LEFT)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getSaxum(), 8))) {
							p.sendMessage(notEnoughBuy);
						} else {
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							p.getInventory().removeItem(TitanFetcher.getSaxum());
							
							p.getInventory().addItem(TitanFetcher.getOssum());
						}
					} else if (click.equals(ClickType.RIGHT) || click.equals(ClickType.SHIFT_RIGHT)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getOssum(), 1))) {
							p.sendMessage(notEnoughSell);
						} else {
							p.getInventory().removeItem(TitanFetcher.getOssum());
							
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
							p.getInventory().addItem(TitanFetcher.getSaxum());
						}
					}
				} else if (display.equalsIgnoreCase("Praefortis Trade")) {
					if (click.equals(ClickType.LEFT) || click.equals(ClickType.SHIFT_LEFT)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getPraefortisShroom(), 9))) {
							p.sendMessage(notEnoughBuy);
						} else {
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().removeItem(TitanFetcher.getPraefortisShroom());
							
							p.getInventory().addItem(TitanFetcher.getPraefortis());
						}
					} else if (click.equals(ClickType.RIGHT) || click.equals(ClickType.SHIFT_RIGHT)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getPraefortis(), 1))) {
							p.sendMessage(notEnoughSell);
						} else {
							p.getInventory().removeItem(TitanFetcher.getPraefortis());
							
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
							p.getInventory().addItem(TitanFetcher.getPraefortisShroom());
						}
					}
				}
			}
		} catch (CommandSyntaxException err) {
			p.sendMessage(ChatColor.RED + "An error has occured! Please report this!");
			err.printStackTrace();
		}
		
	}
	
	@EventHandler
	public void onHarvest(PlayerHarvestBlockEvent e) {
		Player p = e.getPlayer();
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		
		if (e.getHarvestedBlock().getType().equals(Material.CAVE_VINES_PLANT) || e.getHarvestedBlock().getType().equals(Material.CAVE_VINES)) {	
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.isOp()) return;
		if (!(p.getWorld().getName().equalsIgnoreCase("world_titan"))) return;
		if (!(p.getGameMode().equals(GameMode.ADVENTURE))) {
			p.setGameMode(GameMode.ADVENTURE);
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (p.isOp()) return;
		if (p.getWorld().getName().equalsIgnoreCase("world_titan")) {
			p.setGameMode(GameMode.ADVENTURE);
		} else {
			p.setGameMode(GameMode.SURVIVAL);
		}
		
		
	}
	
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent e) {
		ItemStack item = e.getSource();
		if (item.equals(TitanFetcher.getIabesium())) {
			e.setCancelled(true);
		} else if (item.equals(TitanFetcher.getIncitatusLog())) {
			e.setCancelled(true);
		} else if (item.equals(TitanFetcher.getRawFerrumIngot())) {
			e.setResult(TitanFetcher.getFerrumIngot());
		}
	}
	
	@EventHandler
	public void onCook(BlockCookEvent e) {
		ItemStack item = e.getSource();
		if (item.isSimilar(TitanFetcher.getIabesium())) {
			e.setCancelled(true);
		} else if (item.isSimilar(TitanFetcher.getIncitatusLog())) {
			e.setCancelled(true);
		} else if (item.isSimilar(TitanFetcher.getRawFerrumIngot())) {
			e.setResult(TitanFetcher.getFerrumIngot());
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItemInHand();
		if (TitanFetcher.getTitanWorldItems().contains(item)) {
			e.setCancelled(true);
		}
		
		if ((p.getWorld().getName().equalsIgnoreCase("world_titan") || p.getWorld().getName().equalsIgnoreCase("world_titan_nether")) && !(p.isOp())) {
			e.setCancelled(true);
		}
		
		if (p.getWorld().getName().equalsIgnoreCase("world_titan_end") && !(p.isOp()) && (Math.abs(p.getLocation().getX()) < 31 || Math.abs(p.getLocation().getZ()) < 31)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryOpen(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		
		if (e.getClickedBlock() == null) return;
		
		if (e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
			e.setCancelled(true);
			p.openInventory(TitanEnchantmentTable.getTitanEnchantTable());
		}
	}
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (e.getEntity() == null) return;
		if (!(e.getEntity().getWorld().getName().contains("world_titan"))) return;
		
		WorldServer wrld = ((CraftWorld) e.getEntity().getWorld()).getHandle();
		
		if (e.getEntityType().equals(EntityType.ENDERMAN)) {
			e.setCancelled(true);
			
			if (r.nextInt(100) < 10) {
				TitanEnderman en = new TitanEnderman(e.getLocation());
				wrld.addEntity(en);
			}
		} else if (e.getEntityType().equals(EntityType.PIGLIN)) {
			e.setCancelled(true);
			
			if (r.nextInt(100) < 10) {
				TitanPiglin en = new TitanPiglin(e.getLocation(), r.nextBoolean());
				wrld.addEntity(en);
			}
		}
	}
}
