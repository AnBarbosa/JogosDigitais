package br.edu.ufabc.alunos.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.edu.ufabc.alunos.controllers.BattleController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.BattleField;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;


public class BattleScreen extends AbstractScreen {
	
	private BattleController controller;	
	private int uiScale = 2;
	
	private Stage battleStage;
	private Table rootBattle;

	protected SpriteBatch batch;
	private boolean fightingBoss = false;
	

	public BattleScreen(GameApplication game) {
		super(game);
		arrangeScreen(getDefaultArrange());
		//updateScreen(0);
	}
	
	public BattleScreen(GameApplication game, Map<String, Object> arrange) {
		super(game);
		arrangeScreen(arrange);
		//updateScreen(0);
	}

	public static Map<String,Object> getDefaultArrange(){
		Map<String, Object> mapSettings = new HashMap<>();
		BattleCharacter player, enemy;
		player = GameMaster.getPlayer();
		enemy = CharacterCreator.getEnemy(Enemy.RANDOM, "Um Inimigo", 1, false);
		assert(GameMaster.getPlayer().getCurrent_hp() >0);
		assert(GameMaster.getPlayer().getCurrent_hp() >0);
		mapSettings.put("Player", player);
		mapSettings.put("Enemy", enemy);
		mapSettings.put("Boss", false);
		return mapSettings;
	}

	@Override
	public void arrangeScreen(Map<String, Object> settings) {
		//BattleStage
		battleStage = new Stage(new ScreenViewport());
		
		battleStage.getViewport().update(
				Gdx.graphics.getWidth()/uiScale, 
				Gdx.graphics.getHeight()/uiScale);
		
		rootBattle = new Table();
		rootBattle.setFillParent(true);
		battleStage.addActor(rootBattle);
		
		assert(settings.get("Player") instanceof BattleCharacter);
		assert(settings.get("Enemy") instanceof BattleCharacter);
		assert(settings.get("Boss") instanceof Boolean);
		
		BattleCharacter player = (BattleCharacter)settings.get("Player");
        BattleCharacter enemy = (BattleCharacter)settings.get("Enemy");
        this.fightingBoss = (boolean) settings.get("Boss");
		BattleField bf = new BattleField(getApp().getSkin(), this.multiplexer, player, enemy); 
		rootBattle.add(bf).expand().align(Align.center).pad(10f);
		
		controller = new BattleController(bf, this);
		
		addInputProcessor(controller);
		
	}

	@Override
	public void updateScreen(float delta) {
		debugCommands();
		battleStage.act(delta);	
		controller.update(delta);
	}

	@Override
	public void drawScreen(float delta) {	
		game.playMusic("batle");
		battleStage.draw();
	}


	private void debugCommands() {
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			AbstractScreen screen = GameMaster.getPlayerStat("Last_Screen");
			assert(screen != null);
			game.startTransition(this, screen, 
					GameMaster.getFadeOut(), GameMaster.getFadeIn(),
					null);
		}

	}
	

	@Override
	public void resize(int width, int height) {
		battleStage.getViewport().update(
				width/uiScale,  
				height/uiScale, 
				true);
	}
	


	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	public void win() {
		if(fightingBoss) {
			AbstractScreen victory = new WinScreen(game);
			game.startTransition(this, victory, 
					GameMaster.getFadeOut(), GameMaster.getFadeIn(),
					null//()->victory.onTransitionIn()
					);
		} else {
			AbstractScreen screen = GameMaster.getPlayerStat("Last_Screen");
			assert(screen != null);
			game.startTransition(this, screen, 
					GameMaster.getFadeOut(), GameMaster.getFadeIn(),
					()->screen.onTransitionIn());
		}
		
		
	}
	
	public void lose() {
		game.startTransition(this, new LoseScreen(game), 
				GameMaster.getFadeOut(), GameMaster.getFadeIn(),
				null);
	}

	public void run() {
		AbstractScreen screen = GameMaster.getPlayerStat("Last_Screen");
		assert(screen != null);
		game.startTransition(this, screen, 
				GameMaster.getFadeOut(), GameMaster.getFadeIn(),
				()->screen.onTransitionIn());
		
	}

	


}
