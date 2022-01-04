package us.teaminceptus.smpcore.divisions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import us.teaminceptus.smpcore.SMPCore;
import us.teaminceptus.smpcore.divisions.Division.Invite;
import us.teaminceptus.smpcore.divisions.Division.Rank;
import us.teaminceptus.smpcore.divisions.Division.Setting;
import us.teaminceptus.smpcore.utils.GeneralUtils;

public class DivisionCommands implements TabExecutor {

	protected SMPCore plugin;
	
	public DivisionCommands(SMPCore plugin) {
		this.plugin = plugin;
		plugin.getCommand("division").setExecutor(this);
		plugin.getCommand("division").setTabCompleter(this);
		
		plugin.getCommand("divisionchat").setExecutor(this);
		plugin.getCommand("divisionchat").setTabCompleter(this);
		
		plugin.getCommand("divisionsetting").setExecutor(this);
		plugin.getCommand("divisionsetting").setTabCompleter(this);
	}
	
	private static String getRank(Player p) {
		return JavaPlugin.getPlugin(SMPCore.class).getConfig().getConfigurationSection(p.getUniqueId().toString()).getString("rank");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) return false;
		if (!(p.isOp())) {
			p.sendMessage(ChatColor.RED + "This command is currently in beta and is not fully released.");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("division")) {
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Pleaes provide a valid argument!");
				return false;
			} else {
				switch (args[0].toLowerCase()) {
					// User Commands
					case "create": {
						if (getRank(p).equalsIgnoreCase("default")) {
							p.sendMessage(ChatColor.RED + "You need to be VIP or higher to create a division!");
							return false;
						}
						
						if (Division.getByPlayer(p) != null) {
							p.sendMessage(ChatColor.RED + "You need to leave your current division to create a new one!");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide a valid name!");
							return false;
						}
						
						String name = args[1];
						
						try {
							Division div = Division.createDivision(p, name);
							p.sendMessage(ChatColor.GREEN + "Successfully created " + div.getName() + "!");
						} catch (IllegalStateException e) {
							p.sendMessage(ChatColor.RED + "There are too many divisions at the current moment. More will be available later.");
							return false;
						} catch (UnsupportedOperationException e) {
							p.sendMessage(ChatColor.RED + "This division already exists (names must be ENTIRELY different).");
							return false;
						}
						break;
					}
					case "leave": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You are not currently in a division.");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (div.getOwner().getUniqueId().equals(p.getUniqueId())) {
							p.sendMessage(ChatColor.RED + "You can't leave your own division!");
							return false;
						}
						
						div.removePlayer(p);
						div.broadcastMessage(p.getDisplayName() + ChatColor.YELLOW + " has left " + div.getName());
						p.sendMessage(ChatColor.AQUA + "You have successfully left " + div.getName());
						break;
					}
					case "list": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You are not currently in a division.");
							return false;
						}
						
						
						Division div = Division.getByPlayer(p);
						OfflinePlayer owner = div.getOwner();
						List<OfflinePlayer> admins = div.getPlayers().stream().filter(op -> div.getRank(op) == Rank.ADMIN).toList();
						List<OfflinePlayer> members = div.getPlayers().stream().filter(op -> div.getRank(op) == Rank.MEMBER).toList();
						
						String divider = ChatColor.AQUA + "\n----------------------------------\n";
						String ownerString = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Owner\n" + ChatColor.GOLD + owner.getName() + (owner.isOnline() ? ChatColor.GREEN : ChatColor.RED) + " ■";
						String adminString = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Admins\n";
						String membersString = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Members\n";
						
						for (OfflinePlayer admin : admins) {
							adminString += ChatColor.GOLD + admin.getName() + (admin.isOnline() ? ChatColor.GREEN : ChatColor.RED) + " ■" + ChatColor.GRAY + ", ";
						}
						
						for (OfflinePlayer member : members) {
							membersString += ChatColor.GOLD + member.getName() + (member.isOnline() ? ChatColor.GREEN : ChatColor.RED) + " ■" + ChatColor.GRAY + ", ";
						}
						
						String msg = ChatColor.AQUA + "" + ChatColor.UNDERLINE + div.getName() + " Player List\n" + ChatColor.RESET + " \n" + String.join(divider, ownerString, adminString, membersString, "");
						p.sendMessage(msg);
						break;
					}
					case "online": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You are not currently in a division.");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						OfflinePlayer owner = (div.getOwner().isOnline() ? div.getOwner() : null);
						List<OfflinePlayer> admins = div.getPlayers().stream().filter(op -> div.getRank(op) == Rank.ADMIN && op.isOnline()).toList();
						List<OfflinePlayer> members = div.getPlayers().stream().filter(op -> div.getRank(op) == Rank.MEMBER && op.isOnline()).toList();
						
						String divider = ChatColor.AQUA + "\n-------------------\n";
						String ownerString = (owner == null ? "" : ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Owner\n" + ChatColor.GOLD + owner.getName());
						String adminString = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Admins\n";
						String membersString = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Members\n";
						
						for (OfflinePlayer admin : admins) {
							adminString += ChatColor.GOLD + admin.getName() + ChatColor.GRAY + ", ";
						}
						
						for (OfflinePlayer member : members) {
							membersString += ChatColor.GOLD + member.getName() + ChatColor.GRAY + ", ";
						}
						
						String msg = ChatColor.AQUA + "" + ChatColor.UNDERLINE + div.getName() + " Online Player List\n" + String.join(divider, "", ownerString, adminString, membersString, "");
						p.sendMessage(msg);
						break;
					}
					case "info": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You are not currently in a division.");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						TextComponent divider = new TextComponent(ChatColor.AQUA + "\n------------------------------\n");
						
						TextComponent name = new TextComponent(ChatColor.GREEN + "Division Name: " + ChatColor.GOLD + div.getName() + "\n");
						name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + "Owner: " + div.getOwner().getName())));
						
						TextComponent id = new TextComponent(ChatColor.GREEN + "Division ID: " + ChatColor.GOLD + div.getID() + "\n");
						id.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "Copy to Clipboard")));
						id.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, div.getID()));
						
						TextComponent level = new TextComponent(ChatColor.GREEN + "Level: " + ChatColor.GOLD + Integer.toString(div.getLevel()) + "\n");
						
						TextComponent experience = new TextComponent(ChatColor.GREEN + "Total Experience: " + ChatColor.GOLD + GeneralUtils.withSuffix(div.getExperience()) + "\n");
						
						TextComponent networth = new TextComponent(ChatColor.GREEN + "Division Networth: " + ChatColor.GOLD + GeneralUtils.withSuffix(div.getDivisionNetworth()) + "\n");
						
						TextComponent area = new TextComponent(ChatColor.GREEN + "Division Claim Size: " + ChatColor.GOLD + (div.getCorner1() == null || div.getCorner2() == null ? "Not Set" : GeneralUtils.withSuffix(Math.floor(Math.sqrt(div.getCorner1().distanceSquared(div.getCorner2()))))) + "\n");
						area.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.YELLOW + "Corner 1: " + (div.getCorner1() == null ? ChatColor.AQUA + "Not Set" : ChatColor.AQUA + Integer.toString(div.getCorner1().getBlockX()) + ", 320, " + Integer.toString(div.getCorner1().getBlockZ()) ) + "\n"), new Text(ChatColor.YELLOW + "Corner 2: " + (div.getCorner2() == null ? ChatColor.AQUA + "Not Set" : ChatColor.AQUA + Integer.toString(div.getCorner2().getBlockX()) + ", " + Integer.toString(div.getCorner2().getBlockY() - 10) + ", " + Integer.toString(div.getCorner2().getBlockZ()) ))));
						
						TextComponent areaAvailable = new TextComponent(ChatColor.GREEN + "Division Claim Size Available: " + ChatColor.GOLD + (div.getCorner1() == null || div.getCorner2() == null ? "Not Set" : GeneralUtils.withSuffix(Math.floor(Math.sqrt(div.getMaxRegionSizeSquared()) - Math.sqrt(div.getCorner1().distanceSquared(div.getCorner2()))))) + "\n");
						
						TextComponent home = new TextComponent(ChatColor.GREEN + "Home Coordinates: " + ChatColor.AQUA + (div.getHome() == null ? "Not Set" : Integer.toString(div.getHome().getBlockX()) + ", " + Integer.toString(div.getHome().getBlockY()) + ", " + Integer.toString(div.getHome().getBlockZ())) + "\n");
						
						TextComponent players = new TextComponent(ChatColor.GREEN + "Player Amount: " + ChatColor.GOLD + Integer.toString(div.getPlayers().size()) + "\n");
						
						BaseComponent[] components = {
							divider,
							name,
							id,
							level,
							experience,
							networth,
							area,
							areaAvailable,
							home,
							players,
							divider
						};
						
						p.spigot().sendMessage(components);
						break;
					}
					case "join": {
						if (Division.isInDivision(p)) {
							p.sendMessage(ChatColor.RED +"You need to leave your division before joining another one!");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide a division to join!");
							return false;
						}
						
						if (Invite.getByPlayer(p) != null) {
							p.sendMessage(ChatColor.RED + "You already have an outgoing invite!");
							return false;
						}
						
						if (Division.getByName(args[1]) == null) {
							p.sendMessage(ChatColor.RED + "This division does not exist.");
							return false;
						}
						
						Division target = Division.getByName(args[1]);
						
						if (target.getSetting(Setting.CLOSED)) {
							p.sendMessage(ChatColor.RED + "This division is currently closed.");
							return false;
						}
						
						target.sendInvite(new Invite(p, target));
						p.sendMessage(ChatColor.AQUA + "Your invite has been sent!");
						break;
					}
					case "home": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (div.getHome() == null) {
							p.sendMessage(ChatColor.RED + "A home has not been set! Tell the owner to set one!");
							return false;
						}
						
						p.sendMessage(ChatColor.LIGHT_PURPLE + "Warping...");
						p.teleport(div.getHome());
						break;
					}
					// Admin Commands
					case "accept": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.isAdmin(p))) {
							p.sendMessage(ChatColor.RED + "You need to be a division administrator to accept invites!");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide an invite ID or a player to accept.");
							return false;
						}
						
						if (Invite.getById(args[1]) == null && Invite.getByPlayer(Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]))) == null) {
							p.sendMessage(ChatColor.RED + "This player or invite does not exist for this division.");
							return false;
						}
						
						Invite invite = (Invite.getById(args[1]) == null ? Invite.getByPlayer(Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]))) : Invite.getById(args[1]));
						div.acceptInvite(invite); // Chat is through accept() method in Invite
						break;
					}
					case "reject": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.isAdmin(p))) {
							p.sendMessage(ChatColor.RED + "You need to be a division administrator to reject invites!");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide an invite ID or a player to reject.");
							return false;
						}
						
						if (Invite.getById(args[1]) == null && Invite.getByPlayer(Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]))) == null) {
							p.sendMessage(ChatColor.RED + "This player or invite does not exist for this division.");
							return false;
						}
						
						Invite invite = (Invite.getById(args[1]) == null ? Invite.getByPlayer(Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]))) : Invite.getById(args[1]));
						div.revokeInvite(invite); // Chat is through reject() method in Invite
						break;
					}
					case "invites": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.isAdmin(p))) {
							p.sendMessage(ChatColor.RED + "You need to be a division administrator to reject invites!");
							return false;
						}
						
						if (div.getInvites().size() < 1) {
							p.sendMessage(ChatColor.AQUA + "There are no new invites available.");
							return false;
						}
						
						
						String invitesMsg = ChatColor.BLUE + "There are currently " + Integer.toString(div.getInvites().size()) + " invites pending.\n";
						
						for (Invite inv : div.getInvites()) {
							invitesMsg += ChatColor.AQUA + "Invite ID: " + inv.getID() + ChatColor.YELLOW + " | " + ChatColor.AQUA + "Player: " + inv.getPlayer().getName() + "\n";
						}
						
						p.sendMessage(invitesMsg);
						break;
					}
					case "kick": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.isAdmin(p))) {
							p.sendMessage(ChatColor.RED + "You need to be a division administrator to kick members!");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide a player to kick.");
							return false;
						}
						
						if (Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1])) == null) {
							p.sendMessage(ChatColor.RED + "This player does not exist.");
							return false;
						}
						
						OfflinePlayer target = Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]));
						
						if (!(Division.isInDivision(target)) && !(div.getPlayers().contains(target))) {
							p.sendMessage(ChatColor.RED + "This player is not in your division.");
							return false;
						}
						
						if (target.getUniqueId().equals(div.getOwner().getUniqueId())) {
							p.sendMessage(ChatColor.RED + "You can't kick the owner!");
							return false;
						}
						
						div.removePlayer(target);
						div.broadcastMessage(ChatColor.YELLOW + target.getName() + " has been kicked from " + div.getName());
						break;
					}
					// Owner Commands
					case "corner1": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}						

						Division div = Division.getByPlayer(p);
						
						if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
							p.sendMessage(ChatColor.RED + "Ask the owner to change the region!");
							return false;
						}
						
						if (!(p.getWorld().getName().equalsIgnoreCase("world"))) {
							p.sendMessage(ChatColor.RED + "Claims outside of the overworld are not yet supported.");
							return false;
						}
						
						if (div.getCorner2() != null) {
							if (div.getCorner2().distanceSquared(p.getLocation()) > div.getMaxRegionSizeSquared()) { // Went over Maximum
								p.sendMessage(ChatColor.RED + "This region is too big.");
								return false;
							}
						}
							
						div.setCorner1(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Successfully set your Claim Corner #1 to: " + ChatColor.GOLD + Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY()) + ", " + Integer.toString(p.getLocation().getBlockZ()) + ".");
						break;
					}
					case "corner2": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
							p.sendMessage(ChatColor.RED + "Ask the owner to change the region!");
							return false;
						}
						
						if (!(p.getWorld().getName().equalsIgnoreCase("world"))) {
							p.sendMessage(ChatColor.RED + "Claims outside of the overworld are not yet supported.");
							return false;
						}
						
						if (div.getCorner1() != null) {
							if (div.getCorner1().distanceSquared(p.getLocation()) > div.getMaxRegionSizeSquared()) { // Went over Maximum
								p.sendMessage(ChatColor.RED + "This region is too big.");
								return false;
							}
						}
						
						div.setCorner2(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Successfully set your Claim Corner #2 to: " + ChatColor.GOLD + Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY()) + ", " + Integer.toString(p.getLocation().getBlockZ()) + ".");
						break;
					}
					case "sethome": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
							p.sendMessage(ChatColor.RED + "Ask the owner to change the home position!");
							return false;
						}
						
						if (!(div.getClaimRegion().contains(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()))) {
							p.sendMessage(ChatColor.RED + "Your home needs to be inside your claim region!");
							return false;
						}
						
						if (!(p.getLocation().getBlock().isPassable()) && !(p.getLocation().add(0, 1, 0).getBlock().isPassable())) { // Add 1 for head
							p.sendMessage(ChatColor.RED + "Your current location seems invalid.");
							return false;
						}
						
						div.setHome(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Successfully set your Division Home to: " + ChatColor.GOLD + Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY()) + ", " + Integer.toString(p.getLocation().getBlockZ()) + ".");
						break;
					}
					case "disband": {
							if (!(Division.isInDivision(p))) {
								p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
								return false;
							}
							
							Division div = Division.getByPlayer(p);
							
							if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
								p.sendMessage(ChatColor.RED + "Only the owner can disband the division.");
								return false;
							}
							
							if (args.length < 2) {
								p.sendMessage(ChatColor.RED + "Are you sure you want to do this? All data will be permanently lost. Type /div disband confirm to confirm this action.");
								return false;
							} else if (args[1].equalsIgnoreCase("confirm")) {
								div.disband();
							} else {
								p.sendMessage(ChatColor.RED + "Are you sure you want to do this? All data will be permanently lost. Type /div disband confirm to confirm this action.");
								return false;
							}
						break;
					}
					case "setrank": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
							p.sendMessage(ChatColor.RED + "Only the owner can set ranks.");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Please provide the player that has a rank change!");
							return false;
						}
						
						if (Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1])) == null) {
							p.sendMessage(ChatColor.RED + "This player does not exist.");
							return false;
						}
						
						OfflinePlayer target = Bukkit.getOfflinePlayer(GeneralUtils.getUUIDByName(args[1]));
						
						if (!(Division.isInDivision(target)) && !(div.getPlayers().contains(target))) {
							p.sendMessage(ChatColor.RED + "This player is not in your division.");
							return false;
						}
						
						if (target.getUniqueId().equals(div.getOwner().getUniqueId())) {
							p.sendMessage(ChatColor.RED + "You can't change the owner's rank!");
							return false;
						}
						
						if (args.length < 3) {
							p.sendMessage(ChatColor.RED + "Please provide a new rank for the player.");
							return false;
						}
						
						if (Rank.valueOf(args[2].toUpperCase()) == null) {
							p.sendMessage(ChatColor.RED + "This Rank is invalid.");
							return false;
						}
						
						Rank newRank = Rank.valueOf(args[2].toUpperCase());
						
						if (div.getRank(p) == newRank) {
							p.sendMessage(ChatColor.RED + "This player already has this rank.");
							return false;
						}
						
						if (newRank == Rank.OWNER) {
							p.sendMessage(ChatColor.RED + "Please use transfer instead of this.");
							return false;
						}
						
						div.setRank(target, newRank);
						div.broadcastMessage(ChatColor.AQUA + target.getName() + "'s rank has been changed to " + ChatColor.GOLD + newRank.name());
						break;
					}
					case "resetcorners": {
						if (!(Division.isInDivision(p))) {
							p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
							return false;
						}
						
						Division div = Division.getByPlayer(p);
						
						if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
							p.sendMessage(ChatColor.RED + "Only the owner reset the claim area.");
							return false;
						}
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.RED + "Are you sure you want to do this? You will lose all claims areas. Type /division resetcorners confirm to confirm this action.");
							return false;
						} else if (args[1].equalsIgnoreCase("confirm")) {
							div.setHome(null);
							div.setCorner1(null);
							div.setCorner2(null);
							p.sendMessage(ChatColor.GREEN + "Successfully reset your claim corners.");
						} else {
							p.sendMessage(ChatColor.RED + "Are you sure you want to do this? You will lose all claims areas. Type /division resetcorners confirm to confirm this action.");
							return false;
						}
						break;
					}
					default: {
						p.sendMessage(ChatColor.RED + "Please provide a valid parameter.");
						break;
					}
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("divisionchat")){
			if (!(Division.isInDivision(p))) {
				p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
				return false;
			}
			
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Can't say silence.");
				return false;
			}
			
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			
			
			Division div = Division.getByPlayer(p);
			div.sendMessage(p, msg);
		} else if (cmd.getName().equalsIgnoreCase("divisionsetting")) {
			if (!(Division.isInDivision(p))) {
				p.sendMessage(ChatColor.RED + "You need to be in a division to use this command!");
				return false;
			}
			
			Division div = Division.getByPlayer(p);
			if (!(div.getOwner().getUniqueId().equals(p.getUniqueId()))) {
				p.sendMessage(ChatColor.RED + "Ask the owner to change the setting!");
				return false;
			}
			
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Please provide a valid setting.");
				return false;
			}
			
			if (Setting.valueOf(args[0].toUpperCase()) == null) {
				p.sendMessage(ChatColor.RED + "Please provide a valid setting.");
				return false;
			}
			
			Setting set = Setting.valueOf(args[0].toUpperCase());
			
			if (args.length < 2) {
				p.sendMessage(ChatColor.RED + "Please provide true or false.");
				return false;
			}
			
			boolean bool = Boolean.parseBoolean(args[1]);
			
			div.setSetting(set, bool);
			p.sendMessage(ChatColor.GREEN + "Successfully set " + ChatColor.GOLD + set.name() + ChatColor.GREEN + " to " + ChatColor.BLUE + Boolean.toString(bool).toUpperCase() + ".");
		}
		
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player p)) return null;
		List<String> options = new ArrayList<>();
		if (cmd.getName().equalsIgnoreCase("division")) {
			if (args.length == 1) {
				if (Division.isInDivision(p)) {
					options.addAll(Arrays.asList("leave", "list", "info", "online", "home"));
					
					if (Division.getByPlayer(p).isAdmin(p)) {
						options.addAll(Arrays.asList("invites", "accept", "reject", "kick"));
					}
					
					if (Division.getByPlayer(p).getOwner().getUniqueId().equals(p.getUniqueId())) {
						options.addAll(Arrays.asList("disband", "corner1", "corner2", "setrank", "resetcorners", "sethome"));
					}
				} else {
					options.addAll(Arrays.asList("create", "join"));
				}
			} else if (args.length == 2) {
				switch (args[0].toLowerCase()) {
					case "join": {
						for (Division div : Division.getDivisions()) {
							options.add(div.getName());
						}
						break;
					}
					default: {
						return null;
					}
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("divisionsetting")) {
			if (args.length == 1) {
				for (Setting s : Setting.values()) {
					options.add(s.name());
				}
			} else if (args.length == 2) {
				options.add("true");
				options.add("false");
			}
		}
		return options;
	}

}
