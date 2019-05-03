package br.edu.ufabc.alunos.model.battle;

import java.util.Random;

import br.edu.ufabc.alunos.model.battle.enums.Enemy;

public class CharacterCreator {
	private static final Random gerador = new Random();
	public enum Player {
		WARRIOR, WIZARD, ROGUE;
	}
	
	public static BattleCharacter getPlayer(Player player, String name, int level) {
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
	
	public static BattleCharacter getEnemy(Enemy enemy, String name, int level, boolean boss) {
		assert(enemy != null);
		assert(name != null && name.length() > 0);
		assert(level >= 0);
		if(enemy==Enemy.RANDOM) {
			Enemy[] table = {
					Enemy.DRAGON,
					Enemy.MINOTAUR,
					Enemy.MINOTAUR,
					Enemy.MINOTAUR,
					Enemy.KOBOLD,
					Enemy.KOBOLD,
					Enemy.KOBOLD,
					Enemy.KOBOLD,
					Enemy.KOBOLD,
					Enemy.KOBOLD};
					
			
			int index = gerador.nextInt(table.length);
			enemy = table[index];
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
	

	public static BattleCharacter getEnemy(Enemy enemy,int level, boolean boss) {
		assert(enemy != null);
		String name = enemy.getName();
		if(boss) {
			name = "Grande e Poderoso **"+name+"**";
		}
		return getEnemy(enemy, name, level, boss);
		
	}
	
	public static Warrior createWarrior(String name, int level){
		int str = gerador.nextInt(4)+level;
		int dex = gerador.nextInt(3);
		int con = gerador.nextInt(4)+level;
		int magic = gerador.nextInt(2);
		int mind = gerador.nextInt(3);
		int exp =((level)*((10+level)/2));
		
		Warrior w = new Warrior(str, dex, con, magic, mind, level, exp, name);
		w.setTexturePath("Charas/warrior_male.png");
		return w; 
		
	}
	public static Wizard createWizard(String name, int level){
		int str = gerador.nextInt(2);
		int dex = gerador.nextInt(2);
		int con = gerador.nextInt(3);
		int magic = gerador.nextInt(5)+level;
		int mind = gerador.nextInt(3)+level;
		int exp =((level)*((10+level)/2));
		
		Wizard w = new Wizard(str, dex, con, magic, mind, level, exp, name);
		w.setTexturePath("Charas/mage_male.png");
		return w;
		
	}
	public static Rogue createRogue(String name, int level){
		int str = gerador.nextInt(3);
		int dex =gerador.nextInt(6)+level;
		int con = gerador.nextInt(3)+level;
		int magic = gerador.nextInt(3);
		int mind = gerador.nextInt(3)+level;
		int exp =((level)*((10+level)/2));
		
		Rogue r = new Rogue(str, dex, con, magic, mind, level, exp, name);
		r.setTexturePath("Charas/rogue_male.png");
		return r;
		
	}
	public static Dragon createDragon(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(10)+level;
			dex =gerador.nextInt(3)+level;
			con = gerador.nextInt(8)+level;
			magic = gerador.nextInt(10)+level;
			mind = gerador.nextInt(8)+level;
			exp =((level)*((10+level)/2));
				
		}else {
			str = gerador.nextInt(6)+level;
			dex =gerador.nextInt(4);
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(4)+level;
			mind = gerador.nextInt(4);
			exp =((level+5)*((10+level)/2));
		}
		Dragon d =new Dragon(str, dex, con, magic, mind, level, exp, name);
		d.setTexturePath("Monsters/dragon.png");
		return d;
		
	}
	public static Minotaur createMinotaur(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(6)+level;
			dex =gerador.nextInt(3)+level;
			con = gerador.nextInt(5)+level;
			magic = gerador.nextInt(3)+level;
			mind = gerador.nextInt(3)+level;
			exp =((level)*((10+level)/2));
				
		}else {
			str = gerador.nextInt(6)+level;
			dex =gerador.nextInt(2);
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(1);
			mind = gerador.nextInt(1);
			exp =((level)*((10+level)/2))+10;
		}
		
		Minotaur m = new Minotaur(str, dex, con, magic, mind, level, exp, name);
		m.setTexturePath("Monsters/minotaur.png");
		return m;
		
	}
	public static Kobold createKobold(String name, int level, boolean boss){
		int str,dex,con,magic,mind,exp; 
		
		if(boss) {
			str = gerador.nextInt(5)+level;
			dex =gerador.nextInt(10)+level;
			con = gerador.nextInt(6)+level;
			magic = gerador.nextInt(15)+level;
			mind = gerador.nextInt(10)+level;
			exp =((level)*((10+level)/2));
				
		}else {
			str = gerador.nextInt(2);
			dex =gerador.nextInt(5);
			con = gerador.nextInt(2);
			magic = gerador.nextInt(5)+level;
			mind = gerador.nextInt(4)+level;
			exp =((level)*((10+level)/2));
		}
		
		Kobold k = new Kobold(str, dex, con, magic, mind, level, exp, name);
		k.setTexturePath("Monsters/kobold.png");
		return k;
		
	}

}
