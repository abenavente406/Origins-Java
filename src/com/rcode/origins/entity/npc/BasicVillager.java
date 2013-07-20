package com.rcode.origins.entity.npc;

import java.util.Random;
import com.rcode.origins.level.Level;

public class BasicVillager extends Villager {

	Random rand = new Random();

	/**
	 * Array that holds all possible dialogues for basic villagers
	 */
	String[] possibleDialogues = new String[] {
			"Hello sir! Good day!",
			"How are you doing today?",
			"I've never seen you around these parts...",
			"I'm pretty sure I saw Ernie in the woods... Not sure what he's up to",
			"Did you check the woods? Be careful!" };

	public BasicVillager(double x, int y, Level level) {
		super((int) x, y, level);
		init();
	}

	private void init() {

		int image = rand.nextInt(2);

		// The image is chosen on random for basic villagers
		if (image == 1) {
			this.name = "Farmer";
		} else {
			this.name = "Farmer";
		}

		this.setAvatars(image, 28);

		this.height = this.getAvatar().getHeight();
		this.width = this.getAvatar().getWidth();
		
		this.realWidth = width;
		this.realHeight = height - 16;

		int dialogueIndex = rand.nextInt(possibleDialogues.length);

		this.dialogue = possibleDialogues[dialogueIndex];
	}

}
