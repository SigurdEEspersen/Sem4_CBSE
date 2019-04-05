package dk.sdu.mmmi.cbse.player;

import data.Entity;
import data.GameData;
import data.World;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

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
    @ServiceProvider(service = IControlService.class),})
public class PlayerControlSystem implements IControlService {

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            updateShape(player);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        float x = entity.getPositionX();
        float y = entity.getPositionY();
        float radians = entity.getPositionRadians();

        Image[] playerSprites = new Image[1];
        try {
            File pathToFile = new File("C:\\Users\\sigur\\Dropbox\\Skole\\Uni\\4. semester\\Semester projekt\\Sem4_CBSE\\Sem4_CBSE\\Assets\\Player.png");
            Image sprite = ImageIO.read(pathToFile);
            playerSprites[0] = sprite;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        entity.setSprite(playerSprites);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
