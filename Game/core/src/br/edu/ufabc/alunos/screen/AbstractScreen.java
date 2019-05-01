package br.edu.ufabc.alunos.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import br.edu.ufabc.alunos.core.GameApplication;

public abstract class AbstractScreen implements Screen {
	
	protected GameApplication game;
	protected InputMultiplexer multiplexer;
	
	public AbstractScreen(GameApplication game) {
		this.game = game;
		this.multiplexer = new InputMultiplexer();
	}
	
	public void addInputProcessor(InputProcessor ip) {
		multiplexer.addProcessor(ip);
		System.out.println("Multiplexer Added.");
	}
	
	public void removeInputProcessor(InputProcessor ip) {
		multiplexer.removeProcessor(ip);
		System.out.println("Multiplexer Removed.");
	}
	
	@Override
	public  void show() {
		Gdx.input.setInputProcessor(multiplexer);
		System.out.println("Multiplexer setup.");
	}
	
	@Override
	public void render(float delta) {
		this.updateScreen(delta);
		this.drawScreen(delta);
	}
	
	
	public abstract void arrangeScreen(Map<String, Object> settings);
	
	public abstract void updateScreen(float delta);
	
	public abstract void drawScreen(float delta);
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
