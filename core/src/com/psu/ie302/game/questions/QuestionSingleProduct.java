package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct extends QuestionProducts {
	
	protected Product product;
	
	
	public QuestionSingleProduct(Product prod) {
		this.product = prod;
		this.product.generateCashflows(3);
		this.product.setIRR(ProductCalculations.calculateIRR(prod.getCashflows()));
		this.MARR = Player.generateMARR();
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	@Override
	public void setQuestionPrompt() {
		this.questionPrompt = "An investor enters the Shark Tank with the following product:\n"
				
				+ "\tProduct name: " + this.product.getName() + "\n"
				+ "\tCompany: " + this.product.getCompany() + "\n"
				+ "\tDescription: " + this.product.getDescription() + "\n"
				
				+ this.product.getCompany() + " is looking for an investment of $" 
				+ this.product.displayInitialInvestment() + ".\n"
				+ this.product.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows:\n"
				+ this.product.displayCashflows()
				
				+ "As an investor, you have set your MARR to " + this.displayMARR() +".\n"
				
				+ "Do you want to invest in this product? (Y = yes, N = no, D = doesn't matter)";
	}
	
	@Override
	public void setCorrectAnswer() {
		if (this.product.getIRR() > this.MARR) {
			this.correctAnswer = "Y";
		} else if (this.product.getIRR() < this.MARR) {
			this.correctAnswer = "N";
		} else { // IRR == MARR
			this.correctAnswer = "D";
		}
	}
	
	@Override
	// check answer and adjust score accordingly
	public void checkAndDisplayAnswerResults(String ans, Player player) {
		
		// if answer is correct...
		if (this.checkAnswer(ans)) {
			// ... and player invested in one or both, then player wins money
			if (ans.equals("Y") || ans.equals("D")) {
				player.addMoney(100);
				System.out.println("Wise investment - "
						+ "the product paid off! You've earned $100.");
			}
			// ... and player didn't invest, then player doesn't lose anything
			else {
				System.out.println("That product ended up failing, so"
						+ " good thing you didn't invest in it!");
			}
		}
		// if answer is incorrect...
		else {
			// ... and player invested, then player loses money
			if (ans.equals("Y") || ans.equals("D")) {
				player.addMoney(-100);
				System.out.println("Too bad, the product flopped. You lost $100.");
			}
			// ... and player did not invest, then player doesn't win anything
			else {
				System.out.println("Whoops! That product actually ended up doing well. "
						+ "You missed out on the payoff, "
						+ "but at least you didn't lose anything.");
			}
		}
		
		System.out.println("The correct choice was: " + this.correctAnswer + "\n"
				+ "(The correct IRR was: " + ProductCalculations.displayIRR(this.product.getIRR()) + ")\n");
	}
	
}
