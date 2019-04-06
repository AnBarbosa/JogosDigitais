package br.edu.ufabc.alunos.model.ui;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class OptionBox extends Table {

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
		arrows.add(arrow);
		
		uiContainer.add(arrow).expand().align(Align.left).space(5f);
		uiContainer.add(optionLabel).expand().align(Align.left).space(5f);
		uiContainer.row();
		
		calcArrowVisibility();
	}
	
	private void calcArrowVisibility() {
		for(int i = 0; i< arrows.size(); i++) {
			if(i == getSelected()) {
				arrows.get(i).setVisible(true);
			} else {
				arrows.get(i).setVisible(false);
			}
		}
	}

	public void moveUp() {
		selected++;
		selected = selected % options.size();
	}
	
	public void moveDown() {
		selected--;
		if(selected < 0) {
			selected += options.size();
	}		
	}
	
	public int getSelected() {
		return selected;
	}
	
	public void clearChoices() {
		
	}
}
