package com.stephen.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import gamercoder215.smpcore.main;





  @EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
			Player player = event.getPlayer();
						if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
			&& player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Item Ability: Very Weak Throw §eRIGHT CLICK")) {
				
				String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(4);
				List<String> loresplit = new ArrayList<String>(Arrays.asList(lore.split(" ")));
				String damage = loresplit.get(0);
				damage = damage.replaceAll("§c", "");
				damage = damage.replaceAll(",", "");
				
				String finaldamage = damage;
				
				ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
				
				as.setArms(true);
				as.setGravity(false);;
				as.setVisible(false);
				as.setSmall(true);
				as.setMarker(true);
				as.setItemInHand(new ItemStack(Material.IRON_SWORD));
				as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));
				
				player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
				
				Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
				Vector vector = dest.subtract(player.getLocation()).toVector();
				
				new BukkitRunnable() {
					int distance = 15;
					int i = 0;
					public void run() {
						EulerAngle rot = as.getRightArmPose();
						EulerAngle rotnew = rot.add(20, 0, 0);
						as.setRightArmPose(rotnew);
						as.teleport(as.getLocation().add(vector.normalize()));
						
						if(as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(3).isPassable()) {
							
							if(!as.isDead()) {
								
								as.remove();
								
								cancel();
								
								
								
							}	
						}
						
						for(Entity entity : as.getLocation().getChunk().getEntities()) {
							
							if(!as.isDead()) {
								if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
									
									if(entity != player && entity != as && !(entity instanceof ArmorStand)) {
										
										if(entity instanceof LivingEntity) {
											
											LivingEntity livingentity = (LivingEntity) entity;
											livingentity.damage(7, player);
											as.remove();
											
											cancel();
											
										}
										
									}
									
								}
							}
							
						}
					 
						if(i > distance) {
							if(!as.isDead()) {
								as.remove();
								
							cancel();
							}
						}
						i++;
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				event.setCancelled(true);
				}	
						if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
								&& player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Item Ability: Weak Throw §eRIGHT CLICK")) {
							
						String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(4);
						List<String> loresplit = new ArrayList<String>(Arrays.asList(lore.split(" ")));
						String damage = loresplit.get(0);
						damage = damage.replaceAll("§c", "");
						damage = damage.replaceAll(",", "");
							
						String finaldamage = damage;
							
						ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
							
						as.setArms(true);
						as.setGravity(false);;
						as.setVisible(false);
						as.setSmall(true);
						as.setMarker(true);
						as.setItemInHand(new ItemStack(Material.GOLDEN_SWORD));
						as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));
						
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
							
						Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
						Vector vector = dest.subtract(player.getLocation()).toVector();
							
						new BukkitRunnable() {
							int distance = 15;
							int i = 0;
							public void run() {
								EulerAngle rot = as.getRightArmPose();
								EulerAngle rotnew = rot.add(20, 0, 0);
								as.setRightArmPose(rotnew);
								as.teleport(as.getLocation().add(vector.normalize()));
									
								if(as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(3).isPassable()) {
										
									if(!as.isDead()) {
											
										as.remove();
											
										cancel();
											
											
											
										}	
									}
									
								for(Entity entity : as.getLocation().getChunk().getEntities()) {
										
									if(!as.isDead()) {
										if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
												
											if(entity != player && entity != as && !(entity instanceof ArmorStand)) {
													
												if(entity instanceof LivingEntity) {
														
													LivingEntity livingentity = (LivingEntity) entity;
													livingentity.damage(10, player);
													as.remove();
														
													cancel();
														
													}
													
												}
												
											}
										}
										
									}
								 
								if(i > distance) {
									if(!as.isDead()) {
										as.remove();
											
									cancel();
									}
								}
								i++;
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							event.setCancelled(true);
							}
						if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
						&& player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Item Ability: Throw §eRIGHT CLICK")) {
					
				String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(4);
				List<String> loresplit = new ArrayList<String>(Arrays.asList(lore.split(" ")));
				String damage = loresplit.get(0);
				damage = damage.replaceAll("§c", "");
				damage = damage.replaceAll(",", "");
					
				String finaldamage = damage;
					
				ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
					
				as.setArms(true);
				as.setGravity(false);;
				as.setVisible(false);
				as.setSmall(true);
				as.setMarker(true);
				as.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
				as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));
				
				player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
					
				Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
				Vector vector = dest.subtract(player.getLocation()).toVector();
					
				new BukkitRunnable() {
					int distance = 15;
					int i = 0;
					public void run() {
						EulerAngle rot = as.getRightArmPose();
						EulerAngle rotnew = rot.add(20, 0, 0);
						as.setRightArmPose(rotnew);
						as.teleport(as.getLocation().add(vector.normalize()));
							
						if(as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(3).isPassable()) {
								
							if(!as.isDead()) {
									
								as.remove();
									
								cancel();
									
									
									
								}	
							}
							
						for(Entity entity : as.getLocation().getChunk().getEntities()) {
								
							if(!as.isDead()) {
								if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
										
									if(entity != player && entity != as && !(entity instanceof ArmorStand)) {
											
										if(entity instanceof LivingEntity) {
												
											LivingEntity livingentity = (LivingEntity) entity;
											livingentity.damage(20, player);
											as.remove();
												
											cancel();
												
											}
											
										}
										
									}
								}
								
							}
						 
						if(i > distance) {
							if(!as.isDead()) {
								as.remove();
									
							cancel();
							}
						}
						i++;
							
						}
					}.runTaskTimer(plugin, 0L, 1L);
					event.setCancelled(true);
					}		
			if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
					&& player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Item Ability: Strong Throw §eRIGHT CLICK")) {
						
						String lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(4);
						List<String> loresplit = new ArrayList<String>(Arrays.asList(lore.split(" ")));
						String damage = loresplit.get(0);
						damage = damage.replaceAll("§c", "");
						damage = damage.replaceAll(",", "");
							
						String finaldamage = damage;
							
						ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
							
						as.setArms(true);
						as.setGravity(false);
						as.setVisible(false);
						as.setSmall(true);
						as.setMarker(true);
						as.setItemInHand(new ItemStack(Material.NETHERITE_SWORD));
						as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));
							
						player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
						
						Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
						Vector vector = dest.subtract(player.getLocation()).toVector();
							
						new BukkitRunnable() {
							int distance = 15;
							int i = 0;
							public void run() {
								EulerAngle rot = as.getRightArmPose();
								EulerAngle rotnew = rot.add(20, 0, 0);
								as.setRightArmPose(rotnew);
								as.teleport(as.getLocation().add(vector.normalize()));
									
								if(as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(3).isPassable()) {
										
									if(!as.isDead()) {
											
										as.remove();
											
										cancel();
											
											
											
									}	
								}
									
								for(Entity entity : as.getLocation().getChunk().getEntities()) {
										
									if(!as.isDead()) {
										if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
												
											if(entity != player && entity != as && !(entity instanceof ArmorStand)) {
													
												if(entity instanceof LivingEntity) {
														
													LivingEntity livingentity = (LivingEntity) entity;
													livingentity.damage(50, player);
													as.remove();
														
													cancel();
													
												}
													
											}
												
										}
									}
										
								}
								 
								if(i > distance) {
									if(!as.isDead()) {
										as.remove();
											
									cancel();
									}
								}
								i++;
									
						}
					}.runTaskTimer(plugin, 0L, 1L);
					event.setCancelled(true);
				}
    }
}
