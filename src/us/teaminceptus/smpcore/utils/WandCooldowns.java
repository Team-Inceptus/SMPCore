package us.teaminceptus.smpcore.utils;

import java.util.ArrayList;
import java.util.UUID;

public class WandCooldowns {
	static ArrayList<UUID> smiterCooldown = new ArrayList<UUID>();
	static ArrayList<UUID> forcefieldCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> endKillerCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> hastyBuilderCooldown = new ArrayList<UUID>();
	static ArrayList<UUID> immutatioCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> auraCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> vampireCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> superStrengthCooldown = new ArrayList<UUID>();
	
	static ArrayList<UUID> hunterCooldown = new ArrayList<UUID>();
	
	public static ArrayList<UUID> getSmiterCooldown() {
		return smiterCooldown;
	}
	
	public static ArrayList<UUID> getForcefieldCooldown() {
		return forcefieldCooldown;
	}
	
	public static ArrayList<UUID> getEndKillerCooldown() {
		return endKillerCooldown;
	}
	
	public static ArrayList<UUID> getHastyBuilderCooldown() {
		return hastyBuilderCooldown;
	}
	
	public static ArrayList<UUID> getImmutoCooldown() {
		return immutatioCooldown;
	}
	
	public static ArrayList<UUID> getAuraCooldown() {
		return auraCooldown;
	}
	
	public static ArrayList<UUID> getWitherVampireCooldown() {
		return vampireCooldown;
	}
	
	public static ArrayList<UUID> getSuperStrengthCooldown() {
		return superStrengthCooldown;
	}
	
	public static ArrayList<UUID> getHunterCooldown() {
		return hunterCooldown;
	}
}
