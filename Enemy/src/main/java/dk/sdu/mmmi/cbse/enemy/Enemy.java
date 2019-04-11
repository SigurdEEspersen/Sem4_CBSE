package dk.sdu.mmmi.cbse.enemy;

import Interfaces.IEntityMovement;
import data.Entity;
import data.GameData;

/**
 *
 * @author Sigurd E. Espersen
 */
public class Enemy extends Entity implements IEntityMovement{

    private boolean up;
    private float speed;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public void execute(GameData gameData, Entity entity) {
        System.out.println("Enemy movement test");
    }

}
