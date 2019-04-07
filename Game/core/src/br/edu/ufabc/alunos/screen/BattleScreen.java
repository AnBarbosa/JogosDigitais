package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.ui.battle.BattleStage;


public class BattleScreen extends AbstractScreen {
	

	private int uiScale = 2;
	
	private Stage battleStage;
	private Table rootBattle;

	private Stage fadeStage;
	private Image black;
	
	protected SpriteBatch batch;
	

	public BattleScreen(GameApplication game) {
		super(game);
		
		//BattleStage
		battleStage = new Stage(new ScreenViewport());
		battleStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		rootBattle = new Table();
		rootBattle.setFillParent(true);
		battleStage.addActor(rootBattle);
		rootBattle.add(new BattleStage(getApp().getSkin())).expand().align(Align.center).pad(10f);
		
		// Fade
		fadeStage = new Stage(new ScreenViewport());
		fadeStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);
		black = new Image(new Texture(Gdx.files.internal("tutorial/graphics/transitions/black.png")));
		black.setVisible(false);
		Table fade = new Table();
		fade.setFillParent(true);
		fade.add(black);
		fadeStage.addActor(fade);
	}

	@Override
	public void render(float delta) {
		System.out.println("Rendering Battle Screen.");
		debugCommands();
		battleStage.act(delta);
		fadeStage.act(delta);
		
	
		
		battleStage.draw();
		fadeStage.draw();

	}

	private void debugCommands() {
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			game.setScreen(new GameScreenWithUI(game));
		}

	}

	@Override
	public void resize(int width, int height) {
		fadeStage.getViewport().update(width/uiScale,  height/uiScale, true);
		battleStage.getViewport().update(width/uiScale,  height/uiScale, true);
		
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

}
