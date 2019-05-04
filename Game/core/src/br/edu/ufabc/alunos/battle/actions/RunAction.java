package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.model.battle.BattleField;
import br.edu.ufabc.alunos.model.battle.BattleField.BATTLE_STATE;

public class RunAction extends BattleAction {
	
	public RunAction(BattleField bf) {
		super(bf);
		
	}

	@Override
	public void startAction() {
		bf.setBattleFinished(true);
		bf.setBattleState(BATTLE_STATE.PLAYER_RUN);
	}

	@Override
	public boolean isFinished() {
		return bf.isBattleFinished();
	}

}
