package br.edu.ufabc.alunos.model;

import br.edu.ufabc.alunos.util.Log;

public class Actor {

	private int x;
	private int y;
	private TileMap map;
	
	public Actor(TileMap map, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.map = map;
		map.putActorAt(this, x, y);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public boolean move(int dx, int dy) {
		int nextX = this.getX() + dx;
		int nextY = this.getY() + dy;
		if(map.isPassable(nextX, nextY)) {
			this.setPosition(nextX,  nextY);
			return true;
		} else {
			return false;			
		}
	}
	

	private void setX(int x) {
		this.x = x;
	}
	
	private void setY(int y) {
		this.y = y;
	}

	public void setPosition(int x, int y) {
		map.removeActor(this, this.getX(), this.getY());
		if(map.putActorAt(this, x, y)) {
			this.setX(x);
			this.setY(y);
			Log.printf("Player moved to %d, %d\n", x, y);
		} else {
			Log.printf("Cannot put player on %d, %d. Occupied position?\n", x, y);
		}
	}

	public void setMap(TileMap map) {
		this.map = map;
		
	}
	
}
