package br.edu.ufabc.alunos.model.dialog;

import java.util.List;

import br.edu.ufabc.alunos.model.dialog.DialogueNode.NODE_TYPE;

public class DialogueTraverser {
	private DialogueTree dialogue;
	private DialogueNode currentNode;
	
	public DialogueTraverser(DialogueTree dialog) {
		this.dialogue = dialog;
		currentNode = dialogue.getNode(dialogue.getStart());
	}
	
	public DialogueNode next(int pointerIndex) {
		DialogueNode nextNode = dialogue.getNode(currentNode.getNextPointerId(pointerIndex));
		currentNode = nextNode;
		return nextNode;
	}
	
	public List<String> getOptions(){
		return currentNode.getOptions();
	}
	
	public String getText() {
		return currentNode.getText();
	}
	
	public NODE_TYPE getType() {
		return currentNode.getType();
	}
}
