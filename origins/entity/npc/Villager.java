package com.rcode.origins.entity.npc;

import java.util.Random;

import com.rcode.origins.entity.Player;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;

public class Villager extends NPC{

	int movementTimer = 0;
	int movementDir = 1;
	
	int i = 0;
	
	Random rand = new Random();

	public Villager(int x, int y, Level level) {
		super(x, y, level);
		
	}
	
	@Override
	public void update(int delta, Play p){
		this.dir = 1;

		Player player = this.scanForPlayer(p);
		
		if (!(player == null)){

			if (player.isTalking){
				this.speak(p);
			}else{
				this.isTalking = false;
			}
		}else{
			this.isTalking = false;
		}
	}
	

}
