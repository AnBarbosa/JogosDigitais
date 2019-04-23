package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.controllers.DialogueController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.DullChara;
import br.edu.ufabc.alunos.model.dialog.DialogueNode;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.ui.DialogueBox;
import br.edu.ufabc.alunos.ui.OptionBox;
import br.edu.ufabc.alunos.ui.battle.BattleField;
import br.edu.ufabc.alunos.ui.battle.FixedSizeDialogue;

public class LoseScreen extends AbstractScreen {
	
	private SpriteBatch spriteBatch;
	private Texture loseMessage;

	public LoseScreen(GameApplication game) {
		super(game);
		spriteBatch = new SpriteBatch();
		

	}


	
	

	
	
	private void debugCommands() {
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			game.setScreen(new GameScreenWithUI(this.game));
		}

		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			game.setScreen(new BattleScreen(this.game));
		}
	}



	@Override
	public void render(float delta) {
		debugCommands();
		spriteBatch.begin();
		spriteBatch.draw(loseMessage, 0, 0, loseMessage.getWidth(), loseMessage.getHeight());
		spriteBatch.end();

	}

	@Override
	public void resize(int width, int height) {
		spriteBatch.getProjectionMatrix().setToOrtho2D(0,0, width, height);
	
	}





	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void dispose() {
		spriteBatch.dispose();
		
	}

}
