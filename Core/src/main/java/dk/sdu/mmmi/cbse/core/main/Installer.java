package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

/**
 *
 * @author Gruppe 11
 */
public class Installer extends ModuleInstall {

    private static Game g;

    @Override
    public void restored() {

        g = new Game();

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Asteroids";
        cfg.width = 1200;
        cfg.height = 700;
        cfg.useGL30 = false;
        cfg.resizable = false;
        cfg.overrideDensity = 1000;

        new LwjglApplication(g, cfg);
    }
}
