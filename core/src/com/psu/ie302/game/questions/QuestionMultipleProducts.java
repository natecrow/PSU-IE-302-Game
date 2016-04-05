package com.psu.ie302.game.questions;

import com.psu.ie302.game.Player;
import com.psu.ie302.game.Product;
import com.psu.ie302.game.ProductCalculations;

public class QuestionMultipleProducts extends Question {

	protected Product product1;
	protected Product product2;
	@SuppressWarnings("unused")
	private double MARR;
	
	
	public QuestionMultipleProducts(Product prod1, Product prod2) {
		
		this.product1 = prod1;
		this.product2 = prod2;
		
		prod1.generateCashflows(4);
		prod2.generateCashflows(4);
		
		prod1.setIRR(ProductCalculations.calculateIRR(prod1.getCashflows()));
		prod2.setIRR(ProductCalculations.calculateIRR(prod2.getCashflows()));
		
		this.MARR = Player.generateMARR();
		
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	@Override
	public void setQuestionPrompt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/*
	 * cases:
	 * 1. both IRRs > MARR
	 * 		1.2 1's IRR > 2's IRR
	 * 			ans = 1
	 * 		1.2 1's IRR < 2's IRR
	 * 			ans = 2
	 * 		1.3 IRR the same
	 * 			ans = 0 (either)
	 * 2. 1's IRR > MARR, 2's IRR < MARR
	 * 		ans = 1
	 * 3. 1's IRR < MARR, 2's IRR > MARR
	 * 		ans = 2
	 * 4. both IRRs < MARR
	 * 		ans = 4 (neither)
	 * TODO: finish this method
	 */
	public void setCorrectAnswer() {
		if (this.product1.getIRR() > this.product2.getIRR()) {
			this.correctAnswer = "1";
		} else if (this.product1.getIRR() < this.product2.getIRR()) {
			this.correctAnswer = "2";
		}
		
	}

}
