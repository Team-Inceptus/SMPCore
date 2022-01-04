package us.teaminceptus.smpcore.fishing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import us.teaminceptus.smpcore.fishing.FishingUtils.FishSkin;

public class FishingFetcher {
	
	public static List<ItemStack> getItems() {
		List<ItemStack> items = new ArrayList<>();
		for (Method m : FishingFetcher.class.getMethods()) {
			if (m.getName().equals("getItems")) continue;
			if (!(Modifier.isStatic(m.getModifiers()))) continue;
			try {
				Object invoke = m.invoke(null, (Object[]) null);
				if (invoke instanceof ItemStack item) items.add(item);
				else if (invoke instanceof Map<?, ?> map) {
					for (Object obj : map.keySet()) {
						if (obj instanceof EquipmentSlot slot && map.get(slot) instanceof ItemStack stack) {
							items.add(stack);
						}
					}
				}
				else if (invoke instanceof ItemStack[] item) {
					for (ItemStack i : item) items.add(i);
				}
				else continue;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return items;
	}
	
	public static Map<FishSkin, NPC> getNPCS() {
		Map<FishSkin, NPC> npcMap = new HashMap<>();
		
		NPC drownedCreeper = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.DARK_AQUA + "Drowned Creeper");
		FishingUtils.setSkin(drownedCreeper, FishSkin.DROWNED_CREEPER);
		npcMap.put(FishSkin.DROWNED_CREEPER, drownedCreeper);
		
		NPC steveSnowman = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.AQUA + "Steve the Snowman");
		FishingUtils.setSkin(steveSnowman, FishSkin.SNOWMAN_STEVE);
		npcMap.put(FishSkin.SNOWMAN_STEVE, steveSnowman);
		
		NPC seaSnake = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.DARK_GREEN + "Sea Snake");
		FishingUtils.setSkin(seaSnake, FishSkin.SEA_SNAKE);
		npcMap.put(FishSkin.SEA_SNAKE, seaSnake);
		
		return npcMap;
	}
	
	public static Map<FishSkin, Map<ItemStack, Integer>> getDrops() {
		Map<FishSkin, Map<ItemStack, Integer>> drops = new HashMap<>();
		
		return drops;
	}
	
	public static ItemStack getAquaIce() {
		ItemStack aI = new ItemStack(Material.BLUE_ICE);
		ItemMeta aMeta = aI.getItemMeta();
		aMeta.setDisplayName(ChatColor.AQUA + "Aqua Ice");
		aI.setItemMeta(aMeta);
		
		return aI;
	}
	
	public static ItemStack getDrownedSpade() {
		ItemStack dS = new ItemStack(Material.GOLDEN_SHOVEL);
		ItemMeta dMeta = dS.getItemMeta();
		dMeta.setDisplayName(ChatColor.DARK_AQUA + "Drowned Spade");
		dMeta.setUnbreakable(true);
		dMeta.addEnchant(Enchantment.DAMAGE_ALL, 25, true);
		dMeta.addEnchant(Enchantment.DIG_SPEED, 10, true);
		dMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
		dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dS.setItemMeta(dMeta);
		
		return dS;
	}
	
	public static ItemStack getIceSword() {
		ItemStack iceSword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta iMeta = iceSword.getItemMeta();
		iMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Ice Sword");
		iMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		iMeta.addEnchant(Enchantment.DAMAGE_ALL, 450, true);
		iMeta.addEnchant(Enchantment.DAMAGE_ALL, 300, true);
		iMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 300, true);
		iMeta.setLocalizedName("ice_sword");
		iMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 450, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		iMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 450, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		iMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 300, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		iMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 300, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		iceSword.setItemMeta(iMeta);
		
		return iceSword;
	}
	
	
}
