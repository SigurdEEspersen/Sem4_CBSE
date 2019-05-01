package services;

import data.GameData;
import data.World;

/**
 *
 * @author Gruppe 11
 */
public interface IPluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
