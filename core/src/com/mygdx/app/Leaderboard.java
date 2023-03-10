package com.mygdx.app;
import java.io.Serializable;
import java.util.ArrayList;


public class Leaderboard implements Serializable {

    private ArrayList<User> users;
    private int hoursPerStreak;
    private User creator;

    public Leaderboard(User user) {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        this.users = users;
        this.hoursPerStreak = 4;
        this.creator = user;
    }

    public Leaderboard(ArrayList<User> users, int hoursPerStreak, User creator) {
        this.users = users;
        this.hoursPerStreak = hoursPerStreak;
        this.creator = creator;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getHoursPerStreak() {
        return hoursPerStreak;
    }

    public void setHoursPerStreak(int hoursPerStreak) {
        this.hoursPerStreak = hoursPerStreak;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }
}
