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

	Player player;

	private double p_HealthPercent;

	private double h_width, h_height;

	private Image emptyBar;

	private Image imgDialogueBox;

	private DialogueBox dialogueBox = new DialogueBox();

	private TextField dialogue;

	private Rectangle health;

	private Graphics g = null;

	private Color c_health = new Color(0.8f, 0.0f, 0.0f, 0.7f);
	
	private String line_0, line_1, line_2, line_3, line_4;

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

		dialogue = new TextField(gc, gc.getDefaultFont(), 5,
				370, dialogueBox.getWidth() - 10, dialogueBox.getHeight());

	}

	public void update(int delta, Play p) {
		p_HealthPercent = ((double) player.getHealth()) / 100;

		health = new Rectangle(14, 13,
				(float) ((float) h_width * p_HealthPercent), (float) h_height);

	}

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

	public void renderDialogue(Play p, GameContainer gc) {

		if (p.getPlayer().isTalking) {
			imgDialogueBox.draw(0, Game.HEIGHT - imgDialogueBox.getHeight());

			for (Entity npc : p.getLevel().npcs) {
				if (npc.isTalking) {
					
					line_0 = npc.getName() + ":\n";
					line_1 = npc.dialogue.substring(0, npc.dialogue.length());
					
					if (npc.dialogue.length() > 68){
						line_1 = npc.dialogue.substring(0, 68);
						line_2 = npc.dialogue.substring(69, npc.dialogue.length());
					}else{
						line_2 = "";
						line_3 = "";
						line_4 = "";
					}
					if (npc.dialogue.length() >= 68 * 2) {
						line_2 = npc.dialogue.substring(69, 68 * 2);
						line_3 = npc.dialogue.substring(68 * 2 + 1, npc.dialogue.length());
					}
					if (npc.dialogue.length() >= 68 * 3){
						line_3 = npc.dialogue.substring(68 * 2, 68 * 3);
						line_4 = npc.dialogue.substring(68 * 3 + 1, npc.dialogue.length());
					}
					
					if (!(line_1 == null)){
						dialogue.setText(line_0 + line_1);
					}
					if (!(line_2 == null)){
						dialogue.setText(line_0 + line_1 + "\n" + line_2);
					}
					if (!(line_3 == null)){
						dialogue.setText(line_0 + line_1 + "\n" + line_2 + "\n" + line_3);
					}
					if (!(line_4 == null)){
						dialogue.setText(line_0 + line_1 + "\n" + line_2 + "\n" + line_3 + "\n" + line_4);	
					}
					
					dialogue.render(gc, g);
				}
			}
		}
	}
	
}
