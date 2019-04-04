/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Peter
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
        keys = new boolean[keys.length];
        pressedKeys = new boolean[keys.length];

    }

    public void update() {
        for (int i = 0; i < keys.length; i++) {
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
