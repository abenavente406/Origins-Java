package com.rcode.origins.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import com.rcode.origins.Game;
import com.rcode.origins.entity.Player;
import com.rcode.origins.gui.GUI;
import com.rcode.origins.level.OverWorldLevel;

public class OverWorld extends Play {

	public OverWorld(int state) {
		super(state);
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		super.init(gc, sbg);
		level = new OverWorldLevel("res/worlds/world_1.tmx", player, this);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		level.update(delta);
		super.update(gc, sbg, delta);
	}
}
