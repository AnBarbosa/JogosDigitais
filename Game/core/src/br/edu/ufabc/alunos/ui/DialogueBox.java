package br.edu.ufabc.alunos.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.edu.ufabc.alunos.utils.Log;

public class DialogueBox extends Table {
	private static final float TIME_PER_CHARACTER = 0.05f;
	private static final float PREFERED_WIDTH = 200f;
	
	private String targetText = "";
	private float animTimer = 0f;
	private float animationTotalTime = 0f;
	
	private DIALOG_STATE state = DIALOG_STATE.IDLE;
	
	private enum DIALOG_STATE {ANIMATING, IDLE} ;
	protected Label textLabel;
	
	public DialogueBox(Skin skin) {
		super(skin);
		
		this.setBackground("dialoguebox");
		textLabel = new Label("\n", skin);
		this.add(textLabel).expand().align(Align.left).pad(5f);
	}
	
	public DialogueBox(Skin skin, String message) {
		this(skin);
		this.targetText = message;
	}
	
	public void animateText(String text) {
		targetText = text;
		animationTotalTime = text.length()*TIME_PER_CHARACTER;
		state = DIALOG_STATE.ANIMATING;
		animTimer = 0f;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		if(state==DIALOG_STATE.ANIMATING) {
			Log.println("IF 1");
			animTimer += delta;
			if (animTimer > animationTotalTime){
				Log.println("IF 2");
				state = DIALOG_STATE.IDLE;
				animTimer = animationTotalTime;
			}
			String actuallyDisplayed = "";
			int charactersToDisplay = (int) ((animTimer/animationTotalTime)*targetText.length());
			for (int i=0; i < charactersToDisplay; i++) {
				actuallyDisplayed += targetText.charAt(i);
				Log.printf("%s", targetText.charAt(i));
			}
			Log.println("\nEnded...");
			if (!actuallyDisplayed.equals(textLabel.getText().toString())) {
				Log.println("\nUpdating display...");
				setText(actuallyDisplayed);
			}
		}
	}

	protected void setText(String text) {
		if(!text.contains("\n")) {
			text += "\n";
		}
		this.textLabel.setText(text);
		this.textLabel.setWrap(true);
		this.textLabel.pack();
	}
	
	@Override
	public float getPrefWidth() {
		return PREFERED_WIDTH;
	}
	
	
	public boolean isFinished() {
		return state==DIALOG_STATE.IDLE;
	}
	
	
}
