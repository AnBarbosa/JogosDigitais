package br.edu.ufabc.alunos.model.map.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.model.Action;

public class Boss extends WorldObject {
	public Action onTouchAction;
	
	public Boss(int x, int y, TextureRegion tex) {
		super(x, y, 2, 2, 
				false, 
				false, 
				true, 
				tex, 
				null);
		GridPoint2[] grid = {new GridPoint2(0,0), new GridPoint2(0,1),
							new GridPoint2(1,0), new GridPoint2(1,1)};
		this.setTiles(grid);
	}
	
	public void setOnTouchAction(Action a) {
		this.onTouchAction = a;
	}
	
	public void onTouch() {
		if(onTouchAction != null)
			this.onTouchAction.startAction();
		else {
			System.out.print("Não há onTouchAction registrada.");
		}
	}
	
	

}
