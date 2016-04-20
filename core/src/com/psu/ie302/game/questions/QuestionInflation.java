package com.psu.ie302.game.questions;

import java.util.Random;

import com.psu.ie302.game.Player;

public abstract class QuestionInflation extends Question {

	@Override
	public String checkAndDisplayAnswerResults(String ans, Player player) {
		String result = "";
		
		// if answer is within 1 of the correct answer, then award player
		if ((Double.parseDouble(ans) <= Double.parseDouble(this.correctAnswer) + 1) 
				&& (Double.parseDouble(ans) >= Double.parseDouble(this.correctAnswer) - 1)) {
			result += "CORRECT! ";
			player.addScore(1);
		} else {
			result += "WRONG!\n"
				+ "The correct dollar amount is: $" + this.correctAnswer;
		}
	
		return result;
	}
	
	// randomly generate an int given a min and max value
	protected int randomlyGenerateInt(int min, int max) {
		Random rand = new Random();
		return (rand.nextInt((max - min) + 1) + min);
	}
	
	// used to set whether to calculate a past or future / constant or actual value
	protected boolean randomlyGenerateTrueOrFalse() {
		return (Math.random() > 0.5);
	}
	
	// randomly generate a double given a max value
	protected double randomlyGenerateDouble(double min, double max) {
		Random rand = new Random();
		return min + rand.nextDouble() * max;
	}

}
