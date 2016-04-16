package com.psu.ie302.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.psu.ie302.game.IE302Game;

public abstract class AbstractScreen extends Stage implements Screen {

	protected IE302Game game;
	
	protected AbstractScreen(IE302Game game) {
		super(new StretchViewport(800f, 600f, new OrthographicCamera()));
		this.game = game;
	}
	
	/*
	 *	Switch to the game screen corresponding to the given question type 
	 */
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
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Calling to Stage methods
		super.act(delta);
		super.draw();
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
	}

	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}

}
