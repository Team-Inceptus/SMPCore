package us.teaminceptus.smpcore.commands;

import java.util.ArrayList;
import java.util.Date;
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

public class Suspend implements CommandExecutor {
	
	public SMPCore plugin;
	
	public Suspend(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("suspend").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String bumper = org.apache.commons.lang.StringUtils.repeat("\n", 35);
		try {
			if (sender.hasPermission("core.admin.suspend")) {
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
						sender.sendMessage(ChatColor.RED + "Please provide a valid date, in days. For example: /suspend <target> 7 <reason> (7 days)");
					} else {
						String timeParsed = args[1] + " Days";
						int days = Integer.parseInt(args[1]);
						Date time = new Date(System.currentTimeMillis() + (60 * 60 * 24 * days * 1000));
						if (args.length < 3) {
							sender.sendMessage(ChatColor.RED + "A reason needs to be provided.");
						} else {
							ArrayList<String> reasonArgs = new ArrayList<String>();
							for (int i = 2; i < args.length; i++) {
								reasonArgs.add(args[i]);
							}
							String reason = String.join(" ", reasonArgs);
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.YELLOW + "You have been suspended!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason + ChatColor.GOLD + "\nTime: " + ChatColor.AQUA + timeParsed);
							}
							Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), bumper + ChatColor.YELLOW + "You have been suspended!\n\n" + ChatColor.GOLD + "Admin: " + ChatColor.DARK_RED + sender.getName() + ChatColor.GOLD + "\nReason: " + ChatColor.WHITE + reason + ChatColor.GOLD + "\nTime: " + ChatColor.AQUA + timeParsed + bumper, time, sender.getName());
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}
		} catch (NumberFormatException e) {
			sender.sendMessage(ChatColor.RED + "Please provide a valid date, in days. For example: /suspend <target> 7 <reason> (7 days)");
		}
		return false;
	}

}
