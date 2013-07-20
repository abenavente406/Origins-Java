package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState {

	UnicodeFont font;

	Font r_font;

	private int state;

	private Color back;

	public Pause(int state) {
		this.state = state;
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		font = new UnicodeFont("res/fonts/ITCBLKAD.TTF", 16, false, false);
		r_font = font;

		back = new Color(0, 0, 0, .5f);

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		sbg.getState(1).render(gc, sbg, g);

		g.setColor(back);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

		g.setColor(Color.white);
		g.drawString("    Pause\n\nReturn (ENTER)\n  Quit (ESC)",
				gc.getWidth() / 2 - 40, gc.getHeight() / 2 - 40);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			sbg.enterState(1);
		}
	}

	@Override
	public int getID() {
		return state;
	}

}
