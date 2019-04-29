/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.map;

import data.GameData;
import data.World;
import services.IPluginService;

/**
 *
 * @author Peter
 */
public class MapPlugin implements IPluginService{
    private Map map;
    
    @Override
    public void start(GameData gameData, World world) {
        map = new Map();
        System.out.println("test af map " + map.getEnemyCoordinatesX());
        world.addMap(map);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMap(map);
    }
    
}
