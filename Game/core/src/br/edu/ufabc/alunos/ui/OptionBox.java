package br.edu.ufabc.alunos.ui;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import br.edu.ufabc.alunos.core.GameApplication;

public class OptionBox extends Table {

	// For tweaking.
	public float arrowSpace = 2f;
	
	private int selected;
	private List<Image> arrows = new ArrayList<Image>();
	private List<Label> options = new ArrayList<Label>();
	private Table uiContainer;
	
	public OptionBox(Skin skin) {
		super(skin);	
		this.setBackground("optionbox");
		uiContainer = new Table();
		this.add(uiContainer).pad(5f);
	}
	
	
	
	public void addOption(String option) {
		Label optionLabel = new Label(option, this.getSkin());
		options.add(optionLabel);
		Image arrow = new Image(this.getSkin(), "arrow");
		arrow.setVisible(false);
		arrow.setScaling(Scaling.none);
		arrows.add(arrow);
		
		uiContainer.add(arrow).expand().align(Align.left).space(arrowSpace);
		uiContainer.add(optionLabel).expand().align(Align.left).space(5f);
		uiContainer.row();
		
		calculateArrowsVisibility();
	}
	

	public void calculateArrowsVisibility() {
		for(int i = 0; i< arrows.size(); i++) {
			if(i == getSelected()) {
				arrows.get(i).setVisible(true);
			} else {
				arrows.get(i).setVisible(false);
			}
		}
	}

	public void moveDown( ) {
		if(options.size()>0) {
			selected++;
			selected = selected % options.size();
			calculateArrowsVisibility();
		}
	}
	
	public void moveUp() {
		selected--;
		if(selected < 0) {
			selected += options.size();
		}
		calculateArrowsVisibility();
	}
	
	public int getSelected() {
		return selected;
	}
	
	public void clearChoices() {
		uiContainer.clearChildren();
			arrows.clear();
			options.clear();
			selected = 0;
	}
}
