package com.mygdx.app.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;
import com.mygdx.app.Leaderboard;
import com.mygdx.app.User;

public class SocialView {

    public static Table createSocialView(Stage stage) {
        // Neccessary Information
        final AssetStorage assets = AssetStorage.getInstance();
        final Skin skin = AssetStorage.getInstance().skin;

        Table socialTable = new Table();
        socialTable.setDebug(AppConstants.DEBUG);

        // Find Leaderboard
        Leaderboard leaderboard = assets.currentUser.getLeaderboard();
        float spacing = Gdx.graphics.getWidth() / 10;
        socialTable.pad(spacing);

        for (User user : leaderboard.getUsers()) {
            socialTable.row().space(spacing/2, spacing, spacing/2, spacing);

            // Create Name Label
            Label usernameLabel = new Label(user.getUsername(), skin, "big");
            Label numberOfHours = new Label(String.format("%.2f", 0f), skin, "big");
            ProgressBar progress = new ProgressBar(0, leaderboard.getHoursPerStreak(), leaderboard.getHoursPerStreak() * .001f, false, skin);
            Label streakLabel  = new Label(String.valueOf(user.getStreakCounter()), skin, "big");
            //Image crown = new Image();
            socialTable.add(usernameLabel);
            socialTable.add(numberOfHours);
            socialTable.add(progress).expandX().fill();
            socialTable.add(streakLabel);

            progress.setValue(2f);
        }



        // Return final table
        return socialTable;
    }
}
