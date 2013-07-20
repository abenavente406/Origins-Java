package com.rcode.origins.item;

import com.rcode.origins.level.Level;

public class ItemSword extends Item {

	/** The amount of damage the weapon causes */
	private int minDamage, maxDamage;

	private EnumSwordMaterial material;

	/**
	 * Creates a weapon
	 * 
	 * @param id
	 *            : The weapon ID
	 * @param weaponType
	 *            : The type of weapon (sword, axe, mace)
	 */
	public ItemSword(EnumSwordMaterial material, int x, int y,
			boolean isInWorld, Level level) {
		super(isInWorld, level);
		this.minDamage = 20;
		this.maxDamage = 30;
		this.material = material;
		
		this.name = material.toString() + " Sword";
	}

	/**
	 * Gets the minimum amount of damage
	 * 
	 * @return The min damage
	 */
	public int getMinDamage() {
		return minDamage;
	}

	/**
	 * Gets the maximum amount of damage
	 * 
	 * @return The max damage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * Gets the cool down
	 * 
	 * @return the cool down max constant
	 */
	public int getCoolDown() {
		return material.getCoolDown();
	}

	/**
	 * Gets the name of the weapon type
	 * 
	 * @return Sword, mace, or axe
	 */
	public String getMaterial() {
		return material.toString();
	}

}
