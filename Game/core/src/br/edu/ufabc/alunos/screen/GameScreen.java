package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.edu.ufabc.alunos.controllers.PlayerController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.Settings;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.AnimatedActor;
import br.edu.ufabc.alunos.model.Camera;
import br.edu.ufabc.alunos.model.DIRECTION;
import br.edu.ufabc.alunos.model.TERRAIN;
import br.edu.ufabc.alunos.model.TileMap;
import br.edu.ufabc.alunos.utils.AnimationSet;
import br.edu.ufabc.alunos.utils.Log;

public class GameScreen extends AbstractScreen {

	private Texture playerTexture;
	private Texture[] grass;
	private AnimatedActor player;
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
		
		
		TextureAtlas atlas = game.getAssetManager().get("tutorial/graphics_packed/tiles/tilepack.atlas");
		
		Animation[] anims = new Animation[DIRECTION.values().length];
		anims[DIRECTION.NORTH.ordinal()] = new Animation(0.3f/2f, atlas.findRegions("brendan_walk_north"), PlayMode.LOOP_PINGPONG);
		anims[DIRECTION.SOUTH.ordinal()] = new Animation(0.3f/2f, atlas.findRegions("brendan_walk_south"), PlayMode.LOOP_PINGPONG);
		anims[DIRECTION.EAST.ordinal()] =  new Animation(0.3f/2f, atlas.findRegions("brendan_walk_east"), PlayMode.LOOP_PINGPONG);
		anims[DIRECTION.WEST.ordinal()] =  new Animation(0.3f/2f, atlas.findRegions("brendan_walk_west"), PlayMode.LOOP_PINGPONG);
		
		TextureRegion[] stands = new TextureRegion[DIRECTION.values().length];
		stands[DIRECTION.NORTH.ordinal()] = atlas.findRegion("brendan_stand_north");
		stands[DIRECTION.SOUTH.ordinal()] = atlas.findRegion("brendan_stand_south");
		stands[DIRECTION.EAST.ordinal()] = atlas.findRegion("brendan_stand_east");
		stands[DIRECTION.WEST.ordinal()] = atlas.findRegion("brendan_stand_west");
		
		AnimationSet animations = new AnimationSet(anims, stands);

		map = new TileMap(10,10);
		player = new AnimatedActor(map, 0,0, animations);
		controller = new PlayerController(player);
		camera = new Camera();

		
	}

	private void getInputs() {

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
		
		
		
	}
	@Override
	public void render(float delta) {
		this.getInputs();
		controller.update(delta);
		player.update(delta);
		camera.update(player.getWorldX()+0.5f, player.getWorldY()+0.5f);
		
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
		
		
		
		
		batch.draw(player.getSprite(), 
				worldStartX+player.getWorldX()*Settings.SCALED_TILE_SIZE, // O personagem anda de tile em tile 
				worldStartY+player.getWorldY()*Settings.SCALED_TILE_SIZE); 
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
