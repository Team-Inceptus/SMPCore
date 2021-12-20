package us.teaminceptus.smpcore.commands;

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

import us.teaminceptus.smpcore.SMPCore;

public class Suspendlist implements CommandExecutor {
	
	protected SMPCore plugin;
	
	public Suspendlist(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("suspendlist").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Set<BanEntry> entries = Bukkit.getServer().getBanList(Type.NAME).getBanEntries();
		
		List<String> stringEntriesBan = new ArrayList<>();
		
		entries.forEach(entry -> {
			if (entry.getExpiration() == null) return;
			String parsedReason = ChatColor.stripColor(entry.getReason().replaceAll("\\n", "").replaceAll("You have been suspended!", ""));
			String parsedBegan = entry.getCreated().toString();
			
			stringEntriesBan.add(ChatColor.AQUA + entry.getTarget() + " | " + parsedBegan + "\n" + ChatColor.BLUE + "Reason: " + parsedReason + "\n");
		});
		
		sender.sendMessage(ChatColor.GREEN + "Players Suspended:\n" + String.join("\n", stringEntriesBan));
		
		return false;
	}
	
	
	
}
