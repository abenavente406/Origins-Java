package com.rcode.origins.entity.npc;

import com.rcode.origins.entity.Player;
import com.rcode.origins.level.Level;
import com.rcode.origins.quests.Quest;
import com.rcode.origins.states.Play;

public class TownGuard extends Villager {

	public TownGuard(int x, int y, Level level) {
		super(x, y, level);
		init();
	}

	private void init() {

		this.setAvatars(3, 28);

		this.width = avatarDown.getWidth();
		this.height = avatarDown.getHeight();

		this.realWidth = this.width * 2;

		this.name = "Town Guard";

		this.dialogue = "No one leaves town without protection.";

		this.speed = .35f;

		this.detectRange = 300;
	}

	@Override
	public void update(int delta, Play p) {
		if (!Quest.Quest001.isCompleted) {
			this.dialogue = "No one passes without protection.";
		} else {
			this.dialogue = "Stay safe out there!";
			this.realWidth = this.width;
		}

		Player player = scanForPlayer(p);
		Player talker = (Player) scanForPlayerTalker(p);

		double newX = this.getX();

		if (!(player == null)) {
			if (!Quest.Quest001.isCompleted) {
				double distX = player.getX() - this.getX();
				double distY = player.getY() - this.getY();
				double distTotal = Math.sqrt(distX * distX + distY * distY);

				double dx = (distX / distTotal) * speed * delta;
				newX = getX() + dx;
			}
		}

		if (!(talker == null)) {

			if (talker.isTalking()) {
				this.speak(p);
			} else {
				isTalking = false;
			}
		} else {
			this.isTalking = false;
		}

		move(newX, this.getY(), p);
	}
}
