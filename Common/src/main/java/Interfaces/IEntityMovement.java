/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import data.Entity;
import data.GameData;

/**
 *
 * @author Peter
 */
public interface IEntityMovement {
    
    void execute (GameData gameData, Entity entity);
    
}
