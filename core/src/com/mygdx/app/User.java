package com.mygdx.app;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class User implements Serializable {
    private String username;
    private String password;
    private final long start;
    private int points;
    private int percentTaskCompleted;

    private Leaderboard leaderboard;

    private Array<Task> tasks;

    private int streakCounter;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.tasks = new Array<>();
        this.percentTaskCompleted = 0;
        this.points = 0;
        this.streakCounter = 0;
        start = System.currentTimeMillis();

        this.leaderboard = new Leaderboard(this);
    }

    public User(String username, String password, int hours, int percentTaskCompleted, int points, Array<Task> tasks, int streakCounter) {
        this.username = username;
        this.password = password;

        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.points = points;
        this.streakCounter = streakCounter;
        start = System.currentTimeMillis();

        this.leaderboard = new Leaderboard(this);

    }

    public User(int hours, int percentTaskCompleted, int points, Array<Task> tasks, int streakCounter) {
        this.tasks = tasks;
        this.percentTaskCompleted = percentTaskCompleted;
        this.points = points;
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

    public Array<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Array<Task> tasks) {
        this.tasks = tasks;
    }

    public Array<Task> updateTasks(Task task) {
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
        return start == this.start && points == this.points && percentTaskCompleted == this.percentTaskCompleted && streakCounter == this.streakCounter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, points, percentTaskCompleted, tasks, streakCounter);
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
