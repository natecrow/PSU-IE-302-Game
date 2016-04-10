package com.psu.ie302.game.questions;

/*
 * Question for asking player to calculate a past or future value given
 * a current amount of money, an inflation rate per year, and no. of years
 */
public class QuestionInflationType1 extends QuestionInflation {

	private int currentAmt;		// can be either current or actual
	private double f;			// inflation rate
	private int n;				// number of years
	// specifies whether to calculate a future or past value
	// 1/true = future; 0/false = past
	private boolean futureValue;
	
	
	public QuestionInflationType1() {
		this.currentAmt = randomlyGenerateInt(100, 100000);
		this.f = randomlyGenerateDouble(-0.05, 0.1);
		this.n = randomlyGenerateInt(1, 50);
		this.futureValue = this.randomlyGenerateTrueOrFalse();
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	
	public void setQuestionPrompt() {
		// if the future value is to be calculated, then prompt for it
		if (futureValue) {
			this.questionPrompt = "If I have $" + currentAmt 
					+ " today, how much will this amount be in actual dollars after "
					+ n + " years of inflation at a rate of " + f*100.0 + "% per year?";
		}
		// otherwise the past value is to calculated, so prompt for it
		else {
			this.questionPrompt = "If I have $" + currentAmt 
					+ " today, how much was this amount in constant dollars "
					+ n + " years ago with an inflation rate of " + f*100.0 + "% per year?";
		}
	}
	
	public void setCorrectAnswer() {
		// calculate future value if needed
		if (futureValue) {
			this.correctAnswer = String.valueOf(currentAmt * Math.pow(1.0 + this.f, this.n)); 
		}
		// otherwise calculate the past value
		else {
			this.correctAnswer = String.valueOf(currentAmt * Math.pow(1.0 + this.f, -this.n));
		}
	}
	
}
