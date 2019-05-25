package dk.sdu.mmmi.cbse.enemy;

import Interfaces.IAI;
import Interfaces.ICombatEntity;
import Interfaces.IMap;
import data.Entity;
import data.GameData;
import data.World;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class EnemyControlSystem implements IControlService {

    private IMap map;

    @Override
    public void execute(GameData gameData, World world) {
        String partDir[] = System.getProperty("user.dir").split("Sem4_CBSE");
        String rootDir = partDir[0] + "Sem4_CBSE";
        if (!world.getMapArray().isEmpty()) {
            if (world.getMapArray().get(0) != null) {
                map = world.getMapArray().get(0);
                if (map.isSpawning()) {
                    Random r = new Random();
                    Enemy enemy = createEnemy(map.getEnemyCoordinatesX()[r.nextInt(3)], map.getEnemyCoordinatesY()[0]);
                    enemy.setSpritePath(rootDir + "/Enemy/src/main/java/dk/sdu/mmmi/cbse/enemy/enemy.png");
                    enemy.addCombat((ICombatEntity) enemy);
                    enemy.setRadius(25);
                    enemy.setLife(10);
                    world.addEntity(enemy);
                    map.setSpawn(false);
                }
            }
        }

        for (Entity enemy : world.getEntities(Enemy.class)) {

            Enemy enemyMovement = enemy.getCombat(Enemy.class);
            for (IAI ai : world.getAIList()) {
                ai.updateStart((int) enemy.getPositionX(), (int) enemy.getPositionY());
                ai.updateGoal((int) enemy.getPlayerX(), (int) enemy.getPlayerY());
                ai.replan();
                ai.getPath();

                float rotation = (float) Math.atan2(ai.getPath().get((ai.getPath().size()) / 2).y - enemy.getPositionY(), ai.getPath().get((ai.getPath().size()) / 2).x - enemy.getPositionX());
                enemyMovement.setRadians(rotation);
            }
            enemyMovement.setUp(true);
            enemyMovement.execute(gameData, enemy);
        }
    }

    private Enemy createEnemy(float x, float y) {

        float speed = 30 + (float) Math.random() * (150 - 30);
        float radians = 3.1415f / 2;

        Enemy enemy = new Enemy();

        enemy.setPositionX(x);
        enemy.setPositionY(y);
        enemy.setSpeed(speed);
        enemy.setRadians(radians);

        return enemy;
    }
}
