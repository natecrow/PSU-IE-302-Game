package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.psu.ie302.game.IE302Game;

public class EndingScreen extends AbstractScreen {
	
	/*
	 * Display player's final score and 
	 * include button to return to the main menu
	 */
	protected EndingScreen(final IE302Game game) {
		
		super(game);
		
		// create table to hold final score + back button
		final Table tbl = new Table();
		tbl.setFillParent(true);
		stage.addActor(tbl);
		tbl.setDebug(true);
		
		// create label with player's final score
		final Label lblScore = new Label(Integer.toString(game.player.getScore()), game.skin);
		
		// create button for returning to menu
		final TextButton btnBackToMenu = new TextButton("Back to menu", game.skin);
		
		// add everything to table
		tbl.defaults().space(20f);
		tbl.add(lblScore).expandX().fill();
		tbl.row();
		tbl.add(btnBackToMenu).size(150f, 30f);
	}

}
