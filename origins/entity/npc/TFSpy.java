package com.rcode.origins.entity.npc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.rcode.origins.level.Level;

public class TFSpy extends Follower {

	public Image[] walkDown, walkUp, walkLeft, walkRight;

	public TFSpy(int x, int y, Level level) {
		super(x, y, level);
		
		init();
		
		this.enemyRange = 200;
		this.detectRange = 200;
		this.attackRange = 50;
		
		this.name = "The Spy";
		this.dir = 1;
		
		this.maxDamage = 20;
		this.minDamage = 5;
	}
	
	private void init(){
	
		this.avatar = playerSheet.getSubImage(10, 0);
		

		try {
			s_attack = new Sound("res/audio/player/slice.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		walkUp = new Image[]{playerSheet.getSubImage(9, 3), playerSheet.getSubImage(11, 3)};
		walkDown = new Image[]{playerSheet.getSubImage(9, 0), playerSheet.getSubImage(11, 0)};
		walkLeft = new Image[]{playerSheet.getSubImage(9 ,1), playerSheet.getSubImage(10, 1)};
		walkRight = new Image[]{playerSheet.getSubImage(9, 2), playerSheet.getSubImage(10 ,2)};
			
		this.movementUp = new Animation(walkUp, duration);
		this.movementDown = new Animation(walkDown, duration);
		this.movementLeft = new Animation(walkLeft, duration);
		this.movementRight = new Animation(walkRight, duration);
		
		this.height = avatar.getHeight();
		this.width = avatar.getWidth();
		
		this.dialogue = "Don't worry, I'm on your team... Hueh heuh heuh... I'll help you for a bit.";
	}


}
