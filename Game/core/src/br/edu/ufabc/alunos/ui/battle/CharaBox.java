package br.edu.ufabc.alunos.ui.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

public class CharaBox extends Table {

	private Label hpLabel;
	//private HPBar bar;
	private Image fighter;
	private VerticalGroup uiContainer;
	
	public CharaBox(Skin skin) {
		super(skin);
		this.setBackground("optionbox");
		uiContainer = new VerticalGroup();
		this.add(uiContainer).pad(5f);
		
		uiContainer.debugAll();
		hpLabel = new Label("HP", this.getSkin());
		fighter = new Image(new Texture(Gdx.files.internal("tutorial/graphics/pokemon/bulbasaur.png")));
		
		uiContainer.addActor(hpLabel);
		uiContainer.addActor(fighter);
	}
	
}
