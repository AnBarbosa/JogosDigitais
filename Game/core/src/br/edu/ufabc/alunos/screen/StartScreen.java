package br.edu.ufabc.alunos.screen;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.controllers.DialogueController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;
import br.edu.ufabc.alunos.model.dialog.ClassDialogue;
import br.edu.ufabc.alunos.model.dialog.DialogueNode;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.ui.DialogueBox;
import br.edu.ufabc.alunos.ui.OptionBox;

public class StartScreen extends WorldGameScreen {


	private Viewport gameViewport;
	private int uiScale = 2;
	
	private Stage uiStage;
	private Table rootUI;
	private DialogueTree dialogue;
	private DialogueController dialogueController;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	
	public StartScreen(GameApplication game) {
		super(game);
		gameViewport = new ScreenViewport();
		GameMaster.init(game);
		initUI();

		setupInputProcessors();
		
		ClassDialogue classDialogue = exampleDialog();

		classDialogue.start();
		this.updateScreen(0);
	}
	

	private void setupInputProcessors() {
		dialogueController = new DialogueController(dialogueBox, optionBox);
		InputMultiplexer im = super.multiplexer;

		im.addProcessor(0, dialogueController);

		super.addInputProcessor(new InputAdapter() {
			private boolean debugUI = false;
			@Override
			public boolean keyUp(int keycode) {
				if(keycode == Input.Keys.U) {
					debugUI = !debugUI;
					uiStage.setDebugAll(debugUI);
					return true;
				}
				return false;
			}});
		
	}
	
	private ClassDialogue exampleDialog() {
		
		ClassDialogue dialogue = new ClassDialogue(dialogueController, this);
		return dialogue;
	}

	private void startBattle(BattleCharacter enemy) {
		Color color = Color.BLACK;
		Map<String, Object> arrange = BattleScreen.getDefaultArrange();
		arrange.put("Enemy", enemy);
		BattleScreen bs = new BattleScreen(game, arrange);
		game.startTransition(this, bs, 
				new FadeOutTransition(0.8f, color),
				new FadeInTransition(0.8f, color),
				null);
	}
	
	private void debugCommands() {
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			System.out.println("Starting dialogue.");
			dialogueController.startDialogue(dialogue);
		}

		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			System.out.println("Starting dialogue.");
			game.setScreen(new DungeonScreen(game));;
		}

		if(Gdx.input.isKeyJustPressed(Keys.F1)) {
			BattleCharacter player = CharacterCreator.getPlayer(CharacterCreator.Player.WARRIOR, "Guerreiro", 5);
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.DRAGON, 1, false);
			Map<String, Object> arrange = BattleScreen.getDefaultArrange();
			arrange.put("Enemy", enemy);
			arrange.put("Player", enemy);
			GameMaster.setPlayerStat("Last_Screen", this);
			game.setScreen(new BattleScreen(game));
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F2)) {
			BattleCharacter player = CharacterCreator.getPlayer(CharacterCreator.Player.WARRIOR, "Guerreiro", 5);
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.MINOTAUR, 1, false);
			Map<String, Object> arrange = BattleScreen.getDefaultArrange();
			arrange.put("Enemy", enemy);
			arrange.put("Player", enemy);
			GameMaster.setPlayerStat("Last_Screen", this);
			game.setScreen(new BattleScreen(game));
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F3)) {
			BattleCharacter player = CharacterCreator.getPlayer(CharacterCreator.Player.WARRIOR, "Guerreiro", 5);
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.KOBOLD, 1, false);
			Map<String, Object> arrange = BattleScreen.getDefaultArrange();
			arrange.put("Enemy", enemy);
			arrange.put("Player", enemy);
			GameMaster.setPlayerStat("Last_Screen", this);
			game.setScreen(new BattleScreen(game));
		}
		
		
	}
	
	private void initUI() {
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		
		
		rootUI = new Table();
		rootUI.setFillParent(true);
		uiStage.addActor(rootUI);
		
		dialogueBox = new DialogueBox(getApp().getSkin());
		dialogueBox.setVisible(false);
		
		optionBox = new OptionBox(getApp().getSkin());
		optionBox.setVisible(false);
		
		Table dialogTable = new Table();
		dialogTable.add(optionBox).expand().
									align(Align.right).
									space(8f).
									row();
		dialogTable.add(dialogueBox).expand().align(Align.right).space(8f).row();
		rootUI.add(dialogTable).expand().align(Align.bottom);
		//uiStage.setDebugAll(true);
	}
	


	@Override
	public void updateScreen(float delta) {
		super.updateScreen(delta);
		debugCommands();
		dialogueController.update(delta);
		uiStage.act(delta);
//		battleStage.act(delta);
//		fadeStage.act(delta);
	}
	
	@Override
	public void drawScreen(float delta) {
		super.drawScreen(delta);
//		gameViewport.apply();
//		fadeStage.draw();
		uiStage.draw();
//		battleStage.draw();
	}
	

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0,0, width, height);
		uiStage.getViewport().update(width/uiScale,  height/uiScale, true);
		gameViewport.update(width, height);
	}

}
