package com.psu.ie302.game.desktop;

import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psu.ie302.game.IE302Game;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PSU IE 302 Game";
		config.width = IE302Game.VirtualWidth;
		config.height = IE302Game.VirtualHeight;
		new LwjglApplication(new IE302Game(), config);
	}
}
