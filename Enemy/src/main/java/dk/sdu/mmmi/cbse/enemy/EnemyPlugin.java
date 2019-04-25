package dk.sdu.mmmi.cbse.enemy;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;
import Interfaces.ICombatEntity;
import java.util.Random;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})
public class EnemyPlugin implements IPluginService {


    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        for(int i = 0; i < 100; i++){
        Entity enemy = createEnemyShip(gameData);
        enemy.addCombat((ICombatEntity) enemy);
        world.addEntity(enemy);
        }
        
        
    }

    private Enemy createEnemyShip(GameData gameData) {
        Random r = new Random();
        float speed = 30 + r.nextFloat() * (150 - 30);
        float rotationSpeed = 5;
//        float x = gameData.getDisplayWidth() / 2;
        float x = 1 + r.nextFloat() * ( gameData.getDisplayWidth() - 1 );
        float y = gameData.getDisplayHeight();

//        
//        float x = r.nextFloat();
//        float y = r.nextFloat();
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
        for(Entity e : world.getEntities(Enemy.class)){
            world.removeEntity(e);
        }
        
    }

}
