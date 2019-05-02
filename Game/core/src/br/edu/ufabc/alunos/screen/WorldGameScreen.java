package br.edu.ufabc.alunos.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import br.edu.ufabc.alunos.controllers.PlayerControllerAdvanced;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.Camera;
import br.edu.ufabc.alunos.model.PlayerActor;
import br.edu.ufabc.alunos.model.map.world.World;
import br.edu.ufabc.alunos.model.map.world.WorldObject;
import br.edu.ufabc.alunos.model.map.world.WorldRenderer;
import br.edu.ufabc.alunos.utils.Log;

public class WorldGameScreen extends AbstractScreen {

	
	protected PlayerControllerAdvanced playerController;
	
	private World world;
	private Actor player;
	private Camera camera;
	
	protected SpriteBatch batch;
	private WorldRenderer worldRenderer;
	
	public WorldGameScreen(GameApplication game) {
		super(game);
		arrangeScreen(getDefaultArrange());
	}
	public WorldGameScreen(GameApplication game, Map<String, Object> arrange) {
		super(game);
		arrangeScreen(arrange);
	}
	
	

	@Override
	public void arrangeScreen(Map<String, Object> settings) {
		// Loading settings
		assert(settings.get("World") instanceof World);
		assert(settings.get("Player_X") instanceof Integer);
		assert(settings.get("Player_Y") instanceof Integer);
		assert(settings.get("Houses") instanceof Vector2[]);
		
		this.world = (World) settings.get("World");
		int playerX = (int) settings.get("Player_X");
		int playerY = (int) settings.get("Player_Y");
		
		Vector2 houses[] = (Vector2[]) settings.get("Houses");

		// Creating the world	
		batch = new SpriteBatch();

		player = new PlayerActor(world.getMap(), playerX, playerY);
		world.addActor(player);
		playerController = new PlayerControllerAdvanced(player);
		super.addInputProcessor(playerController);
		
		camera = new Camera();
		
		worldRenderer = new WorldRenderer(getApp().getAssetManager(), 
										  world);
		
		for (Vector2 housePos : houses) {
			addHouse((int)housePos.x, (int)housePos.y);
		}
	}


	public static Map<String, Object> getDefaultArrange() {
		Map<String, Object> arrange = new HashMap<>();
		arrange.put("World", new World(100,100));
		arrange.put("Player_X", 50);
		arrange.put("Player_Y", 50);
		
		Vector2 houses[] = {new Vector2(50,53)};
		arrange.put("Houses", houses);
		return arrange;
	}

	
	

	private void addHouse(int i, int j) {
		Texture tex = (Texture) getApp().getAssetManager().get("tutorial/graphics_unpacked/tiles/small_house.png");
		TextureRegion tr = new TextureRegion(tex);
		GridPoint2 grid[] = new GridPoint2[] { new GridPoint2(0,0), new GridPoint2(0,1), new GridPoint2(0,2),		
												new GridPoint2(1,0), new GridPoint2(1,1), new GridPoint2(1,2),
												new GridPoint2(2,0), new GridPoint2(2,1),new GridPoint2(2,2),
																						 new GridPoint2(3,2),
											 new GridPoint2(4,0), new GridPoint2(4,1), new GridPoint2(4,2)};
		
		WorldObject house = new WorldObject(i, j, 5, 5, 
				false, false, true,
				tr, grid);
		
		this.world.addObject(house);
		
	}
	

	@Override
	public void updateScreen(float delta) {
		getDebugInputs();
		playerController.update(delta);
		camera.update(player.getWorldX(), player.getWorldY());
		world.update(delta);
		
		
	}

	@Override
	public void drawScreen(float delta) {
		batch.begin();
		worldRenderer.render(batch, camera);
		batch.end();
	}

	
	private void getDebugInputs() {
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			Gdx.app.exit();		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			Log.debug = !Log.debug;	
		}
		if(Gdx.input.isKeyJustPressed(Keys.R)){
			world = new World(100,100);
			player.setMap(world.getMap());
			player.setPosition(50,50);
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)){
			Log.debug = true;
			Log.println("----- TILE -------");
			for(int x=0; x<world.getWidth(); x++) {
				for(int y=0; y<world.getHeight(); y++) {
					Actor act = world.getTile(x, y).getActor();
					
					if(act != null) {
						Log.printf("TileMap: Actor at %d, %d\n", x, y);
						Log.printf("Actor: I'm at %d, %d\n\n", act.getX(), act.getY());
					}
				}
			}
			Log.println("----- END TILE -------");
			Log.debug = false;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)){
			Log.debug = true;
			Log.println("----- TILE -------");
			for(int y=world.getHeight()-1; y >= 0; y--) {
				Log.printf("\n");
				for(int x=0; x<world.getWidth(); x++) {
					Actor act = world.getTile(x, y).getActor();
					WorldObject obj = world.getTile(x,y).getObject();
					if(act == null && obj == null) {
						Log.printf(" .");
					}
					if(act != null && obj == null) {
						Log.printf(" x");
					}
					if(act == null && obj != null) {
						Log.printf(" o");
					}
					if(act != null && obj != null) {
						Log.printf(" #");
					}
				}
			}
			Log.println("\n----- END TILE -------");
			Log.debug = false;
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



}
