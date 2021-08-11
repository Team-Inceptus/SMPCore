package gamercoder215.smpcore;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import gamercoder215.smpcore.abilities.InfiniBlocks;
import gamercoder215.smpcore.abilities.PerussiWeapons;
import gamercoder215.smpcore.abilities.PlayerAbilities;
import gamercoder215.smpcore.abilities.Spells;
import gamercoder215.smpcore.abilities.SuperPickaxes;
import gamercoder215.smpcore.abilities.WorldAbilities;
import gamercoder215.smpcore.advancements.TitanAdvancements;
import gamercoder215.smpcore.bosses.abilities.AraneaAbilities;
import gamercoder215.smpcore.bosses.abilities.BlazeKingAbilities;
import gamercoder215.smpcore.bosses.abilities.BossStatusUpdate;
import gamercoder215.smpcore.bosses.abilities.CreeperKingAbilities;
import gamercoder215.smpcore.bosses.abilities.DimensionDragonAbilities;
import gamercoder215.smpcore.bosses.abilities.DimensionGuardAbilities;
import gamercoder215.smpcore.bosses.abilities.EmeraldWarriorAbilities;
import gamercoder215.smpcore.bosses.abilities.EnergyGuardianAbilities;
import gamercoder215.smpcore.bosses.abilities.GoldenShulkerAbilities;
import gamercoder215.smpcore.bosses.abilities.GolemKingAbilities;
import gamercoder215.smpcore.bosses.abilities.PhantomKingAbilities;
import gamercoder215.smpcore.bosses.abilities.SnowPrinceAbilities;
import gamercoder215.smpcore.bosses.abilities.SpiderQueenAbilities;
import gamercoder215.smpcore.bosses.abilities.TitanAbilities;
import gamercoder215.smpcore.bosses.abilities.WithermanAbilities;
import gamercoder215.smpcore.bosses.abilities.ZombieKingAbilities;
import gamercoder215.smpcore.bosses.drops.AraneaDrops;
import gamercoder215.smpcore.bosses.drops.BlazeKingDrops;
import gamercoder215.smpcore.bosses.drops.CreeperKingDrops;
import gamercoder215.smpcore.bosses.drops.DimensionGuardDrops;
import gamercoder215.smpcore.bosses.drops.EmeraldWarriorDrops;
import gamercoder215.smpcore.bosses.drops.GolemKingDrops;
import gamercoder215.smpcore.bosses.drops.MinorBossDrops;
import gamercoder215.smpcore.bosses.drops.SnowPrinceDrops;
import gamercoder215.smpcore.bosses.drops.SpiderQueenDrops;
import gamercoder215.smpcore.bosses.drops.ZombieKingDrops;
import gamercoder215.smpcore.commands.Abilities;
import gamercoder215.smpcore.commands.AllowFlight;
import gamercoder215.smpcore.commands.Ban;
import gamercoder215.smpcore.commands.Bed;
import gamercoder215.smpcore.commands.Boss;
import gamercoder215.smpcore.commands.CoderTest;
import gamercoder215.smpcore.commands.Craft;
import gamercoder215.smpcore.commands.EditPlayer;
import gamercoder215.smpcore.commands.Emojilist;
import gamercoder215.smpcore.commands.Enderchest;
import gamercoder215.smpcore.commands.EnderchestSee;
import gamercoder215.smpcore.commands.FlySpeed;
import gamercoder215.smpcore.commands.GetCustomItem;
import gamercoder215.smpcore.commands.GetStatistic;
import gamercoder215.smpcore.commands.Help;
import gamercoder215.smpcore.commands.InvSee;
import gamercoder215.smpcore.commands.Menu;
import gamercoder215.smpcore.commands.Rejoin;
import gamercoder215.smpcore.commands.SetRank;
import gamercoder215.smpcore.commands.SpawnCustomEntity;
import gamercoder215.smpcore.commands.Suspend;
import gamercoder215.smpcore.commands.TitanWarps;
import gamercoder215.smpcore.commands.TradesMenu;
import gamercoder215.smpcore.commands.WandInfo;
import gamercoder215.smpcore.commands.WorldChat;
import gamercoder215.smpcore.creatures.CreatureAbilities;
import gamercoder215.smpcore.creatures.CreatureGUI;
import gamercoder215.smpcore.creatures.CreaturesGuide;
import gamercoder215.smpcore.listeners.GUIManagers;
import gamercoder215.smpcore.listeners.PlayerDrops;
import gamercoder215.smpcore.listeners.PlayerStatusUpdate;
import gamercoder215.smpcore.listeners.titan.TitanEnchants;
import gamercoder215.smpcore.listeners.titan.TitanNPCs;
import gamercoder215.smpcore.listeners.titan.TitanWorld;
import gamercoder215.smpcore.listeners.titan.TitanWorldEnd;
import gamercoder215.smpcore.listeners.titan.TitanWorldNether;
import gamercoder215.smpcore.utils.InventoryUtils;

public class Main extends JavaPlugin {
	
	public ProtocolManager pm;
	
	String[] infoMessages = {
			ChatColor.RED + "Subscribe to GamerCoder215 for Updates, Feed, and Quality Content! https://bit.ly/sub2gamer",
			ChatColor.AQUA + "You can chat with other players in your world only by doing /wc <message>.",
			ChatColor.DARK_RED + "Hacking or Duping are bannable offenses! Remember to play fair!",
			ChatColor.GREEN + "You can use /trades to barter.",
			ChatColor.DARK_AQUA + "Use /menu for statistics and shortcuts!",
			ChatColor.BLUE + "You can use /cguide to find unique creatures!",
			ChatColor.RED + "We are not an anarchy server! Stealing and Griefing are not allowed.",
			ChatColor.BLUE + "Join the Discord to Updates & A Friendly Community - https://discord.io/thenoobygods",
			ChatColor.LIGHT_PURPLE + "You can use /recipes to see the custom recipes!"
			
	};
	
	Random r = new Random();
   public void onEnable() {
	  pm = ProtocolLibrary.getProtocolManager();
	   
	  // Info Messages
	  new BukkitRunnable() {
		  public void run() {
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
	  
	  // World-Specific Modifications
	  new BukkitRunnable() {
		  public void run() {
			  Bukkit.getOnlinePlayers().forEach(p -> {
				  if (p.getWorld().getName().equalsIgnoreCase("world_titan_end")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 6, true, false, false));
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 1, true, false, false));
				  }
				  
				  if (p.getWorld().getName().equalsIgnoreCase("world_titan_end") || p.getWorld().getName().equalsIgnoreCase("world_titan_nether")) {
					  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 4, 3, true, false, false));
				  }
			  });
		  }
	  }.runTaskTimer(this, 0, 2);

	  
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
      new Suspend(this);
      new GetStatistic(this);
      new SetRank(this);
      // Listeners & Utils
      new PlayerStatusUpdate(this);
      new GUIManagers(this);
      new PlayerDrops(this);
      
      new Spells(this);
      new PlayerAbilities(this);
      
      new TitanWorld(this);
      new TitanWorldNether(this);
      new TitanWorldEnd(this);
      
      new TitanEnchants(this);
      new TitanAdvancements(this);
      new TitanNPCs(this);
      
      new InventoryUtils(this);
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
   }
}