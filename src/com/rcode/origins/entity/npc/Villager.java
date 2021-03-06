package com.rcode.origins.entity.npc;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Villager extends NPC {

	/** Timer for movement */
	int movementTimer = 0;
	/** Random movement direction */
	int movementDir = 1;

	/** Counter variable for movement */
	int movementTicks = 0;

	Random rand = new Random();

	public Villager(int x, int y, Level level) {
		super(x, y, level);

	}

	@Override
	public void render(Play p, Graphics g) {
		g.setColor(new Color(0, 0, 0, .4f));
		g.fillOval((float) this.x + 6, (float) this.y + this.height - 5,
				(float) this.width - 12, (float) this.height / 5);

		g.setColor(Color.white);
		this.getAvatar().draw((float) this.x, (float) this.y);
	}

}
