package br.edu.ufabc.alunos.model.map.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.alunos.core.Settings;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.Camera;

public class WorldRenderer {
	private AssetManager assetManager;
	private World world;
	private List<Integer> renderedObjects = new ArrayList<Integer>();
	private List<YSortable> forRendering = new ArrayList<YSortable>();
	
	public WorldRenderer(AssetManager assetManager, World world) {
		this.assetManager = assetManager;
		this.world = world;
	}

	public void render(SpriteBatch batch, Camera camera) {
		float worldStartX = Gdx.graphics.getWidth()/2 - scaled(camera.getCameraX());
		float worldStartY = Gdx.graphics.getHeight()/2 - scaled(camera.getCameraY());

		// The tiles are behind everything.
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				batch.draw(
						world.getTile(x, y).getTexture(), 
						worldStartX+scaled(x), 
						worldStartY+scaled(y), 
						Settings.SCALED_TILE_SIZE,
						Settings.SCALED_TILE_SIZE);
			}
		}
		
		// Now we collect the game Objects and Actors to be drawed
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				Actor actor = world.getTile(x, y).getActor();
				WorldObject object = world.getTile(x,y).getObject();
				if(actor != null) {
					forRendering.add(actor);
				}
				if(object != null) {
					if(renderedObjects.contains(object.hashCode())) {
						// Skip if it'ś already drawn
						continue;
					}
					if(object.isPassable()) {
						// If it's passable, we can draw it right away.
						batch.draw(object.getSprite(), 
								worldStartX+object.getWorldX()*Settings.SCALED_TILE_SIZE,
								worldStartY+object.getWorldY()*Settings.SCALED_TILE_SIZE,
								Settings.SCALED_TILE_SIZE*object.getSizeX(),
								Settings.SCALED_TILE_SIZE*object.getSizeY());

					} else {
						// If it's not, then we add to the list
						forRendering.add(object);
					} // Anyway, we add the hash so we don't redraw it.
					renderedObjects.add(object.hashCode());
				}
			}
		}
		
		Collections.sort(forRendering, new WorldObjectYComparator());
		Collections.reverse(forRendering);
		
		for (YSortable loc : forRendering) {
			if (!loc.isVisible()) {
				continue;
			}
			batch.draw(loc.getSprite(), 
					worldStartX+scaled(loc.getWorldX()),
					worldStartY+scaled(loc.getWorldY()),
					scaled(loc.getSizeX()),
					scaled(loc.getSizeY()));
		}
		
		renderedObjects.clear();
		forRendering.clear();
	}
	
	public float scaled(float dimension) {
		return dimension*Settings.SCALED_TILE_SIZE;
	}

}
