package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.app.UIScreen;

public class HomeScreen extends UIScreen {

    Skin skin;

    public HomeScreen() {
        super(500, 100);
    }

    @Override
    protected void setup() {

        skin = new Skin(Gdx.files.internal("glassy-ui.json"));
        //TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        //skin.addRegions(atlas);

        Label label = new Label("Just Do It!", skin);
        label.setAlignment(Align.center);
        //label.setStyle(skin.get("big", Label.LabelStyle.class));

        label.setColor(Color.WHITE);

        mainTable.add(label);
    }
}
