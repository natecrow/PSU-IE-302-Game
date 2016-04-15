package com.psu.ie302.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

public class MainMenuScreen implements Screen {

	final IE302Game game;
	OrthographicCamera camera;
	
	
	public MainMenuScreen(IE302Game gam) {
		this.game = gam;
		//this.camera = new OrthographicCamera();
		//this.camera.setToOrtho(false, 640, 480);
		
		// create play button
		final TextButton playButton = new TextButton("Play", game.skin, "default");
		playButton.setWidth(200f);
		playButton.setHeight(30f);
		playButton.setPosition(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2) + 20f, Align.center);
		
		// create instructions button
		final TextButton instructionsButton = new TextButton("Instructions", game.skin, "default");
		instructionsButton.setWidth(200f);
		instructionsButton.setHeight(30f);
		instructionsButton.setPosition(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2) - 20f, Align.center);
		
		
		// switch to the main game screen when play button is clicked
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				initGameInfo();
				game.setScreen(new GameScreen(game));
	            dispose();
			}
		});
		
		// add play button to stage
		game.stage.addActor(playButton);
		game.stage.addActor(instructionsButton);
		
		Gdx.input.setInputProcessor(game.stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//      camera.update();
//      game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.stage.draw();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	// creates player, products, and questions
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
		this.game.questionIter = 0;
		
	}
	
	@Override
	public void show() {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}

}
