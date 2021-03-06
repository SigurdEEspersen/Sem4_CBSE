package data;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import Interfaces.ICombatEntity;

/**
 *
 * @author Gruppe 11
 */
public class Entity {

    private final UUID ID = UUID.randomUUID();
    private float positionX, positionY, positionRadians;
    private float[] shapeX, shapeY;
    protected String spritePath;
    private Map<Class, ICombatEntity> movingParts;
    protected float playerX, playerY;
    private float radians, radius;

    public Entity() {
        movingParts = new ConcurrentHashMap<>();
        radians = (float) Math.PI / 2;
    }

    public void addCombat(ICombatEntity movement) {
        movingParts.put(movement.getClass(), movement);
    }

    public <E extends ICombatEntity> E getCombat(Class movementClass) {
        return (E) movingParts.get(movementClass);
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getID() {
        return ID.toString();
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getPlayerX() {
        return playerX;
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}