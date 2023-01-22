package com.mygdx.app.Views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Leaderboard;
import com.mygdx.app.screens.MainScreen;
import jdk.tools.jmod.Main;

public class DashboardView extends Table {

    public DashboardView(Stage stage) {
        // Neccessary Information
        final AssetStorage assets = AssetStorage.getInstance();
        final Skin skin = AssetStorage.getInstance().skin;

        this.setDebug(AppConstants.DEBUG);
        Leaderboard leaderboard = assets.currentUser.getLeaderboard();
        ProgressBar progress = new ProgressBar(0, leaderboard.getHoursPerStreak(), leaderboard.getHoursPerStreak() * .001f, false, skin);

        TextButton startBtn = new TextButton("Start", skin, "small");
        TextButton endBtn = new TextButton("End", skin, "small");
        Label streakLabel = new Label("Streak Count", skin, "big");
        Label streakNum  = new Label(String.valueOf(assets.currentUser.getStreakCounter()), skin, "big");

        // Setup Time Tracker
        double timeElapsed = assets.currentUser.elapsedTimeHours();
        int hours = (int) timeElapsed;
        int minutes = (int) ((timeElapsed - hours) * 60);
        int seconds = (int) (((timeElapsed - hours) * 60 - minutes) * 60);
        String timeLabel = String.format("%02d : %02d : %02d", hours, minutes, seconds);
        Label time = new Label(timeLabel, skin, "big");

        System.out.println("Time:" +  assets.currentUser.elapsedTimeHours());

        this.add(progress).colspan(3).fill().expandX();
        this.row();
        this.add(startBtn).expandX();
        this.add(time).colspan(2).expandX();
        this.row();
        this.add(endBtn).expandX();
        this.add(streakLabel).expandX();
        this.add(streakNum).expandX();
    }


}
