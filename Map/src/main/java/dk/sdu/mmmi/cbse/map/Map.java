package dk.sdu.mmmi.cbse.map;

import Interfaces.IMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Gruppe 11
 */
public class Map implements IMap {

    float[] playerCoordinatesX;
    float[] playerCoordinatesY;

    float[] enemyCoordinatesX;
    float[] enemyCoordinatesY;

    private volatile boolean spawn;

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

        startTimers();

    }

    private void startTimers() {

        Runnable spawnEnemy = new Runnable() {
            public void run() {
                spawn = true;
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(spawnEnemy, 3, 3, TimeUnit.SECONDS);

    }

    @Override
    public float[] getPlayerCoordinatesX() {
        return playerCoordinatesX;
    }

    @Override
    public float[] getPlayerCoordinatesY() {
        return playerCoordinatesY;
    }

    @Override
    public float[] getEnemyCoordinatesX() {
        return enemyCoordinatesX;
    }

    @Override
    public float[] getEnemyCoordinatesY() {
        return enemyCoordinatesY;
    }

    @Override
    public synchronized boolean isSpawning() {
        return spawn;
    }

    @Override
    public synchronized void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

}
