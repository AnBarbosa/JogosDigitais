package br.edu.ufabc.alunos.ui.battle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.edu.ufabc.alunos.battle.actions.BattleAction;
import br.edu.ufabc.alunos.battle.actions.DamageAction;
import br.edu.ufabc.alunos.battle.actions.SerieOfActions;
import br.edu.ufabc.alunos.battle.actions.TextAction;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.achievements.AchievementManager;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.enums.DAMAGE;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;
import br.edu.ufabc.alunos.ui.OptionBox;


public class BattleField extends Table {
	public enum ACTION_STATE { CHOSE_ACTION, WAIT_ENEMY, ACTING};
	public enum BATTLE_STATE { RUNNING, PLAYER_WON, PLAYER_LOSE};

	// State
	private boolean battleFinished = false;	
	private ACTION_STATE actionState;
	private BATTLE_STATE battleState;

	
	// Acoes
	public final int ATAQUE_FISICO = 0;
	public final int ATAQUE_MAGICO = 1;
	public final int FUGIR = 2;
	
	private final String[] optionLabels = { "Ataque Físico", "Ataque Místico", "Fugir"}; 	
	
	private InputMultiplexer multiplexer;
	
	// View
	private CharaBox playerCharaBox, enemyCharaBox;
	private FixedSizeDialogue description;
	private OptionBox options;
	
	private BattleCharacter playerChar;
	private BattleCharacter enemyChar;
	
	// Logic
	private BattleAction currentAction = null;
	public boolean playerTurn; 
	
	
	public BattleField(Skin skin, InputMultiplexer multiplexer, BattleCharacter player, BattleCharacter enemy) {
		this.setMultiplexer(multiplexer);
		int largura = 200;
		int pad = 20;
		actionState = ACTION_STATE.CHOSE_ACTION;
		battleState = BATTLE_STATE.RUNNING;
		playerChar = player;
		enemyChar = enemy;
		playerCharaBox = new CharaBox(skin, true, playerChar);
		enemyCharaBox  = new CharaBox(skin, false, enemyChar);
		
		playerCharaBox.setName(playerChar.getName());
		enemyCharaBox.setName(enemyChar.getName());
		
		
		description = new FixedSizeDialogue(skin, largura,100);
		options = new OptionBox(skin);
		
		for (String option : optionLabels) {
			options.addOption(option);
		}
		setTableLayout(pad);
		chooseFirst();
	}
	
	public BattleField(Skin skin, InputMultiplexer multiplexer) {
		this.setMultiplexer(multiplexer);
		int largura = 200;
		int pad = 20;
		actionState = ACTION_STATE.CHOSE_ACTION;
		battleState = BATTLE_STATE.RUNNING;
		playerChar = GameMaster.getPlayer();
		enemyChar = CharacterCreator.getEnemy(Enemy.RANDOM, "Um Inimigo", 1, false);
		playerCharaBox = new CharaBox(skin, true, playerChar);
		enemyCharaBox  = new CharaBox(skin, false, enemyChar);
		
		playerCharaBox.setName(playerChar.getName());
		enemyCharaBox.setName(enemyChar.getName());
		
		
		description = new FixedSizeDialogue(skin, largura,100);
		options = new OptionBox(skin);
		
		for (String option : optionLabels) {
			options.addOption(option);
		}
		setTableLayout(pad);
		chooseFirst();
	}

	private void setTableLayout(int space) {
		Table tabela = new Table(getSkin());
		tabela.add(playerCharaBox).align(Align.topLeft);
		tabela.add(enemyCharaBox).align(Align.topRight).padBottom(space);
		tabela.row();
		tabela.add(options).align(Align.center).padRight(space);
		tabela.add(description).expand();
		tabela.addActor(description);
		tabela.addActor(options);
		tabela.addActor(playerCharaBox);
		tabela.addActor(enemyCharaBox);
		
		this.add(tabela);
	}
	

	
	
	
	
	private void chooseFirst() {
		double d100 = (1-Math.random())*100;
		d100 = 20;
		if (d100 > 50) {
			actionState = ACTION_STATE.WAIT_ENEMY;
			playerTurn = false;
		} else {
			actionState = ACTION_STATE.CHOSE_ACTION;
			playerTurn = true;
			
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);

		switch(battleState){
			case RUNNING:
				switch(actionState) {
					case CHOSE_ACTION:
						if(!options.getEnabled())
							options.setEnabled(true);
						break;
					case WAIT_ENEMY:
						if(options.getEnabled()) {
							options.setEnabled(false);
						}
						// Enemy will act if it's not acting already.
						if(currentAction == null) {
							DAMAGE attack = enemyChar.getNextAttack();
							String text = enemyChar.getAttackText(attack);
							BattleAction textAction = new TextAction(this, text, this.getMultiplexer());
							BattleAction damageAction = new DamageAction(this, enemyCharaBox, playerCharaBox, attack);
							currentAction = new SerieOfActions(this, textAction, damageAction);
							currentAction.startAction();
							actionState = ACTION_STATE.ACTING;
						} else {
							assert (false) : "Nunca deveriamos chegar aqui.";
							if (currentAction.isFinished()) {
								actionState = ACTION_STATE.CHOSE_ACTION;
							}
						}
						break;
					case ACTING:
						if(options.getEnabled()) {
							options.setEnabled(false);
						}
						// Se a ação terminou, trocamos o turno do jogador atual.
						if(currentAction == null || currentAction.isFinished()) {
							currentAction = null;
							playerTurn = !playerTurn;
							actionState = getNextState(playerTurn);
						} 
						break;
				}
				
				if(playerChar.getCurrent_hp() <= 0) {
					battleState = BATTLE_STATE.PLAYER_LOSE;
					actionState = ACTION_STATE.ACTING; 
	
					currentAction = new TextAction(this, "Você perdeu.", this.multiplexer);
					currentAction.startAction();
	
				} 
				if (enemyChar.getCurrent_hp() <= 0) {
					battleState = BATTLE_STATE.PLAYER_WON;
					actionState = ACTION_STATE.ACTING; 
					AchievementManager.addKill(enemyChar.getType());
					int xp = enemyChar.getExp();
					playerChar.evolve(xp);
					int level = playerChar.getLevel();
					BattleAction text  = new TextAction(this, "Você venceu.", this.multiplexer);
					BattleAction text2 = new TextAction(this, "Você ganhou "+xp+" pontos de experiência.\nSeu nível agora é "+level, this.multiplexer);
					 
					currentAction = new SerieOfActions(this, text,text2);					
					currentAction.startAction();
				}
				
				break;
				
			case PLAYER_LOSE:
			case PLAYER_WON:
				if(currentAction.isFinished()) {
					battleFinished = true;
				} 
				break;

			default:
				break;
			
		}
	}
	private ACTION_STATE getNextState(boolean isPlayerTurn) {
		if (battleState == BATTLE_STATE.PLAYER_WON || battleState == BATTLE_STATE.PLAYER_LOSE)
			return ACTION_STATE.ACTING;
		if(isPlayerTurn) {
			return ACTION_STATE.CHOSE_ACTION; 
		} else {
			return ACTION_STATE.WAIT_ENEMY;
		}
		
	}

	public void optionUp() {
		options.moveUp();
	}
	public void optionDown() {
		options.moveDown();
	}


	public void okPressed() {
		System.out.println("OK PRESSED.");
		if(actionState != ACTION_STATE.CHOSE_ACTION) {
			return;
		}
		switch(options.getSelected()) {
			case ATAQUE_FISICO:
				System.out.println("Você ataca com sua espada.");
				// TODO: Pegar o texto do ataque do personagem do jogador.
				String text = playerChar.getNormalAttackText();
				BattleAction textAction = new TextAction(this, text, this.getMultiplexer());
				BattleAction damageAction = new DamageAction(this, playerCharaBox, enemyCharaBox, DAMAGE.NORMAL);
				currentAction = new SerieOfActions(this, textAction, damageAction);
				break;
				
			case ATAQUE_MAGICO:
				System.out.println("Você ataca com magia negra.");
				// TODO: Pegar o texto do ataque do personagem do jogador.
				String textM = playerChar.getMagicAttackText();
				BattleAction textMagicAction = new TextAction(this, textM, this.getMultiplexer());
				BattleAction damageMagicAction = new DamageAction(this, playerCharaBox, enemyCharaBox, DAMAGE.MAGIC);
				currentAction = new SerieOfActions(this, textMagicAction, damageMagicAction);
				break;
				
			case FUGIR:
				System.out.println("Você tenta fugir.");
				BattleAction tentativa = new TextAction(this, "Você tenta fugir...", this.getMultiplexer());
				String runResult = canRun(0.5d)? "Você consegue." : "Você falhou.";
				System.out.println(runResult);
				BattleAction resultado = new TextAction(this, runResult, this.getMultiplexer());
				List<BattleAction> acoes = new ArrayList<BattleAction>();
				acoes.add(tentativa);
				acoes.add(resultado);
				currentAction = new SerieOfActions(this, acoes);
				break;
				
			default:
				assert(options.getSelected() > 0);
				System.out.println("Opção desconhecida: "+ options.getSelected());
				
		}
		System.out.println("Do Action: " + currentAction);
		currentAction.startAction();
		actionState = ACTION_STATE.ACTING;

		
	}
	
	public boolean canRun(double reference) {
		return (Math.random() <= reference);
			
	}


	public void cancelPressed() {
		System.out.println("Cancelado");
		
	}
	

	
	public void printOnScreen(String text) {
		description.animateText(text);
		actionState = ACTION_STATE.ACTING;
	}

	public void displayText(String text) {
		this.description.animateText(text);
		
	}

	public boolean displayFinished() {
		return this.description.isFinished();
	}

	public InputMultiplexer getMultiplexer() {
		return multiplexer;
	}

	public void setMultiplexer(InputMultiplexer multiplexer) {
		this.multiplexer = multiplexer;
	}
	
	public BATTLE_STATE getBattleState() {
		return battleState;
	}
	
	public boolean isBattleFinished() {
		return battleFinished;
	}
}
