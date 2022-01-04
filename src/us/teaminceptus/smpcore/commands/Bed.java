package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import us.teaminceptus.smpcore.SMPCore;

public class Bed implements CommandExecutor {
   ArrayList<UUID> list = new ArrayList<UUID>();
   private SMPCore plugin;

   public Bed(SMPCore plugin) {
      this.plugin = plugin;
      plugin.getCommand("bed").setExecutor(this);
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("You do not have access to this command.");
      }

      Player p = (Player)sender;
      Location bed = p.getBedSpawnLocation();
      if (bed == null) {
         p.sendMessage(ChatColor.RED + "It seems you do not have a bed spawnpoint. Make sure you set one!");
      } else if (list.contains(p.getUniqueId())) {
         p.sendMessage(ChatColor.RED + "You are currently on a cooldown!");
      } else {
         int x = bed.getBlockX();
         int y = bed.getBlockY();
         int z = bed.getBlockZ();
         p.sendMessage(ChatColor.GREEN + "Found your bed at " + ChatColor.GOLD + x + " " + y + " " + z + ChatColor.GREEN + "! Teleporting...");
         p.teleport(bed);
         this.list.add(p.getUniqueId());
         (new BukkitRunnable() {
            public void run() {
               Bed.this.list.remove(p.getUniqueId());
               p.sendMessage(ChatColor.GREEN + "Your cooldown for " + ChatColor.GOLD + "/bed" + ChatColor.GREEN + " has been refreshed!");
            }
         }).runTaskLater(this.plugin, 600L);
      }

      return false;
   }
}