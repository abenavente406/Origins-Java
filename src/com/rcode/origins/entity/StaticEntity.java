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

    @Override
    public void render(Play p, Graphics g) {
        drawShadow(g);

        switch (dir) {
            case NORTH:
                avatarUp.draw((float)x, (float)y);
                break;
            case EAST:
                avatarRight.draw((float)x, (float)y);
                break;
            case SOUTH:
                avatarDown.draw((float)x, (float)y);
                break;
            case WEST:
                avatarLeft.draw((float)x, (float)y);
                break;
        }
    }
}
