package com.psu.ie302.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.psu.ie302.game.IE302Game;
import com.psu.ie302.game.questions.QuestionMultipleProducts;

public class MultipleProductsQuestionScreen extends AbstractScreen {

	// TODO: add scrollbars for product info
	protected MultipleProductsQuestionScreen(final IE302Game game) {
		
		super(game);
		
		// create table to hold the first product image
		final Table tblImg1 = new Table();
		tblImg1.setPosition(0, (2 * IE302Game.VIRTUAL_HEIGHT) / 3);
		tblImg1.setSize((IE302Game.VIRTUAL_WIDTH / 2), IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tblImg1);
		tblImg1.setDebug(true);
		
		// create image for first product
		
		// size and add first product's image into its table
		tblImg1.defaults().expand().fill();
		
		
		// create table to hold the second product image
		final Table tblImg2 = new Table();
		tblImg2.setPosition(IE302Game.VIRTUAL_WIDTH / 2, (2 * IE302Game.VIRTUAL_HEIGHT) / 3);
		tblImg2.setSize(IE302Game.VIRTUAL_WIDTH / 2, IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tblImg2);
		tblImg2.setDebug(true);
		
		// create image for second product
		
		// size and add second product's image into its table
		tblImg2.defaults().expand().fill();
		
		
		// create table to hold info for the first product
		final Table tblProdInfo1 = new Table();
		
		// create label for first product's info
		final Label lblQuestionPrompt1 = new Label(((QuestionMultipleProducts)game.questions[game.qItr]).getQuestionPrompt(), game.skin);
		lblQuestionPrompt1.setWrap(true);
		lblQuestionPrompt1.setAlignment(Align.left);
		
		// add question prompt label for the first product to its table
		tblProdInfo1.defaults().expand().fill();
		tblProdInfo1.add(lblQuestionPrompt1);
		
		// create and add scrollpane with the first product's info table
		final ScrollPane scrollProdInfo1 = new ScrollPane(tblProdInfo1, game.skin);
		scrollProdInfo1.setPosition(0, IE302Game.VIRTUAL_HEIGHT / 3);
		scrollProdInfo1.setSize(IE302Game.VIRTUAL_WIDTH / 2, IE302Game.VIRTUAL_HEIGHT / 3);
		scrollProdInfo1.setupFadeScrollBars(0.5f, 0.25f);
		stage.addActor(scrollProdInfo1);
		
		// create table to hold info for the second product
		final Table tblProdInfo2 = new Table();
		
		// create label for second product's info
		final Label lblQuestionPrompt2 = new Label(((QuestionMultipleProducts)game.questions[game.qItr]).getQuestionPrompt2(), game.skin);
		lblQuestionPrompt2.setWrap(true);
		lblQuestionPrompt2.setAlignment(Align.left);
		
		// add question prompt label for the second product to its table
		tblProdInfo2.defaults().expand().fill();
		tblProdInfo2.add(lblQuestionPrompt2);
		
		// create and add scrollpane with the second product's info table
		final ScrollPane scrollProdInfo2 = new ScrollPane(tblProdInfo2, game.skin);
		scrollProdInfo2.setPosition(IE302Game.VIRTUAL_WIDTH / 2, IE302Game.VIRTUAL_HEIGHT / 3);
		scrollProdInfo2.setSize(IE302Game.VIRTUAL_WIDTH / 2, IE302Game.VIRTUAL_HEIGHT / 3);
		scrollProdInfo2.setupFadeScrollBars(0.5f, 0.25f);
		stage.addActor(scrollProdInfo2);
		
		
		// create table for answer area
		final Table tblAns = new Table();
		tblAns.setPosition(0, 0);
		tblAns.setSize(IE302Game.VIRTUAL_WIDTH, IE302Game.VIRTUAL_HEIGHT / 3);
		stage.addActor(tblAns);
		tblAns.setDebug(true);
		
		// create label for answer instructions
		final Label lblAns = new Label("Which product will you invest in?", game.skin);
		lblAns.setWrap(true);
		lblAns.setAlignment(Align.center);
		
		// create answer sub-table to hold answer buttons
		final Table tblSubAns = new Table();
		
		// create 'first product' button
		final TextButton btnProd1 = new TextButton("First Product", game.skin);
		
		// create 'either' button
		final TextButton btnEither = new TextButton("Either One", game.skin);
		
		// create 'neither' button
		final TextButton btnNeither = new TextButton("Neither One", game.skin);
		
		// create 'second product' button
		final TextButton btnProd2 = new TextButton("Second Product", game.skin);
		
		// add buttons to sub-table
		tblSubAns.defaults().space(20f);
		tblSubAns.add(btnProd1).size(150f, 50f);
		tblSubAns.add(btnNeither).size(150f, 50f);
		tblSubAns.add(btnEither).size(150f, 50f);
		tblSubAns.add(btnProd2).size(150f, 50f);
		
		// add widgets to answer table
		tblAns.defaults().space(10f);
		tblAns.add(lblAns).expandX().fill();
		tblAns.row();
		tblAns.add(tblSubAns);
		
		
		// When the 'first product' button is clicked, send "1" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnProd1.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnProd1) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"1",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
			}
		});
		
		// When the 'second product' button is clicked, send "2" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnProd2.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnProd2) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"2",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
			}
		});
		
		// When the 'neither' button is clicked, send "0" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnNeither.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnNeither) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"0",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
			}
		});
		
		// When the 'either' button is clicked, send "0" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnEither.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnEither) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"3",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
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
