package com.rcode.origins;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;

import com.rcode.origins.states.GameOver;
import com.rcode.origins.states.House001;
import com.rcode.origins.states.MainMenu;
import com.rcode.origins.states.OverWorld;
import com.rcode.origins.states.Pause;

public class Game extends StateBasedGame {

	/** Name of the game */
	public static final String NAME = "Origins";

	/**
	 * Game state indexes 0: menu 1: play 2: GAMEOVER 3: PAUSE 4: map state
	 */
	public static final int MENU = 0;
	public static final int OVERWORLD = 1;
	public static final int HOUSE001 = 100;
	public static final int GAMEOVER = 2;
	public static final int PAUSE = 3;
	public static final int MAPSTATE = 4;
	public static final int INVENTORYSTATE = 5;

	/**
	 * Game dimensions WIDTH : 640 HEIGHT : 480
	 */
	public static final int WIDTH = 640, HEIGHT = 480;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            : Name of the game
	 */
	public Game(String name) {
		super(name);

		// Add the game states
		this.addState(new MainMenu(MENU));
		this.addState(new OverWorld(OVERWORLD));
		this.addState(new House001(HOUSE001));
		this.addState(new GameOver(GAMEOVER));
		this.addState(new Pause(PAUSE));
	}

	/** Initializes game states */
	public void initStatesList(GameContainer gc) throws SlickException {
		// Enter the menu state
		this.enterState(MENU, new FadeInTransition(), null);
	}

	/** Main method */
	public static void main(String[] args) {

		AppGameContainer appgc;

		try {
			appgc = new AppGameContainer(new ScalableGame(new Game(NAME), 640, 480));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(960, 720, false);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}
}
