package com.psu.ie302.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psu.ie302.game.IE302Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PSU IE 302 Game";
		config.width = 640;
		config.height = 480;
		
		new LwjglApplication(new IE302Game(), config);
	}
}
