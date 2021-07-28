package gamercoder215.smpcore.pets;

import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import gamercoder215.smpcore.utils.GeneralUtils;

public enum Pet {
	
	FURNICA, ZBOR, LENES,
	RATONII, CARTITA, VEVERITA,
	SOBOLUN, SOARECI, PURICII,
	TANTAR, GREIER, PAINJENI,
	GANDAC, POLI, MOLIE,
	ZBURRA, PICIOARE, ALBINA,
	TERMIT, BROASCA, MORMOLOC,
	VIESPE, OMIDA, FLUTURE,
	BABUSCA, MELC, LACUSTA,
	GARGAUN, VIERME, BUBURUZA,
	BIFEAZA, SOMON, PESTE,
	HOMAR, TESTOASA, CALAMAR,
	MEDUZE, PITON, TON,
	SARPE, NAMILA, VIPERA,
	PARALE, BIROL, COLOS,
	GROBIAN,MACARA, PAJURA,
	MAIMULA, LUP, GNU,
	RARITATI, SURICAT, CERB,
	TAUR, APARALOR, GHEPARD,
	RECHIN, MATAHALA, GUSCA,
	VESTEJI, CARO, SOPARLA,
	DUMNEZU, LISUS, BALAUR;
	
	public enum Type {
		DAMAGE, DEFENSE, SPEED
	}
	
	public static ItemStack getIcon(Type t) {
		try {
			if (t.equals(Pet.Type.DAMAGE)) {
				ItemStack dmgPet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Damage Pet\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;582869171,-761640499,-1516239665,1326477683],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgzYWI0MmYyMmFkZDkzNjZkODkzNjRiYTNhZTIwMTNmYTQ1YTQ1NWNkODdjYTZhYWQ4MmY0MDFhNzcifX19\"}]}}}}");
				return dmgPet;
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int getTier(Pet p) {
		if (p.equals(CARTITA) || p.equals(ZBOR) || p.equals(LENES)) return 1;
		else if (p.equals(RATONII) || p.equals(CARTITA) || p.equals(VEVERITA)) return 2;
		else return 0;
	}
}
