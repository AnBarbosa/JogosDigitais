package br.edu.ufabc.alunos.ui.battle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

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
	public enum BATTLE_STATE { CHOSE_ACTION, WAIT_ENEMY, WIN, LOSE, DISPLAYING_TEXT, ANIMATING};

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
		if(state==BATTLE_STATE.CHOSE_ACTION) {
			switch(options.getSelected()) {
			case ATAQUE_MAGICO:
				printOnScreen("Você ataca com magia negra!");
				break;
			case ATAQUE_FISICO:
				printOnScreen("Meteoro de Pegasus!");
				break;
			case FUGIR:
				printOnScreen("Covarde.");
				break;
			default:
				System.out.println("OPÇÃO INVÁLIDA.");
				printOnScreen("Opção Inválida. Desculpe, mas eu não esperava que você fosse capaz de fazer isso.");
			}
		} else {
			System.out.println("Wait your turn.");
		}
		
	}


	public void cancelPressed() {
		System.out.println("Cancelado");
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(state == BATTLE_STATE.DISPLAYING_TEXT) {
			if(description.isFinished()) {
				state = BATTLE_STATE.CHOSE_ACTION;
			}
	}
		
	}
	
	public void printOnScreen(String text) {
		description.animateText(text);
		state = BATTLE_STATE.DISPLAYING_TEXT;
	}
	
	 
	
}
