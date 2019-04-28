/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weapon;

import data.Entity;
import data.GameData;
import data.KeyBindings;
import data.World;
import dk.sdu.mmmi.cbse.player.Player;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;
import Interfaces.ICombatEntity;
import Interfaces.IShooter;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class WeaponControlSystem implements IControlService {


    private float x, y;

    @Override
    public void execute(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {
            x = entity.getPositionX();
            y = entity.getPositionY();
            float rad = entity.getRadians();
            String id = entity.getID();
            if (entity instanceof ICombatEntity && !(entity instanceof Weapon)) {
                if (((ICombatEntity) entity).isShooting()) {
                    Entity wpn = createWeapon(x, y, rad);
                    ((ICombatEntity) entity).setShooting(false);
                    world.addEntity(wpn);
                }
            }
        }
//        
//        for( Entity e : world.getEntities(Weapon.class)){
//            (Weapon) e.setU
//        }

//        
//        for (Entity entity : world.getEntities()) {
//            if(entity.getCombat(ICombatEntity.class)){
//                ICombatEntity combatEntity = entity.getCombat(ICombatEntity.class);
//                    wpn = entity.getWeapons(Weapon.class);
//                    wpn.setShoot(combatEntity.isShooting());
//                    if(wpn.isShoot()){
//                        System.out.println("Bang bang");
//                        projectile = createProjectile(gameData, playerX, playerY);
//                        wpn.setShoot(false); // burde kunne fjernes
//                        world.addEntity(projectile);
//                    }
//                
//            }
//            
//
//            updateShape(entity);
//        }
        for (Entity entity : world.getEntities(Weapon.class)) {
            if (((Weapon) entity).isDead()) {
                world.removeEntity(entity);
            }
            ((Weapon) entity).execute(gameData, entity);
            updateShape(entity);
        }

    }

    private void updateShape(Entity weapon) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        shapex[0] = (float) (weapon.getPositionX() + Math.cos(weapon.getRadians()) * 5);
        shapey[0] = (float) (weapon.getPositionY() + Math.sin(weapon.getRadians()) * 5);

        shapex[1] = (float) (weapon.getPositionX() + Math.cos(weapon.getRadians() - 4 * 3.1415f / 5) * 5);
        shapey[1] = (float) (weapon.getPositionY() + Math.sin(weapon.getRadians() - 4 * 3.1145f / 5) * 5);

        shapex[2] = (float) (weapon.getPositionX() + Math.cos(weapon.getRadians() + 3.1415f) * 5 * 0.5);
        shapey[2] = (float) (weapon.getPositionY() + Math.sin(weapon.getRadians() + 3.1415f) * 5 * 0.5);

        shapex[3] = (float) (weapon.getPositionX() + Math.cos(weapon.getRadians() + 4 * 3.1415f / 5) * 5);
        shapey[3] = (float) (weapon.getPositionY() + Math.sin(weapon.getRadians() + 4 * 3.1415f / 5) * 5);

        weapon.setShapeX(shapex);
        weapon.setShapeY(shapey);
    }

    private Entity createWeapon(float x, float y, float rad) {
        Entity wpn = new Weapon(true);
        wpn.setPositionX(x);
        wpn.setPositionY(y);
        wpn.setRadians(rad);
        ((Weapon) wpn).setSpeed(200);
        wpn.addCombat((ICombatEntity) wpn);
        return wpn;

    }

}
