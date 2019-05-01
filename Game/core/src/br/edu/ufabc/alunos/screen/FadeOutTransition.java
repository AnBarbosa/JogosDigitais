package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.alunos.core.GameApplication;

public class FadeOutTransition extends Transition {
	private Color color;
	private Texture white;
	
	public FadeOutTransition (float duration, Color color, AssetManager am) {
		super(duration, am);
		this.color = color;
		white = am.get("tutorial/graphics/statuseffect/white.png");
	}

	public FadeOutTransition(float duration, Color color) {
		this(duration, color, GameApplication.getAssetManager());
	}

	@Override
	public void render(float delta, SpriteBatch batch) {
		batch.begin();
		batch.setColor(color.r, color.g, color.b, this.getProgress());
		batch.draw(white,  0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	}

}
