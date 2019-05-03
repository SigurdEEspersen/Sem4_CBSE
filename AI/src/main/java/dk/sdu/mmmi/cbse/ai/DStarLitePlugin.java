/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.ai;

import data.GameData;
import data.World;
import services.IPluginService;

/**
 *
 * @author finch
 */
public class DStarLitePlugin implements IPluginService{

    private DStarLite dStar;
    
    @Override
    public void start(GameData gameData, World world) {
        dStar = new DStarLite();
        world.addAI(dStar);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAI(dStar);

    }
    
}
