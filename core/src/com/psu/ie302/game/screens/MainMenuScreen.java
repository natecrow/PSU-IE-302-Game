package com.psu.ie302.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.psu.ie302.game.IE302Game;
import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.questions.QuestionInflationType1;
import com.psu.ie302.game.questions.QuestionInflationType2;
import com.psu.ie302.game.questions.QuestionMultipleProducts;
import com.psu.ie302.game.questions.QuestionSingleProduct;

public class MainMenuScreen extends AbstractScreen {
	
	public MainMenuScreen(final IE302Game game) {
		super(game);
		
		// create table for the title
		final Table tableTitle = new Table();
		tableTitle.setPosition(0, IE302Game.VIRTUAL_HEIGHT / 2);
		tableTitle.setSize(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT / 2);
		stage.addActor(tableTitle);
		tableTitle.setDebug(true);
		
		// create title label
		//TODO: add bigger font to skin or create a title image to import
		final Label labelTitle = new Label("PSU IE Shark Tank", game.skin);
		labelTitle.setFontScale(3.0f);
		labelTitle.setAlignment(Align.center);
		
		// add title label to its table
		tableTitle.add(labelTitle).size(500f, 200f);
		
		// create table for menu buttons
		final Table tableBtns = new Table();
		tableBtns.setPosition(0, 0);
		tableBtns.setSize(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT / 2);
		stage.addActor(tableBtns);
		tableBtns.setDebug(true);
		
		// create play button
		final TextButton btnPlay;
		btnPlay = new TextButton("Play", game.skin, "default");
		
		// create instructions button
		final TextButton btnInstructions;
		btnInstructions = new TextButton("Instructions", game.skin, "default");
		
		// size and add menu buttons to their table
		tableBtns.defaults().size(200f, 50f);
		tableBtns.add(btnPlay).space(10);
		tableBtns.row();
		tableBtns.add(btnInstructions).space(10);
		
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
		game.questions[1] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)]);
		game.questions[0] = new QuestionInflationType1();
		game.questions[2] = new QuestionSingleProduct(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)]);
		game.questions[3] = new QuestionInflationType1();
		game.questions[4] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)],
				this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)],
				3);
		game.questions[5] = new QuestionInflationType2();
		game.questions[6] = new QuestionMultipleProducts(this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)],
						this.game.products[ProductCalculations.randomlyPickProduct(this.game.products.length - 1)],
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
