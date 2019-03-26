package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.alunos.controllers.PlayerController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.Settings;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.Camera;
import br.edu.ufabc.alunos.model.TERRAIN;
import br.edu.ufabc.alunos.model.TileMap;
import br.edu.ufabc.alunos.util.Log;

public class GameScreen extends AbstractScreen {

	private Texture playerTexture;
	private Texture[] grass;
	private Actor player;
	private PlayerController controller;
	private SpriteBatch batch;
	private TileMap map;
	private Camera camera;
	
	
	public GameScreen(GameApplication game) {
		super(game);
		
		batch = new SpriteBatch();
		playerTexture = new Texture(Gdx.files.internal("PlaceHolder.png"));
		grass = new Texture[2];
		grass[0] = new Texture(Gdx.files.internal(TERRAIN.GRASS_1.getTexturePath()));
		grass[1] = new Texture(Gdx.files.internal(TERRAIN.GRASS_2.getTexturePath()));
		
		map = new TileMap(10,10);
		player = new Actor(map, 0,0);
		controller = new PlayerController(player);
		camera = new Camera();

	}

	@Override
	public void render(float delta) {

		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			Gdx.app.exit();		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			Log.debug = !Log.debug;	
		}
		if(Gdx.input.isKeyJustPressed(Keys.R)){
			map = new TileMap(10,10);
			player.setMap(map);
			player.setPosition(0,0);
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)){
			Log.debug = true;
			Log.println("----- TILE -------");
			for(int x=0; x<map.getWidth(); x++) {
				for(int y=0; y<map.getHeight(); y++) {
					Actor act = map.getTile(x, y).getActor();
					
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
			for(int y=map.getHeight()-1; y >= 0; y--) {
				Log.printf("\n");
				for(int x=0; x<map.getWidth(); x++) {
					Actor act = map.getTile(x, y).getActor();
					if(act == null) {
						Log.printf(" .");
					}
					if(act != null) {
						Log.printf(" x");
					}
				}
			}
			Log.println("\n----- END TILE -------");
			Log.debug = false;
		}
		
		
		camera.update(player.getX()+0.5f, player.getY()+0.5f);
		
		float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
		float worldStartY = Gdx.graphics.getHeight()/2- camera.getCameraY()*Settings.SCALED_TILE_SIZE;
		batch.begin();  
		
		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				batch.draw(grass[map.getTile(x, y).getTerrain().ordinal()],
						worldStartX+x*Settings.SCALED_TILE_SIZE,
						worldStartY+y*Settings.SCALED_TILE_SIZE,
						Settings.SCALED_TILE_SIZE,
						Settings.SCALED_TILE_SIZE
						);
			}
		}
		
		
		
		
		batch.draw(playerTexture, 
				worldStartX+player.getX()*Settings.SCALED_TILE_SIZE, // O personagem anda de tile em tile 
				worldStartY+player.getY()*Settings.SCALED_TILE_SIZE); 
		batch.end();
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
		
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
