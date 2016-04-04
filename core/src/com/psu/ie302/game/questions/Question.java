package com.psu.ie302.game.questions;

/*
 * Base Question class that all other questions inherit from
 */
public abstract class Question {
	
	protected String questionPrompt;
	protected String correctAnswer;
	
	public String getQuestionPrompt() {
		return this.questionPrompt;
	}

	public abstract void setQuestionPrompt();
	
	// make sure class that calls this method checks if returned value is not null
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public abstract void setCorrectAnswer();
	
	// returns true if player's answer is correct; false otherwise
	public boolean checkAnswer(String ans) {
		return (ans.equals(this.correctAnswer));
	}
}
