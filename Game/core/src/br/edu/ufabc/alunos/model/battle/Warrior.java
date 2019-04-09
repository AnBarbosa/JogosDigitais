package br.edu.ufabc.alunos.model.battle;

public class Warrior extends Character {

	public Warrior() {
		
	}
		
	@Override
	public void reciveDamege(int damage) {
		this.current_hp -= damage;
		
	}

	@Override
	public void reciveMagicalDamege(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int damage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int magicalDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void evolve(int exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int iniciativa() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
