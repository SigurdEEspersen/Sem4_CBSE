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
    
    void setLife(int i);
    
    boolean isDead();
    
    boolean isHit();
    
    boolean isShooting();
    
    void setShooting(boolean b);
    
}
