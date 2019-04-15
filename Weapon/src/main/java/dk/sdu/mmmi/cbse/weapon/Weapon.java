/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weapon;

import data.Entity;
import Interfaces.IShooter;
import data.GameData;
import data.World;

/**
 *
 * @author Sigurd E. Espersen
 */
public class Weapon extends Entity implements IShooter {
    private boolean shoot;
    private float speed;

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    

    @Override
    public void execute(GameData gd, Entity entity) {
//        float radians = entity.getPositionRadians();
//        float dt = gameData.getDelta();
        
    }
    
}
