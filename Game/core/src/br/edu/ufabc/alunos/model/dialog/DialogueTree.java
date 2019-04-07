package br.edu.ufabc.alunos.model.dialog;

import java.util.HashMap;
import java.util.Map;

public class DialogueTree {

	private Map<Integer, DialogueNode> nodes = new HashMap<Integer, DialogueNode>();
	
	public DialogueNode getNode(int id) {
		return nodes.get(id);
	}
	
	public void addNode(DialogueNode... node) {
		for(DialogueNode n : node) {
			this.nodes.put(n.getID(), n);			
		}
	}
	
	public int getStart() {
		return 0;
	}
	
	public DialogueNode getStartNode() {
		return nodes.get(getStart());
	}
}
