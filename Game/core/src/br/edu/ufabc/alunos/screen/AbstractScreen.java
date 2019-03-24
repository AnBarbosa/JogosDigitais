package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Screen;

import br.edu.ufabc.alunos.core.GameApplication;

public abstract class AbstractScreen implements Screen {
	
	protected GameApplication game;
	
	public AbstractScreen(GameApplication game) {
		this.game = game;
	}
	
	@Override
	public  abstract void show();
	
	@Override
	public abstract void render(float delta);

	@Override
	public abstract void resize(int width, int height) ;

	@Override
	public abstract void pause();

	@Override
	public abstract void resume() ;

	@Override
	public abstract void hide();

	@Override
	public abstract void dispose();
	
	public GameApplication getApp() {
		return game;
	}

}
