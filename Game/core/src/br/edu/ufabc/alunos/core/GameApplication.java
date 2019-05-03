package br.edu.ufabc.alunos.core;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
	private Music initialScreen ;
	private Music mazeScreen ;
	private Music batleScreen;
	private Sound atkSound ;
	private Sound atkSound2;
	private Sound atkSound3 ;
	private Sound atkSound4;
	private Sound magicAtkSound;
	private Sound magicAtkSound2;
	
	private static AssetManager assetManager;
	
	@Override
	public void create() {
		Controls.defaultInit();
		GameMaster.init(this);
		
		transitionScreen = new TransitionScreen(this);
		
		GameApplication.assetManager = new AssetManager();
		
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
		
		  initialScreen = Gdx.audio.newMusic(Gdx.files.internal("music/LordOfTheRingsTheShire-MusicAmbience.mp3"));
		  mazeScreen = Gdx.audio.newMusic(Gdx.files.internal("music/Skyrim-Ambience-Dungeons.mp3"));
		  batleScreen = Gdx.audio.newMusic(Gdx.files.internal("music/TwoStepsFromHell-25TracksBestofAllTimeMostPowerfulEpicMusicMix.mp3"));
		  atkSound = Gdx.audio.newSound(Gdx.files.internal("sound/147287__smokebomb99__sword-slash-2.wav"));
		  atkSound2 = Gdx.audio.newSound(Gdx.files.internal("sound/147288__smokebomb99__sword-slash-1.wav"));
		  atkSound3 = Gdx.audio.newSound(Gdx.files.internal("sound/147289__smokebomb99__axe-slash-2.wav"));
		  atkSound4 = Gdx.audio.newSound(Gdx.files.internal("sound/147290__smokebomb99__axe-slash-1.wav"));
		  magicAtkSound = Gdx.audio.newSound(Gdx.files.internal("sound/249817__spookymodem__magic-missiles.wav"));
//		  magicAtkSound2 = Gdx.audio.newSound(Gdx.files.internal("sound/406063__aleks41__magic-strike.wav"));
		
		
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
	public void playMusic(String screen) { 
		   
		 switch (screen) {  
		 case "initial":  
			 if(initialScreen.isPlaying()) {
				 break;				 
			 }
			 if(mazeScreen.isPlaying()){
				 mazeScreen.stop();
			 }
			 if(batleScreen.isPlaying()){
				 batleScreen.stop();
			 }
			 initialScreen.play();
		   break;  
		case "maze":  
			 if(mazeScreen.isPlaying()) {
				 break;				 
			 }
			 if(initialScreen.isPlaying()){
				 initialScreen.stop();
			 }
			 if(batleScreen.isPlaying()){
				 batleScreen.stop();
			 }
			 mazeScreen.play();
		   break;  
		case "batle":  
			 if(mazeScreen.isPlaying()) {
				 break;				 
			 }else if(initialScreen.isPlaying()){
				 initialScreen.stop();
			 }else if(mazeScreen.isPlaying()){
				 mazeScreen.stop();
			 }
			 batleScreen.play();
			   break;    
		 }  
		
	}
	public void stopMusic(String screen) {
			switch (screen) {  
			 case "initial":  
				 initialScreen.stop();
			   break;  
			case "maze":  
				 mazeScreen.stop();
			   break;  
			case "batle":  
				 batleScreen.stop();
				   break;    
			 }  
	}
	public void playSound(String sound) { 
		Random random = new Random();
		int atk = 1+random.nextInt(3);
		int magic= 1+random.nextInt(1);
		   
		 switch (sound) {  
		 case "atk":  
			 switch (atk) {  
			 case 1:  
				 atkSound.play();
			   break;
			 case 2:  
				 atkSound2.play();
			   break; 
			 case 3:  
				 atkSound3.play();
			   break; 
			 case 4:  
				 atkSound4.play();
			   break; 
			 }  
			 
		   break;  
		case "magicatk": 
			 switch (magic) {  
			 case 1:  
				 magicAtkSound.play();
			   break;
			 case 2:  
				 magicAtkSound.play();
			   break; 
			 }  
		   break;  
		 }  
		
	}
	

}
