package gamercoder215.smpcore.listeners.titan;

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

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.fetcher.TitanFetcher;

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
	
	
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked().getType().equals(EntityType.PLAYER))) return;
		
		Player p = e.getPlayer();
		
		if (!(p.getWorld().getName().contains("world_titan"))) return;
		Player npc = (Player) e.getRightClicked();
		
		HashMap<Integer, String> bd = new HashMap<Integer, String>();
		bd.put(0, "My days of fighting are over, " + p.getName() + ".");
		bd.put(1, "However, you still seem to be full of life!");
		bd.put(2, "How about you help me defeat some old friends?");
		
		String name = ChatColor.stripColor(npc.getCustomName() == null ? npc.getName() : npc.getCustomName());
		if (name.equalsIgnoreCase("Venalicius")) {
			p.playSound(p.getLocation(), villagerSounds[r.nextInt(villagerSounds.length)], 3F, 0F);
			p.openInventory(TitanFetcher.getVenaliciusTrade());
		} else if (name.equalsIgnoreCase("Shulker Wizard")) {
			p.playSound(p.getLocation(), pillagerSounds[r.nextInt(pillagerSounds.length)], 3F, 0.75F);
		} else if (name.equalsIgnoreCase("Bellator")) {
			if (!(plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getConfigurationSection("npc_talks").getBoolean("bellator"))) {
				for (int i = 0; i < bd.size(); i++) {
					int pick = i;
					
					new BukkitRunnable() {
						public void run() {
							p.playSound(p.getLocation(), foxSounds[r.nextInt(foxSounds.length)], 3F, 1F);
							p.sendMessage(bd.get(pick));
						}
					}.runTaskLater(plugin, (r.nextInt(60 - 20) + 20));
				}
				
			} else {
				
			}
		}
	}
}
