package com.mygdx.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public abstract class UIScreen implements Screen {

    //Variables of the UI Screen
    //protected ScreenViewport viewport;
    protected ExtendViewport viewport;
    protected OrthographicCamera camera;
    protected Stage stage;
    protected Table mainTable;

    protected UIScreen(float minWorldWidth, float minWorldHeight){
        camera = new OrthographicCamera();
        //viewport = new ScreenViewport(camera);
        viewport = new ExtendViewport(minWorldWidth, minWorldHeight, camera);
        stage = new Stage(viewport);
        mainTable = new Table();
        stage.addActor(mainTable);
        mainTable.setFillParent(true);
        mainTable.setDebug(AppConstants.DEBUG);

        //viewport.setWorldSize(20, 20);
        //camera.position.set(0, 0, 0);
        //viewport.apply();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        setup();
    }

    @Override
    public void render(float delta) {
        camera.update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //My Own Methods

    protected abstract void setup();

    public Table addTable(){
        Table table = new Table();
        stage.addActor(table);
        return table;
    }
}
