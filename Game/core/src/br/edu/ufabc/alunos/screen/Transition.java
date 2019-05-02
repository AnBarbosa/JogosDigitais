package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.alunos.utils.Log;

public abstract class Transition {
	private AssetManager assetManager;
	private float duration;
	private float timer = 0f;
	private boolean finished = false;
	
	public Transition(float duration, AssetManager am) {
		this.assetManager = am;
		this.duration = duration;
		this.timer = 0;
	}
	
	
	public void update(float delta) {
		//System.out.println("Updating transition.");
		timer += delta;
		if(timer>duration) {
			finished = true;
			//System.out.println("Transition finished.");
		}
	}
	

	public boolean isFinished() {
		return finished;
	}

	public abstract void render(float delta, SpriteBatch batch);
	
	public float getProgress() {
		return (timer/duration);
	}
	
	public void restart() {
		this.timer = 0;
		this.finished = false;
	}
	
	public void setDuration(float val) {
		this.duration = val;
	}

}
