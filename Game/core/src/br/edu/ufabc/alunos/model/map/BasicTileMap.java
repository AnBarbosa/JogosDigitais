package br.edu.ufabc.alunos.model.map;

import java.util.Arrays;

public class BasicTileMap extends TileMap {

	public BasicTileMap(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		
		Tile[] tArray = {new Tile(TERRAIN.GRASS_1), new Tile(TERRAIN.GRASS_2)};
		double[] distArray = {0.8, 0.2};
		this.populateTiles(tArray, distArray);
		
	}
	

	protected void populateTiles(Tile[] t, double[] distribuition) {
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

}
