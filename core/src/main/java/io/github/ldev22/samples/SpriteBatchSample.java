package io.github.ldev22.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.ldev22.common.SampleBase;
import io.github.ldev22.utils.GdxGraphics;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class SpriteBatchSample extends SampleBase {
    public static Logger log = new Logger("GDX DEBUG", Logger.DEBUG);
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        log.debug("create()");

        camera = new OrthographicCamera();
        viewport = new FitViewport(10.80f, 7.20f, camera);
        texture = new Texture(Gdx.files.internal("raw/character.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        float width = 1f;
        float height = 1f;

        batch.draw(
            texture, 8f, 8f,
            width / 2f,
            height / 2f, 1f, 1f, 1f, 1f, 45f,
            0,0, texture.getWidth(), texture.getHeight(), false, false);

        batch.draw(
            texture, 4f, 2f,
            width / 2f,
            height / 2f, 1f, 1f, 4f, 4f, 180f,
            0,0, texture.getWidth(), texture.getHeight(), false, false);

        batch.draw(
            texture, 3f, 3f,
            width / 2f,
            height / 2f, 1f, 1f, 4f, 4f, 45f,
            0,0, texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
