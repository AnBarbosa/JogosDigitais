package br.edu.ufabc.alunos.battle.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.edu.ufabc.alunos.ui.battle.BattleField;

public class SerieOfActions extends BattleAction {
	private Stack<BattleAction> actions;
	private BattleAction currentAction;
	public SerieOfActions(BattleField bf, List<BattleAction> actions) {
		super(bf);
		this.actions = new Stack<BattleAction>();
		for (BattleAction ba : actions) {
			actions.add(ba);
		}
		
	}

	@Override
	public void doAction() {
		currentAction = actions.pop();
		currentAction.doAction();
	}
	
	@Override
	public boolean isFinished() {
		if(actions.isEmpty()) {
			if(currentAction == null) {
				return true;
			} else {
				return currentAction.isFinished();
			}
		} else {
			if(currentAction == null) {
				// There is no action. Lets start the first
				doAction();
			} else if (currentAction.isFinished()) {
				// The current action finished, lets start the next.
				doAction();
			}
			return false;
		}
	}

}
