package br.edu.ufabc.alunos.screen;

import java.util.Map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.model.Action;

public class TransitionScreen extends AbstractScreen implements InputProcessor  {

	private Viewport viewport;
	private AbstractScreen to, from;
	private Transition outTransition;
	private Transition inTransition;
	private Action action;
	private TRANSITION_STATE state;
	private SpriteBatch batch;
	public enum TRANSITION_STATE { IN, OUT;}
	
	public TransitionScreen(GameApplication game) {
		super(game);
		viewport = new ScreenViewport();
		batch = new SpriteBatch();
		//updateScreen(0);
	}
	

	@Override
	public void arrangeScreen(Map<String, Object> settings) {
		// Nothing to do.
		
	}
	
	public void startTransition(AbstractScreen from, AbstractScreen to,
									Transition out, Transition in,
									Action action) {
		this.from = from;
		this.to = to;
		this.outTransition = out;
		this.inTransition = in;
		this.action = action;
		this.state = TRANSITION_STATE.OUT;
		getApp().setScreen(this);
		System.out.println("TransitionScreen: Starting...");
		addInputProcessor(this);
	}
									

	public void updateScreen(float delta) {
		System.out.println("TransitionScreen: Update...");
		if(state == TRANSITION_STATE.OUT) {
			outTransition.update(delta);
			if(outTransition.isFinished()) {
				if(action != null) {
					action.doAction();
				}
				state = TRANSITION_STATE.IN;
				return;
			}
		} else if (state == TRANSITION_STATE.IN) {
			inTransition.update(delta);
			if(inTransition.isFinished()) {
				removeInputProcessor(this);
				getApp().setScreen(to);
				to.updateScreen(delta);
			}
		}
	}
	

	@Override
	public void drawScreen(float delta) {
		switch(state) {
			case OUT:
				from.drawScreen(delta);
				viewport.apply();
				outTransition.render(delta, batch);
				break;
			case IN:
				to.drawScreen(delta);
				viewport.apply();
				inTransition.render(delta, batch);
				break;
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		to.resize(width, height);
		from.resize(width, height);
		viewport.update(width, height);
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return true;
	}


}
