package br.edu.ufabc.alunos.ui.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class CharaBox extends Table {

	private Label hpLabel;
	//private HPBar bar;
	private Image fighter;
	private VerticalGroup uiContainer;
	private int maxHP;
	private int currHP;
	private boolean horizontalFlip;
	public CharaBox(Skin skin, boolean horizontalFlip ) {
		super(skin);
		this.setBackground("optionbox");
		uiContainer = new VerticalGroup();
		this.add(uiContainer).pad(5f);
		
		uiContainer.debugAll();
		hpLabel = new Label("HP", this.getSkin());
		
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("tutorial/graphics/pokemon/bulbasaur.png")));
		sprite.flip(horizontalFlip, false);
		fighter = new Image(sprite);
		
		
		
		
		uiContainer.addActor(hpLabel);
		uiContainer.addActor(fighter);
	}
	
	public CharaBox maxHP(int value) {
		this.maxHP = value;
		updateHP();
		return this;
	}
	public CharaBox currHP(int value) {
		this.currHP = value;
		updateHP();
		return this;
	}
	
	public void heal(int value) {
		this.currHP(MathUtils.clamp(value, 0, maxHP)); 
	}
	
	public void updateHP() {
		float fracao = currHP/maxHP;
		hpLabel.setText("HP: "+currHP+"/"+maxHP);
	}
	

}
