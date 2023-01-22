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

    private boolean productive = false;
    private long productivityStart = 0;
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

    public SArray<Task> getTasks() {
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

    public int getNumberOfTasks(SArray<Task> tasks) {
        return tasks.size;
    }

    public int getNumberOfTasksCompleted(SArray<Task> tasks) {
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

    public boolean toggleProductivity() {
        refreshHours();
        productive = !productive;
        if (productive) {
            productivityStart = System.currentTimeMillis();
        }
        return productive;
    }

    public boolean isProductive() {
        return productive;
    }

    public void refreshHours() {
        if (productive) {
            double productivityDuration = (double) (System.currentTimeMillis() - productivityStart);
            hours += productivityDuration / 3600000;
            productivityStart = System.currentTimeMillis();
        }
    }

    public long getProductivityStart() {
        return productivityStart;
    }

    //public void overrideUser(User user) {
    //    this = user;
    //}

    public void overrideUser(User user) {
        System.out.println("Old UserName: "+ this.username);
        System.out.println("Old Size: "+ this.getTasks().size);
        System.out.println("New UserName: "+ user.username);
        System.out.println("New Size:" + user.getTasks().size);

        this.username = user.getUsername();
        this.password = user.getPassword();
        //this.start = start;
        this.hours = user.getHours();
        this.percentTaskCompleted = user.getPercentTaskCompleted();
        this.leaderboard = user.getLeaderboard();
        this.tasks = user.getTasks();
        this.productive = user.isProductive();
        this.productivityStart = user.getProductivityStart();
        this.streakCounter = user.getStreakCounter();
    }
}
