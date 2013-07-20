package com.rcode.origins.level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.rcode.origins.Game;
import com.rcode.origins.entity.Entity;
import com.rcode.origins.entity.Player;
import com.rcode.origins.entity.monster.BadassSkeleton;
import com.rcode.origins.entity.monster.Ghost;
import com.rcode.origins.entity.npc.BasicVillager;
import com.rcode.origins.entity.npc.TFSpy;
import com.rcode.origins.item.EnumHandWeaponType;
import com.rcode.origins.item.Item;
import com.rcode.origins.item.ItemWeapon;
import com.rcode.origins.states.Play;

public class Level {

	/** Width and height of the level */
	public int width, height;
	/** Width and height of tiles in the level */
	private int tileWidth = 32, tileHeight = 32;
	/** TiledMap of the level */
	public TiledMap world;

	/** Play state - p */
	private Play p;
	
	/** The current player */
	public static Player PLAYER;

	/** Booleans to store data if tiles are solid or choppable */
	private boolean[][] blocked;
	private boolean[][] choppable;

	/** Stores all monsters that are in the level */
	public List<Entity> monsters = new LinkedList<Entity>();
	/** Stores all npcs that are in the level */
	public List<Entity> npcs = new ArrayList<Entity>();
	/** Stores all items that are on the map */
	public List<Item> itemsOnMap = new ArrayList<Item>();
	/** Exit zones */
	public List<ExitZone> exitZones = new ArrayList<ExitZone>();
	
	
	/**
	 * Create a level
	 * @param path
	 *: File path for the tmx file
	 * @param player
	 * : The player in the level
	 * @param p
	 * : The main game state
	 * @throws SlickException
	 */
	public Level(String path, Player player, Play p) throws SlickException {
		loadLevelFile(path);
		this.p = p;
		PLAYER = player;
		
		initMonsters();
		initVillagers();
		initExitZones();

	}
	
	/**
	 * Initializes the monsters on the map
	 * @throws SlickException
	 */
	private void initMonsters() throws SlickException{
		new Ghost(47, 82, this);
		new Ghost(43, 79, this);
		new Ghost(55, 81, this);
		new Ghost(48, 80, this);
		
		new BadassSkeleton(60, 77, this);
	}

	/**
	 * Inititializes npcs
	 * @throws SlickException
	 */
	private void initVillagers() throws SlickException{
		new TFSpy(40, 34, this);
		
		new BasicVillager(25, 27, this);
		new BasicVillager(33, 54, this);
		new BasicVillager(71, 52, this);
		new BasicVillager(30, 46, this);
		
		ItemWeapon item_1 = new ItemWeapon(EnumHandWeaponType.SWORD, 0, 0, true, this);
		item_1.setX(26);
		item_1.setY(27);
		
		ItemWeapon item_2 = new ItemWeapon(EnumHandWeaponType.AXE, 2, 0, true, this);
		item_2.setX(32);
		item_2.setY(28);
	}
	
	/** Initializes exit zones on the map */
	private void initExitZones() {
		
		new ExitZone(100, 45, 43, 2, 1, this);
		
	}
	
	/**
	 * Loads the tiled map from a file
	 * @param path
	 * : File location of the tmx file
	 */
	private void loadLevelFile(String path) {
		try {
			world = new TiledMap(path);
			this.width = world.getWidth();
			this.height = world.getHeight();

			buildCollisionMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the map and all entities in the map
	 * @param delta
	 * @throws SlickException
	 */
	public void update(int delta) throws SlickException {
		
		if (monsters.size() < 2){
			spawnMonster();
		}
		for (Entity e : monsters) {
			e.update(delta, p);
		}
		for (Entity e : npcs) {
			e.update(delta, p);
		}

	}

	/**
	 * Render the level and all entities to the screen
	 * @param g
	 *: Slick graphics object
	 * @param p
	 *: The main game state
	 * @param cameraX 
	 * : The x co-ordinate of the camera
	 * @param cameraY
	 * : The y co-ordinate of the camera
	 */
	public void render(Graphics g, Play p, double cameraX, double cameraY){

		
		// Calculate the offset to the next tile (needed by TiledMap.render())
		int tileOffsetX = (int) -(cameraX % tileWidth);
		int tileOffsetY = (int) -(cameraY % tileHeight);

		// Calculate the index of the leftmost tile that is being displayed
		int tileIndexX = (int) (cameraX / tileWidth);
		int tileIndexY = (int) (cameraY / tileHeight);

		// Calculate how many tiles need to be rendered from the topleftmost
		int visX = (Game.WIDTH - tileOffsetX) / tileWidth + 1;
		int visY = (Game.HEIGHT - tileOffsetY) / tileHeight + 1;


		// render the grass and pathways
		this.world.render(tileOffsetX, tileOffsetY, tileIndexX, tileIndexY, visX, visY, 0, false);

		// render the trees and homes
		this.world.render(tileOffsetX, tileOffsetY, tileIndexX, tileIndexY, visX, visY, 1, false);
		
		g.translate(-(float) cameraX, -(float) cameraY);

		
		for (Item i: itemsOnMap){
			if (i.isInWorld)
				i.render(g, this);
		}
		
		// render the player
		p.getPlayer().render(p, g);
		p.getPlayer().renderHealth(g);
		
		// Render the monsters
		for (Entity e : monsters) {
			if(isOnCamera(e, cameraX, cameraY)){
				e.render(p, g);
				e.renderHealth(g);
			}
		}
		
		// Render the npcs
		for (Entity e : npcs) {
			if(isOnCamera(e, cameraX, cameraY)){
				e.render(p, g);
			}
		}
		
		for (ExitZone ez: exitZones){
			ez.renderExits(g, cameraX, cameraY);
		}
		
		g.translate((float) cameraX, (float) cameraY);

		//Draw the player's coordinates (for debugging reasons)
		//g.drawString((int) p.getPlayer().getX() + " "
		//		+ (int) p.getPlayer().getY(), 10, 40);
	}


	/**
	 * Initializes the tile map's custom tiles
	 * 1: Solid - s
	 * 2: Choppable - c (bushes)
	 */
	private void buildCollisionMap() {
		
		blocked = new boolean[world.getWidth()][world.getHeight()];
		choppable = new boolean[world.getWidth()][world.getHeight()];
		
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				
				//Initialize solid blocks
				int tileID = world.getTileId(x, y, 1);
				String value = world.getTileProperty(tileID, "solid", "0");
				blocked[y][x] = (value.equals("s") ? true : false);
				String value2 = world.getTileProperty(tileID, "choppable", "0");
				choppable[y][x] = (value2.equals("c") ? true: false);

			}
		}
	}


	/**
	 * Adds monsters to the monsters array
	 * @param e
	 * : Monster to add
	 */
	public void addMonster(Entity e) {
		monsters.add(e);
	}
	
	/**
	 * Adds npcs to the npc array
	 * @param e
	 * : NPC to add
	 */
	public void addNPC(Entity e){
		npcs.add(e);
	}

	/**
	 * Adds items to the itemsOnMap array
	 * @param i
	 * : Item to add
	 */
	public void addItemToMap(Item i){
		itemsOnMap.add(i);
	}
	
	/**
	 * Spawns a monster randomly near the player
	 * @throws SlickException
	 */
	public void spawnMonster() throws SlickException{
		
		Random rand = new Random();
		
		if (rand.nextInt(2) == 0){
			if (rand.nextInt(2) == 0){
				new Ghost((int) (p.getPlayer().getX() / 32) + 12, (int) p.getPlayer().getY() / 32, this);
			}else{
				new Ghost((int) p.getPlayer().getX() / 32 - 12, (int) p.getPlayer().getY() / 32, this);
			}
		}else{
			new BadassSkeleton((int) (p.getPlayer().getX() / 32) + 12, (int) p.getPlayer().getY() / 32, this);
		}
	}
	
	/**
	 * Returns if a block is solid
	 * @param x
	 * : The x point to test
	 * @param y
	 * : The y point to test
	 * @param width
	 * : The width of an entity going to pass
	 * @param height
	 * : The height of an entity going to pass
	 * @return True for a solid block; False for a passable block
	 */
	public boolean isBoxBlocked(double x, double y, int width, int height) {
		// Find the tile each corner of the box sits in
		int atx1 = (int) (x + 20 - (width / 2)) / this.getTileWidth();
		int atx2 = (int) (x + width) / this.getTileWidth();
		int aty1 = (int) (y + 20 - (height / 2)) / this.getTileHeight();
		int aty2 = (int) (y + 15 + (height / 2)) / this.getTileHeight();

		// If the given points are outside the bounds, return blocked
		if (atx1 < 0 || aty1 < 0) {
			return true;
		}

		// Test is these tiles have their "block" property set to "1", return
		// result
		if (this.isTileBlocked(atx1, aty1))
			return true;
		if (this.isTileBlocked(atx1, aty2))
			return true;
		if (this.isTileBlocked(atx2, aty1))
			return true;
		if (this.isTileBlocked(atx2, aty2))
			return true;
		return false;
	}

	/**
	 * Returns if a tile is solid
	 * @param tx
	 * : x point of tile
	 * @param ty
	 * : y point of tile
	 * @return If the location provides a solid tile
	 */
	public boolean isTileBlocked(int tx, int ty) {
		// If outside bounds, return blocked
		if (ty >= blocked.length || tx >= blocked[ty].length)
			return true;

		return blocked[ty][tx];
	}
	/**
	 * Returns if a tile is solid
	 * @param x
	 * : x point of tile
	 * @param y
	 * : y point of tile
	 * @return If the location provides a solid tile
	 */
	public boolean isTileBlocked(double x, double y) {
		// Calculate tile indexes
		int tx = (int) (x / this.getTileWidth());
		int ty = (int) (y / this.getTileHeight());

		// If outside bounds, return blocked
		if (ty >= blocked.length || tx >= blocked[ty].length)
			return true;

		return blocked[ty][tx];
	}

	/**
	 * Returns if an entity is solid
	 * @param entity
	 * : The entity to test
	 * @param x
	 * : x co-ordinate of the entity that is testing the other entity
	 * @param y
	 * : y co-ordinate of the entity that is testing the other entity
	 * @return If there is an entity in the way
	 */
	public boolean isUnitBlocked(Entity entity, double x, double y) {
		// Get values of testing unit edges
		double left = x - entity.width / 2;
		double right = x + entity.width / 2;
		double top = y - entity.height / 2;
		double bottom = y + entity.height / 2;

		// Build all game units into a list for checking
		ArrayList<Entity> allEntities = new ArrayList<Entity>(monsters
				.size());
		allEntities.add(PLAYER);

		for(Entity e: monsters){
			allEntities.add(e);
		}
		for(Entity e: npcs){
			allEntities.add(e);
		}

		// Check each unit
		for (Entity e : allEntities) {
			// Return false if any axes are not colliding (CST) or unit checking
			// itself
			if (left >= e.getX() + e.getPhysWidth() / 2
					|| right <= e.getX() - e.getPhysWidth() / 2
					|| top >= e.getY() + e.getPhysHeight() / 2
					|| bottom <= e.getY() - e.getPhysHeight() / 2
					|| entity.equals(e)) {
				continue;
			}

			// Check possible collisions
			if (left < e.getX() + e.getPhysWidth() / 2
					&& right > e.getX() - e.getPhysWidth() / 2
					&& top < e.getY() + e.getPhysHeight() / 2
					&& bottom > e.getY() - e.getPhysHeight() / 2) {
				//System.out.println(e.getName() + " colliding with "
				//		+ entity.getName() + "!");
				return true;
			}
		}
		return false;
	}

	/**
	 * Calculates distance between entities
	 * @param from
	 * : Entity to start from
	 * @param to
	 * : Entity to end at
	 * @return Distance between the two entities
	 */
	public double distanceToEntity(Entity from, Entity to){

		// Find the distance from the player
		double distX = to.getX() - from.getX();
		double distY = to.getY() - from.getY();
		
		return Math.sqrt(distX * distX + distY * distY);
	}
	
	/**
	 * Calculates distance from an item to an entity
	 * @param from
	 * : Entity to start from
	 * @param to
	 * : Item to end at
	 * @return Distance between entity and item
	 */
	public double distanceToItem(Entity from, Item to){

		// Find the distance from the player
		double distX = to.getX() - from.getX();
		double distY = to.getY() - from.getY();
		
		return Math.sqrt(distX * distX + distY * distY);
	}
	
	/**
	 * Checks if an entity is on camera
	 * @param e
	 * : The entity to check
	 * @param cameraX
	 * : The camera's x co-ordinate
	 * @param cameraY
	 * : The camera's y co-ordinate
	 * @return
	 */
	public boolean isOnCamera(Entity e, double cameraX, double cameraY)
	{

		if (e.getX() + e.avatar.getWidth() / 2 > cameraX
				&& e.getX() - e.avatar.getWidth() / 2 < cameraX + Game.WIDTH
				&& e.getY() + e.avatar.getHeight() / 2 > cameraY
				&& e.getY() - e.avatar.getHeight() / 2 < cameraY + Game.HEIGHT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Gets tile width
	 * @return The width of an individual tile
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Gets tile height
	 * @return The height of an individual tile
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * Gets map height tile count
	 * @return How many tiles there are in the map height-wise
	 */
	public int getMapHeightTiles() {
		return (height / tileHeight);
	}

	/**
	 * Gets map width tile count
	 * @return How many tiles there are in the map width-wise
	 */
	public int getMapWidthTiles() {
		return (width / tileWidth);
	}

	/**
	 * Gets the player in the level
	 * @return The player in the level
	 */
	public Player getPlayer() {
		return PLAYER;
	}
	

}
