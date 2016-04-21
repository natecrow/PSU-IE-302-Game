package com.psu.ie302.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.psu.ie302.game.IE302Game;
import com.psu.ie302.game.questions.QuestionSingleProduct;

public class SingleProductQuestionScreen extends AbstractScreen {
	
	private Texture prodTex;
	
	
	public SingleProductQuestionScreen(final IE302Game game) {
		
		super(game);

		// create table to hold the product image
		final Table tblImg = new Table();
		tblImg.setPosition(0, 0.6f * IE302Game.VIRTUAL_HEIGHT);
		tblImg.setSize(IE302Game.VIRTUAL_WIDTH, 0.4f * IE302Game.VIRTUAL_HEIGHT);
		stage.addActor(tblImg);
		
		// create image for product
		prodTex = new Texture(Gdx.files.internal("product_images/"
				+ ((QuestionSingleProduct) game.questions[game.qItr]).getProduct().getImgFilename()));
		final Image prodImg = new Image(prodTex);
		prodImg.setScaling(Scaling.fit);
		
		// size and add image into its table
		tblImg.add(prodImg);
		
		
		// create table to hold info for product
		final Table tblProdInfo = new Table();
		tblProdInfo.setPosition(0, 0.2f * IE302Game.VIRTUAL_HEIGHT);
		tblProdInfo.setSize(IE302Game.VIRTUAL_WIDTH, 0.4f * IE302Game.VIRTUAL_HEIGHT);
		stage.addActor(tblProdInfo);
		
		// create label for product description
		final Label lblQuestionPrompt = new Label(game.questions[game.qItr].getQuestionPrompt(), game.skin);
		lblQuestionPrompt.setWrap(true);
		lblQuestionPrompt.setAlignment(Align.left);
		
		// add question prompt label to its table
		tblProdInfo.defaults().expand().fill();
		tblProdInfo.add(lblQuestionPrompt);
		
		
		// create table for answer area
		final Table tblAns = new Table();
		tblAns.setPosition(0, 0);
		tblAns.setSize(IE302Game.VIRTUAL_WIDTH, 0.2f * IE302Game.VIRTUAL_HEIGHT);
		stage.addActor(tblAns);
		
		// create label for answer instructions
		final Label lblAns = new Label("Will you invest in this product?", game.skin);
		lblAns.setWrap(true);
		lblAns.setAlignment(Align.center);
		
		// create answer sub-table to hold 'yes' and 'no' buttons
		final Table tblSubAns = new Table();
		
		// create 'yes' button
		final TextButton btnYes = new TextButton("Yes", game.skin);
		
		// create 'no' button
		final TextButton btnNo = new TextButton("No", game.skin);
		
		// add 'yes' and 'no' buttons to sub-table
		tblSubAns.defaults().space(20f);
		tblSubAns.add(btnYes).size(100f, 50f);
		tblSubAns.add(btnNo).size(100f, 50f);
		
		// add widgets to answer table
		tblAns.defaults().space(10f);
		tblAns.add(lblAns).expandX().fill();
		tblAns.row();
		tblAns.add(tblSubAns);
		
		// debug options
//		tblImg.setDebug(true);
//		tblProdInfo.setDebug(true);
//		tblAns.setDebug(true);
		
		
		// When the 'yes' button is clicked, send "Y" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnYes.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnYes) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"Y",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
			}
		});
		
		// When the 'yes' button is clicked, send "Y" as the player's answer
		// and display the results.
		// Then, increment to the next question and switch to the
		// corresponding screen.
		btnNo.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor btnNo) {
				String resultText = 
						game.questions[game.qItr].checkAndDisplayAnswerResults(
								"N",
								game.player);
				// display results
				// dispose and switch to next question when player is ready
				displayResultsAndSwitch(tblAns, resultText);
			}
		});
	}
	
	@Override
	public void dispose() {
		super.dispose();
		prodTex.dispose();
	}

}
