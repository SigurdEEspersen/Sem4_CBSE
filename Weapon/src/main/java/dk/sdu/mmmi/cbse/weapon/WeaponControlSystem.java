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

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class WeaponControlSystem implements IControlService {
    private Entity projectile;
    private Weapon wpn;


    @Override
    public void execute(GameData gameData, World world) {
        float playerX = 0;
        float playerY = 0;
        
        for (Entity p : world.getEntities(Player.class)) {
          playerX = p.getPositionX();
          playerY = p.getPositionY();
        }
        
        for (Entity entity : world.getEntities()) {
            if(entity.getCombat(ICombatEntity.class)){
                ICombatEntity combatEntity = entity.getCombat(ICombatEntity.class);
                    wpn = entity.getWeapons(Weapon.class);
                    wpn.setShoot(combatEntity.isShooting());
                    if(wpn.isShoot()){
                        System.out.println("Bang bang");
                        projectile = createProjectile(gameData, playerX, playerY);
                        wpn.setShoot(false); // burde kunne fjernes
                        world.addEntity(projectile);
                    }
                
            }
            

            updateShape(entity);
        }
    }
    
    private Weapon createProjectile(GameData gameData, float dx, float dy) {
        float speed = 300;
        float rotationSpeed = 5;
        float x = dx;
        float y = dy;
        float radians = 3.1415f / 2;

        float[] colour = new float[4];
        colour[0] = 1.0f;
        colour[1] = 1.0f;
        colour[2] = 1.0f;
        colour[3] = 1.0f;


        wpn.setPositionX(x);
        wpn.setPositionY(y);
        wpn.setSpeed(speed);
        wpn.setPositionRadians(radians);
        
        return wpn;
    }

    private void updateShape(Entity weapon) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        shapex[0] = (float) (weapon.getPositionX() + Math.cos(weapon.getPositionRadians()) * 5);
        shapey[0] = (float) (weapon.getPositionY() + Math.sin(weapon.getPositionRadians()) * 5);

        shapex[1] = (float) (weapon.getPositionX() + Math.cos(weapon.getPositionRadians() - 4 * 3.1415f / 5) * 5);
        shapey[1] = (float) (weapon.getPositionY() + Math.sin(weapon.getPositionRadians() - 4 * 3.1145f / 5) * 5);

        shapex[2] = (float) (weapon.getPositionX() + Math.cos(weapon.getPositionRadians() + 3.1415f) * 5 * 0.5);
        shapey[2] = (float) (weapon.getPositionY() + Math.sin(weapon.getPositionRadians() + 3.1415f) * 5 * 0.5);

        shapex[3] = (float) (weapon.getPositionX() + Math.cos(weapon.getPositionRadians() + 4 * 3.1415f / 5) * 5);
        shapey[3] = (float) (weapon.getPositionY() + Math.sin(weapon.getPositionRadians() + 4 * 3.1415f / 5) * 5);

        weapon.setShapeX(shapex);
        weapon.setShapeY(shapey);
    }

}
