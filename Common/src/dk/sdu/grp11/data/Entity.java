/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.grp11.data;

import java.awt.Image;
import java.util.UUID;

/**
 *
 * @author BenPaxIndustries
 */
public class Entity {
    private final UUID ID = UUID.randomUUID();
    
    private Image sprite; 
    
    private float[] dy, dx;

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public float[] getDy() {
        return dy;
    }

    public void setDy(float[] dy) {
        this.dy = dy;
    }

    public float[] getDx() {
        return dx;
    }

    public void setDx(float[] dx) {
        this.dx = dx;
    }
    
    public String getID() {
        return ID.toString();
    }
    
    
    
    
}
