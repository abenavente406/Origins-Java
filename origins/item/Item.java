package com.rcode.origins.item;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Item {

	/** Name of the item (TYPE) */
	protected String name;
	
	/** Icon to be displayed in the inventory menu */
	public Image icon;
	
	/** If the item is to be rendered to the world */
	public boolean isInWorld = false;
	
	/** Location of the item that is rendered on the world */
	private int x, y;
	
	/**
	 * Create an item on the world
	 * @param id
	 * : Id number... Cannot conflict
	 * @param isInWorld
	 * : If the item is to be rendered to the world
	 * @param level
	 * : The level to add the item to
	 */
	public Item(boolean isInWorld, Level level){
		this.isInWorld = isInWorld;
		level.addItemToMap(this);
	}
	
	/**
	 * Get the name of the TYPE of the item
	 * @return TYPE of item
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the icon for the item
	 * @param x
	 * : X tile of the item on the spritesheet
	 * @param y
	 * : Y tile of the item on the spritesheet
	 * @return 
	 */
	public void setIcon(int x, int y){
		this.icon = Play.itemSheet.getSubImage(x, y);
	}
	
	/**
	 * Sets the x coordinate location
	 * @param tx
	 * : X tile location
	 */
	public void setX(int tx){
		x = tx * 32;
	}
	
	/**
	 * Sets the y coordinate location
	 * @param ty
	 * : Y tile location
	 */
	public void setY(int ty){
		y = ty * 32;
	}
	
	/**
	 * Gets the x location
	 * @return the real x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gets the y location
	 * @return the real y coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Adds the item to the world
	 * @param level
	 * : The level to add the item to
	 */
	public void addToWorld(Level level){
		level.itemsOnMap.add(this);
	}
	
	/**
	 * Remove the item from the world
	 * @param p
	 * : Main game state
	 */
	public void removeFromWorld(Play p){
		p.getLevel().itemsOnMap.remove(this);
	}
	
	/**
	 * Renders the item to the world
	 * @param g
	 * : Slick graphics object
	 * @param level
	 * : Level to render the item to
	 */
	public void render(Graphics g, Level level){
		if (this.isInWorld){
			this.icon.draw(x, y);
		}
	}
}
