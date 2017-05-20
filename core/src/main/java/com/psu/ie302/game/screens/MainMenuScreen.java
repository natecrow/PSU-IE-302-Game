package com.psu.ie302.game.screens;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

	private Texture titleTex;

	public MainMenuScreen(final IE302Game game) {
		super(game);

		// create table for the title
		final Table tableTitle = new Table();
		tableTitle.setFillParent(true);
		stage.addActor(tableTitle);

		// create title label
		titleTex = new Texture(Gdx.files.internal("penn_state_shark_tank_bg.jpg"));
		final Image titleImg = new Image(titleTex);

		// add title label to its table
		tableTitle.add(titleImg);

		// create table for menu buttons
		final Table tableBtns = new Table();
		tableBtns.setPosition(0, 0);
		tableBtns.setSize(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tableBtns);

		// create play button
		final TextButton btnPlay = new TextButton("Play", game.skin, "default");

		// create instructions button
		final TextButton btnInstructions = new TextButton("Instructions", game.skin, "default");

		// size and add menu buttons to their table
		tableBtns.defaults().size(200f, 50f).space(10f);
		tableBtns.add(btnPlay);
		tableBtns.row();
		tableBtns.add(btnInstructions);

		// debug options
		// tableTitle.setDebug(true);
		// tableBtns.setDebug(true);

		initGameInfo();

		// switch to a game screen when play button is clicked
		btnPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor btnPlay) {
				dispose();
				switchToNextScreen(game);
			}
		});

		// switch to the instructions screen when instructions button is clicked
		btnInstructions.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor btnInstructions) {
				dispose();
				game.setScreen(new InstructionsScreen(game));
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
				Gdx.files.internal("products.json"));

		// Convert Array List of products into a regular array
		game.products = new Product[productsList.size()];
		game.products = productsList.toArray(this.game.products);

		// create four questions
		//@formatter:off
		ProductCalculations.resetPickedProductsList();
		game.questions = new Question[4];
		game.questions[0] = new QuestionSingleProduct(
				game.products[ProductCalculations.randomlyPickProduct(game.products.length - 1)],
				MathUtils.random(2, 4));
		game.questions[1] = new QuestionInflationType1(
					new BigDecimal(MathUtils.random(50, 100000)),
					BigDecimal.valueOf(MathUtils.random(-0.05f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP),
					MathUtils.random(1, 50),
					MathUtils.randomBoolean());
		game.questions[2] = new QuestionMultipleProducts(
				game.products[ProductCalculations.randomlyPickProduct(game.products.length - 1)],
				game.products[ProductCalculations.randomlyPickProduct(game.products.length - 1)], 3);
		game.questions[3] = new QuestionInflationType2();

		// set question iterator
		game.qItr = 0;

	}

	@Override
	public void dispose() {
		super.dispose();
		titleTex.dispose();
	}

}
