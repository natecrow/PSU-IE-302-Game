package com.psu.ie302.game.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.Json;
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
		
		// Read products from JSON file into an Array List
		Json json = new Json();
		@SuppressWarnings("unchecked")
		ArrayList<Product> productsList = json.fromJson(ArrayList.class, Product.class,
				Gdx.files.local("products.json"));
		
		// Convert Array List of products into a regular array
		Product[] products = new Product[productsList.size()];
		products = productsList.toArray(products);
		
		// create array of questions
		Question[] questions = {
				new QuestionSingleProduct(products[0]),
				new QuestionMultipleProducts(products[1], products[2], 3),
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
