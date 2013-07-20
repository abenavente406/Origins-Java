package com.rcode.origins;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;

import com.rcode.origins.states.GameOver;
import com.rcode.origins.states.InventoryState;
import com.rcode.origins.states.MainMenu;
import com.rcode.origins.states.MapState;
import com.rcode.origins.states.Pause;
import com.rcode.origins.states.Play;

public class Game extends StateBasedGame {

	/** Name of the game */
	public static final String NAME = "Origins";

	/**
	 * Game state indexes 0: menu 1: play 2: gameOver 3: pause 4: map state
	 */
	public static final int menu = 0;
	public static final int play = 1;
	public static final int gameOver = 2;
	public static final int pause = 3;
	public static final int mapState = 4;
	public static final int inventoryState = 5;

	/**
	 * Game dimensions WIDTH : 640 HEIGHT : 480
	 */
	public static final int WIDTH = 640, HEIGHT = 480;

	/**
	 * Constructor
	 * 
	 * @param name
	 *: Name of the game
	 */
	public Game(String name) {
		super(name);
		
		// Add the game states
		this.addState(new MainMenu(menu));
		this.addState(new Play(play));
		this.addState(new GameOver(gameOver));
		this.addState(new Pause(pause));
		this.addState(new MapState(mapState));
		this.addState(new InventoryState(inventoryState));
	}

	/** Initializes game states */
	public void initStatesList(GameContainer gc) throws SlickException {
		
		// Initialize game states
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(gameOver).init(gc, this);
		this.getState(pause).init(gc, this);
		this.getState(mapState).init(gc, this);
		this.getState(inventoryState).init(gc, this);
		
		// Enter the menu state
		this.enterState(menu, new FadeInTransition(), null);
	}

	/** Main method */
	public static void main(String[] args) {

		AppGameContainer appgc;

		try {
			appgc = new AppGameContainer(new Game(NAME));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}
}
