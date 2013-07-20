package com.rcode.origins.item;

public enum EnumSwordMaterial {

	WOOD(10, 20, 200, "WOOD"), IRON(20, 40, 200, "IRON"), MASTER(30, 60, 150, "MASTER"), FIRE(20, 40, 200, "FIRE");

	private int minDamage, maxDamage;

	private int coolDown;

	private EnumSwordMaterial(int minDamage, int maxDamage, int coolDown, String name) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.coolDown = coolDown;
	}

	/**
	 * Gets the minimum amount of damage for the weapon
	 * 
	 * @return The min amount of damage
	 */
	public int getMinDamage() {
		return minDamage;
	}

	/**
	 * Gets the maximum amount of damage for the weapon
	 * 
	 * @return The max amount of damage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * Gets the cooldown that the sword cools at
	 * 
	 * @return The cooldown
	 */
	public int getCoolDown() {
		return coolDown;
	}

	public String getName() {
		return this.getName();
	}
}
