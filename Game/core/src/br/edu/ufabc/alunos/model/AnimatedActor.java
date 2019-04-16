package br.edu.ufabc.alunos.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import br.edu.ufabc.alunos.model.map.DIRECTION;
import br.edu.ufabc.alunos.model.map.TileMap;
import br.edu.ufabc.alunos.utils.AnimationSet;
/** 
 *  This class will animate the movement of the actor based on it ANIMATION_STATE and the fields variables.
 *  The current 'position' on the visual world is represented by the worldX and wolrdY(obtained by getWorldX() and getWorldY().
 *  The animation is divided on:
 *  1) Positional interpolation: To seamless move the actor on screen.
 *  2) Frames animation.
 *  
 *  The Positional Interpolation is updated every frame in which the actor is on ANIMATION_STATE.WALKING, and interpolates between
 *  (srcX, srcY) to (destX, destY), based on the positionalAnimationTimer and the ANIM_TOTAL_TIME.
 *  
 *  The Frames Animation are defined by the Animation Set and the frameAnimationTimer.
 *  
 */
public class AnimatedActor extends Actor{
	
	private static final float REFACE_TIME = 0.1f;
	private static final float ANIM_TOTAL_TIME = 0.25f;

	private float worldX, worldY;
	
	private int srcX, srcY;
	private int destX, destY;
	private float positinalAnimationTimer; // Control the interpolated displacement on screen.
	
	
	private DIRECTION facing;
	private float frameAnimationTimer; // Control which frame of the animation we wanna show.
	private boolean wasMoveRequestedThisFrame;
	
	protected AnimationSet animations;
	
	private ANIMATION_STATE animationState;
	
	public enum ANIMATION_STATE{
		WALKING, STANDING, REFACING;
	}

	public AnimatedActor(TileMap map, int x, int y, AnimationSet animations) {
		super(map, x, y);

		// For the animation
		this.worldX = x;
		this.worldY = y;
		this.animationState = ANIMATION_STATE.STANDING;
		
		this.facing = DIRECTION.EAST;
		this.animations = animations;
	}
	
	private void initializeMoveAnimation(DIRECTION d) {
		int dx = d.getX();
		int dy = d.getY();
		
		this.facing = d;

		positinalAnimationTimer = 0f;
		animationState = ANIMATION_STATE.WALKING;
		
		this.srcX = this.x;
		this.srcY = this.y;
		this.destX = this.x+dx;
		this.destY = this.y+dy;
	}
	
	private void finishMoveAnimation() {
		animationState = ANIMATION_STATE.STANDING;
		positinalAnimationTimer = 0f;
		
		this.worldX = destX;
		this.worldY = destY;
		this.srcX = 0;
		this.srcY = 0;
		this.destX = 0;
		this.destY = 0;
	}
	
	@Override
	public void update(float delta) {
	
		if(this.animationState==ANIMATION_STATE.WALKING) {
			
			positinalAnimationTimer += delta;
			frameAnimationTimer += delta;
			// Updating visual position of actor
			worldX = Interpolation.linear.apply(srcX, destX, positinalAnimationTimer/ANIM_TOTAL_TIME);
			worldY = Interpolation.linear.apply(srcY, destY, positinalAnimationTimer/ANIM_TOTAL_TIME);
			// If animTimer > ANIM_TIME then we already finished the animation.
			if(positinalAnimationTimer > ANIM_TOTAL_TIME) {
				// If we passed the ANIM_TIME, then we should correct the frameTimer.
				float leftOverTime = positinalAnimationTimer-ANIM_TOTAL_TIME;
				frameAnimationTimer -= leftOverTime;
				finishMoveAnimation();
				// If the player requested to move again, we move:
				if(wasMoveRequestedThisFrame) {
					if(move(facing)) {
						positinalAnimationTimer += leftOverTime;
						worldX = Interpolation.linear.apply(srcX, destX, positinalAnimationTimer/ANIM_TOTAL_TIME);
						worldY = Interpolation.linear.apply(srcY, destY, positinalAnimationTimer/ANIM_TOTAL_TIME);
					}
				} else {
					// If we won't move again, we return to frame 0
					frameAnimationTimer = 0f;
				}
			}
		}
		 
		
		if(this.animationState==ANIMATION_STATE.REFACING) {
			positinalAnimationTimer += delta;
			if(positinalAnimationTimer > REFACE_TIME) {
				this.animationState = ANIMATION_STATE.STANDING;
			}
		}

		// Reset
		wasMoveRequestedThisFrame = false;
	}
	
	@Override
	public boolean move(DIRECTION directionTo) {
		
		if (animationState == ANIMATION_STATE.WALKING) {
			if(facing == directionTo) {
				wasMoveRequestedThisFrame = true; 
			}
			return false;
		}
		
		int nextX = this.getX() + directionTo.getX();
		int nextY = this.getY() + directionTo.getY();
		if(map.isPassable(nextX, nextY)) {
			this.initializeMoveAnimation(directionTo);
			this.setPosition(nextX,  nextY);
			return true;
		} else {
			reface(DIRECTION.getOpposite(directionTo));
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
		if(animationState==ANIMATION_STATE.STANDING) {
			return animations.getStanding(facing);
		}
		if(animationState==ANIMATION_STATE.WALKING) {
			return (TextureRegion) animations.getWalking(facing).getKeyFrame(frameAnimationTimer);
		}
		if(animationState==ANIMATION_STATE.REFACING) {
			return (TextureRegion) animations.getWalking(facing).getKeyFrame(0);
		}
		assert (animationState!= null) : System.out.printf("getSprite(): Cannot get a TextureRegion. Unknown state: %s", animationState);
		throw new RuntimeException("Cannot get a TextureRegion to state "+animationState.toString());
	}

	public void reface(DIRECTION dir) {
		if(animationState == ANIMATION_STATE.STANDING) {
			if(facing != dir) {
				animationState = ANIMATION_STATE.REFACING;
				facing = dir;
				positinalAnimationTimer = 0;
			}
		}
	}
	
	@Override
	public float getSizeX() { return 1;}
	@Override
	public float getSizeY() { return 1;}
	
}
