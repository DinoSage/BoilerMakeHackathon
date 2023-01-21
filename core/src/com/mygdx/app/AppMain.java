package com.mygdx.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.app.screens.HomeScreen;
import com.mygdx.app.screens.LoginScreen;
import java.io.*;
import java.net.Socket;

public class AppMain extends Game {
	SpriteBatch batch;
	Texture img;

	Screen homeScreen;
	Screen loginScreen;

	ServerMessage request;
	ServerMessage response;
	ObjectOutputStream clientOut;
	ObjectInputStream serverIn;
	SocketClient clientManager;

	User user;

	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		homeScreen = new HomeScreen();
		loginScreen = new LoginScreen();
		this.setScreen(loginScreen);


		clientManager = new SocketClient();
		//clientManager.testServerRequest();
		clientManager.loginRequest("Nareynater", "password");
		//testServerRequest();
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

	public void testServerRequest() {
		//Search for server host
		System.out.println("Searching for server");
		try (Socket socket = new Socket("10.184.46.131", 9999)) {
			//try (Socket socket = new Socket("10.184.46.131", 9999)) {
			System.out.println("Connected to server");

			//Server Input and Output Streams
			clientOut = new ObjectOutputStream(socket.getOutputStream());
			serverIn = new ObjectInputStream(socket.getInputStream());

			clientOut.writeObject(new ServerMessage("LoginRequest"));
			clientOut.flush();
			response = (ServerMessage) serverIn.readObject();
			System.out.println(response.getResponse());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
