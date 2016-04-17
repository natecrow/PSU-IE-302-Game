package com.psu.ie302.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.psu.ie302.game.questions.Question;
import com.psu.ie302.game.screens.MainMenuScreen;

public class IE302Game extends Game {
	
	public static final int VIRTUAL_WIDTH = 800;
	public static final int VIRTUAL_HEIGHT = 600;
	
	public Skin skin;
	public Player player;
	public Product[] products;
	public Question[] questions;
	public int qItr;
	public BufferedReader reader;
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		reader = new BufferedReader(new InputStreamReader(System.in));
		this.setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose() {
		skin.dispose();
	}

	@Override
	public void render () {
		super.render();
	}
}
