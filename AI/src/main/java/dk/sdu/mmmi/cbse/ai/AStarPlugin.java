/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.ai;

import data.GameData;
import data.Node;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;

/**
 *
 * @author finch
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})

public class AStarPlugin implements IPluginService {

    private AStar aStar;

    @Override
    public void start(GameData gameData, World world) {
        aStar = new AStar(1200, 700, new Node(0, 0), new Node(5, 5));
        world.addAI(aStar);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAI(aStar);
    }

}
