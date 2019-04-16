package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.controllers.DialogueController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.DullChara;
import br.edu.ufabc.alunos.model.dialog.DialogueNode;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.ui.DialogueBox;
import br.edu.ufabc.alunos.ui.OptionBox;
import br.edu.ufabc.alunos.ui.battle.BattleField;
import br.edu.ufabc.alunos.ui.battle.FixedSizeDialogue;

public class GameScreenWithUI extends WorldGameScreen {


	private Viewport gameViewport;
	private int uiScale = 2;
	
	private Stage uiStage;
	private Stage battleStage;
	private Stage fadeStage;
	private Image black;
	private Table rootBattle;
	private Table rootUI;
	private DialogueTree dialogue;
	private DialogueController dialogueController;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	
	//private OptionBoxController optionController;
	//private DialogueBox dialogueBox;
	
	public GameScreenWithUI(GameApplication game) {
		super(game);
		gameViewport = new ScreenViewport();
		GameMaster.init(game);
		initUI();
		initBattleUI();
		initFadeUI();
		setupInputProcessors();
		
		exampleDialog();
		dialogueController.startDialogue(dialogue);
	}
	

	private void setupInputProcessors() {
		dialogueController = new DialogueController(dialogueBox, optionBox);
		InputMultiplexer im = super.multiplexer;
		//im.clear();
		im.addProcessor(0, dialogueController);
		//im.addProcessor(playerController);
		//super.multiplexer.addProcessor(0, dialogueController);
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

	private DialogueTree exampleDialog() {
		dialogue = new DialogueTree();
		DialogueNode apresentacao = new DialogueNode("Olá! \n Legal te conhecer.", 0);
		DialogueNode pergunta = new DialogueNode("Você é um Mago ou Guerreiro?", 1);
		DialogueNode mago = new DialogueNode("Eu senti mesmo uma aura mística.", 2);
		DialogueNode guerreiro = new DialogueNode("Você parece mesmo feroz.", 3);
		
		apresentacao.makeLinear().setNext(pergunta.getID());
		pergunta.addChoice("Mago", mago.getID(), ()->{setPlayer(true);System.out.println("Nas artes místicas encontrei o poder.");});
		pergunta.addChoice("Guerreiro", guerreiro.getID(), ()-> {setPlayer(false);System.out.println("Com suor e sangue conquistei meus inimigos.");});
		
		dialogue.addNode(apresentacao, pergunta, mago, guerreiro);
		return dialogue;
	}
	
	private void setPlayer(boolean mago) {
		int hp, normalDamage, magicDamage;
		String tipo;
		if(mago) {
			tipo = "Mago";
			hp = 30;
			normalDamage = 5;
			magicDamage = 30;
		} else {
			tipo = "Guerreiro";
			hp = 50;
			normalDamage = 20;
			magicDamage = 5;
		}
		GameMaster.setPlayer(new DullChara(hp, normalDamage, magicDamage, tipo));
	}
	
	private void debugCommands() {
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			System.out.println("Starting dialogue.");
			dialogueController.startDialogue(dialogue);
		}

		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			System.out.println("Testando Fixed Line numbers(3)");
			DialogueBox d = new FixedSizeDialogue(getApp().getSkin());
			rootUI.add(d);
			d.setVisible(true);
			d.animateText("I had this issue for years and accidentally solved it by setting the width and packing the label twice. Note that multiline labels were never intended to figure out their own width, so you have to set them externally, preferably from it's parent.");
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_3)) {			
			rootBattle.clear();			
			rootBattle.add(new BattleField(getApp().getSkin(), this.multiplexer)).expand().align(Align.center).pad(10f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			rootBattle.clear();
			rootBattle.add(new BattleField(getApp().getSkin(), this.multiplexer)).expand().align(Align.center).pad(10f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
			rootBattle.clear();
			rootBattle.add(new BattleField(getApp().getSkin(), this.multiplexer)).expand().align(Align.center).pad(10f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_6)) {
			black.setVisible(!black.isVisible());
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_7)) {
			game.setScreen(new BattleScreen(game));
			System.out.println("Changed Screen.");
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
		dialogTable.add(optionBox).expand().align(Align.right).space(8f).row();
		dialogTable.add(dialogueBox).expand().align(Align.right).space(8f).row();
		rootUI.add(dialogTable).expand().align(Align.bottom);
	}
	

	private void initBattleUI() {
		battleStage = new Stage(new ScreenViewport());
		battleStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		rootBattle = new Table();
		rootBattle.setFillParent(true);
		battleStage.addActor(rootBattle);
		
		
	}
	
	private void initFadeUI() {
			fadeStage = new Stage(new ScreenViewport());
			fadeStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
			black = new Image(new Texture(Gdx.files.internal("tutorial/graphics/transitions/black.png")));
			black.setVisible(false);
			Table fade = new Table();
			fade.setFillParent(true);
			fade.add(black);
			fadeStage.addActor(fade);
			//fade.debugAll();
	}

	@Override
	public void render(float delta) {
		debugCommands();
		dialogueController.update(delta);
		gameViewport.apply();
		uiStage.act(delta);
		battleStage.act(delta);
		fadeStage.act(delta);
		super.render(delta);
		fadeStage.draw();
		uiStage.draw();
		battleStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0,0, width, height);
		uiStage.getViewport().update(width/uiScale,  height/uiScale, true);
		fadeStage.getViewport().update(width/uiScale,  height/uiScale, true);
		battleStage.getViewport().update(width/uiScale,  height/uiScale, true);
		gameViewport.update(width, height);
	}

}
