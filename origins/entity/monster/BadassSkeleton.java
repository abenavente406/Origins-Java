package com.rcode.origins.entity.monster;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class BadassSkeleton extends Monster{

	/** Image array to hold the animations */
	Image[] walkUp, walkDown, walkLeft, walkRight;
	
	Random rand = new Random();
	
	public BadassSkeleton(int x, int y, Level level) throws SlickException {
		super(x, y, level);
		
		init();
		this.name = "Badass";
		this.health = 100;
		this.coolDown = 400;
		this.speed = .12f;
		
	}

	private void init() throws SlickException{
		
		this.avatar = playerSheet.getSubImage(7,0);
		s_attack = new Sound("res/audio/player/slice.wav");
		this.width = avatar.getWidth();
		this.height = avatar.getHeight();
		
		this.realWidth = this.width / 3;
		this.realHeight = this.height;
		
		walkUp = new Image[]{playerSheet.getSubImage(6, 3), playerSheet.getSubImage(8, 3)};
		walkDown = new Image[]{playerSheet.getSubImage(6, 0), playerSheet.getSubImage(8, 0)};
		walkLeft = new Image[]{playerSheet.getSubImage(6, 1), playerSheet.getSubImage(7, 1)};
		walkRight = new Image[]{playerSheet.getSubImage(6, 2), playerSheet.getSubImage(7, 2)};
		
		this.movementUp = new Animation(walkUp, duration);
		this.movementDown = new Animation(walkDown, duration);
		this.movementLeft = new Animation(walkLeft, duration);
		this.movementRight = new Animation(walkRight, duration);

		this.maxDamage = 20;
		this.minDamage = 1;
	}

}
