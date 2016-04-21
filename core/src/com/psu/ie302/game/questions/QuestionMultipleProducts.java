package com.psu.ie302.game.questions;

import java.math.BigDecimal;

import com.badlogic.gdx.math.MathUtils;
import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

public class QuestionMultipleProducts extends QuestionProducts {

	private Product product1;
	private Product product2;
	private BigDecimal irrDiff;
	private String questionPrompt2;
	
	public QuestionMultipleProducts(Product prod1, Product prod2, int cashflowYears) {
		super();
		
		this.product1 = prod1;
		this.product2 = prod2;
		
		this.product1.generateCashflows(cashflowYears);
		this.product2.generateCashflows(cashflowYears);
		
		this.product1.setIRR(ProductCalculations.calculateIRR(prod1.getCashflows()));
		this.product2.setIRR(ProductCalculations.calculateIRR(prod2.getCashflows()));
		
		this.MARR = BigDecimal.valueOf(MathUtils.random(0.20f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		
		// if answer doesn't make sense, then recalculate cash flows and IRRs
		while (!this.setCorrectAnswer()) {
			this.product1.generateCashflows(cashflowYears);
			this.product2.generateCashflows(cashflowYears);
			
			this.product1.setIRR(ProductCalculations.calculateIRR(prod1.getCashflows()));
			this.product2.setIRR(ProductCalculations.calculateIRR(prod2.getCashflows()));
		}
		
		this.setQuestionPrompt();
		this.setQuestionPrompt2();
	}
	
	@Override
	// This prompt is actually for product 1
	public void setQuestionPrompt() {
		
		this.questionPrompt = "You are presented with the first product:\n"
		
		+ "    Product name: " + this.product1.getName() + "\n"
		+ "    Company: " + this.product1.getCompany() + "\n"
		+ "    Description: " + this.product1.getDescription() + "\n\n"
		
		+ this.product1.getCompany() + " is looking for an investment of " 
		+ this.product1.displayInitialInvestment() + ". "
		+ this.product1.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows: "
		+ this.product1.displayCashflows()
		
		+ "The IRR is " + ProductCalculations.displayIRR(this.product1.getIRR()) + ". "
				+ "As an investor, you have set your MARR to " + this.displayMARR() + ".";
	}
	
	// This prompt is for product 2
	public void setQuestionPrompt2() {
		
		this.questionPrompt2 = "You are also presented with the second product:\n"
		
		+ "    Product name: " + this.product2.getName() + "\n"
		+ "    Company: " + this.product2.getCompany() + "\n"
		+ "    Description: " + this.product2.getDescription() + "\n\n"
		
		+ this.product2.getCompany() + " is looking for an investment of " 
		+ this.product2.displayInitialInvestment() + ". "
		+ this.product2.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows: "
		+ this.product2.displayCashflows()
		
		+ "The IRR is " + ProductCalculations.displayIRR(this.product2.getIRR()) + ". "
		+ "Your MARR is the same as for product 1.";
	}
	
	// get question prompt for product 2
	public String getQuestionPrompt2() {
		return this.questionPrompt2;
	}

	@Override
	public boolean setCorrectAnswer() {
		// if either irr < -1000% or irr > 1000%, then reject it
		// (remember irrDiff is a percentage, so
		//	i.e. 1.0 = 100%, 10.0 = 1000%, etc.)
		if ((product1.getIRR().abs()).compareTo(BigDecimal.valueOf(10)) >= 0
				|| (product2.getIRR().abs()).compareTo(BigDecimal.valueOf(10)) >= 0) {
			return false;
		}
		
		// case 1: both IRRs < MARR
		//	===> reject both products
		if ((product1.getIRR()).compareTo(MARR) < 0 
				&& (product2.getIRR()).compareTo(MARR) < 0) {
			this.correctAnswer = "0";
			return true;
		}
		// case 2: one IRR > MARR, other IRR < MARR
		//	===> accept the product with IRR > MARR
		else if ((product1.getIRR()).compareTo(MARR) > 0 
				&& (product2.getIRR()).compareTo(MARR) < 0) {
			this.correctAnswer = "1";
			return true;
		}
		else if ((product1.getIRR()).compareTo(MARR) < 0 
				&& (product2.getIRR()).compareTo(MARR) > 0) {
			this.correctAnswer = "2";
			return true;
		}
		// case 3: both IRRs > MARR
		//	===> use Incremental-investment analysis
		else if ((product1.getIRR()).compareTo(MARR) >= 0 
				&& (product2.getIRR()).compareTo(MARR) >= 0) {
			
			// 1. CALCULATE DIFFERENCE OF THE CASH FLOWS
			
			// cash flow of the difference between the two products cash flows
			int[] cashflowDiff = new int[this.product1.getCashflows().length];
			
			int[] A;	// product cash flow with lower initial investment
			int[] B;	// product cash flow with higher initial investment
			String prodA;	// will contain which product is A
			String prodB;	// will contain which product is B
			
			// if product 1 has a higher initial investment, then set it's cash flow to B
			if (product1.getCashflows()[0] <= product2.getCashflows()[0]) {
				B = product1.getCashflows();
				A = product2.getCashflows();
				prodB = "1";
				prodA = "2";
			}
			// otherwise set product 2's cash flow to B
			else { // this.product1.getCashflows()[0] > this.product2.getCashflows()[0]
				B = product2.getCashflows();
				A = product1.getCashflows();
				prodB = "2";
				prodA = "1";
			}
			
			// calculate and store the cash flow differences for each year
			for (int i = 0; i < cashflowDiff.length; i++) {
				cashflowDiff[i] = B[i] - A[i];
			}
			
			// 2. CALCULATE THE IRR OF THE CASH FLOW DIFFERENCE
			irrDiff = ProductCalculations.calculateIRR(cashflowDiff);
			
			// if irrDiff < -1000% or irrDiff > 1000%, then reject it
			// (remember irrDiff is a percentage, so
			//	i.e. 1.0 = 100%, 10.0 = 1000%, etc.)
			if ((irrDiff.abs()).compareTo(BigDecimal.valueOf(10)) >= 0) {
				return false;
			}
			
			// 3. COMPARE IRR TO MARR
			// if IRR > MARR, select B
			if (irrDiff.compareTo(MARR) > 0) {
				this.correctAnswer = prodB;
				return true;
			}
			// if IRR < MARR, select A
			else if (irrDiff.compareTo(MARR) < 0) {
				this.correctAnswer = prodA;
				return true;
			}
			// if IRR = MARR, select either A or B
			else {
				this.correctAnswer = "3";
				return true;
			}
		}
		return false;
	}

	@Override
	// check answer and adjust score accordingly
	public String checkAndDisplayAnswerResults(String ans, Player player) {
		String results = "";
		
		// if answer is correct ...
		if (this.checkAnswer(ans)) {
			player.addScore(1);
			// ... and player invested in one or both, then player wins money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				results += "Wise investment - " 
						+ "you are likely to gain profit on that product!";
			} 
			// ... and player didn't invest, then player doesn't lose anything
			else {
				results += "Good thing you didn't invest; " 
						+ "you probably would have lost money on either product.";
			}
		}
		// if answer is incorrect...
		else {
			// ... and player invested, then player loses money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				results += "Bad investment - " 
						+ "you spent more than you'll likely gain on that product!";
			}
			// ... and player did not invest, then player doesn't win anything
			else {
				results += "Oops! You probably would have " 
						+ "gained profit if had you invested.";
			}
			
			results += "\nThe best investment was: ";
			// display correct answer as a string corresponding to the numerical answer
			if (this.correctAnswer == "0") {
				results += "Neither product.";
			} else if (this.correctAnswer == "1") {
				results += "The first product.";
			} else if (this.correctAnswer == "2") {
				results += "The second product.";
			} else if (this.correctAnswer == "3") {
				results += "Either product.";
			}
		}
		
		// display combined IRR of the difference in cash flows
		if ((product1.getIRR()).compareTo(MARR) >= 0 
				&& (product2.getIRR()).compareTo(MARR) >= 0) {
			results += "\n(The correct IRR of the differences between the " 
					+ "cash flows of the two products is " 
					+ ProductCalculations.displayIRR(this.irrDiff) + ")";
		}
	
		return results;
	}
	
	public Product getProduct1() {
		return product1;
	}

	public Product getProduct2() {
		return product2;
	}

}
