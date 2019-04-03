package br.edu.ufabc.alunos.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import br.edu.ufabc.alunos.screen.GameScreen;

public class GameApplication extends Game {
	
	
	private GameScreen screen;
	private AssetManager assetManager;
	@Override
	public void create() {
		
		this.assetManager = new AssetManager();

		assetManager.load("Tile/32x32/grass.png", Texture.class);
		assetManager.load("Tile/32x32/grass2.png", Texture.class);
		assetManager.load("PlaceHolder.png", Texture.class);
		assetManager.load("tutorial/graphics_packed/tiles/tilepack.atlas", TextureAtlas.class);
		while(!assetManager.isFinished()) {
			assetManager.update();
		}
		
		screen = new GameScreen(this);
		this.setScreen(screen);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(255,0,0,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
		
	}

	public AssetManager getAssetManager() {
		return this.assetManager;
	}

}
