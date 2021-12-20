package us.teaminceptus.smpcore.commands;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.caves.AlphaCave;
import us.teaminceptus.smpcore.listeners.caves.DeltaCave;
import us.teaminceptus.smpcore.listeners.caves.TitanCave;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class Value implements CommandExecutor, Listener {

	protected SMPCore plugin;
	
	public Value(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("value").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static final Rarity COMMON = Rarity.COMMON;
	public static final Rarity UNCOMMON = Rarity.UNCOMMON;
	public static final Rarity RARE = Rarity.RARE;
	public static final Rarity EPIC = Rarity.EPIC;
	public static final Rarity LEGENDARY = Rarity.LEGENDARY;
	public static final Rarity MYTHIC = Rarity.MYTHIC;
	public static final Rarity ULTRA = Rarity.ULTRA;
	public static final Rarity SUPREME = Rarity.SUPREME;
	public static final Rarity SPECIAL = Rarity.SPECIAL;
	
	public static enum Rarity {
		COMMON(0.95),
		UNCOMMON(1.05),
		RARE(1.25),
		EPIC(1.6),
		LEGENDARY(2.25),
		MYTHIC(3),
		ULTRA(4.5),
		SUPREME(6),
		SPECIAL(9.75);
		
		
		private final double multiplier;
		
		Rarity(double multiplier) {
			this.multiplier = multiplier;
		}
		
		public final double getMultiplier() {
			return this.multiplier;
		}
		
		public final String toString() {
			String formatted = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
			
			return formatted;
		}
		
		public final String nameColor() {
			String formatted = ChatColor.BOLD + name();
			
			switch (this) {
			case UNCOMMON:
				return ChatColor.GREEN + formatted;
			case RARE:
				return ChatColor.BLUE + formatted;
			case EPIC:
				return ChatColor.DARK_PURPLE + formatted;
			case LEGENDARY:
				return ChatColor.GOLD + formatted;
			case MYTHIC:
				return ChatColor.LIGHT_PURPLE + formatted;
			case ULTRA:
				return ChatColor.AQUA + formatted;
			case SUPREME:
				return ChatColor.DARK_RED + formatted;
			case SPECIAL:
				return ChatColor.RED + formatted;
			default: {
				return ChatColor.WHITE + formatted;
			}
		}
		}
	}
	
	public static boolean isWeapon(Material m) {
		switch (m) {
			case WOODEN_SWORD:
			case WOODEN_AXE:
			case WOODEN_HOE:
			case STONE_SWORD:
			case STONE_AXE:
			case STONE_HOE:
			case IRON_SWORD:
			case IRON_AXE:
			case IRON_HOE:
			case GOLDEN_SWORD:
			case GOLDEN_AXE:
			case GOLDEN_HOE:
			case DIAMOND_SWORD:
			case DIAMOND_AXE:
			case DIAMOND_HOE:
			case NETHERITE_SWORD:
			case NETHERITE_AXE:
			case NETHERITE_HOE:
			case BOW:
			case CROSSBOW:
			case TRIDENT:
				return true;
			default: {
				return false;
			}
		}
	}
	
	public static boolean isWeapon(ItemStack item) {
		return isWeapon(item.getType());
	}
	
	public static boolean isArmor(Material m) {
		switch (m) {
			case PLAYER_HEAD:
			case ZOMBIE_HEAD:
			case CREEPER_HEAD:
			case DRAGON_HEAD:
			case TURTLE_HELMET:
			case LEATHER_HELMET:
			case LEATHER_CHESTPLATE:
			case LEATHER_LEGGINGS:
			case LEATHER_BOOTS:
			case CHAINMAIL_HELMET:
			case CHAINMAIL_CHESTPLATE:
			case CHAINMAIL_LEGGINGS:
			case CHAINMAIL_BOOTS:
			case IRON_HELMET:
			case IRON_CHESTPLATE:
			case IRON_LEGGINGS:
			case IRON_BOOTS:
			case GOLDEN_HELMET:
			case GOLDEN_CHESTPLATE:
			case GOLDEN_LEGGINGS:
			case GOLDEN_BOOTS:
			case DIAMOND_HELMET:
			case DIAMOND_CHESTPLATE:
			case DIAMOND_LEGGINGS:
			case DIAMOND_BOOTS:
			case NETHERITE_HELMET:
			case NETHERITE_CHESTPLATE:
			case NETHERITE_LEGGINGS:
			case NETHERITE_BOOTS:
				return true;
			default: {
				return false;
			}
		}
	}
	
	public static boolean isArmor(ItemStack item) {
		return isArmor(item.getType());
	}
	
	public static boolean isOre(Material m) {
		switch(m) {
			case COAL:
			case IRON_INGOT:
			case RAW_IRON:
			case GOLD_INGOT:
			case RAW_GOLD:
			case COPPER_INGOT:
			case RAW_COPPER:
			case LAPIS_LAZULI:
			case DIAMOND:
			case EMERALD:
				return true;
			default: 
				return false;
		}
	}
	
	public static long getScore(ItemStack item) {
		if (item == null) return 0;
		long score = 0;
		Material m = item.getType();
		
		if (item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			
			for (Recipe r : Bukkit.getRecipesFor(item)) {
				if (r instanceof ShapedRecipe sr) {
					Collection<ItemStack> recipes = sr.getIngredientMap().values();
					for (ItemStack i : recipes) score += getScore(i);
				} else if (r instanceof ShapelessRecipe sr) {
					List<ItemStack> recipes = sr.getIngredientList();
					for (ItemStack i : recipes) score += getScore(i);
				}
				
			}
			
			if (m.isBlock()) {
				if (meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL) && 
					(meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 1) && meta.hasDisplayName()) {
						score += 100;
					}
				if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.BOLD + "")) {
					score += 400;
				}
				
				if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.MAGIC + "")) {
					score += 700;
				}
			} else if (m.isItem()) {
				if (isWeapon(item) || isArmor(item)) {
					score += 10;
					
					if (item.getType() == Material.PLAYER_HEAD || item.getType() == Material.ZOMBIE_HEAD || item.getType() == Material.CREEPER_HEAD) {
						score += 500;
					}
					
					if (meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
						score += 500;
					}
					
					if (meta.hasEnchants()) {
						for (Enchantment ench : item.getEnchantments().keySet()) {
							int level = meta.getEnchants().get(ench);
							
							if (ench == Enchantment.DAMAGE_ALL || ench == Enchantment.ARROW_DAMAGE || ench == Enchantment.PROTECTION_ENVIRONMENTAL) score += (level * 40);
							if (ench == Enchantment.DAMAGE_ARTHROPODS || ench == Enchantment.DAMAGE_UNDEAD || ench == Enchantment.IMPALING || ench == Enchantment.PROTECTION_EXPLOSIONS
									|| ench == Enchantment.PROTECTION_FALL || ench == Enchantment.PROTECTION_FIRE || ench == Enchantment.PROTECTION_PROJECTILE) score += (level * 35);
							if (ench == Enchantment.MENDING || meta.isUnbreakable()) score += 500;
							if (ench == Enchantment.KNOCKBACK || ench == Enchantment.ARROW_KNOCKBACK || ench == Enchantment.FROST_WALKER ||
									ench == Enchantment.DEPTH_STRIDER || ench == Enchantment.OXYGEN) score += (level * 20);
							if (ench == Enchantment.LOOT_BONUS_MOBS || ench == Enchantment.THORNS) score += (level * 50);
							if (ench == Enchantment.FIRE_ASPECT || ench == Enchantment.ARROW_FIRE || ench == Enchantment.SOUL_SPEED) score += (level * 20);
							if (ench == Enchantment.ARROW_INFINITE) score += 200;
							if (ench == Enchantment.DURABILITY) score += (level * 15);
						}
					}
					
					if (meta.hasAttributeModifiers()) {
						for (Attribute a : meta.getAttributeModifiers().keySet()) {
							for (AttributeModifier mod : meta.getAttributeModifiers().get(a)) {
								long amount = (long) Math.floor(mod.getAmount());
								
								if (a == Attribute.GENERIC_ARMOR || a == Attribute.GENERIC_ATTACK_DAMAGE || a == Attribute.GENERIC_MAX_HEALTH) score += (long) Math.floor(amount * 1.5);
								if (a == Attribute.GENERIC_ARMOR_TOUGHNESS || a == Attribute.GENERIC_ATTACK_KNOCKBACK) score += (long) Math.floor(amount * 2);
								if (a == Attribute.GENERIC_ATTACK_SPEED || a == Attribute.GENERIC_KNOCKBACK_RESISTANCE || a == Attribute.GENERIC_LUCK || a == Attribute.GENERIC_ATTACK_SPEED) score += (long) Math.floor(amount * 1.25);
								
							}
						}
					}
					
					if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.BOLD + "")) score += 500;
					if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.MAGIC + "")) score += 700;
					
					// Containers
					if (TitanFetcher.getTitanWorldItems().contains(item)) score += 500;
					if (PlanataeFetcher.getItems().contains(item)) score += 500;
					if (DeltaCave.getItems().contains(item)) score += 100;
					if (AlphaCave.getItems().contains(item)) score += 500;
					if (TitanCave.getItems().contains(item)) score += 1200;
					if (meta.hasDisplayName() && meta.getDisplayName().contains("Essence")) score += 500;
					// Wands
					if (meta.hasDisplayName() && meta.getDisplayName().contains("Wand") && meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) score += 1200; 
					if (meta.hasDisplayName() && meta.getDisplayName().contains("Immutatio Wand") && meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) score += 1100;
				} else {
					if (isOre(item.getType())) {
						score += 100;
					}
					
					if (meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL) && 
							(meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 1) && meta.hasDisplayName()) {
								score += 100;
							}
					if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.BOLD + "")) {
						score += 400;
					}
					
					if (meta.hasDisplayName() && meta.getDisplayName().contains(ChatColor.MAGIC + "")) {
						score += 700;
					}
				}
				
				
			}
		}
		
		
		return score;
	}
	
	public static Rarity getRarity(ItemStack item) {
		long score = getScore(item);
		
		if (score < 100) return COMMON;
		else if (score >= 100 && score < 500) return UNCOMMON;
		else if (score >= 500 && score < 1200) return RARE;
		else if (score >= 1200 && score < 2300) return EPIC;
		else if (score >= 2300 && score < 5500) return LEGENDARY;
		else if (score >= 5500 && score < 12000) return MYTHIC;
		else if (score >= 12000 && score < 35000) return ULTRA;
		else if (score >= 35000 && score < 50000) return SUPREME;
		else if (score >= 50000) return SPECIAL;
		else return COMMON;
	}
	
	public static byte toNMSSlot(byte slot) {
		// HotBar
		if (slot == 0) return 36;
		else if (slot == 1) return 37;
		else if (slot == 2) return 38;
		else if (slot == 3) return 39;
		else if (slot == 4) return 40;
		else if (slot == 5) return 41;
		else if (slot == 6) return 42;
		else if (slot == 7) return 43;
		else if (slot == 8) return 44;
		// Armor & Offhand
		else if (slot == 36) return 8;
		else if (slot == 37) return 7;
		else if (slot == 38) return 6;
		else if (slot == 39) return 5;
		else if (slot == 40) return 45;
		else return slot;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length < 1) {
			if (!(sender instanceof Player p)) return false;
			if (p.getInventory().getItemInMainHand() == null) {
				p.sendMessage(ChatColor.RED + "Please put an item to value in your hand!");
				return false;
			}
			
			ItemStack item = p.getInventory().getItemInMainHand();
			p.sendMessage(ChatColor.GREEN + "Calculating...");
			
			Rarity rarity = getRarity(item);
			long score = getScore(item);
			double value = score * rarity.getMultiplier();
			p.sendMessage(ChatColor.GREEN + "Item Score: " + Long.toString(score));
			p.sendMessage(ChatColor.GREEN + "Rarity: " + rarity.nameColor());
			p.sendMessage(ChatColor.GREEN + "Rarity Multiplier: x" + Double.toString(rarity.getMultiplier()));
			p.sendMessage(ChatColor.DARK_GREEN + "\nNoobucks Value: " + ChatColor.GOLD + GeneralUtils.withSuffix(value));
		} else if (args[0].equalsIgnoreCase("all")) {
			if (args.length < 2) {
			if (!(sender instanceof Player p)) return false;
			p.sendMessage(ChatColor.GREEN + "Calculating Networth...");
			double echestValue = 0;
			for (ItemStack i : p.getEnderChest()) {
				echestValue += getScore(i) * getRarity(i).getMultiplier();
			}
			
			double invValue = 0;
			for (ItemStack i : p.getInventory()) {
				invValue += getScore(i) * getRarity(i).getMultiplier();
			}
			p.sendMessage(ChatColor.DARK_GREEN + "Ender Chest Value - " + Double.toString(Math.floor(echestValue * 100) / 100));
			p.sendMessage(ChatColor.DARK_GREEN + "Inventory Value - " + Double.toString(Math.floor(invValue * 100) / 100));
			p.sendMessage(ChatColor.GREEN + "\nTotal Networth: " + ChatColor.GOLD + GeneralUtils.withSuffix(invValue + echestValue) + " Noobucks");
			
			} else {
				if (Bukkit.getPlayer(args[1]) == null) {
					sender.sendMessage(ChatColor.GREEN + "This player does not exist!");
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				double echestValue = 0;
				for (ItemStack i : target.getEnderChest()) {
					echestValue += getScore(i) * getRarity(i).getMultiplier();
				}
				
				double invValue = 0;
				for (ItemStack i : target.getInventory()) {
					invValue += getScore(i) * getRarity(i).getMultiplier();
				}
				sender.sendMessage(ChatColor.DARK_GREEN + "Ender Chest Value - " + Double.toString(Math.floor(echestValue * 100) / 100));
				sender.sendMessage(ChatColor.DARK_GREEN + "Inventory Value - " + Double.toString(Math.floor(invValue * 100) / 100));
				sender.sendMessage(ChatColor.GREEN + "\n" + target.getName() + "'s Total Networth: " + ChatColor.GOLD + GeneralUtils.withSuffix(invValue + echestValue) + " Noobucks");
				
			}
		}
		return true;
		
	}
	
	public static boolean containsRarity(ItemStack i) {
		if (!(i.hasItemMeta())) return false;
		if (!(i.getItemMeta().hasLore())) return false;
		
		for (Rarity r : Rarity.values()) {
			if (i.getItemMeta().getLore().contains(r.nameColor())) return true;
			else continue;
		}
		
		return false;
	}
	
	public static ItemStack validate(ItemStack i) {
		if (!(i.hasItemMeta())) return i;
		if (!(i.getItemMeta().hasLore())) return i;
		List<String> lore = i.getItemMeta().getLore();
		int occurences = 0;
		for (Rarity r : Rarity.values()) {
			if (lore.contains(r.nameColor())) {
				occurences++;
			}
		}
		
		if (occurences > 1) {
			for (String s : lore) {
				for (Rarity r : Rarity.values()) {
					if (s.equalsIgnoreCase(r.nameColor())) lore.remove(r.nameColor());
				}
			}
			int target = (i.getItemMeta().hasLore() ? i.getItemMeta().getLore().size() : 0);
			ItemMeta newMeta = i.getItemMeta();
			lore.add(target, getRarity(i).nameColor());
			newMeta.setLore(lore);
			i.setItemMeta(newMeta);
			return i;
		}
		
		try {
			if (getRarity(i) != Rarity.valueOf(ChatColor.stripColor(lore.get(lore.size() - 1)))) {
				ItemMeta newMeta = i.getItemMeta();
				lore.set(lore.size() - 1, getRarity(i).nameColor());
				newMeta.setLore(lore);
				i.setItemMeta(newMeta);
				return i;
			}
		} catch (IllegalArgumentException e) {
			// Do Nothing, Item is outside of plugin (ex. CraftEnhance turn page)
		}
		
		return i;
	}
	

}
