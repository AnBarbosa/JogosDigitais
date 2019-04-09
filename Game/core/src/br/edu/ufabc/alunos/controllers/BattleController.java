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
		switch(Controls.getComando(keycode)) {
			case UNKNOWN:
				return false;
			case UP:
				battle.optionUp();
				break;
			case DOWN:
				battle.optionDown();
				return true;
			case OK:
				battle.okPressed();
				return true;
			case CANCEL:
				battle.cancelPressed();
				return true;
		}
		return false;
	}
	
	
}
