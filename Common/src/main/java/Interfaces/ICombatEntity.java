package Interfaces;

import data.Entity;
import data.GameData;

/**
 *
 * @author Gruppe 11
 */
public interface ICombatEntity {
    
    void execute (GameData gameData, Entity entity);
    
    int getLife();
    
    boolean isDead();
    
    boolean isHit();
    
    boolean isShooting();
    
    void setShooting(boolean b);
    
}
