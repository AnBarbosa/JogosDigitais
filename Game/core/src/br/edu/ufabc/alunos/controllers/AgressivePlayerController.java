package br.edu.ufabc.alunos.controllers;

import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;
import br.edu.ufabc.alunos.model.map.DIRECTION;
import br.edu.ufabc.alunos.screen.DungeonScreen;

public class AgressivePlayerController extends PlayerControllerAdvanced{
	public final float ENEMY_CHANCE = 0.25f;
	public final float START_BOOST = -3.0f;
	public final float BOOST_RATE = 0; //.0075f;
	public final float MIN_INTERVAL = 0.75f;
	DungeonScreen ds;
	
	float boost_encounter_chances = 0;
	float lastSearch = 0f;
	
	private boolean searching;
	
	public AgressivePlayerController(Actor player, DungeonScreen ds) {
		super(player);
		this.ds = ds;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if(searching) {			
			this.boost_encounter_chances += delta;
			this.lastSearch += delta;
		}
	}
	
	protected void considerMove(DIRECTION dir) {
		super.considerMove(dir);
		if(searching) {
			if(lastSearch >= MIN_INTERVAL) {
				lastSearch = 0;
				searchForEnemies();
			}
		}
	}
	
	private void searchForEnemies() {
		float chance = ENEMY_CHANCE+(boost_encounter_chances*BOOST_RATE);
		boolean found = Math.random() <= chance;
		System.out.printf("Procurando inimigos com chance %f. Resultado: %b\n", chance, found);
		if(found) {
			releaseAllCommands();
			boost_encounter_chances = START_BOOST;
			int playerLevel = GameMaster.getPlayer().getLevel();
			int enemyLevel = Math.max(playerLevel-1, 1);
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.RANDOM, enemyLevel, false);
			ds.startBattle(enemy);
		}
	}
	
	public void setSearchingEnemies(boolean searching) {
		this.searching = searching;
		this.boost_encounter_chances = START_BOOST;
		this.lastSearch = 0f;

		
	}

}
