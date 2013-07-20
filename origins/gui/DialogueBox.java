package com.rcode.origins.gui;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.Font;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.opengl.ImageData;

import com.rcode.origins.Game;

public class DialogueBox implements GUIContext{

	private int width, height;
	
	UnicodeFont font;
	
	public DialogueBox(){
		init();
	}
	
	public void init(){
		this.width = Game.WIDTH;
		this.height = 380;
		
		try {
			font = new UnicodeFont("res/fonts/ITCBLKAD.TTF", 12, false, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Input getInput() {
		return null;
	}

	@Override
	public long getTime() {
		return 0;
	}

	@Override
	public int getScreenWidth() {
		return Game.WIDTH;
	}

	@Override
	public int getScreenHeight() {
		return Game.HEIGHT;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Font getDefaultFont() {
		return font;
	}

	@Override
	public void setMouseCursor(String ref, int hotSpotX, int hotSpotY)
			throws SlickException {		
	}

	@Override
	public void setMouseCursor(ImageData data, int hotSpotX, int hotSpotY)
			throws SlickException {		
	}

	@Override
	public void setMouseCursor(Cursor cursor, int hotSpotX, int hotSpotY)
			throws SlickException {		
	}

	@Override
	public void setDefaultMouseCursor() {
	}

}
