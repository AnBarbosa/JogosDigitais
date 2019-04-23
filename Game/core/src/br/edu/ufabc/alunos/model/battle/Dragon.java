package br.edu.ufabc.alunos.model.battle;

public class Dragon extends Character {
	
	public Dragon(int str,int dex,int con,int magic,
			int mind,int level,int exp,String name) {
		int calcHp =((this.gerador.nextInt(21) + con)*10)*level;
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
		damage = (int) ((damage - this.mind)*(1-(this.con/100)));
		this.current_hp -= damage;		
	}

	@Override
	public int damage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(15)+this.str)*(1 + (this.level/10)));
		return damage;
	}

	@Override
	public int magicalDamage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(15)+this.magic)*(1 + (this.level/10)));
		return damage;
	}

	@Override
	public int iniciativa() {
		int ini = this.gerador.nextInt(21)+this.dex+this.level;
		return ini;
	}
	@Override
	public void evolveHp() {
		int calcHp = this.gerador.nextInt(21) + this.con;
		this.hp += calcHp;
		this.current_hp += calcHp;
		
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

