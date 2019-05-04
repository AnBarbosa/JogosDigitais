package br.edu.ufabc.alunos.battle.actions;

import br.edu.ufabc.alunos.ui.battle.CharaBox;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.BattleField;
import br.edu.ufabc.alunos.model.battle.enums.DAMAGE;

public class DamageAction extends BattleAction {
	private DAMAGE type;
	private CharaBox target;
	private CharaBox attacker;
	private TextAction action;
	
	public DamageAction(BattleField bf, CharaBox damager, CharaBox target, DAMAGE type) {
		super(bf);
		this.attacker = damager;
		this.target = target;
		this.type = type;
	}

	@Override
	public void startAction() {
		BattleCharacter hitter = attacker.getCharacter();
		BattleCharacter hitted = target.getCharacter();
		int prevHP = hitted.getCurrent_hp();
		if (type == DAMAGE.NORMAL){
			hitted.reciveDamege(hitter.damage());
		} else {
			hitted.reciveMagicalDamege(hitter.magicalDamage());			
		}
		GameMaster.getGameApplication().playSound(hitter.getSound(type));
		int afterHP = hitted.getCurrent_hp();
		target.sync();
		String text = String.format("%s causou %d de dano à %s.", attacker.getName(), prevHP-afterHP, target.getName());
		action = new TextAction(bf, text, bf.getMultiplexer());
		action.startAction();
	}

	@Override
	public boolean isFinished() {
		return action.isFinished();
	}

}
