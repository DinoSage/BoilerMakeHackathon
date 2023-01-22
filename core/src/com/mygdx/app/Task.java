package com.mygdx.app;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.Serializable;

public class Task extends Label implements Serializable {

    private static int ID_GENERATOR = 1;

    public static Skin skin;

    private String name;
    private boolean complete;
    private int ID;

    public Task (String name, boolean complete) {
        super(name, skin);
        this.name = name;
        this.complete = complete;
        this.ID = ID_GENERATOR;
        ID_GENERATOR += 1;
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

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task other = (Task) o;
            if (other.name.equals(this.name) && other.ID == this.ID) {
                return true;
            }
        }

        return false;
    }
}
