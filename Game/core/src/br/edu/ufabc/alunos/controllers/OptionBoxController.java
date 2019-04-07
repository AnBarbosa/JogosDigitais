package br.edu.ufabc.alunos.controllers;

import com.badlogic.gdx.Input.Keys;

import br.edu.ufabc.alunos.ui.OptionBox;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

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
