package dk.sdu.mmmi.cbse.enemy;

import Interfaces.IEntityMovement;
import data.Entity;
import data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
        float radians = entity.getPositionRadians();
        float dt = gameData.getDelta();

        if (up) {
            entity.setPositionX((float) ((float) entity.getPositionX() + cos(radians) * speed * dt));
            entity.setPositionY((float) ((float) entity.getPositionY() + sin(radians + 1) * speed * dt));
        }
        
        // Speed
        if (up ) {
            entity.setPositionX((float) (cos(entity.getPositionRadians()) * speed * dt + entity.getPositionX()));
            entity.setPositionY((float) (sin(entity.getPositionRadians()) * speed * dt + entity.getPositionY()));
        }
    }

}
