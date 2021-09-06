package gamercoder215.smpcore.advancements;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.AdvancementDisplay;
import eu.endercentral.crazy_advancements.AdvancementDisplay.AdvancementFrame;
import eu.endercentral.crazy_advancements.AdvancementVisibility;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import gamercoder215.smpcore.Main;

public class TitanAdvancements implements Listener {
	
	protected Main plugin;
	public static AdvancementManager m = new AdvancementManager();
	
	public TitanAdvancements(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		// Adding Advancements
		// Goals and Challenges are swapped
		AdvancementDisplay rootD = new AdvancementDisplay(Material.NETHERITE_BLOCK, "The Titan Dimension", "The dimension for the strongest of us", AdvancementFrame.TASK, false, false, AdvancementVisibility.HIDDEN);
		rootD.setBackgroundTexture("textures/block/deepslate.png");
		Advancement root = new Advancement(null, new NameKey("titan", "root"), rootD);
		
		AdvancementDisplay ossumAgeD = new AdvancementDisplay(Material.DEEPSLATE, "Ossum Age", "Mine Ossum with your new titan pickaxe", AdvancementFrame.TASK, true, true, AdvancementVisibility.VANILLA);
		ossumAgeD.setCoordinates(1, 0);
		Advancement ossumAge = new Advancement(root, new NameKey("titan", "mine-ossum"), ossumAgeD);
		
		AdvancementDisplay theStrongestMetalD = new AdvancementDisplay(Material.RAW_IRON_BLOCK, "The Strongest Metal", "Mine Ferrum", AdvancementFrame.TASK, true, true, AdvancementVisibility.VANILLA);
		theStrongestMetalD.setCoordinates(3, 1);
		Advancement theStrongestMetal = new Advancement(ossumAge, new NameKey("titan", "mine-ferrum"), theStrongestMetalD);
		
		AdvancementDisplay enchantedMetalD = new AdvancementDisplay(Material.RAW_COPPER, "Enchanted Metal", "Find Iabesium and Mine it", AdvancementFrame.GOAL, true, true, AdvancementVisibility.PARENT_GRANTED);
		enchantedMetalD.setCoordinates(2, -2);
		Advancement enchantedMetal = new Advancement(ossumAge, new NameKey("titan", "mine-iabesium"), enchantedMetalD);
		
		AdvancementDisplay energySlayerD = new AdvancementDisplay(Material.NETHER_STAR, "Energy Slayer", "Slay the Energy Guardian", AdvancementFrame.CHALLENGE, true, true, AdvancementVisibility.PARENT_GRANTED);
		enchantedMetalD.setCoordinates(3, 2.5F);
		Advancement energySlayer = new Advancement(theStrongestMetal, new NameKey("titan", "kill-energy-guardian"), energySlayerD);
		
		AdvancementDisplay superFarmerD = new AdvancementDisplay(Material.WARPED_FUNGUS, "Super Farmer", "Farm Praefortis", AdvancementFrame.TASK, true, true, AdvancementVisibility.VANILLA);
		superFarmerD.setCoordinates(-1, 0);
		Advancement superFarmer = new Advancement(root, new NameKey("titan", "farm-praefortis"), superFarmerD);
		
		AdvancementDisplay mineTheStarsD = new AdvancementDisplay(Material.GLOWSTONE, "Mine the Stars", "Mine Clarus", AdvancementFrame.CHALLENGE, true, true, AdvancementVisibility.VANILLA);
		mineTheStarsD.setCoordinates(-3, 0);
		Advancement mineTheStars = new Advancement(superFarmer, new NameKey("titan", "mine-clarus"), mineTheStarsD);
		
		
		AdvancementDisplay titanEnchanterD = new AdvancementDisplay(Material.ENCHANTED_BOOK, "Titan Enchanter", "Use the Titan Enchant Table", AdvancementFrame.GOAL, true, true, AdvancementVisibility.VANILLA);
		titanEnchanterD.setCoordinates(0, 2);
		Advancement titanEnchanter = new Advancement(root, new NameKey("titan", "titan-enchant"), titanEnchanterD);
		
		
		m.addAdvancement(root, 
				ossumAge, theStrongestMetal, enchantedMetal, energySlayer,
				superFarmer, mineTheStars,
				titanEnchanter);
		
	}
	
	public static void grantEnchanterAdvancement(Player p) {
		if (!(m.getAdvancement(new NameKey("titan", "titan-enchant"))).isDone(p)) {
			m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "titan-enchant")));
			p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3F, 1F);
		}
	}
	
	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		if (!(e.getDamager().getWorld().getName().equalsIgnoreCase("world_titan"))) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		if (!(e.getEntity().isDead())) return;
		
		Player p = (Player) e.getDamager();
		
		if (e.getEntity().getType().equals(EntityType.ELDER_GUARDIAN)) {
			if (!(m.getAdvancement(new NameKey("titan", "kill-energy-guardian"))).isDone(p)) {
				m.grantAdvancement(p, m.getAdvancement(new NameKey("titan", "kill-energy-guardian")));
			}
		}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		new BukkitRunnable() {
			public void run() {
				m.addPlayer(e.getPlayer());
			}
		}.runTaskLater(plugin, 3);
	}
}
