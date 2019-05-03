package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import data.Entity;
import data.GameData;
import data.World;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.IPluginService;
import services.IPostProcessor;
import services.IControlService;

/**
 *
 * @author Gruppe 11
 */
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IPluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IPluginService> result;
    private SpriteBatch spriteBatch;
    private Sprite backgroundSprite;
    private ConcurrentHashMap<Entity, Boolean> entityMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Entity, ArrayList> spriteMap = new ConcurrentHashMap<>();
    private float pX, pY;

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        result = lookup.lookupResult(IPluginService.class);
        result.addLookupListener(lookupListener);
        result.allItems();

        for (IPluginService plugin : result.allInstances()) {
            System.out.println("Found plugin: " + plugin);
            plugin.start(gameData, world);
            gamePlugins.add(plugin);
        }

        spriteBatch = new SpriteBatch();

        String partDir[] = System.getProperty("user.dir").split("Sem4_CBSE");
        String rootDir = partDir[0] + "Sem4_CBSE";

        for (Entity entity : world.getEntities()) {
            entityMap.put(entity, false);
            ArrayList list = new ArrayList();
            Sprite sprite = new Sprite(new Texture(entity.getSpritePath()));
            sprite.setScale(0.2F);
            list.add(sprite);
            list.add(entity.getPositionX());
            list.add(entity.getPositionY());
            list.add(entity.getRadians());
            spriteMap.put(entity, list);
        }

        backgroundSprite = new Sprite(new Texture(rootDir + "/Core/src/main/java/dk/sdu/mmmi/cbse/core/main/background.png"));

    }

    @Override
    public void render() {
        // clear screen to black
        spriteBatch.flush();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        spriteBatch.setProjectionMatrix(cam.combined);
        float entityRadians = 0;
        for (Entity entity : world.getEntities()) {
            if (entityMap.containsKey(entity)) {
                entityMap.replace(entity, true);
                spriteMap.get(entity).set(1, entity.getPositionX());
                spriteMap.get(entity).set(2, entity.getPositionY());
                spriteMap.get(entity).set(3, entity.getRadians());
            } else {
                entityMap.put(entity, true);
                ArrayList list = new ArrayList();
                Sprite sprite = new Sprite(new Texture(entity.getSpritePath()));
                sprite.setScale(0.2F);
                list.add(sprite);
                list.add(entity.getPositionX());
                list.add(entity.getPositionY());
                list.add(entity.getRadians());
                spriteMap.put(entity, list);
            }

            float positionX = entity.getPositionX();
            float positionY = entity.getPositionY();
            if (entity.getSpritePath().contains("/Player/")) {
                pX = entity.getPositionX();
                pY = entity.getPositionY();

                entityRadians = (float) Math.atan2(
                        Gdx.graphics.getHeight() - Gdx.input.getY() - positionY,
                        Gdx.input.getX() - positionX
                );
                entity.setRadians(entityRadians);
                spriteMap.get(entity).set(3, entityRadians);
            } else {
                entityRadians = entity.getRadians(); 
            }
            if (entity.getSpritePath().contains("/Enemy/")) {
                entity.setPlayerX(pX);
                entity.setPlayerY(pY);
                
            }
        }

        for (Entity e : entityMap.keySet()) {
            if (entityMap.get(e).equals(false)) {
                spriteMap.remove(e);
                entityMap.remove(e);
            }
        }

        update();
        draw();
    }

    private void update() {
        // Update
        for (IControlService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.execute(gameData, world);
        }

        // Post Update
        for (IPostProcessor postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.execute(gameData, world);
        }

    }

    private void draw() {
        spriteBatch.begin();
        backgroundSprite.setPosition(-355, -165);
        backgroundSprite.draw(spriteBatch);
        for (Entity e : spriteMap.keySet()) {
            Sprite sp = (Sprite) spriteMap.get(e).get(0);
            float x = (float) spriteMap.get(e).get(1);
            float y = (float) spriteMap.get(e).get(2);
            float radians = (float) spriteMap.get(e).get(3);
            sp.setRotation((radians * MathUtils.radDeg) % 360);
            sp.setPosition(x, y);
            sp.draw(spriteBatch);
        }
        spriteBatch.end();
        for (Entity e : entityMap.keySet()) {
            entityMap.replace(e, false);
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

    private Collection<? extends IControlService> getEntityProcessingServices() {
        return lookup.lookupAll(IControlService.class);
    }

    private Collection<? extends IPostProcessor> getPostEntityProcessingServices() {
        return lookup.lookupAll(IPostProcessor.class);
    }

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPluginService> updated = result.allInstances();

            for (IPluginService us : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(us)) {
                    us.start(gameData, world);
                    gamePlugins.add(us);
                }
            }

            // Stop and remove module
            for (IPluginService gs : gamePlugins) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    gamePlugins.remove(gs);
                }
            }
        }

    };
}
