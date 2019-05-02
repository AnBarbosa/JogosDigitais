package br.edu.ufabc.alunos.model.inventory;

import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;

public class ItemBuilder {

	public static Item hpPotionSmall() {
		String nome = "Poção Pequena";
		String desc = "Recupera sua vida.";
		Action efeito = () -> {
			BattleCharacter player = GameMaster.getPlayer();
			int currHP = player.getHp();
			int maxHP = player.getHp();
			currHP = Math.min(currHP+10, maxHP);
			player.setCurrent_hp(currHP);		
		};
				
		return new Item(nome, desc, efeito);
	}
	
	public static Item hpPotionBig() {
		String nome = "Poção Grande";
		String desc = "Recupera sua vida.";
		Action efeito = () -> {
			BattleCharacter player = GameMaster.getPlayer();
			int currHP = player.getHp();
			int maxHP = player.getHp();
			currHP = Math.min(currHP+50, maxHP);
			player.setCurrent_hp(currHP);		
		};
				
		return new Item(nome, desc, efeito);
	}
	
	public static Item dragonClaw() {
		String nome = "Garra de Dragão";
		String desc = "Reúna 5 para vencer. Use para recuperar toda a vida e ganhar XP.";
		Action efeito = () -> {
			BattleCharacter player = GameMaster.getPlayer();
			player.setCurrent_hp(player.getHp());		
			player.evolve(10);
		};
				
		return new Item(nome, desc, efeito);
	}
}
