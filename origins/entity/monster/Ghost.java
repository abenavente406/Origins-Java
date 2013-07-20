package com.rcode.origins.entity.monster;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class Ghost extends Monster{

	/** Image arrays for animations */
	Image[] walkUp, walkDown, walkLeft, walkRight;
	
	Random rand = new Random();
	
	public Ghost(int x, int y, Level level) throws SlickException{
		
		super(x, y, level);
		
		this.init();

		this.name = "Docile Ghost";
		this.coolDown = 600;
		
	}
	
	private void init() throws SlickException{
		
		this.avatar = playerSheet.getSubImage(1,0);
		
		s_attack = new Sound("res/audio/player/slice.wav");
		this.width = avatar.getWidth();
		this.height = avatar.getHeight();
		
		this.realWidth = this.width / 3;
		this.realHeight = this.height;
		
		walkUp = new Image[]{playerSheet.getSubImage(0, 3), playerSheet.getSubImage(1, 3)};
		walkDown = new Image[]{playerSheet.getSubImage(0, 0), playerSheet.getSubImage(1, 0)};
		walkLeft = new Image[]{playerSheet.getSubImage(0 ,1), playerSheet.getSubImage(1, 1)};
		walkRight = new Image[]{playerSheet.getSubImage(0, 2), playerSheet.getSubImage(1 ,2)};
		
		this.movementUp = new Animation(walkUp, duration);
		this.movementDown = new Animation(walkDown, duration);
		this.movementLeft = new Animation(walkLeft, duration);
		this.movementRight = new Animation(walkRight, duration);
		
		movementUp.setAutoUpdate(true);
		movementDown.setAutoUpdate(true);
		movementLeft.setAutoUpdate(true);
		movementRight.setAutoUpdate(true);

		this.maxDamage = 10;
		this.minDamage = 1;
	}
	


}
