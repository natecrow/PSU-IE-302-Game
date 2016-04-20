package com.psu.ie302.game.questions;

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
}
