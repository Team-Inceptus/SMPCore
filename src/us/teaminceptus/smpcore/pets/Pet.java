package us.teaminceptus.smpcore.pets;

import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import us.teaminceptus.smpcore.utils.GeneralUtils;

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
	
	public static ItemStack getIcon(Type t) throws CommandSyntaxException {
		if (t.equals(Pet.Type.DAMAGE)) {
			ItemStack dmgPet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Damage Pet\",\"color\":\"dark_red\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;582869171,-761640499,-1516239665,1326477683],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgzYWI0MmYyMmFkZDkzNjZkODkzNjRiYTNhZTIwMTNmYTQ1YTQ1NWNkODdjYTZhYWQ4MmY0MDFhNzcifX19\"}]}}}}");
			return dmgPet;
		} else if (t.equals(Pet.Type.DEFENSE)) {
			ItemStack defPet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Defense Pet\",\"color\":\"green\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;1920685385,-2076357520,-1826153904,-750453537],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODMxMjkxZjhhYmZkOGFiNDVlN2RmMmEyODE2ZTgwZmIyYWUxNmI3ZjFhNDM5YWUzZDNjZTZhYjFlMjg1YmUzOCJ9fX0=\"}]}}}}");
			return defPet;
		} else if (t.equals(Pet.Type.SPEED)) {
			ItemStack spdPet = GeneralUtils.itemFromNBT("{id:\"minecraft:player_head\",Count:1b,tag:{display:{Name:'{\"text\":\"Speed Pet\",\"color\":\"blue\",\"bold\":true,\"italic\":false}'},SkullOwner:{Id:[I;1467310334,1173374197,-1782880167,1306618016],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZjZjU3MWQ4ZDBiN2E1YzQ5M2QyY2ZlZGFlYmVkOWIzZGUxMGFmMzJlYWFiZTZiNWNlOGU0MWNlZGI4OWQyZiJ9fX0=\"}]}}}}");
			return spdPet;
		}
		
		return null;
	}
	
	public static Pet fromTier(int tier, Pet.Type type) {
		if (type.equals(Type.DAMAGE)) {
			if (tier == 1) return FURNICA;
			else if (tier == 2) return RATONII;
			
			else return null;
		}
		
		else return null;
	}
	
	public static double getModifier(double original, Pet p, Pet.Type type) {
		if (type.equals(Pet.Type.DAMAGE)) {
			if (p.equals(FURNICA)) return (original + 2);
			else if (p.equals(RATONII)) return (original + 4.5);
			else if (p.equals(SOBOLUN)) return (original + 6);
			else if (p.equals(TANTAR)) return (original + 8);
			else if (p.equals(GANDAC)) return (original + 10);
			else if (p.equals(ZBURRA)) return (original + 12);
			else if (p.equals(TERMIT)) return (original + 14.5);
			else if (p.equals(VIESPE)) return (original + 17);
			else if (p.equals(BABUSCA)) return (original + 19.5);
			else if (p.equals(GARGAUN)) return (original + 25);
			else if (p.equals(BIFEAZA)) return (original + 30);
			else if (p.equals(HOMAR)) return (original + 55);
			else if (p.equals(MEDUZE)) return (original + 75);
			else if (p.equals(SARPE)) return (original + 100);
			// Titan
			else if (p.equals(PARALE)) return (original * 1.2);
			else if (p.equals(GROBIAN)) return (original * 1.5);
			else if (p.equals(MAIMULA)) return (original * 1.7);
			else if (p.equals(RARITATI)) return (original * 2.1);
			else if (p.equals(TAUR)) return (original * 2.8);
			else if (p.equals(RECHIN)) return (original * 4);
			else if (p.equals(VESTEJI)) return (original * 6);
			else if (p.equals(DUMNEZU)) return (original * 10);
			
			else return original;
		} else if (type == Type.SPEED) {
			if (p == LENES) return (original + 0.1d);
			else return original;
		}
		
		else return original;
	}
	
	public static double[] getModifier(double defense, double health, Pet p) {
		if (p.equals(ZBOR)) return (new double[]{health + 2, defense + 1});
		else if (p.equals(CARTITA)) return (new double[]{health + 3, defense + 1.5});
		else if (p.equals(SOARECI)) return (new double[]{health + 4.5, defense + 3});
		else return (new double[]{0, 0});
	}
	
	
	
	public static int getTier(Pet p) {
		if (p.equals(FURNICA) || p.equals(ZBOR) || p.equals(LENES)) return 1;
		else if (p.equals(RATONII) || p.equals(CARTITA) || p.equals(VEVERITA)) return 2;
		else if (p.equals(SOBOLUN) || p.equals(SOARECI) || p.equals(PURICII)) return 3;
		else if (p.equals(TANTAR) || p.equals(GREIER) || p.equals(PAINJENI)) return 4;
		else if (p.equals(GANDAC) || p.equals(POLI) || p.equals(MOLIE)) return 5;
		else if (p.equals(ZBURRA) || p.equals(PICIOARE) || p.equals(ALBINA)) return 6;
		else if (p.equals(TERMIT) || p.equals(BROASCA) || p.equals(MORMOLOC)) return 7;
		else if (p.equals(VIESPE) || p.equals(OMIDA) || p.equals(FLUTURE)) return 8;
		else if (p.equals(BABUSCA) || p.equals(MELC) || p.equals(LACUSTA)) return 9;
		else if (p.equals(GARGAUN) || p.equals(VIERME) || p.equals(BUBURUZA)) return 10;
		else if (p.equals(BIFEAZA) || p.equals(SOMON) || p.equals(PESTE)) return 11;
		else if (p.equals(HOMAR) || p.equals(TESTOASA) || p.equals(CALAMAR)) return 12;
		else if (p.equals(MEDUZE) || p.equals(PITON) || p.equals(TON)) return 13;
		else if (p.equals(SARPE) || p.equals(NAMILA) || p.equals(VIPERA)) return 14;
		else if (p.equals(PARALE) || p.equals(BIROL) || p.equals(COLOS)) return 15;
		else if (p.equals(GROBIAN) || p.equals(MACARA) || p.equals(PAJURA)) return 16;
		else if (p.equals(MAIMULA) || p.equals(LUP) || p.equals(GNU)) return 17;
		else if (p.equals(RARITATI) || p.equals(SURICAT) || p.equals(CERB)) return 18;
		else if (p.equals(TAUR) || p.equals(APARALOR) || p.equals(GHEPARD)) return 19;
		else if (p.equals(RECHIN) || p.equals(MATAHALA) || p.equals(GUSCA)) return 20;
		else if (p.equals(VESTEJI) || p.equals(CARO) || p.equals(SOPARLA)) return 21;
		else if (p.equals(DUMNEZU) || p.equals(LISUS) || p.equals(BALAUR)) return 22;
		else return 0;
	}
}
