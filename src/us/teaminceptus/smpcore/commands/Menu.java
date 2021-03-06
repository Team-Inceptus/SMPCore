package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.listeners.GUIManagers;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class Menu implements CommandExecutor {
   public SMPCore plugin;

   public Menu(SMPCore plugin) {
      this.plugin = plugin;
      plugin.getCommand("menu").setExecutor(this);
   }
   
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("GUIs can only be executed by players.");
      }
      
      try {
	      Player p = (Player) sender;
	      
	     
	      Inventory gui = GUIManagers.generateGUI(54, ChatColor.GOLD + "SMP Player Menu");
	      
	      ItemStack recipes = new ItemStack(Material.BOOK);
	      ItemMeta recipesMeta = recipes.getItemMeta();
	      recipesMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
	      recipesMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
	      recipesMeta.setDisplayName(ChatColor.YELLOW + "Recipes");
	      ArrayList<String> recipesLore = new ArrayList<String>();
	      recipesLore.add(ChatColor.GOLD + "Right click to view recipes!");
	      recipesMeta.setLore(recipesLore);
	      recipes.setItemMeta(recipesMeta);
	      gui.setItem(31, recipes);
	      
	      ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
	      SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
	      playerHeadMeta.setOwningPlayer(p);
	      playerHeadMeta.setDisplayName(ChatColor.GOLD + p.getName() + "'s Statistics");
	      
	      List<String> playerHeadLore = new ArrayList<String>();
	      playerHeadLore.add(ChatColor.RED + "" + p.getHealth() + " Health");
	      playerHeadLore.add(ChatColor.AQUA + "" + p.getExpToLevel() + " Experience Levels");
	      playerHeadLore.add(ChatColor.GOLD + "" + p.getFoodLevel() + " Food Bars");
	      playerHeadLore.add("");
	      playerHeadLore.add(ChatColor.GOLD + p.getName() + "'s Attributes");
	      playerHeadLore.add(ChatColor.RED + "Attack Damage: " + Math.floor(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()));
	      playerHeadLore.add(ChatColor.GREEN + "Armor: " + Math.floor(p.getAttribute(Attribute.GENERIC_ARMOR).getValue()));
	      playerHeadLore.add(ChatColor.DARK_GREEN + "Armor Toughness: " + Math.floor(p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue()));
	      playerHeadLore.add(ChatColor.AQUA + "Attack Speed: " + Math.floor(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue()));
	      playerHeadLore.add(ChatColor.DARK_GREEN + "Luck: " + Math.floor(p.getAttribute(Attribute.GENERIC_LUCK).getValue()));
	      playerHeadLore.add(ChatColor.RED + "Max Health: " + Math.floor(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
	      playerHeadLore.add(ChatColor.DARK_AQUA + "Movement Speed: " + Math.floor(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()));
	      
	      playerHeadMeta.setLore(playerHeadLore);
	      playerHead.setItemMeta(playerHeadMeta);
	      gui.setItem(13, playerHead);
	      
	      ItemStack bosses = new ItemStack(Material.BONE, 1);
	      ItemMeta bossesMeta = bosses.getItemMeta();
	      bossesMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
	      bossesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	      bossesMeta.setDisplayName(ChatColor.RED + "Bosses");
	      bosses.setItemMeta(bossesMeta);
	      
	      gui.setItem(30, bosses);
	      
	      ItemStack trades = new ItemStack(Material.EMERALD, 1);
	      ItemMeta tradesMeta = trades.getItemMeta();
	      tradesMeta.setDisplayName(ChatColor.GREEN + "Trades");
	      trades.setItemMeta(tradesMeta);
	      
	      gui.setItem(22, trades);
	      
	      ItemStack enderchest = new ItemStack(Material.ENDER_CHEST, 1);
	      ItemMeta ecMeta = enderchest.getItemMeta();
	      ecMeta.setDisplayName(ChatColor.DARK_PURPLE + "Ender Chest");
	      enderchest.setItemMeta(ecMeta);
	      
	      gui.setItem(32, enderchest);
	      
	      ItemStack craftingTable = new ItemStack(Material.CRAFTING_TABLE, 1);
	      ItemMeta cMeta = craftingTable.getItemMeta();
	      cMeta.setDisplayName(ChatColor.DARK_GREEN + "Crafting Menu");
	      craftingTable.setItemMeta(cMeta);
	      
	      gui.setItem(23, craftingTable);
	      
	      ItemStack bed = new ItemStack(Material.RED_BED, 1);
	      ItemMeta bMeta = bed.getItemMeta();
	      bMeta.setDisplayName(ChatColor.RED + "Teleport to Bed");
	      bed.setItemMeta(bMeta);
	      
	      gui.setItem(21, bed);
	      
	      ItemStack abilities = new ItemStack(Material.ALLIUM, 1);
	      ItemMeta aMeta = abilities.getItemMeta();
	      aMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Abilities");
	      aMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	      aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
	      abilities.setItemMeta(aMeta);
	      
	      gui.setItem(39, abilities);
	      
	      ItemStack deltaCaves = new ItemStack(Material.NETHERRACK, 1);
	      ItemMeta dMeta = deltaCaves.getItemMeta();
	      dMeta.setDisplayName(ChatColor.RED + "Delta Caves");
	      deltaCaves.setItemMeta(dMeta);
	      
	      gui.setItem(40, deltaCaves);
	      
	      ItemStack alphaCaves = new ItemStack(Material.END_STONE, 1);
	      ItemMeta alMeta = alphaCaves.getItemMeta();
	      alMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Alpha Caves");
	      alphaCaves.setItemMeta(alMeta);
	      
	      gui.setItem(41, alphaCaves);
	      
	      ItemStack planataeHub = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Planatae Hub\",\"color\":\"green\",\"italic\":false}'},SkullOwner:{Id:[I;-475097093,-1232581123,-1897520009,-1863176499],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI4OWQ1YjE3ODYyNmVhMjNkMGIwYzNkMmRmNWMwODVlODM3NTA1NmJmNjg1YjVlZDViYjQ3N2ZlODQ3MmQ5NCJ9fX0=\"}]}}}}");
	      
	      gui.setItem(49, planataeHub);
	      
	      p.openInventory(gui);
	      return true;
      } catch (Exception e) {
    	  e.printStackTrace();
    	  return false;
      }
   }
}