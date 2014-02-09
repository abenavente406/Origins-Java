package com.rcode.origins.entity;

import com.rcode.origins.states.Play;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class AnimatedEntity extends Entity {

    /**
     * Create the entity
     *
     * @param x The x location (tile scale)
     * @param y The y location (tile scale)
     */
    public AnimatedEntity(int x, int y) {
        super(x, y);
    }

    @Override
    public abstract void update(int delta, Play p);

    @Override
    public void render(Play p, Graphics g) {

        this.movementDown.setAutoUpdate(false);
        this.movementUp.setAutoUpdate(false);
        this.movementLeft.setAutoUpdate(false);
        this.movementRight.setAutoUpdate(false);

        drawShadow(g);

        // Based on direction, draw the entity's animation
        switch (dir) {
            case NORTH:
                if (this.isMoving) {
                    this.movementUp.setAutoUpdate(true);
                    movementUp.draw((float) x, (float) y);
                } else {
                    this.avatarUp.draw((float) x, (float) y);
                }
                break;
            case EAST:
                if (this.isMoving) {
                    this.movementDown.setAutoUpdate(true);
                    movementDown.draw((float) x, (float) y);
                } else {
                    this.avatarDown.draw((float) x, (float) y);
                }
                break;
            case SOUTH:
                if (this.isMoving) {
                    this.movementLeft.setAutoUpdate(true);
                    movementLeft.draw((float) x, (float) y);
                } else {
                    this.avatarLeft.draw((float) x, (float) y);

                }
                break;
            case WEST:
                if (this.isMoving) {
                    this.movementRight.setAutoUpdate(true);
                    movementRight.draw((float) x, (float) y);
                } else {
                    this.avatarRight.draw((float) x, (float) y);
                }
                break;
        }
    }
}
