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
import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.screen.AbstractScreen;
import br.edu.ufabc.alunos.screen.StartScreen;
import br.edu.ufabc.alunos.screen.Transition;
import br.edu.ufabc.alunos.screen.TransitionScreen;
import br.edu.ufabc.alunos.utils.SkinGenerator;

public class GameApplication extends Game {
	
	
	private Screen screen;
	private TransitionScreen transitionScreen;
	
	private Skin skin;
	
	private static AssetManager assetManager;
	
	@Override
	public void create() {
		Controls.defaultInit();
		GameMaster.init(this);
		
		transitionScreen = new TransitionScreen(this);
		
		this.assetManager = new AssetManager();
		
		loadAssets();

		while(!assetManager.isFinished()) {
			assetManager.update();
		}
		skin = SkinGenerator.generateSkin(assetManager);
		screen = new StartScreen(this);
		this.setScreen(screen);
		
		
	}
	
	private void loadAssets() {
		assert(assetManager != null) : "Assert Manager cannot be null.";
		assetManager.load("Tile/32x32/grass.png", Texture.class);
		assetManager.load("Tile/32x32/grass2.png", Texture.class);
		assetManager.load("Tile/32x32/wall.png", Texture.class);
		assetManager.load("Tile/32x32/stone.png", Texture.class);
		
		assetManager.load("Tile/boss.png", Texture.class);
		assetManager.load("PlaceHolder.png", Texture.class);
		assetManager.load("tutorial/graphics_packed/tiles/tilepack.atlas", TextureAtlas.class);
		assetManager.load("tutorial/graphics_unpacked/tiles/small_house.png", Texture.class);
		assetManager.load("tutorial/graphics_packed/ui/uipack.atlas", TextureAtlas.class);
		assetManager.load("tutorial/font/small_letters_font.fnt", BitmapFont.class);
		
		assetManager.load("Screens/LoseScreen.png", Texture.class);
		assetManager.load("Screens/WinScreen.png", Texture.class);
		
		assetManager.load("tutorial/graphics/statuseffect/white.png", Texture.class);
		
		assetManager.load("Charas/mage_male.png", Texture.class);
		assetManager.load("Charas/rogue_male.png", Texture.class);
		assetManager.load("Charas/warrior_male.png", Texture.class);
		
		assetManager.load("Monsters/kobold.png", Texture.class);
		assetManager.load("Monsters/dragon.png", Texture.class);
		assetManager.load("Monsters/minotaur.png", Texture.class);
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		super.render();
		
	}

	public static AssetManager getAssetManager() {
		assert(assetManager != null) : "Cannot retrieve null AssetManager.";
		return assetManager;
	}

	public Skin getSkin() {
		assert(skin != null) : "Cannot retrieve null skin.";
		return skin;
	}
	
	public void startTransition(AbstractScreen from, AbstractScreen to, Transition out, Transition in, Action action) {
		assert(transitionScreen != null): "GameApplication: transitionScreen cannot be null.";
		assert(from != null);
		assert(to != null);
		assert(out != null);
		assert(in != null);
		if(action==null) {
			action = ()-> System.out.println("NULL ACTION.");
		}

		GameMaster.setPlayerStat("Last_Screen", from);
		transitionScreen.startTransition(from, to, out, in, action);
	}
	

}
