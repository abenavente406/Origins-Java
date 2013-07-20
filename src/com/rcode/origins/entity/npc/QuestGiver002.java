package com.rcode.origins.entity.npc;

import com.rcode.origins.level.Level;
import com.rcode.origins.quests.Quest;
import com.rcode.origins.states.Play;

public class QuestGiver002 extends QuestGiver {

	public QuestGiver002(int x, int y, Level level) {
		super(x, y, level);
		init();
	}

	private void init() {

		this.setAvatars(4, 28);

		this.width = avatarDown.getWidth();
		this.height = avatarDown.getHeight();

		this.realWidth = width / 3;
		this.realHeight = height / 5;

		this.name = "Cole Garrison";

		this.dialogue = "I'm so hungry! We are running out of food.";

	}

	@Override
	public void update(int delta, Play p) {
		super.update(delta, p);
		
		if (Quest.Quest002.isCompleted){
			this.dialogue = "You should go check the next town for supplies!";
		}
		
	}

}
