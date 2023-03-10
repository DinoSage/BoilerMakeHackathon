package com.mygdx.app;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.app.AppMain;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {

		if (StartOnFirstThreadHelper.startNewJvmIfRequired()) return;
		try {
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setForegroundFPS(60);
			config.setTitle("BoilerMakeHackathon");
			//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
			new Lwjgl3Application(new AppMain(), config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
