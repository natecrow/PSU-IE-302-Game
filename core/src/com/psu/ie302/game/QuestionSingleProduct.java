package com.psu.ie302.game;

/*
 * Question for deciding to invest in one product or not
 */
public class QuestionSingleProduct {

	private String questionPrompt;
	//private String[] possibleAnswers;
	private String correctAnswer;	// either Yes or No
	private Product product;
	
	
	public QuestionSingleProduct(Product prod) {
		//this.possibleAnswers[0] = "Y";
		//this.possibleAnswers[1] = "N";
		this.product = prod;
		this.setQuestionPrompt();
		this.setCorrectAnswer();
	}
	
	// returns true if player's answer is correct; false otherwise
	public boolean checkAnswer(String ans) {
		return (ans.equals(this.correctAnswer));
	}
	
	public String getQuestionPrompt() {
		return this.questionPrompt;
	}
	
	private void setQuestionPrompt() {
		this.questionPrompt = "Check out the following product and "
				+ "decide whether you want to invest in it or not (Y/N).\n"
				+ "\tProduct name: " + this.product.getName() + "\n"
				+ "\tCompany: " + this.product.getCompany() + "\n"
				+ "\tDescription: " + this.product.getDescription() + "\n"
				+ "\tIRR: " + this.product.getIRR() + "\n"
				+ "\tMARR: " + this.product.getMARR() + "\n"
				+ "Do you want to invest in this product? Type 'Y' or 'N'.\n";
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	private void setCorrectAnswer() {
		if (this.product.getIRR() >= this.product.getMARR()) {
			this.correctAnswer = "Y";
		} else {
			this.correctAnswer = "N";
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
