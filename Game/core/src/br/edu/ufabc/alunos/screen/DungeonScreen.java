package br.edu.ufabc.alunos.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.edu.ufabc.alunos.controllers.AgressivePlayerController;
import br.edu.ufabc.alunos.controllers.DialogueController;
import br.edu.ufabc.alunos.controllers.PlayerControllerAdvanced;
import br.edu.ufabc.alunos.core.GameApplication;
import br.edu.ufabc.alunos.core.GameMaster;
import br.edu.ufabc.alunos.model.Action;
import br.edu.ufabc.alunos.model.Actor;
import br.edu.ufabc.alunos.model.Camera;
import br.edu.ufabc.alunos.model.PlayerActor;
import br.edu.ufabc.alunos.model.achievements.AchievementManager;
import br.edu.ufabc.alunos.model.battle.BattleCharacter;
import br.edu.ufabc.alunos.model.battle.CharacterCreator;
import br.edu.ufabc.alunos.model.battle.enums.Enemy;
import br.edu.ufabc.alunos.model.dialog.DialogueTree;
import br.edu.ufabc.alunos.model.map.world.Boss;
import br.edu.ufabc.alunos.model.map.world.World;
import br.edu.ufabc.alunos.model.map.world.WorldObject;
import br.edu.ufabc.alunos.model.map.world.WorldRenderer;
import br.edu.ufabc.alunos.ui.DialogueBox;
import br.edu.ufabc.alunos.ui.OptionBox;
import br.edu.ufabc.alunos.utils.Log;

public class DungeonScreen extends AbstractScreen {

	// Controller
	private AgressivePlayerController playerController;
	private DialogueController dialogueController;
	
	// Models
	private World world;
	private Actor playerActor;
	
	
	// UI
	private Stage uiStage;
	private Table uiTable;
	private DialogueTree dialogueTree;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	
	// View
	private int uiScale = 2;

	private WorldRenderer worldRenderer;
	private Camera worldCamera;
	private SpriteBatch batch;

	private Viewport worldViewport;
	
	private final float CHECK_DELAY = 0.3f;
	private boolean checkForAchievements = true;
	private float checkForAchievementsDelay = 0.3f;
	// ------------------------------------ INITIALIZE ------------------------------------ 
	
	
	public DungeonScreen(GameApplication game) {
		super(game);
		arrangeScreen(getDefaultArrange());
		this.updateScreen(0.001f);
		
	}
	
	public DungeonScreen(GameApplication game, Map<String, Object> arrange) {
		super(game);
		arrangeScreen(arrange);
		this.updateScreen(0.001f);

	}
	
	public static Map<String, Object> getDefaultArrange(){
		Map<String, Object> arrange = new HashMap<>();
		arrange.put("World", new World(100,100));
		arrange.put("Player_X", 50);
		arrange.put("Player_Y", 50);
		arrange.put("Boss_X", -1);
		arrange.put("Boss_Y", -1);
		return arrange;
	}

	@Override
	public void arrangeScreen(Map<String, Object> settings) {
		// Loading settings
		assert(settings.get("World") instanceof World);
		assert(settings.get("Player_X") instanceof Integer);
		assert(settings.get("Player_Y") instanceof Integer);
		assert(settings.get("Boss_X") == null || settings.get("Boss_X") instanceof Integer);
		assert(settings.get("Boss_Y") == null || settings.get("Boss_Y") instanceof Integer);
		
		this.world = (World) settings.get("World");
		int playerX = (int) settings.get("Player_X");
		int playerY = (int) settings.get("Player_Y");
		int bossX = (int)settings.get("Boss_X");
		int bossY = (int)settings.get("Boss_Y");
		
		// Preparing the world	
		prepareWorld(world, playerX, playerY, bossX, bossY);
		
		// UI SETUP
		prepareUI();
		
		
		// Input Processors
		assert(playerActor != null);
		assert(dialogueBox != null);
		assert(optionBox != null);
		
		//playerController = new PlayerControllerAdvanced(playerActor);
		playerController = new AgressivePlayerController(playerActor, this);
		dialogueController = new DialogueController(dialogueBox, optionBox);
		super.addInputProcessor(dialogueController);
		super.addInputProcessor(playerController);
		
		
	}

	private void prepareWorld(World world, int playerX, int playerY,
											int bossX, int bossY) {
		batch = new SpriteBatch();

		playerActor = new PlayerActor(world.getMap(), playerX, playerY);
		
		if(bossX > -1 && bossY > -1)
			addBoss(bossX, bossY);
		world.addActor(playerActor);
		
		
		worldCamera = new Camera();
		
		worldRenderer = new WorldRenderer(getApp().getAssetManager(), 
										  world);
		

		worldViewport = new ScreenViewport();
	}

	
	private void prepareUI() {
		System.out.println("Preparing UI");
		
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, 
				Gdx.graphics.getHeight()/uiScale, true);
		
		uiTable = new Table();
		uiTable.setFillParent(true);
		uiStage.addActor(uiTable);
		
		
		dialogueBox = new DialogueBox(getApp().getSkin());
		dialogueBox.setVisible(false);
		
		optionBox = new OptionBox(getApp().getSkin());
		optionBox.setVisible(false);
		
		Table dialogTable = new Table();
		dialogTable.add(optionBox).expand()		
									.align(Align.right)
									.space(8f)
									.row();
		dialogTable.add(dialogueBox).expand()
									.align(Align.right)
									.space(8f)
									.row();
		
		uiTable.add(dialogTable).expand().align(Align.bottom);
		System.out.println("Done");
	}
	
	// ------------------------------------  LOOP ---------------------------------------------
	
	private void getDebugInputs() {
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			Gdx.app.exit();		
		}
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			uiStage.setDebugAll(true);
		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
			int level = 1;
			boolean boss = (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT));
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.KOBOLD, 1, boss);
			this.startBattle(enemy);		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
			int level = 1;
			boolean boss = (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT));
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.MINOTAUR, 1, boss);
			this.startBattle(enemy);		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.NUM_3)){
			int level = 1;
			boolean boss = (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT));
			BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.DRAGON, 1, boss);
			this.startBattle(enemy);		
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F1)){
			dialogueTree = null;
			AchievementManager.addMessage("ACHIEVEMENT: TESTE");
			checkForAchievements = true;
			checkForAchievementsDelay = CHECK_DELAY;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)){
			Log.debug = true;
			Log.println("----- TILE -------");
			for(int x=0; x<world.getWidth(); x++) {
				for(int y=0; y<world.getHeight(); y++) {
					Actor act = world.getTile(x, y).getActor();
					
					if(act != null) {
						Log.printf("TileMap: Actor at %d, %d\n", x, y);
						Log.printf("Actor: I'm at %d, %d\n\n", act.getX(), act.getY());
					}
				}
			}
			Log.println("----- END TILE -------");
			Log.debug = false;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)){
			Log.debug = true;
			Log.println("----- TILE -------");
			for(int y=world.getHeight()-1; y >= 0; y--) {
				Log.printf("\n");
				for(int x=0; x<world.getWidth(); x++) {
					Actor act = world.getTile(x, y).getActor();
					WorldObject obj = world.getTile(x,y).getObject();
					if(act == null && obj == null) {
						Log.printf(" .");
					}
					if(act != null && obj == null) {
						Log.printf(" x");
					}
					if(act == null && obj != null) {
						Log.printf(" o");
					}
					if(act != null && obj != null) {
						Log.printf(" #");
					}
				}
			}
			Log.println("\n----- END TILE -------");
			Log.debug = false;
		}
	}
	@Override
	public void updateScreen(float delta) {
		getDebugInputs();
		playerController.update(delta);
		worldCamera.update(playerActor.getWorldX(),  playerActor.getWorldY());
		world.update(delta);

		dialogueController.update(delta);
		
		uiStage.act(delta);
		if(checkForAchievements) {
			
			if(checkForAchievementsDelay > 0) {
				checkForAchievementsDelay -=delta;
			} else {
				playerController.setSearchingEnemies(true);
				checkForAchievementsDelay = CHECK_DELAY;
				checkForAchievements = false;
				
				if(AchievementManager.hasMessages()) {
					this.dialogueTree = AchievementManager.getMessages();
					assert(dialogueTree != null);
					dialogueController.startDialogue(dialogueTree);
					
				}
			}
		}
	}

	@Override
	public void drawScreen(float delta) {
		worldViewport.apply();
		batch.begin();
		worldRenderer.render(batch, worldCamera);
		batch.end();
		// uiStage.draw calls uiViewport.apply();
		//uiStage.getViewport().apply();
		uiStage.draw();
		
	}

	
	// ------------------------------------ LIBGDX  --------------------------------------------------
	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		uiStage.getViewport().update(width/uiScale, height/uiScale, true);
		worldViewport.update(width, height, true);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		uiStage.dispose();		
	}
	
	// ------------------------------------  OTHERS ------------------------------------ 
	public void startBattle(BattleCharacter enemy, boolean boss) {
		playerController.releaseAllCommands();
		Color color = Color.BLACK;
		Map<String, Object> arrange = BattleScreen.getDefaultArrange();
		arrange.put("Enemy", enemy);
		arrange.put("Boss", boss);
		BattleScreen bs = new BattleScreen(game, arrange);
		GameMaster.setPlayerStat("Last_Screen", this);
		game.startTransition(this, bs, 
				new FadeOutTransition(0.8f, color),
				new FadeInTransition(0.8f, color),
				null);
	}
	public void startBattle(BattleCharacter enemy) {
		this.startBattle(enemy, false);
	}
	
	public void onTransitionIn() {
		playerController.setSearchingEnemies(false);
		checkForAchievements = true;
	}
	
	private void addBoss(int x, int y) {
		Texture bossTex = GameApplication.getAssetManager().get("Tile/boss.png");
		TextureRegion texReg = new TextureRegion(bossTex);
		Boss boss = new Boss(x,y, texReg);
		Action fightBoss = new Action(){
			@Override
			public void startAction() {
				BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.DRAGON, 5, true);
				//BattleCharacter enemy = CharacterCreator.getEnemy(Enemy.KOBOLD, 1, false);
				startBattle(enemy, true);
			}
		};
		boss.setOnTouchAction(fightBoss);
		
		world.addObject(boss);
		System.out.println("Boss criado em "+x+" "+y);
	}
}
