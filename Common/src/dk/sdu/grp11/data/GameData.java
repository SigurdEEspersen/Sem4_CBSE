/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.grp11.data;

/**
 *
 * @author BenPaxIndustries
 */
public class GameData {
    private float delta; 
    private int screenWidth; 
    private int screenHeight; 
    private final KeyBindings keys = new KeyBindings();
    
    
    
    public KeyBindings getKeys() {
        return keys;
    }
    
    public void setDelta(float delta) {
        this.delta = delta;
    }
    
    public float getDelta() {
        return delta;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
    
    
    
}
