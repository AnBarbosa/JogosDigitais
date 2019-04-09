package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.ui.battle.BattleField;

public class TextAction extends BattleAction {

	String text;
	
	public TextAction(BattleField bf, String text) {
		super(bf);
		this.text = text;
	}

	@Override
	public void doAction() {
		bf.displayText(text);
	}
	
	@Override
	public boolean isFinished() {
		return bf.displayFinished();
	}

}
