package br.edu.ufabc.alunos.model;

import com.badlogic.gdx.math.Interpolation;

public class AnimatedActor extends Actor{
	
	private float worldX, worldY;
	
	private int srcX, srcY;
	private int destX, destY;
	private float animTimer;
	private final float ANIM_TIME = 0.5f;
	
	
	private ACTOR_STATE state;
	
	public enum ACTOR_STATE{
		WALKING, STANDING;
	}

	public AnimatedActor(TileMap map, int x, int y) {
		super(map, x, y);

		// For the animation
		this.worldX = x;
		this.worldY = y;
		this.state = ACTOR_STATE.STANDING;
	}
	
	private void initializeMove(int oldX, int oldY, int dx, int dy) {
		this.srcX = oldX;
		this.srcY = oldY;
		this.destX = oldX+dx;
		this.destY = oldY+dy;
		animTimer = 0f;
		state = ACTOR_STATE.WALKING;
	}
	
	private void finishMove() {
		state = ACTOR_STATE.STANDING;
		animTimer = 0f;
	}
	
	@Override
	public void update(float delta) {
		if(state==ACTOR_STATE.WALKING) {
			animTimer += delta;
			worldX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
			worldY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
			if(animTimer > ANIM_TIME) {
				finishMove();
			}
		}
	}
	
	@Override
	public boolean move(int dx, int dy) {
		if (state == ACTOR_STATE.WALKING) {
			// If we are already moving, we don't want to move until we finish moving.
			return false;
			
		}
		
		int nextX = this.getX() + dx;
		int nextY = this.getY() + dy;
		if(map.isPassable(nextX, nextY)) {
			this.initializeMove(this.getX(), this.getY(), dx, dy);
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
}
