package com.psu.ie302.game.questions;

import java.text.NumberFormat;
import java.util.Locale;

import com.psu.ie302.game.Player;

/*
 * Base Question class that all other questions inherit from
 */
public abstract class Question {
	
	protected String questionPrompt;
	protected String correctAnswer;
	protected NumberFormat nf;		// to format money as currency

	public Question() {
		nf = NumberFormat.getCurrencyInstance(Locale.US);
	}
	
	public abstract void setQuestionPrompt();
	public abstract void setCorrectAnswer();
	public abstract String checkAndDisplayAnswerResults(String ans, Player player);

	
	public String getQuestionPrompt() {
		return this.questionPrompt;
	}
	
	// make sure class that calls this method checks if returned value is not null
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	// returns true if player's answer is correct; false otherwise
	public boolean checkAnswer(String ans) {
		return (ans.equals(this.correctAnswer));
	}
	
}
