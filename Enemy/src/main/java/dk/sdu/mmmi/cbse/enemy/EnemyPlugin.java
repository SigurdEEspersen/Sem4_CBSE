package dk.sdu.mmmi.cbse.enemy;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;
import Interfaces.ICombatEntity;
import Interfaces.IMap;
import java.util.Random;

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
        
        
        for (int i = 0; i < 3; i++) {
            enemy = createEnemy(gameData, world);
            enemy.addCombat((ICombatEntity) enemy);
            world.addEntity(enemy);
        }
        
    }

    private Enemy createEnemy(GameData gameData, World world) {

        float speed = 30 + (float) Math.random() * (150 - 30);
        IMap map = world.getMapArray().get(0);
        Random r = new Random();
        float x = map.getEnemyCoordinatesX()[r.nextInt(3)];
        float y = map.getEnemyCoordinatesY()[0];
        float radians = 3.1415f / 2;

        float[] colour = new float[4];
        colour[0] = 1.0f;
        colour[1] = 1.0f;
        colour[2] = 1.0f;
        colour[3] = 1.0f;

        Enemy enemy = new Enemy();

        enemy.setPositionX(x);
        enemy.setPositionY(y);
        enemy.setSpeed(speed);
        enemy.setRadians(radians);
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
