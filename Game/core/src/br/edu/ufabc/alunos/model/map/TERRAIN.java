package br.edu.ufabc.alunos.model.map;

import com.badlogic.gdx.files.FileHandle;

public enum TERRAIN {

	GRASS_1("Tile/32x32/grass.png", true),
	GRASS_2("Tile/32x32/grass2.png", true), 
	EMERALD("Tile/32x32/Esmeralda.png", true),
	STONE("Tile/32x32/stone.png", false);
	
	
	
	private final String texturePath;
	private final boolean passable;
	
	TERRAIN(String texturePath, boolean isPassable){
		this.texturePath = texturePath;
		this.passable = isPassable;
	}
	
	public String getTexturePath() {
		return this.texturePath;
	}
	
	public boolean isPassable() {
		return this.passable;
	}
	
}

// Implementar GetTexture() e IsPassable(),


