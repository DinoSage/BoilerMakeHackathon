package com.mygdx.app.Views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.app.AppConstants;
import com.mygdx.app.AssetStorage;

public class DashboardView extends Table {

    public DashboardView(Stage stage) {
        // Neccessary Information
        final AssetStorage assets = AssetStorage.getInstance();
        final Skin skin = AssetStorage.getInstance().skin;

        this.setDebug(AppConstants.DEBUG);
    }

}
