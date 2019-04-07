package br.edu.ufabc.alunos.ui.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import br.edu.ufabc.alunos.ui.DialogueBox;

public class FixedSizeDialogue extends DialogueBox {

	private final int WIDTH;
	private final int HEIGHT;;

	
	public FixedSizeDialogue(Skin skin, int width, int height) {
		super(skin);
		this.WIDTH = width;
		this.HEIGHT = height;
		this.textLabel.setWrap(true);
		this.removeActor(textLabel);
		this.add(textLabel).width(WIDTH).height(HEIGHT);
		//this.debugAll();
	}
	
	public FixedSizeDialogue(Skin skin) {
		super(skin);
		this.WIDTH = 200;
		this.HEIGHT = 75;
		this.textLabel.setWrap(true);
		this.removeActor(textLabel);
		this.add(textLabel).width(WIDTH).height(HEIGHT);
		//this.debugAll();
	}
		
	
	
	@Override
	protected void setText(String text) {
		super.setText(text);

		//System.out.println(text);
	}
	
//	@Override
//	protected void setText(String text) {
//	    String[]  lines = text.split("\r\n|\r|\n");
//	    int numLines = lines.length;
//	    int overLines = numLines - MAX_LINES;
//	    StringBuilder sb = new StringBuilder();
//	    
//	    if (overLines <= 0){
//	    	sb.append(text);
//	    	// Após uma iteração, temos um \n por i.
//	    	// Após todas as iterações, teremos adicionado MAX_LINES - numLines
//	    	int underLines = -overLines;
//	    	for(int i = 0; i<=underLines; i++) {
//	    		sb.append("\n");
//	    	}
//	    } else {
//	    		for (int i = 0; i<MAX_LINES; i++) {
//		    		int lineToPrint = numLines-MAX_LINES+i-1;
//		    		sb.append(lines[lineToPrint]);
//		    		sb.append("\n");
//	    		}
//
//	    	
//	    }
//	    // Remove trailing \n
//    	int lastLineBreak = sb.lastIndexOf("\n");
//    	if(lastLineBreak > 0) {sb.deleteCharAt(lastLineBreak);}
//    	super.setText(sb.toString());
//	}

	@Override
	public float getPrefHeight() { return HEIGHT; }
	
	@Override
	public float getPrefWidth()  { return WIDTH;  
	}
	
	
}
