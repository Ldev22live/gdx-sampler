package io.github.ldev22;

import com.badlogic.gdx.*;
import com.badlogic.gdx.input.NativeInputConfiguration;
import com.badlogic.gdx.utils.Logger;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MultiplexerSample extends ApplicationAdapter {
    public static Logger log =  new Logger("GDX DEBUG", Logger.DEBUG);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        InputMultiplexer multiplexer = new InputMultiplexer();
        InputAdapter firstProcessor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                log.debug("First key down keycode = " + keycode);
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                log.debug("First key up keycode = " + keycode);
                return false;
            }
        };

        InputAdapter secondProcessor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                log.debug("Second key down keycode = " + keycode);
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                log.debug("Second key up keycode = " + keycode);
                return false;
            }
        };

        multiplexer.addProcessor(firstProcessor);
        multiplexer.addProcessor(secondProcessor);

        Gdx.input.setInputProcessor(multiplexer);
    }
}
