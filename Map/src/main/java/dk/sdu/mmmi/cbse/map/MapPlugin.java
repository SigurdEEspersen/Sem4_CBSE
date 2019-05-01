package dk.sdu.mmmi.cbse.map;

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
public class MapPlugin implements IPluginService {

    private Map map;

    @Override
    public void start(GameData gameData, World world) {
        map = new Map();
        world.addMap(map);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMap(map);
    }

}
