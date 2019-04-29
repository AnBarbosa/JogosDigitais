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
import br.edu.ufabc.alunos.model.battle.Character;
import br.edu.ufabc.alunos.ui.HPLabel;


public class CharaBox extends Table {

	private Character chara;
	private HPLabel hpLabel;
	private Image fighter;
	private VerticalGroup uiContainer;
	private int maxHP  = 100;
	private int currHP = 100;
	private boolean horizontalFlip;
	
	
	public CharaBox(Skin skin, boolean horizontalFlip ) {
		super(skin);
		this.setBackground("optionbox");
		uiContainer = new VerticalGroup();
		this.add(uiContainer).pad(5f);
		
		uiContainer.debugAll();
		hpLabel = new HPLabel(100, this.getSkin());
		
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("tutorial/graphics/pokemon/bulbasaur.png")));
		sprite.flip(horizontalFlip, false);
		fighter = new Image(sprite);
		uiContainer.addActor(hpLabel);
		uiContainer.addActor(fighter);
		this.updateHP();
	}
	
	public CharaBox(Skin skin, boolean horizontalFlip, Character character) {
		this(skin, horizontalFlip);
		this.chara = character;
		this.maxHP = character.getHp();
		this.currHP = character.getCurrent_hp();
		this.hpLabel.setMaxHP(this.maxHP);
		this.hpLabel.setCurrHP(this.currHP);
		this.hpLabel.setDisplayedHP(this.currHP);
		this.updateHP();
	}

	public CharaBox maxHP(int value) {
		this.maxHP = value;
		updateHP();
		return this;
	}
	public CharaBox currHP(int value) {
		this.currHP = value;
		this.hpLabel.setCurrHP(value);
		updateHP();
		return this;
	}
	
	public void heal(int value) {
		this.currHP(MathUtils.clamp(value, 0, maxHP)); 
	}
	
	public void updateHP() {
		if(maxHP == 0) {
			return;
		}
		float fracao = currHP/maxHP;
		hpLabel.setText("HP: "+currHP+"/"+maxHP);
	}

	public Character getCharacter() {
		return this.chara;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public void sync() {
		hpLabel.setCurrHP(chara.getCurrent_hp());
	}
	

}
