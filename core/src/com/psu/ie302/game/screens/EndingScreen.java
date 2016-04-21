package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
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
		//tbl.setDebug(true);
		
		// create label with player's final score
		String scoreDisplay = "You got " + game.player.getScore() + " out of " 
				+ game.questions.length + " questions correct.";
		final Label lblScore = new Label(scoreDisplay, game.skin);
		lblScore.setWrap(true);
		lblScore.setAlignment(Align.center);
		
		// create button for returning to menu
		final TextButton btnBackToMenu = new TextButton("Back to menu", game.skin);
		
		// add everything to table
		tbl.defaults().space(20f);
		tbl.add(lblScore).expandX().fill();
		tbl.row();
		tbl.add(btnBackToMenu).size(150f, 50f);
		
		
		// add event for the 'back to menu' button
		btnBackToMenu.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnBackToMenu) {
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
	}

}
