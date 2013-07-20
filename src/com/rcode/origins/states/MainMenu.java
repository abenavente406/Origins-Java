package com.rcode.origins.states;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {

	private int state;
	private int select = 0;

	Sound select_s;
	Sound confirm;
	Sound m_overWorld;

	Font titleFont;
	Image title;

	public MainMenu(int state) {
		this.state = state;
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		titleFont = new Font("Cambria", Font.PLAIN, 36);
		title = new Image("res/gui/title.png");

		select_s = new Sound("res/audio/Menu/select.wav");
		confirm = new Sound("res/audio/Menu/confirm.wav");

		m_overWorld = new Sound("res/audio/music/m_village.wav");

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		title.draw(210, 150);
		g.drawString("P L A Y", 300, 250);
		g.drawString("Q U I T", 300, 290);

		if (select == 0) {
			g.drawString(">          <", 275, 250);
		} else if (select == 1) {
			g.drawString(">          <", 275, 290);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if (!m_overWorld.playing())
			m_overWorld.loop();

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			if (select < 1) {
				select_s.play();
				select++;
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if (select > 0) {
				select_s.play();
				select--;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if (select == 0) {
				confirm.play();
				sbg.enterState(1, new FadeOutTransition(),
						new FadeInTransition());
			} else if (select == 1) {
				AL.destroy();
				gc.exit();
			}
		}

	}

	public int getID() {
		return state;
	}
}
