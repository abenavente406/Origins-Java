package com.rcode.origins.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ExitZone {

	/** Location of the exit zone */
	private int x, y;

	/** Size of the exit zone */
	private int sizeX, sizeY;

	/** The target of the exit zone */
	private int targetZone;

	/** The rectangle (size) of the exit zone */
	private Rectangle rect;

	public ExitZone(int targetZone, int x, int y, int sizeX, int sizeY,
			Level level) {
		this.x = x * 32;
		this.y = y * 32;
		this.sizeX = sizeX * 32;
		this.sizeY = sizeY * 32;
		this.targetZone = targetZone;

		rect = new Rectangle(x, y, sizeX, sizeY);

		level.exitZones.add(this);
	}

	/**
	 * Renders the exit zone
	 * 
	 * @param g
	 *            : Graphics object
	 * @param cameraX
	 * @param cameraY
	 */
	public void renderExits(Graphics g, double cameraX, double cameraY) {

		g.setColor(new Color(.5f, .8f, .5f, .5f));
		// g.fillRect((float) (getX() - cameraX), (float) (getY() - cameraY),
		// (float) getSizeX(),
		// (float) getSizeY());
		g.fillRect(x, y, 64, 32);
		g.setColor(new Color(1, 1, 1, 1));
	}

	/**
	 * Get x location
	 * 
	 * @return The real x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y location
	 * 
	 * @return The real y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the width of the exit zone
	 * 
	 * @return The width of the exit zone
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Get the height of the exit zone
	 * 
	 * @return The height of the exit zone
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * Get the rectangle of the exit zone
	 * 
	 * @return rectangle of exit zone
	 */
	public Rectangle getRect() {
		return this.rect;
	}

	/**
	 * Get the target id
	 * 
	 * @return target id
	 */
	public int getTarget() {
		return targetZone;
	}

	/**
	 * Changes the target id
	 * 
	 * @param targetId
	 */
	public void changeTargetId(int targetId) {
		this.targetZone = targetId;
	}
}
