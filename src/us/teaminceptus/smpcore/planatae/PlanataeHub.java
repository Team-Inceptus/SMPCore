package us.teaminceptus.smpcore.planatae;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class PlanataeHub implements Listener {
	
	// Global Cave Modifications are stored in GAMMA, NOT HERE
	
	protected SMPCore plugin;
	
	public PlanataeHub(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() == null) return;
		if (!(e.getRightClicked() instanceof LivingEntity en)) return;
		
		if (en.getCustomName() == null) return;
		if (en.getCustomName().contains("Tractus")) {
			e.getPlayer().openInventory(getPlanataeMenu(e.getPlayer()));
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_planatae_hub"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) return;
		Player p = e.getPlayer();
		if (!(p.getWorld().getName().contains("world_planatae"))) return;
		Block b = e.getClickedBlock();
		if (b.getType() == Material.END_PORTAL_FRAME) {
			p.sendMessage(ChatColor.GREEN + "Warping...");
			p.teleport(Bukkit.getWorld("world_planatae_hub").getSpawnLocation());
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player p)) return;
		if (!(e.getView().getTitle().contains("Planatae Menu"))) return;
		e.setCancelled(true);
		if (e.getCurrentItem() == null) return;
		
		ItemStack item = e.getCurrentItem();
		if (!(item.hasItemMeta())) return;
		if (!(item.getItemMeta().hasDisplayName())) return;
		int slot = e.getSlot();
		
		if (item.getType() == Material.PLAYER_HEAD) {
			if (item.getItemMeta().getDisplayName().contains("Gamma Planatae System")) {
				p.teleport(Bukkit.getWorld("world_planatae_gamma").getSpawnLocation());
			} else if (item.getItemMeta().getDisplayName().contains("Omega Planatae System")) {
				p.teleport(Bukkit.getWorld("world_planatae_omega").getSpawnLocation());
			} else if (item.getItemMeta().getDisplayName().contains("Titan Planatae System")) {
				p.teleport(Bukkit.getWorld("world_planatae_titan").getSpawnLocation());
			}
			p.sendMessage(ChatColor.GREEN + "Warping...");
		} else if (item.getType() == Material.BEDROCK && item.getItemMeta().getDisplayName().contains("Locked!")) {
			if (slot == 10) {
				p.sendMessage(ChatColor.RED + "To unlock this planatae system, kill a dimensional guard.");
			} else if (slot == 13) {
				p.sendMessage(ChatColor.RED + "To unlock this planatae system, kill 1,500 creepers.");
			} else if (slot == 16) {
				p.sendMessage(ChatColor.RED + "To unlock this planatae system, kill 40 titans.");
			}
		}
	}
	
	public static Inventory getPlanataeMenu(Player p) {
		SMPCore plugin = JavaPlugin.getPlugin(SMPCore.class);
		try {
			Inventory menu = GUIManagers.generateGUI(27, ChatColor.DARK_BLUE + "Planatae Menu");
			ItemStack locked = GeneralUtils.itemFromNBT("{id: \"minecraft:bedrock\", tag: {display: {Name: '{\"text\":\"Locked!\",\"color\":\"dark_red\",\"italic\":false}'}}, Count: 1b}");
			
			ItemStack gamma = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Gamma Planatae System\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;-1264346548,-236565845,-1805961568,-2023379790],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGVmMTQ3ZGRjOTA4ZTY4MjVjMjI5OTk3YWE1Mjk3NjFmNTE2OTFhMTFjOTU1MTI5YTIzMzYzMmQ1NTQ4NzVlIn19fQ==\"}]}}}}");
			ItemStack omega = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Omega Planatae System\",\"color\":\"dark_blue\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;118494323,-75739210,-1508257811,-2088513105],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdkNjZmOTM5MDlhNmQ0NjQxYzY1MzA4MmUwNDc0OTY5MWRlODJjZjc3MjMyYmQyMGFiMzJhZGY0ZiJ9fX0=\"}]}}}}");
			ItemStack titan = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Titan Planatae System\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;29464294,160580508,-1779136758,1804506042],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMzNzgzMmM0MTM1MjU1NTU1M2Q4NmFhYWUxMzQxOTMzMWUzYWRlOTk3YmNlMGI4MTEzN2Q1MjA0ZTRjZGU3ZSJ9fX0=\"}]}}}}");
			
			menu.setItem(10, (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getBoolean("killed_dimguard") || p.hasPermission("core.admin.gamemodebypass") ? gamma : locked));
			menu.setItem(13, (p.getStatistic(Statistic.KILL_ENTITY, EntityType.CREEPER) < 1500 && !(p.hasPermission("core.admin.gamemodebypass")) ? locked : omega));
			menu.setItem(16, (plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getInt("titan_kills") < 40 && !(p.hasPermission("core.admin.gamemodebypass")) ? locked : titan));
			
			return menu;
		} catch (Exception e) {
			return null;
		}
	}
	
}
