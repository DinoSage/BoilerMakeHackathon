package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.app.UIScreen;

public class MainScreen extends UIScreen {

    public MainScreen() {
        super(1000, 500);
    }

    @Override
    protected void setup() {
        // Load Skin
        Skin skin = new Skin(Gdx.files.internal("New Skin/glassy-ui.json"));

        // Add a Horizontal Group of Buttons as Toolbar
        HorizontalGroup toolbar = new HorizontalGroup();

        ButtonGroup toolbarBtns = new ButtonGroup();

        // Toolbar Buttons
        TextButton tasks = new TextButton("Tasks", skin, "small");
        TextButton social = new TextButton("Social", skin, "small");
        TextButton dashboard = new TextButton("Dashboard", skin, "small");

        toolbar.addActor(tasks);
        toolbar.addActor(social);
        toolbar.addActor(dashboard);

        mainTable.add(toolbar).top().expand();

        toolbarBtns.add(tasks, social, dashboard);
        toolbarBtns.setMaxCheckCount(1);


    }
}
