package br.edu.ufabc.alunos.model.battle;

public class DullChara extends Character {

	int damage, magicDamage;
	public DullChara(int hp, int normalDamage, int magicDamage) {
		this.hp = hp;
		this.current_hp = this.hp;
		this.damage = normalDamage;
		this.magicDamage = magicDamage;
		
	}
	@Override
	public void reciveDamege(int damage) {
		this.current_hp -= damage;

	}

	@Override
	public void reciveMagicalDamege(int damage) {
		this.current_hp -= damage;

	}

	@Override
	public int damage() {
		
		return damage;
	}

	@Override
	public int magicalDamage() {
		// TODO Auto-generated method stub
		return magicDamage;
	}

	@Override
	public void evolve(int exp) {
		// TODO Auto-generated method stub

	}

	@Override
	public int iniciativa() {
		// TODO Auto-generated method stub
		return 10;
	}

}
