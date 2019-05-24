package dk.sdu.mmmi.cbse.ai;

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
public class DStarLitePlugin implements IPluginService{

    private DStarLite dStar;
    
    @Override
    public void start(GameData gameData, World world) {
        dStar = new DStarLite();
        dStar.init(100, 100, 400, 400);
        world.addAI(dStar);
        

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAI(dStar);

    }
    
}
