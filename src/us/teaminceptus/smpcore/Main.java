package us.teaminceptus.smpcore;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	  WorldCreator titanWorld = new WorldCreator("world_titan");
	  Bukkit.createWorld(titanWorld);
	  
	  WorldCreator titanWorldNether = new WorldCreator("world_titan_nether");
	  Bukkit.createWorld(titanWorldNether);
	  
	  WorldCreator titanWorldEnd = new WorldCreator("world_titan_end");
	  Bukkit.createWorld(titanWorldEnd);
	  
	  WorldCreator deltaCave = new WorldCreator("world_caves_delta");
	  Bukkit.createWorld(deltaCave);
	  
	  WorldCreator alphaCave = new WorldCreator("world_caves_alpha");
	  Bukkit.createWorld(alphaCave);
	  
	  WorldCreator titanCave = new WorldCreator("world_caves_titan");
	  Bukkit.createWorld(titanCave);
	  
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
			  });
			 
		  }
	  }.runTaskTimer(this, 0, 2);
	  
	  new BukkitRunnable() {
		  public void run() {
			  for (Player p : Bukkit.getOnlinePlayers()) {
			      String uuid = p.getUniqueId().toString();
			      
			      if (main.getConfig().getConfigurationSection(uuid) == null) {
			    	  main.getConfig().createSection(uuid);
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
			      
			      if (main.getConfig().getConfigurationSection(uuid).get("rank") == null) {
			    	  main.getConfig().getConfigurationSection(uuid).set("rank", "default");
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