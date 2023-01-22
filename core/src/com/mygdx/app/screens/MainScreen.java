package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
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
import org.w3c.dom.Text;

public class MainScreen extends UIScreen {

    public MainScreen() {
        super(1000, 500);
    }

    @Override
    protected void setup() {
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

        final List taskList = new List(skin);

        Task.skin = skin;
        Array<Task> taskArray = new Array<>();
        for (int i = 0; i < 10; i ++) {
            final Task task = new Task("hello", false);

            task.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Dialog popup = new Dialog("Edit Task", skin);

                    // Edit Task Label
                    Label edit = new Label("Editing Task", skin);
                    popup.text(edit);

                    // Task name field
                    TextField text = new TextField(task.getName(), skin);
                    popup.add(text).colspan(2);
                    popup.row();

                    // Buttons
                    TextButton editBtn = new TextButton("Edit", skin);
                    TextButton deleteBtn = new TextButton("Delete", skin);
                    CheckBox complete = new CheckBox("Complete?", skin);

                    popup.add(complete);
                    popup.add(editBtn);
                    popup.row();
                    popup.add(deleteBtn).colspan(2);

                    popup.show(stage);
                }
            });

            taskArray.add(task);
        }

        taskList.setItems(taskArray);

        taskList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Task task = (Task) taskList.getSelected();

                // Create Popup Window
                System.out.println("List Clicked!");
                Dialog popup = new Dialog("Edit Task", skin);

                popup.setDebug(true);

                // Edit Task Label
                Label edit = new Label("Editing Task", skin);
                popup.add(edit).colspan(2);
                popup.row();

                // Task name field
                TextField text = new TextField(task.getName(), skin);
                popup.add(text).colspan(2).expandX().fillX();
                popup.row();

                // Buttons
                TextButton editBtn = new TextButton("Edit", skin, "small");
                TextButton deleteBtn = new TextButton("Delete", skin, "small");
                TextButton closeBtn = new TextButton("Close", skin, "small");

                Table completeTable = new Table();
                completeTable.setDebug(AppConstants.DEBUG);
                Label completeLabel = new Label("Complete?", skin);
                CheckBox complete = new CheckBox("", skin);

                popup.add(editBtn);
                popup.add(complete);
                popup.row();
                popup.add(deleteBtn);
                popup.add(closeBtn);

                popup.show(stage);
            }
        });

        taskTable.add(taskList).expand().fill();

        // Add UI Functionality


    }
}
