package com.psu.ie302.game.screens;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.psu.ie302.game.IE302Game;

public class SingleProductQuestionScreen extends AbstractScreen {
	
	public SingleProductQuestionScreen(final IE302Game game) {
		
		super(game);
		
		// add a label to the screen
		Label lblTest = new Label("hello world", game.skin);
		lblTest.setWrap(true);
		lblTest.setFontScale(2.0f);
		lblTest.setWidth(100);
		lblTest.setHeight(30);
		lblTest.setPosition(200, 200, Align.center);
		
		// add everything to stage
		addActor(lblTest);
		
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
		super.dispose();
	}

}
