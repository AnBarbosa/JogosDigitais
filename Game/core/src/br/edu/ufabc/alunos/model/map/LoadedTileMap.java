package br.edu.ufabc.alunos.model.map;

import com.badlogic.gdx.math.Vector2;

import br.edu.ufabc.alunos.model.map.world.World;

public class LoadedTileMap extends TileMap{
	
	public int playerStartX, playerStartY;
	public int bossStartX, bossStartY;
	public static final int CHAO = 0;
	public static final int PAREDE = 1;
	public static final int JOGADOR = 9;
	public static final int BOSS = 8;
	private static GenerateMap map = new GenerateMap();
	
	public LoadedTileMap(int[][] mapa) {
		int linhas = mapa.length;
		int colunas = mapa[0].length;
		
		this.width = colunas;
		this.height = linhas;
		tiles = new Tile[colunas][linhas];
		
		for(int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				int code = mapa[i][j];
				tiles[j][i] = intToTile(code);
				if(code==JOGADOR) {
					playerStartX = i;
					playerStartY = j;
				}
				if(code==BOSS) {
					bossStartX = i;
					bossStartY = j;
				}
			}
		}
	}
	
	public static int[][] basicMap() {
		return  map.retornaMapa() ;
		
	}
	
	public static Tile intToTile(int i) {
		assert(i==CHAO || i==PAREDE || i==JOGADOR || i==BOSS);
		if(i==PAREDE) {
			return new Tile(TERRAIN.WALL);
		}
		else {
			return new Tile(TERRAIN.STONE);
		}
		
	}
	
	public static Vector2 getPlayerPosition(int[][] mapa) {
		int linhas = mapa.length;
		int colunas = mapa[0].length;
		
		float px = -1;
		float py = -1;
		for(int i = 0; i<linhas; i++) {
			for (int j = 0; j<colunas; j++) {
				int code = mapa[i][j];
				if(code==JOGADOR) {
					px = j;
					py = i;
					return new Vector2(px,py);
				}
			}
		}
		if(px< -0.5 || py < -0.5) {
			throw new IllegalArgumentException("Player not found");
		}
		return new Vector2(px,py);
		
	}
	

	public static Vector2 getBossPosition(int[][] mapa) {
		int linhas = mapa.length;
		int colunas = mapa[0].length;
		
		float px = -1;
		float py = -1;
		for(int i = 0; i<linhas; i++) {
			for (int j = 0; j<colunas; j++) {
				int code = mapa[i][j];
				if(code==BOSS) {
					px = j;
					py = i;
					return new Vector2(px,py);
				}
			}
		}
		if(px< -0.5 || py < -0.5) {
			throw new IllegalArgumentException("Boss not found");
		}
		return new Vector2(px,py);
	}
	
	public static World getWorldFrom(int[][]mapa) {
		return new World(new LoadedTileMap(mapa));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPlayerStartX() {
		return playerStartX;
	}

	public int getPlayerStartY() {
		return playerStartY;
	}

	public int getBossStartX() {
		return bossStartX;
	}

	public int getBossStartY() {
		return bossStartY;
	}
	
	
}
