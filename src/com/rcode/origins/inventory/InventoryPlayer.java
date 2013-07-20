package com.rcode.origins.inventory;

import org.newdawn.slick.SlickException;

import com.rcode.origins.entity.Player;
import com.rcode.origins.item.EnumSwordMaterial;
import com.rcode.origins.item.Item;
import com.rcode.origins.item.ItemSword;

public class InventoryPlayer {

	/** Max amount of items in the inventory */
	private final int MAXINV = 36;

	/** Items in the player's inventory (Max 36) */
	private Item[] itemsInInv = new Item[MAXINV];

	/** The player in which this inventory belongs to */
	private Player player;

	/** The next index that is available to add an item to */
	private int availableIndex = 0;

	/**
	 * Initialize the inventory
	 * 
	 * @param player
	 *            : The owner of the inventory
	 */
	public InventoryPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Adds a weapon to the player's inventory
	 * 
	 * @param item
	 *            : Item to add
	 */
	public void addWeaponToInventory(ItemSword item) {
		itemsInInv[availableIndex] = item;
		availableIndex++;
	}
	
	public void popInventory() {
		itemsInInv[availableIndex] = null;		
		availableIndex--;
	}
	
	/**
	 * Removes the last added item from the inventory
	 */
	public void removeFromInventory(int index) {
		itemsInInv[index] = null;
		for (int i = index + 1; i < getAvailableIndex(); i++) {
			itemsInInv[i - 1] = itemsInInv[i];
		}
		availableIndex--;
	}

	/**
	 * Equips an item by index
	 * 
	 * @param index
	 *            : The index of the item
	 */
	public void equipItem(int index) {
		if (itemsInInv[index] != null) {
			player.setEquipped((ItemSword) itemsInInv[index]);
			System.out.println(itemsInInv[index].getName() + " was equipped.");
		} else {
			System.out.println("There is no equipment here.");
		}
	}

	public void update() {
	}

	/**
	 * Gets the items array
	 * 
	 * @return Items
	 */
	public Item[] getItems() {
		return itemsInInv;
	}

	/**
	 * Gets the next available index
	 * 
	 * @return Available index
	 */
	public int getAvailableIndex() {
		return availableIndex;
	}
	
	public static void main(String[] args){
		try {
			InventoryPlayer inventory = new InventoryPlayer(new Player(0, 0));
			for (int i = 0; i < 5; i++) {
				inventory.addWeaponToInventory(new ItemSword(EnumSwordMaterial.WOOD, 0, 0, false, null));
			}
			inventory.removeFromInventory(2);
			System.out.println("Done.");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
