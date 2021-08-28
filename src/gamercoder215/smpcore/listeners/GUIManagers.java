package gamercoder215.smpcore.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.bosses.abilities.CreeperKingAbilities;
import gamercoder215.smpcore.bosses.abilities.EmeraldWarriorAbilities;
import gamercoder215.smpcore.bosses.abilities.TitanAbilities;
import gamercoder215.smpcore.bosses.abilities.ZombieKingAbilities;
import gamercoder215.smpcore.commands.Boss;
import gamercoder215.smpcore.utils.AdvancementMessages;
import gamercoder215.smpcore.utils.TradeInventories;
import gamercoder215.smpcore.utils.TradeParser;
import gamercoder215.smpcore.utils.entities.Witherman;
import gamercoder215.smpcore.utils.entities.arena_titans.AxeTitan;
import gamercoder215.smpcore.utils.entities.arena_titans.CrossbowTitan;
import gamercoder215.smpcore.utils.entities.arena_titans.FireTitan;
import gamercoder215.smpcore.utils.entities.arena_titans.KnockbackTitan;
import gamercoder215.smpcore.utils.entities.arena_titans.MagicalTitan;
import gamercoder215.smpcore.utils.entities.arena_titans.PotionTitan;
import gamercoder215.smpcore.utils.fetcher.ItemFetcher;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;
import net.minecraft.server.level.WorldServer;

public class GUIManagers implements Listener {
   protected Main plugin;

   public EntityType getEntityByName(String name) {
       for (EntityType type : EntityType.values()) {
           if(type.name().equalsIgnoreCase(name)) {
               return type;
           }
       }
       return null;
   }
   
   public static Inventory generateGUI(int size, String label) {
	   	Inventory inv = Bukkit.createInventory(null, size, label);
		ItemStack guiBG = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta guiBGMeta = guiBG.getItemMeta();
		guiBGMeta.setDisplayName(" ");
		guiBG.setItemMeta(guiBGMeta);
		
		if (size < 27) return inv;
		
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, guiBG);
		}
		
		for (int i = size - 9; i < size; i++) {
			inv.setItem(i, guiBG);
		}
		
		if (size >= 27) {
			inv.setItem(9, guiBG);
			inv.setItem(17, guiBG);
		}
		if (size >= 36) {
			inv.setItem(18, guiBG);
			inv.setItem(26, guiBG);
		}
		if (size >= 45) {
			inv.setItem(27, guiBG);
			inv.setItem(35, guiBG);
		}
		if (size == 54) {
			inv.setItem(36, guiBG);
			inv.setItem(44, guiBG);
		}
		return inv;
   }
   
   public static ItemStack getInventoryPlaceholder() {
		ItemStack guiBG = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta guiBGMeta = guiBG.getItemMeta();
		guiBGMeta.setDisplayName(" ");
		guiBG.setItemMeta(guiBGMeta);
		
		return guiBG;
   }
    
   
   public GUIManagers(Main plugin) {
      this.plugin = plugin;
      Bukkit.getPluginManager().registerEvents(this, plugin);
   }
   
   List<UUID> finderCooldown = new ArrayList<>();
   
   String notEnoughMats = ChatColor.RED + "You do not have the required items to spawn this boss!";
   @EventHandler
   public void onClick(InventoryClickEvent e) {
	  if (e.getWhoClicked() == null) return;
      Player p = (Player)e.getWhoClicked();
      WorldServer wServer = ((CraftWorld) p.getWorld()).getHandle();
      
      if (e.getClickedInventory() == null) return;
      Inventory inventory = e.getClickedInventory();
      
      if (!(inventory.contains(getInventoryPlaceholder()))) return;
      InventoryView inv = e.getView();
      if (inv.getTitle().contains("SMP Player Menu")) {
         e.setCancelled(true);
         ItemStack clickedItem = e.getCurrentItem();
         if (clickedItem.getType() == Material.BOOK) {
            Bukkit.dispatchCommand(e.getWhoClicked(), "recipes");
         } else if (clickedItem.getType() == Material.PLAYER_HEAD) {
            p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.UNDERLINE + p.getName() + "'s Statistics\n\n" + ChatColor.GOLD + p.getStatistic(Statistic.DAMAGE_DEALT) + ChatColor.GREEN + " Damage Dealt\n" + ChatColor.GOLD + p.getStatistic(Statistic.MOB_KILLS) + ChatColor.GREEN + " Kills\n" + ChatColor.GOLD + p.getStatistic(Statistic.DEATHS) + ChatColor.GREEN + " Deaths\n" + ChatColor.GOLD + (p.getStatistic(Statistic.LEAVE_GAME) + 1) + ChatColor.GREEN + " Times Played");
         } else if (clickedItem.getType().equals(Material.BONE)) {
        	 Bukkit.dispatchCommand(p, "boss");
         } else if (clickedItem.getType().equals(Material.EMERALD)) {
        	 Bukkit.dispatchCommand(p, "trades");
         } else if(clickedItem.getType().equals(Material.CRAFTING_TABLE)) {
        	 Bukkit.dispatchCommand(p, "craft");
         } else if (clickedItem.getType().equals(Material.ENDER_CHEST)) {
        	 Bukkit.dispatchCommand(p, "enderchest");
         } else if (clickedItem.getType().equals(Material.RED_BED)) {
        	 Bukkit.dispatchCommand(p, "bed");
         } else if (clickedItem.getType().equals(Material.ALLIUM)) {
        	 Bukkit.dispatchCommand(p, "abilities");
         }
      }	else if (inv.getTitle().contains("Wand Info")) {
    	  e.setCancelled(true);
      } else if (inv.getTitle().contains("SMP Bosses Menu") || inv.getTitle().contains("SMP T5 Bosses Menu")) {
         e.setCancelled(true);
         if (e.getCurrentItem() == null) return;
         if (!(e.getCurrentItem().hasItemMeta())) return;
         ItemStack clickedItem = e.getCurrentItem();
         if (clickedItem.getItemMeta().getDisplayName().contains("T5 Bosses")) {
        	 if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER) >= 50 && p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN) >= 1000) {
        		 Boss.openEliteBosses(p);
        	 } else {
        		 p.sendMessage(ChatColor.RED + Integer.toString(p.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER)) + " / 50 Withers and " + Integer.toString(p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN)) + " / 1,000 Enderman.");
        		 if (p.isOp()) {
        			 p.sendMessage(ChatColor.GREEN + "Operator Bypass.");
        			 Boss.openEliteBosses(p);
        		 }
        	 }
        	 
         }
         else if (clickedItem.getItemMeta().getDisplayName().contains("The Hogatar")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.PORKCHOP, 1), 128))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.PORKCHOP, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.PORKCHOP, 64));
        		 p.sendMessage(ChatColor.RED + "The Hogatar has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.RED + "Porkchop!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon hoglin %x% %y% %z% {Silent:0b,Glowing:1b,CustomNameVisible:1b,Health:500f,IsImmuneToZombification:1b,CannotBeHunted:1b,CustomName:'{\"text\":\"The Hogatar\",\"color\":\"red\",\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{Enchantments:[{id:\"minecraft:sharpness\",lvl:3s},{id:\"minecraft:fire_aspect\",lvl:3s}]}},{}],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:11b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:13b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.follow_range,Base:40},{Name:generic.knockback_resistance,Base:1},{Name:generic.movement_speed,Base:2},{Name:generic.attack_damage,Base:5},{Name:generic.armor_toughness,Base:1},{Name:generic.attack_knockback,Base:3}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Super Sniper")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ARROW, 1), 64))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
        		 p.sendMessage(ChatColor.DARK_PURPLE + "A Super Sniper has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_PURPLE + "Arrows!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon skeleton %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,FallFlying:1b,NoAI:0b,CanPickUpLoot:1b,Health:300f,CustomName:'{\"text\":\"Super Sniper\",\"color\":\"dark_purple\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Bow\",\"color\":\"dark_purple\",\"italic\":false}'},Unbreakable:1b,Enchantments:[{id:\"minecraft:unbreaking\",lvl:7s},{id:\"minecraft:power\",lvl:10s},{id:\"minecraft:punch\",lvl:3s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s},{id:\"minecraft:mending\",lvl:1s}]}},{}],ArmorItems:[{},{},{},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Sniper Cap\",\"color\":\"dark_purple\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:4s},{id:\"minecraft:fire_protection\",lvl:10s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:respiration\",lvl:3s},{id:\"minecraft:thorns\",lvl:4s},{id:\"minecraft:unbreaking\",lvl:7s}]}}],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:300},{Name:generic.knockback_resistance,Base:1}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Rotten Army")) {
        	 String displayName = clickedItem.getItemMeta().getDisplayName();
        	 if (displayName.contains("Private of Rotten Army")) {
            	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ROTTEN_FLESH, 1), 16))) {
            		 p.sendMessage(notEnoughMats);
            	 } else {
            		 p.getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 16));
            		 p.sendMessage(ChatColor.DARK_GREEN + "A Private of the Rotten Army has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_GREEN + "Rotten Flesh!");
            		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie %x% %y% %z% {Silent:0b,Glowing:1b,CustomNameVisible:1b,CanPickUpLoot:1b,ActiveEffects:[{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Health:50f,IsBaby:0b,CanBreakDoors:1b,CustomName:'{\"text\":\"Private of Rotten Army\",\"color\":\"dark_green\",\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Knife\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:2s},{id:\"minecraft:smite\",lvl:1s},{id:\"minecraft:sweeping\",lvl:1s}]}},{}],ArmorItems:[{},{},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Vest\",\"italic\":false}',color:0},Enchantments:[{id:\"minecraft:protection\",lvl:4s},{id:\"minecraft:blast_protection\",lvl:6s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:unbreaking\",lvl:9s},{id:\"minecraft:mending\",lvl:1s}]}},{id:\"minecraft:iron_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"War Helmet\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:3s},{id:\"minecraft:blast_protection\",lvl:6s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:unbreaking\",lvl:6s},{id:\"minecraft:mending\",lvl:1s}]}}],Attributes:[{Name:generic.max_health,Base:50},{Name:generic.knockback_resistance,Base:0.1},{Name:zombie.spawn_reinforcements,Base:0}]}"
            			 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                     plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                     
                     int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                     
                	 if (bossSummons == 1) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 5) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 15) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 30) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 55) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 70) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 125) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 }
            	 }
        	 } else if (displayName.contains("Specialist of Rotten Army")) {
            	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.BONE, 1), 16))) {
            		 p.sendMessage(notEnoughMats);
            	 } else {
            		 p.getInventory().removeItem(new ItemStack(Material.BONE, 16));
            		 p.sendMessage(ChatColor.GREEN + "A Specialist of the Rotten Army has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.GREEN + "Bones!");
            		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon stray %x% %y% %z% {Silent:0b,Glowing:1b,CustomNameVisible:1b,CanPickUpLoot:1b,Health:80f,CustomName:'{\"text\":\"Specialist of Rotten Army\",\"color\":\"green\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"Handgun\",\"italic\":false}'},Enchantments:[{id:\"minecraft:unbreaking\",lvl:6s},{id:\"minecraft:power\",lvl:5s},{id:\"minecraft:punch\",lvl:1s}]}},{}],ArmorItems:[{},{},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Vest\",\"italic\":false}',color:0},Enchantments:[{id:\"minecraft:protection\",lvl:4s},{id:\"minecraft:blast_protection\",lvl:6s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:unbreaking\",lvl:9s},{id:\"minecraft:mending\",lvl:1s}]}},{id:\"minecraft:iron_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"War Helmet\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:3s},{id:\"minecraft:blast_protection\",lvl:6s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:unbreaking\",lvl:6s},{id:\"minecraft:mending\",lvl:1s}]}}],ActiveEffects:[{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:80},{Name:generic.knockback_resistance,Base:0.15}]}"
            			 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                     plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                     
                     int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                     
                	 if (bossSummons == 1) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 5) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 15) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 30) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 55) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 70) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 125) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 }
            	 }
        	 } else if (displayName.contains("Seargant of Rotten Army")) {
            	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT, 1), 32))) {
            		 p.sendMessage(notEnoughMats);
            	 } else {
            		 p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 32));
            		 p.sendMessage(ChatColor.DARK_GREEN + "A Seargant of the Rotten Army has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_GREEN + "Gold!");
            		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombified_piglin %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,CanPickUpLoot:1b,Health:400f,CanBreakDoors:1b,AngerTime:200000,CustomName:'{\"text\":\"Seargant of Rotten Army\",\"color\":\"#095201\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:diamond_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Machette\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:8s},{id:\"minecraft:knockback\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:8s}]}},{}],ArmorItems:[{id:\"minecraft:chainmail_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"War Boots\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:5s},{id:\"minecraft:feather_falling\",lvl:6s},{id:\"minecraft:blast_protection\",lvl:10s}]}},{},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Bulletproof Vest\",\"italic\":false}',color:0},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:blast_protection\",lvl:10s},{id:\"minecraft:projectile_protection\",lvl:100s},{id:\"minecraft:unbreaking\",lvl:11s},{id:\"minecraft:mending\",lvl:1s}]}},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"War Helmet\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:5s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:8s},{id:\"minecraft:unbreaking\",lvl:7s},{id:\"minecraft:mending\",lvl:1s}]}}],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:5b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:400},{Name:generic.knockback_resistance,Base:0.25}]}"
            			 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                     plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                     
                     int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                     
                	 if (bossSummons == 1) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 5) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 15) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 30) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 55) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 70) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 125) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 }
            	 }
        	 } else if (displayName.contains("Air Support")) {
            	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.GHAST_TEAR, 1), 32))) {
            		 p.sendMessage(notEnoughMats);
            	 } else {
            		 p.getInventory().removeItem(new ItemStack(Material.GHAST_TEAR, 32));
            		 p.sendMessage(ChatColor.RED + "Rotten Army Air Support has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.RED + "Tears!");
            		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon ghast %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,FallFlying:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:400f,ExplosionPower:10,CustomName:'{\"text\":\"Rotten Army Air Support\",\"color\":\"red\",\"bold\":true,\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Unbreakable:1b,Enchantments:[{id:\"minecraft:projectile_protection\",lvl:32767s}]}},{}],ArmorDropChances:[0.085F,0.085F,0.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:11b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:13b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:400},{Name:generic.follow_range,Base:50},{Name:generic.knockback_resistance,Base:0.75},{Name:generic.attack_knockback,Base:5}]}"
            				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY() + 15)).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                     plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                     
                     int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                     
                	 if (bossSummons == 1) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 5) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 15) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 30) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 55) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 70) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 } else if (bossSummons == 125) {
                		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
                		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
                	 }
            	 }
             } else if (displayName.contains("Rotten Army Submarine")) {
            	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.PRISMARINE_SHARD, 1), 16))) {
            		 p.sendMessage(notEnoughMats);
            	 } else {
            		 if (!(p.isInWater())) {
            			 p.sendMessage(ChatColor.RED + "You need to be in water to summon this boss!");
            		 } else {
            			 p.getInventory().removeItem(new ItemStack(Material.PRISMARINE_SHARD, 16));
            		 	p.sendMessage(ChatColor.DARK_AQUA + "Rotten Army Submarine has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.RED + "Prismarine!");
            		 	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon guardian %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PortalCooldown:200000,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:195f,CustomName:'{\"text\":\"Rotten Army Submarine\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:prismarine_crystals\",Count:32b},{id:\"minecraft:sea_lantern\",Count:16b}],HandDropChances:[100.000F,50.000F],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Guardian Boots\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"This gives the wearer\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"immune to projectiles and\",\"color\":\"gray\",\"italic\":true}','[{\"text\":\"explosions, \",\"color\":\"gray\",\"italic\":true},{\"text\":\"while in water.\",\"color\":\"dark_aqua\",\"italic\":true}]']},Unbreakable:1b,Enchantments:[{id:\"minecraft:feather_falling\",lvl:10s},{id:\"minecraft:thorns\",lvl:6s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:5,Operation:0,UUID:[I;-1957300083,-1248772064,-1494549454,1837608863],Slot:\"feet\"}]}},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Unbreakable:1b,Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s}]}},{}],ArmorDropChances:[8.000F,0.085F,0.000F,0.085F],ActiveEffects:[{Id:2b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:30b,Amplifier:6b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:245},{Name:generic.follow_range,Base:300},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:10},{Name:generic.attack_knockback,Base:2}]}"
            		 			.replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY() + 15)).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
            	         plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
            	         
            	         int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
            	         
            	    	 if (bossSummons == 1) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 5) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 15) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 30) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 55) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 70) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 } else if (bossSummons == 125) {
            	    		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            	    		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	    	 }
            		 }
            	 }
             }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Spider Queen")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.STRING, 1), 128))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.STRING, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.STRING, 64));
        		 p.sendMessage(ChatColor.GRAY + "The Spider Queen has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.GRAY + "String!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon spider ~ ~ ~ {Silent:1b,Glowing:0b,CustomNameVisible:1b,Health:800f,CustomName:'{\"text\":\"Spider Queen\",\"color\":\"gray\",\"bold\":true,\"italic\":false}',ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:7b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:800},{Name:generic.follow_range,Base:150},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_knockback,Base:5}]}");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Witherman")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_PEARL), 64))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.ENDER_PEARL, 16), new ItemStack(Material.ENDER_PEARL, 16));
        		 
        		 p.sendMessage(ChatColor.DARK_AQUA + "A Witherman has spawned from " + ChatColor.GRAY + p.getName() + "'s " + ChatColor.DARK_AQUA + "Pearls!");
        		 Witherman witherman = new Witherman(p.getLocation());
        		 wServer.addEntity(witherman);
        		 
        		 
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Dimensional Guard")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.NETHERITE_SWORD, 1), 3))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.NETHERITE_SWORD, 1));
        		 p.getInventory().removeItem(new ItemStack(Material.NETHERITE_SWORD, 1));
        		 p.getInventory().removeItem(new ItemStack(Material.NETHERITE_SWORD, 1));
        		 p.sendMessage(ChatColor.AQUA + "A Dimensional Guard has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.AQUA + "Netherite!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon wither %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:1b,Health:3000f,Invul:60,Passengers:[{id:\"minecraft:ghast\",Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:2000f,CustomName:'{\"text\":\"Guard\\'s Energy Field\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:20s},{id:\"minecraft:knockback\",lvl:5s},{id:\"minecraft:fire_aspect\",lvl:4s}]}},{}],HandDropChances:[0.000F,0.085F],ArmorItems:[{},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:100s}]}},{}],ArmorDropChances:[0.085F,0.085F,0.000F,0.085F],ActiveEffects:[{Id:14b,Amplifier:255b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:2000},{Name:generic.attack_damage,Base:20},{Name:generic.attack_knockback,Base:0}]}],CustomName:'{\"text\":\"Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',ActiveEffects:[{Id:5b,Amplifier:9b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:3000},{Name:generic.follow_range,Base:200},{Name:generic.knockback_resistance,Base:1}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Blaze King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.BLAZE_ROD, 1), 128))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
        		 p.sendMessage(ChatColor.GOLD + "The Blaze King has spawned from " + ChatColor.YELLOW + p.getName() + "'s " + ChatColor.GOLD + "Blaze Rods!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon blaze %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,CustomName:'{\"text\":\"Blaze King\",\"color\":\"gold\",\"bold\":true,\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:projectile_protection\",lvl:32767s}]}},{}],ArmorDropChances:[0.085F,0.085F,0.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:11b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.follow_range,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:5},{Name:generic.attack_knockback,Base:3}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Warglin")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.PORKCHOP, 1), 64)) && !(p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND_SWORD, 1), 1))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.PORKCHOP, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.DIAMOND_SWORD, 1));
        		 p.sendMessage(ChatColor.DARK_RED + "A Warglin has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_RED + "Materials!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon piglin_brute %x% %y% %z% {PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:150f,IsImmuneToZombification:1b,CustomName:'{\"text\":\"Warglin\",\"color\":\"dark_red\",\"italic\":false}',HandItems:[{id:\"minecraft:diamond_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Warglin\\'s Weapon\",\"color\":\"blue\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:6s},{id:\"minecraft:smite\",lvl:7s},{id:\"minecraft:bane_of_arthropods\",lvl:8s},{id:\"minecraft:knockback\",lvl:2s},{id:\"minecraft:fire_aspect\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:4s}]}},{}],ArmorItems:[{},{},{},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Warglin Helmet\",\"color\":\"dark_blue\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:6s},{id:\"minecraft:fire_protection\",lvl:6s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:respiration\",lvl:4s}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,10.000F],ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:150},{Name:generic.follow_range,Base:50},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:2}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Snow Prince")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.PACKED_ICE, 1), 64)) && !(p.getInventory().containsAtLeast(new ItemStack(Material.SNOW_BLOCK, 1), 48))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.PACKED_ICE, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.SNOW_BLOCK, 48));
        		 p.sendMessage(ChatColor.WHITE + "The Snow Prince has spawned from " + ChatColor.YELLOW + p.getName() + "'s " + ChatColor.WHITE + "Materials!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon polar_bear %x% %y% %z% {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,NoAI:0b,Health:1000f,AngerTime:200000,Passengers:[{id:\"minecraft:snow_golem\",Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:1b,Health:3000f,Pumpkin:0b,CustomName:'{\"text\":\"Snow Prince\",\"color\":\"white\",\"bold\":true,\"italic\":false}',ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Icey Boots\",\"color\":\"aqua\",\"italic\":false,\"bold\":true}',Lore:['{\"text\":\"As cold as blue ice,\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"this is the armor piece\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"that frightens the\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"blaze family.\",\"color\":\"gray\",\"italic\":true}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:4s},{id:\"minecraft:frost_walker\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-995008823,-1850850912,-1484619506,-1757661022],Slot:\"feet\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:5,Operation:0,UUID:[I;-622147545,-184856608,-1553765133,-1332216925],Slot:\"feet\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:2,Operation:0,UUID:[I;-625475929,-1531885736,-1186117894,-1244499706],Slot:\"feet\"}]}},{},{},{}],ArmorDropChances:[9.000F,0.085F,0.085F,0.085F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:11b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:3000},{Name:generic.follow_range,Base:50},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25},{Name:generic.attack_knockback,Base:7}]}],CustomName:'{\"text\":\"Royal Ice Bear\",\"color\":\"#DBDBDB\",\"bold\":true,\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:5s}]}},{}],ArmorDropChances:[0.085F,0.085F,0.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:5b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.follow_range,Base:75},{Name:generic.knockback_resistance,Base:0.5},{Name:generic.attack_damage,Base:30},{Name:generic.attack_knockback,Base:3}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Magillager")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.BOW, 1), 2)) && !(p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD, 1), 16))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.BOW, 1));
        		 p.getInventory().removeItem(new ItemStack(Material.BOW, 1));
        		 p.getInventory().removeItem(new ItemStack(Material.EMERALD, 16));
        		 p.sendMessage(ChatColor.DARK_AQUA + "A Magillager has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_AQUA + "Materials!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon illusioner %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:1b,Health:150f,PatrolLeader:0b,Patrolling:0b,HasRaidGoal:0b,CanJoinRaid:1b,CustomName:'{\"text\":\"Magillager\",\"color\":\"dark_aqua\",\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'{\"text\":\"The Magic Bow\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:10s},{id:\"minecraft:bane_of_arthropods\",lvl:10s},{id:\"minecraft:knockback\",lvl:4s},{id:\"minecraft:looting\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:10s},{id:\"minecraft:power\",lvl:10s},{id:\"minecraft:punch\",lvl:4s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s},{id:\"minecraft:mending\",lvl:1s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:8,Operation:0,UUID:[I;-662801020,1482771992,-1077889840,-288011913],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:4,Operation:0,UUID:[I;814166081,-1747041816,-1911095690,1248500947],Slot:\"offhand\"}]}},{}],HandDropChances:[5.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:13b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:31b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:150},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:10},{Name:generic.attack_knockback,Base:2}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Netherite Skeleton")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ANCIENT_DEBRIS, 1), 1))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.ANCIENT_DEBRIS, 1));
        		 p.sendMessage(ChatColor.DARK_GRAY + "A Netherite Skeleton has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_GRAY + "Debris!");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon wither_skeleton %x% %y% %z% {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PortalCooldown:200000,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:20f,CustomName:'{\"text\":\"Netherite Skeleton\",\"color\":\"#542D05\",\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Sword\",\"italic\":false}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:10s},{id:\"minecraft:smite\",lvl:5s},{id:\"minecraft:bane_of_arthropods\",lvl:5s},{id:\"minecraft:knockback\",lvl:4s}]}},{id:\"minecraft:netherite_block\",Count:2b}],HandDropChances:[15.000F,5.000F],ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Boots\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Leggings\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Chestplate\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}},{id:\"minecraft:netherite_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Netherite Helmet\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s}]}}],ArmorDropChances:[0.085F,0.085F,10.000F,10.000F],ActiveEffects:[{Id:5b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:10b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:20}]}"
        				 .replace("%x%", Integer.toString(p.getLocation().getBlockX())).replace("%y%", Integer.toString(p.getLocation().getBlockY())).replace("%z%", Integer.toString(p.getLocation().getBlockZ())));
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Zombie King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ROTTEN_FLESH, 1), 64))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 64));
        		 ZombieKingAbilities.announceDialogue(ChatColor.BOLD + "Who has summoned the great one?");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        		 new BukkitRunnable() {
        			 public void run() {
        				 ZombieKingAbilities.announceDialogue("Oh, it's just another random player, " + ChatColor.GOLD + p.getName());
        				 new BukkitRunnable() {
        					 public void run() {
        						 ZombieKingAbilities.announceDialogue("Guess another boring fight that I " + ChatColor.UNDERLINE + "always" + ChatColor.RESET + "" + ChatColor.RED + " win at.");
        						 new BukkitRunnable() {
        							 public void run() {
        								 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie_horse ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:30f,Passengers:[{id:\"minecraft:zombie\",Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:10000f,CustomName:'{\"text\":\"Zombie King\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:diamond_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Confugiat\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Blessed by Aribus,\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"Forged by the Zombie King,\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"Enriched with Smite,\",\"color\":\"gray\",\"italic\":true}','{\"text\":\"This sword can do some\",\"color\":\"gray\",\"italic\":true}','[{\"text\":\"serious\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":true,\"underlined\":true},{\"text\":\" damage.\",\"color\":\"gray\",\"bold\":false,\"underlined\":false}]']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:25s},{id:\"minecraft:smite\",lvl:300s},{id:\"minecraft:fire_aspect\",lvl:10s},{id:\"minecraft:looting\",lvl:35s}]}},{}],HandDropChances:[0.500F,0.085F],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{Enchantments:[{id:\"minecraft:feather_falling\",lvl:32767s},{id:\"minecraft:thorns\",lvl:5s},{id:\"minecraft:depth_strider\",lvl:3s},{id:\"minecraft:frost_walker\",lvl:6s},{id:\"minecraft:soul_speed\",lvl:4s}]}},{},{id:\"minecraft:diamond_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:5s}]}},{id:\"minecraft:diamond_helmet\",Count:1b,tag:{Enchantments:[{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:8s},{id:\"minecraft:thorns\",lvl:5s}]}}],ArmorDropChances:[0.000F,0.085F,0.000F,0.000F],ActiveEffects:[{Id:10b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:1b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:6b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:0b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:6000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:30},{Name:generic.attack_knockback,Base:5},{Name:zombie.spawn_reinforcements,Base:0}]}],CustomName:'{\"text\":\"Zombified Horse\",\"color\":\"dark_green\",\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:iron_horse_armor\",Count:1b,tag:{display:{Name:'{\"text\":\"Zombie Horse Armor\",\"color\":\"dark_green\",\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:fire_protection\",lvl:5s}]}},{}],Attributes:[{Name:generic.max_health,Base:30},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_knockback,Base:2},{Name:horse.jump_strength,Base:5}],SaddleItem:{id:\"minecraft:saddle\",Count:1b}}");
        							 }
        						 }.runTaskLater(plugin, 60);
        					 }
        				 }.runTaskLater(plugin, 40);
        			 }
        		 }.runTaskLater(plugin, 40);
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Amethyst Zombie")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.AMETHYST_SHARD, 1), 32))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.AMETHYST_SHARD, 32));
        		 p.sendMessage(ChatColor.DARK_PURPLE + "An Amethyst Zombie has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_PURPLE + "Amethyst");
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:200f,CustomName:'{\"text\":\"Amethyst Zombie\",\"color\":\"light_purple\",\"italic\":false}',HandItems:[{id:\"minecraft:amethyst_shard\",Count:1b,tag:{display:{Name:'{\"text\":\"Super Amethyst Shard\",\"color\":\"dark_purple\",\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:sharpness\",lvl:15s},{id:\"minecraft:smite\",lvl:10s},{id:\"minecraft:bane_of_arthropods\",lvl:10s},{id:\"minecraft:looting\",lvl:10s},{id:\"minecraft:sweeping\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:5,Operation:0,UUID:[I;-909321703,1930970078,-2108694589,370468640],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:5,Operation:0,UUID:[I;114330334,-1037156303,-1388268589,-1427227550],Slot:\"offhand\"}]}},{}],HandDropChances:[0.063F,0.085F],ArmorItems:[{id:\"minecraft:leather_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Boots\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:4,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"feet\"}]}},{id:\"minecraft:leather_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Leggings\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:7,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"legs\"}]}},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Amethyst Chestplate\",\"color\":\"light_purple\",\"italic\":false}',color:12517548},HideFlags:65,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:blast_protection\",lvl:7s},{id:\"minecraft:projectile_protection\",lvl:7s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:8,Operation:0,UUID:[I;243299349,-30981221,-1852776914,208967007],Slot:\"chest\"}]}},{id:\"minecraft:amethyst_block\",Count:16b}],ArmorDropChances:[0.125F,125.000F,0.125F,1.000F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:3b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.movement_speed,Base:0.2},{Name:generic.attack_damage,Base:10},{Name:generic.armor,Base:15},{Name:generic.armor_toughness,Base:10},{Name:generic.attack_knockback,Base:4},{Name:zombie.spawn_reinforcements,Base:1}]}");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        	 }
         }
         // T5 Bosses Spawn
         else if (clickedItem.getItemMeta().getDisplayName().contains("Golden Shulker")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.GOLDEN_APPLE, 1), 32))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
	    		 p.getInventory().removeItem(new ItemStack(Material.GOLDEN_APPLE, 32));
	    		 p.sendMessage(ChatColor.GOLD + "A Golden Shulker has spawned from " + ChatColor.YELLOW + p.getName() + "'s " + ChatColor.GOLD + "Golden Apples!");
	    		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon shulker ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PortalCooldown:200000,CanPickUpLoot:0b,Health:25000f,Peek:3b,AttachFace:0b,Color:4b,CustomName:'{\"text\":\"Golden Shulker\",\"color\":\"gold\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:enchanted_golden_apple\",Count:32b},{}],HandDropChances:[0.500F,0.100F],ArmorItems:[{},{id:\"minecraft:gold_block\",Count:48b},{id:\"minecraft:golden_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Patina\\'s Golden Chestplate\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:8s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:30,Operation:0,UUID:[I;-1702195328,117457616,-2009818561,1416462628],Slot:\"chest\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:20,Operation:0,UUID:[I;-1474531694,-73121069,-2117049900,-1702337560],Slot:\"chest\"}]}},{id:\"minecraft:shulker_shell\",Count:31b}],ArmorDropChances:[1.000F,1.000F,0.050F,1.000F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:25000},{Name:generic.follow_range,Base:300},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:40},{Name:generic.attack_knockback,Base:10}]}");
	             plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
	             
	             int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
	             
	        	 if (bossSummons == 1) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 5) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 15) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 30) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 55) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 70) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 125) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Pillager King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ARROW, 1), 256)) && !p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD, 1), 48)) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
	    		 p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
	    		 p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
	    		 p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
	    		 p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
	    		 p.getInventory().removeItem(new ItemStack(Material.EMERALD, 48));
	    		 p.sendMessage(ChatColor.DARK_GRAY + "The Pillager King has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.DARK_GRAY + "Materials!");
	    		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon ravager ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:10000f,PatrolLeader:0b,Patrolling:0b,HasRaidGoal:0b,CanJoinRaid:1b,Passengers:[{id:\"minecraft:pillager\",Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:20000f,PatrolLeader:1b,Patrolling:0b,HasRaidGoal:0b,CanJoinRaid:1b,CustomName:'{\"text\":\"King Pillager\",\"color\":\"green\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:crossbow\",Count:1b,tag:{display:{Name:'{\"text\":\"Praedo\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:power\",lvl:50s},{id:\"minecraft:punch\",lvl:10s},{id:\"minecraft:multishot\",lvl:1s},{id:\"minecraft:piercing\",lvl:50s},{id:\"minecraft:quick_charge\",lvl:4s}]}},{id:\"minecraft:firework_rocket\",Count:64b,tag:{display:{Name:'{\"text\":\"Pillager Rocket\",\"color\":\"gray\",\"italic\":false}'},Fireworks:{Flight:1b,Explosions:[{Type:0,Trail:1b,Colors:[I;5855577],FadeColors:[I;16777215]}]}}}],HandDropChances:[1.000F,1.000F],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Ucide\\'s Boots\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:40s},{id:\"minecraft:feather_falling\",lvl:60s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:soul_speed\",lvl:15s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-219942479,1029456979,-1724980511,343220973],Slot:\"feet\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:20,Operation:0,UUID:[I;-388507174,-1860677750,-1491346897,500153693],Slot:\"feet\"}]}},{id:\"minecraft:emerald_block\",Count:48b},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{Enchantments:[{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:1327s}]}},{id:\"minecraft:iron_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Raid Crown\",\"bold\":true,\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:8s},{id:\"minecraft:respiration\",lvl:5s},{id:\"minecraft:aqua_affinity\",lvl:1s},{id:\"minecraft:thorns\",lvl:2s},{id:\"minecraft:unbreaking\",lvl:7s}]}}],ArmorDropChances:[0.500F,1.000F,0.000F,1.000F],Attributes:[{Name:generic.max_health,Base:20000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:30},{Name:generic.attack_knockback,Base:35}]}],CustomName:'{\"text\":\"Mutant Ravager\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:leather_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Raider Chestplate\",\"color\":\"gray\",\"bold\":true,\"italic\":false}',color:12895428},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:blast_protection\",lvl:6s},{id:\"minecraft:projectile_protection\",lvl:10s},{id:\"minecraft:thorns\",lvl:9s}]}},{}],ArmorDropChances:[0.085F,0.085F,0.500F,0.085F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:10000},{Name:generic.follow_range,Base:300},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25},{Name:generic.attack_knockback,Base:25}]}");
	             plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
	             
	             int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
	             
	        	 if (bossSummons == 1) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 5) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 15) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 30) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 55) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 70) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 125) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Phantom King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ELYTRA, 1), 1))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
	    		 p.getInventory().removeItem(new ItemStack(Material.ELYTRA, 1));
	    		 p.sendMessage(ChatColor.GRAY + "The Phantom King has spawned from " + ChatColor.GOLD + p.getName() + "'s " + ChatColor.GRAY + "Elytra!");
	    		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon phantom ~ ~10 ~ {Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:15000f,Size:10,CustomName:'{\"text\":\"Phantom King\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:phantom_membrane\",Count:48b},{id:\"minecraft:potion\",Count:1b,tag:{display:{Name:'{\"text\":\"Phantom Potion\",\"color\":\"gray\",\"italic\":false}'},CustomPotionEffects:[{Id:1b,Amplifier:2b,Duration:6000,ShowParticles:1b},{Id:10b,Amplifier:2b,Duration:6000,ShowParticles:1b},{Id:14b,Amplifier:3b,Duration:6000,ShowParticles:1b}],Potion:\"minecraft:empty\",CustomPotionColor:3684408}}],HandDropChances:[1.000F,1.000F],ArmorItems:[{},{id:\"minecraft:iron_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Phantom Leggings\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:45s},{id:\"minecraft:fire_protection\",lvl:2767s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:10,Operation:0,UUID:[I;815077853,-1008122040,-1285646117,-1674774986],Slot:\"legs\"}]}},{id:\"minecraft:elytra\",Count:1b,tag:{display:{Name:'{\"text\":\"Tenuem\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:35s},{id:\"minecraft:fire_protection\",lvl:45s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:15s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:20,Operation:0,UUID:[I;-1874920176,-497530890,-1122040731,-1024443108],Slot:\"chest\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;-595386506,2041791217,-2139681147,600308124],Slot:\"chest\"}]}},{}],ArmorDropChances:[0.085F,1.000F,1.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25},{Name:generic.attack_knockback,Base:4}]}");
	             plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
	             
	             int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
	             
	        	 if (bossSummons == 1) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 5) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 15) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 30) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 55) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 70) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (bossSummons == 125) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 }
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Emerald Warrior")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD, 1), 48))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.EMERALD, 48));
        		 EmeraldWarriorAbilities.announceDialogue(ChatColor.BOLD + "I smell emeralds.");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        		 new BukkitRunnable() {
        			 public void run() {
        				 EmeraldWarriorAbilities.announceDialogue(ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN  + " has them!");
        				 new BukkitRunnable() {
        					 public void run() {
        						 EmeraldWarriorAbilities.announceDialogue(ChatColor.BOLD + "Time to die.");
        						 new BukkitRunnable() {
        							 public void run() {
        								 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon giant ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:25000f,Passengers:[{id:\"minecraft:zombie\",Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:30000f,IsBaby:0b,CanBreakDoors:1b,CustomName:'{\"text\":\"Emerald Warrior\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:emerald\",Count:1b,tag:{display:{Name:'{\"text\":\"Zmaragdus Ope\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:sharpness\",lvl:75s},{id:\"minecraft:smite\",lvl:25s},{id:\"minecraft:bane_of_arthropods\",lvl:25s},{id:\"minecraft:knockback\",lvl:5s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:3,Operation:2,UUID:[I;1144361164,1451180372,-1627504685,574970143],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:3,Operation:2,UUID:[I;-5635101,779111634,-1515982038,1391407138],Slot:\"offhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:2,UUID:[I;-246986267,-1758248927,-2126760814,9017868],Slot:\"mainhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:2,UUID:[I;1533619469,282347120,-1226450610,-870234697],Slot:\"offhand\"}]}},{id:\"minecraft:emerald\",Count:1b,tag:{display:{Name:'{\"text\":\"Emerald Auctus\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorItems:[{id:\"minecraft:iron_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Paribus\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:feather_falling\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:25s},{id:\"minecraft:depth_strider\",lvl:3s},{id:\"minecraft:frost_walker\",lvl:10s},{id:\"minecraft:soul_speed\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-649645371,35865325,-1090060731,-1002859093],Slot:\"feet\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:10,Operation:0,UUID:[I;2020100166,468928600,-2085729551,-1126178794],Slot:\"feet\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:-0.25,Operation:2,UUID:[I;2029265067,19024289,-1649702269,315834866],Slot:\"feet\"}]}},{},{id:\"minecraft:diamond_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Emerald Enriched Chestplate\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:0.5,Operation:0,UUID:[I;1404361387,1368540080,-1711745849,8691705],Slot:\"chest\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:25,Operation:0,UUID:[I;992277292,1222787288,-1384841892,-298741186],Slot:\"chest\"}]}},{id:\"minecraft:leather_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Emerald Warrior Helmet\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',color:41237},HideFlags:65,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:200s},{id:\"minecraft:fire_protection\",lvl:32767s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:50s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:2,Operation:2,UUID:[I;-802250330,931416892,-1184350557,-2085760946],Slot:\"head\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:2,Operation:2,UUID:[I;1570551139,1949255698,-2082253355,127024897],Slot:\"head\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.5,Operation:2,UUID:[I;2104489812,-591967322,-1315324269,-2047885181],Slot:\"head\"}]}}],ArmorDropChances:[0.050F,0.085F,0.030F,1.000F],ActiveEffects:[{Id:1b,Amplifier:0b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:26b,Amplifier:24b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:4b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:30000},{Name:generic.follow_range,Base:500},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:65},{Name:generic.attack_knockback,Base:10},{Name:zombie.spawn_reinforcements,Base:100}]}],CustomName:'{\"text\":\"Warrior\\'s Titan\",\"color\":\"green\",\"bold\":true,\"italic\":false}',ArmorItems:[{},{},{id:\"minecraft:diamond_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Emerald Enriched Chestplate\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:0.5,Operation:0,UUID:[I;1404361387,1368540080,-1711745849,8691705],Slot:\"chest\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:25,Operation:0,UUID:[I;992277292,1222787288,-1384841892,-298741186],Slot:\"chest\"}]}},{id:\"minecraft:emerald_block\",Count:64b}],ArmorDropChances:[0.085F,0.085F,0.085F,1.000F],ActiveEffects:[{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:25000},{Name:generic.follow_range,Base:500},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_knockback,Base:15}]}");
        							 }
        						 }.runTaskLater(plugin, 60);
        					 }
        				 }.runTaskLater(plugin, 40);
        			 }
        		 }.runTaskLater(plugin, 40);
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Creeper King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.TNT, 1), 64))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.TNT, 64));
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        		 CreeperKingAbilities.announceDialogue(ChatColor.BOLD + "Aku weruh ana pemain nyoba ngasilake aku.");
        		 new BukkitRunnable() {
        			 public void run() {
        				 CreeperKingAbilities.announceDialogue(ChatColor.GOLD + p.getName() + ChatColor.GREEN  + " arep njeblug ing jagad lara.");
        				 new BukkitRunnable() {
        					 public void run() {
        						 CreeperKingAbilities.announceDialogue("Uga, kudos kanggo sampeyan kanggo narjamahake.");
        						 new BukkitRunnable() {
        							 public void run() {
        								 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon ghast ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,Health:10000f,ExplosionPower:7,Passengers:[{id:\"minecraft:creeper\",Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:40000f,powered:1b,ExplosionRadius:10b,CustomName:'{\"text\":\"Creeper King\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Crepitus\\'s Sword\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'}}},{id:\"minecraft:orange_dye\",Count:1b,tag:{display:{Name:'{\"text\":\"Golden Gunpowder\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],HandDropChances:[0.010F,1.000F],ArmorItems:[{},{},{},{id:'minecraft:leather_helmet',Count:1b,tag:{display:{Name:'{\"text\":\"Creeper Crown\",\"color\":\"green\",\"bold\":true,\"italic\":false}',Lore:[\"{\\\"text\\\":\\\"Sneak to explode.\\\",\\\"color\\\":\\\"gray\\\",\\\"italic\\\":false}\"],color:51476},HideFlags:1,Unbreakable:1b,Enchantments:[{id:'minecraft:fire_protection',lvl:32767s},{id:'minecraft:blast_protection',lvl:32767s},{id:'minecraft:respiration',lvl:32767s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:10,Operation:0,UUID:[I;155126535,-112639539,-1991033893,-1984088194],Slot:'head'},{AttributeName:'generic.armor_toughness',Name:'generic.armor_toughness',Amount:2,Operation:2,UUID:[I;-945970645,1329876341,-2018248538,538386748],Slot:'head'},{AttributeName:'generic.max_health',Name:'generic.max_health',Amount:-0.25,Operation:0,UUID:[I;1655601796,42814757,-1325670691,-167250676],Slot:'head'}]}}],ArmorDropChances:[0.085F,0.085F,0.085F,0.100F],Attributes:[{Name:generic.max_health,Base:40000},{Name:generic.knockback_resistance,Base:1}]}],CustomName:'{\"text\":\"Royal Ghast\",\"color\":\"green\",\"italic\":false}',HandItems:[{id:\"minecraft:air\",Count:1b},{}],ArmorItems:[{},{},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Milpuko\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:projectile_protection\",lvl:5s},{id:\"minecraft:thorns\",lvl:5s}],AttributeModifiers:[{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:10,Operation:0,UUID:[I;-368925575,-301513808,-1573610956,26497177],Slot:\"chest\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.75,Operation:2,UUID:[I;-1028458552,-1530246215,-1643352042,-486583264],Slot:\"chest\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-1081172476,377046109,-1624282245,-1560377532],Slot:\"chest\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:0,UUID:[I;-1863814995,2061190514,-1490516814,-2096508824],Slot:\"chest\"}]}},{}],ArmorDropChances:[0.085F,0.085F,1.000F,0.085F],Attributes:[{Name:generic.max_health,Base:10000},{Name:generic.knockback_resistance,Base:1}]}");
        							 }
        						 }.runTaskLater(plugin, 60);
        					 }
        				 }.runTaskLater(plugin, 40);
        			 }
        		 }.runTaskLater(plugin, 40);
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Aranea")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.COBWEB, 1), 64))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.COBWEB, 64));
        		 p.sendMessage(ChatColor.DARK_RED + "You summoned an Araena!");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon spider ~ ~5 ~ {Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:60000f,CustomName:'{\"text\":\"Aranea\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false}',ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Saliant Boots\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Sneak to launch.\",\"color\":\"gray\",\"italic\":false}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:50s},{id:\"minecraft:fire_protection\",lvl:327667s},{id:\"minecraft:feather_falling\",lvl:200s},{id:\"minecraft:projectile_protection\",lvl:32767s},{id:\"minecraft:thorns\",lvl:25s},{id:\"minecraft:depth_strider\",lvl:200s},{id:\"minecraft:soul_speed\",lvl:25s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.5,Operation:2,UUID:[I;609111164,2133803578,-1988813555,-642493658],Slot:\"feet\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:3,Operation:2,UUID:[I;834723943,-37729396,-1559490516,559751520],Slot:\"feet\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:2.5,Operation:2,UUID:[I;724814354,2133543774,-2005980464,9588628],Slot:\"feet\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-405599343,90326049,-1906695391,2016605205],Slot:\"feet\"}]}},{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'[{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Chordate Sword \",\"color\":\"red\",\"bold\":true,\"obfuscated\":false},{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:200s},{id:\"minecraft:smite\",lvl:212s},{id:\"minecraft:bane_of_arthropods\",lvl:300s},{id:\"minecraft:knockback\",lvl:50s},{id:\"minecraft:fire_aspect\",lvl:100s},{id:\"minecraft:looting\",lvl:50s},{id:\"minecraft:sweeping\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;-1768090679,-537244671,-1834606829,1709889286],Slot:\"mainhand\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:1,Operation:0,UUID:[I;489399515,-787067520,-1632339604,-981042791],Slot:\"offhand\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.75,Operation:2,UUID:[I;-1935964939,-656651427,-1208389519,1307265224],Slot:\"offhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:5,Operation:0,UUID:[I;-243678660,1556696172,-1728120791,-383891467],Slot:\"mainhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:5,Operation:0,UUID:[I;1531833587,-1051047238,-2090606401,1506779688],Slot:\"offhand\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:4,Operation:2,UUID:[I;-715696331,364462409,-2097099902,228354340],Slot:\"mainhand\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:4,Operation:2,UUID:[I;-1685380959,-1421391710,-1728677529,1537833210],Slot:\"offhand\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.8,Operation:2,UUID:[I;-1113967130,-619165963,-1128713699,-1562641481],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:35,Operation:0,UUID:[I;-1248227078,-788837659,-1390430414,-828060146],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:25,Operation:0,UUID:[I;1673692688,852771497,-1099068021,-521515566],Slot:\"offhand\"}]}},{},{id:\"minecraft:string\",Count:1b,tag:{display:{Name:'{\"text\":\"Spider Sericum\",\"color\":\"gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorDropChances:[0.050F,0.006F,0.085F,1.000F],ActiveEffects:[{Id:1b,Amplifier:1b,Duration:20000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:60000},{Name:generic.follow_range,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:35},{Name:generic.attack_knockback,Base:10}]}");
        	 }
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Iron King")) {
        	 if (!(p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT, 1), 128))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
        		 p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 64));
        		 p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 64));
        		 p.sendMessage(ChatColor.WHITE + "You summoned The Iron King!");
                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
                 
                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
                 
            	 if (bossSummons == 1) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 5) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 15) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 30) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 55) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 70) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 } else if (bossSummons == 125) {
            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
            	 }
        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon iron_golem ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:65000f,AngerTime:200000,CustomName:'{\"text\":\"Iron King\",\"color\":\"white\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Aspect of Ferrum\",\"color\":\"gray\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:125s},{id:\"minecraft:smite\",lvl:125s},{id:\"minecraft:bane_of_arthropods\",lvl:100s},{id:\"minecraft:knockback\",lvl:150s},{id:\"minecraft:looting\",lvl:75s},{id:\"minecraft:sweeping\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:5,Operation:0,UUID:[I;-1528602596,-1832563885,-1396051837,860448903],Slot:\"mainhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:0,UUID:[I;-285710538,-1569963301,-2141991073,-1875654620],Slot:\"mainhand\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:8.5,Operation:2,UUID:[I;1948466226,1723417931,-1277386117,-2024414490],Slot:\"mainhand\"}]}},{}],HandDropChances:[0.002F,0.085F],ArmorItems:[{id:\"minecraft:iron_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Ferrum Boots\",\"color\":\"white\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Said to have been forged from\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"the first iron.\",\"color\":\"gray\",\"italic\":false}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:175s},{id:\"minecraft:fire_protection\",lvl:150s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:32767s},{id:\"minecraft:thorns\",lvl:75s}],AttributeModifiers:[{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:40,Operation:0,UUID:[I;1773858956,-544977435,-2103449208,-1277011057],Slot:\"feet\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:3,Operation:0,UUID:[I;-2017013766,677266787,-2074891791,367146067],Slot:\"feet\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:4,Operation:2,UUID:[I;-1158377462,-1797960638,-1405407034,-1204846758],Slot:\"feet\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:0.1,Operation:2,UUID:[I;-867629630,-1342878673,-1207444390,1765295083],Slot:\"feet\"}]}},{id:\"minecraft:iron_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Ferrum Leggings\",\"color\":\"white\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Said to have been forged from\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"the first iron.\",\"color\":\"gray\",\"italic\":false}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:175s},{id:\"minecraft:fire_protection\",lvl:150s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:32767s},{id:\"minecraft:thorns\",lvl:75s}],AttributeModifiers:[{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:70,Operation:0,UUID:[I;1773858956,-544977435,-2103449208,-1277011057],Slot:\"legs\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:3,Operation:0,UUID:[I;-2017013766,677266787,-2074891791,367146067],Slot:\"legs\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:4,Operation:2,UUID:[I;-1158377462,-1797960638,-1405407034,-1204846758],Slot:\"legs\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:0.1,Operation:2,UUID:[I;-867629630,-1342878673,-1207444390,1765295083],Slot:\"legs\"}]}},{id:\"minecraft:iron_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Ferrum Chestplate\",\"color\":\"white\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Said to have been forged from\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"the first iron.\",\"color\":\"gray\",\"italic\":false}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:175s},{id:\"minecraft:fire_protection\",lvl:150s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:32767s},{id:\"minecraft:thorns\",lvl:75s}],AttributeModifiers:[{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:80,Operation:0,UUID:[I;1773858956,-544977435,-2103449208,-1277011057],Slot:\"chest\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:3,Operation:0,UUID:[I;-2017013766,677266787,-2074891791,367146067],Slot:\"chest\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:4,Operation:2,UUID:[I;-1158377462,-1797960638,-1405407034,-1204846758],Slot:\"chest\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:0.1,Operation:2,UUID:[I;-867629630,-1342878673,-1207444390,1765295083],Slot:\"chest\"}]}},{id:\"minecraft:iron_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Ferrum Helmet\",\"color\":\"white\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\"Said to have been forged from\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"the first iron.\",\"color\":\"gray\",\"italic\":false}']},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:175s},{id:\"minecraft:fire_protection\",lvl:150s},{id:\"minecraft:blast_protection\",lvl:32767s},{id:\"minecraft:respiration\",lvl:32767s},{id:\"minecraft:aqua_affinity\",lvl:1s},{id:\"minecraft:thorns\",lvl:75s}],AttributeModifiers:[{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:50,Operation:0,UUID:[I;1773858956,-544977435,-2103449208,-1277011057],Slot:\"head\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:3,Operation:0,UUID:[I;-2017013766,677266787,-2074891791,367146067],Slot:\"head\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:4,Operation:2,UUID:[I;-1158377462,-1797960638,-1405407034,-1204846758],Slot:\"head\"},{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:0.1,Operation:2,UUID:[I;-867629630,-1342878673,-1207444390,1765295083],Slot:\"head\"}]}}],ArmorDropChances:[0.250F,0.250F,0.025F,0.250F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:9b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:65000},{Name:generic.follow_range,Base:2500},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:75},{Name:generic.armor_toughness,Base:40},{Name:generic.attack_knockback,Base:80}]}");
        	 }
        	 
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Dimensional Dragon")) {
        	 if (p.getWorld().getName().contains("world_the_end")) {
	        	 if (!(p.getInventory().containsAtLeast(ItemFetcher.getBlackHoleCandle(), 1))) {
	        		 p.sendMessage(notEnoughMats);
	        	 } else {
	        		 p.getInventory().removeItem(ItemFetcher.getBlackHoleCandle());
	        		 p.sendMessage(ChatColor.DARK_AQUA + "You summoned the Dimensional Dragon!");
	                 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("boss_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons") + 1);
	                 
	                 int bossSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("boss_summons");
	                 
	            	 if (bossSummons == 1) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(1, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 5) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(2, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 15) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(3, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 30) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(4, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 55) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(5, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 70) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(6, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 } else if (bossSummons == 125) {
	            		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getBossSpawner(7, true));
	            		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	            	 }
	        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon ender_dragon ~ ~10 ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:125000f,DragonPhase:4,Passengers:[{id:\"minecraft:wither\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:15000f,Invul:0,Passengers:[{id:\"minecraft:wither\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:15000f,Invul:0,Passengers:[{id:\"minecraft:wither\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:15000f,Invul:0,Passengers:[{id:\"minecraft:wither\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:15000f,Invul:0,Passengers:[{id:\"minecraft:wither\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:15000f,Invul:0,CustomName:'{\"text\":\"Super Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:'minecraft:netherite_axe',Count:1b,tag:{display:{Name:'{\"text\":\"Dimensional Axe\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:'minecraft:sharpness',lvl:150s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:14.0d,Operation:2,UUID:[I; -286435319, -30653429, -1427532586, -1384093852],Slot:'mainhand'}]}},{}],HandDropChances:[0.125F,0.085F],ActiveEffects:[{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:15}]}],CustomName:'{\"text\":\"Super Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:'minecraft:netherite_axe',Count:1b,tag:{display:{Name:'{\"text\":\"Dimensional Axe\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:'minecraft:sharpness',lvl:150s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:14.0d,Operation:2,UUID:[I; -286435319, -30653429, -1427532586, -1384093852],Slot:'mainhand'}]}},{}],HandDropChances:[0.125F,0.085F],ActiveEffects:[{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:15}]}],CustomName:'{\"text\":\"Super Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:'minecraft:netherite_axe',Count:1b,tag:{display:{Name:'{\"text\":\"Dimensional Axe\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:'minecraft:sharpness',lvl:150s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:14.0d,Operation:2,UUID:[I; -286435319, -30653429, -1427532586, -1384093852],Slot:'mainhand'}]}},{}],HandDropChances:[0.125F,0.085F],ActiveEffects:[{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:15}]}],CustomName:'{\"text\":\"Super Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:'minecraft:netherite_axe',Count:1b,tag:{display:{Name:'{\"text\":\"Dimensional Axe\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:'minecraft:sharpness',lvl:150s}],AttributeModifiers:[{AttributeName:'generic.attack_damage',Name:'generic.attack_damage',Amount:14.0d,Operation:2,UUID:[I; -286435319, -30653429, -1427532586, -1384093852],Slot:'mainhand'}]}},{}],HandDropChances:[0.125F,0.085F],ActiveEffects:[{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:15}]}],CustomName:'{\"text\":\"Super Dimensional Guard\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:netherite_axe\",Count:1b,tag:{display:{Name:'{\"text\":\"Dimensional Axe\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:\"minecraft:sharpness\",lvl:150s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:14.0d,Operation:2,UUID:[I; -286435319, -30653429, -1427532586, -1384093852],Slot:\"mainhand\"}]}},{}],HandDropChances:[0.125F,0.085F],ActiveEffects:[{Id:5b,Amplifier:2b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:15000},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:15}]}],CustomName:'{\"text\":\"Dimensional Dragon\",\"color\":\"dark_aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:netherite_hoe\",Count:1b,tag:{display:{Name:'{\"text\":\"Black Hole Scythe\",\"color\":\"#1C1C1C\",\"bold\":true,\"italic\":false}',Lore:['{\"text\":\" \"}','{\"text\":\"Long ago, a Black Hole appeared\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"in the End Dimension. Its \",\"color\":\"gray\",\"italic\":false}','{\"text\":\"current dragon sacrified\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"itself to save the enderman.\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"Some say this scythe has the\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"power of that dragon.\",\"color\":\"gray\",\"italic\":false}','{\"text\":\" \"}']},HideFlags:1,Unbreakable:1b,Damage:0,Enchantments:[{id:\"minecraft:sharpness\",lvl:255s},{id:\"minecraft:smite\",lvl:255s},{id:\"minecraft:bane_of_arthropods\",lvl:255s},{id:\"minecraft:fire_aspect\",lvl:200s},{id:\"minecraft:looting\",lvl:100s},{id:\"minecraft:sweeping\",lvl:175s},{id:\"minecraft:efficiency\",lvl:255s}],AttributeModifiers:[{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:15,Operation:0,UUID:[I; 944778035, -758298191, -1249296902, 1293301064],Slot:\"mainhand\"},{AttributeName:\"generic.knockback_resistance\",Name:\"generic.knockback_resistance\",Amount:15,Operation:0,UUID:[I;208500265,584338921,-1374155541,2027248460],Slot:\"offhand\"},{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:10,Operation:2,UUID:[I;-265313278,-450543599,-1354273966,-1370465973],Slot:\"mainhand\"},{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:10,Operation:2,UUID:[I;-582653560,265111622,-1428072302,-1878009315],Slot:\"offhand\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:10,Operation:2,UUID:[I;-557753486,1931692043,-1587216230,315775090],Slot:\"mainhand\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:10,Operation:2,UUID:[I;618309132,1113869125,-1189859484,1133300360],Slot:\"offhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:100,Operation:0,UUID:[I;-1128342307,1060718476,-1308479482,-1902424118],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:100,Operation:0,UUID:[I;1263415825,627723872,-1997439225,-636133545],Slot:\"offhand\"}]}},{id:\"minecraft:coal\",Count:1b,tag:{display:{Name:'{\"text\":\"Essence of Black Hole\",\"color\":\"black\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],HandDropChances:[0.050F,1.000F],ArmorItems:[{},{id:\"minecraft:slime_ball\",Count:4b,tag:{display:{Name:'[{\"text\":\"||\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Essence of MC \",\"obfuscated\":false},{\"text\":\"||\",\"obfuscated\":true}]'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}},{id:\"minecraft:netherite_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Patina\\'s Absorber Chestplate\",\"color\":\"light_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:255s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:blast_protection\",lvl:255s},{id:\"minecraft:projectile_protection\",lvl:255s},{id:\"minecraft:thorns\",lvl:75s},{id:\"minecraft:mending\",lvl:8s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:200,Operation:0,UUID:[I;1667594133,-1804187562,-1691839734,1161903829],Slot:\"chest\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:150,Operation:0,UUID:[I;-50716090,1662667759,-2039787423,-1230407297],Slot:\"chest\"}]}},{}],ArmorDropChances:[0.085F,1.000F,1.000F,0.085F],ActiveEffects:[{Id:1b,Amplifier:3b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:4b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:125000},{Name:generic.follow_range,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.movement_speed,Base:0.5},{Name:generic.armor,Base:0.1},{Name:generic.armor_toughness,Base:0.1},{Name:generic.attack_knockback,Base:45}]}");
	        	 }
        	 } else p.sendMessage(ChatColor.AQUA + "The Dimensional Dragon is biased towards the end dimension...");
         } else if (clickedItem.getItemMeta().getDisplayName().contains("Titan")) {
        	 if (!(p.getInventory().containsAtLeast(Boss.getTitanSummoner(), 1))) {
        		 p.sendMessage(notEnoughMats);
        	 } else {
	        	 String itemName = clickedItem.getItemMeta().getDisplayName();
	        	 
	        	 if (itemName.contains("Damage Titan")) {
	        		 p.getInventory().removeItem(Boss.getTitanSummoner());
	        		 TitanAbilities.announceDialogue("The " + ChatColor.DARK_RED + "Damage Titan" + ChatColor.GOLD + " has been summoned by " + ChatColor.YELLOW + p.getName());
	        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon wither ~ ~5 ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:50f,Invul:100,CustomName:'{\"text\":\"Damage Titan\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:brick\",Count:1b,tag:{display:{Name:'{\"text\":\"Damage Ingot\",\"color\":\"red\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{},{},{id:\"minecraft:wither_skeleton_skull\",Count:64b},{id:\"minecraft:sunflower\",Count:1b,tag:{display:{Name:'[{\"text\":\"||\",\"color\":\"red\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Enchanted Damage Coin \",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"||\",\"color\":\"red\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorDropChances:[0.085F,0.085F,1.000F,0.100F],ActiveEffects:[{Id:5b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:50},{Name:generic.follow_range,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:45},{Name:generic.attack_knockback,Base:10}]}");
	        	 } else if (itemName.contains("End Titan")) {
	        		 if (p.getWorld().getName().contains("world_the_end")) {
	        			 p.getInventory().removeItem(Boss.getTitanSummoner());
		        		 TitanAbilities.announceDialogue("The " + ChatColor.DARK_PURPLE + "End Titan" + ChatColor.GOLD + " has been summoned by " + ChatColor.YELLOW + p.getName());
		        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon ender_dragon ~ ~10 ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,Health:90f,DragonPhase:4,CustomName:'{\"text\":\"Ender Titan\",\"color\":\"light_purple\",\"bold\":true,\"italic\":false}',HandItems:[{},{id:\"minecraft:black_dye\",Count:1b,tag:{display:{Name:'{\"text\":\"Essence of Consummationem\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],HandDropChances:[0.085F,1.000F],ArmorItems:[{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Artifact of Flight\",\"color\":\"gold\",\"bold\":true,\"italic\":false}',Lore:['[{\"text\":\"Craft with a \",\"color\":\"gray\",\"italic\":false},{\"text\":\"Tenuem\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}]','{\"text\":\"to make an elytra\",\"color\":\"gray\",\"italic\":false}','{\"text\":\"that can simulate flight.\",\"color\":\"gray\",\"italic\":false}']},SkullOwner:{Id:[I;-1917329199,1493911248,-2090273876,-1830921933],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ0MmJiMzM5MWIzOTY0ZGUyNjZkOWJlYmU3Y2NmN2VmM2MzOTA2MjZlYjdhODQ1NjEyYWQ5MGYzZmE0MmMxMiJ9fX0=\"}]}}}},{id:\"minecraft:end_rod\",Count:1b,tag:{display:{Name:'{\"text\":\"Titan Spike\",\"color\":\"light_purple\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}},{id:\"minecraft:pink_dye\",Count:1b,tag:{display:{Name:'[{\"text\":\"()()\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Enchanted End Essence \",\"color\":\"light_purple\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"()()\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}},{}],ArmorDropChances:[1.000F,1.000F,1.000F,0.085F],Attributes:[{Name:generic.max_health,Base:90},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:65},{Name:generic.attack_knockback,Base:55}]}");
	        		 } else p.sendMessage(ChatColor.RED + "You need to be in the end to spawn this boss!");
	        	 } else if (itemName.contains("Nether Titan")) {
	        		 if (p.getWorld().getName().contains("world_nether")) {
	        			 p.getInventory().removeItem(Boss.getTitanSummoner());
	        			 TitanAbilities.announceDialogue("The " + ChatColor.RED + "Nether Titan" + ChatColor.GOLD + " has been summoned by " + ChatColor.YELLOW + p.getName());
	        			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon magma_cube ~ ~ ~ {Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:0b,Health:200f,Size:5,wasOnGround:1b,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,Passengers:[{id:\"minecraft:blaze\",Silent:1b,Invulnerable:0b,Glowing:0b,CustomNameVisible:1b,PersistenceRequired:0b,NoAI:0b,CanPickUpLoot:1b,Health:1000f,CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Inferno Blaze\",\"color\":\"gold\",\"italic\":false}',Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:25}]}],CustomName:'{\"text\":\"Nether Titan\",\"color\":\"red\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:netherite_sword\",Count:1b,tag:{display:{Name:'[{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Inferno Arescent \",\"color\":\"red\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"()()()\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:250s},{id:\"minecraft:smite\",lvl:200s},{id:\"minecraft:bane_of_arthropods\",lvl:175s},{id:\"minecraft:fire_aspect\",lvl:25s},{id:\"minecraft:looting\",lvl:75s},{id:\"minecraft:sweeping\",lvl:250s}],AttributeModifiers:[{AttributeName:\"generic.max_health\",Name:\"generic.max_health\",Amount:-0.8,Operation:2,UUID:[I;508656979,-1395504158,-1778247833,-1280057405],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:11,Operation:2,UUID:[I;754816496,-144684281,-1084403386,-2053114714],Slot:\"mainhand\"},{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:2,Operation:0,UUID:[I;-1025514587,-1746910995,-1599264703,282817427],Slot:\"mainhand\"}]}},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{id:\"minecraft:diamond_boots\",Count:1b,tag:{Unbreakable:1b}},{},{},{id:\"minecraft:apple\",Count:1b,tag:{display:{Name:'{\"text\":\"Inferno Apple\",\"color\":\"red\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorDropChances:[0.000F,0.085F,0.085F,1.000F],ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:5b,Amplifier:14b,Duration:200000,ShowParticles:0b},{Id:8b,Amplifier:5b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:200},{Name:generic.knockback_resistance,Base:1},{Name:generic.attack_damage,Base:80},{Name:generic.attack_knockback,Base:10}]}");
	        		 } else p.sendMessage(ChatColor.RED + "You need to be in the nether to spawn this boss!");
	        	 } else if (itemName.contains("Speed Titan")) {
	        		 p.getInventory().removeItem(Boss.getTitanSummoner());
	        		 TitanAbilities.announceDialogue("The " + ChatColor.AQUA + "Speed Titan" + ChatColor.GOLD + " has been summoned by " + ChatColor.YELLOW + p.getName());
	        		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon zombie ~ ~ ~ {Silent:0b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,NoAI:0b,CanPickUpLoot:0b,Health:100f,IsBaby:1b,CanBreakDoors:1b,CustomName:'{\"text\":\"Speed Titan\",\"color\":\"aqua\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:iron_sword\",Count:1b,tag:{display:{Name:'{\"text\":\"Speedy Sword\",\"color\":\"dark_blue\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:25s},{id:\"minecraft:smite\",lvl:15s},{id:\"minecraft:bane_of_arthropods\",lvl:15s},{id:\"minecraft:knockback\",lvl:30s},{id:\"minecraft:fire_aspect\",lvl:2s},{id:\"minecraft:looting\",lvl:15s},{id:\"minecraft:sweeping\",lvl:255s}],AttributeModifiers:[{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:15,Operation:0,UUID:[I;-2034508983,666519266,-1184414323,-2059296110],Slot:\"mainhand\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;1318279769,-1970322398,-1432561688,247014184],Slot:\"mainhand\"}]}},{id:\"minecraft:totem_of_undying\",Count:2b}],HandDropChances:[0.250F,0.000F],ArmorItems:[{id:\"minecraft:netherite_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Speedy Soul Boots\",\"color\":\"#301B02\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:15s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:feather_falling\",lvl:255s},{id:\"minecraft:thorns\",lvl:5s},{id:\"minecraft:depth_strider\",lvl:3s},{id:\"minecraft:frost_walker\",lvl:8s},{id:\"minecraft:soul_speed\",lvl:30s}]}},{},{},{id:\"minecraft:leather_helmet\",Count:1b,tag:{display:{Name:'{\"text\":\"Speed Crown\",\"color\":\"blue\",\"bold\":true,\"italic\":false}',color:63487},Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:10s},{id:\"minecraft:fire_protection\",lvl:7s},{id:\"minecraft:respiration\",lvl:10s},{id:\"minecraft:aqua_affinity\",lvl:10s}],AttributeModifiers:[{AttributeName:\"generic.attack_speed\",Name:\"generic.attack_speed\",Amount:5,Operation:0,UUID:[I;-1531656764,-1855894734,-1956664037,1619467644],Slot:\"head\"},{AttributeName:\"generic.movement_speed\",Name:\"generic.movement_speed\",Amount:0.5,Operation:0,UUID:[I;359970970,692931930,-2121941000,78001233],Slot:\"head\"}]}}],ArmorDropChances:[1.000F,0.085F,0.085F,0.085F],ActiveEffects:[{Id:1b,Amplifier:4b,Duration:200000,ShowParticles:0b},{Id:12b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:100},{Name:generic.follow_range,Base:400},{Name:generic.knockback_resistance,Base:0},{Name:generic.movement_speed,Base:0.42},{Name:generic.attack_damage,Base:15},{Name:generic.attack_knockback,Base:5},{Name:zombie.spawn_reinforcements,Base:1}]}");
	        	 } else if (itemName.contains("Golden Titan")) {
	        		 if (!(p.getInventory().containsAtLeast(Boss.getTitanSummoner(), 2))) {
	        			 p.sendMessage(notEnoughMats);
	        		 } else {
	        			p.getInventory().removeItem(Boss.getTitanSummoner());
	        			p.getInventory().removeItem(Boss.getTitanSummoner());
	        			TitanAbilities.announceDialogue("Hello, " + ChatColor.YELLOW + p.getName());
	        			new BukkitRunnable() {
	        				public void run() {
	        					TitanAbilities.announceDialogue("I see you've summoned me with TWO summoners instead of one.");
	    	        			new BukkitRunnable() {
	    	        				public void run() {
	    	        					TitanAbilities.announceDialogue("Doesn't that mean you will most likely die?");
	    	    	        			new BukkitRunnable() {
	    	    	        				public void run() {
	    	    	        					TitanAbilities.announceDialogue("Oh well. Only one way to find out.");
	    	    	    	        			new BukkitRunnable() {
	    	    	    	        				public void run() {
	    	    	    	        					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + p.getName() + " run summon horse ~ ~10 ~ {Silent:0b,Invulnerable:0b,Glowing:0b,CustomNameVisible:0b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:50f,Tame:1b,Variant:770,Passengers:[{id:\"minecraft:skeleton\",Silent:1b,Invulnerable:0b,Glowing:1b,CustomNameVisible:1b,PersistenceRequired:1b,NoAI:0b,CanPickUpLoot:0b,Health:1000f,CustomName:'{\"text\":\"Golden Titan\",\"color\":\"gold\",\"bold\":true,\"italic\":false}',HandItems:[{id:\"minecraft:bow\",Count:1b,tag:{display:{Name:'[{\"text\":\"||()||\",\"color\":\"yellow\",\"bold\":true,\"italic\":false,\"obfuscated\":true},{\"text\":\" Golden Dues Bow \",\"color\":\"gold\",\"bold\":true,\"italic\":false,\"obfuscated\":false},{\"text\":\"||()||\",\"color\":\"yellow\",\"bold\":true,\"italic\":false,\"obfuscated\":true}]'},HideFlags:9,Unbreakable:1b,Enchantments:[{id:\"minecraft:sharpness\",lvl:200s},{id:\"minecraft:smite\",lvl:150s},{id:\"minecraft:bane_of_arthropods\",lvl:150s},{id:\"minecraft:looting\",lvl:125s},{id:\"minecraft:power\",lvl:255s},{id:\"minecraft:flame\",lvl:1s},{id:\"minecraft:infinity\",lvl:1s}],AttributeModifiers:[{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:75,Operation:0,UUID:[I;1868371293,1792364231,-1316035093,932326968],Slot:\"mainhand\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:100,Operation:0,UUID:[I;-233155108,-513851099,-1677277242,909484161],Slot:\"mainhand\"},{AttributeName:\"generic.attack_damage\",Name:\"generic.attack_damage\",Amount:75,Operation:0,UUID:[I;-2125415915,1525566591,-1418838013,-1536361228],Slot:\"offhand\"},{AttributeName:\"generic.luck\",Name:\"generic.luck\",Amount:100,Operation:0,UUID:[I;119399133,784813284,-1509738999,-1818137441],Slot:\"offhand\"}]}},{}],HandDropChances:[1.000F,0.085F],ArmorItems:[{id:\"minecraft:golden_boots\",Count:1b,tag:{display:{Name:'{\"text\":\"Aurum Boots\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:255s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:feather_falling\",lvl:150s},{id:\"minecraft:blast_protection\",lvl:255s},{id:\"minecraft:projectile_protection\",lvl:255s},{id:\"minecraft:thorns\",lvl:50s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:200,Operation:0,UUID:[I;-1917959717,1418939966,-1356398513,964136433],Slot:\"feet\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:200,Operation:0,UUID:[I;1328642494,262557062,-1912573713,-107126586],Slot:\"feet\"}]}},{id:\"minecraft:golden_leggings\",Count:1b,tag:{display:{Name:'{\"text\":\"Aurum Leggings\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:255s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:blast_protection\",lvl:255s},{id:\"minecraft:projectile_protection\",lvl:255s},{id:\"minecraft:thorns\",lvl:50s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:200,Operation:0,UUID:[I;-1917959717,1418939966,-1356398513,964136433],Slot:\"legs\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:200,Operation:0,UUID:[I;1328642494,262557062,-1912573713,-107126586],Slot:\"legs\"}]}},{id:\"minecraft:golden_chestplate\",Count:1b,tag:{display:{Name:'{\"text\":\"Aurum Chestplate\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Unbreakable:1b,Enchantments:[{id:\"minecraft:protection\",lvl:255s},{id:\"minecraft:fire_protection\",lvl:255s},{id:\"minecraft:blast_protection\",lvl:255s},{id:\"minecraft:projectile_protection\",lvl:255s},{id:\"minecraft:thorns\",lvl:50s}],AttributeModifiers:[{AttributeName:\"generic.armor\",Name:\"generic.armor\",Amount:200,Operation:0,UUID:[I;-1917959717,1418939966,-1356398513,964136433],Slot:\"chest\"},{AttributeName:\"generic.armor_toughness\",Name:\"generic.armor_toughness\",Amount:200,Operation:0,UUID:[I;1328642494,262557062,-1912573713,-107126586],Slot:\"chest\"}]}},{id:\"minecraft:gold_block\",Count:16b,tag:{display:{Name:'{\"text\":\"Enchanted Gold Block\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},HideFlags:1,Enchantments:[{id:\"minecraft:protection\",lvl:1s}]}}],ArmorDropChances:[0.050F,0.050F,0.050F,1.000F],ActiveEffects:[{Id:2b,Amplifier:1b,Duration:200000,ShowParticles:0b},{Id:11b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:1000},{Name:generic.follow_range,Base:2000},{Name:generic.knockback_resistance,Base:10},{Name:generic.movement_speed,Base:0.1},{Name:generic.attack_damage,Base:90},{Name:generic.attack_knockback,Base:5}]}],CustomName:'{\"text\":\"Golden Horse\",\"color\":\"gold\",\"italic\":false}',ActiveEffects:[{Id:1b,Amplifier:2b,Duration:200000,ShowParticles:0b},{Id:28b,Amplifier:1b,Duration:200000,ShowParticles:0b}],Attributes:[{Name:generic.max_health,Base:50},{Name:generic.movement_speed,Base:0.5},{Name:generic.armor,Base:25},{Name:generic.armor_toughness,Base:25},{Name:horse.jump_strength,Base:2.5}],SaddleItem:{id:\"minecraft:saddle\",Count:1b},ArmorItem:{id:\"minecraft:golden_horse_armor\",Count:1b,tag:{display:{Name:'{\"text\":\"Patina\\'s Horse Armor\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},Enchantments:[{id:\"minecraft:protection\",lvl:55s}]}}}");
	    	    	    	        					p.getWorld().strikeLightningEffect(p.getLocation());
	    	    	    	        					new BukkitRunnable() {
	    	    	    	        						public void run() {
	    	    	    	        							p.getWorld().strikeLightningEffect(p.getLocation());
	    	    	    	        						}
	    	    	    	        					}.runTaskLater(plugin, 20);
	    	    	    	        				}
	    	    	    	        			}.runTaskLater(plugin, 40);
	    	    	        				}
	    	    	        			}.runTaskLater(plugin, 40);
	    	        				}
	    	        			}.runTaskLater(plugin, 40);
	        				}
	        			}.runTaskLater(plugin, 60);
	        		 }
	        	 }
	        	 
	        	 plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("titan_summons", plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_summons") + 1);
	        	 int titanSummons = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_summons");
	        	 if (titanSummons == 1) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(1, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 3) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(2, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 10) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(3, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 20) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(4, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 35) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(5, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 50) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(6, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 } else if (titanSummons == 100) {
	        		 Bukkit.broadcastMessage(AdvancementMessages.getUnlockedMessage(p) + AdvancementMessages.getTitanSpawner(7, true));
	        		 p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1.5F);
	        	 }
        	 }
         }
         if (clickedItem.equals(getInventoryPlaceholder())) return;
      }
      else if (inv.getTitle().contains("Teleport to Dimension")) {
    	  e.setCancelled(true);
    	  ItemStack clickedItem = e.getCurrentItem();
    	  if (clickedItem.getType().equals(Material.GRASS_BLOCK)) {
    		  p.sendMessage(ChatColor.RED + "You will be teleported in 5 seconds!");
    		  new BukkitRunnable() {
    			  public void run() {
    				  p.teleport(Bukkit.getWorld("world").getSpawnLocation());   			  
    			  }
    		  }.runTaskLater(plugin, 100);
    	  } else if (clickedItem.getType().equals(Material.NETHERRACK)) {
    		  p.sendMessage(ChatColor.RED + "You will be teleported in 5 seconds!");
    		  new BukkitRunnable() {
    			  public void run() {
    				  p.teleport(Bukkit.getWorld("world_nether").getSpawnLocation());   			  
    			  }
    		  }.runTaskLater(plugin, 100);
    	  } else if (clickedItem.getType().equals(Material.END_STONE)) {
    		  p.sendMessage(ChatColor.RED + "You will be teleported in 5 seconds!");
    		  new BukkitRunnable() {
    			  public void run() {
    				  p.teleport(Bukkit.getWorld("world_the_end").getSpawnLocation());   			  
    			  }
    		  }.runTaskLater(plugin, 100);
    	  }
    	  p.closeInventory();
      } else if (inv.getTitle().contains("SMP Trades Menu")) {
    	  e.setCancelled(true);
    	  ItemStack clickedItem = e.getCurrentItem();
    	  String notEnoughTrade = ChatColor.RED + "You don't have the necessary materials to trade!";
    	  String displayName = clickedItem.getItemMeta().getDisplayName();
    	  // Page Turners
    	  if (displayName.contains("Page 1")) {
    		  p.openInventory(TradeInventories.getPage1());
    		  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 1.5F);
    	  } else if (displayName.contains("Page 2")) {
    		  p.openInventory(TradeInventories.getPage2());
    		  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 1.5F);
    	  } else if (displayName.contains("Page 3")) {
    		  p.openInventory(TradeInventories.getPage3());
    		  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 1.5F);
    	  } else if (displayName.contains("Page 4")) {
    		  p.openInventory(TradeInventories.getPage4());
    		  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 1.5F);
    	  } else if (displayName.contains("Page 5")) {
    		  p.openInventory(TradeInventories.getPage5());
    		  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 3F, 1.5F);
    	  }
    	  // Trades
    	  else if (displayName.contains("Trade")) {
    		  if (!(p.getInventory().containsAtLeast(TradeParser.getMaterialCost(clickedItem), TradeParser.getAmountCost(clickedItem)))) {
    			  p.sendMessage(notEnoughTrade);
    			  p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 0F);
    		  } else {
    			  
    			  if (p.getInventory().firstEmpty() == -1) {
    				  p.sendMessage(ChatColor.RED + "Your inventory is full!");
    				  p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 0F);
    			  } else {
    				  p.getInventory().removeItem(new ItemStack(TradeParser.getMaterialCost(clickedItem).getType(), TradeParser.getAmountCost(clickedItem)));
    				  p.getInventory().addItem(new ItemStack(TradeParser.getMaterialReward(clickedItem).getType(), TradeParser.getAmountReward(clickedItem)));
    				  p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
    			  }
    		  }
    	  }
      } else if (inv.getTitle().contains("SMP Spells Menu")) {
    	  e.setCancelled(true);
      } else if (inv.getTitle().contains("Titan Warps")) {
    	  e.setCancelled(true);
    	  
    	  
    	  if (e.getCurrentItem() == null) return;
    	  ItemStack clickedItem = e.getCurrentItem();
    	  
    	  if (clickedItem.getType().equals(Material.NETHERITE_SWORD)) {
    		  p.teleport(Bukkit.getWorld("world_titan").getSpawnLocation(), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.WARPED_WART_BLOCK)) {
    		  p.teleport(new Location(Bukkit.getWorld("world_titan"), 150, 42, -11), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.RAW_COPPER)) {
    		  p.teleport(new Location(Bukkit.getWorld("world_titan"), 0, 5, 0), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.SOUL_SAND)) {
    		  p.teleport(new Location(Bukkit.getWorld("world_titan"), -73, 56, 92), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.ANCIENT_DEBRIS)) {
    		  p.teleport(new Location(Bukkit.getWorld("world_titan"), -188, 68, -11), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.IRON_BLOCK)) {
    		  p.teleport(new Location(Bukkit.getWorld("world_titan"), -52, 106, -244), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.END_PORTAL_FRAME)) {
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
    	  } else if (clickedItem.getType().equals(Material.BLACKSTONE)) {
    		 p.teleport(Bukkit.getWorld("world_titan_nether").getSpawnLocation(), TeleportCause.END_PORTAL);
    	  } else if (clickedItem.getType().equals(Material.BEDROCK)) {
    		  p.teleport(Bukkit.getWorld("world_titan_end").getSpawnLocation(), TeleportCause.END_PORTAL);
    	  }
    	  
    	  if (!p.isOp() && !(p.getGameMode().equals(GameMode.ADVENTURE))) {
    		  p.setGameMode(GameMode.ADVENTURE);
    	  }
      } else if (inv.getTitle().contains("Titan Shop")) {
    	  e.setCancelled(true);
      } else if (inv.getTitle().contains("Titan Finder")) {
    	  e.setCancelled(true);
    	  
    	  ItemStack clickedItem = e.getCurrentItem();
    	  Material type = clickedItem.getType();
    	  
    	  if (type == Material.GRAY_STAINED_GLASS_PANE || type == Material.REDSTONE_TORCH || type == Material.COAL_BLOCK || type == Material.BARRIER || !clickedItem.hasItemMeta()) return;
    	  
    	  if (finderCooldown.contains(p.getUniqueId())) {
    		  p.sendMessage(ChatColor.RED + "Bellator hasn't found a new foe yet!");
    		  return;
    	  }
    	  
    	  
    	  Location playerLoc = new Location(Bukkit.getWorld("world_titan_end"), -15, 74, 0, -90f, -10f);
    	  Location bossLoc = new Location(Bukkit.getWorld("world_titan_end"), 0, 79, 0);
    	  
    	  WorldServer ws = ((CraftWorld) Bukkit.getWorld("world_titan_end")).getHandle();
    	  
		  	p.closeInventory();
		  	p.teleport(playerLoc, TeleportCause.PLUGIN);
		  
    	  if (type.equals(Material.BLAZE_ROD)) {
    		  FireTitan b = new FireTitan(bossLoc);
    		  ws.addEntity(b);
    	  } else if (type.equals(Material.AMETHYST_SHARD)) {
    		  MagicalTitan m = new MagicalTitan(bossLoc);
    		  ws.addEntity(m);
    	  } else if (type.equals(Material.CROSSBOW)) {
    		  CrossbowTitan c = new CrossbowTitan(bossLoc);
    		  
    		  ((LivingEntity) c.getBukkitEntity()).getEquipment().setItemInMainHand(TitanFetcher.getRectus());
    		  ((LivingEntity) c.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0005f);
    		  
    		  ws.addEntity(c);
    	  } else if (type == Material.NETHERITE_AXE) {
    		  AxeTitan a = new AxeTitan(bossLoc);
    		  ws.addEntity(a);
    	  } else if (type.equals(Material.STICK)) {
    		  KnockbackTitan k = new KnockbackTitan(bossLoc);
    		  ws.addEntity(k);
    	  } else if (type == Material.POTION) {
    		  PotionTitan po = new PotionTitan(bossLoc);
    		  ws.addEntity(po);
    	  } else if (type == Material.TNT) {
					ExplosionTitan t = new ExplosionTitan(bossLoc);
					ws.addEntity(t);
				} else if (type == Material.IRON_BLOCK) {
					IronTitan i = new IronTitan(bossLoc, p);
					ws.addEntity(i);
				}
    	  
    	  p.playSound(bossLoc, Sound.ENTITY_ENDER_DRAGON_GROWL, 3F, 1F);
    	  
		  if (!(p.isOp())) {
	    	  
			  finderCooldown.add(p.getUniqueId());
			  
			  new BukkitRunnable() {
				  public void run() {
					  finderCooldown.remove(p.getUniqueId());
					  p.sendMessage(ChatColor.GOLD + "Bellator has found a new foe! Use /titanwarp to teleport!");
				  }
			  }.runTaskLater(plugin, 20 * 60 * 20);
		  }
      }

   }
   
   
   @EventHandler
   public void onMove(InventoryMoveItemEvent e) {
	  if (e.getItem() == null) return;
	  if (e.getSource() == null) return;
	  Inventory inv = e.getSource();
	  Inventory dest = e.getDestination();
	  if (inv.contains(getInventoryPlaceholder())) e.setCancelled(true);
	  if (dest.contains(getInventoryPlaceholder())) e.setCancelled(true);
   }
}