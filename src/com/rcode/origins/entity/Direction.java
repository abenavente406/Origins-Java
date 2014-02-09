package com.rcode.origins.entity;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static Random rand = new Random();

    /**
     * @return Gets a random direction
     */
    public static Direction getRandDirection() {
        return Direction.values()[rand.nextInt(Direction.values().length)];
    }
}
