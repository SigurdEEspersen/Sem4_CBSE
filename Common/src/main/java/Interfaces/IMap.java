package Interfaces;

/**
 *
 * @author Gruppe 11
 */
public interface IMap {

    public float[] getPlayerCoordinatesX();

    public float[] getPlayerCoordinatesY();

    public float[] getEnemyCoordinatesX();

    public float[] getEnemyCoordinatesY();
    
     public boolean isSpawning();

    public void setSpawn(boolean spawn);
    
    
}
