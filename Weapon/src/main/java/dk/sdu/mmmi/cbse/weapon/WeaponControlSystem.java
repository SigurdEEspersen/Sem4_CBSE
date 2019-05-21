package dk.sdu.mmmi.cbse.weapon;

import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;
import Interfaces.ICombatEntity;

/**
 *
 * @author Gruppe 11
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class WeaponControlSystem implements IControlService {

    private float x, y;

    @Override
    public void execute(GameData gameData, World world) {
        String partDir[] = System.getProperty("user.dir").split("Sem4_CBSE");
        String rootDir = partDir[0] + "Sem4_CBSE";

        for (Entity entity : world.getEntities()) {
            x = entity.getPositionX();
            y = entity.getPositionY();
            float rad = entity.getRadians();
            String id = entity.getID();

            if (entity instanceof ICombatEntity && !(entity instanceof Weapon)) {
                if (((ICombatEntity) entity).isShooting()) {
                    Entity wpn = createWeapon(x, y, rad);
                    wpn.setSpritePath(rootDir + "/Weapon/src/main/java/dk/sdu/mmmi/cbse/weapon/bullet4.png");
                    ((ICombatEntity) entity).setShooting(false);
                    ((Weapon) wpn).setLife(1);
                    wpn.setRadius(5);
                    world.addEntity(wpn);
                }
            }
        }

        for (Entity entity : world.getEntities(Weapon.class)) {
            if (((Weapon) entity).isDead()) {
                world.removeEntity(entity);
            }
            ((Weapon) entity).execute(gameData, entity);
        }

    }

 

    private Entity createWeapon(float x, float y, float rad) {
        Entity wpn = new Weapon(true);
        wpn.setPositionX(x);
        wpn.setPositionY(y);
        wpn.setRadians(rad);
        ((Weapon) wpn).setSpeed(300);
        wpn.addCombat((ICombatEntity) wpn);
        return wpn;
    }
}
