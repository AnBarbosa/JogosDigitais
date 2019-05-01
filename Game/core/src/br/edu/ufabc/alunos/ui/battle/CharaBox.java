package br.edu.ufabc.alunos.ui.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.ui.HPLabel;


public class CharaBox extends Table {

	private BattleCharacter chara;
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
	
	public void setFighterImage(String texturePath, boolean flip) {
		if(GameMaster.getAssetManager().contains(texturePath)) {			
			Texture texture = GameMaster.getAssetManager().get(texturePath);
			assert(texture != null);
			Sprite sprite = new Sprite(texture);
			sprite.flip(horizontalFlip, flip);
			
			// Preserve actions and events on this Image.
			Array<Action> actions = fighter.getActions();
			DelayedRemovalArray<EventListener> listeners = fighter.getListeners();
			
			// Remove old actor
			uiContainer.removeActor(fighter);
			
			// Add new actor
			fighter = new Image(sprite);

			uiContainer.addActor(fighter);
			// Re add actions and listeners.
			for(Action a : actions) {
				fighter.addAction(a);
			}
			for(EventListener el : listeners) {
				fighter.addListener(el);
			}
			
			
		} else {
			System.out.println("Não foi possível encontrar a imagem: "+texturePath);
		}

	}
	public CharaBox(Skin skin, boolean horizontalFlip, BattleCharacter character) {
		this(skin, horizontalFlip);
		this.chara = character;
		this.maxHP = character.getHp();
		this.currHP = character.getCurrent_hp();
		this.hpLabel.setMaxHP(this.maxHP);
		this.hpLabel.setCurrHP(this.currHP);
		this.hpLabel.setDisplayedHP(this.currHP);
		this.updateHP();
		
		if(this.chara.getTexturePath() != "") {
			this.setFighterImage(this.chara.getTexturePath(), false);
		}
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

	public BattleCharacter getCharacter() {
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
