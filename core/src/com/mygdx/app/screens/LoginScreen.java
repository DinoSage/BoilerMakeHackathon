package com.mygdx.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.mygdx.app.AssetStorage;

import com.mygdx.app.UIScreen;

import javax.swing.event.ChangeEvent;

public class LoginScreen extends UIScreen {

    // Constructor
    public LoginScreen() {
        super(1000, 500);
    }
    @Override
    protected void setup() {
        Skin skin = AssetStorage.getInstance().skin;

        // Screen Title
        Label title = new Label("Log In!", skin);
        title.setStyle(skin.get("big", Label.LabelStyle.class));

        // Email
        Label emailLabel= new Label("Email: ", skin);
        final TextField emailInput = new TextField("", skin);

        // Password
        Label passwordLabel= new Label("Password: ", skin);
        final TextField passwordInput = new TextField("", skin);

        mainTable.add(title).colspan(2);
        mainTable.row();
        mainTable.add(emailLabel).center().expandX().uniform();
        mainTable.add(emailInput).expandX().fillX().uniform();
        mainTable.row();

        mainTable.add(passwordLabel).center().expandX().uniform();
        mainTable.add(passwordInput).expandX().fillX().uniform();

        // Add Buttons
        final TextButton loginBtn = new TextButton("Login In", skin);
        loginBtn.setStyle(skin.get("small", TextButton.TextButtonStyle.class));
        mainTable.row();
        mainTable.add(loginBtn).colspan(2).prefSize(title.getPrefWidth(), title.getPrefHeight());


        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                emailInput.getText();
                passwordInput.getText();
            }
        });

        // Add UI Functionality


    }

}
