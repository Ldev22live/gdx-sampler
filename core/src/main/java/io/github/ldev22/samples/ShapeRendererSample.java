package io.github.ldev22.samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.ldev22.common.SampleBase;
import io.github.ldev22.utils.GdxGraphics;

import javax.swing.*;

public class ShapeRendererSample extends SampleBase {
    public static Logger log = new Logger("GDX DEBUG", Logger.DEBUG);
    public static float WORLD_WIDTH = 40f;
    public static float WORLD_HEIGHT = 20f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        GdxGraphics.clearScreen(Color.BLACK);

        renderer.setProjectionMatrix(camera.combined);

        if(drawGrid){
            drawGrid();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.G:
                drawGrid = !drawGrid;
                break;
            case Input.Keys.C:
                drawCircles = !drawCircles;
                break;
            case Input.Keys.R:
                drawRectangles = !drawRectangles;
                break;
            case Input.Keys.P:
                drawPoints = !drawPoints;
                break;
        }
        return true;
    }

    private void drawGrid(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int y = (int) -worldHeight; y < (int) worldHeight; y++) {
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        for (int x = (int) -worldWidth; x < (int) worldHeight; x++) {
            renderer.line(-worldHeight, x, worldHeight, x);
        }

        renderer.setColor(Color.RED);
        renderer.line((float)-worldWidth, 0f, (float) worldWidth, 0f);
        renderer.line(0f, (float) -worldHeight, 0f, (float)worldHeight);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        //not centering camera
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
