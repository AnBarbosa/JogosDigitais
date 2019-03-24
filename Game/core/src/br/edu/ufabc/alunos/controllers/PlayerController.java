package br.edu.ufabc.alunos.controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.util.Log;

public class PlayerController extends InputAdapter {
	private int dx=0;
	private int dy=0;
	private Actor player;
	
	
	public PlayerController(Actor player) {
		super();
		this.player = player;
	}


	@Override
	public boolean keyDown(int keycode) {
		boolean processed = false;
		switch(keycode) {
			case Keys.UP:
			case Keys.W:
				dy++;
				processed = true;
				break;
			case Keys.DOWN:
			case Keys.S:
				dy--;
				processed = true;
				break;
			case Keys.LEFT:
			case Keys.A:
				dx--;
				processed = true;
				break;
			case Keys.RIGHT:
			case Keys.D:
				dx++;
				processed = true;
				break;
			default:
				processed = false;
				break;
		}
		if(processed) {
			Log.printf("Trying to move (%d, %d)\n", dx, dy);
			player.move(dx, dy);
			dx = 0;
			dy = 0;
		}
		return processed;
	}
}
