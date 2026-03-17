package io.github.ldev22.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.ldev22.common.SampleFactory;
import io.github.ldev22.common.SampleInfos;

public class GdxSampleLauncher extends Game {
    private Skin skin;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Gdx Sampler");
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new GdxSampleLauncher(), config);
    }

    @Override
    public void create() {
        // Load a basic skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Set initial screen to menu
        setScreen(new MenuScreen(this, skin));
    }

    private void launchSample(String name) {
        Gdx.app.log("Launcher", "Launching sample: " + name);
        try {
            //Class<?> sampleClass = ClassReflection.forName("io.github.ldev22." + name);
            ApplicationListener sample = (ApplicationListener) SampleFactory.newSample(name);
            setScreen(new SampleScreen(this, sample));
        } catch (Exception e) {
            Gdx.app.error("Launcher", "Failed to launch sample: " + name, e);
        }
    }

    // Menu Screen
    private static class MenuScreen implements Screen {
        private final GdxSampleLauncher game;
        private final Skin skin;
        private Stage stage;
        private List<String> sampleList;

        public MenuScreen(GdxSampleLauncher game, Skin skin) {
            this.game = game;
            this.skin = skin;
        }

        @Override
        public void show() {
            stage = new Stage(new ScreenViewport());
            Gdx.input.setInputProcessor(stage);

            Table table = new Table();
            table.setFillParent(true);
            stage.addActor(table);

            // Left panel
            Table leftPanel = new Table();
            leftPanel.setWidth(200);
            leftPanel.setHeight(Gdx.graphics.getHeight());

            sampleList = new List<>(skin);
            sampleList.setItems(SampleInfos.getSamplesNames());
            sampleList.setSelectedIndex(0);

            sampleList.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (getTapCount() == 2) {
                        launchSelectedSample();
                    }
                }
            });

            ScrollPane scrollPane = new ScrollPane(sampleList, skin);
            leftPanel.add(scrollPane).fill().expand().pad(10);
            leftPanel.row();

            TextButton launchButton = new TextButton("Launch Sample", skin);
            launchButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    launchSelectedSample();
                }
            });
            leftPanel.add(launchButton).pad(10).fillX();

            table.add(leftPanel).width(200).fillY();
            table.add().expand().fill();  // Right placeholder
        }

        private void launchSelectedSample() {
            String sampleName = sampleList.getSelected();
            if (sampleName != null && !sampleName.isEmpty()) {
                game.launchSample(sampleName);
            }
        }

        @Override
        public void render(float delta) {
            ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(delta);
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height, true);
        }

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {
            stage.dispose();
        }

        @Override
        public void dispose() {}
    }

    // Wrapper Screen for ApplicationListener samples
    private static class SampleScreen implements Screen {
        private final GdxSampleLauncher game;
        private final ApplicationListener sample;

        public SampleScreen(GdxSampleLauncher game, ApplicationListener sample) {
            this.game = game;
            this.sample = sample;
        }

        @Override
        public void show() {
            sample.create();
        }

        @Override
        public void render(float delta) {
            sample.render();
        }

        @Override
        public void resize(int width, int height) {
            sample.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        @Override
        public void pause() {
            sample.pause();
        }

        @Override
        public void resume() {
            sample.resume();
        }

        @Override
        public void hide() {
            sample.pause();
        }

        @Override
        public void dispose() {
            sample.dispose();
        }
    }
}
