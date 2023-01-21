package com.mygdx.app;

public class Task {

    private String name;
    private boolean complete;

    public Task (String name, boolean complete) {
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
