package us.teaminceptus.smpcore;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

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
import us.teaminceptus.smpcore.commands.Emojilist;
import us.teaminceptus.smpcore.commands.Enderchest;
import us.teaminceptus.smpcore.commands.EnderchestSee;
import us.teaminceptus.smpcore.commands.FlySpeed;
import us.teaminceptus.smpcore.commands.GetCustomItem;
import us.teaminceptus.smpcore.commands.GetStatistic;
import us.teaminceptus.smpcore.commands.Help;
import us.teaminceptus.smpcore.commands.InvSee;
import us.teaminceptus.smpcore.commands.Menu;
import us.teaminceptus.smpcore.commands.Rejoin;
import us.teaminceptus.smpcore.commands.SetRank;
import us.teaminceptus.smpcore.commands.SpawnCustomEntity;
import us.teaminceptus.smpcore.commands.Suspend;
import us.teaminceptus.smpcore.commands.Suspendlist;
import us.teaminceptus.smpcore.commands.TitanWarps;
import us.teaminceptus.smpcore.commands.TradesMenu;
import us.teaminceptus.smpcore.commands.WandInfo;
import us.teaminceptus.smpcore.commands.WorldChat;
import us.teaminceptus.smpcore.creatures.CreatureAbilities;
import us.teaminceptus.smpcore.creatures.CreatureGUI;
import us.teaminceptus.smpcore.creatures.CreaturesGuide;
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

public class Main extends JavaPlugin {
	
	public ProtocolManager pm;
	
	Random r = new Random();
   public void onEnable() {
	   
	  Main main = this;
	  pm = ProtocolLibrary.getProtocolManager();
	  
	  
	  // Info Messages
	  new BukkitRunnable() {
		  public void run() {
				String[] infoMessages = {
						ChatColor.RED + "Subscribe to GamerCoder215 for Updates, Feed, and Quality Content! https://bit.ly/sub2gamer",
						ChatColor.AQUA + "You can chat with other players in your world only by doing /wc <message>.",
						ChatColor.DARK_RED + "Hacking or Duping are bannable offenses! Remember that we are semi-anarchy!",
						ChatColor.GREEN + "You can use /trades to barter.",
						ChatColor.DARK_AQUA + "Use /menu for statistics and shortcuts!",
						ChatColor.BLUE + "You can use /cguide to find unique creatures!",
						ChatColor.RED + "We are not a full anarchy server! Stealing and Griefing are allowed, but no hacking.",
						ChatColor.BLUE + "Join the Discord to Updates & A Friendly Community - https://discord.io/thenoobygods",
						ChatColor.LIGHT_PURPLE + "You can use /recipes to see the custom recipes!",
						ChatColor.GOLD + "You are currently playing on " + ChatColor.GREEN + "TheNoobyGodsSMP" + ChatColor.GOLD + " with " + ChatColor.GREEN + GeneralUtils.thousandSeparator(Bukkit.getOfflinePlayers().length + Bukkit.getOnlinePlayers().size(), ",") + ChatColor.GOLD + " members!",
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
	  
	  // Config Update
	  new BukkitRunnable() {
		  public void run() {
			  for (Player p : Bukkit.getOnlinePlayers()) {
			      String uuid = p.getUniqueId().toString();
			      
			      if (main.getConfig().getConfigurationSection(uuid) == null) {
			    	  main.getConfig().createSection(uuid);
			      }
			      
			      if (!(main.getConfig().getConfigurationSection(uuid).isString("rank"))) {
			    	  main.getConfig().getConfigurationSection(uuid).set("rank", "default");
			      }
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("titan_summons") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("titan_summons", 0);
			      }
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("titan_kills") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("titan_kills", 0);
			      }
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("boss_summons") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("boss_summons", 0);
			      }
			      
			      if (main.getConfig().getConfigurationSection(uuid).isBoolean("killed_dimguard")) {
			    	  main.getConfig().getConfigurationSection(uuid).set("killed_dimguard", false);
			      }
			 
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("pet_damage") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("pet_damage", 0);
			      }
			      if (main.getConfig().getConfigurationSection(uuid).get("pet_defense") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("pet_defense", 0);
			      }
			      if (main.getConfig().getConfigurationSection(uuid).get("pet_speed") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("pet_speed", 0);
			      }
			      
			      // NPCs
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("npc_talks") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).createSection("npc_talks");
			      }
			      
			      if (main.getConfig().getConfigurationSection(uuid).getConfigurationSection("npc_talks").get("bellator") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).getConfigurationSection("npc_talks").set("bellator", false);;
			      }
			      
			      main.saveConfig();
			  }
		  }
	  }.runTaskTimer(this, 100, 100);

	  
	  // Regular Commands
      new Help(this);
      new Bed(this);
      new Menu(this);
      new Boss(this); 
      new Craft(this);
      new Enderchest(this);
      new TradesMenu(this);
      new Abilities(this);
      new WorldChat(this);
      new WandInfo(this);
      new TitanWarps(this);
      new Emojilist(this);
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
      new Banlist(this);
      new Suspend(this);
      new Suspendlist(this);
      new GetStatistic(this);
      new SetRank(this);
      // Listeners & Utils
      new PlayerStatusUpdate(this);
      new GUIManagers(this);
      new PlayerDrops(this);
      new DamageCalculation(this);
      
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