package com.psu.ie302.game;

public class Product {

	private String name;
	private String company;
	private String description;
	private float IRR;
	private float MARR;


	public Product(String pName, String pCo, String pDesc, float irr, float marr) {
		this.name = pName;
		this.company = pCo;
		this.description = pDesc;
		this.IRR = irr;
		this.MARR = marr;
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
	
	public float getIRR() {
		return this.IRR;
	}
	
	public void setIRR(float prodIRR) {
		this.IRR = prodIRR;
	}
	
	public float getMARR() {
		return this.MARR;
	}
	
	public void setMARR(float prodMARR) {
		this.MARR = prodMARR;
	}
	
	// randomly generate the IRR and MARR
	public void generateIRRandMARR() {
		;
	}
}
