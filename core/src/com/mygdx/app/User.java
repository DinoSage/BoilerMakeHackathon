package com.mygdx.app;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    private final long start;
    private int points;
    private int percentTaskCompleted;

    private ArrayList<Task> tasks;

    private int streakCounter;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.tasks = new ArrayList<>();
        this.percentTaskCompleted = 0;
        this.points = 0;
        this.streakCounter = 0;
        start = System.currentTimeMillis();
    }

    public User(String username, String password, int hours, int percentTaskCompleted, int points, ArrayList<Task> tasks, int streakCounter) {
        this.username = username;
        this.password = password;

        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.points = points;
        this.streakCounter = streakCounter;
        start = System.currentTimeMillis();

    }

    public User(int hours, int percentTaskCompleted, int points, ArrayList<Task> tasks, int streakCounter) {
        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.points = points;
        this.streakCounter = streakCounter;
        start = System.currentTimeMillis();

    }

    public int getStreakCounter() {
        return streakCounter;
    }

    public void setStreakCounter(int streakCounter) {
        this.streakCounter = streakCounter;
    }

    public int getPercentTaskCompleted() {
        return percentTaskCompleted;
    }

    public void setPercentTaskCompleted(int percentTaskCompleted) {
        this.percentTaskCompleted = percentTaskCompleted;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double elapsedTimeHours() {
        long now = System.currentTimeMillis();
        return (now - start) / (60.0 * 60.0 * 1000.0);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> updateTasks(Task task) {
        tasks.add(task);
        return tasks;
    }

    public int getNumberOfTasks(ArrayList<Task> tasks) {
        return tasks.size();
    }

    public int getNumberOfTasksCompleted(ArrayList<Task> tasks) {
        int counter = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isTaskComplete()) {
                counter++;
            }
        }
        return counter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
