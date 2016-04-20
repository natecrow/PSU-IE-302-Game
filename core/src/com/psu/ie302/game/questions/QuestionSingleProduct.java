package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct extends QuestionProducts {
	
	private Product product;
	
	
	public Product getProduct() {
		return product;
	}

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
		this.questionPrompt = "You are presented with the following product:\n"
				
				+ "    Product name: " + this.product.getName() + "\n"
				+ "    Company: " + this.product.getCompany() + "\n"
				+ "    Description: " + this.product.getDescription() + "\n\n"
				
				+ this.product.getCompany() + " is looking for an investment of $" 
				+ this.product.displayInitialInvestment() + ". "
				+ this.product.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows: "
				+ this.product.displayCashflows()
				
				+ "As an investor, you have set your MARR to " + this.displayMARR() +".";
	}
	
	@Override
	public void setCorrectAnswer() {
		if (this.product.getIRR() >= this.MARR) {
			this.correctAnswer = "Y";
		} else  {
			this.correctAnswer = "N";
		}
	}
	
	@Override
	// check answer and adjust score accordingly
	public String checkAndDisplayAnswerResults(String ans, Player player) {
		String results = "";
		
		// if answer is correct...
		if (this.checkAnswer(ans)) {
			player.addScore(1);
			// ... and player invested in one or both, then player wins money
			if (ans.equals("Y")) {
				results += "Wise investment - " 
						+ "you are likely to gain profit on that product!\n";
			}
			// ... and player didn't invest, then player doesn't lose anything
			else {
				results += "Good thing you didn't invest; " 
						+ "you probably would have lost money on that product.\n";
			}
		}
		// if answer is incorrect...
		else {
			// ... and player invested, then player loses money
			if (ans.equals("Y")) {
				results += "Bad investment - " 
						+ "you spent more than you'll likely gain on that product!\n";
			}
			// ... and player did not invest, then player doesn't win anything
			else {
				results += "Oops! You probably would have " 
						+ "gained profit if had you invested in that product.\n";
			}
		}
		
		results += "(The correct IRR is: "
				+ ProductCalculations.displayIRR(this.product.getIRR()) + ")";
	
		return results;
	}
	
}
