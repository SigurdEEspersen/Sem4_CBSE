package dk.sdu.mmmi.cbse.enemy;

import data.Entity;
import data.GameData;
import data.KeyBindings;
import data.World;
import dk.sdu.mmmi.cbse.player.Player;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class EnemyControlSystem implements IControlService {

    @Override
    public void execute(GameData gameData, World world) {
        float playerX = 0;
        float playerY = 0;
        
       for (Entity p : world.getEntities(Player.class)) {
          playerX = p.getPositionX();
          playerY = p.getPositionY();
       }
        
        for (Entity enemy : world.getEntities(Enemy.class)) {
            Enemy enemyMovement = enemy.getCombat(Enemy.class);
            
            float rotation = (float) Math.atan2(playerY - enemy.getPositionY(), playerX - enemy.getPositionX());
            enemyMovement.setPositionRadians(rotation);

            enemyMovement.setUp(true);
            enemyMovement.execute(gameData, enemy);
            
            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        float radians = entity.getPositionRadians();

        shapex[0] = (float) (entity.getPositionX() + Math.cos(radians) * 10);
        shapey[0] = (float) (entity.getPositionY() + Math.sin(radians) * 10);

        shapex[1] = (float) (entity.getPositionX() + Math.cos(radians - 4 * 3.1415f / 5) * 10);
        shapey[1] = (float) (entity.getPositionY() + Math.sin(radians - 4 * 3.1145f / 5) * 10);

        shapex[2] = (float) (entity.getPositionX() + Math.cos(radians + 3.1415f) * 10 * 0.5);
        shapey[2] = (float) (entity.getPositionY() + Math.sin(radians + 3.1415f) * 10 * 0.5);

        shapex[3] = (float) (entity.getPositionX() + Math.cos(radians + 4 * 3.1415f / 5) * 10);
        shapey[3] = (float) (entity.getPositionY() + Math.sin(radians + 4 * 3.1415f / 5) * 10);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
