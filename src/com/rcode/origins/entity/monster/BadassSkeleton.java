package com.rcode.origins.entity.monster;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class BadassSkeleton extends Monster {

	public BadassSkeleton(int x, int y, Level level) throws SlickException {
		super(x, y, level);
		init();
	}

	private void init() throws SlickException {

		s_attack = new Sound("res/audio/player/slice.wav");

		this.setAnimation(6, 0);

		this.width = this.getAvatar().getWidth();
		this.height = this.getAvatar().getHeight();

		this.realWidth = this.width / 3;
		this.realHeight = this.height;

		this.maxDamage = 20;
		this.minDamage = 1;

		this.name = "Badass";
		this.health = 100;
		this.coolDown = 400;
		this.speed = .12f;
	}

}
