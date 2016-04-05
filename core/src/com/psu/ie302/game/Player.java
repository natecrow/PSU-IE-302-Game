package com.psu.ie302.game;

import java.util.Random;

public final class Player {

	private static int money;
	
	public Player(int initialMoney) {
		Player.money = initialMoney;
	}
	
	// randomly generate MARR
	public static double generateMARR() {
		Random rand = new Random();
		double max = 0.5;
		return rand.nextDouble() * max;
	}

	public int getMoney() {
		return Player.money;
	}

	public void setMoney(int moneyAmt) {
		Player.money = moneyAmt;
	}
	
	public void addMoney(int amt) {
		Player.money += amt;
	}
}
