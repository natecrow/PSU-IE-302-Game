package com.psu.ie302.game;

import java.util.Random;

public class Product {

	private String name;
	private String company;
	private String description;
	private double IRR;
	private int[] cashflows;
	
	
	/*
	 * Randomly generate the cash flows over 3 years
	 * Cash flow array structure:
	 * 		------------------------------
	 * 		 year	cash flow
	 * 		------------------------------
	 * 		 0		-(initial investment)
	 * 		 1		year 1's cash flow
	 * 		 2		year 2's cash flow
	 *		 ...	...
	 * 		------------------------------
	 */
	public void generateCashflows(int years) {
		
		// create space in cash flows array for the specified number of years
		cashflows = new int[years];
		
		Random rand = new Random();
		
		int max = 2500000; 	// max investment
		int min = 1000;		// min investment
		
		// put negative initial investment into cash flow for year 0
		this.cashflows[0] = -(rand.nextInt((max - min) + 1) + min);
		
		// randomly generate and store the subsequent years of cash flows
		for (int i = 1; i < years; i++) {
			
			// max: previous year's cash flow
			// min: half of previous year's cash flow
			max = Math.abs(cashflows[i-1]);
			min = Math.abs(cashflows[i-1] / 2);

			this.cashflows[i] = (rand.nextInt((max - min) + 1) + min);
		}
	}
	
	// return string of cash flow information after year zero
	public String displayCashflows() {
		String result = "";
		for (int i = 1; i < this.cashflows.length; i++) {
			result += "\t$" + this.cashflows[i]
					+ " in year " + i + "\n";
		}
		return result;
	}
	
	public int displayInitialInvestment() {
		return Math.abs(this.cashflows[0]);
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String prodName) {
		this.name = prodName;
	}
	
	public String getCompany() {
		return this.company;
	}
	
	public void setCompany(String prodCompany) {
		this.company = prodCompany;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String prodDesc) {
		this.description = prodDesc;
	}
	
	// use for background calculations
	public double getIRR() {
		return this.IRR;
	}
	
	public void setIRR(double prodIRR) {
		this.IRR = prodIRR;
	}
	
	public int[] getCashflows() {
		return this.cashflows;
	}
	
	public void setCashflows(int[] prodCashflows) {
		this.cashflows = prodCashflows;
	}
	
}
