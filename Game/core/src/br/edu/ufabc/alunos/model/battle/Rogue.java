package br.edu.ufabc.alunos.model.battle;

public class Rogue extends BattleCharacter {
	
			public Rogue(int str,int dex,int con,int magic,
					int mind,int level,int exp,String name) {
				int calcHp = ((this.gerador.nextInt(7) + con)*10)*level;
				calcHp = Math.max(calcHp, 1);
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
				damage = (int) ((damage-((this.con+this.dex)/10)) * (1 -(this.dex/100)));
				this.current_hp -= Math.max(damage, 0);
						
			}

			@Override
			public void reciveMagicalDamege(int damage) {
				damage = (int) ((damage -((this.mind+this.dex)/10))*(1-(this.dex/100)));
				this.current_hp -= Math.max(damage, 0);		
			}

			@Override
			public int damage() {
				int damage, mult;
				if(this.gerador.nextInt(1)==1) {
				 mult = 10;
				}else {
					mult = 1;
				}
				damage = (int) ((this.gerador.nextInt(5)+this.str)*(0.8 + (this.level/10)));
				return damage* mult;
			}

			@Override
			public int magicalDamage() {
				int damage,mult;
				if(this.gerador.nextInt(1)==1) {
					 mult = 10;
					}else {
						mult = 1;
					}
				damage = (int) ((this.gerador.nextInt(13)+this.magic)*(0.8 + (this.level/10)));
				return damage* mult;
			}

			@Override
			public int iniciativa() {
				int ini = this.gerador.nextInt(21)+this.dex+this.mind+this.str+this.level;
				return ini;
			}
			@Override
			public void evolveHp() {
				int calcHp = this.gerador.nextInt(7) + this.con;
				this.hp += calcHp;
				this.current_hp += calcHp;
				
			}
			@Override
			public int run() {
				if(this.gerador.nextInt(1)==1) {
					int run = 1000000000;
					return run;
				}else {
					int run = this.gerador.nextInt(21)+this.dex+level;
					return run;
				}				
			}

			@Override
			public int notRun() {
				int run = this.gerador.nextInt(21)+this.dex+(level/2);
				return run;
			}
			

			@Override
			public String getNormalAttackText() {
				String formato = "%s ataca com %s";
				String criaturas[] = {
						"Você"
				};
				String armas[] = {
					"sua adaga.",
					"uma flecha.",
					"uma finta."
				};
				int indexArmas = (int) Math.random()*armas.length;
				int indexCriatura = (int) Math.random()*criaturas.length;
				
				return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);

			}

			@Override
			public String getMagicAttackText() {			
				String formato = "%s utiliza %s";
				String criaturas[] = {
						"Você"
				};
				String armas[] = {
					"uma magia menor.",
					"uma flecha encantada.",
					"uma flecha encantada.",
					"um pergaminho mágico."
				};
				int indexArmas = (int) Math.random()*armas.length;
				int indexCriatura = (int) Math.random()*criaturas.length;
				
				return String.format(formato,  criaturas[indexCriatura], armas[indexArmas]);

			}

}
