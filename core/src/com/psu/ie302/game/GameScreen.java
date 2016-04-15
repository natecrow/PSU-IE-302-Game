package com.psu.ie302.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

	final IE302Game game;
	OrthographicCamera camera;	
	
	public GameScreen(final IE302Game gam) {
		
		this.game = gam;
		
		//this.camera = new OrthographicCamera();
		//this.camera.setToOrtho(false, 640, 480);
		
		// display question prompt
		System.out.println("Question " + (this.game.questionIter + 1) + " out of " 
				+ this.game.questions.length + "\n\n"
				+ this.game.questions[this.game.questionIter].getQuestionPrompt());
		
		// input answer
		String ans = null;
		try {
			ans = this.game.reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.game.questions[this.game.questionIter].checkAndDisplayAnswerResults(ans, this.game.player);
		
		// display how much money player has so far
		System.out.println("Your money so far: $" + this.game.player.getScore() + "\n");
		
		// increment question iterator
		this.game.questionIter++;
		
	}
	
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// tell the camera to update its matrices
        //camera.update();
        
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera
        //game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "stuff", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		game.batch.end();
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

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
