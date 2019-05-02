package br.edu.ufabc.alunos.model.dialog;

import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import br.edu.ufabc.alunos.controllers.DialogueController;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.map.LoadedTileMap;
import br.edu.ufabc.alunos.model.map.world.World;
import br.edu.ufabc.alunos.screen.AbstractScreen;
import br.edu.ufabc.alunos.screen.DungeonScreen;

public class ClassDialogue extends DialogueTree {
	private DialogueController controller;
	private AbstractScreen parentScreen;

	public ClassDialogue(DialogueController dc, AbstractScreen callerScreen) {
		controller = dc;
		this.parentScreen = callerScreen;
		// Linear
		DialogueNode apresentacao = new DialogueNode(
				"Olá! \nQue legal te conhecer.", 0);
		
		// Multiple Options
		DialogueNode pergunta = new DialogueNode(
				"Que tipo de aventureiro você é?", 1);
		
		// Options
		DialogueNode mago = new DialogueNode(
				"Eu senti mesmo uma aura mística.", 2);
		DialogueNode guerreiro = new DialogueNode(
				"Você parece mesmo feroz.", 3);
		DialogueNode gatuno = new DialogueNode(
				"Melhor eu checar meus bolsos.", 4);
		
		apresentacao.makeLinear().setNext(pergunta.getID());
		
		pergunta.addChoice("Mago", mago.getID(), ()->{
			setPlayer(CharacterCreator.Player.WIZARD);
			System.out.println("Nas artes místicas encontrei o poder.");
		});
		pergunta.addChoice("Guerreiro", guerreiro.getID(), ()-> {
			setPlayer(CharacterCreator.Player.WARRIOR);
			System.out.println("Com suor e sangue conquistarei meus inimigos.");

		});
		pergunta.addChoice("Gatuno", gatuno.getID(), ()-> {
			setPlayer(CharacterCreator.Player.ROGUE);
			System.out.println("Mãos mais rápidas que o olho enchem meus bolsos.");

		});
		
		this.addNode(apresentacao, pergunta, mago, guerreiro, gatuno);
		
	}
	
	private void goToDungeon() {
		System.out.println("Going to dungeon.");
		GameApplication ga = GameMaster.getGameApplication();
		Map<String, Object> arranges = DungeonScreen.getDefaultArrange();
		World mundo = LoadedTileMap.getWorldFrom(LoadedTileMap.basicMap());
		Vector2 playerPos = LoadedTileMap.getPlayerPosition(LoadedTileMap.basicMap());
		Vector2 bossPos =  LoadedTileMap.getBossPosition(LoadedTileMap.basicMap());
		
		arranges.put("World", mundo);
		arranges.put("Player_X", (int) playerPos.x);
		arranges.put("Player_Y", (int) playerPos.y);
		arranges.put("Boss_X", (int) bossPos.x);
		arranges.put("Boss_Y", (int) bossPos.y);
		AbstractScreen nextScreen = new DungeonScreen(ga, arranges);
		
		ga.startTransition(parentScreen, 
				nextScreen, 
				GameMaster.getFadeOut(), 
				GameMaster.getFadeIn(), 
				null);
	}
	
	private void goToForest() {
		System.out.println("Going to forest.");
		GameApplication ga = GameMaster.getGameApplication();
		AbstractScreen nextScreen = new DungeonScreen(ga);
		ga.startTransition(parentScreen, 
				nextScreen, 
				GameMaster.getFadeOut(), 
				GameMaster.getFadeIn(), 
				null);
	}
	private void setPlayer(CharacterCreator.Player playerType) {
		String playerName = playerType.toString();
		int startLevel = 1;
		BattleCharacter playerCharacter = CharacterCreator.getPlayer(
										playerType, 
										playerName, 
										startLevel);
		GameMaster.setPlayer(playerCharacter);
	}
	
	public void start() {
		this.controller.startDialogue(this, ()->goToDungeon());
	}
}
