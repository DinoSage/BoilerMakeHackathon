package com.mygdx.app;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class User implements Serializable {
    private String username;
    private String password;
    private final long start;
    private double hours;
    private int percentTaskCompleted;

    private Leaderboard leaderboard;

    //private Array<Task> tasks;
    private SArray<Task> tasks;

    private int streakCounter;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        //this.tasks = new Array<>();
        this.tasks = new SArray<>();
        this.percentTaskCompleted = 0;
        this.hours = 0.0;
        this.streakCounter = 0;
        start = System.currentTimeMillis();

        this.leaderboard = new Leaderboard(this);
    }

    public User(String username, String password, double hours, int percentTaskCompleted, SArray<Task> tasks, int streakCounter) {
        this.username = username;
        this.password = password;

        //this.tasks = tasks;
        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.hours = hours;
        this.streakCounter = streakCounter;
        start = System.currentTimeMillis();

        this.leaderboard = new Leaderboard(this);

    }

    public User(int percentTaskCompleted, double hours, SArray<Task> tasks, int streakCounter) {
        //this.tasks = tasks;
        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.hours = hours;
        this.streakCounter = streakCounter;
        start = System.currentTimeMillis();

        this.leaderboard = new Leaderboard(this);

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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double elapsedTimeHours() {
        long now = System.currentTimeMillis();
        return (now - start) / (60.0 * 60.0 * 1000.0);
    }

    public Array<Task> getTasks() {
        return tasks;
    }

    public void setTasks(SArray<Task> tasks) {
        //this.tasks = tasks;
        this.tasks = tasks;
    }

    public SArray<Task> updateTasks(Task task) {
        //tasks.add(task);
        tasks.add(task);
        return tasks;
    }

    public int getNumberOfTasks(Array<Task> tasks) {
        return tasks.size;
    }

    public int getNumberOfTasksCompleted(Array<Task> tasks) {
        int counter = 0;

        for (int i = 0; i < tasks.size; i++) {
            if (tasks.get(i).isTaskComplete()) {
                counter++;
            }
        }
        return counter;
    }


    @Override
    public boolean equals(Object o) {
        return start == this.start && hours == this.hours && percentTaskCompleted == this.percentTaskCompleted && streakCounter == this.streakCounter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, hours, percentTaskCompleted, tasks, streakCounter);
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

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
