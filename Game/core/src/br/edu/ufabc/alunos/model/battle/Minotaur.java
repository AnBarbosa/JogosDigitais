package br.edu.ufabc.alunos.model.battle;

public class Minotaur extends BattleCharacter {
	
	public Minotaur(int str,int dex,int con,int magic,
			int mind,int level,int exp,String name) {
		int calcHp = this.gerador.nextInt(13) + con;
		this.setHp(calcHp);
		this.setCurrent_hp(calcHp);
		this.setStr(str);
		this.setDex(dex);
		this.setCon(con);
		this.setMagic(magic);
		this.setMind(mind);
		this.setLevel(1);
		this.setExp(1);
		this.setName(name);
	 
	}

	@Override
	public void reciveDamege(int damage) {
		damage = (int) ((damage-this.con) * (1 -(this.con/100)));
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
		damage = (int) ((this.gerador.nextInt(13)+this.str)*(1 + (this.level/10)));
		return damage;
	}

	@Override
	public int magicalDamage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(3)+this.magic)*(0.5 + (this.level/10)));
		return damage;
	}

	@Override
	public int iniciativa() {
		int ini = this.gerador.nextInt(21)+this.dex;
		return ini;
	}
	@Override
	public void evolveHp() {
		int calcHp = this.gerador.nextInt(13) + this.con;
		this.hp += calcHp;
		this.current_hp += calcHp;
		
	}
}
