package com.psu.ie302.game.screens;

import java.io.IOException;

import com.psu.ie302.game.IE302Game;

public class MultipleProductsQuestionScreen extends AbstractScreen {

	protected MultipleProductsQuestionScreen(IE302Game game) {
		
		super(game);
		
		// display question prompt
		System.out.println("Question " + (this.game.qItr + 1) + " out of " 
				+ this.game.questions.length + "\n\n"
				+ this.game.questions[this.game.qItr].getQuestionPrompt());
		
		// input answer
		String ans = null;
		try {
			ans = this.game.reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.game.questions[this.game.qItr].checkAndDisplayAnswerResults(ans, this.game.player);
		
		// display how much money player has so far
		System.out.println("Your money so far: $" + this.game.player.getScore() + "\n");
		
		// increment question iterator
		this.game.qItr++;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
