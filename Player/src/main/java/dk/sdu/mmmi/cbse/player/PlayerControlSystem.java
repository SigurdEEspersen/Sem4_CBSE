package dk.sdu.mmmi.cbse.player;

import data.Entity;
import data.GameData;
import data.KeyBindings;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class PlayerControlSystem implements IControlService {

    private void updateShape(Entity entity) {
//        float[] shapex = new float[4];
//        float[] shapey = new float[4];
//        float radians = entity.getRadians();
//
//        shapex[0] = (float) (entity.getPositionX() + Math.cos(radians) * 15);
//        shapey[0] = (float) (entity.getPositionY() + Math.sin(radians) * 15);
//
//        shapex[1] = (float) (entity.getPositionX() + Math.cos(radians - 4 * 3.1415f / 5) * 15);
//        shapey[1] = (float) (entity.getPositionY() + Math.sin(radians - 4 * 3.1145f / 5) * 15);
//
//        shapex[2] = (float) (entity.getPositionX() + Math.cos(radians + 3.1415f) * 15 * 0.5);
//        shapey[2] = (float) (entity.getPositionY() + Math.sin(radians + 3.1415f) * 15 * 0.5);
//
//        shapex[3] = (float) (entity.getPositionX() + Math.cos(radians + 4 * 3.1415f / 5) * 15);
//        shapey[3] = (float) (entity.getPositionY() + Math.sin(radians + 4 * 3.1415f / 5) * 15);
//
//        entity.setShapeX(shapex);
//        entity.setShapeY(shapey);
    }

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Player playerCombat = entity.getCombat(Player.class);

            playerCombat.setDown(gameData.getKeys().isDown(KeyBindings.DOWN));
            playerCombat.setUp(gameData.getKeys().isDown(KeyBindings.UP));
            playerCombat.setLeft(gameData.getKeys().isDown(KeyBindings.LEFT));
            playerCombat.setRight(gameData.getKeys().isDown(KeyBindings.RIGHT));
            playerCombat.setShooting(gameData.getKeys().isDown(KeyBindings.SPACE));

            playerCombat.execute(gameData, entity);

            updateShape(entity);

        }
    }
}
