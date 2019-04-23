package br.edu.ufabc.alunos.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.edu.ufabc.alunos.controllers.Controls;
import br.edu.ufabc.alunos.screen.GameScreenWithUI;
import br.edu.ufabc.alunos.utils.SkinGenerator;

public class GameApplication extends Game {
	
	
	private Screen screen;
	private Skin skin;
	
	private static AssetManager assetManager;
	
	@Override
	public void create() {
		Controls.defaultInit();
		this.assetManager = new AssetManager();

		assetManager.load("Tile/32x32/grass.png", Texture.class);
		assetManager.load("Tile/32x32/grass2.png", Texture.class);
		assetManager.load("PlaceHolder.png", Texture.class);
		assetManager.load("tutorial/graphics_packed/tiles/tilepack.atlas", TextureAtlas.class);
		assetManager.load("tutorial/graphics_unpacked/tiles/small_house.png", Texture.class);
		assetManager.load("tutorial/graphics_packed/ui/uipack.atlas", TextureAtlas.class);
		assetManager.load("tutorial/font/small_letters_font.fnt", BitmapFont.class);
		//assetManager.load("Screens/", Texture.class);
		
		while(!assetManager.isFinished()) {
			assetManager.update();
		}
		skin = SkinGenerator.generateSkin(assetManager);
		screen = new GameScreenWithUI(this);
		this.setScreen(screen);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
		
	}

	public static AssetManager getAssetManager() {
		return assetManager;
	}

	public Skin getSkin() {
		return skin;
	}
	
	

}
