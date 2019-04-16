package br.edu.ufabc.alunos.core;
import br.edu.ufabc.alunos.model.battle.Character;

public enum GameMaster {
	
	INSTANCE;
	
	private static br.edu.ufabc.alunos.model.battle.Character player;
	
	public static void setPlayer(Character thePlayer){
		player = thePlayer;
	}
	
	public static br.edu.ufabc.alunos.model.battle.Character getPlayer(){
		return player;
	}

}
