package com.psu.ie302.game.questions;

import java.math.BigDecimal;
import java.math.MathContext;

import com.badlogic.gdx.math.MathUtils;

/*
 * Question for asking player to calculate a past or future value given
 * a current amount of money, an inflation rate per year, and no. of years
 */
public class QuestionInflationType2 extends QuestionInflation {

	private BigDecimal annualProfit;	// can be either current or actual
	private BigDecimal i;				// market interest rate
	private BigDecimal iDelta;			// inflation-free interest rate
	private BigDecimal g;				// growth rate
	private BigDecimal gDelta;			// inflation-free growth rate
	private int n;						// number of years
	private boolean actualValue;		// 1/true = actual; 0/false = constant
	
	
	public QuestionInflationType2() {
		super();
		
		this.annualProfit = new BigDecimal(MathUtils.random(5000, 10000000));
		this.i = BigDecimal.valueOf(MathUtils.random(0.01f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		this.iDelta = BigDecimal.valueOf(MathUtils.random(0.01f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		this.g = BigDecimal.valueOf(MathUtils.random(0.01f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		this.gDelta = BigDecimal.valueOf(MathUtils.random(0.01f, 0.1f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		this.n = MathUtils.random(1, 20);
		this.actualValue = MathUtils.randomBoolean();
		
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
		
		String annualProfitStr = nf.format(annualProfit);
		String iStr = i.multiply(BigDecimal.valueOf(100)).setScale(2).toString();
		String iDeltaStr = iDelta.multiply(BigDecimal.valueOf(100)).setScale(2).toString();
		String gStr = g.multiply(BigDecimal.valueOf(100)).setScale(2).toString();
		String gDeltaStr = gDelta.multiply(BigDecimal.valueOf(100)).setScale(2).toString();
		
		this.questionPrompt = "The annual profit for a company is " + annualProfitStr + ". "
				+ "Given a market interest rate of " + iStr + "%, "
				+ "an inflation-free interest rate of " + iDeltaStr + "%, "
				+ "a growth rate of " + gStr + "%, "
				+ "and an inflation-free growth rate of " + gDeltaStr + "%, "
				+ "how much will the company earn in " + actualOrConstantDollars 
				+ " dollars after " + n + " years?";
	}

	@Override
	public boolean setCorrectAnswer() {
		if (actualValue) {
			// A*(1-((1+g)^n)*((1+i)^-n)) / (i-g)
			// = ((A * (1 - (((1+g)^n) * (1/((1+i)^n))))) / (i-g))
			this.correctAnswer = ((annualProfit.multiply(
					(BigDecimal.ONE.subtract(
					((g.add(BigDecimal.ONE)).pow(n)).multiply(
					(BigDecimal.ONE.divide(
					(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))))))).divide(
					(i.subtract(g)), MathContext.DECIMAL128)
					).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			return true;
		} else {
			// A*(1-((1+g')^n)*((1+i')^-n)) / (i'-g')
			// = ((A * (1 - (((1+g')^n) * (1/((1+i')^n))))) / (i'-g'))
			this.correctAnswer = ((annualProfit.multiply(
					(BigDecimal.ONE.subtract(
					((gDelta.add(BigDecimal.ONE)).pow(n)).multiply(
					(BigDecimal.ONE.divide(
					(iDelta.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))))))).divide(
					(iDelta.subtract(gDelta)), MathContext.DECIMAL128)
					).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			return true;
		}
	}

}

//// ((1+g)^n)
//((g.add(BigDecimal.ONE)).pow(n))
//
//// ((1+i)^(-n) = (1/((1+i)^n))
//(BigDecimal.ONE.divide(
//		(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))
//
//// (((1+g)^n) * (1/((1+i)^n)))
//((g.add(BigDecimal.ONE)).pow(n)).multiply((BigDecimal.ONE.divide(
//		(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128)))
//
//// (1 - (((1+g)^n) * (1/((1+i)^n))))
//(BigDecimal.ONE.subtract(((g.add(BigDecimal.ONE)).pow(n)).multiply((BigDecimal.ONE.divide(
//		(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128)))))
//
//// (A * (1 - (((1+g)^n) * (1/((1+i)^n)))))
//(annualProfit.multiply((BigDecimal.ONE.subtract(((g.add(BigDecimal.ONE)).pow(n)).multiply((BigDecimal.ONE.divide(
//		(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128)))))))
//
//// (i-g)
//(i.subtract(g))
//
//// ((A * (1 - (((1+g)^n) * (1/((1+i)^n))))) / (i-g))
//((annualProfit.multiply((BigDecimal.ONE.subtract(((g.add(BigDecimal.ONE)).pow(n)).multiply((BigDecimal.ONE.divide(
//		(i.add(BigDecimal.ONE)).pow(n), MathContext.DECIMAL128))))))).divide((i.subtract(g)))).setScale(2, BigDecimal.ROUND_HALF_UP).toString();