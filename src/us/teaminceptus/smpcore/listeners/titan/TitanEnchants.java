package us.teaminceptus.smpcore.listeners.titan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.advancements.TitanAdvancements;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.listeners.titan.TitanEnchantment.TitanEnchant;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;

public class TitanEnchants implements Listener {
	
	public SMPCore plugin;
	
	public TitanEnchants(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	// TODO Inventory
	
	public void notEligible(Player p) {
		new BukkitRunnable() {
			public void run() {
				p.sendMessage(ChatColor.RED + "The item in the slot is not eligible or is not the correct type.");
				p.closeInventory();
			}
		}.run();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() == null) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getView() == null) return;
		if (e.getClickedInventory() == null) return;
		InventoryView inv = e.getView();
		if (!(inv.getTitle().contains("Titan Enchant Table"))) return;
		if (e.getCurrentItem() == null) return;
		
		Inventory titanEtable = inv.getTopInventory();
		ItemStack currentItem = e.getCurrentItem();
		
		if (currentItem.isSimilar(GUIManagers.getInventoryPlaceholder())) e.setCancelled(true);
		if (currentItem.isSimilar(TitanEnchantmentTable.getInnerPlaceholder())) e.setCancelled(true);
		if (currentItem.getType().equals(Material.ENCHANTING_TABLE)) e.setCancelled(true);
		if (currentItem.getType().equals(Material.ENCHANTED_BOOK)) e.setCancelled(true);
		
		ItemStack itemToEnchant = titanEtable.getItem(49);

		
		// Inventory playerInv = inv.getBottomInventory();
		
		if (currentItem.getType().equals(Material.ENCHANTED_BOOK)) {
			// Important Pre-Checks
			if (titanEtable.getItem(49) == null) {
				notEligible(p);
				return;
			}
			TitanEnchantment enchant = TitanEnchantment.fromItemStack(currentItem);
			TitanEnchant ench = TitanEnchantment.parseString(currentItem.getItemMeta().getLore().get(0));
			int level = p.getLevel();
			String notEnoughLevels = ChatColor.RED + "You don't have enough XP Levels to enchant this item!";
			String notEnoughMats = ChatColor.RED + "You don't have the required materials to enchant this item!";
			List<String> itemLore = itemToEnchant.getItemMeta().hasLore() ? itemToEnchant.getItemMeta().getLore() : new ArrayList<String>();
			
			String conflict = ChatColor.RED + "You have a conflicting enchantment!";
			// Conflicting/Duplicate Enchants
			if (itemLore.contains(enchant.name)) {
				p.sendMessage(ChatColor.RED + "Your item already contains this enchantment!");
				return;
			} else if (itemLore.contains("Beforlus") && enchant.name.contains("Beforlus")) {
				p.sendMessage(conflict);
				return;
			} else if (itemLore.contains("Longshot") && enchant.name.contains("Penta-Shot")) {
				p.sendMessage(conflict);
				return;
			} else if (itemLore.contains("Penta-Shot") && enchant.name.contains("Longshot")) {
				p.sendMessage(conflict);
				return;
			}
			
			if (level < 300) {
				p.sendMessage(notEnoughLevels);
			} else {
				try {
					ItemStack novaStack = TitanFetcher.getNova();
					novaStack.setAmount(64);
					
					ItemStack refinedWither = GeneralUtils.itemFromNBT("{id: \"minecraft:coal\", Count: 1b, tag: {HideFlags: 1, display: {Name: '{\"text\":\"Refined Wither Material\",\"color\":\"dark_gray\",\"bold\":true,\"italic\":false}'}, Enchantments: [{lvl: 1s, id: \"minecraft:protection\"}]}}");
					ItemStack refinedWitherStack = refinedWither;
					refinedWitherStack.setAmount(64);
					
					
					ItemStack cStack = TitanFetcher.getConstibilis();
					cStack.setAmount(64);
					
					ItemStack clarusStack = TitanFetcher.getClarus();
					clarusStack.setAmount(64);
					if (ench.equals(TitanEnchant.DOUBLE_DAMAGE)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 640))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(novaStack, novaStack, novaStack,
									novaStack, novaStack, novaStack,novaStack, novaStack, novaStack, novaStack);
						}
					} else if (ench.equals(TitanEnchant.WEAKENING)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getConstibilis(), 320))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(cStack, cStack, cStack, cStack, cStack);
						}
					} else if (ench.equals(TitanEnchant.WITHERING)) {
						if (!(p.getInventory().containsAtLeast(refinedWither, 64)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 640))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(refinedWitherStack);
							p.getInventory().removeItem(novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.POISONING)) {
						if (!(p.getInventory().containsAtLeast(new ItemStack(Material.PUFFERFISH), 16)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 640))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(new ItemStack(Material.PUFFERFISH, 16));
							p.getInventory().removeItem(novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_FIRE)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 832))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_EXPLOSION)) {
						if (!(p.getInventory().containsAtLeast(new ItemStack(Material.TNT), 64)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 704))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(new ItemStack(Material.TNT, 64));
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_PROJECTILE)) {
						if (!(p.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 128)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 704))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
							p.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
							
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_LIGHTNING)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getClarus(), 128)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 704))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(clarusStack, clarusStack);
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_POTION)) {
						if (!(p.getInventory().containsAtLeast(new ItemStack(Material.BLAZE_ROD), 320)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 704))) {
							p.sendMessage(notEnoughMats);
						} else {
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
							
							p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.BLAZE_ROD, 64), 
									new ItemStack(Material.BLAZE_ROD, 64), new ItemStack(Material.BLAZE_ROD, 64));
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack);
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_FALLING)) {
						if (!(p.getInventory().containsAtLeast(new ItemStack(Material.FEATHER), 384)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 704))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(new ItemStack(Material.FEATHER, 64), new ItemStack(Material.FEATHER, 64), 
									new ItemStack(Material.FEATHER, 64), new ItemStack(Material.FEATHER, 64), new ItemStack(Material.FEATHER, 64), 
									new ItemStack(Material.FEATHER, 64));
							p.getInventory().removeItem(novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack);
							
						}
					} else if (ench.equals(TitanEnchant.BEFORLUS_WITHER)) {
						if (!(p.getInventory().containsAtLeast(refinedWither, 256)) && !(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 960)) && !(p.getInventory().containsAtLeast(TitanFetcher.getExitatus(), 256))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							ItemStack exitatusStack = TitanFetcher.getExitatus();
							exitatusStack.setAmount(64);
							
							p.getInventory().removeItem(refinedWitherStack,refinedWitherStack,refinedWitherStack,refinedWitherStack);
							p.getInventory().removeItem(novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack,novaStack,
									novaStack,novaStack,novaStack,novaStack,novaStack);
							p.getInventory().removeItem(exitatusStack,exitatusStack,exitatusStack,exitatusStack);
						}
					} else if (ench.equals(TitanEnchant.SMELTING)) {
						if (!(p.getInventory().containsAtLeast(TitanFetcher.getNova(), 128))) {
							p.sendMessage(notEnoughMats);
							return;
						} else {
							p.getInventory().removeItem(novaStack, novaStack);
						}
					} else {
						p.sendMessage(ChatColor.RED + "This Enchantment is not released yet!");
						return;
					}
					p.setLevel(level - 300);
					ItemMeta itemMeta = itemToEnchant.getItemMeta();
					List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<String>();
					lore.add(enchant.name);
					itemMeta.setLore(lore);
					itemToEnchant.setItemMeta(itemMeta);
					titanEtable.setItem(49, itemToEnchant);
					
					
					TitanAdvancements.grantEnchanterAdvancement(p);
				} catch (CommandSyntaxException err) {
					p.sendMessage(ChatColor.RED + "There was an error enchanting this item.");
					err.printStackTrace();
				}
			}
			
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 3F, 1F);
		}
		
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getPlayer() == null) return;
		Player p = (Player) e.getPlayer();
		if (e.getView() == null) return;
		if (e.getInventory() == null) return;
		InventoryView view = e.getView();
		
		if (!(view.getTitle().contains("Titan Enchant Table"))) return;
		if (view.getItem(49) != null) {
			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItemNaturally(p.getLocation(), view.getItem(49));
			} else {
				p.getInventory().addItem(view.getItem(49));
			}
		}
	}
	
	// TODO Enchants
	@EventHandler
	public void onDamageDefensiveArmor(EntityDamageEvent e) {
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		
		for (ItemStack i : p.getInventory().getArmorContents()) {
			if (i == null) return;
			if (!(i.hasItemMeta())) return;
			if (!(i.getItemMeta().hasLore())) return;
			
			List<String> lore = i.getItemMeta().getLore();
			
			for (String li : lore) {
				if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_EXPLOSION) && (e.getCause().equals(DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(DamageCause.BLOCK_EXPLOSION))) {
					e.setCancelled(true);
				} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_FIRE) && (e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.LAVA))) {
					e.setCancelled(true);
				} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_LIGHTNING) && e.getCause().equals(DamageCause.LIGHTNING)) {
					e.setCancelled(true);
				} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_POTION) && (e.getCause().equals(DamageCause.POISON) || e.getCause().equals(DamageCause.MAGIC))) {
					e.setCancelled(true);
				} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_PROJECTILE) && e.getCause().equals(DamageCause.PROJECTILE)) {
					e.setCancelled(true);
				} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_WITHER) && e.getCause().equals(DamageCause.WITHER)) {
					e.setCancelled(true);
				}
			}
			
		}
	}
	
	@EventHandler
	public void onDamageDefensiveBoots(EntityDamageEvent e) {
		if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getEntity();
		
		if (p.getInventory().getBoots() == null) return;
		
		ItemStack boots = p.getInventory().getBoots();
		
		if (!(boots.hasItemMeta())) return;
		if (!(boots.getItemMeta().hasLore())) return;
		
		for (String li : boots.getItemMeta().getLore()) {
			 if (TitanEnchantment.parseString(li).equals(TitanEnchant.BEFORLUS_FALLING) && e.getCause().equals(DamageCause.FALL)) {
				e.setCancelled(true);
			 }
		}
	}
	
	@EventHandler
	public void onDamageOffensive(EntityDamageByEntityEvent e) {
		if (e.getDamager() == null) return;
		if (!(e.getDamager().getType().equals(EntityType.PLAYER))) return;
		
		Player p = (Player) e.getDamager();
		
		if (p.getInventory().getItemInMainHand() == null && p.getInventory().getItemInOffHand() == null) return;
		ItemStack item = p.getInventory().getItemInMainHand() == null ? p.getInventory().getItemInOffHand() : p.getInventory().getItemInMainHand();
		
		if (!(item.hasItemMeta())) return;
		if (!(item.getItemMeta().hasLore())) return;
		
		for (String li : item.getItemMeta().getLore()) {
			 if (TitanEnchantment.parseString(li).equals(TitanEnchant.DOUBLE_DAMAGE)) {
				e.setDamage(e.getDamage() * 2);
			} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.WITHERING)) {
				if (!(e.getEntity() instanceof LivingEntity)) return;
				LivingEntity en = (LivingEntity) e.getEntity();
				en.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4, true, true, true));
			} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.WEAKENING)) {
				if (!(e.getEntity() instanceof LivingEntity)) return;
				LivingEntity en = (LivingEntity) e.getEntity();
				en.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 6, true, true, true));
			} else if (TitanEnchantment.parseString(li).equals(TitanEnchant.POISONING)) {
				if (!(e.getEntity() instanceof LivingEntity)) return;
				LivingEntity en = (LivingEntity) e.getEntity();
				en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 5, true, true, true));
			}
		}
	}
}
