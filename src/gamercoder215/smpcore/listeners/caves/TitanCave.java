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

	public static ItemStack getMatter(String type) {
		try {
			if (type.equalsIgnoreCase("raw")) {
				ItemStack rawMatter = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Raw Matter\",\"color\":\"dark_blue\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;-16919354,124731398,-1680564083,-1619545682],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA5NWE3ZmQ5MGRhYTFiYmU3MDY5MDg5NzQwZTA1ZDBiZmM2NjI5NmVlM2M0MGVlNzFhNGUwYTY2MTZiMmJiYyJ9fX0=\"}]}}}}");

				return rawMatter;
			} else if (type.equalsIgnoreCase("dark")) {
				ItemStack darkMatter = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Dark Matter\",\"color\":\"dark_purple\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;-1089212003,1212826785,-1484138629,-404192881],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg4MTMwOTI1NmEwNjQxMzVjMDlkNDhiNzM4ODgxYzcwMmU5Y2RjMTMwNjJkYzk5MjdjZWM0ZWM0ZmU1ZWQ3YiJ9fX0=\"}]}}}}");

				return darkMatter;
			} else if (type.equalsIgnoreCase("quantumn")) {
				ItemStack quantumnMatter = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Quantumn Matter\",\"color\":\"light_purple\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;1048396531,2059159011,-1939323280,-630683356],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0=\"}]}}}}");

				return quantumnMatter;
			} else if (type.equalsIgnoreCase("quark_plasma")) {
				ItemStack quarkPlasma = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Quark Plasma\",\"color\":\"dark_green\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;-1371085713,996951556,-2092667368,-1447930404],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjViNWZhYThlNDgxZmNiODRjYmVmMWU1YzQyMGQ2YTgxYTZlNjhmNWEwNzUwMDFhMDI4ODI1YWMyMDE4ZWJlNyJ9fX0=\"}]}}}}");

				return quarkPlasma;
			} else if (type.equalsIgnoreCase("fermionic")) {
				ItemStack fermionicMatter = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Fermionic Matter\",\"color\":\"#FFA000\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;-1129330477,1414415070,-1721945380,-1457986176],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmJhOTlkMDc0OWIzOGM0NGJjMTU5ZTRjNzEwZGRmNGU2MWNlYzEyNGM2NjllODk1MTBlN2I2NGNiOWQ0YjI0NyJ9fX0=\"}]}}}}");

				return fermionicMatter;
			} else if (type.equalsIgnoreCase("degenerative")) {
				ItemStack degenerativeMatter = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Degenerative Matter\",\"color\":\"#D1C300\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;2033765199,245321654,-1719796429,-201417324],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMzNTI4OGMxZDI5ZjVhZWM1NTdjYWY2NjcxY2VlYTJiNjZlMzQ0NTE4MDlkZjAxY2RjZDg1MDM5ZjA0MjFhMiJ9fX0=\"}]}}}}");

				return degenerativeMatter;
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Map<EquipmentSlot, ItemStack> getQuantumnSet() {
		Map<EquipmentSlot, ItemStack> quantumnSet = new HashMap<>();

		ItemStack quantumnSword = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta sMeta = quantumnSword.getItemMeta();

		sMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Quantumn Sword");

		sMeta.addEnchant(Enchantment.DAMAGE_ALL, 800, true);
		sMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 650, true);
		sMeta.addEnchant(Enchantment.DAMAGE_ARTHROPOD, 650, true);
		sMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 250, true);
		sMeta.addEnchant(Enchantment.KNOCKBACK, 25, true);
		sMeta.addEnchant(Enchantment.FIRE_ASPECT, 200, true);

		sMeta.setUnbreakable(true);

		sMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 11250, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		sMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 11250, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		sMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 700, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		sMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 700, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		sMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 625, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		sMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 625, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));

		quantumnSword.setItemMeta(sMeta);

		quantumnSet.put(EquipmentSlot.HAND, quantumnSword);

		return quantumnSet;
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
