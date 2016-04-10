package com.psu.ie302.game.questions;

import java.util.Random;

import com.psu.ie302.game.Player;

/*
 * Question for asking player to calculate a past or future value given
 * a current amount of money, an inflation rate per year, and no. of years
 */
public class QuestionInflationType1 extends Question {

	private int currentAmt;			// can be either current or actual
	private double f;				// inflation rate / year
	private int n;				// number of years
	// specifies whether to calculate a future or past value
	// 1/true = future; 0/false = past
	private boolean futureValue;
	
	
	public QuestionInflationType1() {
		this.currentAmt = randomlyGenerateInt(100, 100000);
		this.n = randomlyGenerateInt(1, 50);
		this.f = randomlyGenerateDouble(0.1);
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
	
	public void checkAndDisplayAnswerResults(String ans, Player player) {
		// if answer is within 1 of the correct answer, then award player
		if ((Double.parseDouble(ans) <= Double.parseDouble(this.correctAnswer) + 1) 
				&& (Double.parseDouble(ans) >= Double.parseDouble(this.correctAnswer) - 1)) {
			System.out.print("CORRECT! ");
		} else {
			System.out.print("WRONG! ");
		}
		System.out.println("The correct dollar amount is: " + this.correctAnswer + "\n");
	}
	
	// randomly generate an int given a min and max value
	// used for generating currentAmt and n
	private int randomlyGenerateInt(int min, int max) {
		Random rand = new Random();
		return (rand.nextInt((max - min) + 1) + min);
	}
	
	// used to set whether to calculate a past or future value
	private boolean randomlyGenerateTrueOrFalse() {
		return (Math.random() > 0.5);
	}
	
	// randomly generate a double
	// used for generating f (inflation rate / year)
	public double randomlyGenerateDouble(double max) {
		Random rand = new Random();
		return rand.nextDouble() * max;
	}
}
