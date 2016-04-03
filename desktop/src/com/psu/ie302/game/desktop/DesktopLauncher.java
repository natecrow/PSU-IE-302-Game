package com.psu.ie302.game.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psu.ie302.game.IE302Game;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.questions.QuestionSingleProduct;

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
		
		// create array of sample products
		// TODO: automatically read these in from an XML file eventually
		Product[] sampleProducts = {
				new Product("Aperture Science Handheld Portal Device / Portal Gun",
						"Aperture Science, Inc.",
						"Experimental tool used to place two portals which objects"
								+ " can through from one to the other.",
						5, 3),
				new Product("Krabby Patty",
						"The Krusty Krab",
						"The most famous sandwich in Bikini Bottom!",
						4, 5),
				new Product("Dunder Mifflin Paper (Ream)",
						"Dunder Mifflin Paper Company, Inc.",
						"500 sheets of bright white copy paper." + 
						" Works well in copiers, inkjet or laser printers.",
						2, 1)
		};
		
		// create array of questions
		QuestionSingleProduct[] questions = {
				new QuestionSingleProduct(sampleProducts[0]),
				new QuestionSingleProduct(sampleProducts[1]),
				new QuestionSingleProduct(sampleProducts[2])
		};
		
		// player's money pool
		int money = 500;
		
		// display how much money player has so far
		System.out.println("Your starting amount: $" + money + "\n");
		
		for (int i = 0; i < questions.length; i++) {
			
			// display question prompt
			System.out.println(questions[i].getQuestionPrompt());
			
			// input answer
			String ans = null;
			ans = reader.readLine();
			
			// check answer and adjust score accordingly
			if (questions[i].checkAnswer(ans)) {
				if (ans.equals("Y")) {
					money += 100;
					System.out.println("Wise investment - "
							+ "the product paid off! You've earned $100.\n");
				} else {
					System.out.println("That product ended up failing, so"
							+ " good thing you didn't invest in it!\n");
				}
			} else {
				if (ans.equals("Y")) {
					money -= 100;
					System.out.println("Too bad, the product flopped. You lost $100.\n");
				} else {
					System.out.println("Whoops! That product actually ended up doing well. "
							+ "You missed out on the payoff, "
							+ "but at least you didn't lose anything.\n");
				}
			}
			
			// display how much money player has so far
			System.out.println("Your money so far: $" + money + "\n");
		}
	}
}
