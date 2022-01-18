package us.teaminceptus.smpcore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import us.teaminceptus.smpcore.abilities.Cosmetics;
import us.teaminceptus.smpcore.abilities.InfiniBlocks;
import us.teaminceptus.smpcore.abilities.PerussiWeapons;
import us.teaminceptus.smpcore.abilities.PlayerAbilities;
import us.teaminceptus.smpcore.abilities.Spells;
import us.teaminceptus.smpcore.abilities.SuperPickaxes;
import us.teaminceptus.smpcore.abilities.WorldAbilities;
import us.teaminceptus.smpcore.advancements.TitanAdvancements;
import us.teaminceptus.smpcore.bosses.abilities.AraneaAbilities;
import us.teaminceptus.smpcore.bosses.abilities.ArenaTitanAbilities;
import us.teaminceptus.smpcore.bosses.abilities.BlazeKingAbilities;
import us.teaminceptus.smpcore.bosses.abilities.BossStatusUpdate;
import us.teaminceptus.smpcore.bosses.abilities.CreeperKingAbilities;
import us.teaminceptus.smpcore.bosses.abilities.DimensionDragonAbilities;
import us.teaminceptus.smpcore.bosses.abilities.DimensionGuardAbilities;
import us.teaminceptus.smpcore.bosses.abilities.EmeraldWarriorAbilities;
import us.teaminceptus.smpcore.bosses.abilities.EnergyGuardianAbilities;
import us.teaminceptus.smpcore.bosses.abilities.GoldenShulkerAbilities;
import us.teaminceptus.smpcore.bosses.abilities.GolemKingAbilities;
import us.teaminceptus.smpcore.bosses.abilities.PhantomKingAbilities;
import us.teaminceptus.smpcore.bosses.abilities.SculkWitchAbilities;
import us.teaminceptus.smpcore.bosses.abilities.SnowPrinceAbilities;
import us.teaminceptus.smpcore.bosses.abilities.SpiderQueenAbilities;
import us.teaminceptus.smpcore.bosses.abilities.TitanAbilities;
import us.teaminceptus.smpcore.bosses.abilities.WithermanAbilities;
import us.teaminceptus.smpcore.bosses.abilities.ZombieKingAbilities;
import us.teaminceptus.smpcore.bosses.drops.AraneaDrops;
import us.teaminceptus.smpcore.bosses.drops.BlazeKingDrops;
import us.teaminceptus.smpcore.bosses.drops.CreeperKingDrops;
import us.teaminceptus.smpcore.bosses.drops.DimensionGuardDrops;
import us.teaminceptus.smpcore.bosses.drops.EmeraldWarriorDrops;
import us.teaminceptus.smpcore.bosses.drops.GolemKingDrops;
import us.teaminceptus.smpcore.bosses.drops.MinorBossDrops;
import us.teaminceptus.smpcore.bosses.drops.SnowPrinceDrops;
import us.teaminceptus.smpcore.bosses.drops.SpiderQueenDrops;
import us.teaminceptus.smpcore.bosses.drops.ZombieKingDrops;
import us.teaminceptus.smpcore.commands.Abilities;
import us.teaminceptus.smpcore.commands.AllowFlight;
import us.teaminceptus.smpcore.commands.Ban;
import us.teaminceptus.smpcore.commands.Banlist;
import us.teaminceptus.smpcore.commands.Bed;
import us.teaminceptus.smpcore.commands.Boss;
import us.teaminceptus.smpcore.commands.CoderTest;
import us.teaminceptus.smpcore.commands.Craft;
import us.teaminceptus.smpcore.commands.EditPlayer;
import us.teaminceptus.smpcore.commands.Enderchest;
import us.teaminceptus.smpcore.commands.EnderchestSee;
import us.teaminceptus.smpcore.commands.FlySpeed;
import us.teaminceptus.smpcore.commands.GetCustomItem;
import us.teaminceptus.smpcore.commands.GetStatistic;
import us.teaminceptus.smpcore.commands.Hat;
import us.teaminceptus.smpcore.commands.Help;
import us.teaminceptus.smpcore.commands.InvSee;
import us.teaminceptus.smpcore.commands.Menu;
import us.teaminceptus.smpcore.commands.Mute;
import us.teaminceptus.smpcore.commands.RankUp;
import us.teaminceptus.smpcore.commands.Rejoin;
import us.teaminceptus.smpcore.commands.SetRank;
import us.teaminceptus.smpcore.commands.SpawnCustomEntity;
import us.teaminceptus.smpcore.commands.Suspend;
import us.teaminceptus.smpcore.commands.Suspendlist;
import us.teaminceptus.smpcore.commands.TitanWarps;
import us.teaminceptus.smpcore.commands.UnMute;
import us.teaminceptus.smpcore.commands.Value;
import us.teaminceptus.smpcore.commands.WandInfo;
import us.teaminceptus.smpcore.commands.WorldChat;
import us.teaminceptus.smpcore.commands.Yeet;
import us.teaminceptus.smpcore.creatures.CreatureAbilities;
import us.teaminceptus.smpcore.creatures.CreatureGUI;
import us.teaminceptus.smpcore.creatures.CreaturesGuide;
import us.teaminceptus.smpcore.fishing.FishingUtils;
import us.teaminceptus.smpcore.listeners.CustomDrops;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.listeners.PlayerDrops;
import us.teaminceptus.smpcore.listeners.PlayerStatusUpdate;
import us.teaminceptus.smpcore.listeners.caves.AlphaCave;
import us.teaminceptus.smpcore.listeners.caves.DeltaCave;
import us.teaminceptus.smpcore.listeners.caves.TitanCave;
import us.teaminceptus.smpcore.listeners.titan.TitanEnchants;
import us.teaminceptus.smpcore.listeners.titan.TitanNPCs;
import us.teaminceptus.smpcore.listeners.titan.TitanWorld;
import us.teaminceptus.smpcore.listeners.titan.TitanWorldEnd;
import us.teaminceptus.smpcore.listeners.titan.TitanWorldNether;
import us.teaminceptus.smpcore.planatae.GammaPlanatae;
import us.teaminceptus.smpcore.planatae.OmegaPlanatae;
import us.teaminceptus.smpcore.planatae.PlanataeHub;
import us.teaminceptus.smpcore.planatae.TitanPlanatae;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.InventoryUtils;
import us.teaminceptus.smpcore.utils.calculation.DamageCalculation;

public class SMPCore extends JavaPlugin {
	
	public static ProtocolManager pm;
	
	public static File divisionsFile;
	public static FileConfiguration divisionsConfig;
	public static File playersDirectory;
	
	
	Random r = new Random();
	
	public static File getDivisionsFile() {
		return divisionsFile;
	}
	
	public static FileConfiguration getDivisionsConfig() {
		return divisionsConfig;
	}
	
	public static File getPlayersDirectory() {
		return playersDirectory;
	}
	
	public static FileConfiguration getFile(OfflinePlayer p) {
		  File playerFile = new File(playersDirectory, p.getUniqueId().toString() + ".yml");
		  
		  if (!(playerFile.exists())) {
			  try {
				  playerFile.createNewFile();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  return YamlConfiguration.loadConfiguration(playerFile);
	}
	
	public void onEnable() {
	  // Setting
	  SMPCore main = this;
	  pm = ProtocolLibrary.getProtocolManager();
	  
	  // Divisions Files & Setting
	  divisionsFile = new File(this.getDataFolder(), "divisions.yml");
	  
	  playersDirectory = new File(this.getDataFolder(), "players");
	  
	  if (!(playersDirectory.exists())) {
		  playersDirectory.mkdir();
	  }
	  
	  if (!(divisionsFile.exists())) {
		  try {
			  divisionsFile.createNewFile();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  divisionsConfig = YamlConfiguration.loadConfiguration(divisionsFile);
	  // new DivisionCommands(this);
	  // new DivisionListener(this);
	  
	  for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
	      String uuid = p.getUniqueId().toString();
	      
	      File playerFile = new File(playersDirectory, uuid + ".yml");
	      
	      if (!(playerFile.exists())) {
	    	  try {
	    		  playerFile.createNewFile();
	    	  } catch (IOException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	      
	      FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	      
	      if (!(playerConfig.isString("rank"))) {
	    	  playerConfig.set("rank", "default");
	      }
	      
	      if (playerConfig.get("titan_summons") == null) {
	    	  playerConfig.set("titan_summons", 0);
	      }
	      
	      if (playerConfig.get("titan_kills") == null) {
	    	  playerConfig.set("titan_kills", 0);
	      }
	      
	      if (playerConfig.get("boss_summons") == null) {
	    	  playerConfig.set("boss_summons", 0);
	      }
	      
	      if (!(playerConfig.isBoolean("killed_dimguard"))) {
	    	  playerConfig.set("killed_dimguard", false);
	      }
	      
	      if (playerConfig.get("pet_damage") == null) {
	    	  playerConfig.set("pet_damage", 0);
	      }
	      if (playerConfig.get("pet_defense") == null) {
	    	  playerConfig.set("pet_defense", 0);
	      }
	      if (playerConfig.get("pet_speed") == null) {
	    	  playerConfig.set("pet_speed", 0);
	      }
	      
	      if (!(playerConfig.isBoolean("killed_dragtitan"))) {
	    	  playerConfig.set("killed_dragtitan", false);
	      }
	      
	      // NPCs
	      
	      if (playerConfig.get("npc_talks") == null) {
	    	  playerConfig.createSection("npc_talks");
	      }
	      
	      if (playerConfig.getConfigurationSection("npc_talks").get("bellator") == null) {
	    	  playerConfig.getConfigurationSection("npc_talks").set("bellator", false);;
	      }
	      
	      if (!(playerConfig.isBoolean("muted"))) {
	    	  playerConfig.set("muted", false);
	      }
	      
	      try {
	    	 playerConfig.save(playerFile);
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	  }
	  
      // Non-Player Updates
      if (!(this.getConfig().isConfigurationSection("networth_leaderboard"))) {
    	  this.getConfig().createSection("networth_leaderboard");
      }
	  
	  // Info Messages
	  new BukkitRunnable() {
		  public void run() {
				String[] infoMessages = {
						ChatColor.AQUA + "You can chat with other players in your world only by doing /wc <message>.",
						ChatColor.DARK_RED + "Hacking or Duping are bannable offenses! Remember to play fair!",
						ChatColor.YELLOW + "Remember to play fair and have fun!",
						ChatColor.DARK_AQUA + "Use /menu for statistics and shortcuts!",
						ChatColor.BLUE + "You can use /cguide to find unique creatures!",
						ChatColor.RED + "We are not an full anarchy server! Stealing, Griefing and Hacking are not allowed!",
						ChatColor.BLUE + "Join the Discord to Updates & A Friendly Community - https://discord.io/thenoobygods",
						ChatColor.LIGHT_PURPLE + "You can use /recipes to see the custom recipes!",
						ChatColor.GOLD + "You are currently playing on " + ChatColor.GREEN + "TheNoobyGodsSMP" + ChatColor.GOLD + " with " + ChatColor.GREEN + GeneralUtils.thousandSeparator(Bukkit.getOfflinePlayers().length + Bukkit.getOnlinePlayers().size(), ",") + ChatColor.GOLD + " total members!",
						ChatColor.DARK_BLUE + "You can apply for staff in the Discord.",
						ChatColor.LIGHT_PURPLE + "We have a new YouTube Channel! Check it out -> https://bit.ly/tng-yt",
				};
			  
			  Bukkit.broadcastMessage(infoMessages[r.nextInt(infoMessages.length)]);
		  }
	  }.runTaskTimer(this, 20 * (r.nextInt(60 - 30) + 30), 20 * (r.nextInt(60 - 30) + 30));
	  
	  // Load External Worlds
	  // Titans
	  WorldCreator titanWorld = new WorldCreator("world_titan");
	  Bukkit.createWorld(titanWorld);
	  
	  WorldCreator titanWorldNether = new WorldCreator("world_titan_nether");
	  Bukkit.createWorld(titanWorldNether);
	  
	  WorldCreator titanWorldEnd = new WorldCreator("world_titan_end");
	  Bukkit.createWorld(titanWorldEnd);
	  
	  // Caves
	  WorldCreator deltaCave = new WorldCreator("world_caves_delta");
	  Bukkit.createWorld(deltaCave);
	  
	  WorldCreator alphaCave = new WorldCreator("world_caves_alpha");
	  Bukkit.createWorld(alphaCave);
	  
	  WorldCreator titanCave = new WorldCreator("world_caves_titan");
	  Bukkit.createWorld(titanCave);
	  
	  // Planatae
	  WorldCreator hubP = new WorldCreator("world_planatae_hub");
	  Bukkit.createWorld(hubP);
	  
	  WorldCreator gammaP = new WorldCreator("world_planatae_gamma");
	  Bukkit.createWorld(gammaP);
	  
	  WorldCreator omegaP = new WorldCreator("world_planatae_omega");
	  Bukkit.createWorld(omegaP);
	  
	  WorldCreator titanP = new WorldCreator("world_planatae_titan");
	  Bukkit.createWorld(titanP);
	  
	  // World-Specific Modifications
	  new BukkitRunnable() {
		  public void run() {
			  Bukkit.getOnlinePlayers().forEach(p -> {
				  if (p.getWorld().getName().equalsIgnoreCase("world_titan_end")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 6, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 1, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_titan_nether") || p.getWorld().getName().equalsIgnoreCase("world_titan_nether")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 3, true, false, false));
				  }
				  
				  if (p.getWorld().getName().contains("caves")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 4, 1, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_caves_delta")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 0, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_caves_alpha")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 3, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_caves_titan")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 6, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_planatae_gamma")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 1, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 2, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 2, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_planatae_omega")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 4, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 5, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 5, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_planatae_titan")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 7, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 9, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 9, true, false, false));
				  }
			  });
			 
		  }
	  }.runTaskTimer(this, 0, 2);
	  
	  // Chalc Ability - Omega Planatae
	  World cW = Bukkit.getWorld("world_planatae_omega");
	  Location[] rodLocations = {
			  new Location(cW, -432, 199, 252),
			  new Location(cW, -426, 211, 237),
			  new Location(cW, -415, 216, 211),
			  new Location(cW, -390, 206, 184),
			  new Location(cW, -359, 218, 193),
			  new Location(cW, -356, 222, 211),
			  new Location(cW, -338, 222, 226),
			  new Location(cW, -323, 220, 243),
			  new Location(cW, -322, 222, 266),
			  new Location(cW, -342, 226, 263),
			  new Location(cW, -351, 221, 278),
			  new Location(cW, -371, 231, 290),
			  new Location(cW, -384, 222, 277),
			  new Location(cW, -387, 222, 293),
			  new Location(cW, -397, 224, 281),
			  new Location(cW, -396, 222, 260),
			  new Location(cW, -386, 224, 241),
			  new Location(cW, -376, 224, 229),
			  new Location(cW, -387, 222, 213),
			  new Location(cW, -396, 222, 213),
			  new Location(cW, -314, 207, 198),
			  new Location(cW, -314, 215, 207),
			  new Location(cW, -293, 200, 204),
			  new Location(cW, -285, 198, 223),
			  new Location(cW, -290, 214, 245),
			  new Location(cW, -291, 220, 250),
			  new Location(cW, -295, 205, 282),
			  new Location(cW, -305, 197, 304),
			  new Location(cW, -333, 203, 320),
			  new Location(cW, -356, 207, 323),
			  new Location(cW, -375, 211, 317),
			  new Location(cW, -394, 212, 306),
			  new Location(cW, -392, 218, 300),
			  new Location(cW, -370, 220, 305),
			  new Location(cW, -351, 222, 301),
			  new Location(cW, -325, 221, 293),
			  new Location(cW, -329, 222, 282),
			  new Location(cW, -328, 224, 272),
			  new Location(cW, -335, 220, 254),
			  new Location(cW, -356, 222, 240),
			  new Location(cW, -362, 222, 246),
			  new Location(cW, -371, 233, 269),
			  new Location(cW, -374, 245, 282),
			  new Location(cW, -365, 222, 257),
			  new Location(cW, -370, 222, 237),
			  new Location(cW, -334, 223, 210),
			  new Location(cW, -304, 219, 242),
			  new Location(cW, -307, 215, 283),
			  new Location(cW, -317, 217, 293),
			  new Location(cW, -336, 223, 286),
			  new Location(cW, -345, 230, 262),
			  new Location(cW, -381, 223, 264),
			  new Location(cW, -392, 222, 272),
			  new Location(cW, -395, 224, 275),
			  new Location(cW, -409, 220, 258),
			  new Location(cW, -421, 215, 253),
			  new Location(cW, -425, 208, 275),
			  new Location(cW, -408, 195, 312),
			  new Location(cW, -377, 172, 342),
			  new Location(cW, -361, 179, 337),
			  new Location(cW, -344, 190, 330),
			  new Location(cW, -312, 187, 319),
			  new Location(cW, -298, 191, 307),
			  new Location(cW, -280, 182, 285),
			  new Location(cW, -283, 196, 264),
			  new Location(cW, -282, 200, 226),
			  new Location(cW, -303, 218, 256),
			  new Location(cW, -325, 222, 254),
			  new Location(cW, -349, 221, 236),
			  new Location(cW, -348, 222, 225),
			  new Location(cW, -365, 220, 213),
			  new Location(cW, -372, 220, 197),
			  new Location(cW, -386, 215, 195),
			  new Location(cW, -418, 210, 212),
			  new Location(cW, -403, 220, 234),
			  new Location(cW, -382, 222, 253),
			  new Location(cW, -373, 228, 266),
			  new Location(cW, -370, 238, 280),
			  new Location(cW, -361, 222, 288),
			  new Location(cW, -337, 222, 280),
			  new Location(cW, -334, 221, 265),
			  new Location(cW, -337, 222, 239),
			  new Location(cW, -322, 222, 227),
			  new Location(cW, -326, 222, 217),
			  new Location(cW, -354, 220, 224),
			  new Location(cW, -349, 223, 212),
			  new Location(cW, -395, 222, 237),
			  new Location(cW, -404, 222, 241),
			  new Location(cW, -413, 221, 261),
			  new Location(cW, -410, 220, 249),
			  new Location(cW, -411, 221, 231),
	  };
	  
	  new BukkitRunnable() {
		  public void run() {
			  cW.strikeLightning(rodLocations[r.nextInt(rodLocations.length)]);
			  cW.strikeLightning(rodLocations[r.nextInt(rodLocations.length)]);
		  }
	  }.runTaskTimer(this, 0, 20 * (r.nextInt(30) + 7));
	  
	  // Update Player Inventory (Rarity)
	  new BukkitRunnable() {
		  public void run() {
			  for (Player p : Bukkit.getOnlinePlayers()) {
				  	PlayerInventory inv = p.getInventory();
					for (byte index = 0; index < 40; index++) {
						ItemStack i = inv.getItem(index);
						if (i == null) continue;
						if (Value.containsRarity(i)) continue;
						ItemStack newItem = i;
						ItemMeta newItemMeta = newItem.getItemMeta();
						List<String> lore = (newItemMeta.hasLore() ? newItemMeta.getLore() : new ArrayList<>());
						int target = (i.getItemMeta().hasLore() ? i.getItemMeta().getLore().size() : 0);
						lore.add(target, Value.getRarity(i).nameColor());
						newItemMeta.setLore(lore);
						if (!(newItemMeta.hasLocalizedName())) {
							newItemMeta.setLocalizedName(newItemMeta.hasDisplayName() ? ChatColor.stripColor(newItemMeta.getDisplayName()).toLowerCase().replaceAll(" ", "_") : 
							(ChatColor.stripColor(CraftItemStack.asNMSCopy(i).c().a()).replaceAll(" ", "_")));
						}
						newItem.setItemMeta(newItemMeta);
						
						inv.setItem(index, Value.validate(newItem));
					}
			  }
		  }
	  }.runTaskTimer(main, 0, 2);
	  
	  // Custom Potions Check
	  new BukkitRunnable() {
		  public void run() {
			  for (Map.Entry<UUID, Long> entry : WorldAbilities.doubleDamage.entrySet()) {
				  if (entry.getValue() == 0 || Bukkit.getPlayer(entry.getKey()) == null) {
					  WorldAbilities.doubleDamage.remove(entry.getKey());
				  }
				  else {
					  WorldAbilities.doubleDamage.put(entry.getKey(), entry.getValue() - 1);
				  }
			  }
			  
			  for (Map.Entry<UUID, Long> entry : WorldAbilities.freeze.entrySet()) {
				  if (entry.getValue() == 0 || Bukkit.getPlayer(entry.getKey()) == null) {
					  WorldAbilities.freeze.remove(entry.getKey());
				  }
				  else {
					  WorldAbilities.freeze.put(entry.getKey(), entry.getValue() - 1);
				  }
			  }
			  
			  for (Map.Entry<UUID, Long> entry : WorldAbilities.radiation.entrySet()) {
				  if (entry.getValue() == 0 || Bukkit.getPlayer(entry.getKey()) == null) {
					  WorldAbilities.radiation.remove(entry.getKey());
				  }
				  else {
					  WorldAbilities.radiation.put(entry.getKey(), entry.getValue() - 1);
				  }
				  
				  Player p = Bukkit.getPlayer(entry.getKey());
				  
				  p.getNearbyEntities(10, 10, 10).forEach(en -> {
					  if (!(en instanceof LivingEntity len)) return;
					  len.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 2, true, true, true));
					  len.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1, true, true, true));
					  len.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 1, true, true, true));
				  });
			  }
			  
			  for (Map.Entry<UUID, Long> entry : WorldAbilities.oxygen.entrySet()) {
				  if (entry.getValue() == 0 || Bukkit.getPlayer(entry.getKey()) == null) {
					  WorldAbilities.oxygen.remove(entry.getKey());
				  }
				  else {
					  WorldAbilities.oxygen.put(entry.getKey(), entry.getValue() - 1);
				  }
			  }
			  
			  for (Map.Entry<UUID, Long> entry : WorldAbilities.xray.entrySet()) {
				  if (entry.getValue() == 0 || Bukkit.getPlayer(entry.getKey()) == null) {
					  WorldAbilities.xray.remove(entry.getKey());
				  }
				  else {
					  WorldAbilities.xray.put(entry.getKey(), entry.getValue() - 1);
				  }
			  }
		  }
	  }.runTaskTimer(main, 0, 20);
	  
	  // Generate Networth Leaderboards
	  new BukkitRunnable() {
		  public void run() {
			  try {
				  Map<Double, OfflinePlayer> players = new HashMap<>();
				  List<Double> scores = new ArrayList<>();
				  for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
					  if (p.isOp()) continue;
					  if (p.isOnline()) {
						  Player online = p.getPlayer();
						  double echestValue = 0;
						  for (ItemStack i : online.getEnderChest()) {
							  echestValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
						  }
							
						  double invValue = 0;
						  for (ItemStack i : online.getInventory()) {
							  invValue += Value.getScore(i) * Value.getRarity(i).getMultiplier();
						  }
						  double newValue = echestValue + invValue;
						  getFile(p).set("last_networth", newValue);
					  }
					  ConfigurationSection section = getFile(p);
					  double value = section.getDouble("last_networth", 0);
					  scores.add(value);
					  players.put(value, p);
					  
				  }
				  
				  Collections.sort(scores, Collections.reverseOrder());
				  
				  ConfigurationSection leaderboard = main.getConfig().getConfigurationSection("networth_leaderboard");
				  leaderboard.set("first", players.get(scores.get(0)));
				  leaderboard.set("first-amount", scores.get(0));
				  leaderboard.set("second", players.get(scores.get(1)));
				  leaderboard.set("second-amount", scores.get(1));
				  leaderboard.set("third", players.get(scores.get(2)));
				  leaderboard.set("third-amount", scores.get(2));
				  leaderboard.set("fourth", players.get(scores.get(3)));
				  leaderboard.set("fourth-amount", scores.get(3));
				  leaderboard.set("fifth", players.get(scores.get(4)));
				  leaderboard.set("fifth-amount", scores.get(4));
				  
				  main.saveConfig();
			  } catch (Exception e) {
				  // Do Nothing
				  e.hashCode();
			  }
		  }
	  }.runTaskTimerAsynchronously(main, 0, 20 * 30); // Updated every 30 seconds
	  
	  // Regular Commands
      new Help(this);
      new Bed(this);
      new Menu(this);
      new Boss(this); 
      new Craft(this);
      new Enderchest(this);
      new Abilities(this);
      new WorldChat(this);
      new WandInfo(this);
      new TitanWarps(this);
      new Hat(this);
      new RankUp(this);
      new Value(this);
      // Admin Commands
      new InvSee(this);
      new FlySpeed(this);
      new EnderchestSee(this);
      new AllowFlight(this);
      new GetCustomItem(this);
      new SpawnCustomEntity(this);
      new Rejoin(this);
      new EditPlayer(this);
      new CoderTest(this);
      new Ban(this);
      new Yeet(this);
      new Banlist(this);
      new Suspend(this);
      new Suspendlist(this);
      new GetStatistic(this);
      new SetRank(this);
      new Mute(this);
      new UnMute(this);
      // Listeners & Utils
      new PlayerStatusUpdate(this);
      new Cosmetics(this);
      new GUIManagers(this);
      new PlayerDrops(this);
      new DamageCalculation(this);
      
      new CustomDrops(this);
      
      new FishingUtils(this);
      
      new Spells(this);
      new PlayerAbilities(this);
      
      new TitanWorld(this);
      new TitanWorldNether(this);
      new TitanWorldEnd(this);
      
      new TitanEnchants(this);
      new TitanAdvancements(this);
      new TitanNPCs(this);
      
      new InventoryUtils(this);
      
      new DeltaCave(this);
      new AlphaCave(this);
      new TitanCave(this);
      
      new GammaPlanatae(this);
      new OmegaPlanatae(this);
      new TitanPlanatae(this);
      new PlanataeHub(this);
      // Abilities
      new WorldAbilities(this);
      new InfiniBlocks(this);
      new SuperPickaxes(this);
      new PerussiWeapons(this);
      // Creatures
      new CreaturesGuide(this);
      new CreatureAbilities(this);
      new CreatureGUI(this);
      // Bosses & Drops
      new BossStatusUpdate(this);
      
      new SpiderQueenAbilities(this);
      new SpiderQueenDrops(this);
      
      new BlazeKingAbilities(this);
      new BlazeKingDrops(this);
      
      new WithermanAbilities(this);
      
      new SnowPrinceAbilities(this);
      new SnowPrinceDrops(this);
      
      new ZombieKingAbilities(this);
      new ZombieKingDrops(this);
      
      new MinorBossDrops(this);
      
      new SculkWitchAbilities(this);
      
      new DimensionGuardAbilities(this);
      new DimensionGuardDrops(this);
      	// T5 Bosses
      	new GoldenShulkerAbilities(this);
      	
      	new DimensionDragonAbilities(this);
      	
      	new PhantomKingAbilities(this);
      	
        new EmeraldWarriorAbilities(this);
        new EmeraldWarriorDrops(this);
        
        new TitanAbilities(this);
        
        new EnergyGuardianAbilities(this);
        
        new AraneaAbilities(this);
        new AraneaDrops(this);
        
        new CreeperKingAbilities(this);
        new CreeperKingDrops(this);
        
        new GolemKingAbilities(this);
        new GolemKingDrops(this);
        
        // Arena Titans
        new ArenaTitanAbilities(this);
	}
}