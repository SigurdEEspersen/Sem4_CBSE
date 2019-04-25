package dk.sdu.mmmi.cbse.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import data.Entity;
import data.GameData;
import data.World;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.IPluginService;
import services.IPostProcessor;
import services.IControlService;
import dk.sdu.mmmi.cbse.player.Player;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IPluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IPluginService> result;
    private Texture t;
    private Sprite sprite;
    private Sprite backgroundSprite;
    private SpriteBatch spriteBatch;
    private String spritePath;
    private float playerX;
    private float playerY;
    private float playerRadians;

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        result = lookup.lookupResult(IPluginService.class);
        result.addLookupListener(lookupListener);
        result.allItems();

        for (IPluginService plugin : result.allInstances()) {
            plugin.start(gameData, world);
            gamePlugins.add(plugin);
        }
        
        for (Entity p : world.getEntities(Player.class)) {
           spritePath = p.getSpritePath();
           
       }
        
        spriteBatch = new SpriteBatch();
        backgroundSprite = new Sprite(new Texture("/background.png"));
        sprite = new Sprite(new Texture(spritePath));
    }

    @Override
    public void render() {
        
        update();
      
        for (Entity p : world.getEntities(Player.class)) {
          playerX = p.getPositionX();
          playerY = p.getPositionY();
          playerRadians = (float) Math.atan2(
                  gameData.getDisplayHeight() - Gdx.input.getY() - p.getPositionY(), 
                  Gdx.input.getX() - p.getPositionX()
          );
          
          p.setRadians(playerRadians);
          
       }

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();
        
        spriteBatch.setProjectionMatrix(cam.combined);
        
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
        
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatch.begin();
        sprite.setSize(sprite.getWidth(), sprite.getHeight());
        
        float angle = playerRadians * MathUtils.radDeg;
        
        if (angle < 0)
            angle +=360;
        
        sprite.setPosition(
                playerX - (sprite.getWidth() / 2),
                playerY - (sprite.getHeight() / 2)
        );
        
        sprite.setScale(0.3F);
        sprite.setRotation(angle);
        backgroundSprite.setPosition(-355, -165);
        
        backgroundSprite.draw(spriteBatch);
        sprite.draw(spriteBatch);
       
        spriteBatch.end();
        
//        for (Entity entity : world.getEntities()) {
//            
//        }
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
