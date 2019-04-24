/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weapon;

import Interfaces.IShooter;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPluginService;

/**
 *
 * @author Sigurd E. Espersen
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPluginService.class),})
public class WeaponPlugin implements IPluginService {
    
    Entity weapon;

    @Override
    public void start(GameData gameData, World world) {
        weapon = createWeapon(gameData);
//        weapon.addWeapons((IShooter) weapon);
        world.addEntity(weapon);
        
    }
    
    private Weapon createWeapon(GameData gameData) {
        Weapon weapon = new Weapon();
        return weapon;
    }
    
    

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }
    }
    
}
