package com.psu.ie302.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.questions.QuestionInflationType1;
import com.psu.ie302.game.questions.QuestionInflationType2;
import com.psu.ie302.game.questions.QuestionMultipleProducts;
import com.psu.ie302.game.questions.QuestionSingleProduct;

public class IE302Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLUE);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, "Hello World", 200, 200);
		batch.end();
		
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
			try {
				ans = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			questions[i].checkAndDisplayAnswerResults(ans, player);
			
			// display how much money player has so far
			System.out.println("Your money so far: $" + player.getMoney() + "\n");
		}
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
}
