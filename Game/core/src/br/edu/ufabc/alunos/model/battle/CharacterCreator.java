package br.edu.ufabc.alunos.model.battle;

import java.util.Random;

public class CharacterCreator {
	private static final Random gerador = new Random();
	public enum Player {
		WARRIOR, WIZARD, ROGUE;
	}
	
	public enum Enemy{
		 DRAGON, MINOTAUR, KOBOLD, RANDOM;
	}
	
	public static Character getPlayer(Player player, String name, int level) {
		assert(player != null);
		assert(name != null && name.length() > 0);
		assert(level >= 0);
		
		switch (player) {
			case WARRIOR:
				return createWarrior(name, level);
			case WIZARD:
				return createWizard(name, level);
			case ROGUE:
				return createRogue(name, level);
			default:
				return null;
		}
	}
	
	public static Character getEnemy(Enemy enemy, String name, int level, boolean boss) {
		assert(enemy != null);
		assert(name != null && name.length() > 0);
		assert(level >= 0);
		if(enemy==Enemy.RANDOM) {
			int index = gerador.nextInt(3);
			enemy = Enemy.values()[index];
		}
		
		switch (enemy) {
			case DRAGON:
				return createDragon(name, level, boss);
			case MINOTAUR:
				return createMinotaur(name, level, boss);
			case KOBOLD:
				return createKobold(name, level, boss);
			default:
				return null;
		}
		
	}
	public static Warrior createWarrior(String name, int level){
		int str = gerador.nextInt(4)+level;
		int dex = gerador.nextInt(3);
		int con = gerador.nextInt(4)+level;
		int magic = gerador.nextInt(2);
		int mind = gerador.nextInt(3);
		int exp =((level)*(10+level/2));
		
		return new Warrior(str, dex, con, magic, mind, level, exp, name);
		
	}
	public static Wizard createWizard(String name, int level){
		int str = gerador.nextInt(2);
		int dex = gerador.nextInt(2);
		int con = gerador.nextInt(3);
		int magic = gerador.nextInt(5)+level;
		int mind = gerador.nextInt(3)+level;
		int exp =((level)*(10+level/2));
		
		return new Wizard(str, dex, con, magic, mind, level, exp, name);
		
	}
	public static Rogue createRogue(String name, int level){
		int str = gerador.nextInt(3);
		int dex =gerador.nextInt(6)+level;
		int con = gerador.nextInt(3)+level;
		int magic = gerador.nextInt(3);
		int mind = gerador.nextInt(3)+level;
		int exp =((level)*(10+level/2));
		
		return new Rogue(str, dex, con, magic, mind, level, exp, name);
		
	}
	public static Dragon createDragon(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(15)+level;
			dex =gerador.nextInt(5)+level;
			con = gerador.nextInt(10)+level;
			magic = gerador.nextInt(15)+level;
			mind = gerador.nextInt(10)+level;
			exp =((level)*(10+level/2));
				
		}else {
			str = gerador.nextInt(6)+level;
			dex =gerador.nextInt(4);
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(4)+level;
			mind = gerador.nextInt(4);
			exp =((level)*(10+level/2));
		}
		
		return new Dragon(str, dex, con, magic, mind, level, exp, name);
		
	}
	public static Minotaur createMinotaur(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(10)+level;
			dex =gerador.nextInt(3)+level;
			con = gerador.nextInt(15)+level;
			magic = gerador.nextInt(3)+level;
			mind = gerador.nextInt(3)+level;
			exp =((level)*(10+level/2));
				
		}else {
			str = gerador.nextInt(6)+level;
			dex =gerador.nextInt(2);
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(1);
			mind = gerador.nextInt(1);
			exp =((level)*(10+level/2));
		}
		
		return new Minotaur(str, dex, con, magic, mind, level, exp, name);
		
	}
	public static Kobold createKobold(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(5)+level;
			dex =gerador.nextInt(10)+level;
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(15)+level;
			mind = gerador.nextInt(10)+level;
			exp =((level)*(10+level/2));
				
		}else {
			str = gerador.nextInt(2);
			dex =gerador.nextInt(5);
			con = gerador.nextInt(2);
			magic = gerador.nextInt(5)+level;
			mind = gerador.nextInt(4)+level;
			exp =((level)*(10+level/2));
		}
		
		return new Kobold(str, dex, con, magic, mind, level, exp, name);
		
	}

}
