package com.rcode.origins.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.rcode.origins.level.HouseLevel;

public class House001 extends Play {

	public House001(int state) {
		super(state);
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		super.init(gc, sbg);
		level = new HouseLevel("res/worlds/inside/house001.tmx", player,
				this);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		super.update(gc, sbg, delta);
	}

}
