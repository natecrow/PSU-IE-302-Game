package com.psu.ie302.game.questions;

import java.math.BigDecimal;
import java.math.MathContext;

import com.badlogic.gdx.math.MathUtils;

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
	
	
	public QuestionInflationType1() {
		super();
		
		// randomly generate the numbers
		this.currentAmt = new BigDecimal(MathUtils.random(50, 100000));
		this.f = BigDecimal.valueOf(MathUtils.random(-0.05f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		this.n = MathUtils.random(1, 50);
		this.futureValue = MathUtils.randomBoolean();
		
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
	
	public void setCorrectAnswer() {
		// calculate future value if needed
		if (futureValue) {
			// currentAmt * (1+f)^n
			this.correctAnswer = (currentAmt.multiply(
							(f.add(BigDecimal.ONE)).pow(n))
							).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}
		// otherwise calculate the past value
		else {
			// currentAmt * (1+f)^(-n) = currentAmt * (1/(f+1)^n)
			this.correctAnswer = (currentAmt.multiply(
							BigDecimal.ONE.divide(
							(f.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))
							).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}
	}
	
}
