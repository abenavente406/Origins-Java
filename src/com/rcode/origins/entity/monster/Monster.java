package com.rcode.origins.entity.monster;

import com.rcode.origins.entity.AnimatedEntity;
import com.rcode.origins.entity.Direction;
import com.rcode.origins.entity.Player;
import com.rcode.origins.level.Level;
import com.rcode.origins.states.Play;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class Monster extends AnimatedEntity {

    /**
     * Timer for random movement
     */
    private int movementTimer = 0;
    private int movementTimerMax = 300;
    /**
     * Direction that will be random
     */
    private Direction movementDir;
    /**
     * Counter variable
     */
    private int i = 0;

    private Random rand = new Random();

    public Monster(int x, int y, Level level) {

        super(x, y);

        level.addMonster(this);
        this.attackRange = 25;
    }

    @Override
    public void update(int delta, Play p) {

        // Decreases the cool down timer for attacking
        if (this.getCooldownTimer() > 0) {
            this.coolDownTimer -= delta;
        }

        // If the monster isn't moving, then start a new movement
        if (!isMoving) {
            movementTimer = rand.nextInt(400) + 1;
            movementDir = Direction.values()[rand.nextInt(4)];
        }

        // Sets the real direction to be the random directions
        this.dir = movementDir;

        // Movement variables that will be manipulated
        int dirX = 0, dirY = 0;

        if (movementTimer < movementTimerMax) {
            switch (movementDir) {
                case NORTH:
                    dirY--;
                    break;
                case SOUTH:
                    dirY++;
                    break;
                case WEST:
                    dirX--;
                    break;
                case EAST:
                    dirX++;
                    break;
            }
            movementTimer++;
            isMoving = true;

        } else {

            // If the movement timer exceeds the max, set the timer back to 0
            if (i < 500) {
                i++;
            } else {
                i = 0;
                isMoving = false;
            }
        }

        // The monster scans for a nearby player
        Player player = this.scanForPlayer(p);

        // Sets the new coordinates of the monster
        double newX = getX() + dirX * getSpeed() * delta;
        double newY = getY() + dirY * getSpeed() * delta;

        // If there is a player nearby, move towards the player
        if (!(player == null)) {

            dir = faceEntity(player, this.getX(), this.getY());
            //System.out.println(dir);
            double distX = player.getX() - this.getX();
            double distY = player.getY() - this.getY();
            double distTotal = Math.sqrt(distX * distX + distY * distY);

            // Attack and return (don't move) if the player is within attacking
            // range
            if (Math.abs(distTotal) < this.attackRange) {
                try {
                    this.attack(player);
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                return;
            }

            double dx = (distX / distTotal) * speed * delta;
            double dy = (distY / distTotal) * speed * delta;
            newX = getX() + dx;
            newY = getY() + dy;
        }

        // Move the monster
        move(newX, newY, p);
    }

    /**
     * Kill the monster
     *
     * @param p
     */
    public void die(Play p) {
        // Remove the monster from the world
        p.getLevel().monsters.remove(this);

        // Debug print
        System.out.println(getName() + " was killed!");
    }

}
