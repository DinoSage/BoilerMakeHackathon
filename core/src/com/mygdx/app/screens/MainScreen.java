package com.mygdx.app.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Task;
import com.mygdx.app.UIScreen;
import com.mygdx.app.Views.SocialView;
import com.mygdx.app.Views.TaskView;

public class MainScreen extends UIScreen {

    public MainScreen() {
        super(1000, 500);
    }

    @Override
    protected void setup() {
        final AssetStorage assets = AssetStorage.getInstance();

        // Load Skin
        final Skin skin = AssetStorage.getInstance().skin;

        // Add a horizontal group of Buttons as Toolbar
        HorizontalGroup toolbar = new HorizontalGroup();

        // Create Toolbar Buttons
        final TextButton tasks = new TextButton("Tasks", skin, "radio");
        TextButton social = new TextButton("Social", skin, "radio");
        TextButton dashboard = new TextButton("Dashboard", skin, "radio");

        // Add Buttons to toolbar
        toolbar.addActor(tasks);
        toolbar.addActor(social);
        toolbar.addActor(dashboard);

        // Create button group to manage buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(tasks, social, dashboard);
        buttons.setMaxCheckCount(1);

        mainTable.add(toolbar).top();
        mainTable.row();


        mainTable.add(SocialView.createSocialView(stage)).expand().fill();
        mainTable.row();


    }
}
