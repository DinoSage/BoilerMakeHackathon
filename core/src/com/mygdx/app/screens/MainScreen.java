package com.mygdx.app.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.app.*;
import com.mygdx.app.Views.DashboardView;
import com.mygdx.app.Views.SocialView;
import com.mygdx.app.Views.TaskView;

import java.util.Currency;

public class MainScreen extends UIScreen {

    AppMain app;

    public static final int TASK_VIEW = 0;
    public static final int SOCIAL_VIEW = 1;
    public static final int DASHBOARD_VIEW = 2;
    public static final int ACCOUNT_VIEW = 3;


    public static Label timeLabel;

    Table currentView;

    public MainScreen(AppMain appMain) {
        super(1000, 500);
        this.app = appMain;
        currentView = new TaskView(stage);
    }

    @Override
    protected void setup() {
        //updateView();
        final AssetStorage assets = AssetStorage.getInstance();

        // Load Skin
        final Skin skin = AssetStorage.getInstance().skin;

        // Add a horizontal group of Buttons as Toolbar
        HorizontalGroup toolbar = new HorizontalGroup();

        // Create Toolbar Buttons
        final TextButton tasks = new TextButton("Tasks", skin, "radio");
        TextButton social = new TextButton("Social", skin, "radio");
        TextButton dashboard = new TextButton("Dashboard", skin, "radio");
        TextButton account = new TextButton("Account", skin, "radio");

        // Add Buttons to toolbar
        toolbar.addActor(tasks);
        toolbar.addActor(social);
        toolbar.addActor(dashboard);
        toolbar.addActor(account);

        // Create button group to manage buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(tasks, social, dashboard, account);
        buttons.setMaxCheckCount(1);

        // Toolbar Functionality
        tasks.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchView(TASK_VIEW);
            }
        });

        social.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchView(SOCIAL_VIEW);
            }
        });

        dashboard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchView(DASHBOARD_VIEW);
            }
        });

        account.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("ACCOUNT");
            }
        });

        mainTable.add(toolbar).top();
        mainTable.row();
        mainTable.add(currentView).expand().fill();
    }

    public void switchView(int view) {
        mainTable.clearChildren(true);
        timeLabel = null;
        switch (view) {
            case TASK_VIEW:
                currentView = new TaskView(stage);
                break;
            case SOCIAL_VIEW:
                currentView = new SocialView(stage);
                break;
            case DASHBOARD_VIEW:
                currentView = new DashboardView(stage);
                break;
        }
        setup();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        AssetStorage.getInstance().currentUser.refreshHours();

        // Setup Time Tracker
        double timeElapsed = AssetStorage.getInstance().currentUser.getHours();
        int hours = (int) timeElapsed;
        int minutes = (int) ((timeElapsed - hours) * 60);
        int seconds = (int) (((timeElapsed - hours) * 60 - minutes) * 60);
        if (timeLabel != null)
            timeLabel.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
    }

/*    public void updateView() {
        switch (CURRENT_VIEW) {
            case TASK_VIEW:
                currentView = new TaskView(stage);
                break;
            case SOCIAL_VIEW:
                currentView = new SocialView(stage);
                break;
            case DASHBOARD_VIEW:
                currentView = new DashboardView(stage);
                break;
        }
    }
*/
}
