package com.psu.ie302.game.questions;

import java.math.BigDecimal;

import com.psu.ie302.game.Player;

public abstract class QuestionInflation extends Question {
	
	@Override
	public String checkAndDisplayAnswerResults(String ans, Player player) {
		String result = "";
		
		BigDecimal playerAns = new BigDecimal(ans);
		BigDecimal correctAns = new BigDecimal(correctAnswer);
		if (playerAns.compareTo(correctAns) == 0) {
			result += "CORRECT! ";
			player.addScore(1);
		} else {
			result += "WRONG!\n"
				+ "The correct dollar amount is: " + nf.format(correctAns);
		}
	
		return result;
	}
}
