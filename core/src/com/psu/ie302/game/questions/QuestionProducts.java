package com.psu.ie302.game.questions;

/*
 * Base Products Question class that all product questions inherit from
 */
public abstract class QuestionProducts extends Question {

	protected double MARR;
	
	
	// returns MARR as a percentage
	public String displayMARR() {
		return (this.MARR * 100.0) + "%";
	}
}
