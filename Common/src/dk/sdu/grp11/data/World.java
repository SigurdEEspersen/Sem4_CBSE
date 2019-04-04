/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.grp11.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author BenPaxIndustries
 */
public class World {
    
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }
    
    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }
    
    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Collection <Entity> getEntities(){
        return entityMap.values();
       
    }
    
    
}
