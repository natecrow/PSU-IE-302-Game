package com.psu.ie302.game.questions;

import java.math.BigDecimal;
import java.math.MathContext;

/*
 * Question for asking player to calculate a past or future value given
 * a current amount of money, an inflation rate per year, and no. of years
 */
public class QuestionInflationType1 extends QuestionInflation {

	private BigDecimal currentAmt;	// can be either current or actual
	private BigDecimal f;			// inflation rate
	private int n;					// number of years
	// specifies whether to calculate a future or past value
	private boolean futureValue;
	
	
	public QuestionInflationType1(BigDecimal currentAmount, BigDecimal inflationRate, int yearsOfInflation, boolean calculateFutureValue) {
		super();
		
		// randomly generate the numbers
		this.currentAmt = currentAmount;
		this.f = inflationRate;
		this.n = yearsOfInflation;
		this.futureValue = calculateFutureValue;
		
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	public void setQuestionPrompt() {
		String currentAmtStr = nf.format(currentAmt);
		String fStr = f.multiply(BigDecimal.valueOf(100)).setScale(2).toString();
		
		// if the future value is to be calculated, then prompt for it
		if (futureValue) {
			this.questionPrompt = "If I have " + currentAmtStr 
					+ " today, how much will this amount be in actual dollars after "
					+ n + " years of inflation at a rate of " + fStr + "% per year?";
		}
		// otherwise the past value is to calculated, so prompt for it
		else {
			this.questionPrompt = "If I have " + currentAmtStr 
					+ " today, how much was this amount in constant dollars "
					+ n + " years ago with an inflation rate of " + fStr + "% per year?";
		}
	}
	
	public boolean setCorrectAnswer() {
		// calculate future value if needed
		if (futureValue) {
			// currentAmt * (1+f)^n
			this.correctAnswer = (currentAmt.multiply(
							(f.add(BigDecimal.ONE)).pow(n))
							).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			return true;
		}
		// otherwise calculate the past value
		else {
			// currentAmt * (1+f)^(-n) = currentAmt * (1/(f+1)^n)
			this.correctAnswer = (currentAmt.multiply(
							BigDecimal.ONE.divide(
							(f.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))
							).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			return true;
		}
	}
	
}
