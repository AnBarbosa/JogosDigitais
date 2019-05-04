package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.battle.BattleField;


public abstract class BattleAction implements Action{

	protected BattleField bf;

	public BattleAction(BattleField bf) {
		super();
		this.bf = bf;
	}
	
	
	public abstract void startAction();
	public abstract boolean isFinished();
	
	
	
}
