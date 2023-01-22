package com.mygdx.app.Views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AppMain;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Task;

public class TaskView extends Table {

    public TaskView(final Stage stage) {
        // Necessary Information
        final AssetStorage assets = AssetStorage.getInstance();
        final Skin skin = AssetStorage.getInstance().skin;

        // Create Table for Task View
        this.setDebug(true);

        final List taskList = new List(skin);
        taskList.setItems(assets.currentUser.getTasks());

        taskList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Exit if no item selected
                if(taskList.getSelected() == null) {
                    return;
                }

                final Task task = (Task) taskList.getSelected();

                // Create Popup Window
                final Dialog popup = new Dialog("Edit Task", skin) {
                    @Override
                    public void hide(){
                        super.hide();
                        AppMain.instance.clientManager.refreshRequest(assets.currentUser);
                    }
                };

                popup.setDebug(AppConstants.DEBUG);

                // Edit Task Label
                Label edit = new Label("Editing Task", skin);
                popup.add(edit).colspan(2);
                popup.row();

                // Task name field
                final TextField text = new TextField(task.getName(), skin);
                popup.add(text).colspan(2).expandX().fillX();
                popup.row();

                // Buttons
                TextButton updateBtn = new TextButton("Update", skin, "small");
                TextButton deleteBtn = new TextButton("Delete", skin, "small");
                TextButton closeBtn = new TextButton("Close", skin, "small");

                Table completeTable = new Table();
                completeTable.setDebug(AppConstants.DEBUG);
                Label completeLabel = new Label("Complete?", skin);
                final CheckBox complete = new CheckBox("", skin);
                complete.setChecked(task.isTaskComplete());

                popup.add(updateBtn);
                popup.add(complete);
                popup.row();
                popup.add(deleteBtn);
                popup.add(closeBtn);

                popup.show(stage);

                // Button Functionality
                updateBtn.addListener(new ChangeListener() {
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

        this.add(taskList).expand().fill();
        this.row();

        // Task Toolbar
        HorizontalGroup taskTools = new HorizontalGroup();
        TextButton addBtn = new TextButton("Add Task", skin, "small");

        taskTools.addActor(addBtn);
        this.add(taskTools).bottom().expandX();
        this.row();

        // Add Button Functionality
        addBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Create Dialog popup
                final Dialog popup = new Dialog("Add Task", skin) {
                    @Override
                    public void hide(){
                        super.hide();
                        System.out.println("New UserName: "+ assets.currentUser.getUsername());
                        System.out.println("New Size:" + assets.currentUser.getTasks().size);
                        AppMain.instance.clientManager.refreshRequest(assets.currentUser);
                    }
                };

                popup.setDebug(AppConstants.DEBUG);

                // Label for Task Name
                Label nameLabel = new Label("Task Name:", skin, "black");
                nameLabel.setAlignment(Align.left, Align.left);

                // Task Name Input
                final TextField input = new TextField("", skin);

                // Buttons
                TextButton addBtn = new TextButton("Add", skin, "small");
                TextButton closeBtn = new TextButton("Close", skin, "small");

                // Add to Popup
                popup.add(nameLabel).colspan(2).expandX();
                popup.row();
                popup.add(input).colspan(2).expandX().fillX();
                popup.row();
                popup.add(addBtn);
                popup.add(closeBtn);
                popup.row();


                // Button Functionality
                closeBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        popup.hide();
                    }
                });

                addBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        // Define new task
                        Task newTask = new Task(input.getText(), false);
                        assets.currentUser.getTasks().add(newTask);

                        // Refresh List
                        taskList.clearItems();
                        taskList.setItems(assets.currentUser.getTasks());
                        popup.hide();
                    }
                });

                // Show popup
                popup.show(stage);
            }
        });
    }
}
