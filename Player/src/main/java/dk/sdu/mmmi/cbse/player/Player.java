package dk.sdu.mmmi.cbse.player;

import Interfaces.IEntityMovement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private boolean left, right, up, down;

    private float speed = 20;
    

    public Player() {
      // The file path for the sprite. 
      spritePath = "/player.gif";
     
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

    @Override
    public String getSpritePath() {
        return this.spritePath;
    }

    @Override
    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

      

    @Override
    public void execute(GameData gameData, Entity entity) {
        
        float dt = gameData.getDelta();
    

//        if (down) {
//            entity.setPositionX((float) ((float) entity.getPositionX() - cos(radians) * speed * dt));
//            entity.setPositionY((float) ((float) entity.getPositionY() - sin(radians + 1) * speed * dt));
//
//            System.out.println("DOWN!");
//        }
//
//        if (up) {
//            entity.setPositionX((float) ((float) entity.getPositionX() + cos(radians) * speed * dt));
//            entity.setPositionY((float) ((float) entity.getPositionY() + sin(radians + 1) * speed * dt));
//
//            System.out.println("UP!");
//        }
        // skal ind i en collision klasse
        if (entity.getPositionX() >= (gameData.getDisplayWidth() - 15)) {
            entity.setPositionX(gameData.getDisplayWidth() - 15);
        }

        if (entity.getPositionY() >= (gameData.getDisplayHeight() - 15)) {
            entity.setPositionY(gameData.getDisplayHeight() - 15);
        }

        if (entity.getPositionX() <= 15) {
            entity.setPositionX(15);
        }

        if (entity.getPositionY() <= (15)) {
            entity.setPositionY(15);
        }

        //Speed
        if (up) {
            entity.setPositionX((float) (cos(radians) * speed * dt + entity.getPositionX()));
            entity.setPositionY((float) (sin(radians) * speed * dt + entity.getPositionY()));
        }

        if (down) {
            entity.setPositionX((float) (cos(radians) * -speed * dt + entity.getPositionX()));
            entity.setPositionY((float) (sin(radians) * -speed * dt + entity.getPositionY()));
        }
    }
}
