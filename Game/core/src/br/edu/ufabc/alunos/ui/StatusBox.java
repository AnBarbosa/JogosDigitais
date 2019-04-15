package br.edu.ufabc.alunos.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StatusBox extends Table {

	private String text;
	
	public void setText(String name) {
		this.text = name;
	}

	public String getText() {
		return this.text;
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public HPLabel getHPBar() {
		// TODO Auto-generated method stub
		return null;
	}
}
