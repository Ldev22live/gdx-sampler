package io.github.ldev22;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ModuleInfoSample extends ApplicationAdapter {
    public static Logger log =  new Logger("GDX DEBUG", Logger.DEBUG);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        log.debug("app=" + Gdx.app);
        log.debug("audio=" + Gdx.audio);
        log.debug("input=" + Gdx.input);
        log.debug("graphics=" + Gdx.graphics);
        log.debug("files=" + Gdx.files);
        log.debug("net=" + Gdx.net);

    }
}
