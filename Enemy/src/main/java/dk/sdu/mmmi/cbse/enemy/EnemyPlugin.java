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
        
        
//        for (int i = 0; i < 3; i++) {
//            enemy = createEnemy(gameData, world);
//            enemy.addCombat((ICombatEntity) enemy);
//            world.addEntity(enemy);
//        }
        
    }



    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
