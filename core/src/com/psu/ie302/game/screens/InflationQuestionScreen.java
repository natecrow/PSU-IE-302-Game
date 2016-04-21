package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.psu.ie302.game.IE302Game;

public class InflationQuestionScreen extends AbstractScreen {
	
	protected InflationQuestionScreen(final IE302Game game) {
		super(game);
		
		// create table for the question space
		final Table tableQuestion = new Table();
		tableQuestion.setPosition(0, 0.2f * IE302Game.VIRTUAL_HEIGHT);
		tableQuestion.setSize(IE302Game.VIRTUAL_WIDTH,
				0.8f * IE302Game.VIRTUAL_HEIGHT);
		stage.addActor(tableQuestion);
		
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
		tableAns.setSize(IE302Game.VIRTUAL_WIDTH, 0.2f * IE302Game.VIRTUAL_HEIGHT);
		stage.addActor(tableAns);
		
		// create label for answer instructions
		final Label labelAns = new Label(
				"Type in your answer. Round it to 2 decimal places.",
				game.skin);
		labelAns.setWrap(true);
		labelAns.setAlignment(Align.center);
		
		// create sub-table to hold input and button
		final Table tableSubAns = new Table();
		//tableSubAns.setDebug(true);
		
		// create text field input for answer
		final TextField textFieldAns = new TextField("", game.skin);
		
		// TODO: accept negative numbers too
		// text field will only accept integers or decimal point numbers
		textFieldAns.setTextFieldFilter(new TextFieldFilter() {
			private char[] accepted = new char[]
		    		{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
			@Override
			public boolean acceptChar(TextField textField, char c) {
			    for (char a : accepted) {
			    	if (a == c) {
			    		// if the input char is a period, then do not accept
			    		// it if there is another period already in the text field
			    		if (c == '.') {
			    			if (textField.getText().contains(Character.toString('.'))) {
			    				return false;
			    			}
			    		}
			    		return true;
			    	}
			    }
			    return false;
			}
		});
		
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
		
		
		// debug options
//		tableQuestion.setDebug(true);
//		tableAns.setDebug(true);
		
		
		// When enter button is clicked, check the answer in the text field 
		// with the correct answer and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		// TODO: make it so user can hit the 'Enter' key too
		btnAns.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnAns) {
				if (!(textFieldAns.getText().isEmpty())) {
					String resultText = 
							game.questions[game.qItr].checkAndDisplayAnswerResults(
									textFieldAns.getText(),
									game.player);
					// display results
					// dispose and switch to next question when player is ready
					displayResultsAndSwitch(tableAns, resultText);
				}
			}
		});
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
