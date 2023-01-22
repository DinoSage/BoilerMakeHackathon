package com.mygdx.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.app.screens.HomeScreen;
import com.mygdx.app.screens.LoginScreen;
import com.mygdx.app.screens.MainScreen;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class AppMain extends Game {

	public static AppMain instance;

	AssetStorage assets;

	public static final int HOME_SCREEN = 0;
	HomeScreen homeScreen = null;

	public static final int LOGIN_SCREEN = 1;
	LoginScreen loginScreen = null;

	public static final int MAIN_SCREEN = 2;
	MainScreen mainScreen = null;

	ServerMessage request;
	ServerMessage response;
	ObjectOutputStream clientOut;
	ObjectInputStream serverIn;
	public SocketClient clientManager;

	@Override
	public void create () {

		// Create Asset Storage
		assets = AssetStorage.getInstance();
		assets.startLoad();
		Task.skin = assets.skin;

		//switchScreen(1);

		clientManager = new SocketClient();
		//clientManager.testServerRequest();
		//clientManager.loginRequest("Nareynater", "password");
		//testServerRequest();

		// Dummy User w/ Dummy Tasks
		User user = new User("user1", "passy");
		ArrayList<User> users = new ArrayList<>();
		users.add(user);

		Leaderboard tempBoard = new Leaderboard(user);
		tempBoard.setHoursPerStreak(4);
		user.setLeaderboard(tempBoard);

		for (int i = 0; i < 3; i++) {
			User tempUser = new User("user" + i, "passy");
			tempUser.setLeaderboard(tempBoard);
			users.add(tempUser);
		}

		tempBoard.setUsers(users);

		//Array<Task> taskList = new Array<>();
		SArray<Task> taskList = new SArray<>();
		for (int i = 0; i < 20; i++) {
			String name = "Task#" + i;
			Task task = new Task(name, false);

			taskList.add(task);
		}

		user.setTasks(taskList);

		//assets.currentUser = user;

		switchScreen(LOGIN_SCREEN);
	}

	@Override
	public void render () {
		ScreenUtils.clear(.2f, .2f, .2f, 1);
		assets.render();
		super.render();
	}
	
	@Override
	public void dispose () {

	}

	public void switchScreen(int screen) {
		switch (screen) {
			case HOME_SCREEN:
				homeScreen = new HomeScreen();
				setScreen(homeScreen);
				break;
			case LOGIN_SCREEN:
				loginScreen = new LoginScreen(this);
				setScreen(loginScreen);
				break;
			case MAIN_SCREEN:
				mainScreen = new MainScreen(this);
				setScreen(mainScreen);
				break;
		}
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
