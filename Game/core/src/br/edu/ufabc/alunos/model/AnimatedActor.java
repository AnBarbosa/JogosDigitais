package br.edu.ufabc.alunos.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import br.edu.ufabc.alunos.util.AnimationSet;

public class AnimatedActor extends Actor{
	
	private float worldX, worldY;
	
	private int srcX, srcY;
	private int destX, destY;
	private float animTimer;
	private final float ANIM_TIME = 0.5f;
	
	
	private DIRECTION facing;
	private float walkTimer;
	private boolean wasMoveRequestedThisFrame;
	
	private AnimationSet animations;
	
	private ACTOR_STATE state;
	
	public enum ACTOR_STATE{
		WALKING, STANDING;
	}

	public AnimatedActor(TileMap map, int x, int y, AnimationSet animations) {
		super(map, x, y);

		// For the animation
		this.worldX = x;
		this.worldY = y;
		this.state = ACTOR_STATE.STANDING;
		
		this.facing = DIRECTION.EAST;
		this.animations = animations;
	}
	
	private void initializeMove(int dx, int dy) {
		this.srcX = this.x;
		this.srcY = this.y;
		this.destX = this.x+dx;
		this.destY = this.y+dy;
	//	this.worldX = this.x;
	//	this.worldY= this.y;
		animTimer = 0f;
		state = ACTOR_STATE.WALKING;
	}
	
	private void initializeMove(DIRECTION d) {
		initializeMove(d.getX(), d.getY()); 
	}
	
	private void finishMove() {
		state = ACTOR_STATE.STANDING;
		animTimer = 0f;
		this.worldX = destX;
		this.worldY = destY;
		this.srcX = 0;
		this.srcY = 0;
	}
	
	@Override
	public void update(float delta) {
		if(this.state==ACTOR_STATE.WALKING) {
			this.animTimer += delta;
			this.walkTimer += delta;
			this.worldX = Interpolation.linear.apply(this.srcX, this.destX, this.animTimer/this.ANIM_TIME);
			this.worldY = Interpolation.linear.apply(this.srcY, this.destY, this.animTimer/this.ANIM_TIME);
			if(this.animTimer > this.ANIM_TIME) {
				float leftOverTime = this.animTimer-this.ANIM_TIME;
				this.walkTimer -= leftOverTime;
				this.finishMove();
				if(this.wasMoveRequestedThisFrame) {
					move(facing);
				} else {
					this.walkTimer = 0f;
				}
			}
		}
		wasMoveRequestedThisFrame = false;
	}
	
	@Override
	public boolean move(DIRECTION directionTo) {
		if (state == ACTOR_STATE.WALKING) {
			if(facing == directionTo) {
				wasMoveRequestedThisFrame = true; 
			}
			return false;
		}
		
		int nextX = this.getX() + directionTo.getX();
		int nextY = this.getY() + directionTo.getY();
		if(map.isPassable(nextX, nextY)) {
			this.initializeMove(directionTo);
			this.setPosition(nextX,  nextY);
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public float getWorldX() {
		return this.worldX;
	}
	
	@Override
	public float getWorldY() {
		return this.worldY;
	}
	
	public TextureRegion getSprite() {
		if(state==ACTOR_STATE.STANDING) {
			return animations.getStanding(facing);
		}
		if(state==ACTOR_STATE.WALKING) {
			return (TextureRegion) animations.getWalking(facing).getKeyFrame(walkTimer);
		}
		assert (state==ACTOR_STATE.STANDING || state==ACTOR_STATE.WALKING) : System.out.printf("getSprite(): Cannot get a TextureRegion. Unknown state: %s", state);
		throw new RuntimeException("Cannot get a TextureRegion to state "+state.toString());
	}
}
