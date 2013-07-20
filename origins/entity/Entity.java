package com.rcode.origins.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.rcode.origins.item.ItemWeapon;
import com.rcode.origins.states.Play;

public abstract class Entity {

	/** The spritesheet where the entity's image is found */
	protected static SpriteSheet playerSheet = Play.playerSheet;

	/** Entity location */
	public double x, y;
	/** Entity direction */
	public int dir = 1; // 0 = up, 1 = down, 2 = left, 3 = right
	/** If the entity is moving */
	protected boolean isMoving = false;

	/** Entity name */
	protected String name;

	/** If the entity is alive */
	public boolean dead = false;

	/** Dialogue for the npc */
	public String dialogue;

	/** If the entity is talking */
	public boolean isTalking = false;

	/** Health of entity */
	protected int health = 20;

	/** Movement speed: default .08 */
	protected float speed = .08f;
	/** Movement speed multiplier */
	protected float speedMult = 1f;

	/** Entity image */
	public Image avatar = null;
	/** Entity image */
	protected Animation movementUp, movementDown, movementLeft, movementRight;
	/** Entity's animation duration time */
	protected int[] duration = { 200, 200 };

	/** Entity's image dimensions */
	public int height;
	/** Entity's image dimensions */
	public int width;

	/** Entity's real dimensions */
	protected int realHeight;
	/** Entity's real dimensions */
	protected int realWidth;

	/** Max and min amount of damage a monster can do */
	protected int maxDamage;
	/** Max and min amount of damage a monster can do */
	protected int minDamage;

	/** Distance an enemy can sense a player */
	protected int detectRange = 100;

	/** Distance that an entity can attack from */
	protected int attackRange = 50;

	/** Item that is equipped */
	public ItemWeapon equipped;
	
	/** Multiplier to increase damage */
	protected int hitMult = 0;

	/** Timers for attacking */
	protected int coolDown = 350;
	/** Timers for attacking */
	protected int coolDownTimer = 0;

	/** The attacking sound effects */
	protected Sound s_attack;
	/** The attacking sound effects */
	protected Sound s_Slice;

	/** Special qualities:
	 * 
	 * No clip: The entity ignores collisions
	 */
	protected boolean noClip = false;
	
	/** Special qualities:
	 *  
	 * God mode: The entity cannot be damaged
	 */
	protected boolean godMode = false;
	
	/** Special qualities:
	 *  
	 * Super speed: The entity moves 400% faster
	 */
	protected boolean superSpeed = false;

	/**
	 * Update entities
	 * 
	 * @param delta
	 * @param p
	 * : The main game state
	 */
	public abstract void update(int delta, Play p);

	/**
	 * Render entities
	 * 
	 * @param p
	 * : The main game state
	 */
	public void render(Play p, Graphics g) {

		this.movementDown.setAutoUpdate(false);
		this.movementUp.setAutoUpdate(false);
		this.movementLeft.setAutoUpdate(false);
		this.movementRight.setAutoUpdate(false);
		
		g.setColor(new Color(0, 0, 0, .4f));
		g.fillOval((float) this.x + 6,  (float) this.y + this.height - 5,  (float) this.width - 12, (float) this.height / 5);
		
		g.setColor(Color.white);
		// Based on direction, draw the entity's animation
		if (dir == 0) {
			if (this.isMoving)
				this.movementUp.setAutoUpdate(true);
			movementUp.draw((float) x, (float) y);
		}
		if (dir == 1) {
			if (this.isMoving)
				this.movementDown.setAutoUpdate(true);
			movementDown.draw((float) x, (float) y);
		}
		if (dir == 2) {
			if (this.isMoving)
				this.movementLeft.setAutoUpdate(true);
			movementLeft.draw((float) x, (float) y);
		}
		if (dir == 3) {
			if (this.isMoving)
				this.movementRight.setAutoUpdate(true);
			movementRight.draw((float) x, (float) y);
		}

	}

	/**
	 * Render health of entities
	 * 
	 * @param g
	 * : The graphics object to draw from
	 */
	public void renderHealth(Graphics g) {

		// If the entity is dead, don't render health
		if (dead)
			return;

		g.drawString(Integer.toString((int) this.health), (float) this.x / 32,
				(float) this.y / 32 - 20);
	}

	/**
	 * Attack target
	 * 
	 * @param target
	 * : The entity being attacked
	 * @throws SlickException
	 */
	public void attack(Entity target) throws SlickException {

		// Only attack when the cooldown finishes cooling down
		if (coolDownTimer <= 0) {

			if (s_Slice == null) {
				s_Slice = new Sound("res/audio/player/slice.wav");
			}

			s_attack.play();
			s_Slice.play();

			// Calculate how much damage to do
			double min, max, damage;

			min = this.getMinDamage();
			max = this.getMaxDamage();
			
			if (!(equipped == null)){
				min = this.getMinDamage() + equipped.getMinDamage();
				max = this.getMaxDamage() + equipped.getMaxDamage();
			}else{
				System.out.println("Nothing equipped!");
			}
			damage = min + (int) (Math.random() * (max - min));

			// Roll for critical hit
			if (Math.random() > 0.9) {
				damage *= 2;
				System.out.println("BIFF! ZOT!");
			}

			// Deal the damage
			target.damage(damage);

			// Debug print
			System.out.println("POW! [" + this.getName() + " attacked "
					+ target.getName() + " for " + damage + " dmg]");

			// Reduce cooldown
			if (!(equipped == null)){
				this.coolDownTimer = equipped.getCoolDown();
			}else{
				this.coolDownTimer = this.coolDown;
			}

		}
	}

	/**
	 * Moves the entity
	 * 
	 * @param newX
	 *: The new x location of the entity
	 * @param newY
	 *: The new y location of the entity
	 * @param p
	 *: The main game state
	 */
	public void move(double newX, double newY, Play p) {

		// No clip allows an entity to ignore collisions
		if (!noClip) {
			if (!p.getLevel().isBoxBlocked(newX, y, this.width, this.height)
					&& (!p.getLevel().isUnitBlocked(this, newX, newY))) {
				x = newX;
			}
			if (!p.getLevel().isBoxBlocked(x, newY, this.width, this.height)
					&& (!p.getLevel().isUnitBlocked(this, newX, newY))) {
				y = newY;
			}
		} else {
			x = newX;
			y = newY;
		}

	}


	/**
	 * Scans for a nearby monster
	 * 
	 * @param p
	 *            : The main game state
	 * @return Monster found as Entity
	 */
	Entity scanForMonster(Play p) {
		// Check through every monster
		for (Entity e : p.getLevel().monsters) {
			// Early failure check - if diff in either axis is too great, move
			// on
			if (Math.abs(this.getX() - e.getX()) > this.getAttackRange()
					|| Math.abs(this.getY() - e.getY()) > this.getAttackRange()) {
				continue;
			}

			// Find the actual distance from the player
			double monsterDist = p.getLevel()
					.distanceToEntity((Entity) this, e);

			// If within range, return the monster as a target
			if (monsterDist <= this.getAttackRange()) {
				return e;
			}
		}
		// No monsters in range, return null
		return null;
	}
	


	/**
	 * Scans for an npc
	 * 
	 * @param p
	 *            : The main game state
	 * @return An npc nearby the player
	 */
	Entity scanForEntity(Play p) {
		// Check through every npc
		for (Entity e : p.getLevel().npcs) {
			// Early failure check - if diff in either axis is too great, move
			// on
			if (Math.abs(this.getX() - e.getX()) > this.detectRange
					|| Math.abs(this.getY() - e.getY()) > this.detectRange) {
				continue;
			}

			// Find the actual distance from the player
			double entityDist = p.getLevel().distanceToEntity((Entity) this, e);

			// If within range, return the monster as a target
			if (entityDist < detectRange) {
				return e;
			}
		}
		// No npcs in range, return null
		return null;
	}
	
	/**
	 * Deals damge to the entity
	 * @param damage
	 *: The amount of damage to cause
	 */
	public void damage(double damage) {
		this.health -= damage;
	}

	/**
	 * Get entity health
	 * 
	 * @return The entity's current health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Get real x-coordinate
	 * 
	 * @return The entity's current x co-ordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get real y co-ordinate
	 * 
	 * @return The entity's current y co-ordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get cooldown timer max value
	 * 
	 * @return The initial value that the cool down timer starts at
	 */
	public int getCooldownTimer() {
		return coolDownTimer;
	}

	/**
	 * Get minimum amount of damage the entity can cause
	 * 
	 * @return The minimum amount of damage an entity can cause
	 */
	public int getMinDamage() {
		return minDamage;
	}

	/**
	 * Get maximum amount of damage the entity can cause
	 * 
	 * @return The maximum amount of damage an entity can cause
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * Get the attack range of the entity
	 * 
	 * @return The attack range of the entity
	 */
	public int getAttackRange() {
		return attackRange;
	}

	/**
	 * Get the adjusted width of the entity
	 * 
	 * @return The adjusted width of the entity
	 */
	public int getPhysWidth() {
		return realWidth;
	}

	/**
	 * Get the adjusted height of the entity
	 * 
	 * @return The adjusted height of the entity.
	 */
	public int getPhysHeight() {
		return realHeight;
	}

	/**
	 * Get the name of the entity
	 * 
	 * @return The name of the entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the speed of the entity
	 * 
	 * @return The speed of which the entity moves
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Get the dialogue of the entity
	 * 
	 * @return The string that represents the dialogue of the entity
	 */
	public String getDialogue() {
		return dialogue;
	}

	/**
	 * Set the dialogue of the entity
	 * 
	 * @param dialogue
	 * : A new string that the entity will say when spoken to
	 */
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
}
