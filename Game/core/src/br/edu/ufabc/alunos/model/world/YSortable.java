package br.edu.ufabc.alunos.model.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface YSortable {

	public TextureRegion getSprite();
	public float getWorldX();
	public float getWorldY();
	public float getSizeX();
	public float getSizeY();
	public boolean isVisible();
}
