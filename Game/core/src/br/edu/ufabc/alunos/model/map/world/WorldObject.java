package br.edu.ufabc.alunos.model.map.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

public class WorldObject implements YSortable {
	private int x,y;
	
	private TextureRegion texture;
	private float sizeX, sizeY;
	
	private boolean walkable;
	private boolean visible;
	private boolean passable;
	
	private List<GridPoint2> tiles;
	
	public WorldObject(int x, int y, float sizeX, float sizeY, 
						boolean walkable, boolean passable, boolean visible,
						TextureRegion texture, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.texture = texture;
		this.walkable = walkable;
		this.passable = passable;
		this.visible = visible;
		this.tiles = new ArrayList<GridPoint2>();
		
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
	}
	
	public List<GridPoint2> getTiles() {
		return new ArrayList(tiles);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update(float delta) {
				
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	@Override
	public TextureRegion getSprite() {
		return texture;
	}

	@Override
	public float getWorldX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getWorldY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public float getSizeX() {
		// TODO Auto-generated method stub
		return sizeX;
	}

	@Override
	public float getSizeY() {
		// TODO Auto-generated method stub
		return sizeY;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}
}
