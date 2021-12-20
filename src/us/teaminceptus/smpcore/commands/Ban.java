package us.teaminceptus.smpcore.commands;


import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.google.gson.Gson;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.classes.APIPlayer;

public class Ban implements CommandExecutor {
	
	public SMPCore plugin;
	
	public Ban(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("ban").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String bumper = org.apache.commons.lang.StringUtils.repeat("\n", 35);
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Please provide a target.");
		} else {
			if (GeneralUtils.sendGETRequestStatusCode("https://api.mojang.com/users/profiles/minecraft/" + args[0]) != 200) {
				sender.sendMessage(ChatColor.RED + "This player does not exist.");
				return false;
			}
			
			Gson g = new Gson();
			UUID uuid = GeneralUtils.untrimUUID(g.fromJson(GeneralUtils.sendGETRequest("https://api.mojang.com/users/profiles/minecraft/" + args[0]), APIPlayer.class).id);
			
			OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
			
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "A reason needs to be provided.");
			} else {
				ArrayList<String> reasonArgs = new ArrayList<String>();
				for (int i = 1; i < args.length; i++) {
					reasonArgs.add(args[i]);
				}
				String reason = String.join(" ", reasonArgs);
				if (target.isOnline()) {
					Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been permanently banned!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason);
				}
				Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), bumper + ChatColor.RED + "You have been permanently banned!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason + bumper, null, sender.getName());
			}
		}
		return false;
	}

}
