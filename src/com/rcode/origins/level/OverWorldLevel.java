package com.rcode.origins.level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.ambient.Rabbit;
import com.rcode.origins.entity.monster.Ghost;
import com.rcode.origins.entity.npc.BasicVillager;
import com.rcode.origins.entity.npc.QuestGiver001;
import com.rcode.origins.entity.npc.QuestGiver002;
import com.rcode.origins.entity.npc.TFSpy;
import com.rcode.origins.entity.npc.TownGuard;
import com.rcode.origins.item.EnumSwordMaterial;
import com.rcode.origins.item.Item;
import com.rcode.origins.item.ItemSword;
import com.rcode.origins.states.Play;

public class OverWorldLevel extends Level {

	/** Stores all monsters that are in the level */
	public List<Entity> monsters = new LinkedList<Entity>();
	/** Stores all npcs that are in the level */
	public List<Entity> npcs = new ArrayList<Entity>();
	/** Stores all ambient creatures */
	public List<Entity> ambientEntities = new ArrayList<Entity>();
	/** Stores all items that are on the map */
	public List<Item> itemsOnMap = new ArrayList<Item>();
	/** Exit zones */
	public List<ExitZone> exitZones = new ArrayList<ExitZone>();

	/**
	 * Create a level
	 * 
	 * @param path
	 *            : File path for the tmx file
	 * @param player
	 *            : The player in the level
	 * @param p
	 *            : The main game state
	 * @throws SlickException
	 */
	public OverWorldLevel(String path, Player player, Play p)
			throws SlickException {
		super(player, p);
		loadLevelFile(path);
		this.p = p;
		PLAYER = player;

		initMonsters();
		initVillagers();
		initExitZones();

	}

	/**
	 * Initializes the monsters on the map
	 * 
	 * @throws SlickException
	 */
	private void initMonsters() throws SlickException {
		new Ghost(58, 30, this);
		new Ghost(66, 35, this);
		new Ghost(55, 48, this);
		new Ghost(51, 24, this);

		new Rabbit(32, 29, this);
	}

	/**
	 * Inititializes npcs
	 * 
	 * @throws SlickException
	 */
	private void initVillagers() throws SlickException {
		new TFSpy(40, 34, this);

		new BasicVillager(25, 28, this);
		new TownGuard(28, 56, this);
		new QuestGiver001(49, 48, this);
		new QuestGiver002(24, 38, this);
		new BasicVillager(30, 46, this);

		ItemSword sword001 = new ItemSword(EnumSwordMaterial.WOOD, 0, 0, true,
				this);
		sword001.setX(60);
		sword001.setY(24);
	}

	/** Initializes exit zones on the map */
	private void initExitZones() {
		
	}
}
