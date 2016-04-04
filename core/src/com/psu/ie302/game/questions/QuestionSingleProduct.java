package com.psu.ie302.game.questions;

import com.psu.ie302.game.Product;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct extends Question {
	
	protected Product product;
	
	
	public QuestionSingleProduct(Product prod) {
		//this.possibleAnswers[0] = "Y";
		//this.possibleAnswers[1] = "N";
		this.product = prod;
		prod.generateMARR();
		prod.generateCashflows(3);
		prod.calculateIRR();
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	//protected String getQuestionPrompt();
	
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
				
				+ "Do you want to invest in this product? (Y/N)\n"
				
				// show IRR for answer checking
				+ "\tIRR: " + this.product.displayIRR() + "\n";
	}
	
	//public void getCorrectAnswer();
	
	public void setCorrectAnswer() {
		if (this.product.getIRR() >= this.product.getMARR()) {
			this.correctAnswer = "Y";
		} else {
			this.correctAnswer = "N";
		}
	}
	
	//public boolean checkAnswer(String ans);
	
	/*
	public Product getQuestionProduct() {
		return this.product;
	}
	
	public void setQuestionProduct(Product prod) {
		this.product = prod;
	}
	*/
	
}
