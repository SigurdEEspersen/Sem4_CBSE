package dk.sdu.mmmi.cbse.player;

import data.Entity;
import data.GameData;
import data.KeyBindings;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class PlayerControlSystem implements IControlService {



    @Override
    public void execute(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Player playerCombat = entity.getCombat(Player.class);

            playerCombat.setDown(gameData.getKeys().isDown(KeyBindings.DOWN));
            playerCombat.setUp(gameData.getKeys().isDown(KeyBindings.UP));
            playerCombat.setLeft(gameData.getKeys().isDown(KeyBindings.LEFT));
            playerCombat.setRight(gameData.getKeys().isDown(KeyBindings.RIGHT));
            playerCombat.setShooting(gameData.getKeys().isDown(KeyBindings.SPACE));

            playerCombat.execute(gameData, entity);

        }
    }
}
