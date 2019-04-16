package br.edu.ufabc.alunos.ui.battle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.edu.ufabc.alunos.battle.actions.BattleAction;
import br.edu.ufabc.alunos.battle.actions.DamageAction;
import br.edu.ufabc.alunos.battle.actions.DamageAction.DAMAGE;
import br.edu.ufabc.alunos.battle.actions.SerieOfActions;
import br.edu.ufabc.alunos.battle.actions.TextAction;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.Character;
import br.edu.ufabc.alunos.model.battle.DullChara;
import br.edu.ufabc.alunos.ui.OptionBox;


public class BattleField extends Table {
	
	private InputMultiplexer multiplexer;
	CharaBox playerCharaBox, enemyCharaBox;
	FixedSizeDialogue description;
	OptionBox options;
	
	Character playerChar;
	Character enemyChar;
	
	String[] optionLabels = { "Ataque Físico", "Ataque Místico", "Fugir"};
	public enum BATTLE_OPTION { ATAQUE_FISICO , ATAQUE_MAGICO , FUGIR }; 	
	public final int ATAQUE_FISICO = 0;
	public final int ATAQUE_MAGICO = 1;
	public final int FUGIR = 2;
	
	public enum ACTION_STATE { CHOSE_ACTION, WAIT_ENEMY, ACTING};
	public enum BATTLE_STATE { RUNNING, PLAYER_WON, PLAYER_LOSE};
	
	private ACTION_STATE actionState;
	private BATTLE_STATE battleState;

	public boolean playerTurn; 
	
	
	private BattleAction currentAction = null;
	private boolean playerWon;	
	
	public BattleField(Skin skin, InputMultiplexer multiplexer) {
		this.setMultiplexer(multiplexer);
		int largura = 200;
		int pad = 20;
		actionState = ACTION_STATE.CHOSE_ACTION;
		battleState = BATTLE_STATE.RUNNING;
		playerChar = GameMaster.getPlayer();
		enemyChar = new DullChara(35, 5, 10, "Mistic Enemy");
		playerCharaBox = new CharaBox(skin, true, playerChar);
		enemyCharaBox  = new CharaBox(skin, false, enemyChar);
		
		playerCharaBox.setName(playerChar.getName());
		enemyCharaBox.setName(enemyCharaBox.getName());
		
		
		description = new FixedSizeDialogue(skin, largura,100);
		options = new OptionBox(skin);
		int i = 0;
		for (String option : optionLabels) {
			options.addOption(option);
		}
		setTableLayout(pad);
		chooseFirst();
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
		//description.act(delta);		
		if(battleState == BATTLE_STATE.RUNNING) {
			switch(actionState) {
				case CHOSE_ACTION:
					if(!options.getEnabled())
						options.setEnabled(true);
					break;
				case WAIT_ENEMY:
					if(options.getEnabled()) {
						options.setEnabled(false);
					}
					if(currentAction == null) {
						currentAction =  new TextAction(this, "O Inimigo te ataca.", null);
						currentAction.doAction();
						actionState = ACTION_STATE.ACTING;
					} else {
						if (currentAction.isFinished()) {
							actionState = ACTION_STATE.CHOSE_ACTION;
						}
					}
					break;
				case ACTING:
					if(options.getEnabled()) {
						options.setEnabled(false);
					}
					if(currentAction == null || currentAction.isFinished()) {
						playerTurn = !playerTurn;
						actionState = getNextState(playerTurn);
						currentAction = null;
					} 
					break;
			}
			if(playerChar.getCurrent_hp() <= 0) {
				battleState = BATTLE_STATE.PLAYER_LOSE;
				actionState = ACTION_STATE.ACTING;
				currentAction = new TextAction(this, "Você perdeu.", this.multiplexer);
				currentAction.doAction();
			} 
			if (enemyChar.getCurrent_hp() <= 0) {
				battleState = BATTLE_STATE.PLAYER_WON;
				actionState = ACTION_STATE.ACTING;
				currentAction = new TextAction(this, "Você venceu.", this.multiplexer);
				currentAction.doAction();
			}
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
				printOnScreen("Teste");
				BattleAction textAction = new TextAction(this, "Você ataca com sua espada", this.getMultiplexer());
				BattleAction damageAction = new DamageAction(this, playerCharaBox, enemyCharaBox, DAMAGE.NORMAL);
				currentAction = new SerieOfActions(this, textAction, damageAction);
				break;
			case ATAQUE_MAGICO:
				System.out.println("Você ataca com magia negra.");
				BattleAction textMagicAction = new TextAction(this, "Você ataca com magia negra", this.getMultiplexer());
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
		currentAction.doAction();
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
	
	
}
