package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.core.GameApplication;

public class GameScreenWithUI extends WorldGameScreen {

	private Viewport gameViewport;
	private int uiScale = 2;
	
	private Stage uiStage;
	private Table root;
	
	//private DialogueBox dialogueBox;
	
	public GameScreenWithUI(GameApplication game) {
		super(game);
		gameViewport = new ScreenViewport();
		initUI();
	}
	
	private void initUI() {
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		
		
	}
//
//	@Override
//	public void show() {
//		// TODO Auto-generated method stub
//
//	}

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
