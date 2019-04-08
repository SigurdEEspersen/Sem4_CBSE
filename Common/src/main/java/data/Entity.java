/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.Image;
import java.util.UUID;

/**
 *
 * @author Peter
 */
public class Entity {
    
    
    private final UUID ID = UUID.randomUUID();



    private float positionX, positionY, positionRadians;
    private float[] shapeX, shapeY;
    private Image[] sprite;
    
    
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

    public Image[] getSprite() {
        return sprite;
    }

    public void setSprite(Image[] sprite) {
        this.sprite = sprite;
    }
    
    public String getID(){
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

    public float getPositionRadians() {
        return positionRadians;
    }

    public void setPositionRadians(float positionRadians) {
        this.positionRadians = positionRadians;
    }
    
   
    
}
