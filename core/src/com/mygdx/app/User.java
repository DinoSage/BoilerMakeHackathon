package com.mygdx.app;

import java.util.ArrayList;
import java.util.Objects;


public class User {
    private final long start;
    private int points;
    private int percentTaskCompleted;

    private ArrayList<Task> tasks;

    private int streakCounter;

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

    @Override
    public boolean equals(Object o) {
        return start == this.start && points == this.points && percentTaskCompleted == this.percentTaskCompleted && streakCounter == this.streakCounter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, points, percentTaskCompleted, tasks, streakCounter);
    }
}
