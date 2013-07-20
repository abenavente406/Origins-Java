package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.rcode.origins.Game;

public class GameOver extends BasicGameState {

	private int id;
	
	public GameOver(int Id){
		this.id = Id;
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("You Died.", Game.WIDTH / 2, Game.HEIGHT / 2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			gc.reinit();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			gc.exit();
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
