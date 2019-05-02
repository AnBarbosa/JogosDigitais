package br.edu.ufabc.alunos.battle.actions;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import br.edu.ufabc.alunos.controllers.Controls;
import br.edu.ufabc.alunos.ui.battle.BattleField;

public class TextAction extends BattleAction implements InputProcessor{

	String text;
	boolean textFinished = false;
	InputMultiplexer multiplexer;
	public TextAction(BattleField bf, String text, InputMultiplexer multiplexer) {
		super(bf);
		this.text = text;
		this.multiplexer = multiplexer;
	}

	@Override
	public void startAction() {
		System.out.println("DO ACTION > "+text);
		if(multiplexer != null) {
			multiplexer.addProcessor(0, this);
		}
		bf.displayText(text);
		
	}
	
	@Override
	public boolean isFinished() {
		if(this.multiplexer == null) {
			return bf.displayFinished();
		}
		
		if(textFinished) {
			this.multiplexer.removeProcessor(this);
		}
		return textFinished;
	}
	
	public String toString() {
		return "Print: "+text; 
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(Controls.getComando(keycode)) {
		case OK:
		case CANCEL:
			if(bf.displayFinished()) {
				textFinished = true;
			}
			return true;
		default:
			break; 
		}
		
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
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
