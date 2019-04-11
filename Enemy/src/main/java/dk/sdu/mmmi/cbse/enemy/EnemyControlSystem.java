package dk.sdu.mmmi.cbse.enemy;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class EnemyControlSystem implements IControlService{

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            updateShape(enemy);
        }
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        float radians = entity.getPositionRadians();
        
        shapex[0] = (float) (500 + Math.cos(radians) * 5);
        shapey[0] = (float) (500 + Math.sin(radians) * 5);

        shapex[1] = (float) (500 + Math.cos(radians - 4 * 3.1415f / 5) * 5);
        shapey[1] = (float) (500 + Math.sin(radians - 4 * 3.1145f / 5) * 5);

        shapex[2] = (float) (500 + Math.cos(radians + 3.1415f) * 5 * 0.5);
        shapey[2] = (float) (500 + Math.sin(radians + 3.1415f) * 5 * 0.5);

        shapex[3] = (float) (500 + Math.cos(radians + 4 * 3.1415f / 5) * 5);
        shapey[3] = (float) (500 + Math.sin(radians + 4 * 3.1415f / 5) * 5);
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
