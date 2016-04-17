package com.psu.ie302.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.questions.QuestionInflationType1;
import com.psu.ie302.game.questions.QuestionInflationType2;
import com.psu.ie302.game.questions.QuestionMultipleProducts;
import com.psu.ie302.game.questions.QuestionSingleProduct;
import com.psu.ie302.game.screens.AbstractScreen;

public class MainMenuScreen extends AbstractScreen {

	final private TextButton btnPlay;
	final private TextButton btnInstructions;
	
	public MainMenuScreen(final IE302Game game) {
		
		super(game);
		
		// create play button
		this.btnPlay = new TextButton("Play", game.skin, "default");
		this.btnPlay.setWidth(200f);
		this.btnPlay.setHeight(30f);
		this.btnPlay.setPosition(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2) + 20f, Align.center);
		
		// create instructions button
		this.btnInstructions = new TextButton("Instructions", game.skin, "default");
		this.btnInstructions.setWidth(200f);
		this.btnInstructions.setHeight(30f);
		this.btnInstructions.setPosition(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2) - 20f, Align.center);
		
		// switch to a game screen when play button is clicked
		this.btnPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				initGameInfo();
				switchToQuestionScreen(game);
	            dispose();
			}
		});
		
		// add everything to stage
		stage.addActor(this.btnPlay);
		stage.addActor(this.btnInstructions);
	}

	/* 
	 * creates player, products, and questions
	 */
	private void initGameInfo() {
		
		// create the player object
		this.game.player = new Player();
		
		// Read products from JSON file into an Array List
		Json json = new Json();
		@SuppressWarnings("unchecked")
		ArrayList<Product> productsList = json.fromJson(ArrayList.class, Product.class,
				Gdx.files.local("products.json"));
		
		// Convert Array List of products into a regular array
		this.game.products = new Product[productsList.size()];
		this.game.products = productsList.toArray(this.game.products);
		
		// create array of questions
		this.game.questions = new Question[8];
		this.game.questions[0] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)]);
		this.game.questions[1] = new QuestionInflationType1();
		this.game.questions[2] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)]);
		this.game.questions[3] = new QuestionInflationType1();
		this.game.questions[4] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
				this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
				3);
		this.game.questions[5] = new QuestionInflationType2();
		this.game.questions[6] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
						this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
						3);
		this.game.questions[7] = new QuestionInflationType2();
		
		// display how much money player has so far
		//System.out.println("Your starting amount: $" + this.game.player.getScore() + "\n");
		
		// set question iterator
		this.game.qItr = 0;
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
