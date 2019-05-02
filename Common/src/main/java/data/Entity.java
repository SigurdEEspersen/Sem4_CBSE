package data;

import Interfaces.IShooter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import Interfaces.ICombatEntity;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Gruppe 11
 */
public class Entity {

    private final UUID ID = UUID.randomUUID();

    private float positionX, positionY, positionRadians;
    private float[] shapeX, shapeY;
    protected Sprite sprite;
    protected String spritePath;
    private Map<Class, ICombatEntity> movingParts;
    private Map<Class, IShooter> weaponParts;
    protected float playerX, playerY;
    protected float playerScale;
    private float radians;

    public Entity() {
        movingParts = new ConcurrentHashMap<>();
        radians = (float) Math.PI / 2;
    }

    public <E extends IShooter> E getWeapons(Class shootClass) {
        return (E) weaponParts.get(shootClass);
    }

    public void addWeapons(IShooter shoot) {
        this.weaponParts.put(shoot.getClass(), shoot);
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

    public float getPlayerScale() {
        return playerScale;
    }

    public void setPlayerScale(float scale) {
        this.playerScale = scale;
    }

}
