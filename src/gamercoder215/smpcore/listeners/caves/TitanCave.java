package gamercoder215.smpcore.listeners.caves;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.GeneralUtils;
import gamercoder215.smpcore.utils.entities.TitanPiglin;

public class TitanCave implements Listener {

	protected Main plugin;
	protected Map<Material, ItemStack> drops;
	protected Map<Material, Integer> dropChance;
	protected Map<Material, Integer> exp;
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.BASALT, getRasa());
		drops.put(Material.SMOOTH_BASALT, getRasa());
		drops.put(Material.BLACKSTONE, getAter());
		drops.put(Material.MAGMA_BLOCK, getCaldus());
		drops.put(Material.GLOWSTONE, getPlasma());
		
		return drops;
	}
	
	public static Map<Material, Integer> getDropChance() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.BASALT, 100);
		dropChance.put(Material.SMOOTH_BASALT, 75);
		dropChance.put(Material.BLACKSTONE, 100);
		dropChance.put(Material.MAGMA_BLOCK, 100);
		dropChance.put(Material.GLOWSTONE, 10);
		
		return dropChance;
	}
	
	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.BASALT, 15);
		exp.put(Material.SMOOTH_BASALT, 20);
		exp.put(Material.BLACKSTONE, 15);
		exp.put(Material.MAGMA_BLOCK, 10);
		exp.put(Material.GLOWSTONE, 35);
		
		return exp;
	}
	
	static Random r = new Random();
	
	public TitanCave(Main plugin) {
		this.plugin = plugin;
		this.drops = getDrops();
		this.exp = getExp();
		this.dropChance = getDropChance();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_titan"))) return;
		
		e.setDropItems(false);
		e.setExpToDrop(0);
		
		if (drops.containsKey(e.getBlock().getType())) {
			ItemStack drop = drops.get(e.getBlock().getType());
			
			int chance = this.dropChance.get(e.getBlock().getType());
			
			if (r.nextInt(100) < chance) {
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
				
				if (r.nextBoolean()) 
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 2F);
				
				if (this.exp != null) {
					e.setExpToDrop(this.exp.get(e.getBlock().getType()));
				}
			}
		}
		
		if (r.nextInt(100) < 15) {
			TitanPiglin p = new TitanPiglin(e.getPlayer().getLocation(), (r.nextInt(100) < 25));
			((CraftWorld) e.getBlock().getWorld()).getHandle().addEntity(p);
		}
	}
	
	public static ItemStack getRasa() {
		ItemStack rasa = new ItemStack(Material.BASALT, 1);
		ItemMeta rMeta = rasa.getItemMeta();
		rMeta.setDisplayName(ChatColor.GRAY + "Rasa");
		
		rasa.setItemMeta(rMeta);
		
		return rasa;
	}
	
	public static ItemStack getAter() {
		ItemStack ater = new ItemStack(Material.BLACKSTONE);
		ItemMeta aMeta = ater.getItemMeta();
		aMeta.setDisplayName(ChatColor.DARK_GRAY + "Ater");
		
		ater.setItemMeta(aMeta);
		
		return ater;
	}
	
	public static ItemStack getCaldus() {
		ItemStack caldus = new ItemStack(Material.MAGMA_BLOCK);
		ItemMeta cMeta = caldus.getItemMeta();
		cMeta.setDisplayName(ChatColor.GOLD + "Caldus");
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		caldus.setItemMeta(cMeta);
		
		return caldus;
	}
	
	public static ItemStack getPlasma() {
		try {
			ItemStack plasma = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Plasma\",\"color\":\"gold\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;324366184,1666009919,-1905030642,-840027344],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg5MDQyMDgyYmI3YTc2MThiNzg0ZWU3NjA1YTEzNGM1ODgzNGUyMWUzNzRjODg4OTM3MTYxMDU3ZjZjNyJ9fX0=\"}]}}}}");
			return plasma;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<ItemStack> getItems() {
		List<ItemStack> items = new ArrayList<>();
		
		items.add(getAter());
		items.add(getCaldus());
		items.add(getRasa());
		items.add(getPlasma());
		
		return items;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!(p.getWorld().getName().equalsIgnoreCase("world_caves_titan"))) return;
		
		if (p.isInWater()) p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 5, 3, true, true, false));
		
	}
	
	@EventHandler
	public void onPortalExit(EntityPortalEnterEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
	
		if (!(p.getWorld().getName().equalsIgnoreCase("world_titan"))) return;
		p.teleport(new Location(Bukkit.getWorld("world_caves_titan"), -62, 71, -56, p.getLocation().getYaw(), p.getLocation().getPitch()));
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlock() == null) return;
		
		for (ItemStack i : getItems()) {
			if (e.getItemInHand().isSimilar(i)) e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		if (!(p.getWorld().getName().equalsIgnoreCase("world_caves_titan"))) return;
		
		if (e.getCause() == DamageCause.FIRE) e.setCancelled(true);
		if (e.getCause() == DamageCause.FIRE_TICK) e.setCancelled(true);
		if (e.getCause() == DamageCause.LAVA) e.setCancelled(true);
	}

}
