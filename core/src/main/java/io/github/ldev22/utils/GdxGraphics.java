package io.github.ldev22.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GdxGraphics {
    public static void clearScreen(Color color){
        color = Color.BLACK;
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void clearScreen(float red, float green, float blue, float alpha){
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void use(Batch batch, Runnable action) {
        batch.begin();
        action.run();
        batch.end();
    }
}
