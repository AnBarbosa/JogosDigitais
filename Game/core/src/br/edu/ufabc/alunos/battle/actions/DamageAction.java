package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.ui.battle.BattleField;
import br.edu.ufabc.alunos.ui.battle.CharaBox;
import br.edu.ufabc.alunos.model.battle.Character;

public class DamageAction extends BattleAction {
	public enum DAMAGE { NORMAL, MAGIC };
	private DAMAGE type;
	private CharaBox target;
	private CharaBox damager;
	private TextAction action;
	
	public DamageAction(BattleField bf, CharaBox damager, CharaBox target, DAMAGE type) {
		super(bf);
		this.damager = damager;
		this.target = target;
		this.type = type;
	}

	@Override
	public void doAction() {
		Character hitter = damager.getCharacter();
		Character hitted = target.getCharacter();
		int prevHP = hitted.getCurrent_hp();
		if (type == DAMAGE.NORMAL){
			hitted.reciveDamege(hitter.damage());
		} else {
			hitted.reciveMagicalDamege(hitter.magicalDamage());			
		}
		int afterHP = hitted.getCurrent_hp();
		target.sync();
		String text = String.format("%s causou %d de dano à %s.", damager.getName(), prevHP-afterHP, target.getName());
		action = new TextAction(bf, text, bf.getMultiplexer());
		action.doAction();
	}

	@Override
	public boolean isFinished() {
		return action.isFinished();
	}

}
