package br.edu.ufabc.alunos.utils;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.edu.ufabc.alunos.model.map.DIRECTION;

public class AnimationSet {
	
	private Map<DIRECTION, Animation> animations;
	private Map<DIRECTION, TextureRegion> standings; 
	
	public AnimationSet(Animation[] animations, TextureRegion[] standings) {
		super();
		this.animations = new HashMap<>();
		this.standings = new HashMap<>();
		for (DIRECTION d: DIRECTION.values()) {
			this.animations.put(d, animations[d.ordinal()]);
			this.standings.put(d, standings[d.ordinal()]);
		}
	}

	public Animation getWalking(DIRECTION d) {
		return animations.get(d);
	}
	
	public TextureRegion getStanding(DIRECTION d) {
		return standings.get(d);
	}

}
