package com.rcode.origins.entity.behavior;

import java.util.Random;

import com.rcode.origins.entity.Entity;
import com.rcode.origins.states.Play;

public class Behavior {

	static int durationTick;
	static int durationMax = EnumBehaviors.RUNAWAY.getDuration();

	/** Timer for random movement */
	int movementTimer = 0;
	int movementTimerMax = 300;
	/** Direction that will be random */
	int movementDir = 1;
	/** Counter variable */
	int i = 0;

	Random rand = new Random();

	public static void runAway(Entity e, Play p) {

		if (durationTick < durationMax) {
			int dirX = 0, dirY = 0;

			if (e.getX() < p.getPlayer().getX()
					&& e.getY() < p.getPlayer().getY()) {
				// Player is in quadrant I

				dirX--;
				dirY--;

			} else if (e.getX() > p.getPlayer().getX()
					&& e.getY() < p.getPlayer().getY()) {
				// Player is in quadrant II

				dirX++;
				dirY--;

			} else if (e.getX() > p.getPlayer().getX()
					&& e.getY() > p.getPlayer().getY()) {
				// Player is in quadrant III

				dirX++;
				dirY++;

			} else if (e.getX() < p.getPlayer().getX()
					&& e.getY() > p.getPlayer().getY()) {
				// Player is in quadrant IV

				dirX--;
				dirY++;
			}

			double newX = e.getX() + dirX * e.getSpeed() * 2;
			double newY = e.getY() + dirY * e.getSpeed() * 2;

			e.move(newX, newY, p);
		} else {
			durationTick = 0;
		}

	}
}
