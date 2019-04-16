package br.edu.ufabc.alunos.model.map.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.map.BasicTileMap;
import br.edu.ufabc.alunos.model.map.Tile;
import br.edu.ufabc.alunos.model.map.TileMap;

public class World {
	
	private String name;
	private TileMap map;
	private List<Actor> actors;
	private List<WorldObject> objects;
	
	
	public World(int width, int height) {
		this.map 	= new BasicTileMap(width, height);
		this.actors = new ArrayList<Actor>();
		this.objects= new ArrayList<WorldObject>();
	}
	
	
	public void update(float delta) {
		for (Actor a : actors) {
			a.update(delta);
		}
		for (WorldObject o: objects) {
			o.update(delta);
		}
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public List<Actor> getActors(){
		return actors;
	}
	
	public void addInnerPortal(Vector2 from, Vector2 to) {
		map.getTile((int)from.x, (int)from.y).addInnerPortal((int)to.x, (int)to.y);
	}
	
	public List<WorldObject> getWorldObjects(){
		return objects;
	}
	
	public void addActor(Actor a) {
		map.getTile(a.getX(), a.getY()).setActor(a);
		actors.add(a);
	}
	
	public void removeActor(Actor actor) {
		map.removeActor(actor,  actor.getX(), actor.getY());
		actors.remove(actor);
	}
	/** 
	 * Add a WorldObject to the World. 
	 * It must be added to the map to appear on the screen and to the objects list 
	 * to be ....
	 * 
	 * @param object What will be added.
	 */
	public void addObject(WorldObject object) {
		int tileX;
		int tileY;
		for(GridPoint2 point: object.getTiles()) {
			tileX = object.getX() + point.x;
			tileY = object.getY() + point.y;
			map.getTile(tileX,  tileY).setObject(object);
		}
		objects.add(object);
	}
	
	public int getHeight() {
		return map.getHeight();
	}
	
	public int getWidth() {
		return map.getWidth();
	}
	
	public Tile getTile(int x, int y) {
		return map.getTile(x, y);
	}
}
