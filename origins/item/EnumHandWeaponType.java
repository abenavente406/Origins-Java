package com.rcode.origins.item;

public enum EnumHandWeaponType {

	SWORD(10, 30, 400),
	MACE(20, 40, 600),
	AXE(30, 40, 800);
	
	/** Min amount of damage */
	private int minDamage = 0;
	
	/** Max amount of damage */
	private int maxDamage = 0;
	
	/** Cool down maximum */
	private int coolDown = 0;
	
	/**
	 * Initializes the weapon type
	 * @param minDamage
	 * @param maxDamage
	 * @param coolDown
	 */
	private EnumHandWeaponType(int minDamage, int maxDamage, int coolDown){
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.coolDown = coolDown;
	}
	
	/**
	 * Gets the minimum amount of damage for the weapon
	 * @return The min amount of damage
	 */
	public int getMinDamage(){
		return minDamage;
	}
	
	/**
	 * Gets the maximum amount of damage for the weapon
	 * @return The max amount of damage
	 */
	public int getMaxDamage(){
		return maxDamage;
	}
	
	/**
	 * Gets the cooldown that the sword cools at
	 * @return The cooldown
	 */
	public int getCoolDown(){
		return coolDown;
	}
}
