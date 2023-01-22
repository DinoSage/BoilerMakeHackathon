package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Task;
import com.mygdx.app.UIScreen;
import org.w3c.dom.Text;

public class MainScreen extends UIScreen {

    public MainScreen() {
        super(1000, 500);
    }

    @Override
    protected void setup() {
        // Load Skin
        Skin skin = AssetStorage.getInstance().skin;

        // Add a horizontal group of Buttons as Toolbar
        HorizontalGroup toolbar = new HorizontalGroup();

        // Create Toolbar Buttons
        TextButton tasks = new TextButton("Tasks", skin, "small");
        TextButton social = new TextButton("Social", skin, "small");
        TextButton dashboard = new TextButton("Dashboard", skin, "small");

        // Add Buttons to toolbar
        toolbar.addActor(tasks);
        toolbar.addActor(social);
        toolbar.addActor(dashboard);

        // Create button group to manage buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(tasks, social, dashboard);
        buttons.setMaxCheckCount(1);

        
    }
}
