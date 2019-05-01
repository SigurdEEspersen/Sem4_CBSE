package services;

import data.GameData;
import data.World;

/**
 *
 * @author Gruppe 11
 */
public interface IPostProcessor {

    void execute(GameData gameData, World world);
}
