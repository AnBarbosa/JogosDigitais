package br.edu.ufabc.alunos.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class HPLabel extends Label {
	private int maxHP  = 100;
	private int currHP = 100;
	
	// Animating
	private int displayedCurrent = 100;
	private float animatingTimer = 0f;
	private final float TOTAL_TIME = 0.5f;
	private float displayUpdateTime = 1;

	
	
	public HPLabel(int maxHP, Skin skin) {
		super("", skin);
		displayedCurrent = maxHP;
		this.currHP = maxHP;
		this.maxHP = maxHP;
		updateHPText();
	}
	
	
	private String getHpText(int currHP, int maxHP) {
		StringBuilder sb = new StringBuilder("HP: ");
		sb.append(currHP);
		sb.append("/");
		sb.append(maxHP);
		return sb.toString();
	}
	
	private void updateHPText() {
		this.setText(getHpText(this.displayedCurrent, this.maxHP));
	}
	
	public void setCurrHP(int newHP) {
		this.currHP = newHP;
		int delta = displayedCurrent - currHP;
		if(delta < 0) {
			delta = -delta;
		}
		
		displayUpdateTime = TOTAL_TIME / delta; 
	}
	
	public void setDisplayedHP(int newHP) {
		this.displayedCurrent = newHP;
	}
	
	public void setMaxHP(int newHP) {
		this.maxHP = newHP;
	}

	public void act(float delta) {
		
		if(displayedCurrent != currHP) {
			animatingTimer += delta;
			if(animatingTimer > displayUpdateTime) {
				animatingTimer -= displayUpdateTime;
				if(displayedCurrent > currHP) {
					displayedCurrent--;
				}
				if(displayedCurrent < currHP) {
					displayedCurrent++;
				} 
			}
			updateHPText();
			if(displayedCurrent == currHP) {
				animatingTimer = 0;
				displayUpdateTime = 1;
			}
		}
	}

}
