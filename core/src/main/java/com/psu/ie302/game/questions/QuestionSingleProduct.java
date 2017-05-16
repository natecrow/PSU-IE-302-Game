package com.psu.ie302.game.questions;

import java.math.BigDecimal;

import com.badlogic.gdx.math.MathUtils;
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

	public QuestionSingleProduct(Product prod, int numOfYears) {
		super();
		
		this.product = prod;
		this.product.generateCashflows(numOfYears);
		this.product.setIRR(ProductCalculations.calculateIRR(prod.getCashflows()));
		this.MARR = BigDecimal.valueOf(MathUtils.random(0.5f)).setScale(4, BigDecimal.ROUND_HALF_UP);
		
		// if answer doesn't make sense, then recalculate cash flows and IRRs
		while (!this.setCorrectAnswer()) {
			this.product.generateCashflows(numOfYears);
			this.product.setIRR(ProductCalculations.calculateIRR(prod.getCashflows()));
		}
		
		this.setQuestionPrompt();
	}
	
	@Override
	public void setQuestionPrompt() {
		this.questionPrompt = "You are presented with the following product:\n"
				
				+ "    Product name: " + this.product.getName() + "\n"
				+ "    Company: " + this.product.getCompany() + "\n"
				+ "    Description: " + this.product.getDescription() + "\n\n"
				
				+ this.product.getCompany() + " is looking for an investment of " 
				+ this.product.displayInitialInvestment() + ". "
				+ this.product.getCompany() + " projects that with this investment in year 0, "
				+ "you will receive the following cash flows: "
				+ this.product.displayCashflows()
				
				+ "As an investor, you have set your MARR to " + this.displayMARR() +".";
	}
	
	@Override
	public boolean setCorrectAnswer() {
		// if irr < -1000% or irr > 1000%, then reject it
		// (remember irrDiff is a percentage, so
		//	i.e. 1.0 = 100%, 10.0 = 1000%, etc.)
		if ((product.getIRR().abs()).compareTo(BigDecimal.valueOf(10)) >= 0) {
			return false;
		}
		
		if ((product.getIRR()).compareTo(MARR) >= 0) {
			this.correctAnswer = "Y";
			return true;
		} else  {
			this.correctAnswer = "N";
			return true;
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
