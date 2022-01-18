package us.teaminceptus.smpcore.commands;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.gson.Gson;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.GeneralUtils;
import us.teaminceptus.smpcore.utils.classes.APIPlayer;

public class UnMute implements CommandExecutor {
	
	protected SMPCore plugin;
	
	public UnMute(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("unmute").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender.hasPermission("core.admin.mute"))) {
			sender.sendMessage(ChatColor.RED + "You have no power here.");
			return false;
		}
		
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Please provide a valid player.");
			return false;
		}
		
		if (GeneralUtils.sendGETRequestStatusCode("https://api.mojang.com/users/profiles/minecraft/" + args[0]) != 200) {
			sender.sendMessage(ChatColor.RED + "This player does not exist.");
			return false;
		}
		
		Gson g = new Gson();
		UUID uuid = GeneralUtils.untrimUUID(g.fromJson(GeneralUtils.sendGETRequest("https://api.mojang.com/users/profiles/minecraft/" + args[0]), APIPlayer.class).id);
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
		
		if (target == null) {
			sender.sendMessage(ChatColor.RED + "This player does not exist.");
			return false;
		}
		
		FileConfiguration config = SMPCore.getFile(target);
		
		if (!(config.getBoolean("muted"))) {
			sender.sendMessage(ChatColor.RED + "This player is not muted.");
			return false;
		}
		
		config.set("muted", false);
		sender.sendMessage(ChatColor.GREEN + "Successfully unmuted " + ChatColor.GOLD + target.getName());
		if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.GREEN + "You have been unmuted by " + sender.getName() + "!");
		
		try {
			config.save(new File(SMPCore.getPlayersDirectory(), target.getUniqueId().toString() + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
