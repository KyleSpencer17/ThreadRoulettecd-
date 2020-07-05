package com.spencer.pojos;

public class PlayerHistory {

	private String name;
	private double bet;
	private double winnings;
	public PlayerHistory(String name, double bet, double winnings) {
		super();
		this.name = name;
		this.bet = bet;
		this.winnings = winnings;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBet() {
		return bet;
	}
	public void setBet(double bet) {
		this.bet = bet;
	}
	public double getWinnings() {
		return winnings;
	}
	public void setWinnings(double winnings) {
		this.winnings = winnings;
	}
	@Override
	public String toString() {
		return "PlayerHistory [name=" + name + ", bet=" + bet + ", winnings=" + winnings + "]";
	}
}
