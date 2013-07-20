package com.rcode.origins.entity.behavior;

public enum EnumBehaviors {

	RUNAWAY(300), ATTACK(-1);

	private int duration; // -1 if the behavior has no duration

	private EnumBehaviors(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}
}
