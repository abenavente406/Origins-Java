package com.rcode.origins.inventory;

import com.rcode.origins.entity.Player;
import com.rcode.origins.item.Item;
import com.rcode.origins.item.ItemWeapon;

public class InventoryPlayer {

	private Item[] itemsInInv = new Item[36];
	
	private Player player;
	
	private int availableIndex = 0;
	
	public InventoryPlayer(Player player){
		this.player = player;
	}
	
	public void addWeaponToInventory(ItemWeapon item){
		itemsInInv[availableIndex] = item;
		availableIndex++;
	}
	
	public void removeFromInventory(int index){
		itemsInInv[availableIndex] = null;
		availableIndex--;
	}
	
	public void equipItem(int index){
		if (itemsInInv[index] != null){
				player.equipped = (ItemWeapon)itemsInInv[index];
				System.out.println(itemsInInv[index].getName() + " was equipped.");
		}else{
			System.out.println("There is no equipment here.");
		}
	}
	
	public void update(){
		
	}
	
	public Item[] getItems(){
		return itemsInInv;
	}
	
	public int getAvailableIndex(){
		return availableIndex;
	}
}
