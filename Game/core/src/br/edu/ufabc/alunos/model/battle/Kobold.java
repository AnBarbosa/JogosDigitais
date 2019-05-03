package br.edu.ufabc.alunos.model.battle;

import br.edu.ufabc.alunos.model.battle.enums.Enemy;

public class Kobold extends BattleCharacter {
	
	public Kobold(int str,int dex,int con,int magic,
			int mind,int level,int exp,String name) {
		int calcHp = ((this.gerador.nextInt(4) + con)*10)*level;
		calcHp = Math.max(calcHp, 1);
		this.setHp(calcHp);
		this.setCurrent_hp(calcHp);
		this.setStr(str);
		this.setDex(dex);
		this.setCon(con);
		this.setMagic(magic);
		this.setMind(mind);
		this.setLevel(level);
		this.setExp(exp);
		this.setName(name);
		this.type = Enemy.KOBOLD;
	}

	@Override
	public void reciveDamege(int damage) {
		damage = (int) ((damage) * (1 -(this.con/100)));
		this.current_hp -= damage;
				
	}

	@Override
	public void reciveMagicalDamege(int damage) {
		damage = (int) ((damage)*(1-(this.con/100)));
		this.current_hp -= damage;		
	}

	@Override
	public int damage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(3)+this.str)*(0.5 + (this.level/10)));
		return Math.max(damage, 0);
	}

	@Override
	public int magicalDamage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(7)+this.magic)*(1 + (this.level/10)));
		return damage;
	}

	@Override
	public int iniciativa() {
		int ini = this.gerador.nextInt(21)+this.dex;
		return ini;
	}
	@Override
	public void evolveHp() {
		int calcHp = this.gerador.nextInt(4) + this.con;
		this.hp += calcHp;
		this.current_hp += calcHp;
		
	}
	

	@Override
	public String getNormalAttackText() {
		String formato = "%s ataca com %s";
		String criaturas[] = {
				"O kobold", "O pequeno monstro", "Seu inimigo"
		};
		String armas[] = {
			"sua cauda.",
			"uma mordida.",
			"Uma espada."
		};
		int indexArmas = (int) Math.random()*armas.length;
		int indexCriatura = (int) Math.random()*criaturas.length;
		
		return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);
	}


	@Override
	public String getMagicAttackText() {
		String formato = "%s ataca com %s";
		String criaturas[] = {
				"O kobold", "O pequeno monstro", "Seu inimigo"
		};
		String armas[] = {
			"uma varinha mágica.",
			"uma magia!?!",
			"uma faca encantada."
		};
		int indexArmas = (int) Math.random()*armas.length;
		int indexCriatura = (int) Math.random()*criaturas.length;
		
		return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);
	}

	@Override
	public int run() {
		int run = this.gerador.nextInt(21)+this.dex;
		return run;
	}

	@Override
	public int notRun() {
		int run = this.gerador.nextInt(21)+this.str+(level/3);
		return run;
	}
	
}
