package us.teaminceptus.smpcore.fishing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.mcmonkey.sentinel.SentinelTrait;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.SkinTrait;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.fetcher.ItemFetcher;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher.Potion;
import us.teaminceptus.smpcore.utils.fetcher.WandFetcher;
import us.teaminceptus.smpcore.utils.fetcher.WeaponFetcher;

public class FishingUtils implements Listener {

	protected SMPCore plugin;
	protected NPCRegistry registry;
	
	static Random r = new Random();
	
	public FishingUtils(SMPCore plugin) {
		this.plugin = plugin;
		this.registry = CitizensAPI.getNPCRegistry();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void addNPCAttributes(Player target, NPC npc, double tier) {
		SentinelTrait sentinel = npc.getOrAddTrait(SentinelTrait.class);
		sentinel.addTarget("players");
		sentinel.addTarget("axolotls");
		sentinel.addTarget("iron_golem");
		
		sentinel.setHealth(tier * 200);
		sentinel.armor = (tier * 10);
		sentinel.respawnTime = -1;
		sentinel.attackRate = 10;
		
		new BukkitRunnable() {
			public void run() {
				npc.despawn();
				target.sendMessage(ChatColor.GREEN + "This entity has despawned!");
			}
		}.runTaskLater(JavaPlugin.getPlugin(SMPCore.class), 20 * 60 * 10);
	}
	
	public static void setSkin(NPC npc, FishSkin skin) {
		SkinTrait skinT = npc.getOrAddTrait(SkinTrait.class);
		skinT.setSkinPersistent(skin.getName(), skin.getSignature(), skin.getTexture());
	}
	
	public static enum FishSkin {
		
		DROWNED_CREEPER("ewogICJ0aW1lc3RhbXAiIDogMTY0MDU0NjIzMTQzOCwKICAicHJvZmlsZUlkIiA6ICIwNTVhOTk2NTk2M2E0YjRmOGMwMjRmMTJmNDFkMmNmMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVWb3hlbGxlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y0MTZkM2YzNTJlMGUyNjM0MDA0MzAzZWQ3OWQ4MTEyZTQ3YTI4M2E4YzNlMjU4NmVkZmFkNjJkM2E3ZWEyYjgiCiAgICB9CiAgfQp9", 
				"D7dht08+aMJfGXhmYw9wZxccu1fJ+IRWfjHLUCV9pRbiS+drnHCgbAkik7r/d2b4o2sgMUFyP83TNA0kS13rnwDViT9jF2/ob9M/sB5acvrPLiyqyHfY2XafxQQ1orH3a3eTVP0qOVLACUS61qzIePyIyJF7m0LvYFyOsO7Yuu2Pu0CJ0a8Kru+Sy2yP7Hw5sS5sOYudwaMdogxe1tjJUJRtKXKvC3TaSUF9kBXzjB4ZXaJYzQK8sQLMP3Nfo0CDYJAXowB1/Yviokask8XWGcKXmftZx4RjPEbkPEnXYnLklkQIuK4S7Es8aPqFvfAG42QhA38aaSNp38yXLzQ3uVZTUbSFQZmbHHi4u4IGrhlM8EozQoea2i/3+yzvcgPUUiiM6ssxENK1dWOT0NcCeFCDrilmNOaaDKbwNI1R5LQ6FEIibIPjZoq0/8GVS97ZSFq+O6o6gsgpc1/8Y+WFcaQ4MVBSzcVQVd4VR544RLhzNVPTvIzQhRKOQn0pv0S6Dd7fcmDDKRDlnb9sNLZwrYhIUmcUvWsOO5SscLh2Ehpqe/hfi2k4Mh60rojFDQAWzwNqpqjtxJSmlbAoUHp0tf4loY9z+HvK4MDJ2+9GsK74OjC/GJm+bHl78/QIOp/LmO4LphjlpJ38NirXELtMPau3gD6eCLrRxDbt+4KuR9E=",
				1),
		SNOWMAN_STEVE("ewogICJ0aW1lc3RhbXAiIDogMTY0MDYzNTc1MjI2MiwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q4MTIyZmQzNmVlYzdlZjI0NDdiMWQ0NGMzMzcxMDllYzRkNWY1NTIyY2M2MjFhOGUzZjg0ODIzYjA5NDJmNzgiCiAgICB9CiAgfQp9", 
				"fqhqUDR04FhPstyzEfMGFmZZE6nTiWYYC4nwrsdLKNje7nLvY5JbZ9QLE5ywO/76FdRU4vAk0m52IxXKEJVv6sx8LRPDqn1274QgU9D+SVvD9aJPZmrpFsQNlRhFnFwyuFhEMxHEgiY6j9lcwUt4bNV3rq+lJYsTSFFAg628zW3qFGrVNKQtcnfdBoOPmPi3pKX1kDkubdPyUQmo8OLREVBIInXJcVJkezFVz6Hn3nWk6ajgoW40ILKDmqyrMDl0xpnLlmXIlmrKqj+ATZxnhdHSjtHDJw2FzHe9U1FNcVoQV0Y27DfAeWjMBxD0KzXxcuVijkrOtF777EG/0Cz1VXZR6h1s6OAowbs3FpTcqk0vKG2XJPsiMtRkBhgRxwTS2b2PIDhJVHrtLyNUf/dcOTIAUyDbSWnjpUTG6yIcpeAm+Z1u0bb19RENYSX6xTYP4mS88uxmJKw9s+K3dXF1u490aqcOI8OKAcQmZ0L0P5X9MXyTu4wtbYd4V4zAkQhPuwsVOmlzgsP4MBf18YyekVL9liH5dVHfmyH4ibTGgXo64zZE8OE55LZY3LxLTLgzsJbK1hr8tG1SvlPM2zSCFP/LHO485FQOmmdpTs5F2vK1eLK6SnSwD3M+/VBJuDe6PGk6k0ALRgQxiljHB1vF910Byl9DNqbQfZ03HjNSNrk=",
				1),
		SEA_SNAKE("ewogICJ0aW1lc3RhbXAiIDogMTY0MDYzNjAzMzcwMCwKICAicHJvZmlsZUlkIiA6ICI2MTZiODhkNDMwNzM0ZTM3OWM3NDc1ODdlZTJkNzlmZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWxseUZpbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81YzYyZDEzMWU1YmU5NDBhMDJhYTc0OWYyYjA3MGIwMzE0MWJjMTg5OGZhYWE2ZGZhM2VkY2QyNGNjODgzOTQzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
				"kSW47lU077WSxwxLIdY9HO92MsKSiMlDKr9uCIKi6YQ9YTHi6QGu70cNf4TBKbXCQy6KIR7xP/s4gPmwf3y5XjAF1//lCqoLzvYgMaOKp8kB/0m7tc/krNydIsJJIMG8HGZIZHzg+HR+EILpTjQ57aKANqRjhwx05HZCCUe77LkBhqgR3yfWlUsieeRNcvSVIh/EKCmaTEc9tGbhAxwpAz7Sb8Of4UxCLX6FTghCaZTgwl5iROlnAp0Xp9m27A5YoGv960K+hc1CKZJMLnWNnqKS6wkuUVU0Ea9+wMJj9DuePSERzfFKf4Fn1/JYfoC2TPJ3EDf9rKa80wqoNwrUGiKawxxQpyp5s1mDhK1TD3SQHw8gSzZPsRfiXii7Z/qhyAsy51DXNZIKuCOG4PaFPxameEPqY9S8pBkxm3MoG35aAx9oDaQqa6L/VoZ6Rdf7MrbT88+ML28opVCtEvQymCSLLOgAI4IexmivZzCTce0cYyNpRk5OuOsbRqUZFzkkSHDlDoBZgw1YLwpvLAOsPp6ck06MX453eeHYVStwoUjg1hNExYq4piHx0NGsyWIrTZ/yWstYA5Wjq5u+XSs46nv6LAGH4fcO5YfmjL9XeZkLlWQX/ULVK/ZAIwTIAbHTBGYPYzAoWvpE4F9H5deyX9YXbH4rPdyoO1rw9hy9Pcg=",
				2),
		;
		
		private final String texture;
		private final String signature;
		private final int tier;
		
		private FishSkin(String texture, String signature, int tier) {
			this.texture = texture;
			this.signature = signature;
			this.tier = tier;
		}
		
		public final String toString() {
			return name().toLowerCase();
		}
		
		public final String getName() {
			return toString();
		}
		
		public final String getTexture() {
			return this.texture;
		}
		
		public final String getSignature() {
			return this.signature;
		}
		
		public final int getTier() {
			return this.tier;
		}
	};
	
	@EventHandler
	public void onFish(PlayerFishEvent e) {
		if (e.getState() != State.CAUGHT_FISH) return;
		Player p = e.getPlayer();
		
		double goodLuck = Math.floor(p.getAttribute(Attribute.GENERIC_LUCK).getValue() * r.nextDouble());
		double badLuck = Math.floor(goodLuck - (p.getAttribute(Attribute.GENERIC_LUCK).getValue() * r.nextDouble()));
		
		boolean isUnlucky = (goodLuck > badLuck ? false : true);
		
		Map<ItemStack, Double> items = new HashMap<>();
		items.put(new ItemStack(Material.TOTEM_OF_UNDYING), 20.0D);
		items.put(new ItemStack(Material.NETHER_STAR), 15.0D);
		items.put(new ItemStack(Material.EMERALD, r.nextInt(28) + 4), 20.0D);
		items.put(new ItemStack(Material.IRON_CHESTPLATE), 10.0D);
		items.put(new ItemStack(Material.DIAMOND_HELMET), 26.5D);
		items.put(new ItemStack(Material.NETHERITE_BOOTS), 10.0D);
		items.put(new ItemStack(Material.IRON_LEGGINGS), 15.0D);
		items.put(new ItemStack(Material.IRON_ORE, r.nextInt(16) + 16), 45.5D);
		items.put(new ItemStack(Material.DIAMOND_CHESTPLATE), 8.5D);
		items.put(new ItemStack(Material.NETHERITE_CHESTPLATE), 7.0D);
		items.put(new ItemStack(Material.DIAMOND, r.nextInt(15) + 1), 15.0D);
		items.put(new ItemStack(Material.OAK_SAPLING, r.nextInt(4) + 1), 25.0D);
		items.put(new ItemStack(Material.COOKIE, r.nextInt(8) + 1), 15.5D);
		
		Map<ItemStack, Double> rareItems = new HashMap<>();
		rareItems.put(ItemFetcher.getOverworldEssence(), 30.0D);
		rareItems.put(ItemFetcher.getEnergyCore(), 25.5D);
		rareItems.put(ItemFetcher.getT2Heart(), 24.5D);
		rareItems.put(ItemFetcher.getWitherMaterial(), 18.5D);
		rareItems.put(ItemFetcher.getUndeadCore(), 23.0D);
		rareItems.put(ItemFetcher.getAquaticCore(), 25.0D);
		rareItems.put(PlanataeFetcher.getAurum(), 16.0D);
		rareItems.put(ItemFetcher.getVoidCrack(), 17.5D);
		rareItems.put(ItemFetcher.getIronEssence(), 5.5D);
		rareItems.put(ItemFetcher.getGoldenEssence(), 4.25D);
		rareItems.put(PlanataeFetcher.getEpsilonSheet(), 10.5D);
		
		Map<ItemStack, Double> legendaryItems = new HashMap<>();
		legendaryItems.put(TitanFetcher.getAttributeApple(), 35.0D);
		legendaryItems.put(TitanFetcher.getInfernoLightsaber(), 10.5D);
		legendaryItems.put(TitanFetcher.generatePotion(Potion.values()[r.nextInt(Potion.values().length)]), 7.5D);
		legendaryItems.put(WeaponFetcher.getWitherCounter(), 16.5D);
		legendaryItems.put(TitanFetcher.getMitisEssence(), 9.5D);
		legendaryItems.put(WeaponFetcher.getSpiderCounter(), 26.5D);
		
		ItemStack vA = TitanFetcher.getVelocityArrow();
		vA.setAmount(r.nextInt(8) + 1);
		legendaryItems.put(vA, 10.5D);
		
		legendaryItems.put(WandFetcher.getDamageWand(p, plugin).generateWand(), 4.5D);
		legendaryItems.put(WandFetcher.getEnderWand(p, plugin).generateWand(), 3.0D);
		if (isUnlucky) {
			// Spawn Entity Logic
			if (r.nextInt(100) < 20) {
				if (r.nextInt(100) < 3) {
					
				}
			}
		} else {
			if (r.nextInt(100) < 20) {
				if (r.nextInt(100) < 3) {
					ItemStack item = legendaryItems.keySet().stream().toList().get(r.nextInt(legendaryItems.keySet().size()));
					if (r.nextInt(100) < legendaryItems.get(item)) {
						e.setCancelled(true);
						Item entityItem = p.getWorld().dropItemNaturally(e.getHook().getLocation(), item);
						e.getHook().addPassenger(entityItem);
					}
				} else {
					ItemStack item = rareItems.keySet().stream().toList().get(r.nextInt(rareItems.keySet().size()));
					if (r.nextInt(100) < rareItems.get(item)) {
						e.setCancelled(true);
						Item entityItem = p.getWorld().dropItemNaturally(e.getHook().getLocation(), item);
						e.getHook().addPassenger(entityItem);
					}
				}
			} else {
				ItemStack item = items.keySet().stream().toList().get(r.nextInt(items.keySet().size()));
				if (r.nextInt(100) < items.get(item)) {
					e.setCancelled(true);
					Item entityItem = p.getWorld().dropItemNaturally(e.getHook().getLocation(), item);
					e.getHook().addPassenger(entityItem);
				}
			}
		}
	}
	
	// NPC Abilities
	@EventHandler
	public void onSpawn(NPCSpawnEvent e) {
		NPC npc = e.getNPC();
		SentinelTrait sentinel = npc.getOrAddTrait(SentinelTrait.class);
		LivingEntity en = (LivingEntity) npc.getEntity();
		World w = en.getWorld();
		
		if (npc.getName().contains("Sea Snake")) {
			new BukkitRunnable() {
		          public void run() {
		        	  if (en.isDead()) return;
		        	  
		        	  for (Entity possibleTarget : en.getNearbyEntities(50, 50, 50)) {
			        	  if (possibleTarget instanceof Player p) {
			        		  boolean chance = r.nextBoolean();
			        		  
			        		  if (chance) {
			        			  sentinel.setInvincible(true);
			        			  en.setInvisible(true);
			        			  
			        			  ArmorStand as = (ArmorStand) en.getWorld().spawnEntity(en.getLocation().subtract(0.0D, 0.3D, 0.0D), EntityType.ARMOR_STAND);
			                      as.setArms(false);
			                      as.setSmall(true);
			                      as.setMarker(true);
			                      as.setInvulnerable(true);
			                      as.setVisible(false);
			                      
			                      Location dest = en.getLocation().add(en.getLocation().getDirection().multiply(10));
			                      Vector vector = dest.subtract(en.getLocation()).toVector();
			                      
			                      if (!(as.getWorld().getBlockAt(as.getLocation().add(vector.multiply(10))).isPassable())) {
			                    	  as.remove();
			                    	  sentinel.setInvincible(false);
			                    	  en.setInvisible(false);
			                    	  return;
			                      }
			                      new BukkitRunnable() {
			                    	  int i = 0;
			                    	  int distance = 10;
			                    	  
			                    	  public void run() {
			                    		  if (!(as.getWorld().getBlockAt(as.getLocation().add(vector.multiply(distance))).isPassable())) cancel();
			                              as.teleport(as.getLocation().add(vector.normalize()));
			                              if (!as.getLocation().getBlock().getBlockData().matches(Material.AIR.createBlockData()))
			                            	  p.sendBlockChange(as.getLocation(), Material.DIRT.createBlockData()); 
			                              if (this.i > this.distance && 
			                                !as.isDead()) {
			                                World world = as.getWorld();
			                                world.createExplosion(as.getLocation(), 4.0F, false, false);
			                                en.teleport(as.getLocation().add(0.0D, 3.0D, 0.0D));
			                                en.setInvisible(false);
			                                sentinel.setInvincible(false);
			                                as.remove();
			                                cancel();
			                              } 
			                              this.i++;
			                    	  }
			                      }.runTaskTimer(plugin, 0, 3);
			        		  } else {
			                      ArmorStand as = (ArmorStand)w.spawnEntity(en.getLocation().add(0.0D, 0.5D, 0.0D), EntityType.ARMOR_STAND);
			                      as.setVisible(false);
			                      as.setSmall(true);
			                      as.setMarker(true);
			                      as.setArms(false);
			                      as.setBasePlate(false);
			                      as.setInvulnerable(true);
			                      
			                      Location dest = en.getLocation().add(en.getLocation().getDirection().multiply(10));
			                      Vector vector = dest.subtract(en.getLocation()).toVector();
			                      new BukkitRunnable() {
			                          int i = 0;
			                          
			                          int distance = 15;
			                    	  public void run() {
			                    	       as.teleport(as.getLocation().add(vector.normalize()));
			                               w.spawnParticle(Particle.SNEEZE, as.getLocation(), 10);
			                               for (Entity ten : en.getLocation().getChunk().getEntities()) {
			                            	   if (!(ten instanceof LivingEntity len)) continue;
			                            	   if (len.getUniqueId().equals(en.getUniqueId())) continue;
			                            	   if (!(as.isDead()) && as.getLocation().distanceSquared(len.getLocation()) <= 1.0D) {
			                            		   len.damage(10, en);
			                            		   len.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 2));
			                            		   as.remove();
			                            		   cancel();
			                            	   }
			                               }
			                               if (this.i > this.distance && !as.isDead()) {
			                            	   as.remove();
			                            	   cancel();
			                               } 
			                    	  }
			                      }.runTaskTimer(plugin, 0, 4);
			        		  }
			        		  
			        		  if (en.isDead()) cancel();
			        		  break;
			        	  }
			        }
		        	  
		        	if (en.isInvisible()) en.setInvisible(false);
		        	if (sentinel.invincible) sentinel.setInvincible(false);
		          }
			}.runTaskTimer(plugin, 0, 60);
		}
	}
	
	// Other Abilities

}
