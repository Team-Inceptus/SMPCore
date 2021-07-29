package com.stephen.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class ItemsManager {

	public static ItemStack ThrowingKnife;
	
	public static ItemStack ThrowingKnife2;
	
	public static ItemStack ThrowingKnife3;
	
	public static ItemStack ThrowingKnife4;
	
	
	
	public static void init() {
		createThrowingSword();
		createThrowingSword2();
		createThrowingSword3();
		createThrowingSword4();
	}
	
	private static void createThrowingSword() {
		ItemStack item = new ItemStack(Material.STICK, 64);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Throwing Knife T1");
		List<String> lore = new ArrayList<>();
		lore.add("§7Damage: §b+1");
		lore.add("");
		lore.add("§6Item Ability: Very Weak Throw §eRIGHT CLICK");
		lore.add("§7Throw your knife and deal");
		lore.add("");
		lore.add("§67 Damage.");
		meta.setLore(lore);
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		item.setItemMeta(meta);
		ThrowingKnife = item;
		
		
	
	
	}
	private static void createThrowingSword2() {
		ItemStack item = new ItemStack(Material.STICK, 64);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aThrowing Knife T2");
		List<String> lore = new ArrayList<>();
		lore.add("§7Damage: §c+2");
		lore.add("");
		lore.add("§6Item Ability: Weak Throw §eRIGHT CLICK");
		lore.add("§7Throw your knife and deal");
		lore.add("");
		lore.add("§610 Damage.");
		meta.setLore(lore);
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		item.setItemMeta(meta);
		ThrowingKnife2 = item;
	
	}
	private static void createThrowingSword3() {
		ItemStack item = new ItemStack(Material.STICK, 64);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§3Throwing Knife T3");
		List<String> lore = new ArrayList<>();
		lore.add("§7Damage: §c+2");
		lore.add("");
		lore.add("§6Item Ability: Throw §eRIGHT CLICK");
		lore.add("§7Throw your knife and deal");
		lore.add("");
		lore.add("§620 Damage.");
		meta.setLore(lore);
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		item.setItemMeta(meta);
		ThrowingKnife3 = item;
	}
	private static void createThrowingSword4() {
		ItemStack item = new ItemStack(Material.STICK, 64);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§4Throwing Knife T4");
		List<String> lore = new ArrayList<>();
		lore.add("§7Damage: §c+2");
		lore.add("");
		lore.add("§6Item Ability: Strong Throw §eRIGHT CLICK");
		lore.add("§7Throw your knife and deal");
		lore.add("");
		lore.add("§650 Damage.");
		meta.setLore(lore);
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		item.setItemMeta(meta);
		ThrowingKnife4 = item;
	}
