package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.Gdx;
import data.Entity;
import data.GameData;
import data.KeyBindings;
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
        float radians2 = entity.getPositionRadians();
        float radians = (float)Math.atan2(Gdx.input.getX(), Gdx.input.getY());
        
        shapex[0] = (float) (entity.getPositionX() + Math.cos(radians2) * 15);
        shapey[0] = (float) (entity.getPositionY() + Math.sin(radians2) * 15);

        shapex[1] = (float) (entity.getPositionX() + Math.cos(radians2 - 4 * 3.1415f / 5) * 15);
        shapey[1] = (float) (entity.getPositionY() + Math.sin(radians2 - 4 * 3.1145f / 5) * 15);

        shapex[2] = (float) (entity.getPositionX() + Math.cos(radians2 + 3.1415f) * 15 * 0.5);
        shapey[2] = (float) (entity.getPositionY() + Math.sin(radians2 + 3.1415f) * 15 * 0.5);

        shapex[3] = (float) (entity.getPositionX() + Math.cos(radians2 + 4 * 3.1415f / 5) * 15);
        shapey[3] = (float) (entity.getPositionY() + Math.sin(radians2 + 4 * 3.1415f / 5) * 15);
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    @Override
    public void execute(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Player.class)) {
            Player playerMovement = entity.getMovement(Player.class);

            playerMovement.setDown(gameData.getKeys().isDown(KeyBindings.DOWN));
            playerMovement.setUp(gameData.getKeys().isDown(KeyBindings.UP));
            playerMovement.setLeft(gameData.getKeys().isDown(KeyBindings.LEFT));
            playerMovement.setRight(gameData.getKeys().isDown(KeyBindings.RIGHT));
            playerMovement.execute(gameData, entity);

                     // System.out.println("Entity har x: " + entity.getPositionX());
                     // System.out.println("Entity har y: " + entity.getPositionY());
            // updateShape(entity);

        }
    }
}
