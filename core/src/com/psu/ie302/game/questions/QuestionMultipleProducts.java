package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

public class QuestionMultipleProducts extends QuestionProducts {

	private Product product1;
	private Product product2;
	private double irrDiff;
	
	
	public QuestionMultipleProducts(Product prod1, Product prod2, int cashflowYears) {
		
		this.product1 = prod1;
		this.product2 = prod2;
		
		this.product1.generateCashflows(cashflowYears);
		this.product2.generateCashflows(cashflowYears);
		
		this.product1.setIRR(ProductCalculations.calculateIRR(prod1.getCashflows()));
		this.product2.setIRR(ProductCalculations.calculateIRR(prod2.getCashflows()));
		
		this.MARR = Player.generateMARR();
		
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	@Override
	public void setQuestionPrompt() {
		
		this.questionPrompt = "Two investors enter the Shark Tank with the following products:\n\n"
		
		+ "FIRST PRODUCT\n"
		+ "\tProduct name: " + this.product1.getName() + "\n"
		+ "\tCompany: " + this.product1.getCompany() + "\n"
		+ "\tDescription: " + this.product1.getDescription() + "\n"
		+ this.product1.getCompany() + " is looking for an investment of $" 
		+ this.product1.displayInitialInvestment() + ".\n"
		+ this.product1.getCompany() + " projects that with this investment in year 0, "
		+ "you will receive the following cash flows:\n"
		+ this.product1.displayCashflows()
		+ "The IRR is " + ProductCalculations.displayIRR(this.product1.getIRR()) + "\n\n"
		
		+ "SECOND PRODUCT\n"
		+ "\tProduct name: " + this.product2.getName() + "\n"
		+ "\tCompany: " + this.product2.getCompany() + "\n"
		+ "\tDescription: " + this.product2.getDescription() + "\n"
		+ this.product2.getCompany() + " is looking for an investment of $" 
		+ this.product2.displayInitialInvestment() + ".\n"
		+ this.product2.getCompany() + " projects that with this investment in year 0, "
		+ "you will receive the following cash flows:\n"
		+ this.product2.displayCashflows()
		+ "The IRR is " + ProductCalculations.displayIRR(this.product2.getIRR()) + "\n\n"
				
		+ "As an investor, you have set your MARR to " + this.displayMARR() +".\n"
		
		+ "Do you want to invest in either of these products?\n"
		+ "\t(0 = neither, 1 = first product, 2 = second product, 3 = either)";
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
	public void checkAndDisplayAnswerResults(String ans, Player player) {
		// if answer is correct ...
		if (this.checkAnswer(ans)) {
			// ... and player invested in one or both, then player wins money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				player.addMoney(100);
				System.out.println("Wise investment - "
						+ "that product(s) paid off! You've earned $100.");
			} 
			// ... and player didn't invest, then player doesn't lose anything
			else {
				System.out.println("Both products ended up failing, so"
						+ " good thing you didn't invest in either of them!");
			}
		}
		// if answer is incorrect...
		else {
			// ... and player invested, then player loses money
			if (ans.equals("1") || ans.equals("2") || ans.equals("3")) {
				player.addMoney(-100);
				System.out.println("Too bad, the product flopped. You lost $100.");
			}
			// ... and player did not invest, then player doesn't win anything
			else {
				System.out.println("Whoops! That product(s) actually ended up doing well. "
						+ "You missed out on the payoff, "
						+ "but at least you didn't lose anything.");
			}
		}
		
		System.out.println("The correct choice was: " + this.correctAnswer + "\n"
				+ "(The correct IRR was: " + ProductCalculations.displayIRR(this.irrDiff) + ")\n");
	}

}
