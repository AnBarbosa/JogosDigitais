package br.edu.ufabc.alunos.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.map.DIRECTION;
import br.edu.ufabc.alunos.model.map.TileMap;
import br.edu.ufabc.alunos.utils.AnimationSet;

public class PlayerActor extends AnimatedActor {

	public PlayerActor(TileMap map, int x, int y) {
		super(map, x, y, null);
		TextureAtlas atlas = GameMaster.getAssetManager().get("tutorial/graphics_packed/tiles/tilepack.atlas");
		
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
		
		AnimationSet playerAnimations = new AnimationSet(anims, stands);
		this.animations = playerAnimations;


	}

}
