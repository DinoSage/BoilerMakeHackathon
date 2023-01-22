package com.mygdx.app;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;
import java.io.Serializable;

public class Task extends Label implements Serializable {

    public static Skin skin;

    private String name;
    private boolean complete;

    public Task (String name, boolean complete) {
        super(name, skin);
        this.name = name;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTaskComplete() {
        return complete;
    }

    public void setTask(boolean task) {
        this.complete = complete;
    }
}
