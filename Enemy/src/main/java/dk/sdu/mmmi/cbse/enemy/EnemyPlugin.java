package dk.sdu.mmmi.cbse.enemy;

import Interfaces.IEntityMovement;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})
public class EnemyPlugin implements IPluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        enemy.addMovement((IEntityMovement) enemy);
        world.addEntity(enemy);
    }

    private Enemy createEnemyShip(GameData gameData) {

        float speed = 100;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight();
        float radians = 3.1415f / 2;

        float[] colour = new float[4];
        colour[0] = 1.0f;
        colour[1] = 1.0f;
        colour[2] = 1.0f;
        colour[3] = 1.0f;

        Enemy enemyShip = new Enemy();

        enemyShip.setPositionX(x);
        enemyShip.setPositionY(y);
        enemyShip.setSpeed(speed);
        enemyShip.setPositionRadians(radians);
        
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
