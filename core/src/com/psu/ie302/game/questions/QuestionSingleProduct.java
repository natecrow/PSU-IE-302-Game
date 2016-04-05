package com.psu.ie302.game.questions;

import com.psu.ie302.game.Product;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct extends Question {
	
	protected Product product;
	
	
	public QuestionSingleProduct(Product prod) {
		this.product = prod;
		prod.generateMARR();
		prod.generateCashflows(3);
		prod.calculateIRR();
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	@Override
	public void setQuestionPrompt() {
		//TODO: delete IRR once it is calculated automatically
		this.questionPrompt = "An investor walks into the room with the following product:\n"
				
				+ "\tProduct name: " + this.product.getName() + "\n"
				+ "\tCompany: " + this.product.getCompany() + "\n"
				+ "\tDescription: " + this.product.getDescription() + "\n"
				
				+ this.product.getCompany() + " is looking for an investment of $" 
				+ this.product.displayInitialInvestment() + ".\n"
				+ this.product.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows:\n"
				+ this.product.displayCashflows()
				
				+ "As an investor, you have set your MARR to " + this.product.displayMARR() +".\n"
				
				+ "Do you want to invest in this product? (Y = yes, N = no, D = doesn't matter)\n"
				
				// show IRR for answer checking
				+ "\tIRR: " + this.product.displayIRR() + "\n";
	}
	
	@Override
	public void setCorrectAnswer() {
		if (this.product.getIRR() > this.product.getMARR()) {
			this.correctAnswer = "Y";
		} else if (this.product.getIRR() < this.product.getMARR()) {
			this.correctAnswer = "N";
		} else { // IRR == MARR
			this.correctAnswer = "D";
		}
	}
	
	//TODO: handle "neither" case
	public void checkAndDisplayAnswerResults(String ans, int money) {
		// check answer and adjust score accordingly
		if (this.checkAnswer(ans)) {
			if (ans.equals("Y")) {
				money += 100;
				System.out.println("Wise investment - "
						+ "the product paid off! You've earned $100.\n");
			} else {
				System.out.println("That product ended up failing, so"
						+ " good thing you didn't invest in it!\n");
			}
		} else {
			if (ans.equals("Y")) {
				money -= 100;
				System.out.println("Too bad, the product flopped. You lost $100.\n");
			} else {
				System.out.println("Whoops! That product actually ended up doing well. "
						+ "You missed out on the payoff, "
						+ "but at least you didn't lose anything.\n");
			}
		}
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
