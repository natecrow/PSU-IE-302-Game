package com.psu.ie302.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

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
			public void clicked(InputEvent event, float x, float y){
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
	public void show() {
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

	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}

}
