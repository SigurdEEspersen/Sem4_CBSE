package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.sun.javafx.stage.ScreenHelper;
import data.Entity;
import data.GameData;
import data.World;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.enemy.Enemy;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.cbse.weapon.Weapon;
import java.util.Collection;
import java.util.List;
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

    
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IPluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IPluginService> result;
    private SpriteBatch spriteBatch;
    private Sprite playerSprite;
    private Sprite backgroundSprite;
    private Sprite bulletSprite;
    private Sprite enemySprite;
    private float playerX;
    private float playerY;
    private float playerRadians;
    
    // tile map
    private TiledMap map;
    private static OrthographicCamera cam;
    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void create() {
            
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
        
        bulletSprite = new Sprite(new Texture(rootDir + "/Weapon/src/main/java/dk/sdu/mmmi/cbse/weapon/bullet4.png"));
        enemySprite = new Sprite(new Texture(rootDir + "/Enemy/src/main/java/dk/sdu/mmmi/cbse/enemy/enemy.png"));
        
        // tile map
        map = new TmxMapLoader().load(rootDir + "/Core/src/main/java/dk/sdu/mmmi/cbse/core/main/sduMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        MapProperties prop = map.getProperties();
        
        gameData.setDisplayWidth(prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class));
        gameData.setDisplayHeight(prop.get("height", Integer.class) * prop.get("tileheight", Integer.class));
    
        
        cam = new OrthographicCamera(
                prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class),
                prop.get("height", Integer.class) * prop.get("tileheight", Integer.class)
        );
        
        cam.zoom = 1.28F;
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();
        
        /**
         * sets sprites
         */
        for (Entity e: world.getEntities()) {
           
            // player
            if (e instanceof Player) {
                e.setPlayerScale(0.2F);
                e.setSprite(new Sprite(new Texture(e.getSpritePath())));
                playerSprite = e.getSprite();
            }
            
            // bullet
//            if (e instanceof Weapon) {
//                e.setSprite(new Sprite(new Texture(e.getSpritePath())));
//                bulletSprite = e.getSprite();
//            }
//            
//            // enemy
//            if (e instanceof Enemy) {
//                e.setSprite(new Sprite(new Texture(e.getSpritePath())));
//                enemySprite = e.getSprite();
//            }
        }
        
        for (Entity e: world.getEntities(Weapon.class)) {
            e.setPlayerScale(0.15F);
        }
        
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderer.setView(cam);
        renderer.render();

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        //priteBatch.setProjectionMatrix(cam.combined);

        for (Entity p : world.getEntities(Player.class)) {
            playerX = p.getPositionX();
            playerY = p.getPositionY();
            playerRadians = (float) Math.atan2(
                    Gdx.graphics.getHeight() - Gdx.input.getY() - playerY,
                    Gdx.input.getX() - playerX
            );

            p.setRadians(playerRadians);
        }

        for (Entity p : world.getEntities()) {
            p.setPlayerX(playerX);
            p.setPlayerY(playerY);
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
//        for (Entity entity : world.getEntities()) {
//
//        }

        float angle = (playerRadians - ((float)Math.PI / 2)) * MathUtils.radDeg;

        if (angle < 0) {
            angle += 360;
        }

        spriteBatch.begin();
//        backgroundSprite.setPosition(-355, -165);
//        backgroundSprite.draw(spriteBatch);

        playerSprite.setPosition(
                playerX - playerSprite.getWidth() / 2,
                playerY - playerSprite.getHeight() / 2
        );

        playerSprite.setSize(playerSprite.getWidth(), playerSprite.getHeight());
        playerSprite.setRotation(angle);
        playerSprite.setScale(0.5F);

//        for (Entity b : world.getEntities(Weapon.class)) {
//            bulletSprite.setPosition(b.getPositionX(), b.getPositionY());
//            bulletSprite.setSize(bulletSprite.getWidth(), bulletSprite.getHeight());
//            bulletSprite.setScale(0.15F);
//            bulletSprite.draw(spriteBatch);
//        }
        
        for (Entity e : world.getEntities(Enemy.class)) {
            enemySprite.setPosition(
                    e.getPositionX() - enemySprite.getWidth() / 2,
                    e.getPositionY() - enemySprite.getHeight() / 2
            );
            
            enemySprite.setScale(0.2F);
            
            float rotation = e.getRadians() * MathUtils.radDeg;

            if (rotation < 0) {
                rotation += 360;
            }

            enemySprite.setRotation(rotation);
            enemySprite.draw(spriteBatch);
        }
        
        for (Entity pl : world.getEntities(Player.class)) {
            for (Entity b : world.getEntities(Weapon.class)) {
                float bulang = b.getRadians() * MathUtils.radDeg;
                
                if (bulang < 0)
                    bulang += 360;
                
                bulletSprite.setPosition(
                        b.getPositionX() - bulletSprite.getWidth() / 2, 
                        b.getPositionY() - bulletSprite.getHeight() / 2);
                bulletSprite.setSize(bulletSprite.getWidth(), bulletSprite.getHeight());
                bulletSprite.setScale(0.15F);
                bulletSprite.setRotation(bulang);
                bulletSprite.draw(spriteBatch);
            }
            
            playerSprite.draw(spriteBatch);
        }
        
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
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
