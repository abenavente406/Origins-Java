package com.rcode.origins.entity.npc;

import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class NPC extends Entity{

	protected int enemyRange;
	
	/**
	 * Constructor
	 * @param x
	 * : X tile
	 * @param y
	 * : Y tile
	 * @param level
	 * : Level to add the entity
	 */
	public NPC(int x, int y, Level level){
	
		level.addNPC(this);
		
		this.x = x * 32;
		this.y = y * 32;
	}
	
	@Override
	public void update(int delta, Play p) {
		this.dir = 1;
	}
	
	/**
	 * Scan for a nearby player
	 * @param p
	 * : The main game state
	 * @return A nearby player
	 */
	Player scanForPlayer(Play p)
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

	/**
	 * Scan for a nearby monster
	 * @param p
	 * : The main game state
	 * @return A nearby monster
	 */
	Entity scanForMonster(Play p) {
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
	 * @param p
	 * : The main game state
	 */
	public void speak(Play p){
		this.isTalking = true;
	}

}
