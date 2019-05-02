package br.edu.ufabc.alunos.model.dialog;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
	private final List<Integer> pointers = new ArrayList<>();
	private final List<String> optionsLabels	= new ArrayList<>();
	private final List<ChoiceAction> observers = new ArrayList<>();
	private final String text;
	private final int id;
	
	private NODE_TYPE type;
	
	public enum NODE_TYPE { MULTIPLE_CHOICE,	LINEAR,		FINAL	;}
	
	public DialogueNode(String text, int id) {
		this.text = text;
		this.id = id;
		this.type = NODE_TYPE.FINAL;
	}
	
	public void addChoice(String option, int nodeID) {
		if(type == NODE_TYPE.LINEAR) {
			// If it is linear, it does not have labels, so we just clear the pointers list.
			pointers.clear();
		}
		optionsLabels.add(option);
		pointers.add(nodeID);
		type = NODE_TYPE.MULTIPLE_CHOICE;
	}
	

	public void addChoice(String option, int nodeID, ChoiceAction optionObserver) {
		observers.add(optionObserver);
		addChoice(option, nodeID);
	}

	
	public NODE_TYPE getType() {
		return type;
	}

	public int getID() {
		return this.id;
	}

	public int getNextPointerId(int index) {
		return pointers.get(index);
	}

	public List<String> getOptions() {
		return new ArrayList<>(optionsLabels);
	}
	
	public List<ChoiceAction> getObservers(){
		return new ArrayList<>(observers);
	}
	
	public ChoiceAction getObserver(int choice) {
		if(observers.size() >= choice+1) {
			return observers.get(choice);
		} else 
			return null;
	}

	public String getText() {
		return this.text;
	}

	public DialogueNode makeLinear() {
		pointers.clear();
		optionsLabels.clear();
		//
		type=NODE_TYPE.LINEAR;
		return this;
	}

	public DialogueNode setNext(int nextID) {
		pointers.add(nextID);
		return this;
	}

	
	
}

