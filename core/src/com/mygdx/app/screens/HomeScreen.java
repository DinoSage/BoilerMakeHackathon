package com.mygdx.app.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.app.UIScreen;

public class HomeScreen extends UIScreen {

    Skin skin;

    public HomeScreen() {
        super(1600, 900);
    }

    @Override
    protected void setup() {

        skin = new Skin(new TextureAtlas("glassy-ui.json"));
        Label label = new Label("Agility Rush", skin);
        label.setAlignment(Align.center);
        label.setStyle(skin.get("title", Label.LabelStyle.class));

        label.setColor(Color.WHITE);

        mainTable.add(label);
    }
}
