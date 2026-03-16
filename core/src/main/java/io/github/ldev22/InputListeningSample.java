package io.github.ldev22;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class InputListeningSample extends ApplicationAdapter implements InputProcessor {
    public static Logger log =  new Logger("GDX DEBUG", Logger.DEBUG);
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private final int maxMessageCount = 15;
    private Array<String> messages;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        log.debug("create()");

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080f, 720f, camera);

        batch = new SpriteBatch();
        font = new BitmapFont();
        messages = new Array<>();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        draw();

        batch.end();
    }

    private void draw(){
        //mouse or touch x and y points
        for(int i = 0; i < messages.size; i++){
            font.draw(batch, messages.get(i), 20f,720f-40f * (i + 1));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        String message = "keyDown screen X" + keycode;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        String message = "keyUp screen X" + keycode;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        String message = "keyTyped " + character;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    private void addMessage(String message){
        messages.add(message);
        if(messages.size > maxMessageCount){
            messages.removeIndex(0);
        }
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        String message = "touchUp screen X" + screenX + "screen Y" + screenY;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        String message = "touchdragged screen X" + screenX + "touchdragged Y" + screenY;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        String message = "mouseMoved screen X" + screenX + "mouseMoved Y" + screenY;
        log.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        String message = "scrolled screen X" + amountX + "scrolled Y" + amountY;
        log.debug(message);
        addMessage(message);
        return true;
    }
}
