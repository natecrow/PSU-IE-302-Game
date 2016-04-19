package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.psu.ie302.game.IE302Game;

public class InflationQuestionScreen extends AbstractScreen {
	
	protected InflationQuestionScreen(final IE302Game game) {
		super(game);
		
		// create table for the question space
		final Table tableQuestion = new Table();
		tableQuestion.setPosition(0, IE302Game.VIRTUAL_HEIGHT / 3);
		tableQuestion.setSize(IE302Game.VIRTUAL_WIDTH,
				2 * IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tableQuestion);
		//tableQuestion.setDebug(true);
		
		// create label for the question
		final Label labelQuestion = new Label(
				this.game.questions[this.game.qItr].getQuestionPrompt(),
				game.skin);
		labelQuestion.setWrap(true);
		labelQuestion.setAlignment(Align.center);
		
		// size and add question label into its table
		tableQuestion.defaults().expand().fill();
		tableQuestion.add(labelQuestion);
		
		
		// create table for answer space
		final Table tableAns = new Table();
		tableAns.setPosition(0, 0);
		tableAns.setSize(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tableAns);
		//tableAns.setDebug(true);
		
		// create label for answer instructions
		final Label labelAns = new Label(
				"Type in your answer. Round it by 2 decimal places.",
				game.skin);
		labelAns.setWrap(true);
		labelAns.setAlignment(Align.center);
		
		// create sub-table to hold input and button
		final Table tableSubAns = new Table();
		//tableSubAns.setDebug(true);
		
		// create text field input for answer
		final TextField textFieldAns = new TextField("", game.skin);
		
		// create enter button for text field input
		final TextButton btnAns = new TextButton("Enter", game.skin);
		
		// size and add input and button into sub-table
		tableSubAns.defaults().space(5f);
		tableSubAns.add(textFieldAns);
		tableSubAns.add(btnAns).size(textFieldAns.getWidth(), textFieldAns.getHeight());
		
		// size and add widgets into answer table
		tableAns.defaults().space(10f);
		tableAns.add(labelAns).expandX().fill();
		tableAns.row();
		tableAns.add(tableSubAns);
		
		
		// When enter button is clicked, check the answer in the text field 
		// with the correct answer and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		// TODO: make it so user can hit the 'Enter' key too
		btnAns.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnAns) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								textFieldAns.getText(),
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tableAns, resultText);
			}
		});
		
//		// display question prompt
//		System.out.println("Question " + (this.game.qItr + 1) + " out of " 
//				+ this.game.questions.length + "\n\n"
//				+ this.game.questions[this.game.qItr].getQuestionPrompt());
//		
//		// display how much money player has so far
//		System.out.println("Your money so far: $" + this.game.player.getScore() + "\n");
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
