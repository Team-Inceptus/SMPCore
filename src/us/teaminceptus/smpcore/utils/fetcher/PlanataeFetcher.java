package us.teaminceptus.smpcore.utils.fetcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import us.teaminceptus.smpcore.utils.GeneralUtils;

public class PlanataeFetcher {
	
	public static List<ItemStack> getItems() {
		List<ItemStack> items = new ArrayList<>();
		try {
			for (Method m : PlanataeFetcher.class.getDeclaredMethods()) {
				if (m.isDefault()) continue;
				if (m.getName().equalsIgnoreCase("getItems")) continue;
				
				Object feedback = m.invoke(null);
				
				if (feedback instanceof List<?> l) for (Object i : l) {
					if (i instanceof ItemStack item) items.add(item);
					else continue;
				}
				else if (feedback instanceof ItemStack item) items.add(item);
				else continue;
			}
			return items;
		} catch (Exception e) {	
			return items;
		}
	}
	
	public static ItemStack getGammaAmethyst() {
		ItemStack gA = new ItemStack(Material.AMETHYST_SHARD);
		ItemMeta gMeta = gA.getItemMeta();
		gMeta.setDisplayName(ChatColor.DARK_PURPLE + "Gamma Amethyst");
		
		gA.setItemMeta(gMeta);
		
		return gA;
	}
	
	public static ItemStack getGammaStone() {
		ItemStack gS = new ItemStack(Material.DEEPSLATE);
		ItemMeta gMeta = gS.getItemMeta();
		
		gMeta.setDisplayName(ChatColor.DARK_GRAY + "Gamma Stone");
		gMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		gS.setItemMeta(gMeta);
		
		return gS;
	}
	
	public static ItemStack getKappaStone() {
		ItemStack kS = new ItemStack(Material.TUFF);
		ItemMeta kMeta = kS.getItemMeta();
		
		kMeta.setDisplayName(ChatColor.GRAY + "Kappa Stone");
		
		kS.setItemMeta(kMeta);
		
		return kS;
	}
	
	public static ItemStack getRawChalc() {
		ItemStack ch = new ItemStack(Material.RAW_COPPER);
		ItemMeta cMeta = ch.getItemMeta();
		
		cMeta.setDisplayName(GeneralUtils.hexToChat("5e4200", "Raw Chalc Ingot"));
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		ch.setItemMeta(cMeta);
		
		return ch;
	}
	
	public static ItemStack getIotaLava() {
		ItemStack iLava = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta iMeta = iLava.getItemMeta();
		
		iMeta.setDisplayName(ChatColor.GOLD + "Iota Lava");
		iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		iMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "So hot, can smelt");
		lore.add(ChatColor.GRAY + "items infinitely.");
		iMeta.setLore(lore);
		
		iLava.setItemMeta(iMeta);
		
		return iLava;
	}
	
	public static ItemStack getEpsilonSheet() {
		ItemStack eS = new ItemStack(Material.DRIED_KELP);
		ItemMeta eMeta = eS.getItemMeta();
		
		eMeta.setDisplayName(ChatColor.DARK_GRAY + "Epsilon Sheet");
		
		eS.setItemMeta(eMeta);
		
		return eS;
	}
	
	public static ItemStack getGammaPowder() {
		ItemStack gP = new ItemStack(Material.GUNPOWDER);
		ItemMeta gMeta = gP.getItemMeta();
		
		gMeta.setDisplayName(ChatColor.GRAY + "Gamma Powder");
		
		gP.setItemMeta(gMeta);
		
		return gP;
	}
	
	public static ItemStack getEpsilonStone() {
		ItemStack eS = new ItemStack(Material.GRANITE);
		ItemMeta eMeta = eS.getItemMeta();
		
		eMeta.setDisplayName(ChatColor.YELLOW + "Epsilon Stone");
		
		eS.setItemMeta(eMeta);
		
		return eS;
	}
	
	public static ItemStack getGammaIce() {
		ItemStack gI = new ItemStack(Material.PACKED_ICE);
		ItemMeta gMeta = gI.getItemMeta();
		
		gMeta.setDisplayName(ChatColor.AQUA + "Gamma Ice");
		
		gI.setItemMeta(gMeta);
		
		return gI;
	}
	
	public static ItemStack getZetaPowder() {
		ItemStack zP = new ItemStack(Material.SUGAR);
		ItemMeta zMeta = zP.getItemMeta();
		
		zMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Zeta Powder");
		
		zP.setItemMeta(zMeta);
		
		return zP;
	}
	
	public static ItemStack getEpsilonIce() {
		ItemStack eI = new ItemStack(Material.BLUE_ICE);
		ItemMeta eMeta = eI.getItemMeta();
		
		eMeta.setDisplayName(ChatColor.DARK_AQUA + "Epsilon Ice");
		
		eI.setItemMeta(eMeta);
		
		return eI;
	}
	
	public static ItemStack getOmegaDiamond() {
		ItemStack oD = new ItemStack(Material.DIAMOND);
		ItemMeta oMeta = oD.getItemMeta();
		
		oMeta.setDisplayName(ChatColor.DARK_AQUA + "Omega Diamond");
		oMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		oMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oD.setItemMeta(oMeta);
		
		return oD;
	}
	
	public static ItemStack getSigmaQuartz() {
		ItemStack sQ = new ItemStack(Material.QUARTZ);
		ItemMeta sMeta = sQ.getItemMeta();
		
		sMeta.setDisplayName(ChatColor.WHITE + "Sigma Quartz");
		sMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		sMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		sQ.setItemMeta(sMeta);
		
		return sQ;
	}
	
	public static ItemStack getSigmaBrick() {
		ItemStack sB = new ItemStack(Material.NETHER_BRICK);
		ItemMeta sMeta = sB.getItemMeta();
		
		sMeta.setDisplayName(GeneralUtils.hexToChat("7c1113", "Sigma Brick"));
		
		sB.setItemMeta(sMeta);
		
		return sB;
	}
	
	public static ItemStack getOmegaPlasma() {
		try {
			ItemStack oP = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Omega Plasma\",\"color\":\"yellow\",\"bold\":true,\"italic\":false}'},SkullOwner:{Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI0ZmFhMWRjNTJhZDZjMTM2NzFmZDdjNjhlOTg1ZDVkMWZlZmVhNWIxZjE3NmZhODI5NTZjZDMxNWRlNTAifX19\"}]},Id:[I; -507505342, -1940111085, -1326241871, 1834761283]}}}");
			return oP;
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getRawNivi() {
		ItemStack rawN = new ItemStack(Material.RAW_IRON);
		ItemMeta rMeta = rawN.getItemMeta();
		rMeta.setDisplayName(ChatColor.WHITE + "Raw Nivi");
		
		rawN.setItemMeta(rMeta);
		
		return rawN;
	}
	
	public static ItemStack getNivi() {
		ItemStack nI = new ItemStack(Material.IRON_INGOT);
		ItemMeta nMeta = nI.getItemMeta();
		nMeta.setDisplayName(ChatColor.WHITE + "Nivi");
		
		nI.setItemMeta(nMeta);
		
		return nI;
	}
	
	public static ItemStack getOmegaStar() {
		ItemStack oS = new ItemStack(Material.NETHER_STAR);
		ItemMeta oMeta = oS.getItemMeta();
		
		oMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Omega Star");
		
		oS.setItemMeta(oMeta);
		
		return oS;
	}
	
	public static ItemStack getOmegaStarShard() {
		ItemStack oSh = new ItemStack(Material.IRON_NUGGET);
		ItemMeta oMeta = oSh.getItemMeta();
		oMeta.setDisplayName(ChatColor.WHITE + "Omega Star Shard");
		oMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		oMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oSh.setItemMeta(oMeta);
		
		return oSh;
	}
	
	public static ItemStack getOmegaEssence() {
		ItemStack oE = new ItemStack(Material.PURPLE_DYE);
		ItemMeta eMeta = oE.getItemMeta();
		eMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Omega Essence");
		eMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		eMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oE.setItemMeta(eMeta);
		
		return oE;
	}
	
	public static ItemStack getSigmaEssence() {
		ItemStack sE = new ItemStack(Material.EMERALD);
		ItemMeta sMeta = sE.getItemMeta();
		sMeta.setDisplayName(ChatColor.GREEN + "Sigma Essence");
		sMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		sMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		sE.setItemMeta(sMeta);
		
		return sE;
	}
	
	public static ItemStack getOmegaStone() {
		ItemStack oS = new ItemStack(Material.END_STONE);
		ItemMeta oMeta = oS.getItemMeta();
		oMeta.setDisplayName(ChatColor.WHITE + "Omega Stone");
		oMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		oMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oS.setItemMeta(oMeta);
		
		return oS;
	}
	
	public static ItemStack getSigmaStone() {
		ItemStack sS = new ItemStack(Material.STONE);
		ItemMeta sMeta = sS.getItemMeta();
		sMeta.setDisplayName(ChatColor.GRAY + "Sigma Stone");
		sMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		sMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		sS.setItemMeta(sMeta);
		
		return sS;
	}
	
	public static ItemStack getEpsilonScrap() {
		ItemStack eS = new ItemStack(Material.NETHERITE_SCRAP);
		ItemMeta eMeta = eS.getItemMeta();
		eMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Epsilon Scrap");
		eMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		eMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		eS.setItemMeta(eMeta);
		
		return eS;
	}
	
	public static ItemStack getOmegaCharge() {
		ItemStack oC = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta oMeta = oC.getItemMeta();
		oMeta.setDisplayName(ChatColor.GOLD + "Omega Charge");
		oMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		oMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oC.setItemMeta(oMeta);
		
		return oC;
	}
	
	public static ItemStack getGehe() {
		ItemStack gH = new ItemStack(Material.LEATHER);
		ItemMeta gMeta = gH.getItemMeta();
		gMeta.setDisplayName(ChatColor.RED + "Gehe");
		gMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		gMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		gH.setItemMeta(gMeta);
		
		return gH;
	}
	
	public static ItemStack getOmegaBrick() {
		ItemStack oB = new ItemStack(Material.BRICK);
		ItemMeta oMeta = oB.getItemMeta();
		oMeta.setDisplayName(ChatColor.RED + "Omega Brick");
		oMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		oMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		oB.setItemMeta(oMeta);
		
		return oB;
	}
	
	public static ItemStack getChalc() {
		ItemStack cH = new ItemStack(Material.COPPER_INGOT);
		ItemMeta cMeta = cH.getItemMeta();
		cMeta.setDisplayName(GeneralUtils.hexToChat("5e4200", "Chalc Ingot"));
		cMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		cMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		cH.setItemMeta(cMeta);
		
		return cH;
	}
	
	public static ItemStack getLympiaShard() {
		ItemStack lI = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta lMeta = lI.getItemMeta();
		lMeta.setDisplayName(ChatColor.DARK_AQUA + "Lympia Shard");
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		lI.setItemMeta(lMeta);
		
		return lI;
	}
	
	public static ItemStack getLympiaCrystal() {
		ItemStack lC = new ItemStack(Material.PRISMARINE_CRYSTALS);
		ItemMeta lMeta = lC.getItemMeta();
		lMeta.setDisplayName(ChatColor.AQUA + "Lympia Crystal");
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		lC.setItemMeta(lMeta);
		
		return lC;
	}
	
	public static ItemStack getLambdaStone() {
		ItemStack lS = new ItemStack(Material.DARK_PRISMARINE);
		ItemMeta lMeta = lS.getItemMeta();
		lMeta.setDisplayName(ChatColor.DARK_GREEN + "Lambda Stone");
		
		lS.setItemMeta(lMeta);
		
		return lS;
	}
	
	public static ItemStack getUpsilonegen() {
		ItemStack uG = new ItemStack(Material.LIME_DYE);
		ItemMeta uMeta = uG.getItemMeta();
		uMeta.setDisplayName(ChatColor.GREEN + "Upsilonegen");
		
		uG.setItemMeta(uMeta);
		
		return uG;
	}
	
	public static ItemStack getZaminium() {
		ItemStack zM = new ItemStack(Material.END_STONE);
		ItemMeta zMeta = zM.getItemMeta();
		zMeta.setDisplayName(ChatColor.GRAY + "Zaminium");
		zMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		zMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		zM.setItemMeta(zMeta);
		
		return zM;
	}
	
	public static ItemStack getHardenedZaminium() {
		ItemStack zM = new ItemStack(Material.NETHERITE_INGOT);
		ItemMeta zMeta = zM.getItemMeta();
		zMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Hardened Zaminium Ingot");
		zMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		zMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		zM.setItemMeta(zMeta);
		
		return zM;
	}
	
	public static ItemStack getRawTermentium() {
		ItemStack tM = new ItemStack(Material.RAW_IRON);
		ItemMeta tMeta = tM.getItemMeta();
		tMeta.setDisplayName(ChatColor.WHITE + "Raw Termentium Ingot");
		tMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		tMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		tM.setItemMeta(tMeta);
		
		return tM;
	}
	
	public static ItemStack getTermentium() {
		ItemStack tM = new ItemStack(Material.IRON_INGOT);
		ItemMeta tMeta = tM.getItemMeta();
		tMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Termentium Ingot");
		tMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		tMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		tM.setItemMeta(tMeta);
		
		return tM;
	}
	
	public static ItemStack getClade() {
		ItemStack cL = new ItemStack(Material.SMOOTH_SANDSTONE);
		ItemMeta cMeta = cL.getItemMeta();
		cMeta.setDisplayName(ChatColor.YELLOW + "Clade");
		
		cL.setItemMeta(cMeta);
		
		return cL;
	}
	
	public static ItemStack getBeratiumNugget() {
		ItemStack bN = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta bMeta = bN.getItemMeta();
		bMeta.setDisplayName(ChatColor.GOLD + "Gold Beratium Nugget");
		
		bN.setItemMeta(bMeta);
		
		return bN;
	}
	
	public static ItemStack getBeratiumIngot() {
		ItemStack bN = new ItemStack(Material.NETHER_BRICK);
		ItemMeta bMeta = bN.getItemMeta();
		bMeta.setDisplayName(ChatColor.GOLD + "Beratium Ingot");
		
		bN.setItemMeta(bMeta);
		
		return bN;
	}
	
	public static ItemStack getSoftBeratium() {
		ItemStack sB = new ItemStack(Material.NETHERRACK);
		ItemMeta sMeta = sB.getItemMeta();
		sMeta.setDisplayName(ChatColor.DARK_RED + "Soft Beratium Block");
		
		sB.setItemMeta(sMeta);
		
		return sB;
	}
	
	public static ItemStack getBeratiumWart() {
		ItemStack bW = new ItemStack(Material.NETHER_WART);
		ItemMeta bMeta = bW.getItemMeta();
		bMeta.setDisplayName(ChatColor.DARK_RED + "Beratium Wart");
		
		bW.setItemMeta(bMeta);
		
		return bW;
	}
	
	public static ItemStack getRawAurum() {
		ItemStack rA = new ItemStack(Material.RAW_GOLD);
		ItemMeta rMeta = rA.getItemMeta();
		rMeta.setDisplayName(ChatColor.GOLD + "Raw Aurum Ingot");
		
		rA.setItemMeta(rMeta);
		
		return rA;
	}
	
	public static ItemStack getAurum() {
		ItemStack rA = new ItemStack(Material.GOLD_INGOT);
		ItemMeta rMeta = rA.getItemMeta();
		rMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Aurum Ingot");
		rMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		rMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		rA.setItemMeta(rMeta);
		
		return rA;
	}
	
	public static ItemStack getPrasinus() {
		ItemStack pS = new ItemStack(Material.EMERALD);
		ItemMeta pMeta = pS.getItemMeta();
		pMeta.setDisplayName(ChatColor.DARK_GREEN + "Prasinus");
		pMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		pMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		pS.setItemMeta(pMeta);
		
		return pS;
	}
	
	public static ItemStack getLambdaAter() {
		ItemStack lA = new ItemStack(Material.BLACKSTONE);
		ItemMeta lMeta = lA.getItemMeta();
		lMeta.setDisplayName(GeneralUtils.hexToChat("202021", "Lambda Ater"));
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		lA.setItemMeta(lMeta);
		
		return lA;
	}
	
	public static ItemStack getSilicomium() {
		ItemStack sC = new ItemStack(Material.COAL);
		ItemMeta sMeta = sC.getItemMeta();
		sMeta.setDisplayName(ChatColor.DARK_GRAY + "Silicomium");
		
		sC.setItemMeta(sMeta);
		
		return sC;
	}
	
	public static ItemStack getStercus() {
		ItemStack sT = new ItemStack(Material.CHARCOAL);
		ItemMeta sMeta = sT.getItemMeta();
		sMeta.setDisplayName(ChatColor.GRAY + "Stercus");
		
		sT.setItemMeta(sMeta);
		
		return sT;
	}
	
	public static ItemStack getLambdaStick() {
		ItemStack lS = new ItemStack(Material.STICK);
		ItemMeta lMeta = lS.getItemMeta();
		lMeta.setDisplayName(GeneralUtils.hexToChat("c69500", "Lambda Stick"));
		lMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		lS.setItemMeta(lMeta);
		
		return lS;
	}
	
}
