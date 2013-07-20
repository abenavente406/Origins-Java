package com.rcode.origins.entity.ambient;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Rabbit extends AmbientEntity {

	private Image[] explosion;
	
	private SpriteSheet explosionS;
	
	private int[] duration;
	
	public Rabbit(int x, int y, Level level) throws SlickException {
		super(x, y, level);
		init();
		
		this.health = 5;
	}

	private void init() throws SlickException {

		this.setAnimation(0, 24);

		this.width = avatarDown.getWidth();
		this.height = avatarDown.getHeight();

		this.realWidth = width / 3;
		this.realHeight = height / 3;

		this.maxDamage = 10;
		this.minDamage = 1;

		this.name = "Bunny Rabbit";
		this.speed = .12f;
		
		explosionS = new SpriteSheet("res/sprites/entities/explosion.png", 32, 64);
		
		explosion = new Image[]{explosionS.getSubImage(7, 0), explosionS.getSubImage(8, 0), 
				explosionS.getSubImage(9, 0), explosionS.getSubImage(10, 0), explosionS.getSubImage(11, 0), 
				explosionS.getSubImage(12, 0)};
		
		duration = new int[explosion.length];
		
		for (int i = 0; i < duration.length; i++){
			duration[i] = 100;
		}
		
		death = new Animation(explosion, duration);
				
	}
	public void render(Play p, Graphics g) {

		g.setColor(new Color(0, 0, 0, .4f));
		g.fillOval((float) this.x + 6, (float) this.y + this.height - 7,
				(float) this.width - 12, (float) this.height / 5);

		super.render(p, g);
	}
}
