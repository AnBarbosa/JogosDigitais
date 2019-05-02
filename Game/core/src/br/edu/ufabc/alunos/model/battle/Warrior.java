package br.edu.ufabc.alunos.model.battle;


public class Warrior extends BattleCharacter {

	public Warrior(int str,int dex,int con,int magic,
			int mind,int level,int exp,String name) {
		int calcHp = (this.gerador.nextInt(11) + con)*level;
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
	 
	}
		
	@Override
	public void reciveDamege(int damage) {
		
		damage = (int) ((damage - this.con) * (1 -(this.dex/100)));
		
		this.current_hp -= damage;
		
	}

	@Override
	public void reciveMagicalDamege(int damage) {
		
		damage = (int) ((damage -this.con)*(1.5-(this.mind/100)));
		this.current_hp -= damage;
	}

	@Override
	public int damage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(9)+this.str)*(0.8 + (this.level/10)));
		
		return damage;
	}

	@Override
	public int magicalDamage() {
		int damage;
		damage = (int) ((this.gerador.nextInt(5)+this.magic)*(0.5 + (this.level/10)));
		
		return damage;
	}

	@Override
	public int iniciativa() {
		int ini = this.gerador.nextInt(21)+this.dex;
		return ini;
	}

	@Override
	public void evolveHp() {
		int calcHp = this.gerador.nextInt(11) + this.con;
		this.hp += calcHp;
		this.current_hp += calcHp;
		
	}
	
	@Override
	public String getNormalAttackText() {
		String formato = "%s ataca com %s";
		String criaturas[] = {
				"Você"
		};
		String armas[] = {
			"golpes ágeis.",
			"sua espada.",
			"força incomum."
		};
		int indexArmas = (int) (Math.random()*armas.length);
		int indexCriatura = (int) (Math.random()*criaturas.length);
		
		return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);

	}

	@Override
	public String getMagicAttackText() {			
		String formato = "%s utiliza %s";
		String criaturas[] = {
				"Você"
		};
		String armas[] = {
			"uma magia simples.","uma magia simples.","uma magia simples.",
			"uma pequena bola de fogo.", "pequenas chamas.", "um ritual comum.",
			"um pergaminho mágico.",
		};
		int indexArmas = (int) Math.random()*armas.length;
		int indexCriatura = (int) Math.random()*criaturas.length;
		
		return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);

	}

}
