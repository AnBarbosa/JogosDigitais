package br.edu.ufabc.alunos.controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.AnimatedActor;
import br.edu.ufabc.alunos.model.map.DIRECTION;
import br.edu.ufabc.alunos.utils.Log;

/* This player controller also have a timer and permits the player to
 * reface without moving.
 */
public class PlayerControllerAdvanced extends InputAdapter {
	
	private static final float WALK_REFACE_THRESHOLD = 0.1f;


	private float[] buttonTimer;
	private AnimatedActor player;
	private Map<COMANDO, Boolean> comandos;
		
	public PlayerControllerAdvanced(Actor player) {
		super();
		this.player = (AnimatedActor) player;
		this.comandos = new HashMap<>();
		int numberOfCommands = COMANDO.values().length;
		this.buttonTimer = new float[numberOfCommands];
		for (COMANDO c : COMANDO.values()) {
			comandos.put(c, false);
			buttonTimer[c.ordinal()] = 0f;
		}
	}
	
	private float getButtonTimer(COMANDO command) {
		return buttonTimer[commandDirection(command).ordinal()];
	}
	
	private float getButtonTimer(DIRECTION dir) {
		return buttonTimer[dir.ordinal()];
	}
	
	private void increaseButtonTimer(COMANDO command, float delta) {
		buttonTimer[commandDirection(command).ordinal()] += delta;
	}
	
	private void increaseButtonTimer(DIRECTION dir, float delta) {
		buttonTimer[dir.ordinal()] += delta;
	}
	
	private void resetButtonTimer(COMANDO command) {
		buttonTimer[commandDirection(command).ordinal()] = 0;
	}
	private void resetButtonTimer(DIRECTION dir) {
		buttonTimer[dir.ordinal()] = 0;
	}
	public void update(float delta) {
		if(isCommand(COMANDO.UP)) {
			processMoveCommand(COMANDO.UP, delta);
			System.out.println("walk north");
		}
		if(isCommand(COMANDO.DOWN)) {
			processMoveCommand(COMANDO.DOWN, delta);
			System.out.println("walk south");
		}
		if(isCommand(COMANDO.LEFT)) {
			processMoveCommand(COMANDO.LEFT, delta);
			System.out.println("walk west");
		}
		if(isCommand(COMANDO.RIGHT)) {
			processMoveCommand(COMANDO.RIGHT, delta);
			System.out.println(buttonTimer[COMANDO.RIGHT.ordinal()]);
			System.out.println("walk east");
		}
	}

	public boolean isCommand(COMANDO c) {
		return comandos.get(c);
	}

	private DIRECTION commandDirection(COMANDO c) {
		switch(c) {
			case UP:
				return DIRECTION.NORTH;
			case DOWN:
				return DIRECTION.SOUTH;
			case RIGHT:
				return DIRECTION.EAST;
			case LEFT:
				return DIRECTION.WEST;
			default:
				return DIRECTION.EAST;
		}
	
	}
	@Override
	public boolean keyDown(int keycode) {
		COMANDO com = Controls.getComando(keycode);
		comandos.put(com, true);
		switch (com) {
		case UP:
		case DOWN:
		case RIGHT:
		case LEFT:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public boolean keyUp(int keycode) {
		Log.println("Player Controller: KeyUP " + keycode + "("+Input.Keys.toString(keycode)+")");
		COMANDO command = Controls.getComando(keycode);
		releaseCommand(command);
		
		switch (command) {
			case UP:
			case DOWN:
			case RIGHT:
			case LEFT:
				comandos.forEach((k,v)-> Log.println(k+": "+v));
				return true;
			default:
				return false;
		}
	}
	
	private void releaseCommand(COMANDO comm){
		comandos.put(comm, false);
		buttonTimer[comm.ordinal()] = 0f;
	}
	
	private void processMoveCommand(COMANDO command, float time) {
		updateTimer(command, time);
		considerReface(commandDirection(command));
		considerMove(commandDirection(command));
	}
	
	private void updateTimer(COMANDO command, float time) {
		increaseButtonTimer(command, time);
	}
	
	private void resetTimer(DIRECTION dir) {
		resetButtonTimer(dir);
	}
	private void considerMove(DIRECTION dir) {
		System.out.println(buttonTimer[commandDirection(COMANDO.RIGHT).ordinal()]);
		if(getButtonTimer(dir) > WALK_REFACE_THRESHOLD) {
			player.move(dir);
		}
	}
	private void considerReface(DIRECTION dir) {
		if(getButtonTimer(dir) < WALK_REFACE_THRESHOLD) {
			player.reface(dir);
		}
	}
}
