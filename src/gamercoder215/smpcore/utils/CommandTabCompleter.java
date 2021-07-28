package gamercoder215.smpcore.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandTabCompleter implements TabCompleter {;

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> suggested = new ArrayList<String>();
		if (cmd.getName().equalsIgnoreCase("getstatistic")) {
			if (args.length == 1) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					suggested.add(pl.getName());
				}
				return suggested;
			} else if (args.length == 2) {
				for (Statistic st : Statistic.values()) {
					suggested.add(st.name().toLowerCase());
				}
				return suggested;
			}
		}
		return null;
	}
}
