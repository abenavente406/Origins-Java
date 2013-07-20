package com.rcode.origins.entity.npc;

import com.rcode.origins.level.Level;
import com.rcode.origins.quests.Quest;
import com.rcode.origins.states.Play;

public class QuestGiver001 extends QuestGiver {

	public QuestGiver001(int x, int y, Level level) {
		super(x, y, level);
		init();
	}

	private void init() {

		this.setAvatars(2, 28);

		this.width = avatarDown.getWidth();
		this.height = avatarDown.getHeight();

		this.realWidth = width;
		this.realHeight = height - 16;

		this.name = "Ernie";

		this.dialogue = "Hello! I lost my sword somewhere in the maze...";

	}

	@Override
	public void update(int delta, Play p) {
		if (!p.getPlayer().hasSword()) {
			this.dialogue = "Hello! I lost my sword somewhere in the maze...";
		} else {
			Quest.Quest001.isCompleted = true;
			this.dialogue = "Wow you found it! You can actually have it, I have another one.";
		}
		super.update(delta, p);
	}

}
