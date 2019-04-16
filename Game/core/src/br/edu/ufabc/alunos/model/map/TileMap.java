package br.edu.ufabc.alunos.model.map;

import java.util.Arrays;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.utils.Log;

public class TileMap {

	protected Tile[][] tiles;
	protected int width;
	protected int height;

	public TileMap() {
		super();
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean isPassable(int x, int y) {
		Log.printf("Is Passable %d, %d?\n", x,y);
		if(x < 0 || x >= this.width) {
			Log.println("Not Passable. X out of bounds.");
			return false;
		}
		if(y < 0 || y >= this.height) {
			Log.println("Not Passable. Y out of bounds.");
			return false;
		}
		Log.println("Maybe, depends of the tile. Tile? "+ tiles[x][y].isPassable());
		return tiles[x][y].isPassable();
	}

	public boolean putActorAt(Actor actor, int x, int y) {
		Tile t = this.getTile(x,  y);
		if(t != null) {
			t.setActor(actor);
			return true;
		} else {
			return false;			
		}
	}

	public boolean moveActorFromTo(Actor actor, int oldX, int oldY, int newX, int newY) {
		if(this.putActorAt(actor, newX, newY)) {
			if(this.removeActor(actor, oldX, oldY)) {
				return true;
			} else {
				Log.printf("Te actor was added to %d, %d but we could not remove it from from %d, %d.\nCheck if it is on this position.", newX, newY, oldX, oldY);
			}
		} else {
			Log.printf("Could not put actor %s on %d, %d.", actor.getName(), newX, newY);
		}
		return false;
	}

	public boolean removeActor(Actor actor, int x, int y) {
		Tile t = this.getTile(x, y);
		if(t == null) {
			return false;
		}
		Actor currentActor = t.getActor();
		if(currentActor != null && currentActor.equals(actor)) {
			t.setActor(null);
			return true;
		} else {
			return false;
		}
		
	}

}