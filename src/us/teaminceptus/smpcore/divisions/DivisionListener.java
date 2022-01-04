package us.teaminceptus.smpcore.divisions;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.divisions.Division.Setting;

public class DivisionListener implements Listener {

	protected SMPCore plugin;
	
	public DivisionListener(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	 
	// Global Events
	
	@EventHandler
	public void onDamagePlayer(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player p)) return;
		if (!(e.getDamager() instanceof Player damager)) return;
		if (e.isCancelled()) return;
		
		if (Division.isInDivision(p) && Division.isInDivision(damager)) {
			Division div = Division.getByPlayer(p);
			
			if (div.equals(Division.getByPlayer(damager)) && (!(div.getSetting(Setting.PVP_ENABLED)) || div.getSetting(Setting.PASSIVE_ENABLED))) {
				e.setCancelled(true);
				damager.sendMessage(ChatColor.AQUA + "This person is in your division! Relax!");
			}
		} else if (Division.isInDivision(p) && !(Division.isInDivision(damager))) {
			Division div = Division.getByPlayer(p);
			if (div.getSetting(Setting.PASSIVE_ENABLED)) {
				e.setCancelled(true);
				damager.sendMessage(ChatColor.AQUA+ "This division has marked themselves as passive.");
			}
		}
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() == null) return;
		if (!(e.getDamager() instanceof Player p)) return;
		if (e.isCancelled()) return;
		
		Entity en = e.getEntity();
		if (Division.isInDivision(p)) {
			Division div = Division.getByPlayer(p);
			
			if (div.getSetting(Setting.PASSIVE_ENABLED) && e.getEntity() instanceof Player) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Your division is passive! You cannot attack other players!");
			}
			
			for (Division division : Division.getDivisions()) {
				if (division.getClaimRegion() == null) continue;
				if (!(division.equals(div)) && division.getClaimRegion().contains(en.getLocation().getX(), en.getLocation().getY(), en.getLocation().getZ())) {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "This entity is apart of " + division.getName() + " territory. You cannot damage it.");
				}
			}
		} else {
			for (Division division : Division.getDivisions()) {
				if (division.getClaimRegion() == null) continue;
				if (division.getClaimRegion().contains(en.getLocation().getX(), en.getLocation().getY(), en.getLocation().getZ())) {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "This entity is apart of " + division.getName() + " territory. You cannot damage it.");
				}
			}
		}
	}
	
	/*
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		
		for (Division div : Division.getDivisions()) {
			if (div.getClaimRegion() == null) continue;
			if (div.getClaimRegion().contains(b.getX(), b.getY(), b.getZ()) && !(div.getPlayersUUID().contains(p.getUniqueId()))) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "This region is claimed by " + ChatColor.GOLD + div.getName() + ChatColor.RED + ". You cannot build here.");
			}
		}
	}
	*/
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getPlayer() == null) return;
		Block b = e.getBlock();
		Player p = e.getPlayer();
		
		for (Division div : Division.getDivisions()) {
			if (div.getClaimRegion() == null) continue;
			if (div.getClaimRegion().contains(b.getX(), b.getY(), b.getZ()) && !(div.getPlayersUUID().contains(p.getUniqueId()))) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "This region is claimed by " + ChatColor.GOLD + div.getName() + ChatColor.RED + ". You cannot build here.");
			}
		}
	}
	
	@EventHandler
	public void onBlockExplode(EntityExplodeEvent e) {
		Entity en = e.getEntity();
		
		for (Division div : Division.getDivisions()) {
			if (div.getClaimRegion() == null) continue;
			if (div.getClaimRegion().contains(en.getLocation().getX(), en.getLocation().getY(), en.getLocation().getZ()) && !(div.getSetting(Setting.EXPLOSION_BREAK))) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		Block b = e.getBlock();
		
		for (Division div : Division.getDivisions()) {
			if (div.getClaimRegion() == null) continue;
			if (div.getClaimRegion().contains(b.getX(), b.getY(), b.getZ()) && !(div.getSetting(Setting.FIRE_BREAK))) {
				e.setCancelled(true);
			}
		}
	}
	
	static Random r = new Random();
	
	// Division XP Increase
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		LivingEntity en = e.getEntity();
		if (en.getKiller() == null) return;
		Player p = en.getKiller();
		if (Division.isInDivision(p) && r.nextInt(100) < 25) {
			Division div = Division.getByPlayer(p);
			double exp = Math.floor(Math.min((en.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / (double) r.nextInt(10)), (r.nextInt(190) + 10) * r.nextDouble()) * 10) / 10;
			div.addExperience(exp);
			
			TextComponent xpAdd = new TextComponent(ChatColor.GREEN + "+" + Double.toString(exp) + " Division Exp");
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, xpAdd);
		}
	}
	
	@EventHandler
	public void onFarm(BlockBreakEvent e) {
		if (e.getPlayer() == null) return;
		Player p = e.getPlayer();
		Block b = e.getBlock();
		BlockData data = b.getBlockData();
		
		if (r.nextInt(100) < 10 && Division.isInDivision(p) && data instanceof Ageable ageable && b.getType() != Material.FIRE && ageable.getAge() == ageable.getMaximumAge()) {
			Division div = Division.getByPlayer(p);
			double exp = Math.floor(r.nextInt(10) * r.nextDouble() * 10) / 10;
			div.addExperience(exp);
			
			TextComponent xpAdd = new TextComponent(ChatColor.GREEN + "+" + Double.toString(exp) + " Division Exp");
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, xpAdd);
		}
	}
}
