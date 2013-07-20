package com.rcode.origins.level;

import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.npc.BasicVillager;
import com.rcode.origins.states.House001;

public class HouseLevel extends Level {

	public HouseLevel(String path, Player player, House001 h)
			throws SlickException {
		super(player, h);
		loadLevelFile(path);
		init(h);
	}

	private void init(House001 h) {
		
		new BasicVillager(3, 3, this);
		h.getPlayer().setX(5);
		h.getPlayer().setY(9);
	}

}
