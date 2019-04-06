package br.edu.ufabc.alunos.controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.ui.OptionBox;

public class OptionBoxController extends InputAdapter {

	private OptionBox box;

	public OptionBoxController(OptionBox box) {
		super();
		this.box = box;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode == Keys.K){
			System.out.println("Keys.K");
			box.moveUp();
			
			return true;
		} else if (keycode == Keys.J) {
			box.moveDown();
			return true;
		}
		return false;
	}


	
}
