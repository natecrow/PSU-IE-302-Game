package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

public class QuestionMultipleProducts extends QuestionProducts {

	private Product product1;
	private Product product2;
	private double irrDiff;
	private String questionPrompt2;
	
	
	public QuestionMultipleProducts(Product prod1, Product prod2, int cashflowYears) {
		
		this.product1 = prod1;
		this.product2 = prod2;
		
		this.product1.generateCashflows(cashflowYears);
		this.product2.generateCashflows(cashflowYears);
		
		this.product1.setIRR(ProductCalculations.calculateIRR(prod1.getCashflows()));
		this.product2.setIRR(ProductCalculations.calculateIRR(prod2.getCashflows()));
		
		this.MARR = Player.generateMARR();
		
		this.setQuestionPrompt();
		this.setQuestionPrompt2();
		
		this.setCorrectAnswer();
	}
	
	@Override
	// This prompt is actually for product 1
	public void setQuestionPrompt() {
		
		this.questionPrompt = "You are presented with the first product:\n"
		
		+ "    Product name: " + this.product1.getName() + "\n"
		+ "    Company: " + this.product1.getCompany() + "\n"
		+ "    Description: " + this.product1.getDescription() + "\n\n"
		
		+ this.product1.getCompany() + " is looking for an investment of $" 
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
		
		+ this.product2.getCompany() + " is looking for an investment of $" 
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
	public void setCorrectAnswer() {
		
		// case 1: both IRRs < MARR
		//	===> reject both products
		if (this.product1.getIRR() < this.MARR && this.product2.getIRR() < this.MARR) {
			this.correctAnswer = "0";
		}
		// case 2: one IRR > MARR, other IRR < MARR
		//	===> accept the product with IRR > MARR
		else if (this.product1.getIRR() > this.MARR && this.product2.getIRR() < this.MARR) {
			this.correctAnswer = "1";
		}
		else if (this.product1.getIRR() < this.MARR && this.product2.getIRR() > this.MARR) {
			this.correctAnswer = "2";
		}
		// case 3: both IRRs > MARR
		//	===> use Incremental-investment analysis
		else if (this.product1.getIRR() >= this.MARR && this.product2.getIRR() >= this.MARR) {
			
			// 1. CALCULATE DIFFERENCE OF THE CASH FLOWS
			
			// cash flow of the difference between the two products cash flows
			int[] cashflowDiff = new int[this.product1.getCashflows().length];
			
			int[] A;	// product cash flow with lower initial investment
			int[] B;	// product cash flow with higher initial investment
			String prodA;	// will contain which product is A
			String prodB;	// will contain which product is B
			
			// if product 1 has a higher initial investment, then set it's cash flow to B
			if (this.product1.getCashflows()[0] <= this.product2.getCashflows()[0]) {
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
			
			// 3. COMPARE IRR TO MARR
			// if IRR > MARR, select B
			if (this.irrDiff > this.MARR) {
				this.correctAnswer = prodB;
			}
			// if IRR < MARR, select A
			else if (this.irrDiff < this.MARR) {
				this.correctAnswer = prodA;
			}
			// if IRR = MARR, select either A or B
			else {
				this.correctAnswer = "3";
			}
		}
	}

	@Override
	// check answer and adjust score accordingly
	public String checkAndDisplayAnswerResults(String ans, Player player) {
		String results = "";
		
		// if answer is correct ...
		if (this.checkAnswer(ans)) {
			// ... and player invested in one or both, then player wins money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				player.addScore(1);
				results += "Wise investment - "
						+ "that product(s) paid off! You've earned $100.\n";
			} 
			// ... and player didn't invest, then player doesn't lose anything
			else {
				results += "Both products ended up failing, so"
						+ " good thing you didn't invest in either of them!\n";
				player.addScore(1);
			}
		}
		// if answer is incorrect...
		else {
			// ... and player invested, then player loses money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				player.addScore(0);
				results += "Too bad, the product flopped. You lost $100.\n";
			}
			// ... and player did not invest, then player doesn't win anything
			else {
				results += "Whoops! That product(s) actually ended up doing well. "
						+ "You missed out on the payoff, "
						+ "but at least you didn't lose anything.\n";
			}
		}
		
		results += "The correct choice was: " + this.correctAnswer + "\n"
				+ "(The correct combined IRR (if any) was: " + ProductCalculations.displayIRR(this.irrDiff) + ")\n";
	
		return results;
	}

}
