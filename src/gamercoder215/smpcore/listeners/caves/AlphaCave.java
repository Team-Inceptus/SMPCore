package gamercoder215.smpcore.listeners.caves;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gamercoder215.smpcore.Main;
import gamercoder215.smpcore.utils.GeneralUtils;

public class AlphaCave implements Listener {
	
	protected Main plugin;
	protected Map<Material, ItemStack> drops;
	protected Map<Material, Integer> dropChance;
	protected Map<Material, Integer> exp;
	
	static Random r = new Random();
	
	public static Map<Material, ItemStack> getDrops() {
		Map<Material, ItemStack> drops = new HashMap<>();
		
		drops.put(Material.STONE, getAlphaStone());
		drops.put(Material.END_STONE, getPrimoStone());
		drops.put(Material.OBSIDIAN, getHardenedAlphaStone());
		
		return drops;
	}
	
	public static Map<Material, Integer> getDropChance() {
		Map<Material, Integer> dropChance = new HashMap<>();
		
		dropChance.put(Material.STONE, 100);
		dropChance.put(Material.END_STONE, 50);
		dropChance.put(Material.OBSIDIAN, 100);
		
		return dropChance;
	}
	
	public static Map<Material, Integer> getExp() {
		Map<Material, Integer> exp = new HashMap<>();
		
		exp.put(Material.STONE, 10);
		exp.put(Material.END_STONE, 10);
		exp.put(Material.OBSIDIAN, 40);
		
		return exp;
	}
	
	public AlphaCave(Main plugin) {
		this.plugin = plugin;
		this.drops = getDrops();
		this.exp = getExp();
		this.dropChance = getDropChance();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_alpha"))) return;
		
		Block b = e.getBlock();
		
		if (b.getType() == Material.STONE) {
			if (r.nextInt(100) < 15) {
				b.getWorld().dropItemNaturally(b.getLocation(), getAlphaLapis());
			}
		} else if (b.getType() == Material.END_STONE) {
			if (r.nextInt(100) < 10) {
				e.setCancelled(true);
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.OBSIDIAN);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlock() == null) return;
		
		for (ItemStack i : getItems()) {
			if (e.getItemInHand().isSimilar(i)) e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock() == null) return;
		if (!(e.getBlock().getWorld().getName().equalsIgnoreCase("world_caves_alpha"))) return;
		
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
	}
	
	public static ItemStack getAlphaStone() {
		ItemStack aStone = new ItemStack(Material.STONE);
		ItemMeta aMeta = aStone.getItemMeta();
		aMeta.setDisplayName(ChatColor.GRAY + "Alpha Stone");
		aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aStone.setItemMeta(aMeta);
		
		return aStone;
	}
	
	public static ItemStack getAlphaLapis() {
		ItemStack aLapis = new ItemStack(Material.LAPIS_LAZULI);
		ItemMeta aMeta = aLapis.getItemMeta();
		aMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Alpha Lapis Lazuli");
		aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		aLapis.setItemMeta(aMeta);
		
		return aLapis;
	}
	
	public static ItemStack getPrimoStone() {
		ItemStack pStone = new ItemStack(Material.END_STONE);
		ItemMeta pMeta = pStone.getItemMeta();
		pMeta.setDisplayName(ChatColor.GRAY + "Primo Stone");
		pMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		pMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pStone.setItemMeta(pMeta);

		return pStone;
	}
	
	public static ItemStack getHardenedAlphaStone() {
		ItemStack aStone = new ItemStack(Material.OBSIDIAN);
		ItemMeta aMeta = aStone.getItemMeta();
		aMeta.setDisplayName(GeneralUtils.hexToChat("2e293a", "Hardened Alpha Stone"));
		aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aStone.setItemMeta(aMeta);
		
		return aStone;
	}
	
	
	public static List<ItemStack> getItems() {
		List<ItemStack> items = new ArrayList<>();
		
		items.add(getAlphaStone());
		items.add(getPrimoStone());
		items.add(getHardenedAlphaStone());
		items.add(getAlphaLapis());
		
		items.add(getAlphaSword());
		return items;
	}
	
	public static ItemStack getAlphaSword() {
		ItemStack aSword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta aMeta = aSword.getItemMeta();
		aMeta.setDisplayName(ChatColor.DARK_GREEN + "Alpha Sword");
		aMeta.addEnchant(Enchantment.DAMAGE_ALL, 25, true);
		aMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 15, true);
		aMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 15, true);
		aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		aMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 40, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		
		aSword.setItemMeta(aMeta);
		return aSword;
	}
}
