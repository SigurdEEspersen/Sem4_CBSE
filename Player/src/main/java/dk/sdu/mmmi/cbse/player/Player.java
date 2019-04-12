package dk.sdu.mmmi.cbse.player;

import Interfaces.IEntityMovement;
import data.Entity;
import data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Peter
 */
public class Player extends Entity implements IEntityMovement {

    // movement
    private boolean left, right, up, down;
    private float speed = 20;

    @Override
    public void execute(GameData gameData, Entity entity) {
        float x = entity.getPositionX();
        float y = entity.getPositionY();
        float radians = entity.getPositionRadians();
        float dt = gameData.getDelta();

        if (down) {
            entity.setPositionX((float) ((float) entity.getPositionX() + cos(radians + 1) * speed * dt));
            System.out.println("DOWN!");
        }

        if (up) {
            entity.setPositionX((float) ((float) entity.getPositionX() + cos(radians) * speed * dt));
            System.out.println("UP!");
        }

        entity.setPositionX(entity.getPositionX() * dt);

        if (entity.getPositionX() > gameData.getDisplayWidth()) {
            entity.setPositionX(gameData.getDisplayWidth());
        }

        entity.setPositionY(entity.getPositionY() * dt);

        if (entity.getPositionY() > gameData.getDisplayHeight()) {
            entity.setPositionY(gameData.getDisplayHeight());

        }

        // turning
        if (left) {
            entity.setPositionRadians(entity.getPositionRadians() + 5 * dt);
        }
        if (right) {
            entity.setPositionRadians(entity.getPositionRadians() - 5 * dt);
        }

        // Speed
        if (up || down) {
            entity.setPositionX((float) (cos(entity.getPositionRadians()) * speed * dt + entity.getPositionX()));
            entity.setPositionY((float) (sin(entity.getPositionRadians()) * speed * dt + entity.getPositionY()));
        }

    }
    
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
