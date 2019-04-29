/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.map;

import Interfaces.IMap;

/**
 *
 * @author Peter
 */
public class Map implements IMap{
    
    float[] playerCoordinatesX;
    float[] playerCoordinatesY;
    
    float[] enemyCoordinatesX;
    float[] enemyCoordinatesY;

    public Map() {
        playerCoordinatesX = new float[1];
        playerCoordinatesX[0] = 600;
        playerCoordinatesY = new float[1];
        playerCoordinatesY[0] = 100;
        
        enemyCoordinatesY = new float[1];
        enemyCoordinatesY[0] = 700;
        enemyCoordinatesX = new float[4];
        enemyCoordinatesX[0] = 0;
        enemyCoordinatesX[1] = 400;
        enemyCoordinatesX[2] = 800;
        enemyCoordinatesX[3] = 1200;
    }

    public float[] getPlayerCoordinatesX() {
        return playerCoordinatesX;
    }

    public float[] getPlayerCoordinatesY() {
        return playerCoordinatesY;
    }

    public float[] getEnemyCoordinatesX() {
        return enemyCoordinatesX;
    }

    public float[] getEnemyCoordinatesY() {
        return enemyCoordinatesY;
    }    
}
