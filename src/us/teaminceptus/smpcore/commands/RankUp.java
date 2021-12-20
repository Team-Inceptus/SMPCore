package us.teaminceptus.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.utils.PermissionUtils;

public class RankUp implements CommandExecutor {

	protected SMPCore plugin;
	
	public RankUp(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("rankup").setExecutor(this);
	}
	
	public static void setRank(Player p, String rank) {
		SMPCore plugin = JavaPlugin.getPlugin(SMPCore.class);
		
		plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set("rank", rank);
		
		if (rank.equalsIgnoreCase("vip")) {
			p.setDisplayName(ChatColor.DARK_GREEN + "VIP " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
			p.setPlayerListName(ChatColor.DARK_GREEN + "[VIP] " + ChatColor.GREEN + p.getName() + ChatColor.RESET);
		} else if (rank.equalsIgnoreCase("vip+")) {
			p.setDisplayName(ChatColor.GREEN + "VIP+ " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
			p.setPlayerListName(ChatColor.GREEN + "[VIP+] " + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET);
		} else if (rank.equalsIgnoreCase("mvp")) {
			p.setDisplayName(ChatColor.AQUA + "MVP " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
			p.setPlayerListName(ChatColor.AQUA + "[MVP] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
		} else if (rank.equalsIgnoreCase("mvp+")) {
			p.setDisplayName(ChatColor.DARK_PURPLE + "MVP+ " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
			p.setPlayerListName(ChatColor.DARK_PURPLE + "[MVP+] " + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
		}
		
		PermissionUtils.giveDefaultPermissions(plugin, p);
		return;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) return false;
		if (p.hasPermission("core.admin.gamemodebypass")) {
			p.sendMessage(ChatColor.RED + "Non-admins only.");
			return false;
		}
		double daysPlayed = (p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20 * 60 * 60 * 24));
		
		if (daysPlayed > 1 && daysPlayed < 3) {
			setRank(p, "vip");
		} else if (daysPlayed > 3 && daysPlayed < 5) {
			setRank(p, "vip+");
		} else if (daysPlayed > 5 && daysPlayed < 8) {
			setRank(p, "mvp");
		} else if (daysPlayed > 8) {
			setRank(p, "mvp+");
		}
		
		return true;
	}

}
