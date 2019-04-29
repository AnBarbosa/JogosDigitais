package br.edu.ufabc.alunos.core;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.battle.Character;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.CharacterCreator.Player;
import br.edu.ufabc.alunos.screen.FadeInTransition;
import br.edu.ufabc.alunos.screen.FadeOutTransition;
import br.edu.ufabc.alunos.screen.Transition;

public enum GameMaster {
	
	INSTANCE;
	
	private static br.edu.ufabc.alunos.model.battle.Character player;
	private static GameApplication game;
	
	public static void setPlayer(Character thePlayer){
		player = thePlayer;
	}
	
	public static br.edu.ufabc.alunos.model.battle.Character getPlayer(){
		if(player != null) {
			return player;
		}
		return CharacterCreator.getPlayer(Player.WARRIOR, "Guerreiro.", 1);
	}

	public static void init(GameApplication game) {
		GameMaster.game = game;
		
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
			public void doAction() {
				return;
			}
		};
		return action;
	}

}
