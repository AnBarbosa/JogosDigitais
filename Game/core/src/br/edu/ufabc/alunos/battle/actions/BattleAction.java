package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.ui.battle.BattleField;

public abstract class BattleAction {

	protected BattleField bf;

	public BattleAction(BattleField bf) {
		super();
		this.bf = bf;
	}
	
	
	public abstract void startAction();
	public abstract boolean isFinished();
	
	
	
}
