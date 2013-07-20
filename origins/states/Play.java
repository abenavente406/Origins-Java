package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.rcode.origins.Game;
import com.rcode.origins.entity.Player;
import com.rcode.origins.gui.GUI;
import com.rcode.origins.level.ExitZone;
import com.rcode.origins.level.Level;

public class Play extends BasicGameState {

	private int state;

	public static SpriteSheet itemSheet;
	public static SpriteSheet playerSheet;
	
	TiledMap worldMap;
	public static Level level;
	private Player player;
	private GUI gui;

	float worldX;
	float worldY;

	/** X co-ordinate of the 'camera' */
	private double cameraX;
	/** Y co-ordinate of the 'camera' */
	private double cameraY;

	Sound m_overWorld;
	
	// Direction
	int movingDir; // 0 = up, 1 = down, 2 = left, 3 = right

	// Input var
	Input input;

	private boolean renderExits;
	
	private int renderTimer = 0;
	private int renderTimerMax = 400;
	
	public Play(int state) {
		this.state = state;
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		// Initialize the spritesheet that all sprites use
		playerSheet = new SpriteSheet("res/sprites/entities/EntitySprites.png", 32, 32);
		itemSheet = new SpriteSheet("res/sprites/items/ItemSheet.png", 32, 32);

		player = new Player();
		level = new Level("res/worlds/world_1.tmx", player, this);
		gui = new GUI(player, gc);
		input = gc.getInput();

		cameraX = player.getX() - Game.WIDTH / 2;
		cameraY = player.getY() - Game.HEIGHT / 2;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		g.setColor(Color.white);
		level.render(g, this, cameraX, cameraY);
		gui.renderHealth(g, this);
		gui.renderDialogue(this, gc);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if (renderTimer > 0){
			renderTimer--;
		}
		
		if (player.dead)
			sbg.enterState(2);
		
		int dirX = 0;
		int dirY = 0;

		if (input.isKeyDown(Keyboard.KEY_W)) {
			dirY--;
		}
		if (input.isKeyDown(Keyboard.KEY_S)) {
			dirY++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			dirX--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			dirX++;
		}

		getPlayer().update(dirX, dirY, delta, this, gc);
		level.update(delta);
		
		gui.update(delta, this);
		
		cameraX = this.getPlayer().getX() - (Game.WIDTH) / 2;
		cameraY = this.getPlayer().getY() - Game.HEIGHT / 2;

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			sbg.enterState(Game.pause);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E))
			sbg.enterState(Game.inventoryState);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F4))
			toggleRenderExits();
	}

	public GUI getGUI() {
		return gui;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}
	
	public int getID() {
		return state;
	}
	
	public void toggleRenderExits(){
		if (renderTimer <= 0){
			this.renderExits = !this.renderExits;
			this.renderTimer = renderTimerMax;
		}
	}
}
