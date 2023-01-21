package com.mygdx.app;

import java.util.ArrayList;

public class ServerEnvironment {

    ArrayList<Leaderboard> leaderboards;
    ArrayList<User> users;

    public ServerEnvironment() {
        leaderboards = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addLeaderboard(Leaderboard leaderboard) {
        leaderboards.add(leaderboard);
    }

    public Leaderboard getLeaderboard(Leaderboard leaderboard) {
        for (Leaderboard cLeaderboard : leaderboards) {
            if (cLeaderboard.equals(leaderboard)) {
                return cLeaderboard;
            }
        }
        return null;
    }


    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(User user) {
        for (User cUser : users) {
            if (cUser.equals(user)) {
                return cUser;
            }
        }
        return null;
    }

    public User getUser(String username) {
        for (User cUser : users) {
            if (cUser.getUsername().equals(username)) {
                return cUser;
            }
        }
        return null;
    }

}
