package com.mygdx.app.screens;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.app.*;
import com.mygdx.app.Views.TaskView;

public class MainScreen extends UIScreen {

    AppMain app;

    public MainScreen(AppMain appMain) {
        super(1000, 500);
        this.app = appMain;
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
        TextButton account = new TextButton("Account", skin, "radio");

        // Add Buttons to toolbar
        toolbar.addActor(tasks);
        toolbar.addActor(social);
        toolbar.addActor(dashboard);
        toolbar.addActor(account);

        // Create button group to manage buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(tasks, social, dashboard, account);
        buttons.setMaxCheckCount(1);

        mainTable.add(toolbar).top();
        mainTable.row();


        mainTable.add(TaskView.createTasksTable(stage)).expand().fill();
        mainTable.row();


    }
}
