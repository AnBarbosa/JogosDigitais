package br.edu.ufabc.alunos.model.achievements;

import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;
import br.edu.ufabc.alunos.model.dialog.DialogueNode;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.screen.AbstractScreen;

public class AchievementManager {
	private static boolean firstKill = false;
	private static boolean gameWon = false;
	private static int dragonsKilled = 0;
	private static int koboldsKilled = 0;
	private static int minotaursKilled = 0;
	
	private static DialogueTree achievementsMessages;
	private static int numberOfMessages = 0;
	
	private AchievementManager() {}
	
	public static void addKill(Enemy type) {
		if (!firstKill) {
			firstKill = true;
			addMessage("Parabens.\nVocê recebeu seu primeiro\nACHIEVEMENT:\n  **Primeira Vitória**");
		}
		switch(type) {
		case DRAGON:
			dragonsKilled++;
			if(dragonsKilled == 1) {
				addMessage("Você recebeu um\nACHIEVEMENT\nCaçador de Dragões");
			}
			if(dragonsKilled == 5) {
				addMessage("Você recebeu um\nACHIEVEMENT\n*Matador de Dragões*.");
			}
			break;
		case MINOTAUR:
			minotaursKilled++;
			if(minotaursKilled == 1) {
				addMessage("Você recebeu um\nACHIEVEMENT\nCaçador de Minotauros");
			}
			if(minotaursKilled == 5) {
				addMessage("Você recebeu um\nACHIEVEMENT\n*Matador de Minotauros*.");
			}
			break;
		case KOBOLD:
			koboldsKilled++;
			if(koboldsKilled == 1) {
				addMessage("Você recebeu um\nACHIEVEMENT\nCaçador de Kobolds");
			}
			if(koboldsKilled == 5) {
				addMessage("Você recebeu um\nACHIEVEMENT\n*Matador de Kobolds*.");
			}
			break;
		}
		
		if(dragonsKilled+minotaursKilled+koboldsKilled == 15) {
			addMessage("Parabens.\nVocê recebeu um\nACHIEVEMENT:\n  **Assassino Serial**");
		}
	}
	
	public static void addMessage(String message) {
		System.out.println("Achievement - Adding achievement message: "+message);
		if(achievementsMessages == null) {
			achievementsMessages = new DialogueTree();
			DialogueNode node = new DialogueNode(message, numberOfMessages++);
			achievementsMessages.addNode(node);		
		
		} else {
			DialogueNode lastNode = achievementsMessages.getNode(numberOfMessages-1);
			lastNode.makeLinear();
			lastNode.setNext(numberOfMessages);
			DialogueNode node = new DialogueNode(message, numberOfMessages);
			achievementsMessages.addNode(node);
			numberOfMessages++;
		}
		
	}

	
	public static DialogueTree getMessages() {
		System.out.println("Achievement - Getting achievement messages");
		DialogueTree messages = achievementsMessages;
		achievementsMessages = null; // We wont show it two times
		numberOfMessages = 0;
		return messages;
	}
	
	
	
	public static boolean hasMessages() {
		return achievementsMessages != null;
	}
	
	
}
