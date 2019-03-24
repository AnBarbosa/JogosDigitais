package br.edu.ufabc.alunos.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import br.edu.ufabc.alunos.screen.GameScreen;

public class GameApplication extends Game {
	
	
	private GameScreen screen;
	@Override
	public void create() {
		screen = new GameScreen(this);
		this.setScreen(screen);

	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(255,0,0,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
		
	}

}
