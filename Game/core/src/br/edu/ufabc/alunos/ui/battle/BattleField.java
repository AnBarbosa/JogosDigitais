package br.edu.ufabc.alunos.ui.battle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.edu.ufabc.alunos.battle.actions.BattleAction;
import br.edu.ufabc.alunos.battle.actions.SerieOfActions;
import br.edu.ufabc.alunos.battle.actions.TextAction;
import br.edu.ufabc.alunos.ui.OptionBox;

public class BattleField extends Table {
	CharaBox player, enemy;
	FixedSizeDialogue description;
	OptionBox options;
	
	String[] optionLabels = { "Ataque Físico", "Ataque Místico", "Fugir"};
	public enum BATTLE_OPTION { ATAQUE_FISICO , ATAQUE_MAGICO , FUGIR }; 
	public final int ATAQUE_FISICO = 0;
	public final int ATAQUE_MAGICO = 1;
	public final int FUGIR = 2;
	
	private BATTLE_STATE state;
	private BATTLE_STATE nextState;
	public enum BATTLE_STATE { CHOSE_ACTION, WAIT_ENEMY, WIN, LOSE, DISPLAYING_TEXT, ANIMATING};
	public boolean playerTurn; 
	
	
	private BattleAction currentAction = null;	
	
	public BattleField(Skin skin) {
		int largura = 200;
		int pad = 20;
		state = BATTLE_STATE.CHOSE_ACTION;
		player = new CharaBox(skin, true);
		enemy  = new CharaBox(skin, false);
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
		if (d100 > 50) {
			state = BATTLE_STATE.WAIT_ENEMY;
			playerTurn = false;
		} else {
			state = BATTLE_STATE.CHOSE_ACTION;
			playerTurn = true;
			
		}
	}
	
	@Override
	public void act(float delta) {
		switch(state) {
			case CHOSE_ACTION:
				break;
			case WAIT_ENEMY:
				if(currentAction == null) {
					currentAction = new TextAction(this, "O Inimigo te ataca.");
					currentAction.doAction();
				} else {
					if (currentAction.isFinished()) {
						state = BATTLE_STATE.CHOSE_ACTION;
					}
				}
				break;
			case DISPLAYING_TEXT:
				if(description.isFinished())
					state = nextState;
				break;
			case ANIMATING:
				break;
			case WIN:
				break;
			case LOSE:
				break;
		}
	}
	private void setTableLayout(int space) {
		Table tabela = new Table(getSkin());
		tabela.add(player).align(Align.topLeft);
		tabela.add(enemy).align(Align.topRight).padBottom(space);
		tabela.row();
		tabela.add(options).align(Align.center).padRight(space);
		tabela.add(description).expand();
		
		this.add(tabela);
	}
	

	
	public void optionUp() {
		options.moveUp();
	}
	public void optionDown() {
		options.moveDown();
	}


	public void okPressed() {
		
		switch(options.getSelected()) {
		case ATAQUE_FISICO:
			currentAction = new TextAction(this, "Você ataca com sua espada");
			break;
		case ATAQUE_MAGICO:
			currentAction = new TextAction(this, "Você ataca com magia negra");
			break;
		case FUGIR:
			BattleAction tentativa = new TextAction(this, "Você tenta fugir...");
			String runResult = canRun(0.5d)? "Você consegue." : "Você falhou.";
			BattleAction resultado = new TextAction(this, runResult);
			List<BattleAction> acoes = new ArrayList<BattleAction>();
			acoes.add(tentativa);
			acoes.add(resultado);
			currentAction = new SerieOfActions(this, acoes);
			break;
		}
		if(currentAction != null && state==BATTLE_STATE.CHOSE_ACTION) {
			currentAction.doAction();
		}
		
	}
	
	public boolean canRun(double reference) {
		return (Math.random() <= reference);
			
	}


	public void cancelPressed() {
		System.out.println("Cancelado");
		
	}
	
	public void displayText(String text) {
		this.description.animateText(text);
		this.state = BATTLE_STATE.DISPLAYING_TEXT;
	}

	public boolean displayFinished() {
		return this.description.isFinished();
		
	}
	
}
