package com.psu.ie302.game.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psu.ie302.game.IE302Game;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PSU IE 302 Game";
		config.width = 640;
		config.height = 480;
		
		new LwjglApplication(new IE302Game(), config);
		
		
		// TODO: make separate classes for this code in the core folder
		// set up input reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// create array of questions + answers
		// the answer to each question is in the row below in the same column
		String[][] QAs = {
				{"Product 1. <name>\n <description>\n <give MARR and IRR> ",
				 "Product 2. <name>\n <description>\n <give MARR and IRR> ",
				 "Product 3. <name>\n <description>\n <give MARR and IRR> "},
				{"Y",
				 "N",
				 "Y"}
		};
		
		// player's money pool
		int money = 500;
		
		for (int i = 0; i < QAs[0].length; i++) {
			
			// display how much money player has so far
			System.out.println("Your money so far: " + money + "\n");
			
			// question
			System.out.println(QAs[0][i]
					+ "Do you want to invest in this product? Y/N.");
			
			// input answer
			String ans = null;
			ans = reader.readLine();
			
			// if answer is wrong, prompt user to re-enter it
			while ( !(ans.equals("Y")) && !(ans.equals("N")) ) {
				System.out.println("Invalid input. Enter 'Y' or 'N'.");
				ans = reader.readLine();
			}
			
			if (ans.equals(QAs[1][i])) {
				money += 100;
				System.out.println("Good choice - "
						+ "the product was a success! You've earned $100!\n");
			} else {
				money -= 50;
				System.out.println("Too bad, the product flopped. You lost $50.\n");
			}
		}
	}
}
