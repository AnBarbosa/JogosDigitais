package br.edu.ufabc.alunos.controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.AnimatedActor;
import br.edu.ufabc.alunos.model.DIRECTION;

public class PlayerControllerWithTimer extends InputAdapter {
	
	private static final float WALK_REFACE_THRESHOLD = 0.1f;


	private float[] buttonTimer;
	private AnimatedActor player;
	private Map<COMMAND, Boolean> comandos;
	
	public enum COMMAND {
		UP(0), RIGHT(1), DOWN(2), LEFT(4);
		
		private int index;
		private COMMAND(int i) {
			this.index = i;
		}
		
		public int getIndex() {
			return index;
		}
		public static int getIndex(COMMAND c) {
			return c.getIndex();
		}
		
	}
	
	
	
	public PlayerControllerWithTimer(Actor player) {
		super();
		this.player = (AnimatedActor) player;
		this.comandos = new HashMap<>();
		int numberOfCommands = COMMAND.values().length;
		this.buttonTimer = new float[numberOfCommands];
		for (COMMAND c : COMMAND.values()) {
			comandos.put(c, false);
			buttonTimer[c.ordinal()] = 0f;
		}
	}
	
	private float getButtonTimer(COMMAND command) {
		return buttonTimer[commandDirection(command).ordinal()];
	}
	
	private float getButtonTimer(DIRECTION dir) {
		return buttonTimer[dir.ordinal()];
	}
	
	private void increaseButtonTimer(COMMAND command, float delta) {
		buttonTimer[commandDirection(command).ordinal()] += delta;
	}
	
	private void increaseButtonTimer(DIRECTION dir, float delta) {
		buttonTimer[dir.ordinal()] += delta;
	}
	
	private void resetButtonTimer(COMMAND command) {
		buttonTimer[commandDirection(command).ordinal()] = 0;
	}
	private void resetButtonTimer(DIRECTION dir) {
		buttonTimer[dir.ordinal()] = 0;
	}
	public void update(float delta) {
		if(isCommand(COMMAND.UP)) {
			processMoveCommand(COMMAND.UP, delta);
			System.out.println("walk north");
		}
		if(isCommand(COMMAND.DOWN)) {
			processMoveCommand(COMMAND.DOWN, delta);
			System.out.println("walk south");
		}
		if(isCommand(COMMAND.LEFT)) {
			processMoveCommand(COMMAND.LEFT, delta);
			System.out.println("walk west");
		}
		if(isCommand(COMMAND.RIGHT)) {
			processMoveCommand(COMMAND.RIGHT, delta);
			System.out.println(buttonTimer[COMMAND.RIGHT.ordinal()]);
			System.out.println("walk east");
		}
	}

	public boolean isCommand(COMMAND c) {
		return comandos.get(c);
	}

	private DIRECTION commandDirection(COMMAND c) {
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
		boolean processed = false;
		switch(keycode) {
			case Keys.UP:
			case Keys.W:
				comandos.put(COMMAND.UP, true);
				processed = true;
				break;
			case Keys.DOWN:
			case Keys.S:
				comandos.put(COMMAND.DOWN, true);
				processed = true;
				break;
			case Keys.LEFT:
			case Keys.A:
				comandos.put(COMMAND.LEFT, true);
				processed = true;
				break;
			case Keys.RIGHT:
			case Keys.D:
				comandos.put(COMMAND.RIGHT, true);
				processed = true;
				break;
			default:
				processed = false;
				break;
		}
		return processed;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		boolean processed = false;
		switch(keycode) {
			case Keys.UP:
			case Keys.W:
				releaseCommand(COMMAND.UP);
				processed = true;
				break;
			case Keys.DOWN:
			case Keys.S:
				releaseCommand(COMMAND.DOWN);
				processed = true;
				break;
			case Keys.LEFT:
			case Keys.A:
				releaseCommand(COMMAND.LEFT);
				processed = true;
				break;
			case Keys.RIGHT:
			case Keys.D:
				releaseCommand(COMMAND.RIGHT);
				processed = true;
				break;
			default:
				processed = false;
				break;
		}
		if(processed) {
			comandos.forEach((k,v)-> System.out.println(k+": "+v));
		}
		return processed;
	}
	
	private void releaseCommand(COMMAND comm){
		comandos.put(comm, false);
		buttonTimer[comm.ordinal()] = 0f;
	}
	
	private void processMoveCommand(COMMAND command, float time) {
		updateTimer(command, time);
		considerReface(commandDirection(command));
		considerMove(commandDirection(command));
	}
	
	private void updateTimer(COMMAND command, float time) {
		increaseButtonTimer(command, time);
	}
	
	private void resetTimer(DIRECTION dir) {
		resetButtonTimer(dir);
	}
	private void considerMove(DIRECTION dir) {
		System.out.println(buttonTimer[commandDirection(COMMAND.RIGHT).ordinal()]);
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
