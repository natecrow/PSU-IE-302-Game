package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;

/*
 * Base Products Question class that all product questions inherit from
 */
public abstract class QuestionProducts extends Question {

	protected double MARR;
	
	
	public abstract void checkAndDisplayAnswerResults(String ans, Player player);
	
	
	// returns MARR as a percentage
	public String displayMARR() {
		return (this.MARR * 100.0) + "%";
	}
	
	// use for background calculations
	public double getMARR() {
		return this.MARR;
	}

	public void setMARR(double prodMARR) {
		this.MARR = prodMARR;
	}
}
