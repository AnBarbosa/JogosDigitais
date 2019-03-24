package br.edu.ufabc.alunos.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.edu.ufabc.alunos.core.GameApplication;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Unnamed Game";
		config.width  = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		
		new LwjglApplication(new GameApplication(), config);
	}
}
