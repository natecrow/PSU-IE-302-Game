package com.psu.ie302.game.questions;

import java.math.BigDecimal;

/*
 * Base Products Question class that all product questions inherit from
 */
public abstract class QuestionProducts extends Question {

	protected BigDecimal MARR;
	
	// returns MARR as a percentage
	public String displayMARR() {
		return MARR.multiply(BigDecimal.valueOf(100)).setScale(2).toString() + "%";
	}
}
