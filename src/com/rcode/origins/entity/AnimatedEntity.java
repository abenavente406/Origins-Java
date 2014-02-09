package com.rcode.origins.entity;

import com.rcode.origins.states.Play;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public abstract class AnimatedEntity extends Entity {

    /** The image arrays for animations */
    protected Image[] _movementUp, _movementDown, _movementLeft, _movementRight;
    protected Image[] _attackUp, _attackDown, _attackLeft, _attackRight;
    protected Image[] _death;


    /** Entity image */
    protected Animation movementUp, movementDown, movementLeft, movementRight;
    protected Animation attackUp, attackDown, attackLeft, attackRight;

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
                    this.movementRight.setAutoUpdate(true);
                    movementRight.draw((float) x, (float) y);
                } else {
                    this.avatarRight.draw((float) x, (float) y);
                }
                break;
            case SOUTH:
                if (this.isMoving) {
                    this.movementDown.setAutoUpdate(true);
                    movementDown.draw((float) x, (float) y);
                } else {
                    this.avatarDown.draw((float) x, (float) y);
                }
                break;
            case WEST:
                if (this.isMoving) {
                    this.movementLeft.setAutoUpdate(true);
                    movementLeft.draw((float) x, (float) y);
                } else {
                    this.avatarLeft.draw((float) x, (float) y);

                }
                break;
        }
    }

    /**
     * Creates the image array for animation with 3 frames
     *
     * @param x
     *            : The starting x tile on the spritesheet
     * @param y
     *            : The starting y tile on the spritesheet
     */
    public void setAnimation(int x, int y) {
        setAnimation(x, y, 3, playerSheet);
    }

    /**
     * Creates the image array for animation with a custom amount of frames
     * *Only 1-7
     *
     * @param x
     *            : The starting x tile on the spritesheet
     * @param y
     *            : The starting y tile on the spritesheet
     * @param frames
     * 			  : The number of frames in the animation
     */
    public void setAnimation(int x, int y, int frames, SpriteSheet playerSheet) {

        _movementDown = new Image[frames];
        _movementLeft = new Image[frames];
        _movementRight = new Image[frames];
        _movementUp = new Image[frames];

        for (int i = 0; i < frames; i++) {
            _movementDown[i] = playerSheet.getSubImage(x + i, y);
            _movementLeft[i] = playerSheet.getSubImage(x + i, y + 1);
            _movementRight[i] = playerSheet.getSubImage(x + i, y + 2);
            _movementUp[i] = playerSheet.getSubImage(x + i, y + 3);
        }

        duration = new int[frames];

        for (int i = 0; i < frames; i++){
            duration[i] = 150;
        }

        setAvatars(x, y);

        this.movementUp = new Animation(_movementUp, duration);
        this.movementDown = new Animation(_movementDown, duration);
        this.movementLeft = new Animation(_movementLeft, duration);
        this.movementRight = new Animation(_movementRight, duration);

    }
}
