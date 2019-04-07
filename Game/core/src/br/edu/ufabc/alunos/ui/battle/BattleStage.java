package br.edu.ufabc.alunos.ui.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.edu.ufabc.alunos.ui.OptionBox;

public class BattleStage extends Table {
	CharaBox player, enemy;
	FixedSizeDialogue description;
	OptionBox options;
	String[] optionLabels = { "Ataque Físico", "Ataque Místico", "Fugir"};

	public BattleStage(Skin skin) {
		int largura = 200;
		int pad = 20;
		
		player = new CharaBox(skin);
		enemy  = new CharaBox(skin);
		description = new FixedSizeDialogue(skin, largura,100);
		options = new OptionBox(skin);
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
		System.out.println("Solicitada opção:" + optionLabels[options.getSelected()]);
		
	}


	public void cancelPressed() {
		// TODO Auto-generated method stub
		
	}
	
}
