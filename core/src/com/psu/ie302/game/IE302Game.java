package com.psu.ie302.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.psu.ie302.game.questions.Question;

public class IE302Game extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	public Skin skin;
	
	public Player player;
	public Product[] products;
	public Question[] questions;
	public int qItr;
	public BufferedReader reader;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		font = new BitmapFont();
		font.setColor(Color.CYAN);
		
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		this.setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
}
