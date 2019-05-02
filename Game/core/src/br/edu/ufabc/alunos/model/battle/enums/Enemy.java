package br.edu.ufabc.alunos.model.battle.enums;

public enum Enemy{
	 DRAGON{
		 public String getName() {
			 String names[] = {"Dragnar", "Drake",
					 "Draco", "Ryuu", 
					 "Hydra", "Tiamat", 
					 "Puff, the Fairy Dragon",
					 "Dragão De Fogo",
					 };
			 int i = (int) (Math.random()*names.length);
			 return names[i];
		 }
		 
	 }, 
	 MINOTAUR{
		 public String getName() {
			 String names[] = {"Mestre dos Labirintos", "Bull",
					 "Grande Touro", "Minotauro", 
					 "Pontius", "Minotauro", "Minotauro", "Cria de Minus",
					 "Guardião dos Labirintos"};
			 int i = (int) (Math.random()*names.length);
			 return names[i];
		 }
	 }
	 , KOBOLD{
		 public String getName() {
			 String names[] = {"Kobold", "Kobold Ligeiro",
					 "Ulba, o pequeno", "Sniss, o Kobold",
					 "Grande Nogre, o Kobold", };
			 int i = (int) (Math.random()*names.length);

			 return names[i];
		 }
	 }, 
	 RANDOM{
		 public String getName() {
			 return "monstro";
		 }
	 };

	 
	 public abstract String getName();
}