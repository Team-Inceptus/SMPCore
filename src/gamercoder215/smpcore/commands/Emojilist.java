package gamercoder215.smpcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import gamercoder215.smpcore.Main;

public class Emojilist implements CommandExecutor {
	
	public Main plugin;
	
	public Emojilist(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("emojilist").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		String[] vipEmojis = {
				ChatColor.GREEN + "Available to VIP and Above:",
				ChatColor.DARK_PURPLE + "o/ - (*w*)/",
				ChatColor.RED + ":no: - X",
				ChatColor.GREEN + ":yes: - ✓",
				ChatColor.WHITE + ":123: - " + ChatColor.RED + "1" + ChatColor.YELLOW + "2" + ChatColor.GREEN + "3",
				ChatColor.RED + ":oof: - OOF"
		};
		
		String[] vipPlusEmojis = {
			ChatColor.DARK_GREEN + "Availabel to VIP+ and Above:",
			ChatColor.BLUE + "o7 - (-_-)ゞ゛",
			ChatColor.DARK_RED + ":shut: - -s h u t  u p-",
			ChatColor.YELLOW + ":star: - ★彡",
			ChatColor.AQUA + ":sad: - (╥﹏╥)",
			ChatColor.RED + ":gun: - ︻┳═一",
		};
		
		String[] mvpEmojis = {
			ChatColor.DARK_AQUA + "Available to MVP and Above:",
			ChatColor.RED + "<3 - ❤️",
			ChatColor.DARK_GREEN + ":D",
			ChatColor.DARK_RED + "D:",
			ChatColor.DARK_BLUE + "-.-",
			ChatColor.YELLOW + ":hazard: - ☢",
			ChatColor.WHITE + ":tm: - ™️",
			ChatColor.WHITE + ":c: - ©️"
		};
		
		String[] mvpPlusEmojis = {
			ChatColor.AQUA + "Available to MVP+ and Above:",
			ChatColor.AQUA + ":snowman: - ⛇",
			ChatColor.WHITE + ":soccer: - ⚽",
			ChatColor.GOLD + ":trophy: - 🏆",
			ChatColor.WHITE + ":right: - →",
			ChatColor.WHITE + ":left: - ←",
			ChatColor.WHITE + ":up: - ↑",
			ChatColor.WHITE + ":down: - ↓",
			ChatColor.WHITE + ":leftright: - ⇄",
			ChatColor.WHITE + ":updown: - ⇅",
			ChatColor.RED + ":tableflip: - (ノಠ益ಠ)ノ彡┻━┻ |"
		};
		
		sender.sendMessage(vipEmojis);
		sender.sendMessage(vipPlusEmojis);
		sender.sendMessage(mvpEmojis);
		sender.sendMessage(mvpPlusEmojis);
		// TODO Auto-generated method stub
		return false;
	}

}
