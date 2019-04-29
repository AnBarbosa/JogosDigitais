package br.edu.ufabc.alunos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.alunos.controllers.Controls;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.ui.battle.BattleField.BATTLE_STATE;

public class LoseScreen extends AbstractScreen implements InputProcessor{
	
	private SpriteBatch spriteBatch;
	private Texture loseImage;

	public LoseScreen(GameApplication game) {
		super(game);
		spriteBatch = new SpriteBatch();
		loseImage = GameApplication.getAssetManager().get("Screens/LoseScreen.png");
		this.addInputProcessor(this);
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
		spriteBatch.draw(loseImage, 0, 0, loseImage.getWidth(), loseImage.getHeight());
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








	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(Controls.getComando(keycode)) {
			case OK:
				System.out.println("Ok pressed");
				game.startTransition(this, new GameScreenWithUI(game), 
						GameMaster.getFadeOut(), 
						GameMaster.getFadeIn(), 
						null);
				//game.setScreen(new GameScreenWithUI(game));
				return true;
			case CANCEL:
				System.out.println("Cancel pressed");
				Gdx.app.exit();
				System.exit(0);
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
