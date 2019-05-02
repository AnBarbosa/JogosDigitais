package br.edu.ufabc.alunos.controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputAdapter;

import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.dialog.ChoiceAction;
import br.edu.ufabc.alunos.model.dialog.ClassDialogue;
import br.edu.ufabc.alunos.model.dialog.DialogueNode;
import br.edu.ufabc.alunos.model.dialog.DialogueNode.NODE_TYPE;
import br.edu.ufabc.alunos.model.dialog.DialogueTraverser;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.ui.DialogueBox;
import br.edu.ufabc.alunos.ui.OptionBox;
import br.edu.ufabc.alunos.utils.Log;

public class DialogueController extends InputAdapter {

	private DialogueTraverser traverser;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	private List<Action> endDialogAction;

	public DialogueController(DialogueBox dialogueBox, OptionBox optionBox) {
		super();
		this.dialogueBox = dialogueBox;
		this.optionBox = optionBox;
		endDialogAction = new ArrayList<>();
	}
	
	public void addEndAction(Action a) {
		endDialogAction.add(a);
	}
	
	public void executeEndActions() {
		for (Action a: endDialogAction) {
			a.startAction();
		}
		endDialogAction.clear();
	}
	
	public void startDialogue(DialogueTree dialogue) {
		traverser = new DialogueTraverser(dialogue);
		dialogueBox.setVisible(true);
		dialogueBox.animateText(traverser.getText());
		setupNextOptions(dialogue.getStartNode());
	}
	

	private void setupNextOptions(DialogueNode next) {
		if(next.getType() == NODE_TYPE.MULTIPLE_CHOICE) {
			optionBox.clearChoices();
			for(String s :  next.getOptions()) {
				optionBox.addOption(s);
			}
		}
	}

	private void progress(int index) {
		// The options should only became visible when the dialog ends. (it's set on the update method)
		optionBox.setVisible(false);
		DialogueNode nextNode = traverser.next(index);
		dialogueBox.animateText(nextNode.getText());
		setupNextOptions(nextNode);
	}
	
	public void update(float delta) {
		if(dialogueBox.isFinished() && traverser != null) {
			if(traverser.getType()==NODE_TYPE.MULTIPLE_CHOICE) {
				optionBox.setVisible(true);
			}
		}
	}
	
	@Override 
	public boolean keyDown(int keycode) {
		if(dialogueBox.isVisible()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(optionBox.isVisible()) {
			Log.println("Dialogue key up - optionBox is Visible.");
			switch(Controls.getComando(keycode)) {
				case UNKNOWN:
					return false;
				case DOWN:
					System.out.println("Dialogue key up - optionBox is Visible - move Down");
					optionBox.moveDown();
					return true;
				case UP:
					optionBox.moveUp();
					return true;
				default:
					System.out.println("Dialogue key up - optionBox is Visible - default case");
					break;
			}
		} 
		
		if (traverser != null 
				&& Controls.isComando(keycode, COMANDO.OK)
				&& dialogueBox.isFinished()) {
			switch(traverser.getType()) {
				case FINAL:
					System.out.println("Dialogue Complete.");
					traverser = null;
					dialogueBox.setVisible(false);
					executeEndActions();
					break;
				case LINEAR:
					progress(0);
					break;
				case MULTIPLE_CHOICE:
					int selected = optionBox.getSelected();
					ChoiceAction chosenOptionAction = traverser.getChoiceObserver(selected);
					if(chosenOptionAction != null) {
						chosenOptionAction.doAction();
					}
					progress(optionBox.getSelected());
			}
		}
		
		if(dialogueBox.isVisible()) {
			return true;
		}
		return false;
	}

	public void startDialogue(ClassDialogue classDialogue, Action endAction) {
		this.addEndAction(endAction);
		this.startDialogue(classDialogue);
		
	}

	
}
