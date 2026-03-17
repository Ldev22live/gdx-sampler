package io.github.ldev22.samples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.ldev22.common.SampleBase;
import io.github.ldev22.utils.GdxGraphics;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class ReflectionSample extends SampleBase {
    public static Logger log = new Logger("GDX DEBUG", Logger.DEBUG);
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        GdxGraphics.clearScreen(Color.BLACK);
        log.debug("create()");

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080f, 720f, camera);
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        debugReflection(ReflectionSample.class);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        //clears screen use DRY


        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        draw();

        batch.end();
    }

    private void draw() {
        //mouse or touch x and y points
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
        String mouseButton = "";
        if (leftPressed) {
            mouseButton = "Left mouse button pressed";
            log.debug(mouseButton);
        } else {
            mouseButton = "No button pressed";
        }

        if (rightPressed) {
            mouseButton = "Right mouse button pressed";
            log.debug(mouseButton);
        }

        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        String keyPressed = "";
        if (wPressed) {
            keyPressed = "W has been pressed";
        } else if (sPressed) {
            keyPressed = "S has been pressed";
        } else {
            keyPressed = "Awaiting keyboard input";
        }
        font.draw(batch, "Mouse X = " + mouseX + "Mouse Y = " + mouseY, 20f, 720f - 20f);
        font.draw(batch, mouseButton, 20f, 720f - 60f);
        font.draw(batch, keyPressed, 20f, 720f - 100f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    private static <T> void debugReflection(Class<ReflectionSample> reflectionSampleClass) {
        // Implementation here (currently empty)
        Field[] fields = ClassReflection.getDeclaredFields(reflectionSampleClass);
        Method[] methods = ClassReflection.getDeclaredMethods(reflectionSampleClass);
        log.debug("Field count = " + fields.length);

        for(Field field : fields){
            log.debug("Field name = " + field.getName() + " Field Type = " + field.getType());
        }

        log.debug("Method count = " + methods.length);
        for(Method method: methods){
            log.debug("method name = " + method.getName() + " param count = " + method.getParameterTypes().length);
        }

    }
}
