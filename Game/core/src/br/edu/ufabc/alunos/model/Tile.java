package br.edu.ufabc.alunos.model;

public class Tile implements Cloneable{

	private final TERRAIN terrain;
	private Actor actor;
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Tile(TERRAIN terrain) {
		this.terrain = terrain;
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
		return this.actor==null;
	}
}
