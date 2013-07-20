package com.rcode.origins.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

import com.rcode.origins.Game;
import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.states.Play;

public class GUI {

	/** The main player */
	Player player;

	/** The health percentage of the player's health */
	private double p_HealthPercent;

	/** The dimensions for the health bar */
	private double h_width, h_height;

	/** Image for the empty player health bar */
	private Image emptyBar;

	/** Image for the dialogue box */
	private Image imgDialogueBox;

	/** The instance for the dialogue box */
	private DialogueBox dialogueBox = new DialogueBox();

	/** Sets up a textfield to hold the text */
	private TextField dialogue;

	/** The rectangle that gets filled to represent the player's health */
	private Rectangle health;

	/** Slick graphics object */
	private Graphics g = null;

	/** The color of the player's health */
	private Color c_health = new Color(0.8f, 0.0f, 0.0f, 0.7f);

	/** Dialouge is split into 5 lines */
	private String line_0, line_1, line_2, line_3, line_4;

	/**
	 * Create an instance of GUI (only to be run once)
	 * 
	 * @param player
	 *            : The player in which the health is gotten from
	 * @param gc
	 *            : The Game container
	 * @throws SlickException
	 */
	public GUI(Player player, GameContainer gc) throws SlickException {
		this.player = player;
		init(gc);
	}

	public void init(GameContainer gc) throws SlickException {

		emptyBar = new Image("res/GUI/Status Bar.png");
		imgDialogueBox = new Image("res/GUI/dialogue.png");

		p_HealthPercent = player.getHealth() / 100;
		h_width = 182;
		h_height = 17;
		health = new Rectangle(14, 13, (float) h_width, (float) h_height);

		dialogue = new TextField(gc, gc.getDefaultFont(), 5, 370,
				dialogueBox.getWidth() - 10, dialogueBox.getHeight());

	}

	/**
	 * Updates all gui components
	 * 
	 * @param delta
	 *            : Delta...
	 * @param p
	 *            : The main game state
	 */
	public void update(int delta, Play p) {
		p_HealthPercent = ((double) player.getHealth()) / 100;

		health = new Rectangle(14, 13,
				(float) ((float) h_width * p_HealthPercent), (float) h_height);

	}

	/**
	 * Renders the PLAYER'S helath to the screen
	 * 
	 * @param g
	 *            : The slick graphics object
	 * @param p
	 *            : The main game state
	 */
	public void renderHealth(Graphics g, Play p) {

		if (this.g == null)
			this.g = g;

		emptyBar.draw(10, 10);

		g.setColor(c_health);
		g.fill(health);

		g.setColor(Color.white);
		g.drawString("HP: " + Integer.toString(player.getHealth()) + " / 100",
				45, 12);

	}

	/**
	 * Renders the dialogue to the screen in the dialogue box
	 * 
	 * THIS NEEDS OPTIMIZATION
	 * 
	 * @param p
	 *            : The main game state
	 * @param gc
	 *            : The game container
	 */
	public void renderDialogue(Play p, GameContainer gc) {

		if (p.getPlayer().isTalking()) {
			imgDialogueBox.draw(0, Game.HEIGHT - imgDialogueBox.getHeight());

			for (Entity npc : p.getLevel().npcs) {
				if (npc.isTalking()) {

					// Line 0 is always the entity name (i.e. Santa:\n)
					line_0 = npc.getName() + ":\n";
					line_1 = npc.getDialogue().substring(0,
							npc.getDialogue().length());

					// Check if the dialogue is greater than 68 characters long
					if (npc.getDialogue().length() > 68) {
						line_1 = npc.getDialogue().substring(0, 68);
						line_2 = npc.getDialogue().substring(69,
								npc.getDialogue().length());
					} else {
						line_2 = "";
						line_3 = "";
						line_4 = "";
					}
					if (npc.getDialogue().length() >= 68 * 2) {
						line_2 = npc.getDialogue().substring(69, 68 * 2);
						line_3 = npc.getDialogue().substring(68 * 2 + 1,
								npc.getDialogue().length());
					}
					if (npc.getDialogue().length() >= 68 * 3) {
						line_3 = npc.getDialogue().substring(68 * 2, 68 * 3);
						line_4 = npc.getDialogue().substring(68 * 3 + 1,
								npc.getDialogue().length());
					}

					if (!(line_1 == null)) {
						dialogue.setText(line_0 + line_1);
					}
					if (!(line_2 == null)) {
						dialogue.setText(line_0 + line_1 + "\n" + line_2);
					}
					if (!(line_3 == null)) {
						dialogue.setText(line_0 + line_1 + "\n" + line_2 + "\n"
								+ line_3);
					}
					if (!(line_4 == null)) {
						dialogue.setText(line_0 + line_1 + "\n" + line_2 + "\n"
								+ line_3 + "\n" + line_4);
					}

					dialogue.render(gc, g);
				}
			}
		}
	}

}
