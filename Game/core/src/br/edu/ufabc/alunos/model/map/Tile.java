package br.edu.ufabc.alunos.model.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.map.world.WorldObject;

public class Tile implements Cloneable{

	private final TERRAIN terrain;
	private Texture texture;
	private WorldObject object;
	private Actor actor;
	
	public Tile(TERRAIN terrain) {
		this.terrain = terrain;
		this.texture = GameApplication.getAssetManager().get(terrain.getTexturePath(), Texture.class);
		this.actor = null;
	}
	
	public TERRAIN getTerrain() {
		return terrain;
	}
	
	@Override
	public Tile clone() {
		return new Tile(this.terrain);
	}
	
	public boolean isPassable() {
		if(!terrain.isPassable()) {
			return false;
		}
		if(actor != null) {
			return false;
		}
		if(object != null) {
			return object.isPassable();
		}
		return true;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}


	public void setObject(WorldObject object) {
		this.object = object;
	}

	public WorldObject getObject() {
		return object;
	}

	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture t) {
		this.texture = t;
	}
}
