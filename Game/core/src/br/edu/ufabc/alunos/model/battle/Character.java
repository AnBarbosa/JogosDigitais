package br.edu.ufabc.alunos.model.battle;

import java.util.Random;

import br.edu.ufabc.alunos.ui.battle.CharaBox;

public abstract class Character {
	
	protected String name;
	protected int hp;
	protected int current_hp;
	protected int str;
	protected int dex;
	protected int con;
	protected int magic;
	protected int mind;
	protected int level;
	protected int exp;
	public abstract void reciveDamege(int damage);
	public abstract void reciveMagicalDamege(int damage);
	public abstract int damage();
	public abstract int magicalDamage();
//	public abstract void evolve(int exp);
	public abstract void evolveHp();
	public abstract int iniciativa();
	protected Random gerador = new Random();
	
	public void evolve(int exp) {
		this.exp += exp;
		// só sobe um nivel por vez mesmo qua ganhe muito xp
		if(this.exp >=((this.level+1)*((10 + this.level)/2))) {
			this.level ++; 
			this.evolveHp();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCurrent_hp() {
		return current_hp;
	}
	public void setCurrent_hp(int current_hp) {
		this.current_hp = current_hp;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	public int getMagic() {
		return magic;
	}
	public void setMagic(int magic) {
		this.magic = magic;
	}
	public int getMind() {
		return mind;
	}
	public void setMind(int mind) {
		this.mind = mind;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	
	
	

}
