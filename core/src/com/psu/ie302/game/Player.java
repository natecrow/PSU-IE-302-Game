package com.psu.ie302.game;

public final class Player {

	private static int score;
	
	public Player() {
		Player.score = 0;
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
