package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.app.UIScreen;

public class LoginScreen extends UIScreen {

    // Constructor
    public LoginScreen() {
        super(500, 100);
    }
    @Override
    protected void setup() {
        Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));

        // Screen Title
        Label title = new Label("Log In!", skin);
        title.setStyle(skin.get("big", Label.LabelStyle.class));

        // Email
        Label emailLabel= new Label("Email: ", skin);
        TextField emailInput = new TextField("", skin);

        // Password
        Label passwordLabel= new Label("Password: ", skin);
        TextField passwordInput = new TextField("", skin);

        mainTable.add(title).colspan(2);
        mainTable.row();
        mainTable.add(emailLabel).center().expandX().uniform();
        mainTable.add(emailInput).expandX().fillX().uniform();
        mainTable.row();

        mainTable.add(passwordLabel).center().expandX().uniform();
        mainTable.add(passwordInput).expandX().fillX().uniform();

        // Add Buttons
        TextButton loginBtn = new TextButton("Login In", skin);
        loginBtn.setStyle(skin.get("small", TextButton.TextButtonStyle.class));
        mainTable.row();
        mainTable.add(loginBtn).colspan(2).prefSize(title.getPrefWidth(), title.getPrefHeight());
    }
}
