package com.rcode.origins.entity.monster;

import java.util.Random;

import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Monster extends Entity{

	/** Timer for random movement */
	int movementTimer = 0;
	int movementTimerMax = 300;
	/** Direction that will be random */
	int movementDir = 1;
	/** Counter variable */
	int i = 0;
	
	Random rand = new Random();
	
	public Monster(int x, int y, Level level){

		level.addMonster(this);
		
		this.attackRange = 25;
		
		this.x = x * 32;
		this.y = y * 32;
	}
	
	@Override
	public void update(int delta, Play p) {

		this.dir = 1;

		// Decreases the cool down timer for attacking
		if (this.getCooldownTimer() > 0) {
			this.coolDownTimer -= delta;
		}
		
		// If the monster isn't moving, then start a new movement
		if (!isMoving){
			movementTimer = rand.nextInt(400) + 1;
			movementDir = rand.nextInt(4);
		}
			
		// Sets the real direction to be the random directions
		this.dir = movementDir;
			
		// Movement variables that will be manipulated
		int dirX = 0, dirY = 0;
		
		if (movementTimer < movementTimerMax) {
			if(movementDir == 0)
				dirY--;
			if(movementDir == 1)
				dirY++;
			if(movementDir == 2)
				dirX--;
			if(movementDir == 3)
				dirX++;
			
			movementTimer++;
			isMoving = true;
			
		} else {
			
			// If the movement timer exceeds the max, set the timer back to 0
			if (i < (rand.nextInt(2500) + 50)) {
				i++;
			} else {
				i = 0;
				isMoving = false;
			}
		}

		// The monster scans for a nearby player
		Player player = this.scanForTarget(p);
		
		// Sets the new coordinates of the monster
		double newX = getX() + dirX * getSpeed() * delta;
		double newY = getY() + dirY * getSpeed() * delta;
		
		// If there is a player nearby, move towards the player
		if(!(player == null)){
			double distX = player.getX() - this.getX();
			double distY = player.getY() - this.getY();
			double distTotal = Math.sqrt(distX * distX + distY * distY);

			// Attack and return (don't move) if the player is within attacking
			// range
			if (Math.abs(distTotal) < this.attackRange)
			{
				try {
					this.attack(player);
				} catch (SlickException e) {
					e.printStackTrace();
				}
				return;
			}

			double dx = (distX / distTotal) * speed * delta;
			double dy = (distY / distTotal) * speed * delta;
			newX = getX() + dx;
			newY = getY() + dy;
		}
		
		// Move the monster
		move(newX, newY, p);
	}
	
	/**
	 * Kill the monster
	 * @param p
	 */
	public void die(Play p)
	{
		// Remove the monster from the world
		p.getLevel().monsters.remove(this);

		// Debug print
		System.out.println(getName() + " was killed!");
	}

	/**
	 * Scan for a nearby player
	 * @param p
	 * @return Player
	 */
	Player scanForTarget(Play p)
	{
		// At present, the only target for a monster is the Player
		Player player = p.getPlayer();

		double distX = this.getX() - player.getX();
		double distY = this.getY() - player.getY();
		double playerDist = Math.sqrt(distX * distX + distY * distY);

		// If the player is within detection range, set as target
		if (playerDist < detectRange)
		{
			return player;
		}
		return null;
	}
}
