package com.rcode.origins.quests;

public class Quest {

	// The player has to find his sword
	public static final Quest Quest001 = new Quest("Hello");
	// The player has to talk to a grandma
	public static final Quest Quest002 = new Quest("Grammy!");

	/** If the quest is completed */
	public boolean isCompleted;

	/** Name of the quest */
	protected String name;

	public Quest(String name) {
		this.name = name;
	}

	/** Starts the quest */
	public void start() {
		this.isCompleted = false;
	}

	/** Stops the quest */
	public void stop() {
		this.isCompleted = true;
	}

}
