package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.rcode.origins.Game;
import com.rcode.origins.entity.Player;
import com.rcode.origins.gui.GUI;
import com.rcode.origins.level.Level;

public class Play extends BasicGameState {

	public StateBasedGame parentGame;
	
	private int state;

	public static SpriteSheet itemSheet;
	public static SpriteSheet playerSheet;
	public static SpriteSheet playerSheet2;

	TiledMap worldMap;
	public Level level;
	protected Player player;
	protected GUI gui;

	float worldX;
	float worldY;

	/** X co-ordinate of the 'camera' */
	protected double cameraX;
	/** Y co-ordinate of the 'camera' */
	protected double cameraY;

	Sound m_overWorld;

	// Direction
	int movingDir; // 0 = up, 1 = down, 2 = left, 3 = right

	// Input var
	Input input;

	public boolean renderExits;

	private int renderTimer = 0;
	private int renderTimerMax = 400;

	private int collisionTimer = 0;
	private int collisionTimerMax = 400;

	public Play(int state) {
		this.state = state;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		g.setColor(Color.white);
		level.render(g, this, cameraX, cameraY);
		gui.renderHealth(g, this);
		gui.renderDialogue(this, gc);
		g.drawString(Integer.toString(this.getID()), gc.getWidth() - 40, 0);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if (renderTimer > 0) {
			renderTimer--;
		}

		if (player.dead)
			sbg.enterState(2);

		if (gc.getInput().isKeyPressed(Keyboard.KEY_E)){
			sbg.enterState(5);
		}
		
		getPlayer().update(delta, this, gc);
		gui.update(delta, this);
		
		cameraX = this.getPlayer().getX() - (Game.WIDTH) / 2;
		cameraY = this.getPlayer().getY() - Game.HEIGHT / 2;

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			sbg.enterState(Game.PAUSE);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F4))
			toggleRenderExits();
		if (Keyboard.isKeyDown(Keyboard.KEY_F5))
			refreshCollisions();

		if (player.currentExitZone != null) {
			// sbg.enterState(player.currentExitZone.getTarget(), new
			// FadeOutTransition(), new FadeInTransition());
		}
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

	private void toggleRenderExits() {
		if (renderTimer <= 0) {
			this.renderExits = !this.renderExits;
			this.renderTimer = renderTimerMax;
		}
	}

	private void refreshCollisions() {
		if (collisionTimer <= 0) {
			getLevel().buildCollisionMap();
			this.collisionTimer = collisionTimerMax;
		}
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		parentGame = game;
		
		playerSheet = new SpriteSheet("res/sprites/entities/EntitySprites.png",
				32, 32);
		playerSheet2 = new SpriteSheet("res/sprites/entities/EntitySprite2.png",
				64, 64);
		itemSheet = new SpriteSheet("res/sprites/items/ItemSheet.png", 32, 32);

		player = new Player(35, 45);

		gui = new GUI(player, container);
		input = container.getInput();

		cameraX = player.getX() - Game.WIDTH / 2;
		cameraY = player.getY() - Game.HEIGHT / 2;
	}
}
