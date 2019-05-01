package dk.sdu.mmmi.cbse.enemy;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})
public class EnemyPlugin implements IPluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
