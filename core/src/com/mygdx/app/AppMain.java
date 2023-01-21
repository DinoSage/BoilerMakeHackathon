package com.mygdx.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.app.screens.HomeScreen;
import com.mygdx.app.screens.LoginScreen;

public class AppMain extends Game {
	SpriteBatch batch;
	Texture img;

	Screen homeScreen;
	Screen loginScreen;

	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		homeScreen = new HomeScreen();
		loginScreen = new LoginScreen();
		this.setScreen(loginScreen);
	}

	@Override
	public void render () {
		ScreenUtils.clear(.2f, .2f, .2f, 1);
		super.render();
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
