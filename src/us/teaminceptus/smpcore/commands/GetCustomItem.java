package us.teaminceptus.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.caves.AlphaCave;
import us.teaminceptus.smpcore.listeners.caves.DeltaCave;
import us.teaminceptus.smpcore.listeners.caves.TitanCave;
import us.teaminceptus.smpcore.utils.fetcher.ArenaTitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.ItemFetcher;
import us.teaminceptus.smpcore.utils.fetcher.PlanataeFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher;
import us.teaminceptus.smpcore.utils.fetcher.TitanFetcher.Potion;
import us.teaminceptus.smpcore.utils.fetcher.WandFetcher;
import us.teaminceptus.smpcore.utils.fetcher.WeaponFetcher;

public class GetCustomItem implements CommandExecutor {
	
	private SMPCore plugin;
	
	public GetCustomItem(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("getcustomitem").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
		
		Player p = (Player) sender;
		if (args.length < 1) p.sendMessage(ChatColor.RED + "Please provide an item!");
		else {
			switch (args[0].toLowerCase()) {
				case "op_sword":
					ItemStack opSword = new ItemStack(Material.NETHERITE_SWORD, 1);
					ItemMeta swordMea = opSword.getItemMeta();
					swordMea.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
					swordMea.addEnchant(Enchantment.LOOT_BONUS_MOBS, 32767, true);
					opSword.setItemMeta(swordMea);
					
					p.getInventory().addItem(opSword);
					break;
				case "totem_stack":
					ItemStack totemStack = new ItemStack(Material.TOTEM_OF_UNDYING, 32);
					p.getInventory().addItem(totemStack);
				case "titan_token":
					p.getInventory().addItem(Boss.getTitanSummoner());
					break;
				case "black_hole_candle":
					p.getInventory().addItem(ItemFetcher.getBlackHoleCandle());
					break;
				case "telehook":
					p.getInventory().addItem(ItemFetcher.getTeleportationHook());
					break;
				case "respawn_core":
					p.getInventory().addItem(ItemFetcher.getInventoryCore());
					break;
				case "perussi_wither":
					p.getInventory().addItem(WeaponFetcher.getWitherCounter());
					break;
				case "perussi_spider":
					p.getInventory().addItem(WeaponFetcher.getSpiderCounter());
					break;
				case "perussi_skeleton":
					p.getInventory().addItem(WeaponFetcher.getArcherPerussi());
					break;
				case "perussi_dragon":
					p.getInventory().addItem(WeaponFetcher.getDragonCounter());
					break;
				case "essence_golden":
					p.getInventory().addItem(ItemFetcher.getGoldenEssence());
					break;
				case "essence_iron":
					p.getInventory().addItem(ItemFetcher.getIronEssence());
					break;
				case "essence_enchanted_overworld":
					p.getInventory().addItem(ItemFetcher.getEnchantedOverworldEssence());
					break;
				case "essence_enchanted_nether":
					p.getInventory().addItem(ItemFetcher.getEnchantedNetherEssence());
					break;
				case "ferrum_core":
					p.getInventory().addItem(TitanFetcher.getFerrumCore());
					break;
				case "wand":
					if (args.length < 2) p.sendMessage(ChatColor.RED + "You need to provide a wand type!");
					else {
						switch (args[1].toLowerCase()) {
							case "explosion":
								ItemStack explosionWand = WandFetcher.getExplosionWand(p, plugin).generateWand();
								p.getInventory().addItem(explosionWand);
								break;
							case "heal":
								ItemStack healWand = WandFetcher.getHealingWand(p, plugin).generateWand();
								p.getInventory().addItem(healWand);
								break;
							case "lightning":
								ItemStack lightningWand = WandFetcher.getLightningWand(p, plugin).generateWand();
								p.getInventory().addItem(lightningWand);
								break;
							case "end":
								ItemStack endWand = WandFetcher.getEnderWand(p, plugin).generateWand();
								p.getInventory().addItem(endWand);
								break;
							case "changer":
								ItemStack immutatioWand = WandFetcher.getImmutoWand(p, plugin).generateWand();
								p.getInventory().addItem(immutatioWand);
								break;
							case "inferno":
								ItemStack infernoWand = WandFetcher.getInfernoWand(p, plugin).generateWand();
								p.getInventory().addItem(infernoWand);
								break;
							case "withermeal":
								ItemStack withermeal = WandFetcher.getWithermeal(p, plugin).generateWand();
								p.getInventory().addItem(withermeal);
								break;
							case "damage":
								ItemStack damageWand = WandFetcher.getDamageWand(p, plugin).generateWand();
								p.getInventory().addItem(damageWand);
								break;
						}
					}
					break;
				case "v3items":
					Inventory v3inv = Bukkit.createInventory(null, 54);
					for (ItemStack i : TitanFetcher.getTitanWeapons()) {
						v3inv.addItem(i);
					}
					
					for (ItemStack[] i : TitanFetcher.getTitanArmors()) {
						v3inv.addItem(i);
					}
					
					p.openInventory(v3inv);
					break;
				case "v3items2":
					Inventory v3inv2 = Bukkit.createInventory(null, 54);
					
					for (ItemStack i : TitanFetcher.getTitanWorldItems()) {
						v3inv2.addItem(i);
					}
					
					p.openInventory(v3inv2);
					break;
				case "v3items3":
					Inventory v3inv3 = Bukkit.createInventory(null, 54);
					
					for (ItemStack i : DeltaCave.getItems()) {
						v3inv3.addItem(i);
					}
					
					for (ItemStack i : AlphaCave.getItems()) {
						v3inv3.addItem(i);
					}
					
					for (ItemStack i : TitanCave.getItems()) {
						v3inv3.addItem(i);
					}
					
					p.openInventory(v3inv3);
					break;
				case "matter":
					if (args.length < 2) {
						p.sendMessage(ChatColor.RED + "You need to provide a valid type of matter!");
						return false;
					} else {
						p.getInventory().addItem(TitanCave.getMatter(args[1]));
					}
					break;
				case "v3items4": {
					Inventory items = Bukkit.createInventory(null, 54);
					
					for (int i = 0; i < PlanataeFetcher.getItems().size(); i++) {
						items.addItem(PlanataeFetcher.getItems().get(i));
					}
					
					p.openInventory(items);
					break;
				}
				case "titan_essence": {
					p.getInventory().addItem(TitanFetcher.getTitanEssence());
					break;
				}
				case "arena_sets": {
					Inventory inv = Bukkit.createInventory(null, 54);
					
					inv.addItem(ArenaTitanFetcher.getTitanAmethystusSet().values().toArray(new ItemStack[] {}));
					inv.addItem(ArenaTitanFetcher.getTitanNetheriteSet().values().toArray(new ItemStack[] {}));
					inv.addItem(ArenaTitanFetcher.getPlasmaArmor().values().toArray(new ItemStack[] {}));
					inv.addItem(ArenaTitanFetcher.getHydraSet().values().toArray(new ItemStack[] {}));
					
					inv.addItem(ArenaTitanFetcher.getArcherHelmet());
					inv.addItem(ArenaTitanFetcher.getWitherScythe());
					inv.addItem(ArenaTitanFetcher.getProtectionPotato());
					
					p.openInventory(inv);
					break;
				}
				case "v3items5": {
					Inventory inv = Bukkit.createInventory(null, 54);
					
					for (Potion pot : Potion.values()) {
						inv.addItem(TitanFetcher.generatePotion(pot));
					}
					
					p.openInventory(inv);
				}
				
			}
		}
		return false;
	}

}
