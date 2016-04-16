package com.psu.ie302.game;

import java.util.Random;

public final class Player {

	private static int score;
	
	public Player() {
		Player.score = 0;
	}
	
	// randomly generate MARR
	public static double generateMARR() {
		Random rand = new Random();
		double max = 0.5;
		return rand.nextDouble() * max;
	}

	public int getScore() {
		return Player.score;
	}

	public void setMoney(int moneyAmt) {
		Player.score = moneyAmt;
	}
	
	public void addScore(int amt) {
		Player.score += amt;
	}
}
