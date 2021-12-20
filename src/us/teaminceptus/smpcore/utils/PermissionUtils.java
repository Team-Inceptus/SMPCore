package us.teaminceptus.smpcore.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import us.teaminceptus.smpcore.SMPCore;

public class PermissionUtils {
	
	private static Map<UUID, PermissionAttachment> attachments = new HashMap<>();
	
	public static PermissionAttachment giveDefaultPermissions(SMPCore plugin, Player p) {
		PermissionAttachment a = p.addAttachment(plugin);
		attachments.put(p.getUniqueId(), a);
		
		a.setPermission("coreprotect.inspect", true);
		a.setPermission("bukkit.command.tps", true);
		a.setPermission("craftenhance.view", true);
		a.setPermission("coreprotect.lookup", true);
		a.setPermission("core.special.itemprotector", true);
		a.setPermission("minecraft.debugstick", true);
		a.setPermission("minecraft.command.me", true);
		a.setPermission("minecraft.command.list", true);
		a.setPermission("bukkit.command.version", true);
		a.setPermission("discordsrv.chat", true);
		a.setPermission("minecraft.command.help", true);
		a.setPermission("minecraft.command.msg", true);
		a.setPermission("simpletpa.tpa", true);
		a.setPermission("simpletpa.tpahere", true);
		a.setPermission("simpletpa.tpatoggle", true);
		a.setPermission("simpletpa.tpaccept", true);
		a.setPermission("simpletpa.tpdeny", true);
		a.setPermission("simpletpa.tpcancel", true);
		
		return a;
	}
	
	public static Map<UUID, PermissionAttachment> getAttachments() {
		return attachments;
	}
	
	public static PermissionAttachment giveTrialModPermissions(SMPCore plugin, Player p) {
		PermissionAttachment a = giveDefaultPermissions(plugin, p);
		
		a.setPermission("coreprotect.inspect", true);
		a.setPermission("bukkit.command.tps", true);
		a.setPermission("craftenhance.view", true);
		a.setPermission("coreprotect.lookup", true);
		a.setPermission("core.special.itemprotector", true);
		a.setPermission("minecraft.debugstick", true);
		a.setPermission("minecraft.command.me", true);
		a.setPermission("minecraft.command.list", true);
		a.setPermission("bukkit.command.version", true);
		a.setPermission("discordsrv.chat", true);
		a.setPermission("minecraft.command.help", true);
		a.setPermission("minecraft.command.msg", true);
		a.setPermission("simpletpa.tpa", true);
		a.setPermission("simpletpa.tpahere", true);
		a.setPermission("simpletpa.tpatoggle", true);
		a.setPermission("simpletpa.tpaccept", true);
		a.setPermission("simpletpa.tpdeny", true);
		a.setPermission("simpletpa.tpcancel", true);
		
		a.setPermission("minecraft.command.teleport", true);
		a.setPermission("minecraft.command.gamemode", true);
		a.setPermission("minecraft.command.kick", true);
		a.setPermission("simpletpa.tpahereall", true);
		a.setPermission("core.admin.invsee", true);
		a.setPermission("core.admin.getstatistic", true);
		a.setPermission("core.admin.gamemodebypass", true);
		a.setPermission("core.admin.flyspeed", true);
		a.setPermission("worldedit", true);
		a.setPermission("minecraft.nbt", true);

		
		return a;
	}
	
	public static PermissionAttachment giveJrModPermissions(SMPCore plugin, Player p) {
		PermissionAttachment a = giveTrialModPermissions(plugin, p);
		
		a.setPermission("coreprotect.inspect", true);
		a.setPermission("bukkit.command.tps", true);
		a.setPermission("craftenhance.view", true);
		a.setPermission("coreprotect.lookup", true);
		a.setPermission("core.special.itemprotector", true);
		a.setPermission("minecraft.debugstick", true);
		a.setPermission("minecraft.command.me", true);
		a.setPermission("minecraft.command.list", true);
		a.setPermission("bukkit.command.version", true);
		a.setPermission("discordsrv.chat", true);
		a.setPermission("minecraft.command.help", true);
		a.setPermission("minecraft.command.msg", true);
		a.setPermission("simpletpa.tpa", true);
		a.setPermission("simpletpa.tpahere", true);
		a.setPermission("simpletpa.tpatoggle", true);
		a.setPermission("simpletpa.tpaccept", true);
		a.setPermission("simpletpa.tpdeny", true);
		a.setPermission("simpletpa.tpcancel", true);
		
		a.setPermission("minecraft.command.teleport", true);
		a.setPermission("minecraft.command.gamemode", true);
		a.setPermission("minecraft.command.kick", true);
		a.setPermission("simpletpa.tpahereall", true);
		a.setPermission("core.admin.invsee", true);
		a.setPermission("core.admin.getstatistic", true);
		a.setPermission("core.admin.gamemodebypass", true);
		a.setPermission("core.admin.flyspeed", true);
		a.setPermission("worldedit", true);
		a.setPermission("minecraft.nbt", true);
		
		a.setPermission("minecraft.command.give", true);
		a.setPermission("minecraft.command.clear", true);
		a.setPermission("core.admin.editplayer", true);
		a.setPermission("core.admin.invsee", true);
		a.setPermission("craftenhance.edit", true);
		a.setPermission("coreprotect.restore", true);
		a.setPermission("coreprotect.status", true);
		a.setPermission("core.admin.suspend", true);
		a.setPermission("core.admin.suspendlist", true);
		a.setPermission("minecraft.commandblock", true);
		a.setPermission("minecraft.admin.command_feedback", true);
		a.setPermission("minecraft.command.item", true);
		a.setPermission("minecraft.command.setblock", true);
		a.setPermission("minecraft.command.clone", true);
		a.setPermission("minecraft.command.fill", true);
		a.setPermission("minecraft.command.effect", true);
		a.setPermission("minecraft.command.particle", true);
		a.setPermission("minecraft.command.locate", true);
		a.setPermission("minecraft.command.locatebiome", true);
		a.setPermission("minecraft.command.enchant", true);
		a.setPermission("minecraft.command.experience", true);
		
		return a;
	}
	
	public static PermissionAttachment giveModPermissions(SMPCore plugin, Player p) {
		PermissionAttachment a = giveJrModPermissions(plugin, p);
		
		a.setPermission("coreprotect.inspect", true);
		a.setPermission("bukkit.command.tps", true);
		a.setPermission("craftenhance.view", true);
		a.setPermission("coreprotect.lookup", true);
		a.setPermission("core.special.itemprotector", true);
		a.setPermission("minecraft.debugstick", true);
		a.setPermission("minecraft.command.me", true);
		a.setPermission("minecraft.command.list", true);
		a.setPermission("bukkit.command.version", true);
		a.setPermission("discordsrv.chat", true);
		a.setPermission("minecraft.command.help", true);
		a.setPermission("minecraft.command.msg", true);
		a.setPermission("simpletpa.tpa", true);
		a.setPermission("simpletpa.tpahere", true);
		a.setPermission("simpletpa.tpatoggle", true);
		a.setPermission("simpletpa.tpaccept", true);
		a.setPermission("simpletpa.tpdeny", true);
		a.setPermission("simpletpa.tpcancel", true);
		
		a.setPermission("minecraft.command.teleport", true);
		a.setPermission("minecraft.command.gamemode", true);
		a.setPermission("minecraft.command.kick", true);
		a.setPermission("simpletpa.tpahereall", true);
		a.setPermission("core.admin.invsee", true);
		a.setPermission("core.admin.getstatistic", true);
		a.setPermission("core.admin.gamemodebypass", true);
		a.setPermission("core.admin.flyspeed", true);
		a.setPermission("worldedit", true);
		a.setPermission("minecraft.nbt", true);
		
		a.setPermission("minecraft.command.give", true);
		a.setPermission("minecraft.command.clear", true);
		a.setPermission("core.admin.editplayer", true);
		a.setPermission("core.admin.invsee", true);
		a.setPermission("craftenhance.edit", true);
		a.setPermission("coreprotect.restore", true);
		a.setPermission("coreprotect.status", true);
		a.setPermission("core.admin.suspend", true);
		a.setPermission("core.admin.suspendlist", true);
		a.setPermission("minecraft.commandblock", true);
		a.setPermission("minecraft.admin.command_feedback", true);
		a.setPermission("minecraft.command.item", true);
		a.setPermission("minecraft.command.setblock", true);
		a.setPermission("minecraft.command.clone", true);
		a.setPermission("minecraft.command.fill", true);
		a.setPermission("minecraft.command.effect", true);
		a.setPermission("minecraft.command.particle", true);
		a.setPermission("minecraft.command.locate", true);
		a.setPermission("minecraft.command.locatebiome", true);
		a.setPermission("minecraft.command.enchant", true);
		a.setPermission("minecraft.command.experience", true);
		
		a.setPermission("core.admin.ban", true);
		a.setPermission("core.admin.banlist", true);
		a.setPermission("minecraft.command.data", true);
		a.setPermission("minecraft.command.banlist", true);
		a.setPermission("minecraft.command.ban", true);
		a.setPermission("minecraft.command.ban-ip", true);
		a.setPermission("minecraft.command.pardon", true);
		a.setPermission("minecraft.command.gamerule", true);
		a.setPermission("minecraft.command.kill", true);
		a.setPermission("minecraft.command.bossbar", true);
		a.setPermission("citizens", true);
		
		return a;
	}

}
