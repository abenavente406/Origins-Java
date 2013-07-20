package com.rcode.origins.entity.npc;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class BasicVillager extends Villager {

	Random rand = new Random();

	/**
	 * Array that holds all possible dialogues for basic villagers
	 */
	String[] possibleDialogues = new String[] { "Hello sir! Good day!",
			"How are you doing today?",
			"I've never seen you around these parts..."};

	public BasicVillager(double x, int y, Level level) {
		super((int) x, y, level);

		init();
	}

	private void init() {
		
		int image = rand.nextInt(2);
		
		// The image is chosen on random for basic villagers
		if (image == 1){
			this.name = "Farmer";
		}else{
			this.name = "Farmer";
		}
		
		this.avatar = playerSheet.getSubImage(image, 28);

		this.height = avatar.getHeight();
		this.width = avatar.getWidth();
		
		int dialogueIndex = rand.nextInt(possibleDialogues.length);
		

		this.dialogue = possibleDialogues[dialogueIndex];
	}
	
	@Override
	public void render(Play p, Graphics g){
		g.setColor(new Color(0, 0, 0, .4f));
		g.fillOval((float)this.x + 6,  (float) this.y + this.height - 5,  (float) this.width - 12, (float) this.height / 5);
		
		g.setColor(Color.white);
		this.avatar.draw((float) this.x, (float) this.y);
	}

}
