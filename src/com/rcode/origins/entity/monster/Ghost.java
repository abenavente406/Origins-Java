package com.rcode.origins.entity.monster;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class Ghost extends Monster {

	public Ghost(int x, int y, Level level) throws SlickException {

		super(x, y, level);

		this.init();

	}

	private void init() throws SlickException {

		this.setAnimation(0, 0);

		s_attack = new Sound("res/audio/player/slice.wav");

		this.width = this.getAvatar().getWidth();
		this.height = this.getAvatar().getHeight();
		this.realWidth = this.width / 3;
		this.realHeight = this.height;

		this.maxDamage = 10;
		this.minDamage = 1;

		this.name = "Docile Ghost";
		this.coolDown = 600;
	}

}
