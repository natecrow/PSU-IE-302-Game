package com.psu.ie302.game.questions;

/*
 * Question for asking player to calculate a past or future value given
 * a current amount of money, an inflation rate per year, and no. of years
 */
public class QuestionInflationType2 extends QuestionInflation {

	private int annualProfit;	// can be either current or actual
	private boolean actualValue;// 1/true = actual; 0/false = constant
	private double i;			// market interest rate
	private double iDelta;		// inflation-free interest rate
	private double g;			// growth rate
	private double gDelta;		// inflation-free growth rate
	private int n;				// number of years
	
	
	public QuestionInflationType2() {
		this.annualProfit = randomlyGenerateInt(5000, 10000000);
		this.actualValue = this.randomlyGenerateTrueOrFalse();
		this.i = randomlyGenerateDouble(0.0, 0.1);
		this.iDelta = randomlyGenerateDouble(0.0, 0.1);
		this.g = randomlyGenerateDouble(0.0, 0.1);
		this.gDelta = randomlyGenerateDouble(0.0, 0.1);
		this.n = randomlyGenerateInt(1, 20);
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	
	@Override
	public void setQuestionPrompt() {
		
		// temp var that asks for an actual or constant dollars answer
		String actualOrConstantDollars;
		if (actualValue) {
			actualOrConstantDollars = "actual";
		} else {
			actualOrConstantDollars = "constant";
		}
		
		this.questionPrompt = "The annual profit for a company is $" + this.annualProfit + ". "
				+ "Given a market interest rate of " + this.i*100.0 + "%, "
				+ "an inflation-free interest rate of " + this.iDelta*100.0 + "%, "
				+ "a growth rate of " + this.g*100.0 + "%, "
				+ "and an inflation-free growth rate of " + this.gDelta*100.0 + "%, "
				+ "how much will the company earn in " + actualOrConstantDollars 
				+ " dollars after " + this.n + " years?";
	}

	@Override
	public void setCorrectAnswer() {
		if (actualValue) {
			this.correctAnswer = String.valueOf( this.annualProfit 
					* (1 - (Math.pow(1 + this.g, n) 
					* Math.pow(1 + this.i, (-n))))
					/ (this.i - this.g) );
		} else {
			this.correctAnswer = String.valueOf( this.annualProfit 
					* (1 - (Math.pow(1 + this.gDelta, n) 
					* Math.pow(1 + this.iDelta, (-n))))
					/ (this.iDelta - this.gDelta) );		
		}
	}

}
