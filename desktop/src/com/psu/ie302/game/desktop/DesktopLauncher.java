package com.psu.ie302.game.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psu.ie302.game.IE302Game;
import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.questions.QuestionInflationType1;
import com.psu.ie302.game.questions.QuestionInflationType2;
import com.psu.ie302.game.questions.QuestionMultipleProducts;
import com.psu.ie302.game.questions.QuestionSingleProduct;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PSU IE 302 Game";
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new IE302Game(), config);
		
		
		// set up input reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// create the player object
		Player player = new Player(500);
		
		// create array of sample products
		// TODO: automatically read these in from an JSON or XML file
		Product[] sampleProducts = {
				new Product("Aperture Science Handheld Portal Device / Portal Gun",
						"Aperture Science, Inc.",
						"Experimental tool used to place two portals which objects"
								+ " can pass through from one to the other."),
				new Product("Krabby Patty",
						"The Krusty Krab",
						"The most famous sandwich in Bikini Bottom!"),
				new Product("Dunder Mifflin Paper (Ream)",
						"Dunder Mifflin Paper Company, Inc.",
						"500 sheets of bright white copy paper." + 
						" Works well in copiers, inkjet or laser printers.")
		};
		
		// create array of questions
		Question[] questions = {
				new QuestionSingleProduct(sampleProducts[0]),
				new QuestionMultipleProducts(sampleProducts[1], sampleProducts[2], 3),
				new QuestionInflationType1(),
				new QuestionInflationType2()
		};
		
		// player's money pool
		player.setMoney(500);
		
		// display how much money player has so far
		System.out.println("Your starting amount: $" + player.getMoney() + "\n");
		
		for (int i = 0; i < questions.length; i++) {
			// display question prompt
			System.out.println(questions[i].getQuestionPrompt());
			
			// input answer
			String ans = null;
			ans = reader.readLine();
			
			questions[i].checkAndDisplayAnswerResults(ans, player);
			
			// display how much money player has so far
			System.out.println("Your money so far: $" + player.getMoney() + "\n");
		}
	}
}
