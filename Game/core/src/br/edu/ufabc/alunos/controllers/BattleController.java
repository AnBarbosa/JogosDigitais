package br.edu.ufabc.alunos.controllers;

import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.ui.battle.BattleField;

public class BattleController extends InputAdapter {
	private BattleField battle;
	
	
	public BattleController(BattleField battle) {
		this.battle = battle;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean bool = false;
		
		switch(Controls.getComando(keycode)) {
			case UNKNOWN:
				bool = false;
				break;
			case UP:
				battle.optionUp();
				bool = true;
				break;
			case DOWN:
				battle.optionDown();
				bool = true;
				break;
			case OK:
				battle.okPressed();
				bool = true;
				break;
			case CANCEL:
				battle.cancelPressed();
				bool = true;
				break;
			default:
				bool = false;
				break;
		}
		return bool;
		
	}
	
	
}
