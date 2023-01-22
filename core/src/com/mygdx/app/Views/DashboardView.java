package com.mygdx.app.Views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Leaderboard;

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
    }


}
