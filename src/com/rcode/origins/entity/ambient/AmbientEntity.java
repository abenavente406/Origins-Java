package com.rcode.origins.entity.ambient;

//import org.newdawn.slick.Graphics;

import java.util.Random;

import com.rcode.origins.entity.Entity;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class AmbientEntity extends Entity {

	/** Timer for random movement */
	int movementTimer = 0;
	int movementTimerMax = 300;
	/** Direction that will be random */
	int movementDir = 1;

	/** Time not moving */
	int timeNotMoving = 0;
	int timeNotMovingMax = 1000;

	int movementTicks = 0;
	int movementTicksMax = 100;

	Random rand = new Random();

	public AmbientEntity(int x, int y, Level level) {
		super(x, y);
		level.addAmbient(this);

		this.dir = rand.nextInt(4);
		this.detectRange = 120;
	}

	@Override
	public void update(int delta, Play p) {
		this.dir = 1;

		// Decreases the cool down timer for attacking
		if (this.getCooldownTimer() > 0) {
			this.coolDownTimer -= delta;
		}

		// If the monster isn't moving, then start a new movement
		if (!isMoving) {

			timeNotMoving++;

			if (timeNotMoving > timeNotMovingMax) {
				movementTimer = rand.nextInt(400) + 1;
				movementDir = rand.nextInt(4);

				isMoving = true;
				timeNotMoving = 0;
				timeNotMovingMax = rand.nextInt(1200) + 220;
			}
		}

		// Sets the real direction to be the random directions
		this.dir = movementDir;

		// Movement variables that will be manipulated
		int dirX = 0, dirY = 0;

		if (movementTimer < movementTimerMax) {
			if (movementDir == 0)
				dirY--;
			if (movementDir == 1)
				dirY++;
			if (movementDir == 2)
				dirX--;
			if (movementDir == 3)
				dirX++;

			movementTimer++;
			isMoving = true;
		} else {

			// If the movement timer exceeds the max, set the timer back to 0
			if (movementTicks < movementTicksMax) {
				movementTicks++;
				movementTicksMax = rand.nextInt(200) + 250;
				movementTimerMax = rand.nextInt(300) + 200;
			} else {
				movementTicks = 0;
				isMoving = false;
			}
		}

		double newX = getX() + dirX * getSpeed() * delta;
		double newY = getY() + dirY * getSpeed() * delta;

		move(newX, newY, p);

	}
	
	/**
	 * Kill the monster
	 * 
	 * @param p
	 */
	public void die(Play p) {
		
		this.death.draw((float) getX(), (float) getY());
		
		// Remove the monster from the world
		//p.getLevel().ambientEntities.remove(this);

		// Debug print
		System.out.println(getName() + " was killed!");
	}

}
