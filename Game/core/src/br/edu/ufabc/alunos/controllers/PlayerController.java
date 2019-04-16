package br.edu.ufabc.alunos.controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.map.DIRECTION;

public class PlayerController extends InputAdapter {
	private int dx=0;
	private int dy=0;
	private Actor player;

	
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
	
	private Map<COMMAND, Boolean> comandos;
	
	public PlayerController(Actor player) {
		super();
		this.player = player;
		this.comandos = new HashMap<>();
		for (COMMAND c : COMMAND.values()) {
			comandos.put(c, false);
		}
	}
	
	public void update(float delta) {
		if(isCommand(COMMAND.UP)) {
			player.move(DIRECTION.NORTH);
			System.out.println("walk north");
		}
		if(isCommand(COMMAND.DOWN)) {
			player.move(DIRECTION.SOUTH);
			System.out.println("walk s");
		}
		if(isCommand(COMMAND.LEFT)) {
			player.move(DIRECTION.WEST);
			System.out.println("walk w");
		}
		if(isCommand(COMMAND.RIGHT)) {
			player.move(DIRECTION.EAST);
			System.out.println("walk e");
		}
	}

	public boolean isCommand(COMMAND c) {
		return comandos.get(c);
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
				comandos.put(COMMAND.UP, false);
				processed = true;
				break;
			case Keys.DOWN:
			case Keys.S:
				comandos.put(COMMAND.DOWN, false);
				processed = true;
				break;
			case Keys.LEFT:
			case Keys.A:
				comandos.put(COMMAND.LEFT, false);
				processed = true;
				break;
			case Keys.RIGHT:
			case Keys.D:
				comandos.put(COMMAND.RIGHT, false);
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
}
