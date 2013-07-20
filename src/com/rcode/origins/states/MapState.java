package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.rcode.origins.level.Level;

public class MapState extends BasicGameState {

	private int state;

	private Image miniMap;

	private Circle playerSpot;

	public MapState(int state) {
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		miniMap = new Image("res/gui/mapState.png");

		playerSpot = new Circle((float) Level.PLAYER.getX() * .0745f,
				(float) Level.PLAYER.getY() * .0745f, 4f);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		miniMap.draw(0, 0);

		g.fill(playerSpot);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game.enterState(1);
		}
	}

	@Override
	public int getID() {
		return state;
	}

}
