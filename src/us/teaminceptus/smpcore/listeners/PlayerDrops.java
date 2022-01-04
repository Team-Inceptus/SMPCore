package us.teaminceptus.smpcore.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import us.teaminceptus.smpcore.SMPCore;

public class PlayerDrops implements Listener {
	
	public SMPCore plugin;
	
	public PlayerDrops(SMPCore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	private String parseDamageCause(DamageCause dmg, Entity killer) {
		
		if (dmg.equals(DamageCause.CONTACT)) return " was killed by something he touched";
		else if (dmg.equals(DamageCause.CRAMMING)) return " was squished too much";
		else if (dmg.equals(DamageCause.DRAGON_BREATH)) return " was killed by the Dragon's Breath";
		else if (dmg.equals(DamageCause.DROWNING)) return " ran out of air";
		else if (dmg.equals(DamageCause.DRYOUT)) return " was too try";
		else if (dmg.equals(DamageCause.FALL)) return " broke his legs";
		else if (dmg.equals(DamageCause.FIRE)) return " burned to death";
		else if (dmg.equals(DamageCause.FIRE_TICK)) return " burned to death";
		else if (dmg.equals(DamageCause.FLY_INTO_WALL)) return " broke his head";
		else if (dmg.equals(DamageCause.FREEZE)) return " froze to death";
		else if (dmg.equals(DamageCause.HOT_FLOOR)) return "'s toes melted";
		else if (dmg.equals(DamageCause.LAVA)) return " melted to death";
		else if (dmg.equals(DamageCause.LIGHTNING)) return " was smited";
		else if (dmg.equals(DamageCause.MAGIC)) return " was killed by magic";
		else if (dmg.equals(DamageCause.MELTING)) return " melted to death";
		else if (dmg.equals(DamageCause.POISON)) return " was poisoned";
		else if (dmg.equals(DamageCause.STARVATION)) return " starved himself to death";
		else if (dmg.equals(DamageCause.SUFFOCATION)) return " was encased in a block";
		else if (dmg.equals(DamageCause.SUICIDE)) return " commited die";
		else if (dmg.equals(DamageCause.THORNS)) return " was prickled to death";
		else if (dmg.equals(DamageCause.VOID)) return " was consumed by the physical endless void";
		else if (dmg.equals(DamageCause.WITHER)) return " withered to death";
		else if (dmg.equals(DamageCause.BLOCK_EXPLOSION) || dmg.equals(DamageCause.ENTITY_EXPLOSION)) return " exploded";
		else if (dmg.equals(DamageCause.FALLING_BLOCK)) return " was squished by a falling object";
		
		else if (killer == null) return " died";
		else if (killer instanceof Projectile p && p.getShooter() == null) return " died";
		
		else if (dmg.equals(DamageCause.ENTITY_ATTACK)) return " was killed by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.ENTITY_SWEEP_ATTACK)) return " was killed by " + (killer.getCustomName() != null ? killer.getCustomName() : killer.getName());
		else if (dmg.equals(DamageCause.PROJECTILE)) return " was killed by " + ( ( (Entity) ( (Projectile) killer).getShooter()).getCustomName() != null ? ((Entity) ((Projectile) killer).getShooter()).getCustomName() : ((Entity) ((Projectile) killer).getShooter()).getName());
		else return " died";
	}
	
	ArrayList<UUID> teleporters = new ArrayList<UUID>();
	
	@EventHandler
	public void onDeathAnimationKiller(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (p.getName().startsWith("CIT-")) return;
		
		if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			e.setDeathMessage(p.getDisplayName() + ChatColor.GREEN + parseDamageCause(((EntityDamageByEntityEvent) p.getLastDamageCause()).getCause(), ((EntityDamageByEntityEvent) p.getLastDamageCause()).getDamager()));
		} else {
			e.setDeathMessage(p.getDisplayName() + ChatColor.GREEN + parseDamageCause(p.getLastDamageCause().getCause(), null));
		}
		
		// Drop Head
		ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
		playerHeadMeta.setOwningPlayer(p);
		playerHeadMeta.setDisplayName(ChatColor.GOLD + p.getName() + "'s " + ChatColor.GREEN + "Head");
		
		ArrayList<String> playerHeadLore = new ArrayList<>();
		playerHeadLore.add(ChatColor.GRAY + "Obtained by killing " + ChatColor.GOLD + p.getName() + ChatColor.GRAY + ".");
		
		playerHeadMeta.setLore(playerHeadLore);
		
		playerHead.setItemMeta(playerHeadMeta);
		
		p.getWorld().dropItemNaturally(p.getLocation(), playerHead);
		// Drop Actual XP
		ExperienceOrb exp = (ExperienceOrb) p.getWorld().spawnEntity(p.getLocation(), EntityType.EXPERIENCE_ORB);
		exp.setExperience(p.getTotalExperience());
	}
}
