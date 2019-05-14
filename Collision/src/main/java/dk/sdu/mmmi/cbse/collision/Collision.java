package dk.sdu.mmmi.cbse.collision;

import Interfaces.ICombatEntity;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPostProcessor;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPostProcessor.class),})
public class Collision implements IPostProcessor {

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if ((!(e1.getID().equals(e2.getID()))) && 
                        (!(e1.getSpritePath().contains("/enemy/") && e2.getSpritePath().contains("/enemy/"))) && 
                        (!(e1.getSpritePath().contains("/weapon/") && e2.getSpritePath().contains("/weapon/"))) && 
                        (!(e1.getSpritePath().contains("/player/") && e2.getSpritePath().contains("/weapon/"))) && 
                        (!(e1.getSpritePath().contains("/weapon/") && e2.getSpritePath().contains("/player/")))) {

//                    System.out.println("-----");
//                    
//                    System.out.println("De har: " + e1.getSpritePath() + " og " + e2.getSpritePath());
//                    
//                    System.out.println("-----");
                    if (detectCollsion(e1, e2)) {
                        ICombatEntity player = (ICombatEntity) e1;
                        ICombatEntity enemy = (ICombatEntity) e2;
                        enemy.setLife(enemy.getLife() - 1);
                        if (player.isDead()) {
                            world.removeEntity((Entity) player);
                        }
                        if (enemy.isDead()) {
                            world.removeEntity((Entity) enemy);
                        }

                    }
                }
            }
        }
    }

    private boolean detectCollsion(Entity e1, Entity e2) {
        
//        System.out.println((e1.getPositionX() - e2.getPositionX() * (e1.getPositionX() - e2.getPositionX()) + e1.getPositionY() - e2.getPositionY()) * (e1.getPositionY() - e2.getPositionY()) + " < " + (e1.getRadius() + e2.getRadius()) * (e1.getRadius() + e2.getRadius()));
        if ((e1.getPositionX() - e2.getPositionX() * (e1.getPositionX() - e2.getPositionX())
                + e1.getPositionY() - e2.getPositionY()) * (e1.getPositionY() - e2.getPositionY())
                < (e1.getRadius() + e2.getRadius()) * (e1.getRadius() + e2.getRadius())) {
            return true;
        }
        return false;
    }

}
