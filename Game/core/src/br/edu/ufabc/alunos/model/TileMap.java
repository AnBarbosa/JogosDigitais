package br.edu.ufabc.alunos.model;

import java.util.Arrays;

import br.edu.ufabc.alunos.util.Log;

public class TileMap {

	private Tile[][] tiles;
	private int width, height;
	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		
		Tile[] tArray = {new Tile(TERRAIN.GRASS_1), new Tile(TERRAIN.GRASS_2)};
		double[] distArray = {0.8, 0.2};
		this.populateTiles(tArray, distArray);
		
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
			Log.println("NO. X out of bounds.");
			return false;
		}
		if(y < 0 || y >= this.height) {
			Log.println("NO. Y out of bounds.");
			return false;
		}
		Log.println("Maybe, depends on the tile. Tile? "+ tiles[x][y].isPassable());
		return tiles[x][y].isPassable();
	}
	
	private void populateTiles(Tile[] t) {
		int randomIndex = 0;
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if(t.length > 1) {
					randomIndex = (int) (Math.random()*t.length);
				}
				tiles[x][y] = t[randomIndex].clone();
			}
		}
	}
	
	private  void populateTiles(Tile[] t, double[] distribuition) {
		assert(t.length == distribuition.length);
		double sum = Arrays.stream(distribuition).sum();
		double[] percentilesArray = Arrays.stream(distribuition).map((v)-> v/sum).toArray();
		
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				double dice = Math.random();
				double accumulatedPercentile = percentilesArray[0];
				int tileIndex = 0;
				while(dice > accumulatedPercentile) {
					tileIndex++;
					accumulatedPercentile += percentilesArray[tileIndex];
				}
				tiles[x][y] = t[tileIndex].clone();
			}
		}
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
