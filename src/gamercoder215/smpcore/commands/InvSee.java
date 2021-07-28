package gamercoder215.smpcore.commands;

import gamercoder215.smpcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvSee implements CommandExecutor {
   public Main plugin;

   public InvSee(Main plugin) {
      this.plugin = plugin;
      plugin.getCommand("invsee").setExecutor(this);
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Only players can see inventories!");
      }

      Player p = (Player)sender;
      if (args[0] == null) {
         p.sendMessage(ChatColor.RED + "Please provide a user!");
      } else if (Bukkit.getPlayer(args[0]) == null) {
         p.sendMessage(ChatColor.RED + "This player does not exist.");
      } else {
         Inventory playerInv = Bukkit.getPlayer(args[0]).getInventory();
         p.openInventory(playerInv);
         p.sendMessage(ChatColor.GREEN + "Look at player " + ChatColor.GOLD + args[0] + "'s" + ChatColor.GREEN + " inventory.");
      }

      return false;
   }
}