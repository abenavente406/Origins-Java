package com.rcode.origins.entity.npc;

import java.util.Random;

import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.monster.Monster;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Follower extends NPC{

	int movementTimer = 0;
	int movementDir = 1;
	
	int timeNotMoving = 0;
	int timeNotMovingMax = 1000;
	
	int i = 0;
	
	Random rand = new Random();
	
	public Follower(int x, int y, Level level) {
		super(x, y, level);
	}

	/**
	 * Followers use the same movement logic as Monsters...
	 * See monster javadoc
	 */
	@Override
	public void update(int delta, Play p){
		this.dir = 1;
		
		if (!(timeNotMoving <= 0)){
			timeNotMoving -= delta;
		}
		
		if (this.getCooldownTimer() > 0) {
			this.coolDownTimer -= delta;
		}
		
		if (!isMoving && timeNotMoving <= 0){
			movementTimer = rand.nextInt(800) + 1;
			movementDir = rand.nextInt(4);
		}
			
		this.dir = movementDir;
			
		int dirX = 0, dirY = 0;
		
		if (movementTimer < 80) {
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
			if (i < (rand.nextInt(2500) + 50)) {
				i++;
			} else {
				i = 0;
				timeNotMoving = timeNotMovingMax;
				isMoving = false;
			}
		}

		Player player = this.scanForPlayer(p);
		Monster monster = (Monster) this.scanForMonster(p);
 
		double newX = getX() + dirX * getSpeed() * delta;
		double newY = getY() + dirY * getSpeed() * delta;
		
		// NPCS take attacking monsters to be priority over following the player
		if (!(monster == null)){
			double distX = monster.getX() - this.getX();
			double distY = monster.getY() - this.getY();
			double distTotal = Math.sqrt(distX * distX + distY * distY);

			if (Math.abs(distTotal) < this.attackRange)
			{
				try {
					this.attack(monster);
					if (monster.getHealth() <= 0){
						monster.die(p);
					}
				} catch (SlickException e) {
					e.printStackTrace();
				}
				return;
			}
			
			double dx = (distX / distTotal) * speed * delta;
			double dy = (distY / distTotal) * speed * delta;
			newX = getX() + dx;
			newY = getY() + dy;
		}else{
			if (!(player == null) && player.isFollowable){
				double distX = player.getX() - this.getX();
				double distY = player.getY() - this.getY();
				double distTotal = Math.sqrt(distX * distX + distY * distY);
	
	
				if (Math.abs(distTotal) < this.attackRange)
				{
					return;
				}
	
				double dx = (distX / distTotal) * speed * delta;
				double dy = (distY / distTotal) * speed * delta;
				newX = getX() + dx;
				newY = getY() + dy;
			}
		}
		
		if (!(player == null)){

			if (player.isTalking){
				this.speak(p);
			}else{
				isTalking = false;
			}
		}else{
			this.isTalking = false;
		}
		
		move(newX, newY, p);
	}
}
