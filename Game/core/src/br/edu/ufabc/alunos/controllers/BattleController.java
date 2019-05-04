package br.edu.ufabc.alunos.controllers;

import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.battle.BattleField;
import br.edu.ufabc.alunos.model.battle.BattleField.BATTLE_STATE;
import br.edu.ufabc.alunos.screen.BattleScreen;

public class BattleController extends InputAdapter {
	private BattleField battle;
	private BattleScreen bs;

	
	private float screenChangeCountDown = 0.4f; // Seconds
	private boolean update = true;
	
	
	public BattleController(BattleField battle, BattleScreen bs) {
		this.battle = battle;
		this.bs = bs;
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
				BATTLE_STATE bs = battle.getBattleState();
				if(bs==BATTLE_STATE.PLAYER_WON || bs==BATTLE_STATE.PLAYER_LOSE) {
					screenChangeCountDown -= 3;
					System.out.printf("Acelerating end: %f", screenChangeCountDown);
				}
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
	
	public void update(float delta) {
		if(update && battle.isBattleFinished()) {
			screenChangeCountDown -= delta;
			if(screenChangeCountDown < 0) {
				update = false;
				switch(battle.getBattleState()) {
				case PLAYER_WON:
					bs.win();
					break;
				case PLAYER_LOSE:
					bs.lose();
					break;
				case PLAYER_RUN:
					bs.run();
					break;
				}
				
			}
		}
	}
	public void updateOld(float delta) {
		if(update) {
			if(battle.isBattleFinished())
			if(battle.getBattleState()==BATTLE_STATE.PLAYER_WON) {
				screenChangeCountDown -=delta;
				if (screenChangeCountDown <0) {
					bs.win();
					update = false;
				}
			} else if (battle.getBattleState()==BATTLE_STATE.PLAYER_LOSE) {
				screenChangeCountDown -=delta;
				if (screenChangeCountDown <0) {
					bs.lose();		
					update = false;
				}
			}
		}
	}
	
	
}
