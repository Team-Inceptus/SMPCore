package gamercoder215.smpcore.commands;

import gamercoder215.smpcore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
   public Main plugin;

   public Help(Main plugin) {
      this.plugin = plugin;
      plugin.getCommand("help").setExecutor(this);
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      Player p = (Player)sender;
      p.sendMessage("" + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.DARK_GREEN + "TheNoobyGodsSMP Help" + ChatColor.RESET + "\n\n" + ChatColor.GREEN + "• Questions or Suggestions? Contact a God on Discord -> https://discord.io/thenoobygods\n\n• Found a Bug? DM GamerCoder215#2640");
      return false;
   }
}