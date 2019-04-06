package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.controllers.OptionBoxController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.model.ui.DialogueBox;
import br.edu.ufabc.alunos.model.ui.OptionBox;

public class GameScreenWithUI extends WorldGameScreen {


	private Viewport gameViewport;
	private int uiScale = 2;
	
	private Stage uiStage;
	private Table root;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	
	private OptionBoxController optionController;
	//private DialogueBox dialogueBox;
	
	public GameScreenWithUI(GameApplication game) {
		super(game);
		gameViewport = new ScreenViewport();
		initUI();
		optionController = new OptionBoxController(optionBox);
		super.addInputProcessor(optionController);
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
	
	private void initUI() {
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		
		
		root = new Table();
		root.setFillParent(true);
		uiStage.addActor(root);
		
		Table dialogTable = new Table();
		dialogueBox = new DialogueBox(getApp().getSkin());
		dialogueBox.animateText("Ola aventureiro. \n Posso te ajudar?");
		
		dialogTable.add(dialogueBox).expand().align(Align.bottom).pad(8f);
	
		Table optionTable = new Table();
		optionBox = new OptionBox(getApp().getSkin());
		optionBox.addOption("Sim");
		optionBox.addOption("Não");
		optionBox.addOption("Talvez. O que você me oferece?");
		optionTable.add(optionBox).expand().align(Align.bottom);
		
		root.add(dialogTable).expand().align(Align.bottom);
		root.add(optionTable).expand().align(Align.bottomRight).pad(10f);
		
	}

	@Override
	public void render(float delta) {
		gameViewport.apply();
		uiStage.act(delta);
		super.render(delta);
		uiStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0,0, width, height);
		uiStage.getViewport().update(width/uiScale,  height/uiScale, true);
		gameViewport.update(width, height);
	}

}
