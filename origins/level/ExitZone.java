package com.rcode.origins.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ExitZone {

	/** Location of the exit zone */
	private int x, y;
	
	/** Size of the exit zone */
	private int sizeX, sizeY;
	
	private int targetZone;
	
	private Rectangle rect;
	
	public ExitZone(int targetZone, int x, int y, int sizeX, int sizeY, Level level){
		this.x = x * 32;
		this.y = y * 32;
		this.sizeX = sizeX * 32;
		this.sizeY = sizeY * 32;
		this.targetZone = targetZone;
		
		rect = new Rectangle(x, y, sizeX, sizeY);
		
		level.exitZones.add(this);
	}
	
	public void renderExits(Graphics g, double cameraX, double cameraY){
		
		g.setColor(new Color(.5f, .8f, .5f, .5f));
		//g.fillRect((float) (getX() - cameraX), (float) (getY() - cameraY), (float) getSizeX(),
			//	(float) getSizeY());
		g.fillRect(x, y, 64, 32);
		g.setColor(new Color(1, 1, 1, 1));
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getSizeX(){
		return sizeX;
	}
	
	public int getSizeY(){
		return sizeY;
	}
	
	public Rectangle getRect(){
		return this.rect;
	}

	public int getTarget(){
		return targetZone;
	}
	
	public void changeTargetId(int targetId){
		this.targetZone = targetId;
	}
}
