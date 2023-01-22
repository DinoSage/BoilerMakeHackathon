package com.mygdx.app.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Task;
import com.mygdx.app.UIScreen;

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

        // Create Table for Task View
        Table taskTable = new Table();
        taskTable.setDebug(true);

        mainTable.add(toolbar).top();
        mainTable.row();
        mainTable.add(taskTable).expand().fill();

        final List taskList = new List(skin) {

        };
        taskList.setItems(assets.currentUser.getTasks());

        taskList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                final Task task = (Task) taskList.getSelected();

                // Create Popup Window
                final Dialog popup = new Dialog("Edit Task", skin);

                popup.setDebug(true);

                // Edit Task Label
                Label edit = new Label("Editing Task", skin);
                popup.add(edit).colspan(2);
                popup.row();

                // Task name field
                final TextField text = new TextField(task.getName(), skin);
                popup.add(text).colspan(2).expandX().fillX();
                popup.row();

                // Buttons
                TextButton editBtn = new TextButton("Edit", skin, "small");
                TextButton deleteBtn = new TextButton("Delete", skin, "small");
                TextButton closeBtn = new TextButton("Close", skin, "small");

                Table completeTable = new Table();
                completeTable.setDebug(AppConstants.DEBUG);
                Label completeLabel = new Label("Complete?", skin);
                final CheckBox complete = new CheckBox("", skin);
                complete.setChecked(task.isTaskComplete());

                popup.add(editBtn);
                popup.add(complete);
                popup.row();
                popup.add(deleteBtn);
                popup.add(closeBtn);

                popup.show(stage);

                // Button Functionality
                editBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        task.setComplete(complete.isChecked());
                        task.setName(text.getText());

                        if (complete.isChecked()) {
                            boolean success = assets.currentUser.getTasks().removeValue(task, false);
                            taskList.clearItems();
                            taskList.setItems(assets.currentUser.getTasks());
                        }
                        popup.hide();
                    }
                });

                deleteBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        boolean success = assets.currentUser.getTasks().removeValue(task, false);
                        taskList.clearItems();
                        taskList.setItems(assets.currentUser.getTasks());
                        popup.hide();
                    }
                });

                closeBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        popup.hide();
                    }
                });
            }
        });

        taskTable.add(taskList).expand().fill();

        // Add UI Functionality


    }
}
