package com.rcode.origins.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.rcode.origins.Game;
import com.rcode.origins.gui.InventoryScreen;

public class InventoryState extends BasicGameState{

	private int id;
	
	private InventoryScreen guiInv;

	public InventoryState(int id){
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		guiInv = new InventoryScreen();
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		game.getState(Game.play).render(container, game, g);
		guiInv.renderInventoryScreen(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			game.enterState(Game.play);
	}

	@Override
	public int getID() {
		return id;
	}

}
