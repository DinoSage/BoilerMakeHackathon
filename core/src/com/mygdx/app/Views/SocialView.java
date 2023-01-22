package com.mygdx.app.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Leaderboard;
import com.mygdx.app.User;

public class SocialView extends Table {

    public SocialView(Stage stage) {
        // Neccessary Information
        final AssetStorage assets = AssetStorage.getInstance();
        final Skin skin = AssetStorage.getInstance().skin;

        this.setDebug(AppConstants.DEBUG);

        // Find Leaderboard
        Leaderboard leaderboard = assets.currentUser.getLeaderboard();
        float spacing = Gdx.graphics.getWidth() / 10;
        this.pad(spacing);

        for (User user : leaderboard.getUsers()) {
            this.row().space(spacing/2, spacing, spacing/2, spacing);

            // Create Name Label
            Label usernameLabel = new Label(user.getUsername(), skin, "big");
            Label numberOfHours = new Label(String.format("%.2f", (float) user.getHours()), skin, "big");
            ProgressBar progress = new ProgressBar(0, leaderboard.getHoursPerStreak(), leaderboard.getHoursPerStreak() * .001f, false, skin);
            Label streakLabel  = new Label(String.valueOf(user.getStreakCounter()), skin, "big");
            //Image crown = new Image();
            this.add(usernameLabel);
            this.add(numberOfHours);
            this.add(progress).expandX().fill();
            this.add(streakLabel);

            progress.setValue(2f);
        }
    }
}
