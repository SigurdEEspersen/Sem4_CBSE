package dk.sdu.mmmi.cbse.player;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Peter
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class PlayerControlSystem implements IControlService {

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        float radians = entity.getPositionRadians();

        shapex[0] = (float) (500 + Math.cos(radians) * 15);
        shapey[0] = (float) (500 + Math.sin(radians) * 15);

        shapex[1] = (float) (500 + Math.cos(radians - 4 * 3.1415f / 5) * 15);
        shapey[1] = (float) (500 + Math.sin(radians - 4 * 3.1145f / 5) * 15);

        shapex[2] = (float) (500 + Math.cos(radians + 3.1415f) * 15 * 0.5);
        shapey[2] = (float) (500 + Math.sin(radians + 3.1415f) * 15 * 0.5);

        shapex[3] = (float) (500 + Math.cos(radians + 4 * 3.1415f / 5) * 15);
        shapey[3] = (float) (500 + Math.sin(radians + 4 * 3.1415f / 5) * 15);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            updateShape(player);

        }
    }

}
