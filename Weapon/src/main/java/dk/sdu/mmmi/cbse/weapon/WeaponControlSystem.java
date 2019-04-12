/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weapon;

import Interfaces.IEntityMovement;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IControlService;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IControlService.class),})
public class WeaponControlSystem implements IControlService {

    @Override
    public void execute(GameData gameData, World world) {
        for (Entity weapon : world.getEntities()) {
            updateShape(weapon);
        }
    }
    
    private Entity createWeapon(float x, float y, float radians, String uuid) {
        Entity wpn = new Weapon();
        
        wpn.addMovement((IEntityMovement) wpn);
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
