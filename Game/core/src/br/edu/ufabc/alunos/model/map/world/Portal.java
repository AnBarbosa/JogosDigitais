package br.edu.ufabc.alunos.model.map.world;

import com.badlogic.gdx.math.Vector2;

public class Portal {
	int x1, x2;
	int y1, y2;
	
	public Portal(Vector2 from, Vector2 to) {
		this.x1 = from.x1;
		this.y1 = from.y1;
		
		this.x2 = to.x2;
		this.y2 = to.y2;
	}
}
