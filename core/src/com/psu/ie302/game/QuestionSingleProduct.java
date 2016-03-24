package com.psu.ie302.game;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct {

	private String questionPrompt;
	private String[] possibleAnswers;
	private String correctAnswer;	// either Yes or No
	
	// returns if player's answer is correct (True = yes, False = no)
	public boolean checkAnswer(String ans) {
		return (ans == this.correctAnswer);
	}
	
	public String getQuestionPrompt() {
		return this.questionPrompt;
	}
	
	public void setQuestionPrompt() {
		this.questionPrompt = "Check out the following product and "
				+ "decide whether you want to invest in it or not (Y/N).";
	}
	
	public String[] getPossibleAnswers() {
		return this.possibleAnswers;
	}
	
	public void setPossibleAnswers() {
		this.possibleAnswers[0] = "Y";
		this.possibleAnswers[1] = "N";
	}
	
}
