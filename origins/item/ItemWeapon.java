package com.rcode.origins.item;

import com.rcode.origins.level.Level;

public class ItemWeapon extends Item{

	/** The amount of damage the weapon causes */
	private int minDamage, maxDamage;
	
	/** Weapon type from enumeration */
	private EnumHandWeaponType weaponType;
	
	/**
	 * Creates a weapon
	 * @param id
	 * : The weapon ID
	 * @param weaponType
	 * : The type of weapon (sword, axe, mace)
	 */
	public ItemWeapon(EnumHandWeaponType weaponType, int x, int y, boolean isInWorld, Level level) {
		super(isInWorld, level);
		this.weaponType = weaponType;
		this.minDamage = weaponType.getMinDamage();
		this.maxDamage = weaponType.getMaxDamage();
		this.name = weaponType.name();
		this.setIcon(x, y);
	}
	
	/**
	 * Gets the minimum amount of damage
	 * @return The min damage
	 */
	public int getMinDamage(){
		return minDamage;
	}
	
	/**
	 * Gets the maximum amount of damage
	 * @return The max damage
	 */
	public int getMaxDamage(){
		return maxDamage;
	}
	
	/**
	 * Gets the cool down
	 * @return the cool down max constant
	 */
	public int getCoolDown(){
		return weaponType.getCoolDown();
	}
	/**
	 * Gets the name of the weapon type
	 * @return Sword, mace, or axe
	 */
	public String getWeaponType(){
		return weaponType.toString();
	}

}
