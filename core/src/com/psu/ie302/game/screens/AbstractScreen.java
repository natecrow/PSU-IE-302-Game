package com.psu.ie302.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.psu.ie302.game.IE302Game;

public abstract class AbstractScreen implements Screen {

	protected IE302Game game;
	protected Stage stage;
	
	protected AbstractScreen(IE302Game game) {
		this.game = game;
		
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT);
		
		stage = new Stage(new FitViewport(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT, camera));
		Gdx.input.setInputProcessor(this.stage);
	}
	
	/*
	 *	Switch to the game screen corresponding to the given question type 
	 */
	// TODO: handle the case where there are no more products and so we must switch to the end screen
	protected void switchToQuestionScreen(IE302Game game) {
		if (game.questions[game.qItr].getClass().getName() == "com.psu.ie302.game.questions.QuestionSingleProduct") {
			game.setScreen(new SingleProductQuestionScreen(game));
		}
		else if (game.questions[game.qItr].getClass().getName() == "com.psu.ie302.game.questions.QuestionMultipleProducts") {
			game.setScreen(new MultipleProductsQuestionScreen(game));
		}
		else {	// otherwise it must be an inflation question
			game.setScreen(new InflationQuestionScreen(game));
		}
	}
	
	/*
	 * Overwrites a given table to show the results of the answer.
	 * Also returns true when the player has clicked to move on to the 
	 * next screen
	 */
	protected void displayResultsAndSwitch(Table tbl, String resultTxt) {
		tbl.clearChildren();
		
		// Create and display label for answer results
		final Label labelResults = new Label(resultTxt, game.skin);
		labelResults.setWrap(true);
		labelResults.setAlignment(Align.center);
		
		// Create a button to move on to the next question
		final TextButton btnNextQuestion = new TextButton("Next Question", game.skin);
		btnNextQuestion.setSize(100f, 30f);
		
		// Add widgets to the table
		tbl.defaults().space(10f);
		tbl.add(labelResults).expandX().fill();
		tbl.row();
		tbl.add(btnNextQuestion).size(150f, 30f);
		//tbl.setDebug(true);
		
		// Check if player has clicked 'next' yet.
		// Once they do, then dispose of objects and switch screens to next question.
		btnNextQuestion.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnNextQuestion) {
				game.qItr++;
				dispose();
				switchToQuestionScreen(game);
			}
		});
	}

	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Calling to Stage methods
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override public void show() {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}

}
