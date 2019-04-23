package br.edu.ufabc.alunos.controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;

public class Controls {
	private static Map<Integer, COMANDO> controles = new HashMap<>();
	
	public static void defaultInit() {
		controles.put(Keys.UP,  COMANDO.UP);
		controles.put(Keys.DOWN,  COMANDO.DOWN);
		controles.put(Keys.LEFT,  COMANDO.LEFT);
		controles.put(Keys.RIGHT,  COMANDO.RIGHT);
		controles.put(Keys.ENTER,  COMANDO.OK);
		controles.put(Keys.BACKSPACE,  COMANDO.CANCEL);
		controles.put(Keys.ESCAPE,  COMANDO.QUIT);
		
		controles.put(Keys.W,  COMANDO.UP);
		controles.put(Keys.S,  COMANDO.DOWN);
		controles.put(Keys.A,  COMANDO.LEFT);
		controles.put(Keys.D,  COMANDO.RIGHT);
		controles.put(Keys.Z,  COMANDO.OK);
		controles.put(Keys.X,  COMANDO.CANCEL);
		controles.put(Keys.Q,  COMANDO.QUIT);
		
		// Usar os Keys.BUTTON para aceitar um controle além do teclado.
		controles.put(Keys.W,  COMANDO.UP);
		controles.put(Keys.S,  COMANDO.DOWN);
		controles.put(Keys.A,  COMANDO.LEFT);
		controles.put(Keys.D,  COMANDO.RIGHT);
		controles.put(Keys.Z,  COMANDO.OK);
		controles.put(Keys.X,  COMANDO.CANCEL);
		controles.put(Keys.Q,  COMANDO.QUIT);
		
	}
	
	public static COMANDO getComando(int keycode) {
		return controles.getOrDefault(keycode, COMANDO.UNKNOWN);
	}
	
	public static void addComando(int keycode, COMANDO comando) {
		controles.put(keycode, comando);
	}
	
	public static boolean isUnknown(int keycode) {
		return !controles.containsKey(keycode);
	}
	
	public static boolean isComando(int keycode, COMANDO comando) {
		return controles.getOrDefault(keycode, COMANDO.UNKNOWN) == comando;
	}
	
}
