package com.rcode.origins.entity.npc;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class TFSpy extends Follower {

	/**
	 * Create the spy
	 * 
	 * @param x
	 *            : The x location (tile)
	 * @param y
	 *            : The y location (tile)
	 * @param level
	 *            : The level to spawn the spy
	 * @throws SlickException
	 */
	public TFSpy(int x, int y, Level level) throws SlickException {
		super(x, y, level);

		init();

		this.enemyRange = 200;
		this.detectRange = 200;
		this.attackRange = 50;

		this.name = "The Spy";

		this.dir = rand.nextInt(4);

		this.maxDamage = 20;
		this.minDamage = 5;
	}

	private void init() throws SlickException {

		this.setAnimation(9, 0);

		s_attack = new Sound("res/audio/player/slice.wav");

		this.height = this.getAvatar().getHeight();
		this.width = this.getAvatar().getWidth();

		this.dialogue = "Don't worry, I'm on your team... Hueh heuh heuh... I'll help you for a bit.";
	}

}
