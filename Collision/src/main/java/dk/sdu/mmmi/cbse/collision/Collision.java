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
                if (!(e1.getID().equals(e2.getID())) || 
                        (e1.getSpritePath().contains("/Enemy/") && e2.getSpritePath().contains("/Enemy/")) || 
                        (e1.getSpritePath().contains("/Player/") && e2.getSpritePath().contains("/Weapon/")) || 
                        (e1.getSpritePath().contains("/Weapon/") && e2.getSpritePath().contains("/Player/"))) {

                    if (detectCollsion(e1, e2)) {
                        ICombatEntity player = (ICombatEntity) e1;
                        ICombatEntity enemy = (ICombatEntity) e2;
                        System.out.println("Collides");
                        enemy.setLife(enemy.getLife() - 1);
                        System.out.println("Life: " + enemy.getLife());
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
        if ((e1.getPositionX() - e2.getPositionX() * (e1.getPositionX() - e2.getPositionX())
                + e1.getPositionY() - e2.getPositionY()) * (e1.getPositionY() - e2.getPositionY())
                < (e1.getRadius() + e2.getRadius()) * (e1.getRadius() + e2.getRadius())) {
            return true;
        }
        return false;
    }

}
