package com.rcode.origins.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

import com.rcode.origins.Game;
import com.rcode.origins.entity.monster.Monster;
import com.rcode.origins.entity.npc.NPC;
import com.rcode.origins.inventory.InventoryPlayer;
import com.rcode.origins.item.Item;
import com.rcode.origins.item.ItemWeapon;
import com.rcode.origins.level.ExitZone;
import com.rcode.origins.states.Play;

public class Player extends Entity {

	/** The image arrays for animations */
	Image[] walkUp, walkDown, walkLeft, walkRight;

	public static InventoryPlayer inventory;

	public boolean isFollowable = false;

	private int pickUpRange = 20;

	private int speechRange = 40;

	/**
	 * Different cool down timers
	 * 
	 * choiceCool: set followable choiceCool_1: set talking choiceCool_2:
	 * setting no clip
	 */
	int choiceCool, choiceCool_1, choiceCool_2;

	/**
	 * Max cool down timer
	 */
	int choiceCoolMax = 500;

	/**
	 * Constructor
	 * 
	 * @throws SlickException
	 */
	public Player() throws SlickException {

		this.init();

		this.height = avatar.getHeight();
		this.width = avatar.getWidth();
		System.out.println("Width: " + this.width + "  Height: " + this.height);

		this.name = "Steve";

		this.health = 100;

	}

	/**
	 * Initialize the player
	 * 
	 * @throws SlickException
	 */
	public void init() throws SlickException {

		// Initialize the sound the player makes when attacking
		this.s_attack = new Sound("res/audio/player/sword_swing_1.wav");

		// Initialize animations and images for the player
		this.avatar = playerSheet.getSubImage(0, 1);
		walkUp = new Image[] { playerSheet.getSubImage(3, 3),
				playerSheet.getSubImage(4, 3) };
		walkDown = new Image[] { playerSheet.getSubImage(3, 0),
				playerSheet.getSubImage(5, 0) };
		walkLeft = new Image[] { playerSheet.getSubImage(3, 1),
				playerSheet.getSubImage(4, 1) };
		walkRight = new Image[] { playerSheet.getSubImage(3, 2),
				playerSheet.getSubImage(4, 2) };
		this.movementUp = new Animation(walkUp, duration);
		this.movementDown = new Animation(walkDown, duration);
		this.movementLeft = new Animation(walkLeft, duration);
		this.movementRight = new Animation(walkRight, duration);

		// Initialize the max and min damage the player can make
		this.maxDamage = 10;
		this.minDamage = 1;

		// Initialize the player's location (real coordinate * tile size)
		this.x = 45 << 5;
		this.y = 34 << 5;

		inventory = new InventoryPlayer(this);

	}

	/**
	 * Update method
	 * 
	 * @param dirX
	 *            : The x direction
	 * @param dirY
	 *            : The y direction
	 * @param delta
	 * @param p
	 *            : The main game state
	 * @throws SlickException
	 */
	public void update(int dirX, int dirY, int delta, Play p, GameContainer gc)
			throws SlickException {

		this.isMoving = false;

		// If the player has no health, then die
		if (health <= 0)
			die(this, p);

		// The speed resets to .08 but changes due to special traits
		this.speed = .08f;

		// Decrease the cooldown timer if necessary
		if (this.getCooldownTimer() > 0) {
			this.coolDownTimer -= delta;
		}

		// Decrease all cooldown timers
		if (this.choiceCool > 0)
			this.choiceCool -= delta;
		if (this.choiceCool_1 > 0)
			this.choiceCool_1 -= delta;
		if (this.choiceCool_2 > 0)
			this.choiceCool_2 -= delta;

		// The player scans for a nearby monster
		Monster m = (Monster) scanForMonster(p);
		// The player scans for a nearby npc
		// NPC npc = (NPC) scanForEntity(p);
		// The player scans for a talkable npc
		NPC speechCandidate = (NPC) scanForTalker(p);
		// The player scans for items on the ground
		Item itemCandidate = scanForItem(p);
		// Checks if the player is in an exit zone
		ExitZone exitZone = isInExitZone(p);

		if (!(exitZone == null)) {
			System.out.println("Exit zone found!");
		}

		// If the player holds shift, he/she sprints
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)
				|| (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			this.speed = .15f;
		}

		// If super speed is enabled, the speed is .4
		if (superSpeed) {
			this.speed = .4f;
		}

		// Change the player's x location based on direction and speed
		double newX = getX() + dirX * getSpeed() * delta;
		double newY = getY() + dirY * getSpeed() * delta;

		// Move the player
		this.move(newX, newY, p);

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.isMoving = true;
			dir = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.isMoving = true;
			dir = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.isMoving = true;
			dir = 2;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.isMoving = true;
			dir = 3;
		}

		// Sets if the player can be followed
		if (Keyboard.isKeyDown(Keyboard.KEY_F))
			setFollowable();

		// If the player is talking, but walks away from an npc, the player
		// stops talking
		if (isTalking && speechCandidate == null)
			this.isTalking = false;

		// Allows the player to talk
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!(speechCandidate == null)) {
				this.setTalking(speechCandidate, p);
			}
		}

		// Get input from the game container
		gc.getInput();
		// Player attacks using the mouse left button
		if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (!(m == null)) {
				attack(m);
				if (m.getHealth() < 0) {
					m.die(p);
				}
			}
			if (!(itemCandidate == null)) {
				pickUpItem(itemCandidate, p);
			}
		}

		// If the player presses 'N', no clip is toggled
		if (Keyboard.isKeyDown(Keyboard.KEY_N))
			this.toggleNoClip();

	}

	/**
	 * Searches for a talker (ONLY FOR PLAYER CLASS)
	 * 
	 * @param p
	 *            : Main game state
	 * @return A talkable npc
	 */
	Entity scanForTalker(Play p) {
		// Check through every npc
		for (Entity e : p.getLevel().npcs) {
			// Early failure check - if diff in either axis is too great, move
			// on
			if (Math.abs(this.getX() - e.getX()) > this.speechRange
					|| Math.abs(this.getY() - e.getY()) > this.speechRange) {
				continue;
			}

			// Find the actual distance from the player
			double entityDist = p.getLevel().distanceToEntity((Entity) this, e);

			// If within range, return the monster as a target
			if (entityDist < speechRange) {
				return e;
			}
		}
		// No npcs in range, return null
		return null;
	}

	/**
	 * Scans for items on the ground
	 * 
	 * @param p
	 *            : The main game state
	 * @return An item near the player
	 */
	Item scanForItem(Play p) {
		// Check through every npc
		for (Item i : p.getLevel().itemsOnMap) {
			// Early failure check - if diff in either axis is too great, move
			// on
			if (Math.abs(this.getX() - i.getX()) > this.pickUpRange
					|| Math.abs(this.getY() - i.getY()) > this.pickUpRange) {
				continue;
			}

			// Find the actual distance from the player
			double entityDist = p.getLevel().distanceToItem((Entity) this, i);

			// If within range, return the monster as a target
			if (entityDist < pickUpRange) {
				return i;
			}
		}
		// No npcs in range, return null
		return null;
	}

	ExitZone isInExitZone(Play p) {
		// Check all exit zones

		for (ExitZone ez : p.getLevel().exitZones) {
			if (this.x > ez.getX() && this.x < ez.getX() + ez.getSizeX()
					&& this.y > ez.getY() && this.y < ez.getY() + ez.getSizeY()) {
				return ez;
			}

		}

		return null;
	}

	/**
	 * Adds item to inventory
	 * 
	 * @param i
	 *            : The item to pick up
	 */
	private void pickUpItem(Item i, Play p) {
		System.out.println(i.getName() + " was added to the inventory.");
		i.removeFromWorld(p);
		inventory.addWeaponToInventory((ItemWeapon) i);
		inventory.equipItem(0);
	}

	/**
	 * Renders the player's health
	 */
	@Override
	public void renderHealth(Graphics g) {

		if (dead)
			return;

		g.drawString(Integer.toString((int) this.health),
				(float) Game.WIDTH / 2, (float) Game.HEIGHT / 2 - 20);
	}

	/**
	 * Kills the player
	 * 
	 * @param player
	 *            : The player to KILL
	 * @param p
	 */
	public void die(Player player, Play p) {
		this.dead = true;
	}

	/**
	 * Sets the player to be followable or not
	 */
	public void setFollowable() {
		if (choiceCool <= 0) {
			this.isFollowable = !isFollowable;
			choiceCool = choiceCoolMax;
		}
	}

	/**
	 * Toggles the player to be talking
	 * 
	 * @param npc
	 *            : The target of the conversation
	 * @param p
	 *            : The main game state
	 */
	public void setTalking(NPC npc, Play p) {
		if (choiceCool_1 <= 0) {

			if (!this.isTalking) {
				System.out.println("Is Talking");
				isTalking = true;
			} else {
				this.isTalking = false;
			}

			choiceCool_1 = choiceCoolMax;
		}
	}

	/**
	 * Toggles no clip
	 */
	public void toggleNoClip() {
		if (choiceCool_2 <= 0) {
			this.noClip = !noClip;
			choiceCool_2 = choiceCoolMax;
		}
	}

	/**
	 * Toggles god mode
	 */
	public void toggleGodMode() {
		if (choiceCool_2 <= 0) {
			this.godMode = !this.godMode;
			choiceCool_2 = choiceCoolMax;
		}
	}

	@Override
	public void update(int delta, Play p) {
		return;
	}

}
