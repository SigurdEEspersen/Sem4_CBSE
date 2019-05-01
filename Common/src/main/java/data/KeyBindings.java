package data;

/**
 *
 * @author Gruppe 11
 */
public class KeyBindings {

    private static boolean[] keys;
    private static boolean[] pressedKeys;

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ESCAPE = 4;
    public static final int SPACE = 5;

    public KeyBindings() {
        keys = new boolean[6];
        pressedKeys = new boolean[6];

    }

    public void update() {
        for (int i = 0; i < 6; i++) {
            pressedKeys[i] = keys[i];
        }
    }

    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public boolean isDown(int k) {
        return keys[k];
    }

    public boolean isPressed(int k) {
        return keys[k] && !pressedKeys[k];
    }
}
