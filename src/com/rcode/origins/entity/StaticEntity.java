package com.rcode.origins.entity;

import com.rcode.origins.states.Play;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class StaticEntity extends Entity {

    /**
     * Create the entity
     *
     * @param x : The x tile location
     * @param y
     */
    public StaticEntity(int x, int y) {
        super(x, y);
    }

    @Override
    public abstract void update(int delta, Play p);

    public void render(Graphics g, int delta) {

        drawShadow(g);

        switch (dir) {
            case NORTH:
                break;
            case EAST:
                break;
            case SOUTH:
                break;
            case WEST:
                break;
        }
    }
}
