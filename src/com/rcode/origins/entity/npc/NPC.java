package com.rcode.origins.entity.npc;

import com.rcode.origins.entity.Direction;
import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.StaticEntity;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;
import org.newdawn.slick.Graphics;

public class NPC extends StaticEntity {

	/** The range in which npcs can detect enemies */
	protected int enemyRange;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            : X tile
	 * @param y
	 *            : Y tile
	 * @param level
	 *            : Level to add the entity
	 */
	public NPC(int x, int y, Level level) {
		super(x, y);
		level.addNPC(this);

        // NPCs are always facing southward
        dir = Direction.SOUTH;
	}

	@Override
	public void update(int delta, Play p) {
		Player player = this.scanForPlayer(p);

		if (!(player == null)) {

			if (player.isTalking()) {
				this.speak(p);
			} else {
				this.isTalking = false;
			}
		} else {
			this.isTalking = false;
		}
	}

    /**
	 * Scan for a nearby monster
	 * 
	 * @param p
	 *            : The main game state
	 * @return A nearby monster
	 */
	@Override
	protected Entity scanForMonster(Play p) {
		// Check through every monster
		for (Entity e : p.getLevel().monsters) {
			// Early failure check - if diff in either axis is too great, move
			// on
			if (Math.abs(this.getX() - e.getX()) > this.enemyRange
					|| Math.abs(this.getY() - e.getY()) > this.enemyRange) {
				continue;
			}

			// Find the actual distance from the player
			double monsterDist = p.getLevel()
					.distanceToEntity((Entity) this, e);

			// If within range, return the monster as a target
			if (monsterDist < enemyRange) {
				return e;
			}
		}
		// No monsters in range, return null
		return null;
	}

	/**
	 * Say dialogue
	 * 
	 * @param p
	 *            : The main game state
	 */
	public void speak(Play p) {
		this.isTalking = true;
	}

}
