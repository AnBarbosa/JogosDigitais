package br.edu.ufabc.alunos.core;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.alunos.model.battle.Character;

public enum GameMaster {
	
	INSTANCE;
	
	private static br.edu.ufabc.alunos.model.battle.Character player;
	private static GameApplication game;
	
	public static void setPlayer(Character thePlayer){
		player = thePlayer;
	}
	
	public static br.edu.ufabc.alunos.model.battle.Character getPlayer(){
		return player;
	}

	public static void init(GameApplication game) {
		GameMaster.game = game;
		
	}

	public static AssetManager getAssetManager() {
		return GameApplication.getAssetManager();
	}

}
