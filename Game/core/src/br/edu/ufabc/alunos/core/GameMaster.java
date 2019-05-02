package br.edu.ufabc.alunos.core;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;

import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.CharacterCreator.Player;
import br.edu.ufabc.alunos.screen.FadeInTransition;
import br.edu.ufabc.alunos.screen.FadeOutTransition;
import br.edu.ufabc.alunos.screen.Transition;

public enum GameMaster {
	
	INSTANCE;
	
	private static br.edu.ufabc.alunos.model.battle.BattleCharacter player;
	private static GameApplication game;
	
	private static Map<String, Object> playerStats;
	
	public static void setPlayer(BattleCharacter thePlayer){
		player = thePlayer;
	}
	
	public static br.edu.ufabc.alunos.model.battle.BattleCharacter getPlayer(){
		if(player != null) {
			return player;
		}
		return CharacterCreator.getPlayer(Player.WARRIOR, "Guerreiro.", 1);
	}

	public static void init(GameApplication game) {
		GameMaster.game = game;
		playerStats = new HashMap<>();
		
	}

	public static AssetManager getAssetManager() {
		return GameApplication.getAssetManager();
	}

	public static Transition getFadeOut() {
		final FadeOutTransition transition = new FadeOutTransition(Settings.TRANSITION_TIME, Color.BLACK, game.getAssetManager());  
		return transition;
	}
	
	public static Transition getFadeIn() {
		final FadeInTransition transition = new FadeInTransition(Settings.TRANSITION_TIME, Color.BLACK, game.getAssetManager());  
		return transition;
	}

	public static Action getNullAction() {
		final Action action = new Action() {
			@Override
			public void startAction() {
				return;
			}
		};
		return action;
	}

	public static GameApplication getGameApplication() {
		assert(game != null);
		return game;
	}

	public static void setPlayerStat(String stat, Object value) {
		assert(playerStats != null);
		playerStats.put(stat, value);
		
	}
	
	public static <T> T getPlayerStat(String stat){
		assert(playerStats != null);
		Object value = playerStats.getOrDefault(stat, null);
		return (T) value;
			
	}
	
	
	
	

}
