package com.rcode.origins.entity.npc;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.rcode.origins.entity.Entity;

public class StatusRenders {

	public static final StatusRenders questReady = new StatusRenders(31, 28);

	int x_tile, y_tile;
	int x_loc, y_loc;

	Image statusImage;

	SpriteSheet sheet = null;

	public StatusRenders(int tx, int ty) {

		this.x_tile = tx;
		this.y_tile = ty;

		init();
	}

	private void init() {

		if (sheet == null) {
			try {
				sheet = new SpriteSheet(
						"res/sprites/entities/EntitySprites.png", 32, 32);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.statusImage = sheet.getSubImage(x_tile, y_tile);
	}

	public void render(Entity e) {
		statusImage.draw((float) e.getX(), (float) e.getY() - e.getHeight());
	}
}
