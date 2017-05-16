package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.psu.ie302.game.IE302Game;

public class InstructionsScreen extends AbstractScreen {

	protected InstructionsScreen(final IE302Game game) {
		
		super(game);
		
		// create table to hold everything
		final Table tbl = new Table();
		tbl.setFillParent(true);
		stage.addActor(tbl);
		//tbl.setDebug(true);
		
		// create label with instructions
		String instructionsStr =
				"You are a rich investor on the Penn State Shark Tank game show.\n\n"
				
				+ "In each round of the game, companies will pitch you their products "
				+ "and you must decide whether to invest in them or not. "
				+ "Sometimes you will be pitched with one product at a time, "
				+ "and other times you will be pitched with two products at a time "
				+ "and must choose to invest in one, either, or neither of them.\n\n"
				
				+ "Also, in between the product pitches, you will be asked to calculate "
				+ "constant and/or actual dollar amounts of a given sum of money under "
				+ "certain conditions to test your knowledge of inflation.\n\n"

				+ "You will have to apply the skills you learned in " 
				+ "IE 302: Engineering Economy to help you make smart decisions. "
				+ "In particular, you must understand constant & actual dollar analysis"
				+ " and IRR-related concepts, including incremental-analysis. "
				+ "All the information you need to calculate the best answers will be "
				+ "given to you. "
				+ "You will be asked " + game.questions.length
				+ " questions each time you play."
				+ "\n\nGood luck!";
		final Label lblScore = new Label(instructionsStr, game.skin);
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