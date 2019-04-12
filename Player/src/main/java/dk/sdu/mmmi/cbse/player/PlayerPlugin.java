package dk.sdu.mmmi.cbse.player;



import Interfaces.IEntityMovement;
import com.badlogic.gdx.graphics.Texture;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Peter
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
        player = createPlayerShip(gameData);
        player.addMovement((IEntityMovement) player);
        world.addEntity(player);
        Texture texture = new Texture("/Update_Center_Folder/player.gif");
    }

    private Player createPlayerShip(GameData gameData) {
        
        float speed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
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
        player.setPositionRadians(radians);
        
        
        //player.setTexture(new Texture("/Update_Center_Folder/player.gif"));
        //player.setTextureWidth(253);
        //player.setTextureWidth(216);
        
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }


}
