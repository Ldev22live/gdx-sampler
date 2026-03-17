package io.github.ldev22.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.ldev22.common.SampleBase;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ApplicationListenerSample extends SampleBase {
    public static Logger log =  new Logger("GDX DEBUG", Logger.DEBUG);
    private SpriteBatch batch;
    private Texture image;
    private boolean renderInterrupted = true;
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        image = new Texture("images/atlasSample.png");
        log.debug("Create() called");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
        if(renderInterrupted){
            log.debug("render()");
            renderInterrupted = false;
        }
    }

    @Override
    public void pause() {
        log.debug("pause()");
        renderInterrupted = true;

    }

    @Override
    public void resume() {
        log.debug("Resume()");
        renderInterrupted = true;

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        log.debug("dispose()");
    }
}
