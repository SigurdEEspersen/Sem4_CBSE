package dk.sdu.mmmi.cbse.player;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;
import Interfaces.ICombatEntity;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})
public class PlayerPlugin implements IPluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayer(gameData);
        player.addCombat((ICombatEntity) player);
        world.addEntity(player);
    }

    private Player createPlayer(GameData gameData) {

        float speed = 500;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = 15;
        float radians = 3.1415f / 2;

        float[] colour = new float[4];
        colour[0] = 1.0f;
        colour[1] = 1.0f;
        colour[2] = 1.0f;
        colour[3] = 1.0f;

        Player player = new Player();

        player.setPositionX(x);
        player.setPositionY(y);
        player.setSpeed(speed);
        player.setRadians(radians);

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
