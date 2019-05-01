package dk.sdu.mmmi.cbse.core.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import data.GameData;
import data.KeyBindings;

/**
 *
 * @author Gruppe 11
 */
public class GameInputProcessor extends InputAdapter {

    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean keyDown(int k) {
        if (k == Keys.W) {
            gameData.getKeys().setKey(KeyBindings.UP, true);
        }
        if (k == Keys.A) {
            gameData.getKeys().setKey(KeyBindings.LEFT, true);
        }
        if (k == Keys.S) {
            gameData.getKeys().setKey(KeyBindings.DOWN, true);
        }
        if (k == Keys.D) {
            gameData.getKeys().setKey(KeyBindings.RIGHT, true);
        }

        if (k == Keys.ESCAPE) {
            gameData.getKeys().setKey(KeyBindings.ESCAPE, true);
        }
        if (k == Keys.SPACE) {
            gameData.getKeys().setKey(KeyBindings.SPACE, true);
        }

        return true;
    }

    public boolean keyUp(int k) {
        if (k == Keys.W) {
            gameData.getKeys().setKey(KeyBindings.UP, false);
        }
        if (k == Keys.A) {
            gameData.getKeys().setKey(KeyBindings.LEFT, false);
        }
        if (k == Keys.S) {
            gameData.getKeys().setKey(KeyBindings.DOWN, false);
        }
        if (k == Keys.D) {
            gameData.getKeys().setKey(KeyBindings.RIGHT, false);
        }

        if (k == Keys.ESCAPE) {
            gameData.getKeys().setKey(KeyBindings.ESCAPE, false);
        }
        if (k == Keys.SPACE) {
            gameData.getKeys().setKey(KeyBindings.SPACE, false);
        }

        return true;
    }
}
