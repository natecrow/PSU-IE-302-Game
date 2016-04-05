package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct extends Question {
	
	protected Product product;
	private double MARR;
	
	
	public QuestionSingleProduct(Product prod) {
		this.product = prod;
		prod.generateCashflows(3);
		prod.setIRR(ProductCalculations.calculateIRR(prod.getCashflows()));
		this.MARR = Player.generateMARR();
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	@Override
	public void setQuestionPrompt() {
		this.questionPrompt = "An investor walks into the room with the following product:\n"
				
				+ "\tProduct name: " + this.product.getName() + "\n"
				+ "\tCompany: " + this.product.getCompany() + "\n"
				+ "\tDescription: " + this.product.getDescription() + "\n"
				
				+ this.product.getCompany() + " is looking for an investment of $" 
				+ this.product.displayInitialInvestment() + ".\n"
				+ this.product.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows:\n"
				+ this.product.displayCashflows()
				
				+ "As an investor, you have set your MARR to " + this.displayMARR() +".\n"
				
				+ "Do you want to invest in this product? (Y = yes, N = no, D = doesn't matter)\n"
				
				// show IRR for answer checking
				+ "\tIRR: " + this.product.displayIRR() + "\n";
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
	
	//TODO: handle "neither" case
	public void checkAndDisplayAnswerResults(String ans, Player player) {
		// check answer and adjust score accordingly
		if (this.checkAnswer(ans)) {
			if (ans.equals("Y") || ans.equals("D")) {
				player.addMoney(100);
				System.out.println("Wise investment - "
						+ "the product paid off! You've earned $100.\n");
			} else {
				System.out.println("That product ended up failing, so"
						+ " good thing you didn't invest in it!\n");
			}
		} else {
			if (ans.equals("Y")) {
				player.addMoney(-100);
				System.out.println("Too bad, the product flopped. You lost $100.\n");
			} else {
				System.out.println("Whoops! That product actually ended up doing well. "
						+ "You missed out on the payoff, "
						+ "but at least you didn't lose anything.\n");
			}
		}
	}
	
	// returns MARR as a percentage
	public String displayMARR() {
		return (this.MARR * 100.0) + "%";
	}
	
	// use for background calculations
	public double getMARR() {
		return this.MARR;
	}

	public void setMARR(double prodMARR) {
		this.MARR = prodMARR;
	}
	
	/*
	public Product getQuestionProduct() {
		return this.product;
	}
	
	public void setQuestionProduct(Product prod) {
		this.product = prod;
	}
	*/
	
}
