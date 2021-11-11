package us.teaminceptus.smpcore.listeners.titan;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.Main;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class TitanNPCs implements Listener {
	
	protected Main plugin;
	
	public TitanNPCs(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	Random r = new Random();
	
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
	
	Sound[] foxSounds = {
			Sound.ENTITY_FOX_EAT,
			Sound.ENTITY_FOX_AGGRO,
			Sound.ENTITY_FOX_SPIT,
			Sound.ENTITY_FOX_SNIFF
	};
	
	
	
	boolean bellatorTalking = false;
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked().getType().equals(EntityType.PLAYER))) return;
		
		Player p = e.getPlayer();
		
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		Player npc = (Player) e.getRightClicked();
		
		HashMap<Integer, String> bd = new HashMap<Integer, String>();
		bd.put(0, "Hello there, " + p.getName() + "!");
		bd.put(1, "It's been a while since i've foughten in the " + ChatColor.RED + "titan war" + ChatColor.WHITE + ".");
		bd.put(2, "I still have a few rouge titans lurking around these parts...");
		bd.put(3, "You seem to be full of life, however! How about your help me take down a few baddies?");
		
		String name = ChatColor.stripColor(npc.getCustomName() == null ? npc.getName() : npc.getCustomName());
		if (name.equalsIgnoreCase("Venalicius")) {
			p.playSound(p.getLocation(), villagerSounds[r.nextInt(villagerSounds.length)], 3F, 0F);
			p.openInventory(TitanFetcher.getVenaliciusTrade());
		} else if (name.equalsIgnoreCase("Shulker Wizard")) {
			p.playSound(p.getLocation(), pillagerSounds[r.nextInt(pillagerSounds.length)], 3F, 0.75F);
		} else if (name.equalsIgnoreCase("Bellator")) {
			if (!(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getConfigurationSection("npc_talks").getBoolean("bellator"))) {
				if (bellatorTalking) return;
				bellatorTalking = true;
				
				for (int i = 0; i < bd.size(); i++) {
					new BukkitRunnable() {
						public void run() {
							e.hashCode();
						}
					}.runTaskLater(plugin, r.nextInt(100 - 40) + 40);
					p.sendMessage(ChatColor.BLUE + "[Bellator] " + ChatColor.WHITE + bd.get(i));
				}
				
				p.openInventory(TitanFinder.getTitanFinder(plugin, p));
				plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getConfigurationSection("npc_talks").set("bellator", true);
				plugin.saveConfig();
				
				bellatorTalking = false;
				
			} else {
				p.openInventory(TitanFinder.getTitanFinder(plugin, p));
			}
		}
	}
}
