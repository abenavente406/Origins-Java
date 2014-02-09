package com.rcode.origins.entity.npc;

import java.util.Random;

import com.rcode.origins.entity.AnimatedEntity;
import com.rcode.origins.entity.Direction;
import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.monster.Monster;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Follower extends AnimatedEntity {

    int enemyRange;

	/** Timer that indicates movement */
	int movementTimer = 0;
	int movementTimerMax = 700;
	/** Random movement direction */
	Direction movementDir;

	/** Time not moving */
	int timeNotMoving = 0;
	int timeNotMovingMax = 1000;

	int movementTicks = 0;
	int movementTicksMax = 100;

	Random rand = new Random();

	public Follower(int x, int y, Level level) {
		super(x, y);
        level.addNPC(this);
	}

	/**
	 * Followers use the same movement logic as Monsters... See monster javadoc
	 */
	@Override
	public void update(int delta, Play p) {

		this.dir = Direction.getRandDirection();

		// Decreases the cool down timer for attacking
		if (this.getCooldownTimer() > 0) {
			this.coolDownTimer -= delta;
		}

		// If the monster isn't moving, then start a new movement
		if (!isMoving) {

			timeNotMoving++;

			if (timeNotMoving > timeNotMovingMax) {
				movementTimer = rand.nextInt(400) + 1;
				movementDir = Direction.getRandDirection();

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
			if (movementDir == Direction.NORTH)
				dirY--;
			if (movementDir == Direction.SOUTH)
				dirY++;
			if (movementDir == Direction.WEST)
				dirX--;
			if (movementDir == Direction.EAST)
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

		Player player = this.scanForPlayer(p);
		Monster monster = (Monster) this.scanForMonster(p);

		double newX = getX() + dirX * getSpeed() * delta;
		double newY = getY() + dirY * getSpeed() * delta;

		// NPCS take attacking monsters to be priority over following the player
		if (!(monster == null)) {
			double distX = monster.getX() - this.getX();
			double distY = monster.getY() - this.getY();
			double distTotal = Math.sqrt(distX * distX + distY * distY);

			if (Math.abs(distTotal) < this.attackRange) {

				dir = this.faceEntity(monster, this.getX(), this.getY());

				try {
					this.attack(monster);
					if (monster.getHealth() <= 0) {
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
		} else {
			if (!(player == null) && player.isFollowable) {

				dir = this.faceEntity(player, this.getX(), this.getY());
				double distX = player.getX() - this.getX();
				double distY = player.getY() - this.getY();
				double distTotal = Math.sqrt(distX * distX + distY * distY);

				if (Math.abs(distTotal) < this.attackRange) {
					return;
				}

				double dx = (distX / distTotal) * speed * delta;
				double dy = (distY / distTotal) * speed * delta;
				newX = getX() + dx;
				newY = getY() + dy;
			}
		}

		move(newX, newY, p);
	}
}
