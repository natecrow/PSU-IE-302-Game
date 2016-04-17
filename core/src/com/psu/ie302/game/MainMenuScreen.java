package com.psu.ie302.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.questions.QuestionInflationType1;
import com.psu.ie302.game.questions.QuestionInflationType2;
import com.psu.ie302.game.questions.QuestionMultipleProducts;
import com.psu.ie302.game.questions.QuestionSingleProduct;
import com.psu.ie302.game.screens.AbstractScreen;

public class MainMenuScreen extends AbstractScreen {

	private Table table;
	
	public MainMenuScreen(final IE302Game game) {
		super(game);
		
		// create play button
		final TextButton btnPlay;
		btnPlay = new TextButton("Play", game.skin, "default");
		
		// create instructions button
		final TextButton btnInstructions;
		btnInstructions = new TextButton("Instructions", game.skin, "default");

		// create table
		table = new Table();
		table.setFillParent(true);
		stage.addActor(this.table);
		table.setDebug(true);
		
		// size and add widgets to the table
		table.defaults().size(200f, 50f);
		table.add(btnPlay).space(10);
		table.row();
		table.add(btnInstructions).space(10);
		
		// switch to a game screen when play button is clicked
		btnPlay.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnPlay) {
				initGameInfo();
				switchToQuestionScreen(game);
	            dispose();
			}
		});
	}

	/* 
	 * creates player, products, and questions
	 */
	private void initGameInfo() {
		
		// create the player object
		game.player = new Player();
		
		// Read products from JSON file into an Array List
		Json json = new Json();
		@SuppressWarnings("unchecked")
		ArrayList<Product> productsList = json.fromJson(ArrayList.class, Product.class,
				Gdx.files.local("products.json"));
		
		// Convert Array List of products into a regular array
		game.products = new Product[productsList.size()];
		game.products = productsList.toArray(this.game.products);
		
		// create array of questions
		game.questions = new Question[8];
		game.questions[1] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)]);
		game.questions[0] = new QuestionInflationType1();
		game.questions[2] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)]);
		game.questions[3] = new QuestionInflationType1();
		game.questions[4] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
				this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
				3);
		game.questions[5] = new QuestionInflationType2();
		game.questions[6] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
						this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length)],
						3);
		game.questions[7] = new QuestionInflationType2();
		
		// display how much money player has so far
		//System.out.println("Your starting amount: $" + this.game.player.getScore() + "\n");
		
		// set question iterator
		game.qItr = 0;
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
