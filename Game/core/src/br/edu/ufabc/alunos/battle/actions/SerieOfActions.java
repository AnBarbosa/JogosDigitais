package br.edu.ufabc.alunos.battle.actions;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.utils.Queue;

import br.edu.ufabc.alunos.ui.battle.BattleField;

public class SerieOfActions extends BattleAction {
	private Queue<BattleAction> actions;
	private BattleAction currentAction;
	public SerieOfActions(BattleField bf, List<BattleAction> actions) {
		super(bf);
		this.actions = new Queue<BattleAction>();
		for (BattleAction ba : actions) {
			this.actions.addLast(ba);
		}
		
	}
	
	public SerieOfActions(BattleField bf, BattleAction...actions) {
		super(bf);
		this.actions = new Queue<BattleAction>();
		for (BattleAction action: actions) {
			this.actions.addLast(action);
		}
	}

	@Override
	public void doAction() {
		currentAction = actions.removeFirst();
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
