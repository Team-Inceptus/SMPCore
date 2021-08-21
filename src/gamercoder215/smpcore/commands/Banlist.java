package gamercoder215.smpcore.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.BanEntry;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import gamercoder215.smpcore.Main;

public class Banlist implements CommandExecutor {
	
	protected Main plugin;
	
	public Banlist(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("banlist").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Set<BanEntry> entries = Bukkit.getServer().getBanList(Type.NAME).getBanEntries();
		
		List<String> stringEntriesBan = new ArrayList<>();
		
		entries.forEach(entry -> {
			if (entry.getExpiration() != null) return;
			String parsedReason = ChatColor.stripColor(entry.getReason().replaceAll("\\n", "").replaceAll("You have been permanently banned!", ""));
			String parsedBegan = entry.getCreated().toString();
			
			stringEntriesBan.add(ChatColor.AQUA + entry.getTarget() + " | " + parsedBegan + "\n" + ChatColor.BLUE + "" + parsedReason + "\n");
		});
		
		sender.sendMessage(ChatColor.GREEN + "Players Banned:\n" + String.join("\n", stringEntriesBan));
		
		return false;
	}
	
	
	
}
