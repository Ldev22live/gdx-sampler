package io.github.ldev22.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.*;
import io.github.ldev22.common.SampleBase;
import io.github.ldev22.utils.GdxGraphics;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class ViewportSample extends SampleBase {
    public static Logger log = new Logger("GDX DEBUG", Logger.DEBUG);
    public static float WORLD_WIDTH = 1080f; //1 world unit -> 1 pixel
    public static float WORLD_HEIGHT = 720f;
    public static float CAMERA_SPEED = 2.0f;
    public static float CAMERA_ZOOM_SPPED = 2.0f;
    private OrthographicCamera camera;
    private Viewport currentViewport;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    private ArrayMap<String, Viewport> viewPorts = new ArrayMap<>();
    private int currentViewPortIndex = -1;
    private String currentViewportName = "";
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        log.debug("create()");

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        createViewports();
        selectNextViewport();

            Gdx.input.setInputProcessor(this);
    }

    private void createViewports(){
       viewPorts.put("StretchViewport", new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
       viewPorts.put("FitViewport", new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
       viewPorts.put("FillViewport", new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
       viewPorts.put("ScreenViewPort", new ScreenViewport(camera));
       viewPorts.put("ExtendViewPort", new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        selectNextViewport();
        return false;
    }

    private void selectNextViewport(){
        currentViewPortIndex = (currentViewPortIndex + 1) % viewPorts.size;

        currentViewport = viewPorts.getValueAt(currentViewPortIndex);
        currentViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewportName = viewPorts.getKeyAt(currentViewPortIndex);

        log.debug("Selected Viewport = " + currentViewportName);
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxGraphics.clearScreen(Color.BLACK);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        draw();

        batch.end();
    }

    private void draw() {

        batch.draw(texture, 0.0f, 0.0f, WORLD_WIDTH, WORLD_HEIGHT);
        font.draw(batch, currentViewportName, 50f, 100f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
